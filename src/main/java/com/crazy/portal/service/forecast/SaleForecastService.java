package com.crazy.portal.service.forecast;

import com.alibaba.fastjson.JSONObject;
import com.crazy.portal.bean.forecast.*;
import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.dao.forecast.ForecastLineMapper;
import com.crazy.portal.dao.forecast.ForecastMapper;
import com.crazy.portal.entity.forecast.Forecast;
import com.crazy.portal.entity.forecast.ForecastLine;
import com.crazy.portal.entity.handover.DeliverDetail;
import com.crazy.portal.service.handover.AbstractHandover;
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
            agencyTemplate.setYearMonth(forecast.getOperationYearMonth());
            agencyTemplate.setCompany(forecast.getCompany());
            agencyTemplate.setCustomerName(forecast.getCustomerName());
            agencyTemplate.setCustomerAbbreviation(forecast.getCustomerAbbreviation());
            agencyTemplate.setForecastType(forecast.getForecastType());
            agencyTemplate.setVmNumber(forecast.getVmNumber());
            agencyTemplate.setVersion(forecast.getVersion());
            agencyTemplate.setCloseDate(forecast.getCloseDate());
            agencyTemplate.setDelayStock(forecast.getDelayStock());
            ForecastLine line = forecast.getLine();
            agencyTemplate.setForecastMonthOne(line.getForecastMonthOne());
            agencyTemplate.setLastWriteOne(line.getLastWriteOne());
            agencyTemplate.setGapOne(line.getGapOne());
            agencyTemplate.setRemarkOne(line.getRemarkOne());
            agencyTemplate.setForecastMonthTwo(line.getForecastMonthTwo());
            agencyTemplate.setLastWriteTwo(line.getLastWriteTwo());
            agencyTemplate.setGapTwo(line.getGapTwo());
            agencyTemplate.setRemarkTwo(line.getRemarkTwo());
            agencyTemplate.setForecastMonthThree(line.getForecastMonthThree());
            agencyTemplate.setLastWriteThree(line.getLastWriteThree());
            agencyTemplate.setGapThree(line.getGapThree());
            agencyTemplate.setRemarkThree(line.getRemarkThree());
            agencyTemplate.setForecastMonthFour(line.getForecastMonthFour());
            agencyTemplate.setLastWriteFour(line.getLastWriteFour());
            agencyTemplate.setGapFour(line.getGapFour());
            agencyTemplate.setRemarkFour(line.getRemarkFour());
            agencyTemplate.setForecastMonthFive(line.getForecastMonthFive());
            agencyTemplate.setLastWriteFive(line.getLastWriteFive());
            agencyTemplate.setGapFive(line.getGapFive());
            agencyTemplate.setRemarkFive(line.getRemarkFive());
            agencyTemplate.setForecastMonthSix(line.getForecastMonthSix());
            agencyTemplate.setLastWriteSix(line.getLastWriteSix());
            agencyTemplate.setGapSix(line.getGapSix());
            agencyTemplate.setRemarkSix(line.getRemarkSix());

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
        List<BiAgencyCheckTemplate> reqBiDataList = JSONObject.parseArray(JSONObject.toJSONString(agencyForecastList), BiAgencyCheckTemplate.class);
        for(BiAgencyCheckTemplate biAgencyCheckTemplate : reqBiDataList){
            //TODO 匹配产品相关信息
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
            agencyTemplate.setYearMonth(forecast.getOperationYearMonth());
            agencyTemplate.setCompany(forecast.getCompany());
            agencyTemplate.setCustomerName(forecast.getCustomerName());
            agencyTemplate.setCustomerAbbreviation(forecast.getCustomerAbbreviation());
            agencyTemplate.setForecastType(forecast.getForecastType());
            agencyTemplate.setVmNumber(forecast.getVmNumber());
            agencyTemplate.setVersion(forecast.getVersion());
            agencyTemplate.setCloseDate(forecast.getCloseDate());
            agencyTemplate.setDelayStock(forecast.getDelayStock());
            agencyTemplate.setForecastId(String.valueOf(forecast.getId()));
            agencyTemplate.setErrorMsg(forecast.getErrorMsg());
            ForecastLine line = forecast.getLine();
            agencyTemplate.setForecastMonthOne(line.getForecastMonthOne());
            agencyTemplate.setLastWriteOne(line.getLastWriteOne());
            agencyTemplate.setGapOne(line.getGapOne());
            agencyTemplate.setRemarkOne(line.getRemarkOne());
            agencyTemplate.setForecastMonthTwo(line.getForecastMonthTwo());
            agencyTemplate.setLastWriteTwo(line.getLastWriteTwo());
            agencyTemplate.setGapTwo(line.getGapTwo());
            agencyTemplate.setRemarkTwo(line.getRemarkTwo());
            agencyTemplate.setForecastMonthThree(line.getForecastMonthThree());
            agencyTemplate.setLastWriteThree(line.getLastWriteThree());
            agencyTemplate.setGapThree(line.getGapThree());
            agencyTemplate.setRemarkThree(line.getRemarkThree());
            agencyTemplate.setForecastMonthFour(line.getForecastMonthFour());
            agencyTemplate.setLastWriteFour(line.getLastWriteFour());
            agencyTemplate.setGapFour(line.getGapFour());
            agencyTemplate.setRemarkFour(line.getRemarkFour());
            agencyTemplate.setForecastMonthFive(line.getForecastMonthFive());
            agencyTemplate.setLastWriteFive(line.getLastWriteFive());
            agencyTemplate.setGapFive(line.getGapFive());
            agencyTemplate.setRemarkFive(line.getRemarkFive());
            agencyTemplate.setForecastMonthSix(line.getForecastMonthSix());
            agencyTemplate.setLastWriteSix(line.getLastWriteSix());
            agencyTemplate.setGapSix(line.getGapSix());
            agencyTemplate.setRemarkSix(line.getRemarkSix());
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
        for(AgencyErrorTemplate errorTemplate : errorTemplateList){
            Forecast dbForecast = forecastMapper.selectByPrimaryKey(Integer.parseInt(errorTemplate.getForecastId()));
            if(null == dbForecast || !dbForecast.getBatchNo().equals(batchNo)){
                log.error("{} , parameter value : batchNo[{}] , dbForecast[{}]", FORECAST_DB_DATA_MISMATCH.getZhMsg(),
                        batchNo, (null == dbForecast ? dbForecast : JSONObject.toJSON(dbForecast)));
                throw new BusinessException(FORECAST_DB_DATA_MISMATCH);
            }
            forecastMapper.deleteByPrimaryKey(Integer.parseInt(errorTemplate.getForecastId()));
            Forecast forecast = new Forecast();
            forecast.setOperationYearMonth(errorTemplate.getYearMonth());
            forecast.setCompany(errorTemplate.getCompany());
            forecast.setCustomerName(errorTemplate.getCustomerName());
            forecast.setCustomerAbbreviation(errorTemplate.getCustomerAbbreviation());
            forecast.setForecastType(errorTemplate.getForecastType());
            forecast.setVmNumber(errorTemplate.getVmNumber());
            forecast.setVersion(errorTemplate.getVersion());
            forecast.setCloseDate(errorTemplate.getCloseDate());
            forecast.setDelayStock(errorTemplate.getDelayStock());
            forecast.setCreateTime(new Date());
            forecast.setCreateUserId(userId);
            forecast.setBatchNo(batchNo);
            forecastMapper.insertSelective(forecast);
            if(log.isDebugEnabled()){
                log.debug("[modify data] Save forecast head data , userId:{} , data:{}", userId, JSONObject.toJSON(forecast));
            }
            ForecastLine line = new ForecastLine();
            line.setfId(forecast.getId());
            line.setForecastMonthOne(errorTemplate.getForecastMonthOne());
            line.setLastWriteOne(errorTemplate.getLastWriteOne());
            line.setGapOne(errorTemplate.getGapOne());
            line.setRemarkOne(errorTemplate.getRemarkOne());
            line.setForecastMonthTwo(errorTemplate.getForecastMonthTwo());
            line.setLastWriteTwo(errorTemplate.getLastWriteTwo());
            line.setGapTwo(errorTemplate.getGapTwo());
            line.setRemarkTwo(errorTemplate.getRemarkTwo());
            line.setForecastMonthThree(errorTemplate.getForecastMonthThree());
            line.setLastWriteThree(errorTemplate.getLastWriteThree());
            line.setGapThree(errorTemplate.getGapThree());
            line.setRemarkThree(errorTemplate.getRemarkThree());
            line.setForecastMonthFour(errorTemplate.getForecastMonthFour());
            line.setLastWriteFour(errorTemplate.getLastWriteFour());
            line.setGapFour(errorTemplate.getGapFour());
            line.setRemarkFour(errorTemplate.getRemarkFour());
            line.setForecastMonthFive(errorTemplate.getForecastMonthFive());
            line.setLastWriteFive(errorTemplate.getLastWriteFive());
            line.setGapFive(errorTemplate.getGapFive());
            line.setRemarkFive(errorTemplate.getRemarkFive());
            line.setForecastMonthSix(errorTemplate.getForecastMonthSix());
            line.setLastWriteSix(errorTemplate.getLastWriteSix());
            line.setGapSix(errorTemplate.getGapSix());
            line.setRemarkSix(errorTemplate.getRemarkSix());
            forecastLineMapper.insertSelective(line);
            if(log.isDebugEnabled()){
                log.debug("[modify data] Save forecast line data , userId:{} , data:{}", userId, JSONObject.toJSON(line));
            }
        }
        List<BiAgencyCheckTemplate> biResponseData = ExcelUtils.readExcel(forecastPullPath + "forecast_check.xlsx", BiAgencyCheckTemplate.class);
        //TODO BI返回的数据录入数据库
        ForecastResult result = new ForecastResult();
        result.setData(biResponseData);
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

    public PageInfo<Forecast> queryAgencyForecastData(Integer pageNum, Integer pageSize, Integer userId) {
        PortalUtil.defaultStartPage(pageNum,pageSize);
        List<Forecast> result = forecastMapper.selectByUser(userId);
        return new PageInfo<>(result);

    }

    public void deleteAgencyForecastData(Integer[] AgencyForecastData) {

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
            if(functionCode == Enums.BI_FUNCTION_CODE.CHECK_SALES_IMPORT_FILE){
                response = mockThirdResult() ? "\"OK:/Users/lee/Documents/job_code/portal_file/pull_thrid/forecast/forecast_check.xlsx\"" :
                        "\"NG:/Users/lee/Documents/job_code/portal_file/pull_thrid/forecast/forecast_check.xlsx\"";
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
