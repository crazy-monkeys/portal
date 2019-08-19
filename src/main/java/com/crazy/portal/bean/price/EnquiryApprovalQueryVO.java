package com.crazy.portal.bean.price;

import lombok.Data;

import java.util.Date;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 23:33 2019-08-19
 * @Modified by:
 */
@Data
public class EnquiryApprovalQueryVO {

    /**
     * 申请人
     */
    private String proposer;

    /**
     * 审批状态 1:通过 -1:驳回
     */
    private Integer approvalStatus;

    /**
     * 生效开始时间
     */
    private Date effectiveBeginTime;

    /**
     * 生效结束时间
     */
    private Date effectiveEndTime;
}
