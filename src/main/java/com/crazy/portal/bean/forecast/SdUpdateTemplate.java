package com.crazy.portal.bean.forecast;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * Created by lee on 2019/9/13.
 */

@Data
public class SdUpdateTemplate extends BaseRowModel {

    @ExcelProperty(index = 0, value = "ID")
    private String id;

    @ExcelProperty(index = 1, value = "年月")
    private String operationYearMonth;

    @ExcelProperty(index = 2, value = "出货公司")
    private String company;

    @ExcelProperty(index = 3, value = "BU")
    private String bu;

    @ExcelProperty(index = 4, value = "PDT")
    private String pdt;

    @ExcelProperty(index = 5, value = "Product Type")
    private String productType;

    @ExcelProperty(index = 6, value = "Platform")
    private String platform;

    @ExcelProperty(index = 7, value = "产品型号")
    private String productModel;

    @ExcelProperty(index = 8, value = "首代销售预测总数量（K）")
    private String totalSdSaleForecastNum;

    @ExcelProperty(index = 9, value = "本次销售预测总数量（K）")
    private String totalForecastNum;

    @ExcelProperty(index = 10, value = "首代Buffer总数量（K）")
    private String totalSdBufferForecastNum;

    @ExcelProperty(index = 11, value = "客户专货库存截止日期")
    private String closeDate;

    @ExcelProperty(index = 12, value = "客户未完成的所有专货库存")
    private String delayStock;

    @ExcelProperty(index = 13, value = "月份1")
    private String forecastMonthOne;

    @ExcelProperty(index = 14, value = "上次填写1")
    private String lastWriteOne;

    @ExcelProperty(index = 15, value = "本次填写1")
    private String currentWriteOne;

    @ExcelProperty(index = 16, value = "GAP1")
    private String gapOne;

    @ExcelProperty(index = 17, value = "首代Buffer1")
    private String sdBufferOne;

    @ExcelProperty(index = 18, value = "月份2")
    private String forecastMonthTwo;

    @ExcelProperty(index = 19, value = "上次填写2")
    private String lastWriteTwo;

    @ExcelProperty(index = 20, value = "本次填写2")
    private String currentWriteTwo;

    @ExcelProperty(index = 21, value = "GAP2")
    private String gapTwo;

    @ExcelProperty(index = 22, value = "首代Buffer2")
    private String sdBufferTwo;

    @ExcelProperty(index = 23, value = "月份3")
    private String forecastMonthThree;

    @ExcelProperty(index = 24, value = "上次填写3")
    private String lastWriteThree;

    @ExcelProperty(index = 25, value = "本次填写3")
    private String currentWriteThree;

    @ExcelProperty(index = 26, value = "GAP3")
    private String gapThree;

    @ExcelProperty(index = 27, value = "首代Buffer3")
    private String sdBufferThree;

    @ExcelProperty(index = 28, value = "月份4")
    private String forecastMonthFour;

    @ExcelProperty(index = 29, value = "上次填写4")
    private String lastWriteFour;

    @ExcelProperty(index = 30, value = "本次填写4")
    private String currentWriteFour;

    @ExcelProperty(index = 31, value = "GAP4")
    private String gapFour;

    @ExcelProperty(index = 32, value = "首代Buffer4")
    private String sdBufferFour;

    @ExcelProperty(index = 33, value = "月份5")
    private String forecastMonthFive;

    @ExcelProperty(index = 34, value = "上次填写5")
    private String lastWriteFive;

    @ExcelProperty(index = 35, value = "本次填写5")
    private String currentWriteFive;

    @ExcelProperty(index = 36, value = "GAP5")
    private String gapFive;

    @ExcelProperty(index = 37, value = "首代Buffer5")
    private String sdBufferFive;

    @ExcelProperty(index = 38, value = "月份6")
    private String forecastMonthSix;

    @ExcelProperty(index = 39, value = "上次填写6")
    private String lastWriteSix;

    @ExcelProperty(index = 40, value = "本次填写6")
    private String currentWriteSix;

    @ExcelProperty(index = 41, value = "GAP6")
    private String gapSix;

    @ExcelProperty(index = 42, value = "首代Buffer6")
    private String sdBufferSix;

}