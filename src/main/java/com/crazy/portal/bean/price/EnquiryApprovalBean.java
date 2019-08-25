package com.crazy.portal.bean.price;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 23:33 2019-08-19
 * @Modified by:
 */
@Data
public class EnquiryApprovalBean {

    /**
     * 询价申请id
     */
    private List<Integer> applyIds = new ArrayList<>();

    /**
     * @see  com.crazy.portal.util.Enums.APPROVAL_STATUS
     */
    private String approvalStatus;

    //审批人
    private String approver;

    //审批说明
    private String approvalRemark;
}
