package com.crazy.portal.entity.business.rebate;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 接口API OBJECT
 * @author Shawn
 * @date   2019-08-28 21:40::50
 */
@Data
public class BusinessPriceRoleAO {
    /**
     * ID
     */
    @JsonProperty(value = "id")
    private Integer id;

    /**
     * 客户外部号
     */
    @JsonProperty(value = "customer_code")
    private String customerCode;

    /**
     * 客户全称
     */
    @JsonProperty(value = "customer_name")
    private String customerName;

    /**
     * 客户内部号
     */
    @JsonProperty(value = "customer_incode")
    private String customerIncode;

    /**
     * 客户简称
     */
    @JsonProperty(value = "customer_short_name")
    private String customerShortName;

    /**
     * 产品型号
     */
    @JsonProperty(value = "product")
    private String product;

    /**
     * 有效时间开始
     */
    @JsonProperty(value = "start_date")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date startDate;

    /**
     * 有效结束时间
     */
    @JsonProperty(value = "end_date")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    /**
     * 实际价格
     */
    @JsonProperty(value = "price")
    private BigDecimal price;

    /**
     * 销售下限
     */
    @JsonProperty(value = "sales_limit_lower")
    private BigDecimal salesLimitLower;

    /**
     * 销售上限
     */
    @JsonProperty(value = "sales_limit_upper")
    private BigDecimal salesLimitUpper;

    /**
     * 核算类型
     */
    @JsonProperty(value = "calculate_type")
    private String calculateType;

    /**
     * rebate执行方式
     */
    @JsonProperty(value = "rebate_excute_mode")
    private String rebateExcuteMode;

    /**
     * 价格申请人
     */
    @JsonProperty(value = "price_proposer")
    private String priceProposer;

    /**
     * 发货类型
     */
    @JsonProperty(value = "shipment_type")
    private String shipmentType;

    /**
     * 关联公司外部编号
     */
    @JsonProperty(value = "related_customer_code")
    private String relatedCustomerCode;

    /**
     * 关联公司全称
     */
    @JsonProperty(value = "related_customer_name")
    private String relatedCustomerName;

    /**
     * 关联产品
     */
    @JsonProperty(value = "related_product")
    private String relatedProduct;

    /**
     * 价格策略序号（对方暂时没提供）
     */
    @JsonProperty(value = "price_strategy_number")
    private String priceStrategyNumber;

    /**
     * 创建时间
     */
    @JsonProperty(value = "create_time")
    private String createTime;


}