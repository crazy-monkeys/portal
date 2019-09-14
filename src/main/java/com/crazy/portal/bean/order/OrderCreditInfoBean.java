package com.crazy.portal.bean.order;

import lombok.Data;

@Data
public class OrderCreditInfoBean {

    /** 订单ID **/
    private Integer orderId;

    /** 授信额度初始值 **/
    private String creditInitialValue;

    /** 授信额度剩余值 **/
    private String creditSurplusValue;

    /** 授信额度可用值 **/
    private String creditaVailableValue;

    /** SAP订单类型 **/
    private String sapOrderType;

}
