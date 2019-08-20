package com.crazy.portal.bean.price;

import lombok.Data;

import java.util.Date;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 21:27 2019-08-20
 * @Modified by:
 */
@Data
public class EnquiryPriceVO {
    /** 新建申请 start **/
    private String productModel;

    private String customerName;

    private String remark;


    /** 查询参数 start **/
    private String bu;

    private String pdt;

    private String platform;

    private String productType;

    /** @see com.crazy.portal.util.Enums.APPROVAL_STATUS **/
    private String approvalStatus;

    private Date applyBeginTime;

    private Date applyEndTime;

    private String proposer;

}
