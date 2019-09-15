package com.crazy.portal.bean.forecast;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * Created by lee on 2019/8/31.
 */

@Data
public class BiAgencyCheckTemplate extends BaseRowModel {

    @ExcelProperty(index = 0, value = "年月")
    private String operationYearMonth;

    @ExcelProperty(index = 1, value = "公司")
    private String company;

    @ExcelProperty(index = 2, value = "代理简称")
    private String AgencyAbbreviation;

    @ExcelProperty(index = 3, value = "客户简称")
    private String customerAbbreviation;

    @ExcelProperty(index = 4, value = "platform")
    private String platform;

    @ExcelProperty(index = 5, value = "产品型号")
    private String productModel;

    @ExcelProperty(index = 6, value = "虚拟料号")
    private String vmNumber;

    @ExcelProperty(index = 7, value = "版本要求")
    private String version;

    @ExcelProperty(index = 8, value = "截止日期")
    private String closeDate;

    @ExcelProperty(index = 9, value = "客户未完成的所有专货库存")
    private String delayStock;

    @ExcelProperty(index = 10, value = "月份1")
    private String forecastMonthOne;

    @ExcelProperty(index = 11, value = "本次填写1")
    private String currentWriteOne;

    @ExcelProperty(index = 12, value = "备注1")
    private String remarkOne;

    @ExcelProperty(index = 13, value = "月份2")
    private String forecastMonthTwo;

    @ExcelProperty(index = 14, value = "本次填写2")
    private String currentWriteTwo;

    @ExcelProperty(index = 15, value = "备注2")
    private String remarkTwo;

    @ExcelProperty(index = 16, value = "月份3")
    private String forecastMonthThree;

    @ExcelProperty(index = 17, value = "本次填写3")
    private String currentWriteThree;

    @ExcelProperty(index = 18, value = "备注3")
    private String remarkThree;

    @ExcelProperty(index = 19, value = "月份4")
    private String forecastMonthFour;

    @ExcelProperty(index = 20, value = "本次填写4")
    private String currentWriteFour;

    @ExcelProperty(index = 21, value = "备注4")
    private String remarkFour;

    @ExcelProperty(index = 22, value = "月份5")
    private String forecastMonthFive;

    @ExcelProperty(index = 23, value = "本次填写5")
    private String currentWriteFive;

    @ExcelProperty(index = 24, value = "备注5")
    private String remarkFive;

    @ExcelProperty(index = 25, value = "月份6")
    private String forecastMonthSix;

    @ExcelProperty(index = 26, value = "本次填写6")
    private String currentWriteSix;

    @ExcelProperty(index = 27, value = "备注6")
    private String remarkSix;

    @ExcelProperty(index = 28, value = "错误信息")
    private String errorMsg;

    @ExcelProperty(index = 29, value = "PortalID")
    private String id;

}
