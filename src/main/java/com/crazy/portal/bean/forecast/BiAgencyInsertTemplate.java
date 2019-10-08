package com.crazy.portal.bean.forecast;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * Created by lee on 2019/8/31.
 */

@Data
public class BiAgencyInsertTemplate extends BaseRowModel {

    @ExcelProperty(index = 0, value = "年月")
    private String operationYearMonth;

    @ExcelProperty(index = 1, value = "公司")
    private String company;

    @ExcelProperty(index = 2, value = "代理简称")
    private String agencyAbbreviation;

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

    @ExcelProperty(index = 11, value = "代理商填写值1")
    private String currentWriteOne;

    @ExcelProperty(index = 12, value = "阿米巴队长填写值1")
    private String ambAdjustmentOne;

    @ExcelProperty(index = 13, value = "代理商备注1")
    private String remarkOne;

    @ExcelProperty(index = 14, value = "阿米巴队长备注1")
    private String ambRemarkOne;

    @ExcelProperty(index = 15, value = "月份2")
    private String forecastMonthTwo;

    @ExcelProperty(index = 16, value = "代理商填写值2")
    private String currentWriteTwo;

    @ExcelProperty(index = 17, value = "阿米巴队长填写值2")
    private String ambAdjustmentTwo;

    @ExcelProperty(index = 18, value = "代理商备注2")
    private String remarkTwo;

    @ExcelProperty(index = 19, value = "阿米巴队长备注2")
    private String ambRemarkTwo;

    @ExcelProperty(index = 20, value = "月份3")
    private String forecastMonthThree;

    @ExcelProperty(index = 21, value = "代理商填写值3")
    private String currentWriteThree;

    @ExcelProperty(index = 22, value = "阿米巴队长填写值3")
    private String ambAdjustmentThree;

    @ExcelProperty(index = 23, value = "代理商备注3")
    private String remarkThree;

    @ExcelProperty(index = 24, value = "阿米巴队长备注3")
    private String ambRemarkThree;

    @ExcelProperty(index = 25, value = "月份4")
    private String forecastMonthFour;

    @ExcelProperty(index = 26, value = "代理商填写值4")
    private String currentWriteFour;

    @ExcelProperty(index = 27, value = "阿米巴队长填写值4")
    private String ambAdjustmentFour;

    @ExcelProperty(index = 28, value = "代理商备注4")
    private String remarkFour;

    @ExcelProperty(index = 29, value = "阿米巴队长备注4")
    private String ambRemarkFour;

    @ExcelProperty(index = 30, value = "月份5")
    private String forecastMonthFive;

    @ExcelProperty(index = 31, value = "代理商填写值5")
    private String currentWriteFive;

    @ExcelProperty(index = 32, value = "阿米巴队长填写值5")
    private String ambAdjustmentFive;

    @ExcelProperty(index = 33, value = "代理商备注5")
    private String remarkFive;

    @ExcelProperty(index = 34, value = "阿米巴队长备注5")
    private String ambRemarkFive;

    @ExcelProperty(index = 35, value = "月份6")
    private String forecastMonthSix;

    @ExcelProperty(index = 36, value = "代理商填写值6")
    private String currentWriteSix;

    @ExcelProperty(index = 37, value = "阿米巴队长填写值6")
    private String ambAdjustmentSix;

    @ExcelProperty(index = 38, value = "代理商备注6")
    private String remarkSix;

    @ExcelProperty(index = 39, value = "阿米巴队长备注6")
    private String ambRemarkSix;

    @ExcelProperty(index = 40, value = "错误信息")
    private String errorMsg;

    @ExcelProperty(index = 41, value = "ID")
    private String biId;

    @ExcelProperty(index = 42, value = "PortalID")
    private String id;

}
