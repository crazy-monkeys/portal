package com.crazy.portal.entity.business.rebate;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author Administrator
 * @date   2019-08-28 21:40::50
 */
@Data
public class BusinessPriceRole {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer rebateId;

    /**
     * 客户外部号
     */
    private String customerCode;

    /**
     * 客户全称
     */
    private String customerName;

    /**
     * 客户内部号
     */
    private String customerIncode;

    /**
     * 客户简称
     */
    private String customerShortName;

    /**
     * 产品型号
     */
    private String product;

    /**
     * 有效时间开始
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date startDate;

    /**
     * 有效结束时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    /**
     * 实际价格
     */
    private BigDecimal price;

    /**
     * 销售下限
     */
    private BigDecimal salesLimitLower;

    /**
     * 销售上限
     */
    private BigDecimal salesLimitUpper;

    /**
     * 核算类型
     */
    private String calculateType;

    /**
     * rebate执行方式
     */
    private String rebateExcuteMode;

    /**
     * 价格申请人
     */
    private String priceProposer;

    /**
     * 发货类型
     */
    private String shipmentType;

    /**
     * 关联公司外部编号
     */
    private String relatedCustomerCode;

    /**
     * 关联公司全称
     */
    private String relatedCustomerName;

    /**
     * 关联产品
     */
    private String relatedProduct;

    /**
     * 价格策略序号
     */
    private String priceStrategyNumber;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer active;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer createId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer updateId;

    /**
     * 更新时间
     */
    @JsonIgnore
    @JSONField(serialize = false)
    private Date updateTime;

    /**
     * 插入时间
     */
    @JsonIgnore
    @JSONField(serialize = false)
    private Date insertTime;


}