package com.crazy.portal.bean.forecast;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * Created by lee on 2019/9/18.
 */

@Data
public class BiTotalUpdateTemplate extends BaseRowModel {

    @ExcelProperty(index = 0, value = "BI_ID")
    private String biId;

    @ExcelProperty(index = 1, value = "")
    private String forecastMonthOne;

    @ExcelProperty(index = 2, value = "")
    private String sdAdjustmentOne;

    @ExcelProperty(index = 3, value = "")
    private String sdRemarkOne;

    @ExcelProperty(index = 4, value = "")
    private String forecastMonthTwo;

    @ExcelProperty(index = 5, value = "")
    private String sdAdjustmentTwo;

    @ExcelProperty(index = 6, value = "")
    private String sdRemarkTwo;

    @ExcelProperty(index = 7, value = "")
    private String forecastMonthThree;

    @ExcelProperty(index = 8, value = "")
    private String sdAdjustmentThree;

    @ExcelProperty(index = 9, value = "")
    private String sdRemarkThree;

    @ExcelProperty(index = 10, value = "")
    private String forecastMonthFour;

    @ExcelProperty(index = 11, value = "")
    private String sdAdjustmentFour;

    @ExcelProperty(index = 12, value = "")
    private String sdRemarkFour;

    @ExcelProperty(index = 13, value = "")
    private String forecastMonthFive;

    @ExcelProperty(index = 14, value = "")
    private String sdAdjustmentFive;

    @ExcelProperty(index = 15, value = "")
    private String sdRemarkFive;

    @ExcelProperty(index = 16, value = "")
    private String forecastMonthSix;

    @ExcelProperty(index = 17, value = "")
    private String sdAdjustmentSix;

    @ExcelProperty(index = 18, value = "")
    private String sdRemarkSix;

    @ExcelProperty(index = 19, value = "错误信息")
    private String errorMsg;

}
