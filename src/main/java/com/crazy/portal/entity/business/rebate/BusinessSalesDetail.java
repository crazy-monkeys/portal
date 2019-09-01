package com.crazy.portal.entity.business.rebate;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author Administrator
 * @date   2019-08-28 21:41::30
 */
@Data
public class BusinessSalesDetail {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer rebateId;

    /**
     * 代理简称
     */
    private String agencyShortName;

    /**
     * 代理全称
     */
    private String agencyName;

    /**
     * 客户简称
     */
    private String customerShortName;

    /**
     * 客户外部号
     */
    private String customerCode;

    /**
     * 客户类别
     */
    private String customerType;

    /**
     * 阿米巴队长
     */
    private String amebaHeader;

    /**
     * 阿米巴部门
     */
    private String amebaDepartment;

    /**
     * BU
     */
    private String bu;

    /**
     * 产品类型
     */
    private String product;

    /**
     * 出货类型
     */
    private String shipmentType;

    /**
     * 数量
     */
    private Integer qty;

    /**
     * 销售价格
     */
    private BigDecimal salesPrice;

    /**
     * po价格
     */
    private BigDecimal poPrice;

    /**
     * 实际价格
     */
    private BigDecimal actualPrice;

    /**
     * rebate金额
     */
    private BigDecimal rebateAmount;

    /**
     * 核算年月
     */
    private String accountYearMonth;

    /**
     * 出货年月
     */
    private String shipmentYearMonth;

    /**
     * 出货日期
     */
    private String shipmentDate;

    /**
     * 订单月份
     */
    private String orderMonth;

    /**
     * 价格策略ID
     */
    private String priceRoleId;

    /**
     * 价格策略序号
     */
    private String priceStrategyNumber;

    /**
     * 出货公司
     */
    private String shipmentCompany;

    /**
     * rebate类型
     */
    private String rebateType;

    /**
     * 平台
     */
    private String class3;

    @JsonIgnore
    @JSONField(serialize = false)
    private Integer active;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer createId;
    @JsonIgnore
    @JSONField(serialize = false)
    private Date createTime;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer updateId;
    @JsonIgnore
    @JSONField(serialize = false)
    private Date updateTime;

}