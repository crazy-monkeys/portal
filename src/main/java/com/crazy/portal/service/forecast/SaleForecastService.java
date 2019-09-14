package com.crazy.portal.service.forecast;

import com.alibaba.fastjson.JSONObject;
import com.crazy.portal.bean.customer.CustomerOrgBean;
import com.crazy.portal.bean.forecast.*;
import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.dao.forecast.ForecastLineMapper;
import com.crazy.portal.dao.forecast.ForecastMapper;
import com.crazy.portal.entity.cusotmer.CustomerInfo;
import com.crazy.portal.entity.forecast.Forecast;
import com.crazy.portal.entity.forecast.ForecastLine;
import com.crazy.portal.service.customer.CustomerInfoService;
import com.crazy.portal.util.*;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    public ForecastResult uploadAgencyTemplate(MultipartFile excel, Integer userId) {
        BusinessUtil.notNull(excel, FORECAST_EXCEL_CHECK_ERROR);
        List<AgencyTemplate> agencyForecastList = ExcelUtils.readExcel(excel, AgencyTemplate.class);
        if(null == agencyForecastList || agencyForecastList.isEmpty()){
            log.warn(FORECAST_DATA_NOT_EMPTY.getZhMsg());
            throw new BusinessException(FORECAST_DATA_NOT_EMPTY);
        }
        String agencyAbbreviation = getAgencyAbbreviation(userId);
        String batchNo = generateBathNo();
        for(AgencyTemplate template : agencyForecastList){
            template.setAgencyAbbreviation(agencyAbbreviation);
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
            template.setId(String.valueOf(forecast.getId()));
        }
        return pushForecastDataToBi(batchNo);
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
            ExcelUtils.writeExcel(response, null, AgencyErrorTemplate.class);
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
        forecastMapper.clearErrorMsgByBatch(batchNo);
        for(AgencyErrorTemplate errorTemplate : errorTemplateList){
            Forecast forecast = forecastMapper.selectByPrimaryKey(Integer.parseInt(errorTemplate.getForecastId()));
            copyTemplateFields(errorTemplate, forecast);
            forecast.setErrorMsg("clear");
            forecastMapper.updateByPrimaryKeySelective(forecast);
        }
        return pushForecastDataToBi(batchNo);
    }

    /**
     * 代理商预测数据提交至 下一流程进行审批
     * @param batchNo
     * @param userId
     */
    public void commitAgencyForecastData(String batchNo, Integer userId) {
        int num = forecastMapper.countErrorDataByBatch(batchNo, userId);
        if(num != 0){
            log.error("Check error data num , parameter value : batchNo[{}] , userId:[{}]", batchNo, userId);
            throw new BusinessException(FORECAST_ERROR_DATA_EXISTS);
        }
        forecastMapper.updateStatus(1, batchNo, userId);
    }

    public PageInfo<Forecast> queryAgencyForecastData(Integer pageNum, Integer pageSize, Integer userId, Integer isReject) {
        PortalUtil.defaultStartPage(pageNum,pageSize);
        List<Forecast> result = forecastMapper.selectByUser(userId, isReject);
        return new PageInfo<>(result);

    }

    /**
     * 代理商预测数据删除
     * @param biIds
     */
    public void deleteAgencyForecastData(Integer[] biIds) {
        String biParam = StringUtils.join(biIds, ",");
        try {
            String response = CallApiUtils.callBiGetApi(DELETEFORECAST, "PORTAL/BI/", String.format("sIDList=%s&sSummaryIDList=%s", biParam, biParam));
            response = response.replace("\"", "").replace("\\","");
            if(StringUtils.isNotEmpty(response) && response.contains("删除成功")){
                forecastMapper.deleteByBiIds(biIds);
                return;
            }
            throw new BusinessException(FORECAST_BI_DELETE_FAIL);
        }catch (Exception ex) {
            log.error(FORECAST_BI_DELETE_FAIL.getZhMsg(), ex);
            throw new BusinessException(FORECAST_BI_DELETE_FAIL);
        }
    }

    public void updateAgencyForecastData(List<ForecastParam> list, Integer userId) {
        BusinessUtil.notNull(list, FORECAST_REQ_PARAM_NOT_EMPTY);
        for(ForecastParam data : list){
            Forecast forecast = forecastMapper.selectByPrimaryKey(data.getForecastId());
            if(null == forecast){
                log.error("{} , parameter value : {}", FORECAST_DB_DATA_MISMATCH.getZhMsg(), data);
                throw new BusinessException(FORECAST_DB_DATA_MISMATCH);
            }
            if(forecast.getStatus() == 2){
                //TODO 如果数据已经被确认需要重新确认
            }
            ForecastLine line = data.getLine();
            ForecastLine dbLine = forecastLineMapper.selectByPrimaryKey(line.getLineId());
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

        List<AgencyErrorTemplate> errorList = new ArrayList<>();
        for(Forecast forecast : rejectDataList) {
            AgencyErrorTemplate agencyTemplate = new AgencyErrorTemplate();
            copyDbFields(forecast, agencyTemplate);
            errorList.add(agencyTemplate);
        }
        ExcelUtils.writeExcel(response, errorList, AgencyErrorTemplate.class);
    }

    public PageInfo<Forecast> queryApprovalForecastData(Integer pageNum, Integer pageSize, Integer userId) {
        PortalUtil.defaultStartPage(pageNum,pageSize);
        //TODO 根据登录用户查询他名下可以查看的代理商
        Integer[] userIds = new Integer[]{1,2};
        List<Forecast> result = forecastMapper.selectByLeader(userIds);
        return new PageInfo<>(result);
    }

    public void updateSingleForecastData(ForecastParam param) {
        ForecastLine paramLine = param.getLine();
        ForecastLine dbRecord = forecastLineMapper.selectByPrimaryKey(paramLine.getLineId());
        if(null == dbRecord){
            throw new BusinessException("没有记录");
        }
        if(param.getSortNum() == 1){
            dbRecord.setAmbAdjustmentOne(paramLine.getAmbAdjustmentOne());
            dbRecord.setAmbRemarkOne(paramLine.getRemarkOne());
            dbRecord.setSdAdjustmentOne(paramLine.getSdAdjustmentOne());
            dbRecord.setSdRemarkOne(paramLine.getSdRemarkOne());
        }
        if(param.getSortNum() == 2){
            dbRecord.setAmbAdjustmentTwo(paramLine.getAmbAdjustmentTwo());
            dbRecord.setAmbRemarkTwo(paramLine.getRemarkTwo());
            dbRecord.setSdAdjustmentTwo(paramLine.getSdAdjustmentTwo());
            dbRecord.setSdRemarkTwo(paramLine.getSdRemarkTwo());
        }
        if(param.getSortNum() == 3){
            dbRecord.setAmbAdjustmentThree(paramLine.getAmbAdjustmentThree());
            dbRecord.setAmbRemarkThree(paramLine.getRemarkThree());
            dbRecord.setSdAdjustmentThree(paramLine.getSdAdjustmentThree());
            dbRecord.setSdRemarkThree(paramLine.getSdRemarkThree());
        }
        if(param.getSortNum() == 4){
            dbRecord.setAmbAdjustmentFour(paramLine.getAmbAdjustmentFour());
            dbRecord.setAmbRemarkFour(paramLine.getRemarkFour());
            dbRecord.setSdAdjustmentFour(paramLine.getSdAdjustmentFour());
            dbRecord.setSdRemarkFour(paramLine.getSdRemarkFour());
        }
        if(param.getSortNum() == 5){
            dbRecord.setAmbAdjustmentFive(paramLine.getAmbAdjustmentFive());
            dbRecord.setAmbRemarkFive(paramLine.getRemarkFive());
            dbRecord.setSdAdjustmentFive(paramLine.getSdAdjustmentFive());
            dbRecord.setSdRemarkFive(paramLine.getSdRemarkFive());
        }
        if(param.getSortNum() == 6){
            dbRecord.setAmbAdjustmentSix(paramLine.getAmbAdjustmentSix());
            dbRecord.setAmbRemarkSix(paramLine.getRemarkSix());
            dbRecord.setSdAdjustmentSix(paramLine.getSdAdjustmentSix());
            dbRecord.setSdRemarkSix(paramLine.getSdRemarkSix());
        }
        forecastLineMapper.updateByPrimaryKeySelective(dbRecord);
    }

    public void passApprovalForecastData(Integer[] forecastIds, String passMsg) {
        forecastMapper.updateStatusByIds(forecastIds, 2);
        //TODO 最终提交给BI
    }

    public void rejectApprovalForecastData(Integer[] forecastIds, String rejectMsg) {
        forecastMapper.updateStatusByIds(forecastIds, -1);
    }

    public void downloadDataByAmb(HttpServletResponse response, Integer[] forecastIds) {
        List<Forecast> forecastList = forecastMapper.selectByIds(forecastIds);
        List<AmbUpdateTemplate> templateList = new ArrayList<>();
        for(Forecast forecast : forecastList) {
            AmbUpdateTemplate agencyTemplate = new AmbUpdateTemplate();
            copyDbFields(forecast, agencyTemplate);
            templateList.add(agencyTemplate);
        }
        ExcelUtils.writeExcel(response, templateList, AmbUpdateTemplate.class);
    }

    public void downloadDataBySd(HttpServletResponse response, Integer[] forecastIds) {
        List<Forecast> forecastList = forecastMapper.selectByIds(forecastIds);
        List<SdUpdateTemplate> templateList = new ArrayList<>();
        for(Forecast forecast : forecastList) {
            SdUpdateTemplate agencyTemplate = new SdUpdateTemplate();
            copyDbFields(forecast, agencyTemplate);
            templateList.add(agencyTemplate);
        }
        ExcelUtils.writeExcel(response, null, SdUpdateTemplate.class);
    }

    private ForecastResult pushForecastDataToBi(String batchNo) {
        List<Forecast> biList = forecastMapper.selectByBatchNo(batchNo);
        List<BiAgencyCheckTemplate> reqBiDataList = new ArrayList<>();
        try {
            for(Forecast forecast : biList){
                BiAgencyCheckTemplate biData = new BiAgencyCheckTemplate();
                BeanUtils.copyNotNullFields(forecast, biData);
                BeanUtils.copyNotNullFields(forecast.getLine(), biData);
                biData.setId(String.valueOf(forecast.getId()));
                reqBiDataList.add(biData);
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        String checkFileName = ExcelUtils.writeExcel(forecastPushPath, reqBiDataList, BiAgencyCheckTemplate.class);
        BiResponse response = callBiServer(CHECK_FORECAST_IMPORT_DATA, forecastPushPath, checkFileName, forecastPullPath);
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
        CustomerInfo customerInfo = customerInfoService.getDealerByUser(userId);
        BusinessUtil.notNull(customerInfo, FORECAST_AGENCY_INFO_ERROR);
        BusinessUtil.assertEmpty(customerInfo.getCustAbbreviation(), FORECAST_AGENCY_INFO_ERROR);
        return customerInfo.getCustAbbreviation();
    }

    /**
     * 根据客户简称获取关联上级客户信息，同时对数据正确性进行基础校验
     * @param customerAbbreviation
     * @return
     */
    private CustomerOrgBean getCustomerOrgInfo(String customerAbbreviation) {
        CustomerOrgBean customerOrgBean = customerInfoService.selectByAbbreviation(customerAbbreviation);
        BusinessUtil.notNull(customerOrgBean, FORECAST_NOT_FOUND_CUSTOMER_INFO);
        BusinessUtil.assertEmpty(customerOrgBean.getCustType(), FORECAST_NOT_FOUND_CUSTOMER_INFO);
        BusinessUtil.assertEmpty(customerOrgBean.getSales(), FORECAST_NOT_FOUND_CUSTOMER_INFO);
        BusinessUtil.assertEmpty(customerOrgBean.getAmb(), FORECAST_NOT_FOUND_CUSTOMER_INFO);
        BusinessUtil.assertEmpty(customerOrgBean.getPm(), FORECAST_NOT_FOUND_CUSTOMER_INFO);
        BusinessUtil.assertEmpty(customerOrgBean.getOffice(), FORECAST_NOT_FOUND_CUSTOMER_INFO);
        return customerOrgBean;
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
