package com.crazy.portal.bean.order;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrderApprovalBean {

    /** 订单ID **/
    @NotNull(message = "订单ID不能为空")
    private Integer orderId;

    /** 审批信息 **/
    private String reason;

    /** 审批操作 1：通过 2：驳回 **/
    @NotNull(message = "审批状态不能为空")
    private Integer approvalStatus;
}
