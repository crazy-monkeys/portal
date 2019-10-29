package com.crazy.portal.bean.forecast;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * Created by lee on 2019/8/31.
 */

@Data
public class BiAgencyUpdateTemplate {

    @ExcelProperty(index = 0, value = "ID")
    private String biId;

    @ExcelProperty(index = 1, value = "版本要求")
    private String version;

    @ExcelProperty(index = 2, value = "截止日期")
    private String closeDate;

    @ExcelProperty(index = 3, value = "客户未完成的所有专货库存")
    private String delayStock;

    @ExcelProperty(index = 4, value = "月份1")
    private String forecastMonthOne;

    @ExcelProperty(index = 5, value = "代理商填写值1")
    private String currentWriteOne;

    @ExcelProperty(index = 6, value = "阿米巴队长填写值1")
    private String ambAdjustmentOne;

    @ExcelProperty(index = 7, value = "代理商备注1")
    private String remarkOne;

    @ExcelProperty(index = 8, value = "阿米巴队长备注1")
    private String ambRemarkOne;

    @ExcelProperty(index = 9, value = "月份2")
    private String forecastMonthTwo;

    @ExcelProperty(index = 10, value = "代理商填写值2")
    private String currentWriteTwo;

    @ExcelProperty(index = 11, value = "阿米巴队长填写值2")
    private String ambAdjustmentTwo;

    @ExcelProperty(index = 12, value = "代理商备注2")
    private String remarkTwo;

    @ExcelProperty(index = 13, value = "阿米巴队长备注2")
    private String ambRemarkTwo;

    @ExcelProperty(index = 14, value = "月份3")
    private String forecastMonthThree;

    @ExcelProperty(index = 15, value = "代理商填写值3")
    private String currentWriteThree;

    @ExcelProperty(index = 16, value = "阿米巴队长填写值3")
    private String ambAdjustmentThree;

    @ExcelProperty(index = 17, value = "代理商备注3")
    private String remarkThree;

    @ExcelProperty(index = 18, value = "阿米巴队长备注3")
    private String ambRemarkThree;

    @ExcelProperty(index = 19, value = "月份4")
    private String forecastMonthFour;

    @ExcelProperty(index = 20, value = "代理商填写值4")
    private String currentWriteFour;

    @ExcelProperty(index = 21, value = "阿米巴队长填写值4")
    private String ambAdjustmentFour;

    @ExcelProperty(index = 22, value = "代理商备注4")
    private String remarkFour;

    @ExcelProperty(index = 23, value = "阿米巴队长备注4")
    private String ambRemarkFour;

    @ExcelProperty(index = 24, value = "月份5")
    private String forecastMonthFive;

    @ExcelProperty(index = 25, value = "代理商填写值5")
    private String currentWriteFive;

    @ExcelProperty(index = 26, value = "阿米巴队长填写值5")
    private String ambAdjustmentFive;

    @ExcelProperty(index = 27, value = "代理商备注5")
    private String remarkFive;

    @ExcelProperty(index = 28, value = "阿米巴队长备注5")
    private String ambRemarkFive;

    @ExcelProperty(index = 29, value = "月份6")
    private String forecastMonthSix;

    @ExcelProperty(index = 30, value = "代理商填写值6")
    private String currentWriteSix;

    @ExcelProperty(index = 31, value = "阿米巴队长填写值6")
    private String ambAdjustmentSix;

    @ExcelProperty(index = 32, value = "代理商备注6")
    private String remarkSix;

    @ExcelProperty(index = 33, value = "阿米巴队长备注6")
    private String ambRemarkSix;

    @ExcelProperty(index = 34, value = "Portal ID")
    private String portalId;

    @ExcelProperty(index = 35, value = "错误信息")
    private String errorMsg;

}
