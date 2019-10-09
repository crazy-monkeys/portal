package com.crazy.portal.bean.forecast;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * Created by lee on 2019/9/18.
 */

@Data
public class BiTotalInsertTemplate extends BaseRowModel {

    @ExcelProperty(index = 0, value = "年月")
    private String operationYearMonth;

    @ExcelProperty(index = 1, value = "公司")
    private String company;

    @ExcelProperty(index = 2, value = "Platform")
    private String platform;

    @ExcelProperty(index = 3, value = "产品型号")
    private String productModel;

    @ExcelProperty(index = 4, value = "虚拟料号")
    private String vmNumber;

    @ExcelProperty(index = 5, value = "月份1")
    private String forecastMonthOne;

    @ExcelProperty(index = 6, value = "首代调整1")
    private String sdAdjustmentOne;

    @ExcelProperty(index = 7, value = "首代备注1")
    private String sdRemarkOne;

    @ExcelProperty(index = 8, value = "月份2")
    private String forecastMonthTwo;

    @ExcelProperty(index = 9, value = "首代调整2")
    private String sdAdjustmentTwo;

    @ExcelProperty(index = 10, value = "首代备注2")
    private String sdRemarkTwo;

    @ExcelProperty(index = 11, value = "月份3")
    private String forecastMonthThree;

    @ExcelProperty(index = 12, value = "首代调整3")
    private String sdAdjustmentThree;

    @ExcelProperty(index = 13, value = "首代备注3")
    private String sdRemarkThree;

    @ExcelProperty(index = 14, value = "月份4")
    private String forecastMonthFour;

    @ExcelProperty(index = 15, value = "首代调整4")
    private String sdAdjustmentFour;

    @ExcelProperty(index = 16, value = "首代备注4")
    private String sdRemarkFour;

    @ExcelProperty(index = 17, value = "月份5")
    private String forecastMonthFive;

    @ExcelProperty(index = 18, value = "首代调整5")
    private String sdAdjustmentFive;

    @ExcelProperty(index = 19, value = "首代备注5")
    private String sdRemarkFive;

    @ExcelProperty(index = 20, value = "月份6")
    private String forecastMonthSix;

    @ExcelProperty(index = 21, value = "首代调整6")
    private String sdAdjustmentSix;

    @ExcelProperty(index = 22, value = "首代备注6")
    private String sdRemarkSix;

    @ExcelProperty(index = 23, value = "错误信息")
    private String errorMsg;

    @ExcelProperty(index = 24, value = "ID")
    private String biId;

    @ExcelProperty(index = 25, value = "PortalID")
    private String id;

}
