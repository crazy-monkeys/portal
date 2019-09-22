package com.crazy.portal.entity.order;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author weiying
 * @date   2019-09-22 19:50::48
 */
@Data
public class DeliverOrderLine {
    /**
     * 
     */
    private Integer deliverOrderLineId;

    /**
     * 
     */
    private String sapDeliverOrderLineNo;

    /**
     * 
     */
    private Integer salesOrderId;

    /**
     * 
     */
    private String sapOrderNo;

    /**
     * 
     */
    private Integer salesOrderLineId;

    /**
     * 
     */
    private String sapSalesOrderLineNo;

    /**
     * 
     */
    private Integer deliverOrderId;

    /**
     * 
     */
    private String sapDeliverOrderNo;

    /**
     * 
     */
    private String productId;

    /**
     * 
     */
    private String product;

    /**
     * 
     */
    private Integer deliveryQuantity;

    /**
     * 
     */
    private Integer active;

    /**
     * 
     */
    private Integer createUserId;

    /**
     * 
     */
    private Date createDate;

    /**
     * 
     */
    private Integer updateUserId;

    /**
     * 
     */
    private Date updateDate;
}