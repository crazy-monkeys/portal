package com.crazy.portal.bean.order;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Date;

@Slf4j
@Data
public class OrderLineEO extends BaseRowModel {

    /**
     * 物料号
     */
    @ExcelProperty(value = "物料号", index = 0)
    private String productId;

    /**
     * 平台
     */
    @ExcelProperty(value = "平台", index = 1)
    private String platform;
    /**
     * 数量
     */
    @ExcelProperty(value = "数量", index = 2)
    private String num;

    /**
     * 期望交货月份
     */
    @ExcelProperty(value = "期望交货月份(yyyy-MM)", index = 3)
    @JSONField(format = "yyyy-MM")
    private Date expectedDeliveryMonth = null;

    /**
     * 定价日期
     */
    private String priceDate;

    /**
     * 含税价格
     */
    private BigDecimal rPrice = BigDecimal.ZERO;

    /**
     * 不含税价格
     */
    private BigDecimal rNetPrice = BigDecimal.ZERO;
}