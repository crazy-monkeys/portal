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
import com.crazy.portal.service.customer.CustomerInfoService;
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


    @Value("${file.path.forecast.push}")
    private String forecastPushPath;
    @Value("${file.path.forecast.pull}")
    private String forecastPullPath;

    @Value("${ftp.path.forecast.push}")
    private String ftpUploadPath;
    @Value("${ftp.path.forecast.pull}")
    private String ftpDownloadPath;

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
            lastYearMonthDate = DateUtil.computeWithMonth(lastYearMonthDate, -1);
            lastYearMonth = DateUtil.format(lastYearMonthDate, DateUtil.MONTH_FORMAT);
        }catch (Exception ex) {
            log.error(FORECAST_YEAR_MONTH_FORMAT_ERROR.getZhMsg() + "yearMonth:" + yearMonth, ex);
            throw new BusinessException(FORECAST_YEAR_MONTH_FORMAT_ERROR);
        }
        List<Forecast> forecastList = forecastMapper.selectByYearMonth(lastYearMonth, userId);
        if(null == forecastList || forecastList.isEmpty()){
            ExcelUtils.writeExcel(response, null, AgencyTemplate.class);
        }
        List<AgencyTemplate> templateList = new ArrayList<>();
        for(Forecast forecast : forecastList) {
            AgencyTemplate agencyTemplate = new AgencyTemplate();
            copyDbFields(forecast, agencyTemplate);
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
     * @param userId
     * @return
     */
    @Transactional
    public ForecastResult uploadAgencyTemplate(MultipartFile excel, Integer userId) {
        BusinessUtil.notNull(excel, FORECAST_EXCEL_CHECK_ERROR);
        List<AgencyTemplate> agencyForecastList = ExcelUtils.readExcel(excel, AgencyTemplate.class);
        if(null == agencyForecastList || agencyForecastList.isEmpty()){
            log.warn(FORECAST_DATA_NOT_EMPTY.getZhMsg());
            throw new BusinessException(FORECAST_DATA_NOT_EMPTY);
        }
        //获取代理商信息
        String agencyAbbreviation = getAgencyAbbreviation(userId);
        String batchNo = generateBathNo();
        for(AgencyTemplate template : agencyForecastList){
            template.setAgencyAbbreviation(agencyAbbreviation);
            //获取代理商上级客户信息
            CustomerOrgBean customerOrgBean = getCustomerOrgInfo(template.getCustomerAbbreviation());
            Forecast forecast = new Forecast(userId);
            copyTemplateFields(template, forecast);
            //设置客户字段
            forecast.setCustomerType(customerOrgBean.getCustType());
            forecast.setSalePeople(customerOrgBean.getSales());
            forecast.setAmbLeader(customerOrgBean.getAmb());
            forecast.setSdPeople(customerOrgBean.getPm());
            forecast.setRepresentative(customerOrgBean.getOffice());
            //当前操作批次
            forecast.setBatchNo(batchNo);
            forecastMapper.insertSelective(forecast);
            if(log.isDebugEnabled()){
                log.debug("[upload data] Save forecast head data , userId:{} , data:{}", userId, JSONObject.toJSON(forecast));
            }
            ForecastLine line = new ForecastLine();
            copyTemplateFields(template, line);
            line.setfId(forecast.getId());
            forecastLineMapper.insertSelective(line);
            if(log.isDebugEnabled()){
                log.debug("[upload data] Save forecast line data , userId:{} , data:{}", userId, JSONObject.toJSON(line));
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
            ExcelUtils.writeExcel(response, null, AgencyErrorTemplate.class);
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
            Forecast forecast = forecastMapper.selectByPrimaryKey(Integer.parseInt(errorTemplate.getForecastId()));
            //不允许用户擅自更改ID，每行数据都要进行校验
            BusinessUtil.notNull(forecast, FORECAST_DB_DATA_MISMATCH);
            BusinessUtil.assertTrue(batchNo.equals(forecast.getBatchNo()), FORECAST_DB_DATA_MISMATCH);
            copyTemplateFields(errorTemplate, forecast);
            forecast.setErrorMsg("clear");
            forecastMapper.updateByPrimaryKeySelective(forecast);
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
     * @param pageNum
     * @param pageSize
     * @param userId
     * @param customerAbbreviation
     * @param status
     * @param salePeople
     * @param uploadStartTime
     * @param uploadEndTime
     * @param ambPeople
     * @param sdPeople
     * @param agencyAbbreviation
     * @param channel
     * @return
     */
    public PageInfo<Forecast> queryApprovalForecastData(Integer pageNum, Integer pageSize, Integer userId,
                                                        String customerAbbreviation, Integer status, String salePeople,
                                                        String uploadStartTime, String uploadEndTime,
                                                        String ambPeople, String sdPeople, String agencyAbbreviation,
                                                        String channel) {
        PortalUtil.defaultStartPage(pageNum,pageSize);
        List<Integer> userList = userCustomerMappingService.selectUserMapping(userId, Enums.CustomerMappingModel.Forecast.getValue());
        Integer[] userIds = new Integer[userList.size()];
        List<Forecast> result = forecastMapper.selectByLeader(userList.toArray(userIds), customerAbbreviation, status,
                salePeople, uploadStartTime, uploadEndTime, ambPeople, sdPeople, agencyAbbreviation, channel);
        return new PageInfo<>(result);
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
    public void passApprovalForecastData(Integer[] forecastIds, String passMsg) {
        //是否有无法识别的类型操作
        boolean isReturn = false;
        try {
            List<BiAgencyInsertTemplate> insertData = new ArrayList<>();
            List<BiAgencyUpdateTemplate> updateData = new ArrayList<>();
            List<String> biIds = new ArrayList<>();
            for(Integer id : forecastIds){
                Forecast forecast = forecastMapper.selectRelationByKey(id);
                //数据已经提交，代理商请求删除
                if(forecast.getStatus() == 2 && forecast.getAgencyStatusType() == -1){
                    biIds.add(forecast.getBiId());
                    continue;
                }
                //数据未提交，提交给到BI
                if(forecast.getStatus() == 1 && forecast.getAgencyStatusType() == 1){
                    BiAgencyInsertTemplate insertTemplate = new BiAgencyInsertTemplate();
                    copyDbFields(forecast, insertTemplate);
                    insertData.add(insertTemplate);
                    continue;
                }
                //数据已提交，代理商请求修改
                if(forecast.getStatus() == 2 && forecast.getAgencyStatusType() == 2){
                    BiAgencyUpdateTemplate updateTemplate = new BiAgencyUpdateTemplate();
                    copyDbFields(forecast, updateTemplate);
                    updateData.add(updateTemplate);
                    continue;
                }
                isReturn = true;
                return;
            }
            if(insertData.size() == forecastIds.length){
                if(log.isDebugEnabled()){
                    log.debug("【新增】批量通过代理商预测数据，数据信息：{}", JSONObject.toJSONString(insertData));
                }
                requestBiInsertServer(insertData, forecastIds, passMsg);
                return;
            }
            if(updateData.size() == forecastIds.length){
                if(log.isDebugEnabled()){
                    log.debug("【修改】批量通过代理商预测数据，数据信息：{}", JSONObject.toJSONString(insertData));
                }
                requestBiUpdateServer(updateData, forecastIds, passMsg);
                return;
            }
            if(biIds.size() == forecastIds.length){
                if(log.isDebugEnabled()){
                    log.debug("【删除】批量通过代理商预测数据，数据信息：{}", JSONObject.toJSONString(insertData));
                }
                requestBiDeleteServer(biIds);
                return;
            }
            throw new BusinessException(FORECAST_DATA_TYPE_DISUNITY);
        }finally {
            if(isReturn){
                throw new BusinessException(FORECAST_DATA_OPERATION_ERROR);
            }
        }
    }

    /**
     * 代理商预测数据驳回
     * @param forecastIds
     * @param rejectMsg
     */
    public void rejectApprovalForecastData(Integer[] forecastIds, String rejectMsg) {
        for(Integer id : forecastIds) {
            Forecast forecast = forecastMapper.selectByPrimaryKey(id);
            if(forecast.getStatus() == 2){
                throw new BusinessException(FORECAST_ALREADY_COMMIT_NOT_REJECT);
            }
        }
        forecastMapper.updateStatusByIds(forecastIds, -1, rejectMsg);
    }

    public void downloadDataByAmb(HttpServletResponse response, Integer[] forecastIds) {
        List<Forecast> forecastList = forecastMapper.selectByIds(forecastIds);
        List<AmbUpdateTemplate> templateList = new ArrayList<>();
        for(Forecast forecast : forecastList) {
            AmbUpdateTemplate agencyTemplate = new AmbUpdateTemplate();
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
            forecastLineMapper.updateByForecastId(forecastLine);
        }
    }

    public void downloadDataBySd(HttpServletResponse response, Integer[] forecastIds) {
        List<SdUpdateTemplate> templateList = forecastSdMapper.selectTotalByForecastIds(forecastIds);
        for(SdUpdateTemplate template : templateList){
            ForecastSd forecastSd = forecastSdMapper.selectByMonthAndProduct(template.getOperationYearMonth(),
                    template.getCompany(),
                    template.getBu(), template.getPdt(), template.getProductType(),
                    template.getPlatform(), template.getProductModel());
            if(null == forecastSd){
                forecastSd = new ForecastSd();
                forecastSd.setBu(template.getBu());
                forecastSd.setPdt(template.getPdt());
                forecastSd.setProductType(template.getProductType());
                forecastSd.setPlatform(template.getPlatform());
                forecastSd.setProductModel(template.getProductModel());
                forecastSd.setVmNumber("");
                forecastSd.setSdAdjustmentOne(template.getSdBufferOne());
                forecastSd.setSdAdjustmentTwo(template.getSdBufferTwo());
                forecastSd.setSdAdjustmentThree(template.getSdBufferThree());
                forecastSd.setSdAdjustmentFour(template.getSdBufferFour());
                forecastSd.setSdAdjustmentFive(template.getSdBufferFive());
                forecastSd.setSdAdjustmentSix(template.getSdBufferSix());
                forecastSdMapper.insertSelective(forecastSd);
            }else{
                forecastSdMapper.updateByPrimaryKeySelective(forecastSd);
            }
            template.setId(String.valueOf(forecastSd.getId()));
        }
        ExcelUtils.writeExcel(response, templateList, SdUpdateTemplate.class);
    }

    public void uploadDataBySd(MultipartFile excel, Integer userId) {
        List<SdUpdateTemplate> sdList = ExcelUtils.readExcel(excel, SdUpdateTemplate.class);
        List<BiTotalInsertTemplate> insertTemplates = new ArrayList<>();
        List<BiTotalUpdateTemplate> updateTemplates = new ArrayList<>();
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
            if(StringUtils.isEmpty(forecastSd.getBiId())){
                handleTotalDataByInsert(insertTemplates, forecastSd);
            }else{
                handleTotalDataByUpdate(updateTemplates, forecastSd);
            }
        }
        //新增操作
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
        }

    }

    private void handleTotalDataByInsert(List<BiTotalInsertTemplate> insertTemplates, ForecastSd forecastSd) {
        try {
            BiTotalInsertTemplate insertTemplate = new BiTotalInsertTemplate();
            BeanUtils.copyNotNullFields(forecastSd, insertTemplate);
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
        BiResponse response = requestBiCheckServer(forecastPushPath, checkFileName, forecastPullPath);
        List<BiAgencyCheckTemplate> responseDataList = ExcelUtils.readExcel(response.getFilePath(), BiAgencyCheckTemplate.class);
        ForecastResult result = new ForecastResult();
        result.setSuccess(response.isSuccess());
        result.setBatchNo(batchNo);
        for(BiAgencyCheckTemplate responseData : responseDataList){
            if(!response.isSuccess()){
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

    protected BiResponse callBiServerByFtp(Enums.BI_FUNCTION_CODE functionCode, String filePath, String fileName, String pullPath) {
        try {
            log.info("ftp path:======================"+filePath+"/"+pullPath);
            //推送文件的地址信息
            String pushServerFile = String.format("%s%s", ftpUploadPath, fileName);
            String pushLocalFile = String.format("%s%s", filePath, fileName);

            if(log.isDebugEnabled()) {
                log.debug("[forecast] Portal to BI >> Ftp file path:{} , Local file path:{}", pushServerFile, pushLocalFile);
            }
            ftpClientUtil.put(pushServerFile, pushLocalFile);
            BiResponse result = callBiServer(functionCode, ftpUploadPath, fileName, ftpDownloadPath);
            log.info("[forecast] BI handle result info >> {}", JSONObject.toJSONString(result));
            //获取文件的地址信息
            String biFileName = result.getFilePath().substring(result.getFilePath().lastIndexOf("/") + 1);
            String pullLocalFile = String.format("%s%s", pullPath, biFileName);
            if(log.isDebugEnabled()) {
                log.debug("[forecast] BI to Portal info >> Ftp file path:{} , Local file path:{}", result.getFilePath(), pullLocalFile);
            }
            ftpClientUtil.get(result.getFilePath(), pullLocalFile);
            result.setFilePath(pullLocalFile);
            return result;
        }catch (Exception ex) {
            throw new RuntimeException("Ftp exception or bi server exception", ex);
        }
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
            String response = CallApiUtils.callBiApi(CHECK_FORECAST_IMPORT_DATA, "PORTAL/BI/", fullPath, pullPath);
            //检测返回信息是否空
            if(StringUtils.isEmpty(response)){
                log.error("{} -> {}", FORECAST_BI_RESPONSE_EXCEPTION.getZhMsg(), response);
                throw new BusinessException(FORECAST_BI_RESPONSE_EXCEPTION);
            }
            //解析BI返回的结果信息，去掉多余的字符
            response = response.replace("\"", "");
            if(log.isDebugEnabled()){
                log.debug("[forecast] Bi server response info -> pushPath:[{}], pullPath:[{}], response:[{}]", fullPath, pullPath, response);
            }
            //将返回结果信息进行截取，获取到对方的文件名
            String BiFileName = response.split(":")[1];
            //封装结果流转给下一个流程处理
            if(response.contains(SUCCESS_CODE)){
                return new BiResponse(true, BiFileName);
            }
            if(response.contains(ERROR_CODE)){
                return new BiResponse(false, BiFileName);
            }
            log.error(FORECAST_BI_RESPONSE_EXCEPTION.getZhMsg(), response);
            throw new BusinessException(FORECAST_BI_RESPONSE_EXCEPTION);
        }catch (Exception ex) {
            log.error(FORECAST_BI_SERVER_EXCEPTION.getZhMsg(), ex);
            throw new BusinessException(FORECAST_BI_SERVER_EXCEPTION);
        }
    }


    /**
     * 预测数据Delete服务
     * @param biIds
     */
    private void requestBiDeleteServer(List<String> biIds) {
        try {
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
    private void requestBiInsertServer(List<BiAgencyInsertTemplate> insertData, Integer[] forecastIds, String passMsg) {
        try {
            String insertFileName = ExcelUtils.writeExcel(forecastPushPath, insertData, BiAgencyInsertTemplate.class);
            String fullPath = String.format("%s%s", ftpUploadPath, insertFileName);
            String param = "sFromUrl=" + fullPath + "&sToUrl=" + ftpDownloadPath;
            String response = CallApiUtils.callBiGetApi(INSERT_FORECAST_IMPORT_DATA, "PORTAL/BI/", param);
            //检测返回信息是否空
            if(StringUtils.isEmpty(response)){
                log.error("{} -> {}", FORECAST_BI_RESPONSE_EXCEPTION.getZhMsg(), response);
                throw new BusinessException(FORECAST_BI_RESPONSE_EXCEPTION);
            }
            //解析BI返回的结果信息，去掉多余的字符
            response = response.replace("\"", "");
            if(log.isDebugEnabled()){
                log.debug("[forecast] Bi server response info -> pushPath:[{}], pullPath:[{}], response:[{}]", fullPath, forecastPullPath, response);
            }
            //将返回结果信息进行截取，获取到对方的文件名
            String biFileName = response.split(":")[1];
            List<BiTotalInsertTemplate> insertResList = ExcelUtils.readExcel(forecastPullPath+biFileName, BiTotalInsertTemplate.class);
            for(BiTotalInsertTemplate insertTemplate : insertResList){
                forecastMapper.updateBiInfoByKey(Integer.parseInt(insertTemplate.getId()), insertTemplate.getBiId(), insertTemplate.getErrorMsg());
            }
            if(response.contains(SUCCESS_CODE)){
                forecastMapper.updateStatusByIds(forecastIds, 2, passMsg);
            }
        }catch (Exception ex) {
            log.error(FORECAST_BI_SERVER_EXCEPTION.getZhMsg(), ex);
            throw new BusinessException(FORECAST_BI_SERVER_EXCEPTION);
        }
    }

    /**
     * 预测数据Update服务
     * @param updateData
     */
    private void requestBiUpdateServer(List<BiAgencyUpdateTemplate> updateData, Integer[] forecastIds, String passMsg) {
        try {
            String updateFileName = ExcelUtils.writeExcel(forecastPushPath, updateData, BiAgencyUpdateTemplate.class);
            String fullPath = String.format("%s%s", ftpUploadPath, updateFileName);
            String param = "sFromUrl=" + fullPath + "&sToUrl=" + ftpDownloadPath;
            String response = CallApiUtils.callBiGetApi(UPDATE_FORECAST_IMPORT_DAT, "PORTAL/BI/", param);
            //检测返回信息是否空
            if(StringUtils.isEmpty(response)){
                log.error("{} -> {}", FORECAST_BI_RESPONSE_EXCEPTION.getZhMsg(), response);
                throw new BusinessException(FORECAST_BI_RESPONSE_EXCEPTION);
            }
            //解析BI返回的结果信息，去掉多余的字符
            response = response.replace("\"", "");
            if(log.isDebugEnabled()){
                log.debug("[forecast] Bi server response info -> pushPath:[{}], pullPath:[{}], response:[{}]", fullPath, forecastPullPath, response);
            }
            //将返回结果信息进行截取，获取到对方的文件名
            String biFileName = response.split(":")[1];
            List<BiAgencyUpdateTemplate> updateResList = ExcelUtils.readExcel(forecastPullPath+biFileName, BiAgencyUpdateTemplate.class);
            for(BiAgencyUpdateTemplate updateTemplate : updateResList){
                if(StringUtils.isNotEmpty(updateTemplate.getErrorMsg())){
                    forecastMapper.updateBiInfoByBiId(updateTemplate.getBiId(), updateTemplate.getErrorMsg());
                }
            }
            if(response.contains(SUCCESS_CODE)){
                forecastMapper.updateStatusByIds(forecastIds, 2, passMsg);
            }
        }catch (Exception ex) {
            log.error(FORECAST_BI_SERVER_EXCEPTION.getZhMsg(), ex);
            throw new BusinessException(FORECAST_BI_SERVER_EXCEPTION);
        }
    }

    private BiResponse callBiServer(Enums.BI_FUNCTION_CODE functionCode, String filePath, String fileName, String pullPath) {
        try {
            String fullPath = String.format("%s%s", filePath, fileName);
            String response;
            if(functionCode == Enums.BI_FUNCTION_CODE.CHECK_FORECAST_IMPORT_DATA){
                response = mockThirdResult() ? "\"OK:"+fullPath+"\"" :
                        "\"NG:"+fullPath+"\"";
            }else{

                response = "";
            }
//            String response = CallApiUtils.callBiApi(functionCode, "PORTAL/BI/", fullPath, pullPath);
            if(StringUtils.isEmpty(response)){
                log.error("{} -> {}", FORECAST_BI_RESPONSE_EXCEPTION.getZhMsg(), response);
                throw new BusinessException(FORECAST_BI_RESPONSE_EXCEPTION);
            }
            response = response.replace("\"", "");
            if(log.isDebugEnabled()){
                log.debug("[forecast] Bi server response info -> pushPath:[{}], pullPath:[{}], response:[{}]", fullPath, pullPath, response);
            }
            String BiFileName = response.split(":")[1];
            if(response.contains(SUCCESS_CODE)){
                return new BiResponse(true, BiFileName);
            }
            if(response.contains(ERROR_CODE)){
                return new BiResponse(false, BiFileName);
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

    /**
     * 根据登录用户获取代理商信息
     * @param userId
     * @return
     */
    private String getAgencyAbbreviation(Integer userId) {
        try {
            CustomerInfo customerInfo = customerInfoService.getDealerByUser(userId);
            BusinessUtil.notNull(customerInfo, FORECAST_AGENCY_INFO_ERROR);
            BusinessUtil.assertEmpty(customerInfo.getCustAbbreviation(), FORECAST_AGENCY_INFO_ERROR);
            return customerInfo.getCustAbbreviation();
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

    class BiResponse {

        //是否成功处理
        private boolean isSuccess = false;
        //文件地址（完整路径+文件名称）
        private String filePath;

        BiResponse() {
        }

        public BiResponse(boolean isSuccess, String filePath) {
            this.isSuccess = isSuccess;
            this.filePath = filePath;
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
    }

}
