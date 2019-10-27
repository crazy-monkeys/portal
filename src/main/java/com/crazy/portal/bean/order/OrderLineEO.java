package com.crazy.portal.bean.order;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
@Data
public class OrderLineEO {

    /**
     * 物料号
     */
    private String productId;

    /**
     * 平台
     */
    private String platform;
    /**
     * 数量
     */
    private String num;

    /**
     * 期望交货月份
     */
    private String expectedDeliveryMonth;

    /**
     * 客户简称
     */
    private String custAbbreviation;

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