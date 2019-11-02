package com.crazy.portal.service.forecast;

import com.alibaba.fastjson.JSONObject;
import com.crazy.portal.bean.customer.CustomerOrgBean;
import com.crazy.portal.bean.forecast.*;
import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.dao.forecast.ForecastLineMapper;
import com.crazy.portal.dao.forecast.ForecastMapper;
import com.crazy.portal.dao.forecast.ForecastSdMapper;
import com.crazy.portal.entity.cusotmer.CustomerInfo;
import com.crazy.portal.entity.forecast.Forecast;
import com.crazy.portal.entity.forecast.ForecastLine;
import com.crazy.portal.entity.forecast.ForecastSd;
import com.crazy.portal.entity.price.CatalogPrice;
import com.crazy.portal.entity.product.ProductInfoDO;
import com.crazy.portal.entity.system.User;
import com.crazy.portal.service.customer.CustomerInfoService;
import com.crazy.portal.service.price.CatalogPriceService;
import com.crazy.portal.service.product.ProductService;
import com.crazy.portal.service.system.UserCustomerMappingService;
import com.crazy.portal.util.*;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

import static com.crazy.portal.util.Enums.BI_FUNCTION_CODE.*;
import static com.crazy.portal.util.ErrorCodes.BusinessEnum.*;

/**
 * Created by lee on 2019/8/18.
 */

@Slf4j
@Service
public class SaleForecastService {

    @Resource
    private ForecastMapper forecastMapper;
    @Resource
    private ForecastLineMapper forecastLineMapper;
    @Resource
    private FTPClientUtil ftpClientUtil;
    @Resource
    private CustomerInfoService customerInfoService;
    @Resource
    private ForecastSdMapper forecastSdMapper;
    @Resource
    private UserCustomerMappingService userCustomerMappingService;
    @Resource
    private ProductService productService;
    @Resource
    private CatalogPriceService catalogPriceService;

    //Portal 本地文件交互地址
    @Value("${file.path.forecast.push}")
    private String forecastPushPath;
    @Value("${file.path.forecast.pull}")
    private String forecastPullPath;

    //BI FTP交互地址
    @Value("${ftp.path.forecast.push}")
    private String ftpUploadPath;
    @Value("${ftp.path.forecast.pull}")
    private String ftpDownloadPath;

    //是否模拟BI服务
    @Value("${portal.mock-bi-server}")
    private boolean mockBiServer;

    private String SUCCESS_CODE = "OK";
    private String ERROR_CODE = "NG";

    /**
     * 代理商预测模版下载
     * @param response
     * @param yearMonth
     * @param userId
     */
    public void downloadAgencyTemplate(HttpServletResponse response, String yearMonth, Integer userId){
        BusinessUtil.assertEmpty(yearMonth, FORECAST_YEAR_MONTH_FORMAT_ERROR);
        String lastYearMonth;
        try {
            Date lastYearMonthDate = DateUtil.parseDate(yearMonth, DateUtil.MONTH_FORMAT);
            lastYearMonth = DateUtil.format(lastYearMonthDate, DateUtil.MONTH_FORMAT);
        }catch (Exception ex) {
            log.error(FORECAST_YEAR_MONTH_FORMAT_ERROR.getZhMsg() + "yearMonth:" + yearMonth, ex);
            throw new BusinessException(FORECAST_YEAR_MONTH_FORMAT_ERROR);
        }
        List<Forecast> forecastList = forecastMapper.selectByYearMonth(lastYearMonth, userId);
        if(null == forecastList || forecastList.isEmpty()){
            ExcelUtils.writeExcel(response, Collections.singletonList(new AgencyTemplate()), AgencyTemplate.class);
        }
        List<AgencyTemplate> templateList = new ArrayList<>();
        for(Forecast forecast : forecastList) {
            AgencyTemplate agencyTemplate = new AgencyTemplate();
            copyDbFields(forecast, agencyTemplate);

            agencyTemplate.setOperationYearMonth("");
            agencyTemplate.setCloseDate("");
            agencyTemplate.setDelayStock("");

            agencyTemplate.setForecastMonthOne(forecast.getLine().getForecastMonthTwo());
            agencyTemplate.setLastWriteOne(forecast.getLine().getCurrentWriteTwo());
            agencyTemplate.setCurrentWriteOne("");

            agencyTemplate.setForecastMonthTwo(forecast.getLine().getForecastMonthThree());
            agencyTemplate.setLastWriteTwo(forecast.getLine().getCurrentWriteThree());
            agencyTemplate.setCurrentWriteTwo("");

            agencyTemplate.setForecastMonthThree(forecast.getLine().getForecastMonthFour());
            agencyTemplate.setLastWriteThree(forecast.getLine().getCurrentWriteFour());
            agencyTemplate.setCurrentWriteThree("");

            agencyTemplate.setForecastMonthFour(forecast.getLine().getForecastMonthFive());
            agencyTemplate.setLastWriteFour(forecast.getLine().getCurrentWriteFive());
            agencyTemplate.setCurrentWriteFour("");

            agencyTemplate.setForecastMonthFive(forecast.getLine().getForecastMonthSix());
            agencyTemplate.setLastWriteFive(forecast.getLine().getCurrentWriteSix());
            agencyTemplate.setCurrentWriteFive("");

            agencyTemplate.setForecastMonthSix("");
            agencyTemplate.setLastWriteSix("");
            agencyTemplate.setCurrentWriteSix("");

            templateList.add(agencyTemplate);
        }
        ExcelUtils.writeExcel(response, templateList, AgencyTemplate.class);
    }

    /**
     * 代理商预测数据上传
     * 1：自动带出客户属性
     * 2：自动带出产品属性
     * 3：进行入库动作
     * 4：传输给到BI进行数据check
     * @param excel
     * @return
     */
    @Transactional
    public ForecastResult uploadAgencyTemplate(MultipartFile excel, User user) {
        BusinessUtil.notNull(excel, FORECAST_EXCEL_CHECK_ERROR);
        //获取代理商信息
        CustomerInfo customerInfo = new CustomerInfo();
        String agencyAbbreviation = new String();
        if(user.getUserType().equals(Enums.USER_TYPE.agent.toString())){
            customerInfo = getCustomerInfo(user.getId());
            agencyAbbreviation = customerInfo.getCustAbbreviation();
        }
        List<AgencyTemplate> agencyForecastList = ExcelUtils.readExcel(excel, AgencyTemplate.class);
        if(null == agencyForecastList || agencyForecastList.isEmpty()){
            log.warn(FORECAST_DATA_NOT_EMPTY.getZhMsg());
            throw new BusinessException(FORECAST_DATA_NOT_EMPTY);
        }
        String batchNo = generateBathNo();
        for(AgencyTemplate template : agencyForecastList){
            boolean isDate = BusinessUtil.isDateTime(template.getCloseDate());
            BusinessUtil.assertTrue(isDate, FORECAST_DATE_FORMAT_ERROR);

            //如果 当前人不是代理商  代理商取模版中的客户信息
            if(!user.getUserType().equals(Enums.USER_TYPE.agent.toString())){
                agencyAbbreviation = template.getCustomerAbbreviation();
            }
            template.setAgencyAbbreviation(agencyAbbreviation);
            template.setChannel("A04".equals(customerInfo.getBusinessType()) ? "代理" : "直供");
            //获取代理商上级客户信息
            CustomerOrgBean customerOrgBean = getCustomerOrgInfo(template.getCustomerAbbreviation());
            ProductInfoDO productInfo = getProductInfo(template.getProductModel(), template.getPlatform());
            Forecast forecast = new Forecast(user.getId());
            copyTemplateFields(template, forecast);
            forecast.setAgencyAbbreviation(agencyAbbreviation);
            //设置产品字段
            forecast.setBu(productInfo.getBu());
            forecast.setPdt(productInfo.getPdt());
            forecast.setProductType(productInfo.getSubCategory());
            forecast.setVmNumber(productInfo.getSapMid());
            //设置客户字段
            forecast.setCustomerType(customerOrgBean.getCustType());
            forecast.setSalePeople(customerOrgBean.getSales());
            forecast.setAmbLeader(customerOrgBean.getAmb());
            forecast.setSdPeople(customerOrgBean.getPm());
            forecast.setRepresentative(customerOrgBean.getOffice());
            //当前操作批次
            forecast.setBatchNo(batchNo);
            int cnt = forecastMapper.checkRecord(forecast);
            BusinessUtil.assertTrue((cnt == 0), FORECAST_DATA_REPEAT_ERROR);
            forecastMapper.insertSelective(forecast);
            if(log.isDebugEnabled()){
                log.debug("[upload data] Save forecast head data , userId:{} , data:{}", user.getId(), JSONObject.toJSON(forecast));
            }
            ForecastLine line = new ForecastLine();
            copyTemplateFields(template, line);
            line.setfId(forecast.getId());
            //重新获取上一次的填写值
            setLastValue(forecast, line);
            forecastLineMapper.insertSelective(line);
            if(log.isDebugEnabled()){
                log.debug("[upload data] Save forecast line data , userId:{} , data:{}", user.getId(), JSONObject.toJSON(line));
            }
//            template.setId(String.valueOf(forecast.getId()));
        }
        return checkForecastData(batchNo);
    }

    /**
     * 下载错误数据文件
     * @param response
     * @param batchNo
     * @param userId
     */
    public void downloadAgencyError(HttpServletResponse response, String batchNo, Integer userId) {
        if(log.isDebugEnabled()){
            log.debug("Download error data file, parameter value : batchNo[{}], userId:[{}]", batchNo, userId);
        }
        BusinessUtil.assertEmpty(batchNo, FORECAST_REQ_PARAM_NOT_EMPTY);
        BusinessUtil.notNull(userId, FORECAST_REQ_PARAM_NOT_EMPTY);
        List<Forecast> errorTemplateList = forecastMapper.selectErrorDataByBatch(batchNo, userId);
        if(null == errorTemplateList || errorTemplateList.isEmpty()){
            log.warn("[Download error file] error data is empty, return empty file.");
            ExcelUtils.writeExcel(response, Collections.singletonList(new AgencyErrorTemplate()), AgencyErrorTemplate.class);
            return;
        }
        List<AgencyErrorTemplate> errorList = new ArrayList<>();
        for(Forecast forecast : errorTemplateList) {
            AgencyErrorTemplate agencyTemplate = new AgencyErrorTemplate();
            agencyTemplate.setForecastId(String.valueOf(forecast.getId()));
            copyDbFields(forecast, agencyTemplate);
            errorList.add(agencyTemplate);
        }
        ExcelUtils.writeExcel(response, errorList, AgencyErrorTemplate.class);
    }

    /**
     * 代理商预测数据 修正
     * @param excel
     * @param batchNo
     * @param userId
     * @return
     */
    @Transactional
    public ForecastResult modifyErrorData(MultipartFile excel, String batchNo, Integer userId) {
        if(log.isDebugEnabled()){
            log.debug("Modify error data, parameter value : batchNo[{}] , userId:[{}]", batchNo, userId);
        }
        BusinessUtil.notNull(excel, FORECAST_EXCEL_CHECK_ERROR);
        BusinessUtil.assertEmpty(batchNo, FORECAST_REQ_PARAM_NOT_EMPTY);
        List<AgencyErrorTemplate> errorTemplateList = ExcelUtils.readExcel(excel, AgencyErrorTemplate.class);
        if(null == errorTemplateList || errorTemplateList.isEmpty()){
            log.warn(FORECAST_DATA_NOT_EMPTY.getZhMsg());
            throw new BusinessException(FORECAST_DATA_NOT_EMPTY);
        }
//        int dbCnt = forecastMapper.countErrorDataByBatch(batchNo, userId);
//        if(dbCnt != errorTemplateList.size()){
//            log.error("{} , parameter value : batchNo[{}]", FORECAST_DB_DATA_MISMATCH.getZhMsg(), batchNo);
//            throw new BusinessException(FORECAST_DB_DATA_MISMATCH);
//        }
        //将数据库中的错误信息清除
        forecastMapper.clearErrorMsgByBatch(batchNo);
        for(AgencyErrorTemplate errorTemplate : errorTemplateList){
            boolean isDate = BusinessUtil.isDateTime(errorTemplate.getCloseDate());
            BusinessUtil.assertTrue(isDate, FORECAST_DATE_FORMAT_ERROR);
            Forecast forecast = forecastMapper.selectByPrimaryKey(Integer.parseInt(errorTemplate.getForecastId()));
            //不允许用户擅自更改ID，每行数据都要进行校验
            BusinessUtil.notNull(forecast, FORECAST_DB_DATA_MISMATCH);
            BusinessUtil.assertTrue(batchNo.equals(forecast.getBatchNo()), FORECAST_DB_DATA_MISMATCH);
            copyTemplateFields(errorTemplate, forecast);
            forecast.setErrorMsg("clear");
            forecastMapper.updateByPrimaryKeySelective(forecast);
            //update line record
            ForecastLine line = forecastLineMapper.selectByForecastId(forecast.getId());
            copyTemplateFields(errorTemplate, line);
            forecastLineMapper.updateByPrimaryKeySelective(line);
        }
        return checkForecastData(batchNo);
    }

    /**
     * 代理商预测数据提交至 下一流程进行审批
     * @param batchNo
     * @param userId
     */
    public void commitAgencyForecastData(String batchNo, Integer userId) {
        //提交前检测是否存在错误数据
        int num = forecastMapper.countErrorDataByBatch(batchNo, userId);
        if(num != 0){
            log.error("Check error data num , parameter value : batchNo[{}] , userId:[{}]", batchNo, userId);
            throw new BusinessException(FORECAST_ERROR_DATA_EXISTS);
        }
        //状态解释  agencyStatusType 1:请求新增   status    1:待审批
        forecastMapper.updateStatus(1,1, batchNo, userId);
    }

    /**
     * 代理商查询被驳回数据
     * @param pageNum
     * @param pageSize
     * @param userId
     * @return
     */
    public PageInfo<Forecast> queryAgencyRejectForecastData(Integer pageNum, Integer pageSize, Integer userId) {
        PortalUtil.defaultStartPage(pageNum,pageSize);
        List<Forecast> result = forecastMapper.selectPageByUser(userId, null, -1, null, null, null);
        return new PageInfo<>(result);

    }

    /**
     * 代理商查询数据（不包含驳回数据及未走完流程的数据）
     * @param pageNum
     * @param pageSize
     * @param userId
     * @param customerAbbreviation
     * @param status
     * @param salePeople
     * @param uploadStartTime
     * @param uploadEndTime
     * @return
     */
    public PageInfo<Forecast> queryAgencyForecastData(Integer pageNum, Integer pageSize, Integer userId,
                                                      String customerAbbreviation, Integer status, String salePeople,
                                                      String uploadStartTime, String uploadEndTime) {
        if(null != status && status == -1){
            return new PageInfo<>(null);
        }
        PortalUtil.defaultStartPage(pageNum,pageSize);
        List<Forecast> result = forecastMapper.selectPageByUser(userId, customerAbbreviation, status, salePeople, uploadStartTime, uploadEndTime);
        return new PageInfo<>(result);
    }

    /**
     * 代理商预测数据删除
     * @param forecastIds
     */
    @Transactional
    public void deleteAgencyForecastData(Integer[] forecastIds) {
        for(Integer id : forecastIds){
            Forecast forecast = forecastMapper.selectByPrimaryKey(id);
            if(forecast.getStatus() == 3){
                throw new BusinessException(FORECAST_OPERATION_DELETE_REPEAT);
            }
            BusinessUtil.notNull(forecast, FORECAST_DB_DATA_MISMATCH);
            //数据已驳回，无法删除
            if(forecast.getStatus() == -1){
                throw new BusinessException(FORECAST_REJECT_DATA_NOT_DELETE);
            }
            //数据未提交，直接进行删除
            if(forecast.getStatus() == 1){
                forecastMapper.deleteByPrimaryKey(id);
                continue;
            }
            //数据已经提交，则需要提交给审批人员进行删除
            if(forecast.getStatus() == 2){
                forecast.setAgencyStatusType(-1);
                forecast.setStatus(3);
                forecastMapper.updateByPrimaryKeySelective(forecast);
                continue;
            }
        }
    }

    /**
     * 代理商预测数据修改
     * @param list
     * @param userId
     */
    @Transactional
    public void updateAgencyForecastData(List<ForecastParam> list, Integer userId) {
        BusinessUtil.notNull(list, FORECAST_REQ_PARAM_NOT_EMPTY);
        for(ForecastParam data : list){
            Forecast forecast = forecastMapper.selectByPrimaryKey(data.getForecastId());
            if(null == forecast){
                log.error("{} , parameter value : {}", FORECAST_DB_DATA_MISMATCH.getZhMsg(), data);
                throw new BusinessException(FORECAST_DB_DATA_MISMATCH);
            }
            if(forecast.getStatus() == 4){
                throw new BusinessException(FORECAST_OPERATION_UPDATE_REPEAT);
            }
            //数据已驳回，无法修改
            if(forecast.getStatus() == -1){
                throw new BusinessException(FORECAST_REJECT_DATA_NOT_DELETE);
            }
            ForecastLine line = data.getLine();
            ForecastLine dbLine = forecastLineMapper.selectByPrimaryKey(line.getLineId());
            BusinessUtil.notNull(list, FORECAST_DB_DATA_MISMATCH);
            if(StringUtils.isNotEmpty(line.getCurrentWriteOne())){
                dbLine.setCurrentWriteOne(line.getCurrentWriteOne());
            }
            if(StringUtils.isNotEmpty(line.getCurrentWriteTwo())){
                dbLine.setCurrentWriteTwo(line.getCurrentWriteTwo());
            }
            if(StringUtils.isNotEmpty(line.getCurrentWriteThree())){
                dbLine.setCurrentWriteThree(line.getCurrentWriteThree());
            }
            if(StringUtils.isNotEmpty(line.getCurrentWriteFour())){
                dbLine.setCurrentWriteFour(line.getCurrentWriteFour());
            }
            if(StringUtils.isNotEmpty(line.getCurrentWriteFive())){
                dbLine.setCurrentWriteFive(line.getCurrentWriteFive());
            }
            if(StringUtils.isNotEmpty(line.getCurrentWriteSix())){
                dbLine.setCurrentWriteSix(line.getCurrentWriteSix());
            }
            //数据已提交，修改需提交审批人再提交BI
            if(forecast.getStatus() == 2){
                forecast.setStatus(4);
                forecast.setAgencyStatusType(2);
            }
            forecastLineMapper.updateByPrimaryKeySelective(dbLine);
            forecast.setUpdateTime(new Date());
            forecast.setUpdateUserId(userId);
            forecastMapper.updateByPrimaryKey(forecast);
        }
    }

    /**
     * 被驳回数据下载
     * @param response
     * @param forecastIds
     * @param userId
     */
    public void downloadRejectData(HttpServletResponse response, Integer[] forecastIds, Integer userId) {
        BusinessUtil.notNull(forecastIds, FORECAST_REQ_PARAM_NOT_EMPTY);
        List<Forecast> rejectDataList = forecastMapper.selectRejectDataByIds(forecastIds, userId);

        List<AgencyRejectTemplate> rejectList = new ArrayList<>();
        for(Forecast forecast : rejectDataList) {
            AgencyRejectTemplate rejectTemplate = new AgencyRejectTemplate();
            copyDbFields(forecast, rejectTemplate);
            rejectList.add(rejectTemplate);
        }
        ExcelUtils.writeExcel(response, rejectList, AgencyRejectTemplate.class);
    }

    /**
     * 查询代理商预测数据
     * @return
     */
    public PageInfo<Forecast> queryApprovalForecastData(Integer pageNum, Integer pageSize, Integer userId,
                                                        String customerAbbreviation, String salePeople,
                                                        String uploadStartTime, String uploadEndTime,
                                                        String ambPeople, String sdPeople, String agencyAbbreviation,
                                                        String channel) {
        List<Integer> userList;
        PortalUtil.defaultStartPage(pageNum,pageSize);
        try {
            userList = userCustomerMappingService.selectUserMapping(userId, Enums.CustomerMappingModel.Forecast.getValue());
            Integer[] userIds = new Integer[userList.size()];
            List<Forecast> result = forecastMapper.selectByLeader(userList.toArray(userIds), customerAbbreviation,
                    salePeople, uploadStartTime, uploadEndTime, ambPeople, sdPeople, agencyAbbreviation, channel);
            return new PageInfo<>(result);
        }catch (Exception ex) {
            log.error(FORECAST_AGENCY_QUERY_ERROR.getZhMsg(), ex);
            throw new BusinessException(FORECAST_AGENCY_QUERY_ERROR);
        }
    }

    public PageInfo<Forecast> queryBiForecastData(Integer pageNum, Integer pageSize, Integer userId,
                                                        String customerAbbreviation, String salePeople,
                                                        String uploadStartTime, String uploadEndTime,
                                                        String agencyAbbreviation, String channel) {
        try {
            List<Integer> userList = userCustomerMappingService.selectUserMapping(userId, Enums.CustomerMappingModel.Forecast.getValue());
            Integer[] userIds = new Integer[userList.size()];
            PortalUtil.defaultStartPage(pageNum,pageSize);
            List<Forecast> result = forecastMapper.selectBiDataByLeader(userList.toArray(userIds), customerAbbreviation,
                    salePeople, uploadStartTime, uploadEndTime, agencyAbbreviation, channel);
            return new PageInfo<>(result);
        }catch (Exception ex) {
            log.error(FORECAST_AGENCY_QUERY_ERROR.getZhMsg(), ex);
            throw new BusinessException(FORECAST_AGENCY_QUERY_ERROR);
        }
    }

    /**
     * 首代的展示方式查询代理商预测数据
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<ForecastSd> queryForecastDataBySd(Integer pageNum, Integer pageSize){
        PortalUtil.defaultStartPage(pageNum,pageSize);
        List<ForecastSd> result = forecastSdMapper.selectPage();
        return new PageInfo<>(result);
    }

    /**
     * 代理商预测数据单条修改
     * @param param
     */
    public void updateSingleForecastData(ForecastParam param) {
        ApprovalUpdateLineParam paramLine = param.getUpdateLine();
        ForecastLine dbRecord = forecastLineMapper.selectByPrimaryKey(paramLine.getLineId());
        if(null == dbRecord){
            throw new BusinessException("没有记录");
        }
        if(param.getSortNum() == 1){
            dbRecord.setAmbAdjustmentOne(paramLine.getAmbAdjustment());
            dbRecord.setAmbRemarkOne(paramLine.getAmbRemark());
        }
        if(param.getSortNum() == 2){
            dbRecord.setAmbAdjustmentTwo(paramLine.getAmbAdjustment());
            dbRecord.setAmbRemarkTwo(paramLine.getAmbRemark());
        }
        if(param.getSortNum() == 3){
            dbRecord.setAmbAdjustmentThree(paramLine.getAmbAdjustment());
            dbRecord.setAmbRemarkThree(paramLine.getAmbRemark());
        }
        if(param.getSortNum() == 4){
            dbRecord.setAmbAdjustmentFour(paramLine.getAmbAdjustment());
            dbRecord.setAmbRemarkFour(paramLine.getAmbRemark());
        }
        if(param.getSortNum() == 5){
            dbRecord.setAmbAdjustmentFive(paramLine.getAmbAdjustment());
            dbRecord.setAmbRemarkFive(paramLine.getAmbRemark());
        }
        if(param.getSortNum() == 6){
            dbRecord.setAmbAdjustmentSix(paramLine.getAmbAdjustment());
            dbRecord.setAmbRemarkSix(paramLine.getAmbRemark());
        }
        forecastLineMapper.updateByPrimaryKeySelective(dbRecord);
    }

    /**
     * 代理商预测数据批量通过
     * @param forecastIds
     * @param passMsg
     */
    @Transactional
    public void passApprovalForecastData(Integer[] forecastIds, String passMsg, Integer userId) {
        //校验是否属于同一类型的数据操作（删除/修改/新增）
        List<Integer> typeList =  forecastMapper.selectOperationTypeByIds(forecastIds);
        if(null == typeList || typeList.size() != 1){
            log.error("【预测数据通过】不能同时执行'删除/修改/新增'操作，ID:{}, type:{}", forecastIds, typeList);
            throw new BusinessException(FORECAST_DATA_TYPE_DISUNITY);
        }
        //第一步：判定是否有跨月操作的行为
        List<String> monthList = forecastMapper.selectMonthByIds(forecastIds);
        if(null == monthList || monthList.size() != 1){
            log.error("【预测数据通过】同时操作多月份数据异常，ids：{}", forecastIds);
            throw new BusinessException(FORECAST_CHECK_IDENTICAL_MONTH_ERROR);
        }
        List<Forecast> operationData = forecastMapper.selectByIds(forecastIds);
        Integer type = typeList.get(0);
        /**
         * 删除可以执行部分删除
         */
        if(type == -1){
            operationDelete(operationData);
            return;
        }
        /**
         * 修改可以批量修改
         */
        if(type == 2){
            operationUpdate(operationData, forecastIds, passMsg);
            return;
        }
        //第二步：获取当前用户下的代理商
        List<Integer> userList;
        try {
            userList = userCustomerMappingService.selectUserMapping(userId, Enums.CustomerMappingModel.Forecast.getValue());
        }catch (Exception ex) {
            log.error(FORECAST_AGENCY_QUERY_ERROR.getZhMsg(), ex);
            throw new BusinessException(FORECAST_AGENCY_QUERY_ERROR);
        }
        //第三步：判定是否操作的当月全量代理商数据
        int dataTotalNum = forecastMapper.countDataNumByMonthAndUser(userList, monthList.get(0));
        BusinessUtil.assertTrue(dataTotalNum == forecastIds.length, FORECAST_DATA_TOTAL_CHECK_ERROR);
        //第四步：是否数据都已经被调整过，否则不允许通过
//        int ambAdjustmentNum = forecastMapper.checkAmbAdjustmentNum(forecastIds);
//        BusinessUtil.assertTrue(ambAdjustmentNum == 0, FORECAST_AMB_ADJUSTMENT_NUM_ERROR);
        /**
         * 新增需要全部一起新增
         */
        if(type == 1){
            operationInsert(operationData, forecastIds, passMsg);
            return;
        }
        throw new BusinessException(FORECAST_DATA_TYPE_DISUNITY);
    }

    /**
     * 代理商预测数据驳回
     * @param forecastIds
     * @param rejectMsg
     */
    public void rejectApprovalForecastData(Integer[] forecastIds, String rejectMsg) {
        //如果选择驳回，则不管数据选择多少，都将待提交的数据全部驳回
        forecastMapper.rejectDataByIds(forecastIds, rejectMsg);
        /*for(Integer id : forecastIds) {
            Forecast forecast = forecastMapper.selectByPrimaryKey(id);
            if(forecast.getStatus() == 2){
                throw new BusinessException(FORECAST_ALREADY_COMMIT_NOT_REJECT);
            }
        }
        forecastMapper.updateStatusByIds(forecastIds, -1, rejectMsg);*/
    }

    /**
     *
     * @param response
     * @param forecastIds
     */
    public void downloadDataByAmb(HttpServletResponse response, Integer[] forecastIds) {
        List<Forecast> forecastList = forecastMapper.selectByIds(forecastIds);
        List<AmbUpdateTemplate> templateList = new ArrayList<>();
        for(Forecast forecast : forecastList) {
            AmbUpdateTemplate agencyTemplate = new AmbUpdateTemplate();
            agencyTemplate.setPoPrice(getPriceByProduct(forecast.getProductModel(), forecast.getPlatform(), forecast.getCompany()));
            copyDbFields(forecast, agencyTemplate);
            agencyTemplate.setId(String.valueOf(forecast.getId()));
            agencyTemplate.setTotalForecastSalesVolume(sumValue(agencyTemplate));
            templateList.add(agencyTemplate);
        }
        ExcelUtils.writeExcel(response, templateList, AmbUpdateTemplate.class);
    }

    public void uploadDataByAmb(MultipartFile excel, Integer userId) {
        List<AmbUpdateTemplate> ambList = ExcelUtils.readExcel(excel, AmbUpdateTemplate.class);
        for(AmbUpdateTemplate ambUpdateTemplate : ambList){
            ForecastLine forecastLine = new ForecastLine();
            copyTemplateFields(ambUpdateTemplate, forecastLine);
            forecastLine.setfId(Integer.parseInt(ambUpdateTemplate.getId()));
            forecastMapper.updatePoPriceById(Integer.parseInt(ambUpdateTemplate.getId()), ambUpdateTemplate.getPoPrice());
            forecastLineMapper.updateByForecastId(forecastLine);
        }
    }

    /**
     * 首代下载代理商预测数据，目前必须下载全部的
     * @param response
     * @param forecastIds
     */
    public void downloadDataBySd(HttpServletResponse response, Integer[] forecastIds, Integer userId) {
        //第一步：判定是否有跨月操作的行为
        List<String> monthList = forecastMapper.selectMonthByIds(forecastIds);
        if(null == monthList || monthList.size() != 1){
            log.error("【预测数据通过】同时操作多月份数据异常，ids：{}", forecastIds);
            throw new BusinessException(FORECAST_CHECK_IDENTICAL_MONTH_ERROR);
        }
        //第二步：获取当前用户下的代理商
        List<Integer> userList;
        try {
            userList = userCustomerMappingService.selectUserMapping(userId, Enums.CustomerMappingModel.Forecast.getValue());
        }catch (Exception ex) {
            log.error(FORECAST_AGENCY_QUERY_ERROR.getZhMsg(), ex);
            throw new BusinessException(FORECAST_AGENCY_QUERY_ERROR);
        }
        //第三步：判定是否操作的当月全量代理商数据
        int dataTotalNum = forecastMapper.countDataNumByMonthAndUser(userList, monthList.get(0));
        BusinessUtil.assertTrue(dataTotalNum == forecastIds.length, FORECAST_DATA_TOTAL_CHECK_ERROR);
        //第四步：是否数据都已经被调整过，否则不允许通过
//        int ambAdjustmentNum = forecastMapper.checkAmbAdjustmentNum(forecastIds);
//        BusinessUtil.assertTrue(ambAdjustmentNum == 0, FORECAST_AMB_ADJUSTMENT_NUM_ERROR);

        List<SdUpdateTemplate> templateList = forecastSdMapper.selectTotalByForecastIds(forecastIds);
        for(SdUpdateTemplate template : templateList){
            ForecastSd forecastSd = forecastSdMapper.selectByMonthAndProduct(template.getOperationYearMonth(),
                    template.getCompany(),
                    template.getBu(), template.getPdt(), template.getVmNumber(),
                    template.getPlatform(), template.getProductModel());
            if(null == forecastSd){
                forecastSd = new ForecastSd();
                copyObjFields(template, forecastSd);
                forecastSd.setSdAdjustmentOne(template.getSdBufferOne());
                forecastSd.setSdAdjustmentTwo(template.getSdBufferTwo());
                forecastSd.setSdAdjustmentThree(template.getSdBufferThree());
                forecastSd.setSdAdjustmentFour(template.getSdBufferFour());
                forecastSd.setSdAdjustmentFive(template.getSdBufferFive());
                forecastSd.setSdAdjustmentSix(template.getSdBufferSix());
                forecastSdMapper.insertSelective(forecastSd);
            }else{
                forecastSd.setSdAdjustmentOne(template.getSdBufferOne());
                forecastSd.setSdAdjustmentTwo(template.getSdBufferTwo());
                forecastSd.setSdAdjustmentThree(template.getSdBufferThree());
                forecastSd.setSdAdjustmentFour(template.getSdBufferFour());
                forecastSd.setSdAdjustmentFive(template.getSdBufferFive());
                forecastSd.setSdAdjustmentSix(template.getSdBufferSix());
                forecastSdMapper.updateByPrimaryKeySelective(forecastSd);
            }
            template.setId(String.valueOf(forecastSd.getId()));
        }
        ExcelUtils.writeExcel(response, templateList, SdUpdateTemplate.class);
    }

    public void uploadDataBySd(MultipartFile excel, Integer userId) {
        List<SdUpdateTemplate> sdList = ExcelUtils.readExcel(excel, SdUpdateTemplate.class);
/*        List<BiTotalInsertTemplate> insertTemplates = new ArrayList<>();
        List<BiTotalUpdateTemplate> updateTemplates = new ArrayList<>();*/
        for(SdUpdateTemplate template : sdList){
            ForecastSd forecastSd = forecastSdMapper.selectByPrimaryKey(Integer.parseInt(template.getId()));
            BusinessUtil.notNull(forecastSd, FORECAST_DB_DATA_MISMATCH);
            forecastSd.setSdAdjustmentOne(template.getSdBufferOne());
            forecastSd.setSdAdjustmentTwo(template.getSdBufferTwo());
            forecastSd.setSdAdjustmentThree(template.getSdBufferThree());
            forecastSd.setSdAdjustmentFour(template.getSdBufferFour());
            forecastSd.setSdAdjustmentFive(template.getSdBufferFive());
            forecastSd.setSdAdjustmentSix(template.getSdBufferSix());
            forecastSdMapper.updateByPrimaryKeySelective(forecastSd);
/*            if(StringUtils.isEmpty(forecastSd.getBiId())){
                handleTotalDataByInsert(insertTemplates, forecastSd);
            }else{
                handleTotalDataByUpdate(updateTemplates, forecastSd);
            }*/
        }
        /*//新增操作
        if(!insertTemplates.isEmpty()){
            String insertFileName = ExcelUtils.writeExcel(forecastPushPath, insertTemplates, BiTotalInsertTemplate.class);
            BiResponse response = callBiServer(INSERT_FORECAST_IMPORT_DATA, forecastPushPath, insertFileName, forecastPullPath);
            List<BiTotalInsertTemplate> responseDataList = ExcelUtils.readExcel(response.getFilePath(), BiTotalInsertTemplate.class);
            if(response.isSuccess()){
                for(BiTotalInsertTemplate insertTemplate : responseDataList){
                    ForecastSd forecastSd = forecastSdMapper.selectByPrimaryKey(Integer.parseInt(insertTemplate.getId()));
                    BusinessUtil.notNull(forecastSd, FORECAST_BI_CHECK_RESPONSE_ID_NOT_EXISTS);
                    forecastSd.setBiId(insertTemplate.getBiId());
                    forecastSd.setErrorMsg(insertTemplate.getErrorMsg());
                    forecastSdMapper.updateByPrimaryKeySelective(forecastSd);
                }
            }else{
                throw new BusinessException(FORECAST_SD_DATA_COMMIT_ERROR);
            }
        }
        //修改操作
        if(!updateTemplates.isEmpty()){
            String updateFileName = ExcelUtils.writeExcel(forecastPushPath, insertTemplates, BiTotalUpdateTemplate.class);
            BiResponse response = callBiServer(UPDATE_FORECAST_IMPORT_DAT, forecastPushPath, updateFileName, forecastPullPath);
            List<BiTotalUpdateTemplate> responseDataList = ExcelUtils.readExcel(response.getFilePath(), BiTotalUpdateTemplate.class);
            if(response.isSuccess()){
                for(BiTotalUpdateTemplate updateTemplate : responseDataList){
                    ForecastSd forecastSd = forecastSdMapper.selectByBiId(updateTemplate.getBiId());
                    BusinessUtil.notNull(forecastSd, FORECAST_BI_ID_NOT_EXISTS);
                    forecastSd.setErrorMsg(updateTemplate.getErrorMsg());
                    forecastSdMapper.updateByPrimaryKeySelective(forecastSd);
                }
            }else{
                throw new BusinessException(FORECAST_SD_DATA_COMMIT_ERROR);
            }
        }*/

    }

    private void operationDelete(List<Forecast> operationData) {
        List<String> biIds = new ArrayList<>();
        for(Forecast forecast : operationData) {
            //数据已经提交，代理商请求删除
            if (forecast.getStatus() == 2 && forecast.getAgencyStatusType() == -1 && StringUtils.isNotEmpty(forecast.getBiId())) {
                biIds.add(forecast.getBiId());
            }
        }
        if(log.isDebugEnabled()){
            log.debug("【删除】批量通过代理商预测数据，数据信息：{}", JSONObject.toJSONString(operationData));
        }
        requestBiDeleteServer(biIds);
        return;
    }

    private void operationInsert(List<Forecast> operationData, Integer[] forecastIds, String passMsg) {
        //detail
        List<BiAgencyInsertTemplate> insertData = new ArrayList<>();
        for(Forecast forecast : operationData){
            BiAgencyInsertTemplate insertTemplate = new BiAgencyInsertTemplate();
            copyDbFields(forecast, insertTemplate);
            insertTemplate.setId(String.valueOf(forecast.getId()));
            insertData.add(insertTemplate);
        }
        //total
        List<BiTotalInsertTemplate> insertTotalData = new ArrayList<>();
        List<ForecastSd> sdList = forecastSdMapper.selectByMonth(operationData.get(0).getOperationYearMonth());
        BusinessUtil.notNull(sdList, FORECAST_SD_BUFFER_ADJUSTMENT_NUM_ERROR);
        BusinessUtil.assertFlase(sdList.isEmpty(), FORECAST_SD_BUFFER_ADJUSTMENT_NUM_ERROR);
        for(ForecastSd forecastSd : sdList) {
            handleTotalDataByInsert(insertTotalData, forecastSd);
        }
        if(log.isDebugEnabled()){
            log.debug("【新增】批量通过代理商预测数据，数据信息：{}", JSONObject.toJSONString(insertData));
        }
        BiResponse result = requestBiInsertServer(insertTotalData, insertData, forecastIds, passMsg);
        if(log.isDebugEnabled()){
            log.debug("【新增】批量通过代理商预测数据，BI处理结果：{}", JSONObject.toJSONString(result));
        }
        //更新明细数据的处理结果
        List<BiAgencyInsertTemplate> insertResList = ExcelUtils.readExcel(result.getFilePath(), BiAgencyInsertTemplate.class);
        for(BiAgencyInsertTemplate detailData : insertResList){
            BusinessUtil.assertEmpty(detailData.getId(), FORECAST_BI_CHECK_RESPONSE_ID_NOT_EXISTS);
            forecastMapper.updateBiInfoByKey(Integer.parseInt(detailData.getId()), detailData.getBiId(), detailData.getErrorMsg());
        }
        //更新汇总数据的处理结果
        List<BiTotalInsertTemplate> totalResList = ExcelUtils.readExcel(result.getTotalFilePath(), BiTotalInsertTemplate.class);
        for(BiTotalInsertTemplate totalData : totalResList){
            BusinessUtil.assertEmpty(totalData.getId(), FORECAST_BI_CHECK_RESPONSE_ID_NOT_EXISTS);
            forecastSdMapper.updateBiInfoByKey(Integer.parseInt(totalData.getId()), totalData.getBiId(), totalData.getErrorMsg());
        }
        if(result.isSuccess()){
            forecastMapper.updateStatusByIds(forecastIds, 2, passMsg);
        }
    }

    private void operationUpdate(List<Forecast> operationData, Integer[] forecastIds, String passMsg) {
        //detail
        List<BiAgencyUpdateTemplate> updateData = new ArrayList<>();
        for(Forecast forecast : operationData){
            if(forecast.getStatus() == 4 && forecast.getAgencyStatusType() == 2){
                BiAgencyUpdateTemplate updateTemplate = new BiAgencyUpdateTemplate();
                copyDbFields(forecast, updateTemplate);
                updateTemplate.setPortalId(String.valueOf(forecast.getId()));
                updateData.add(updateTemplate);
            }
        }
        //total
        List<BiTotalUpdateTemplate> updateTotalData = new ArrayList<>();
        List<ForecastSd> sdList = forecastSdMapper.selectByMonth(operationData.get(0).getOperationYearMonth());
        BusinessUtil.notNull(sdList, FORECAST_SD_BUFFER_ADJUSTMENT_NUM_ERROR);
        BusinessUtil.assertFlase(sdList.isEmpty(), FORECAST_SD_BUFFER_ADJUSTMENT_NUM_ERROR);
        for(ForecastSd forecastSd : sdList) {
            handleTotalDataByUpdate(updateTotalData, forecastSd);
        }
        if(log.isDebugEnabled()){
            log.debug("【修改】批量通过代理商预测数据，数据信息：{}", JSONObject.toJSONString(updateData));
        }
        BiResponse result = requestBiUpdateServer(updateTotalData, updateData, forecastIds, passMsg);
        if(log.isDebugEnabled()){
            log.debug("【修改】批量通过代理商预测数据，BI处理结果：{}", JSONObject.toJSONString(result));
        }
        //更新明细数据的处理结果
        List<BiAgencyUpdateTemplate> updateResList = ExcelUtils.readExcel(result.getFilePath(), BiAgencyUpdateTemplate.class);
        for(BiAgencyUpdateTemplate detailData : updateResList){
            forecastMapper.updateBiInfoByBiId(detailData.getBiId(), detailData.getErrorMsg());
        }
        //更新汇总数据的处理结果
        List<BiTotalUpdateTemplate> totalResList = ExcelUtils.readExcel(result.getTotalFilePath(), BiTotalUpdateTemplate.class);
        for(BiTotalUpdateTemplate totalData : totalResList){
            forecastSdMapper.updateBiInfoByBiId(totalData.getBiId(), totalData.getErrorMsg());
        }
        if(result.isSuccess()){
            forecastMapper.updateStatusByIds(forecastIds, 2, passMsg);
        }
    }

    private void handleTotalDataByInsert(List<BiTotalInsertTemplate> insertTemplates, ForecastSd forecastSd) {
        try {
            BiTotalInsertTemplate insertTemplate = new BiTotalInsertTemplate();
            BeanUtils.copyNotNullFields(forecastSd, insertTemplate);
            insertTemplate.setId(String.valueOf(forecastSd.getId()));
            insertTemplates.add(insertTemplate);
        }catch (Exception ex) {
            log.error("BeanUtils copyNotNullFields exception", ex);
            throw new BusinessException("BeanUtils copyNotNullFields exception");
        }
    }

    private void handleTotalDataByUpdate(List<BiTotalUpdateTemplate> updateTemplates, ForecastSd forecastSd) {
        try {
            BiTotalUpdateTemplate updateTemplate = new BiTotalUpdateTemplate();
            BeanUtils.copyNotNullFields(forecastSd, updateTemplate);
            updateTemplates.add(updateTemplate);
        }catch (Exception ex) {
            log.error("BeanUtils copyNotNullFields exception", ex);
            throw new BusinessException("BeanUtils copyNotNullFields exception");
        }
    }

    /**
     * 请求BI服务check代理商预测数据
     * @param batchNo
     * @return
     */
    private ForecastResult checkForecastData(String batchNo) {
        List<Forecast> biList = forecastMapper.selectByBatchNo(batchNo);
        List<BiAgencyCheckTemplate> reqBiDataList = new ArrayList<>();
        for(Forecast forecast : biList){
            BiAgencyCheckTemplate biData = new BiAgencyCheckTemplate();
            copyDbFields(forecast, biData);
            biData.setId(String.valueOf(forecast.getId()));
            reqBiDataList.add(biData);
        }
        String checkFileName = ExcelUtils.writeExcel(forecastPushPath, reqBiDataList, BiAgencyCheckTemplate.class);
        BiResponse response;
        if(mockBiServer){
            response = mockRequestBiCheckServer(checkFileName, forecastPushPath);
        }else{
            response = requestBiCheckServer(forecastPushPath, checkFileName, forecastPullPath);
        }
        List<BiAgencyCheckTemplate> responseDataList = ExcelUtils.readExcel(response.getFilePath(), BiAgencyCheckTemplate.class);
        ForecastResult result = new ForecastResult();
        result.setSuccess(response.isSuccess());
        result.setBatchNo(batchNo);
        for(BiAgencyCheckTemplate responseData : responseDataList){
            if(!response.isSuccess() && mockBiServer){
                responseData.setErrorMsg("模拟出来的错误，上线需要去掉");
            }
            if(StringUtils.isNotEmpty(responseData.getErrorMsg())){
                BusinessUtil.assertEmpty(responseData.getId(), FORECAST_BI_CHECK_RESPONSE_ID_NOT_EXISTS);
                forecastMapper.updateErrorMsgById(Integer.parseInt(responseData.getId()), responseData.getErrorMsg());
            }
        }
        List<Forecast> resultData = forecastMapper.selectByBatchNo(batchNo);
        result.setData(resultData);
        return result;
    }

    private String generateBathNo() {
        return UUID.randomUUID().toString().toUpperCase();
    }

    private BiResponse mockRequestBiCheckServer(String fileName, String pullPath) {
        return new BiResponse(mockThirdResult(), pullPath + fileName);
    }

    /**
     * 文件内容Check服务
     * @param filePath
     * @param fileName
     * @param pullPath
     * @return
     */
    private BiResponse requestBiCheckServer(String filePath, String fileName, String pullPath) {
        try {
            String fullPath = String.format("%s%s", filePath, fileName);
            String ftpFullPath = String.format("%s%s", ftpUploadPath, fileName);
            //上传文件到ftp
            ftpClientUtil.put(ftpFullPath, fullPath);
            String response = CallApiUtils.callBiApi(CHECK_FORECAST_IMPORT_DATA, "PORTAL/BI/", ftpFullPath, ftpDownloadPath);
            //检测返回信息是否空
            if(StringUtils.isEmpty(response)){
                log.error("{} -> {}", FORECAST_BI_RESPONSE_EXCEPTION.getZhMsg(), response);
                throw new BusinessException(FORECAST_BI_RESPONSE_EXCEPTION);
            }
            //解析BI返回的结果信息，去掉多余的字符
            response = response.replace("\"", "").replace("\\", "");
            if(log.isDebugEnabled()){
                log.debug("[forecast] Bi server response info -> pushPath:[{}], pullPath:[{}], response:[{}]", fullPath, pullPath, response);
            }
            //将返回结果信息进行截取，获取到对方的文件名
            String biFilePath = response.split(":")[1];
            String biFileName = biFilePath.substring(biFilePath.lastIndexOf("/") + 1, biFilePath.length());
            ftpClientUtil.get(biFilePath, pullPath+biFileName);
            //封装结果流转给下一个流程处理
            if(response.contains(SUCCESS_CODE)){
                return new BiResponse(true, pullPath+biFileName);
            }
            if(response.contains(ERROR_CODE)){
                return new BiResponse(false, pullPath+biFileName);
            }
            log.error(FORECAST_BI_RESPONSE_EXCEPTION.getZhMsg(), response);
            throw new BusinessException(FORECAST_BI_RESPONSE_EXCEPTION);
        }catch (Exception ex) {
            log.error(FORECAST_BI_SERVER_EXCEPTION.getZhMsg(), ex);
            throw new BusinessException(FORECAST_BI_SERVER_EXCEPTION);
        }
    }

    public static void main(String[] args) {
        List<String> biIds = new ArrayList<>();
        biIds.add(null);
        System.out.println(StringUtils.join(biIds, ","));
    }

    /**
     * 预测数据Delete服务
     * @param biIds
     */
    private void requestBiDeleteServer(List<String> biIds) {
        try {
            if(null == biIds || biIds.isEmpty()){
                throw new BusinessException(FORECAST_BI_ID_EMPTY);
            }
            String ids = StringUtils.join(biIds, ",");
            String response = CallApiUtils.callBiGetApi(DELETEFORECAST, "PORTAL/BI/", String.format("sIDList=%s&sSummaryIDList=%s", ids, ids));
            response = response.replace("\"", "").replace("\\","");
            if(StringUtils.isNotEmpty(response) && response.contains("删除成功")){
                forecastMapper.deleteByBiIds(biIds);
            }else{
                throw new BusinessException(FORECAST_BI_DELETE_FAIL);
            }
        }catch (Exception ex) {
            log.error(FORECAST_BI_SERVER_EXCEPTION.getZhMsg(), ex);
            throw new BusinessException(FORECAST_BI_SERVER_EXCEPTION);
        }
    }

    /**
     * 预测数据Insert服务
     * @param insertData
     */
    private BiResponse requestBiInsertServer(List<BiTotalInsertTemplate> insertTotalData, List<BiAgencyInsertTemplate> insertData, Integer[] forecastIds, String passMsg) {
        try {
            //detail file
            String detailFile = ExcelUtils.writeExcel(forecastPushPath, insertData, BiAgencyInsertTemplate.class);
            String ftpDetailFilePath = String.format("%s%s", ftpUploadPath, detailFile);
            //total file
            String totalFile = ExcelUtils.writeExcel(forecastPushPath, insertTotalData, BiTotalInsertTemplate.class);
            String ftpTotalFilePath = String.format("%s%s", ftpUploadPath, totalFile);
            //mock bi server
            if(mockBiServer){
                return new BiResponse(mockThirdResult(), forecastPushPath+detailFile, forecastPushPath+totalFile);
            }
            ftpClientUtil.put(ftpUploadPath+detailFile, forecastPushPath+detailFile);
            ftpClientUtil.put(ftpUploadPath+totalFile, forecastPushPath+totalFile);
            //http get param value
            String param = "sFromUrl=" + ftpDetailFilePath + "&sFromSummaryPath=" + ftpTotalFilePath + "&sToUrl=" + ftpDownloadPath + "&sToSummaryPath=" + ftpDownloadPath;

            log.info("BI insert url :"+param);
            String response = CallApiUtils.callBiGetApi(INSERT_FORECAST_IMPORT_DATA, "PORTAL/BI/", param);
            log.info("BI insert response :"+response);
            //检测返回信息是否空
            if(StringUtils.isEmpty(response)){
                log.error("{} -> {}", FORECAST_BI_RESPONSE_EXCEPTION.getZhMsg(), response);
                throw new BusinessException(FORECAST_BI_RESPONSE_EXCEPTION);
            }
            //去掉返回字符中的引号
            response = response.replace("\"","").replace("\\", "");
            String[] resFileArray = response.split(":")[1].split(",");
            //BI返回的明细数据文件
            String biDetailFileName = resFileArray[0].substring(resFileArray[0].lastIndexOf("/") + 1, resFileArray[0].length());
            ftpClientUtil.get(resFileArray[0], forecastPullPath+biDetailFileName);
            //BI返回的汇总数据文件
            String biTotalFileName = resFileArray[1].substring(resFileArray[1].lastIndexOf("/") + 1, resFileArray[1].length());
            ftpClientUtil.get(resFileArray[1], forecastPullPath+biTotalFileName);
            //封装结果流转给下一个流程处理
            if(response.contains(SUCCESS_CODE)){
                return new BiResponse(true, forecastPullPath+biDetailFileName, forecastPullPath+biTotalFileName);
            }
            if(response.contains(ERROR_CODE)){
                return new BiResponse(true, forecastPullPath+biDetailFileName, forecastPullPath+biTotalFileName);
            }
            log.error(FORECAST_BI_RESPONSE_EXCEPTION.getZhMsg(), response);
            throw new BusinessException(FORECAST_BI_RESPONSE_EXCEPTION);
        }catch (Exception ex) {
            log.error(FORECAST_BI_SERVER_EXCEPTION.getZhMsg(), ex);
            throw new BusinessException(FORECAST_BI_SERVER_EXCEPTION);
        }
    }

    /**
     * 预测数据Update服务
     * @param updateData
     */
    private BiResponse requestBiUpdateServer(List<BiTotalUpdateTemplate> updateTotalData, List<BiAgencyUpdateTemplate> updateData, Integer[] forecastIds, String passMsg) {
        try {
            //detail file
            String detailFile = ExcelUtils.writeExcel(forecastPushPath, updateData, BiAgencyUpdateTemplate.class);
            String ftpDetailFilePath = String.format("%s%s", ftpUploadPath, detailFile);
            //total file
            String totalFile = ExcelUtils.writeExcel(forecastPushPath, updateTotalData, BiTotalUpdateTemplate.class);
            String ftpTotalFilePath = String.format("%s%s", ftpUploadPath, totalFile);
            //mock bi server
            if(mockBiServer){
                return new BiResponse(mockThirdResult(), forecastPushPath+detailFile, forecastPushPath+totalFile);
            }
            ftpClientUtil.put(ftpUploadPath+detailFile, forecastPushPath+detailFile);
            ftpClientUtil.put(ftpUploadPath+totalFile, forecastPushPath+totalFile);
            //http get param value
            String param = "sFromUrl=" + ftpDetailFilePath + "&sFromSummaryPath=" + ftpTotalFilePath + "&sToUrl=" + ftpDownloadPath + "&sToSummaryPath=" + ftpDownloadPath;

            String response = CallApiUtils.callBiGetApi(UPDATE_FORECAST_IMPORT_DATA, "PORTAL/BI/", param);
            //检测返回信息是否空
            if(StringUtils.isEmpty(response)){
                log.error("{} -> {}", FORECAST_BI_RESPONSE_EXCEPTION.getZhMsg(), response);
                throw new BusinessException(FORECAST_BI_RESPONSE_EXCEPTION);
            }
            //去掉返回字符中的引号
            response = response.replace("\"","").replace("\\", "");
            String[] resFileArray = response.split(":")[1].split(",");
            //BI返回的明细数据文件
            String biDetailFileName = resFileArray[0].substring(resFileArray[0].lastIndexOf("/") + 1, resFileArray[0].length());
            ftpClientUtil.get(resFileArray[0], forecastPullPath+biDetailFileName);
            //BI返回的汇总数据文件
            String biTotalFileName = resFileArray[1].substring(resFileArray[1].lastIndexOf("/") + 1, resFileArray[1].length());
            ftpClientUtil.get(resFileArray[1], forecastPullPath+biTotalFileName);
            //封装结果流转给下一个流程处理
            if(response.contains(SUCCESS_CODE)){
                return new BiResponse(true, forecastPullPath+biDetailFileName, forecastPullPath+biTotalFileName);
            }
            if(response.contains(ERROR_CODE)){
                return new BiResponse(true, forecastPullPath+biDetailFileName, forecastPullPath+biTotalFileName);
            }
            log.error(FORECAST_BI_RESPONSE_EXCEPTION.getZhMsg(), response);
            throw new BusinessException(FORECAST_BI_RESPONSE_EXCEPTION);
        }catch (Exception ex) {
            log.error(FORECAST_BI_SERVER_EXCEPTION.getZhMsg(), ex);
            throw new BusinessException(FORECAST_BI_SERVER_EXCEPTION);
        }
    }

    private boolean mockThirdResult() {
        String time = String.valueOf(new Date().getTime());
        int len = time.length();
        int i = Integer.parseInt(String.valueOf(time.charAt(len - 1)));
        return i > 4;
    }

    private String sumValue(AmbUpdateTemplate agencyTemplate){
        BigDecimal total;
        try {
            total = BigDecimal.ZERO.add(new BigDecimal(agencyTemplate.getCurrentWriteOne()))
                    .add(new BigDecimal(agencyTemplate.getCurrentWriteTwo()))
                    .add(new BigDecimal(agencyTemplate.getCurrentWriteThree()))
                    .add(new BigDecimal(agencyTemplate.getCurrentWriteFour()))
                    .add(new BigDecimal(agencyTemplate.getCurrentWriteFive()))
                    .add(new BigDecimal(agencyTemplate.getCurrentWriteSix()));
        }catch (Exception ex) {
            return null;
        }
        return String.valueOf(total);
    }

    private void copyObjFields(Object in, Object out) {
        try {
            BeanUtils.copyNotNullFields(in, out);
        }catch (Exception ex) {
            log.error("BeanUtils copyNotNullFields exception", ex);
            throw new BusinessException("BeanUtils copyNotNullFields exception");
        }
    }

    private void copyDbFields(Forecast forecast, Object object) {
        try {
            BeanUtils.copyNotNullFields(forecast, object);
            BeanUtils.copyNotNullFields(forecast.getLine(), object);
        }catch (Exception ex) {
            log.error("BeanUtils copyNotNullFields exception", ex);
            throw new BusinessException("BeanUtils copyNotNullFields exception");
        }
    }

    private void copyTemplateFields(Object object, Forecast forecast) {
        try {
            BeanUtils.copyNotNullFields(object, forecast);
        }catch (Exception ex) {
            log.error("BeanUtils copyNotNullFields exception", ex);
            throw new BusinessException("BeanUtils copyNotNullFields exception");
        }
    }

    private void copyTemplateFields(Object object, ForecastLine line) {
        try {
            BeanUtils.copyNotNullFields(object, line);
        }catch (Exception ex) {
            log.error("BeanUtils copyNotNullFields exception", ex);
            throw new BusinessException("BeanUtils copyNotNullFields exception");
        }
    }

    private String getPriceByProduct(String productModel, String platform, String priceType) {
        try {
            CatalogPrice catalogPrice = catalogPriceService.getPriceByProduct(productModel, platform,priceType);
            /*BusinessUtil.notNull(catalogPrice, FORECAST_PO_PRICE_GET_ERROR);
            BusinessUtil.notNull(catalogPrice.getCatalogPrice(), FORECAST_PO_PRICE_GET_ERROR);*/
            return String.valueOf(null==catalogPrice?0:catalogPrice.getCatalogPrice());
        }catch (Exception ex) {
            log.error("获取产品价格异常"+ex);
            throw new BusinessException(FORECAST_PO_PRICE_GET_ERROR);
        }
    }

    /**
     * 根据登录用户获取代理商信息
     * @param userId
     * @return
     */
    private CustomerInfo getCustomerInfo(Integer userId) {
        try {
            CustomerInfo customerInfo = customerInfoService.getDealerByUser(userId);
            BusinessUtil.notNull(customerInfo, FORECAST_AGENCY_INFO_ERROR);
            BusinessUtil.assertEmpty(customerInfo.getCustAbbreviation(), FORECAST_AGENCY_INFO_ERROR);
            BusinessUtil.assertEmpty(customerInfo.getBusinessType(), FORECAST_AGENCY_INFO_ERROR);
            return customerInfo;
        }catch (Exception ex) {
            log.error(FORECAST_AGENCY_MATCH_ERROR.getZhMsg(), ex);
            throw new BusinessException(FORECAST_AGENCY_MATCH_ERROR);
        }
    }

    /**
     * 根据客户简称获取关联上级客户信息，同时对数据正确性进行基础校验
     * @param customerAbbreviation
     * @return
     */
    private CustomerOrgBean getCustomerOrgInfo(String customerAbbreviation) {
        try {
            log.info("=================user"+customerAbbreviation);
            CustomerOrgBean customerOrgBean = customerInfoService.selectByAbbreviation(customerAbbreviation);
            BusinessUtil.notNull(customerOrgBean, FORECAST_NOT_FOUND_CUSTOMER_INFO);
            BusinessUtil.assertEmpty(customerOrgBean.getCustType(), FORECAST_NOT_FOUND_CUSTOMER_INFO);
            BusinessUtil.assertEmpty(customerOrgBean.getSales(), FORECAST_NOT_FOUND_CUSTOMER_INFO);
            BusinessUtil.assertEmpty(customerOrgBean.getAmb(), FORECAST_NOT_FOUND_CUSTOMER_INFO);
            BusinessUtil.assertEmpty(customerOrgBean.getPm(), FORECAST_NOT_FOUND_CUSTOMER_INFO);
            BusinessUtil.assertEmpty(customerOrgBean.getOffice(), FORECAST_NOT_FOUND_CUSTOMER_INFO);
            return customerOrgBean;
        }catch (Exception ex) {
            log.error(FORECAST_CUSTOMER_MATCH_ERROR.getZhMsg(), ex);
            throw new BusinessException(FORECAST_CUSTOMER_MATCH_ERROR);
        }
    }

    /**
     * 根据客户输入的物料号获取系统内的产品信息，同时对数据进行基础校验
     * @return
     */
    private ProductInfoDO getProductInfo(String productModel, String platForm) {
        try {
            ProductInfoDO productInfoDO = productService.selectByProductModelAndPlatForm(productModel, platForm);
            log.error("prodct:"+productModel+";"+platForm);
            BusinessUtil.notNull(productInfoDO, FORECAST_PRODUCT_INFO_GET_ERROR);
            BusinessUtil.assertEmpty(productInfoDO.getBu(), FORECAST_PRODUCT_INFO_GET_ERROR);
            BusinessUtil.assertEmpty(productInfoDO.getPdt(), FORECAST_PRODUCT_INFO_GET_ERROR);
            BusinessUtil.assertEmpty(productInfoDO.getSapMid(), FORECAST_PRODUCT_INFO_GET_ERROR);
            return productInfoDO;
        }catch (Exception ex) {
            log.error(FORECAST_PRODUCT_INFO_GET_ERROR.getZhMsg(), ex);
            throw new BusinessException(FORECAST_PRODUCT_INFO_GET_ERROR);
        }
    }

    private void setLastValue(Forecast forecast, ForecastLine line) {
        try {
            Date currentDate = DateUtil.parseDate(forecast.getOperationYearMonth(), DateUtil.MONTH_FORMAT);
            Date lastDate = DateUtil.computeWithMonth(currentDate, -1);
            forecast.setLastMonth(DateUtil.format(lastDate, DateUtil.MONTH_FORMAT));
            Forecast lastValue =  forecastMapper.selectLastValue(forecast);
            if(null == lastValue){
                return;
            }
            ForecastLine lastLineValue = lastValue.getLine();
            if(null == lastLineValue){
                return;
            }
            line.setLastWriteOne(lastLineValue.getCurrentWriteTwo());
            line.setLastWriteTwo(lastLineValue.getCurrentWriteThree());
            line.setLastWriteThree(lastLineValue.getCurrentWriteFour());
            line.setLastWriteFour(lastLineValue.getCurrentWriteFive());
            line.setLastWriteFive(lastLineValue.getCurrentWriteSix());
            line.setLastWriteSix(null);
        }catch (Exception ex) {
            throw new BusinessException(FORECAST_GET_LAST_VALUE_ERROR);
        }
    }

    class BiResponse {

        //是否成功处理
        private boolean isSuccess = false;
        //文件地址（完整路径+文件名称）
        private String filePath;
        //total file path
        private String totalFilePath;

        BiResponse() {
        }

        public BiResponse(boolean isSuccess, String filePath) {
            this.isSuccess = isSuccess;
            this.filePath = filePath;
        }

        public BiResponse(boolean isSuccess, String filePath, String totalFilePath) {
            this.isSuccess = isSuccess;
            this.filePath = filePath;
            this.totalFilePath = totalFilePath;
        }

        public boolean isSuccess() {
            return isSuccess;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public String getTotalFilePath() {
            return totalFilePath;
        }

        public void setTotalFilePath(String totalFilePath) {
            this.totalFilePath = totalFilePath;
        }
    }

}
