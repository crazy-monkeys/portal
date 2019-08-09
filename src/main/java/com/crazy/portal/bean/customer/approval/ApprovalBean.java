package com.crazy.portal.bean.customer.approval;

import lombok.Data;

/**
 * @Description TODO
 * @Author Shawn
 * @Date 2019-08-09 21:52
 * @Modify by
 */
@Data
public class ApprovalBean {
    //客户ID
    private Integer id;
    //代理商ID
    private Integer dealerId;
    //销售ID
    private Integer salesId;
    //审批信息
    private String approvalRemark;

    private Integer userId;
}
