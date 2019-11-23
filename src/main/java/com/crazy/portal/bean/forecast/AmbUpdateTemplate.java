package com.crazy.portal.bean.forecast;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * Created by lee on 2019/9/11.
 */

@Data
public class AmbUpdateTemplate {

    @ExcelProperty(index = 0, value = "ID")
    private String id;

    @ExcelProperty(index = 1, value = "年月")
    private String operationYearMonth;

    @ExcelProperty(index = 2, value = "出货公司")
    private String company;

    @ExcelProperty(index = 3, value = "渠道")
    private String channel;

    @ExcelProperty(index = 4, value = "代理简称")
    private String agencyAbbreviation;

    @ExcelProperty(index = 5, value = "客户简称")
    private String customerAbbreviation;

    @ExcelProperty(index = 6, value = "客户类别")
    private String customerType;

    @ExcelProperty(index = 7, value = "预测类别")
    private String forecastType;

    @ExcelProperty(index = 8, value = "销售")
    private String salePeople;

    @ExcelProperty(index = 9, value = "队长")
    private String ambLeader;

    @ExcelProperty(index = 10, value = "首代")
    private String sdPeople;

    @ExcelProperty(index = 11, value = "代表处")
    private String representative;

    @ExcelProperty(index = 12, value = "BU")
    private String bu;

    @ExcelProperty(index = 13, value = "PDT")
    private String pdt;

    @ExcelProperty(index = 14, value = "Product Type")
    private String productType;

    @ExcelProperty(index = 15, value = "Platform")
    private String platform;

    @ExcelProperty(index = 16, value = "产品型号")
    private String productModel;

    @ExcelProperty(index = 17, value = "版本要求（非必填）")
    private String version;

    @ExcelProperty(index = 18, value = "本次销售预测总数量（K）")
    private String totalForecastNum;

    @ExcelProperty(index = 19, value = "本次销售预测总销售额（$K）")
    private String totalForecastSalesVolume;

    @ExcelProperty(index = 20, value = "客户专货库存截止日期")
    private String closeDate;

    @ExcelProperty(index = 21, value = "客户未完成的所有专货库存")
    private String delayStock;

    @ExcelProperty(index = 22, value = "月份1")
    private String forecastMonthOne;

    @ExcelProperty(index = 23, value = "上次销售预测1")
    private String lastWriteOne;

    @ExcelProperty(index = 24, value = "本次代理填写值1")
    private String currentWriteOne;

    @ExcelProperty(index = 25, value = "代理上报GAP1")
    private String gapOne;

    @ExcelProperty(index = 25, value = "备注1")
    private String remarkOne;

    @ExcelProperty(index = 26, value = "本次队长调整1")
    private String ambAdjustmentOne;

    @ExcelProperty(index = 27, value = "本次销售预测1")
    private String ambForecastSaleOne;

    @ExcelProperty(index = 28, value = "队长调整后GAP1")
    private String ambGapOne;

    @ExcelProperty(index = 29, value = "队长备注1")
    private String ambRemarkOne;

    @ExcelProperty(index = 30, value = "月份2")
    private String forecastMonthTwo;

    @ExcelProperty(index = 31, value = "上次销售预测2")
    private String lastWriteTwo;

    @ExcelProperty(index = 32, value = "本次代理填写值2")
    private String currentWriteTwo;

    @ExcelProperty(index = 34, value = "代理上报GAP2")
    private String gapTwo;

    @ExcelProperty(index = 33, value = "备注2")
    private String remarkTwo;

    @ExcelProperty(index = 34, value = "本次队长调整2")
    private String ambAdjustmentTwo;

    @ExcelProperty(index = 35, value = "本次销售预测2")
    private String ambForecastSaleTwo;

    @ExcelProperty(index = 36, value = "队长调整后GAP2")
    private String ambGapTwo;

    @ExcelProperty(index = 37, value = "队长备注2")
    private String ambRemarkTwo;

    @ExcelProperty(index = 38, value = "月份3")
    private String forecastMonthThree;

    @ExcelProperty(index = 39, value = "上次销售预测3")
    private String lastWriteThree;

    @ExcelProperty(index = 40, value = "本次代理填写值3")
    private String currentWriteThree;

    @ExcelProperty(index = 43, value = "代理上报GAP3")
    private String gapThree;

    @ExcelProperty(index = 41, value = "备注3")
    private String remarkThree;

    @ExcelProperty(index = 42, value = "本次队长调整3")
    private String ambAdjustmentThree;

    @ExcelProperty(index = 43, value = "本次销售预测3")
    private String ambForecastSaleThree;

    @ExcelProperty(index = 44, value = "队长调整后GAP3")
    private String ambGapThree;

    @ExcelProperty(index = 45, value = "队长备注3")
    private String ambRemarkThree;

    @ExcelProperty(index = 46, value = "月份4")
    private String forecastMonthFour;

    @ExcelProperty(index = 47, value = "上次销售预测4")
    private String lastWriteFour;

    @ExcelProperty(index = 48, value = "本次代理填写值4")
    private String currentWriteFour;

    @ExcelProperty(index = 52, value = "代理上报GAP4")
    private String gapFour;

    @ExcelProperty(index = 49, value = "备注4")
    private String remarkFour;

    @ExcelProperty(index = 50, value = "本次队长调整4")
    private String ambAdjustmentFour;

    @ExcelProperty(index = 51, value = "本次销售预测4")
    private String ambForecastSaleFour;

    @ExcelProperty(index = 52, value = "队长调整后GAP4")
    private String ambGapFour;

    @ExcelProperty(index = 53, value = "队长备注4")
    private String ambRemarkFour;

    @ExcelProperty(index = 54, value = "月份5")
    private String forecastMonthFive;

    @ExcelProperty(index = 55, value = "上次销售预测5")
    private String lastWriteFive;

    @ExcelProperty(index = 56, value = "本次代理填写值5")
    private String currentWriteFive;

    @ExcelProperty(index = 61, value = "代理上报GAP5")
    private String gapFive;

    @ExcelProperty(index = 57, value = "备注5")
    private String remarkFive;

    @ExcelProperty(index = 58, value = "本次队长调整5")
    private String ambAdjustmentFive;

    @ExcelProperty(index = 59, value = "本次销售预测5")
    private String ambForecastSaleFive;

    @ExcelProperty(index = 60, value = "队长调整后GAP5")
    private String ambGapFive;

    @ExcelProperty(index = 61, value = "队长备注5")
    private String ambRemarkFive;

    @ExcelProperty(index = 62, value = "月份6")
    private String forecastMonthSix;

    @ExcelProperty(index = 63, value = "上次销售预测6")
    private String lastWriteSix;

    @ExcelProperty(index = 64, value = "本次代理填写值6")
    private String currentWriteSix;

    @ExcelProperty(index = 70, value = "代理上报GAP6")
    private String gapSix;

    @ExcelProperty(index = 65, value = "备注6")
    private String remarkSix;

    @ExcelProperty(index = 66, value = "本次队长调整6")
    private String ambAdjustmentSix;

    @ExcelProperty(index = 67, value = "本次销售预测6")
    private String ambForecastSaleSix;

    @ExcelProperty(index = 68, value = "队长调整后GAP6")
    private String ambGapSix;

    @ExcelProperty(index = 69, value = "队长备注6")
    private String ambRemarkSix;

    @ExcelProperty(index = 70, value = "PO价格")
    private String poPrice;

    @ExcelProperty(index = 71, value = "虚拟料号")
    private String vmNumber;
}
