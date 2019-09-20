package com.crazy.portal.bean.order;

import lombok.Data;

import java.util.List;

@Data
public class BatchModifyOrderBean {

    /** 订单ID集合 **/
    private List<Integer> orderIds;
    /** 发货日期 **/
    private String deliveryDate;
}
