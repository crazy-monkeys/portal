package com.crazy.portal.bean.order;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class OrderLineEO extends BaseRowModel {
    /**
     * 物料号
     */
    @ExcelProperty(value = "物料号", index = 0)
    private String materialNumber;
    /**
     * 单位
     */
    @ExcelProperty(value = "单位", index = 1)
    private String unit;
    /**
     * 数量
     */
    @ExcelProperty(value = "数量", index = 2)
    private String num;
}