package com.crazy.portal.bean.order;

import com.crazy.portal.bean.common.PageBean;
import lombok.Data;

import java.util.Date;

@Data
public class OrderQueryBean extends PageBean {
    /** 订单号 **/
    private String rSapOrderId;
    /** 下单人 **/
    private Integer dealerId;
    /** 下单开始日期 **/
    private Date createBeginTime;
    /** 下单结束日期 **/
    private Date createEndTime;
    /** 订单类型**/
    private String orderType;
    /** 下单类型**/
    private String underOrderType;
    /** 售达方**/
    private String soldTo;
    /** 送达方 **/
    private String sendTo;
    /** 采购订单编号 **/
    private String purchaseNo;
    /**审批状态**/
    private Integer approvalStatus;
}
