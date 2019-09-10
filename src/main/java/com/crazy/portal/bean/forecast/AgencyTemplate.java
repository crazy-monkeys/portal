package com.crazy.portal.bean.forecast;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * Created by lee on 2019/8/31.
 */

@Data
public class AgencyTemplate extends BaseRowModel {

    @ExcelProperty(index = 0, value = "年月")
    private String yearMonth;

    @ExcelProperty(index = 1, value = "出货公司")
    private String company;

    @ExcelProperty(index = 2, value = "渠道")
    private String channel;

    @ExcelProperty(index = 3, value = "客户简称")
    private String customerAbbreviation;

    @ExcelProperty(index = 4, value = "预测类别")
    private String forecastType;

    @ExcelProperty(index = 5, value = "Platform")
    private String Platform;

    @ExcelProperty(index = 6, value = "产品型号")
    private String productModel;

    @ExcelProperty(index = 7, value = "虚拟料号")
    private String vmNumber;

    @ExcelProperty(index = 8, value = "版本要求")
    private String version;

    @ExcelProperty(index = 9, value = "客户专货库存截止日期")
    private String closeDate;

    @ExcelProperty(index = 10, value = "客户未完成的所有专货库存")
    private String delayStock;

    @ExcelProperty(index = 11, value = "月份1")
    private String forecastMonthOne;

    @ExcelProperty(index = 12, value = "上次填写1")
    private String lastWriteOne;

    @ExcelProperty(index = 13, value = "本次填写1")
    private String currentWriteOne;

    @ExcelProperty(index = 14, value = "GAP1")
    private String gapOne;

    @ExcelProperty(index = 15, value = "备注1")
    private String remarkOne;

    @ExcelProperty(index = 16, value = "月份2")
    private String forecastMonthTwo;

    @ExcelProperty(index = 17, value = "上次填写2")
    private String lastWriteTwo;

    @ExcelProperty(index = 18, value = "本次填写2")
    private String currentWriteTwo;

    @ExcelProperty(index = 19, value = "GAP2")
    private String gapTwo;

    @ExcelProperty(index = 20, value = "备注2")
    private String remarkTwo;

    @ExcelProperty(index = 21, value = "月份3")
    private String forecastMonthThree;

    @ExcelProperty(index = 22, value = "上次填写3")
    private String lastWriteThree;

    @ExcelProperty(index = 23, value = "本次填写3")
    private String currentWriteThree;

    @ExcelProperty(index = 24, value = "GAP3")
    private String gapThree;

    @ExcelProperty(index = 25, value = "备注3")
    private String remarkThree;

    @ExcelProperty(index = 26, value = "月份4")
    private String forecastMonthFour;

    @ExcelProperty(index = 27, value = "上次填写4")
    private String lastWriteFour;

    @ExcelProperty(index = 28, value = "本次填写4")
    private String currentWriteFour;

    @ExcelProperty(index = 29, value = "GAP4")
    private String gapFour;

    @ExcelProperty(index = 30, value = "备注4")
    private String remarkFour;

    @ExcelProperty(index = 31, value = "月份5")
    private String forecastMonthFive;

    @ExcelProperty(index = 32, value = "上次填写5")
    private String lastWriteFive;

    @ExcelProperty(index = 33, value = "本次填写5")
    private String currentWriteFive;

    @ExcelProperty(index = 34, value = "GAP5")
    private String gapFive;

    @ExcelProperty(index = 35, value = "备注5")
    private String remarkFive;

    @ExcelProperty(index = 36, value = "月份6")
    private String forecastMonthSix;

    @ExcelProperty(index = 37, value = "上次填写6")
    private String lastWriteSix;

    @ExcelProperty(index = 38, value = "本次填写6")
    private String currentWriteSix;

    @ExcelProperty(index = 39, value = "GAP6")
    private String gapSix;

    @ExcelProperty(index = 40, value = "备注6")
    private String remarkSix;

}