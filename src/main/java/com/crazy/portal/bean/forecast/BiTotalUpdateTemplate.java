package com.crazy.portal.bean.forecast;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * Created by lee on 2019/9/18.
 */

@Data
public class BiTotalUpdateTemplate {

    @ExcelProperty(index = 0, value = "ID")
    private String biId;

    @ExcelProperty(index = 1, value = "月份1")
    private String forecastMonthOne;

    @ExcelProperty(index = 2, value = "首代调整1")
    private String sdAdjustmentOne;

    @ExcelProperty(index = 3, value = "首代备注1")
    private String sdRemarkOne;

    @ExcelProperty(index = 4, value = "月份2")
    private String forecastMonthTwo;

    @ExcelProperty(index = 5, value = "首代调整2")
    private String sdAdjustmentTwo;

    @ExcelProperty(index = 6, value = "首代备注2")
    private String sdRemarkTwo;

    @ExcelProperty(index = 7, value = "月份3")
    private String forecastMonthThree;

    @ExcelProperty(index = 8, value = "首代调整3")
    private String sdAdjustmentThree;

    @ExcelProperty(index = 9, value = "首代备注3")
    private String sdRemarkThree;

    @ExcelProperty(index = 10, value = "月份4")
    private String forecastMonthFour;

    @ExcelProperty(index = 11, value = "首代调整4")
    private String sdAdjustmentFour;

    @ExcelProperty(index = 12, value = "首代备注4")
    private String sdRemarkFour;

    @ExcelProperty(index = 13, value = "月份5")
    private String forecastMonthFive;

    @ExcelProperty(index = 14, value = "首代调整5")
    private String sdAdjustmentFive;

    @ExcelProperty(index = 15, value = "首代备注5")
    private String sdRemarkFive;

    @ExcelProperty(index = 16, value = "月份6")
    private String forecastMonthSix;

    @ExcelProperty(index = 17, value = "首代调整6")
    private String sdAdjustmentSix;

    @ExcelProperty(index = 18, value = "首代备注6")
    private String sdRemarkSix;

    @ExcelProperty(index = 19, value = "Portal ID")
    private String portalId;

    @ExcelProperty(index = 20, value = "错误信息")
    private String errorMsg;

}
