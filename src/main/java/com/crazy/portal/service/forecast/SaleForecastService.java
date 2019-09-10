package com.crazy.portal.service.forecast;

import com.alibaba.fastjson.JSONObject;
import com.crazy.portal.bean.forecast.*;
import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.dao.forecast.ForecastLineMapper;
import com.crazy.portal.dao.forecast.ForecastMapper;
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
            try {
                BeanUtils.copyNotNullFields(forecast, agencyTemplate);
                BeanUtils.copyNotNullFields(forecast.getLine(), agencyTemplate);
                templateList.add(agencyTemplate);
            }catch (Exception ex) {
                ex.printStackTrace();
            }
            templateList.add(agencyTemplate);
        }
        ExcelUtils.writeExcel(response, templateList, AgencyTemplate.class);
    }

    public ForecastResult uploadAgencyTemplate(MultipartFile excel, Integer userId) {
        BusinessUtil.notNull(excel, FORECAST_EXCEL_CHECK_ERROR);
        List<AgencyTemplate> agencyForecastList = ExcelUtils.readExcel(excel, AgencyTemplate.class);
        if(null == agencyForecastList || agencyForecastList.isEmpty()){
            log.warn(FORECAST_DATA_NOT_EMPTY.getZhMsg());
            throw new BusinessException(FORECAST_DATA_NOT_EMPTY);
        }
//        String AgencyAbbreviation = customerInfoService.getDealerByUser(userId).getCustAbbreviation();
        String AgencyAbbreviation = "测试的代理商简称";
        List<BiAgencyCheckTemplate> reqBiDataList = JSONObject.parseArray(JSONObject.toJSONString(agencyForecastList), BiAgencyCheckTemplate.class);
        for(BiAgencyCheckTemplate biAgencyCheckTemplate : reqBiDataList){
            biAgencyCheckTemplate.setAgencyAbbreviation(AgencyAbbreviation);
        }
        String checkFileName = ExcelUtils.writeExcel(forecastPushPath, reqBiDataList, BiAgencyCheckTemplate.class);
        BiResponse response = callBiServer(CHECK_FORECAST_IMPORT_DATA, forecastPushPath, checkFileName, forecastPullPath);
        List<BiAgencyCheckTemplate> responseDataList = ExcelUtils.readExcel(response.getFilePath(), BiAgencyCheckTemplate.class);
        String batchNo = generateBathNo();
        for(BiAgencyCheckTemplate responseData : responseDataList){
            Forecast forecast = new Forecast();
            try {
                BeanUtils.copyNotNullFields(responseData, forecast);
                forecast.setCreateUserId(userId);
                forecast.setCreateTime(new Date());
                forecast.setBatchNo(batchNo);
                forecastMapper.insertSelective(forecast);
                if(log.isDebugEnabled()){
                    log.debug("[upload data] Save forecast head data , userId:{} , data:{}", userId, JSONObject.toJSON(forecast));
                }
                ForecastLine line = new ForecastLine();
                BeanUtils.copyNotNullFields(responseData, line);
                line.setfId(forecast.getId());
                forecastLineMapper.insertSelective(line);
                if(log.isDebugEnabled()){
                    log.debug("[upload data] Save forecast line data , userId:{} , data:{}", userId, JSONObject.toJSON(line));
                }
            }catch (Exception ex) {

            }
        }
        ForecastResult result = new ForecastResult();
        result.setSuccess(response.isSuccess());
        result.setData(responseDataList);
        result.setBatchNo(batchNo);
        return result;
    }

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
            try {
                BeanUtils.copyNotNullFields(forecast, agencyTemplate);
                agencyTemplate.setForecastId(String.valueOf(forecast.getId()));
                BeanUtils.copyNotNullFields(forecast.getLine(), agencyTemplate);
            }catch (Exception ex) {
                ex.printStackTrace();
            }
            errorList.add(agencyTemplate);
        }
        ExcelUtils.writeExcel(response, errorList, AgencyErrorTemplate.class);
    }

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
        int dbCnt = forecastMapper.countErrorDataByBatch(batchNo, userId);
        if(dbCnt != errorTemplateList.size()){
            log.error("{} , parameter value : batchNo[{}]", FORECAST_DB_DATA_MISMATCH.getZhMsg(), batchNo);
            throw new BusinessException(FORECAST_DB_DATA_MISMATCH);
        }

        List<Forecast> forecastList = forecastMapper.selectNotErrorDataByBatch(batchNo, userId);
//        String AgencyAbbreviation = customerInfoService.getDealerByUser(userId).getCustAbbreviation();
        String AgencyAbbreviation = "测试的代理商简称";
        List<BiAgencyCheckTemplate> reqBiDataList = JSONObject.parseArray(JSONObject.toJSONString(forecastList), BiAgencyCheckTemplate.class);
        reqBiDataList.addAll(JSONObject.parseArray(JSONObject.toJSONString(forecastList), BiAgencyCheckTemplate.class));
        for(BiAgencyCheckTemplate biAgencyCheckTemplate : reqBiDataList){
            biAgencyCheckTemplate.setAgencyAbbreviation(AgencyAbbreviation);
        }

        String checkFileName = ExcelUtils.writeExcel(forecastPushPath, reqBiDataList, BiAgencyCheckTemplate.class);
        BiResponse response = callBiServer(CHECK_FORECAST_IMPORT_DATA, forecastPushPath, checkFileName, forecastPullPath);
        List<BiAgencyCheckTemplate> responseDataList = ExcelUtils.readExcel(response.getFilePath(), BiAgencyCheckTemplate.class);
        //清理前面的记录，以最新返回的为准
        forecastMapper.deleteByBatchNo(batchNo, userId);
        for(BiAgencyCheckTemplate responseData : responseDataList){
            Forecast forecast = new Forecast();
            try {
                BeanUtils.copyNotNullFields(responseData, forecast);
                forecast.setCreateUserId(userId);
                forecast.setCreateTime(new Date());
                forecast.setBatchNo(batchNo);
                forecastMapper.insertSelective(forecast);
                if(log.isDebugEnabled()){
                    log.debug("[modify data] Save forecast head data , userId:{} , data:{}", userId, JSONObject.toJSON(forecast));
                }
                ForecastLine line = new ForecastLine();
                BeanUtils.copyNotNullFields(responseData, line);
                line.setfId(forecast.getId());
                forecastLineMapper.insertSelective(line);
                if(log.isDebugEnabled()){
                    log.debug("[modify data] Save forecast line data , userId:{} , data:{}", userId, JSONObject.toJSON(line));
                }
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        ForecastResult result = new ForecastResult();
        result.setSuccess(response.isSuccess());
        result.setData(responseDataList);
        result.setBatchNo(batchNo);
        return result;
    }

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

    public void deleteAgencyForecastData(Integer[] biIds) {
        String biParam = StringUtils.join(biIds, ",");
        try {
            String response = CallApiUtils.callBiGetApi(DELETEFORECAST, "PORTAL/BI/", String.format("sIDList=%s&sSummaryIDList=%s", biParam, biParam));
            response = response.replace("\"", "").replace("\\","");
            if("删除成功".equals(response)){
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

    public void downloadRejectData(HttpServletResponse response, Integer[] forecastIds, Integer userId) {
        BusinessUtil.notNull(forecastIds, FORECAST_REQ_PARAM_NOT_EMPTY);
        List<Forecast> rejectDataList = forecastMapper.selectRejectDataByIds(forecastIds, userId);

        List<AgencyErrorTemplate> errorList = new ArrayList<>();
        for(Forecast forecast : rejectDataList) {
            AgencyErrorTemplate agencyTemplate = new AgencyErrorTemplate();
            try {
                BeanUtils.copyNotNullFields(forecast, agencyTemplate);
                BeanUtils.copyNotNullFields(forecast.getLine(), agencyTemplate);
            }catch (Exception ex) {
                ex.printStackTrace();
            }
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
            String response;
            if(functionCode == Enums.BI_FUNCTION_CODE.CHECK_FORECAST_IMPORT_DATA){
                response = mockThirdResult() ? "\"OK:/service/ftp/portal_file/pull_thrid/forecast/forecast_check.xlsx\"" :
                        "\"NG:/service/ftp/portal_file/pull_thrid/forecast/forecast_check.xlsx\"";
            }else{

                response = "";
            }
            String fullPath = String.format("%s%s", filePath, fileName);
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
