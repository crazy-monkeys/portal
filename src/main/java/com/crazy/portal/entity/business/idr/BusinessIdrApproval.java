package com.crazy.portal.entity.business.idr;

import lombok.Data;

import java.util.Date;

/**
 * 
 * @author Shawn
 * @date   2019-08-25 16:58::55
 */
@Data
public class BusinessIdrApproval {
    /**
     * 
     */
    private Integer id;

    /**
     * 保差退ID
     */
    private Integer idrInfoId;

    /**
     * 审批单号
     */
    private String orderNo;

    /**
     * 单据类型,KP(报价),CP（差价）,TH（退货）,HH（换货）
     */
    private String orderTye;

    /**
     * 审批人
     */
    private String reviewedpeople;

    /**
     * 下一位审批人
     */
    private String currentreviewer;

    /**
     * 审批状态 Agree(同意)，Reject(驳回)
     */
    private String reviewstatus;

    /**
     * 审批意见
     */
    private String comment;

    /**
     * Success或报错信息
     */
    private String message;

    /**
     * 创建时间
     */
    private Date createTime;


}