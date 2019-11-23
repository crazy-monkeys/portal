package com.crazy.portal.bean.forecast;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * Created by lee on 2019/9/13.
 */

@Data
public class SdUpdateTemplate {

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

    @ExcelProperty(index = 8, value = "虚拟料号")
    private String vmNumber;

    @ExcelProperty(index = 9, value = "首代销售预测总数量（K）")
    private String totalSdSaleForecastNum;

    @ExcelProperty(index = 10, value = "本次销售预测总数量（K）")
    private String totalForecastNum;

    @ExcelProperty(index = 11, value = "首代Buffer总数量（K）")
    private String totalSdBufferForecastNum;

    @ExcelProperty(index = 12, value = "客户专货库存截止日期")
    private String closeDate;

    @ExcelProperty(index = 13, value = "客户未完成的所有专货库存")
    private String delayStock;

    @ExcelProperty(index = 14, value = "月份1")
    private String forecastMonthOne;

    @ExcelProperty(index = 15, value = "上次销售预测1")
    private String lastWriteOne;

    @ExcelProperty(index = 16, value = "本次销售预测填写值1")
    private String currentWriteOne;

    @ExcelProperty(index = 17, value = "GAP1")
    private String gapOne;

    @ExcelProperty(index = 18, value = "首代Buffer1")
    private String sdBufferOne;

    @ExcelProperty(index = 19, value = "首代销售预测1")
    private String sdTotalValueOne;

    @ExcelProperty(index = 20, value = "月份2")
    private String forecastMonthTwo;

    @ExcelProperty(index = 21, value = "上次销售预测2")
    private String lastWriteTwo;

    @ExcelProperty(index = 22, value = "本次销售预测填写值2")
    private String currentWriteTwo;

    @ExcelProperty(index = 23, value = "GAP2")
    private String gapTwo;

    @ExcelProperty(index = 24, value = "首代Buffer2")
    private String sdBufferTwo;

    @ExcelProperty(index = 25, value = "首代销售预测2")
    private String sdTotalValueTwo;

    @ExcelProperty(index = 26, value = "月份3")
    private String forecastMonthThree;

    @ExcelProperty(index = 27, value = "上次销售预测3")
    private String lastWriteThree;

    @ExcelProperty(index = 28, value = "本次销售预测填写值3")
    private String currentWriteThree;

    @ExcelProperty(index = 29, value = "GAP3")
    private String gapThree;

    @ExcelProperty(index = 30, value = "首代Buffer3")
    private String sdBufferThree;

    @ExcelProperty(index = 31, value = "首代销售预测3")
    private String sdTotalValueThree;

    @ExcelProperty(index = 32, value = "月份4")
    private String forecastMonthFour;

    @ExcelProperty(index = 33, value = "上次销售预测4")
    private String lastWriteFour;

    @ExcelProperty(index = 34, value = "本次销售预测填写值4")
    private String currentWriteFour;

    @ExcelProperty(index = 35, value = "GAP4")
    private String gapFour;

    @ExcelProperty(index = 36, value = "首代Buffer4")
    private String sdBufferFour;

    @ExcelProperty(index = 37, value = "首代销售预测4")
    private String sdTotalValueFour;

    @ExcelProperty(index = 38, value = "月份5")
    private String forecastMonthFive;

    @ExcelProperty(index = 39, value = "上次销售预测5")
    private String lastWriteFive;

    @ExcelProperty(index = 40, value = "本次销售预测填写值5")
    private String currentWriteFive;

    @ExcelProperty(index = 41, value = "GAP5")
    private String gapFive;

    @ExcelProperty(index = 42, value = "首代Buffer5")
    private String sdBufferFive;

    @ExcelProperty(index = 43, value = "首代销售预测5")
    private String sdTotalValueFive;

    @ExcelProperty(index = 44, value = "月份6")
    private String forecastMonthSix;

    @ExcelProperty(index = 45, value = "上次销售预测6")
    private String lastWriteSix;

    @ExcelProperty(index = 46, value = "本次销售预测填写值6")
    private String currentWriteSix;

    @ExcelProperty(index = 47, value = "GAP6")
    private String gapSix;

    @ExcelProperty(index = 48, value = "首代Buffer6")
    private String sdBufferSix;

    @ExcelProperty(index = 49, value = "首代销售预测6")
    private String sdTotalValueSix;

}
