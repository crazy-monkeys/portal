package com.crazy.portal.bean.price;

import com.crazy.portal.bean.common.PageBean;
import lombok.Data;


/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 21:27 2019-08-20
 * @Modified by:
 */
@Data
public class EnquiryPriceVO extends PageBean {

    /**
     * 产品型号
     */
    private String productModel;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 申请人
     */
    private String proposer;

    /**
     * 申请说明
     */
    private String remark;

    /**
     * 审批状态
     * @see com.crazy.portal.util.Enums.APPROVAL_STATUS
     **/
    private String approvalStatus;

    /**
     * 审批人
     */
    private String approver;

    /**
     * 审批说明
     */
    private String approvalRemark;
}
