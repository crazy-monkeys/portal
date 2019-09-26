package com.crazy.portal.entity.order;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author weiying
 * @date   2019-09-22 22:40::15
 */
@Data
public class DeliverOrder {
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
    private Integer salesOrderId;

    /**
     *
     */
    private String sapOrderNo;

    /**
     *
     */
    private String soldTo;

    /**
     *
     */
    private String sendTo;

    /**
     *
     */
    private String actualDeliveryDate;

    /**
     *
     */
    private String shippingPoint;

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
    private Date createTime;

    /**
     *
     */
    private Integer updateUserId;

    /**
     *
     */
    private Date updateTime;

    /**
     * 
     */
    @JSONField(format="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date deliverDate;

    private List<DeliverOrderLine> deliverOrderLineList;

    private List<OrderInvoice> orderInvoiceList;
}