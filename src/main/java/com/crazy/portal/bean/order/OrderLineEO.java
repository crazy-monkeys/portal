package com.crazy.portal.bean.order;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
@Data
public class OrderLineEO {

    /**
     * 物料号
     */
    @ExcelProperty(value = "物料号", index = 0)
    private String productId;
    /**
     * 客户简称
     */
    @ExcelProperty(value = "客户简称", index = 1)
    private String custAbbreviation;
    /**
     * 平台
     */
    @ExcelProperty(value = "平台", index = 2)
    private String platform;
    /**
     * 数量
     */
    @ExcelProperty(value = "数量", index = 3)
    private String num;

    /**
     * 期望交货月份
     */
    @ExcelProperty(value = "期望交货月份(yyyy-MM)", index = 4)
    private String expectedDeliveryMonth;

    /**
     * 定价日期
     */
    @ExcelIgnore
    private String priceDate;

    /**
     * 含税价格
     */
    @ExcelIgnore
    private BigDecimal rPrice = BigDecimal.ZERO;

    /**
     * 不含税价格
     */
    @ExcelIgnore
    private BigDecimal rNetPrice = BigDecimal.ZERO;

    @ExcelIgnore
    private String pu;

    @ExcelIgnore
    private String pdt;
}