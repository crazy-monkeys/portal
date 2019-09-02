package com.crazy.portal.service.forecast;

import com.crazy.portal.bean.forecast.*;
import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.dao.forecast.ForecastLineMapper;
import com.crazy.portal.dao.forecast.ForecastMapper;
import com.crazy.portal.entity.forecast.Forecast;
import com.crazy.portal.entity.forecast.ForecastLine;
import com.crazy.portal.util.BusinessUtil;
import com.crazy.portal.util.DateUtil;
import com.crazy.portal.util.ExcelUtils;
import com.crazy.portal.util.PortalUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    @Value("${file.path.forecast.push}")
    private String forecastPushPath;
    @Value("${file.path.forecast.pull}")
    private String forecastPullPath;

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
            for(ForecastLine line : forecast.getLines()){
                if(1 == line.getSortNum()){
                    agencyTemplate.setForecastMonthOne(line.getForecastMonth());
                    agencyTemplate.setLastWriteOne(line.getLastWrite());
                    agencyTemplate.setGapOne(line.getGap());
                    agencyTemplate.setRemarkOne(line.getRemark());
                }
                if(2 == line.getSortNum()){
                    agencyTemplate.setForecastMonthTwo(line.getForecastMonth());
                    agencyTemplate.setLastWriteTwo(line.getLastWrite());
                    agencyTemplate.setGapTwo(line.getGap());
                    agencyTemplate.setRemarkTwo(line.getRemark());
                }
                if(3 == line.getSortNum()){
                    agencyTemplate.setForecastMonthThree(line.getForecastMonth());
                    agencyTemplate.setLastWriteThree(line.getLastWrite());
                    agencyTemplate.setGapThree(line.getGap());
                    agencyTemplate.setRemarkThree(line.getRemark());
                }
                if(4 == line.getSortNum()){
                    agencyTemplate.setForecastMonthFour(line.getForecastMonth());
                    agencyTemplate.setLastWriteFour(line.getLastWrite());
                    agencyTemplate.setGapFour(line.getGap());
                    agencyTemplate.setRemarkFour(line.getRemark());
                }
                if(5 == line.getSortNum()){
                    agencyTemplate.setForecastMonthFive(line.getForecastMonth());
                    agencyTemplate.setLastWriteFive(line.getLastWrite());
                    agencyTemplate.setGapFive(line.getGap());
                    agencyTemplate.setRemarkFive(line.getRemark());
                }
                if(6 == line.getSortNum()){
                    agencyTemplate.setForecastMonthSix(line.getForecastMonth());
                    agencyTemplate.setLastWriteSix(line.getLastWrite());
                    agencyTemplate.setGapSix(line.getGap());
                    agencyTemplate.setRemarkSix(line.getRemark());
                }
            }
            templateList.add(agencyTemplate);
        }
        ExcelUtils.writeExcel(response, templateList, AgencyTemplate.class);
    }

    public ForecastResult uploadAgencyTemplate(MultipartFile excel, Integer userId) {
        List<AgencyTemplate> agencyForecastList = ExcelUtils.readExcel(excel, AgencyTemplate.class);
        String batchNo = generateBathNo();
        for(AgencyTemplate template : agencyForecastList){
            Forecast forecast = new Forecast();
            forecast.setOperationYearMonth(template.getYearMonth());
            forecast.setCompany(template.getCompany());
            forecast.setCustomerName(template.getCustomerName());
            forecast.setCustomerAbbreviation(template.getCustomerAbbreviation());
            forecast.setForecastType(template.getForecastType());
            forecast.setVmNumber(template.getVmNumber());
            forecast.setVersion(template.getVersion());
            forecast.setCloseDate(template.getCloseDate());
            forecast.setDelayStock(template.getDelayStock());
            forecast.setCreateUserId(userId);
            forecast.setCreateTime(new Date());
            forecast.setBatchNo(batchNo);
            forecastMapper.insertSelective(forecast);

            saveForecastLineData(forecast.getId(), 1, template.getForecastMonthOne(), template.getLastWriteOne(),
                    template.getCurrentWriteOne(), template.getGapOne(), template.getRemarkOne());

            saveForecastLineData(forecast.getId(), 2, template.getForecastMonthTwo(), template.getLastWriteTwo(),
                    template.getCurrentWriteTwo(), template.getGapTwo(), template.getRemarkTwo());

            saveForecastLineData(forecast.getId(), 3, template.getForecastMonthThree(), template.getLastWriteThree(),
                    template.getCurrentWriteThree(), template.getGapThree(), template.getRemarkThree());

            saveForecastLineData(forecast.getId(), 4, template.getForecastMonthFour(), template.getLastWriteFour(),
                    template.getCurrentWriteFour(), template.getGapFour(), template.getRemarkFour());

            saveForecastLineData(forecast.getId(), 5, template.getForecastMonthFive(), template.getLastWriteFive(),
                    template.getCurrentWriteFive(), template.getGapFive(), template.getRemarkFive());

            saveForecastLineData(forecast.getId(), 6, template.getForecastMonthSix(), template.getLastWriteSix(),
                    template.getCurrentWriteSix(), template.getGapSix(), template.getRemarkSix());
        }
        List<BiAgencyCheckTemplate> biResponseData = ExcelUtils.readExcel(forecastPullPath + "forecast_check.xlsx", BiAgencyCheckTemplate.class);
        //TODO BI返回的数据录入数据库
        ForecastResult result = new ForecastResult();
        result.setData(biResponseData);
        result.setBatchNo(batchNo);
        return result;
    }

    public void downloadAgencyError(HttpServletResponse response, String batchNo, Integer userId) {
        List<Forecast> errorTemplateList = forecastMapper.selectErrorDataByBatch(batchNo, userId);
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
            for(ForecastLine line : forecast.getLines()){
                if(1 == line.getSortNum()){
                    agencyTemplate.setForecastMonthOne(line.getForecastMonth());
                    agencyTemplate.setLastWriteOne(line.getLastWrite());
                    agencyTemplate.setGapOne(line.getGap());
                    agencyTemplate.setRemarkOne(line.getRemark());
                }
                if(2 == line.getSortNum()){
                    agencyTemplate.setForecastMonthTwo(line.getForecastMonth());
                    agencyTemplate.setLastWriteTwo(line.getLastWrite());
                    agencyTemplate.setGapTwo(line.getGap());
                    agencyTemplate.setRemarkTwo(line.getRemark());
                }
                if(3 == line.getSortNum()){
                    agencyTemplate.setForecastMonthThree(line.getForecastMonth());
                    agencyTemplate.setLastWriteThree(line.getLastWrite());
                    agencyTemplate.setGapThree(line.getGap());
                    agencyTemplate.setRemarkThree(line.getRemark());
                }
                if(4 == line.getSortNum()){
                    agencyTemplate.setForecastMonthFour(line.getForecastMonth());
                    agencyTemplate.setLastWriteFour(line.getLastWrite());
                    agencyTemplate.setGapFour(line.getGap());
                    agencyTemplate.setRemarkFour(line.getRemark());
                }
                if(5 == line.getSortNum()){
                    agencyTemplate.setForecastMonthFive(line.getForecastMonth());
                    agencyTemplate.setLastWriteFive(line.getLastWrite());
                    agencyTemplate.setGapFive(line.getGap());
                    agencyTemplate.setRemarkFive(line.getRemark());
                }
                if(6 == line.getSortNum()){
                    agencyTemplate.setForecastMonthSix(line.getForecastMonth());
                    agencyTemplate.setLastWriteSix(line.getLastWrite());
                    agencyTemplate.setGapSix(line.getGap());
                    agencyTemplate.setRemarkSix(line.getRemark());
                }
            }
            errorList.add(agencyTemplate);
        }
        ExcelUtils.writeExcel(response, errorList, AgencyErrorTemplate.class);
    }

    public ForecastResult modifyErrorData(MultipartFile excel, String batchNo, Integer userId) {
        List<AgencyErrorTemplate> errorTemplateList = ExcelUtils.readExcel(excel, AgencyErrorTemplate.class);
        for(AgencyErrorTemplate errorTemplate : errorTemplateList){
            Forecast dbForecast = forecastMapper.selectByPrimaryKey(Integer.parseInt(errorTemplate.getForecastId()));
            if(null == dbForecast || !dbForecast.getBatchNo().equals(batchNo)){
                throw new BusinessException("没有记录");
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
            saveForecastLineData(forecast.getId(), 1, errorTemplate.getForecastMonthOne(), errorTemplate.getLastWriteOne(),
                    errorTemplate.getCurrentWriteOne(), errorTemplate.getGapOne(), errorTemplate.getRemarkOne());

            saveForecastLineData(forecast.getId(), 2, errorTemplate.getForecastMonthTwo(), errorTemplate.getLastWriteTwo(),
                    errorTemplate.getCurrentWriteTwo(), errorTemplate.getGapTwo(), errorTemplate.getRemarkTwo());

            saveForecastLineData(forecast.getId(), 3, errorTemplate.getForecastMonthThree(), errorTemplate.getLastWriteThree(),
                    errorTemplate.getCurrentWriteThree(), errorTemplate.getGapThree(), errorTemplate.getRemarkThree());

            saveForecastLineData(forecast.getId(), 4, errorTemplate.getForecastMonthFour(), errorTemplate.getLastWriteFour(),
                    errorTemplate.getCurrentWriteFour(), errorTemplate.getGapFour(), errorTemplate.getRemarkFour());

            saveForecastLineData(forecast.getId(), 5, errorTemplate.getForecastMonthFive(), errorTemplate.getLastWriteFive(),
                    errorTemplate.getCurrentWriteFive(), errorTemplate.getGapFive(), errorTemplate.getRemarkFive());

            saveForecastLineData(forecast.getId(), 6, errorTemplate.getForecastMonthSix(), errorTemplate.getLastWriteSix(),
                    errorTemplate.getCurrentWriteSix(), errorTemplate.getGapSix(), errorTemplate.getRemarkSix());
        }
        List<BiAgencyCheckTemplate> biResponseData = ExcelUtils.readExcel(forecastPullPath + "forecast_check.xlsx", BiAgencyCheckTemplate.class);
        //TODO BI返回的数据录入数据库
        ForecastResult result = new ForecastResult();
        result.setData(biResponseData);
        result.setBatchNo(batchNo);
        return result;
    }

    public void commitAgencyForecastData(String batchNo, Integer userId) {
        int cnt = forecastMapper.countErrorDataByBatch(batchNo, userId);
        if(cnt != 0){
            throw new BusinessException("");
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
        for(ForecastParam data : list){
            Forecast forecast = forecastMapper.selectByPrimaryKey(data.getForecastId());
            if(null == forecast){
                throw new BusinessException("");
            }
            if(forecast.getStatus() == 2){
                //TODO 如果数据已经被确认需要重新确认
            }
            for(ForecastLine line : data.getLines()){
                ForecastLine dbLine = forecastLineMapper.selectByPrimaryKey(line.getId());
                if(null == dbLine){
                    throw new BusinessException("");
                }
                dbLine.setCurrentWrite(line.getCurrentWrite());
                forecastLineMapper.updateByPrimaryKeySelective(dbLine);
            }
            forecast.setUpdateTime(new Date());
            forecast.setUpdateUserId(userId);
            forecastMapper.updateByPrimaryKey(forecast);
        }
    }

    private void saveForecastLineData(Integer fId, Integer sortNum, String forecastMonth, String lastWrite,
                                      String currentWrite, String gap, String remark) {
        ForecastLine lineOne = new ForecastLine();
        lineOne.setfId(fId);
        lineOne.setSortNum(sortNum);
        lineOne.setForecastMonth(forecastMonth);
        lineOne.setLastWrite(lastWrite);
        lineOne.setCurrentWrite(currentWrite);
        lineOne.setGap(gap);
        lineOne.setRemark(remark);
        forecastLineMapper.insertSelective(lineOne);
    }

    private String generateBathNo() {
        return UUID.randomUUID().toString().toUpperCase();
    }

}
