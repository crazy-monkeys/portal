package com.crazy.portal.bean.customer.approval;

import lombok.Data;

/**
 * @Description
 * @Author Shawn
 * @Date 2019-08-09 21:52
 * @Modify by
 */
@Data
public class ApprovalBean {
    /*审批类型 1 通过  2驳回*/
    private Integer approvalType;
    //审批人Id
    private Integer approveUser;
    //客户Id
    private Integer custId;
    //审批意见
    private String approvalRemark;

    //分配代理商
    private Integer salesId;
    //分配销售
    private Integer dealerId;
}
