package com.crazy.portal.bean.order;

import com.crazy.portal.bean.common.PageBean;
import lombok.Data;

@Data
public class OrderQueryBean extends PageBean {
    /** 订单号 **/
    private String orderNo;
    /** 下单人 **/
    private String buyer;
    /** 订单状态 **/
    private String orderStatus;
    /** 下单开始时间 **/
    private String placeOrderStartDate;
    /** 下单结束时间 **/
    private String placeOrderEndDate;
    /** 发货开始时间 **/
    private String deliveryStartDate;
    /** 发货结束时间 **/
    private String deliveryEndDate;
}
