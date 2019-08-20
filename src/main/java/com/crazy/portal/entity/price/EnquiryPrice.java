package com.crazy.portal.entity.price;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author Bill
 * @date   2019-08-20 21:51::38
 */
@Data
public class EnquiryPrice {
    /**
     * 序号
     */
    private Integer id;

    /**
     * 产品型号
     */
    private String productModel;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 申请说明
     */
    private String applyRemark;

    /**
     * 申请时间
     */
    private Date applyTime;

    /**
     * 
     */
    private String bu;

    /**
     * 
     */
    private String pdt;

    /**
     * 产品类型
     */
    private String productType;

    /**
     * 平台
     */
    private String platform;

    /**
     * 目录价格
     */
    private BigDecimal catalogPrice;

    /**
     * 内部客户
     */
    private String inCustomer;

    /**
     * 生效时间
     */
    private Date effectTime;

    /**
     * 失效时间
     */
    private Date deadTime;

    /**
     * 更新时间
     */
    private Date modifyTime;

    /**
     * 审批状态
     */
    private String approvalStatus;

    /**
     * 审批说明
     */
    private String approvalRemark;

    /**
     * 审批人
     */
    private String approver;

    /**
     * 申请人
     */
    private String proposer;

    /**
     * 创建时间
     */
    private Date createTime;
}