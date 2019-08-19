package com.crazy.portal.bean.price;

import lombok.Data;

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
    private Integer applyId;

    /**
     * 1:申请通过  -1:申请驳回
     */
    private Integer status;
}
