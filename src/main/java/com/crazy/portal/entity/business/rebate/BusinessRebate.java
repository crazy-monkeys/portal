package com.crazy.portal.entity.business.rebate;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class BusinessRebate {
    private Integer id;
    /**
     * 代理商全称
     */
    private String agencyName;

    /**
     * 客户简称
     */
    private String customerShortName;

    /**
     * 客户属性/类别
     */
    private String customerType;

    /**
     * 销售名称
     */
    private String salesName;

    /**
     * 阿米巴队长
     */
    private String amebaHeader;

    /**
     * 阿米巴部门/代表处
     */
    private String amebaDepartment;

    /**
     * 出货公司
     */
    private String shipmentCompany;

    /**
     * 核算年月
     */
    private String accountYearMonth;

    /**
     * 订单年月
     */
    private String orderMonth;

    /**
     * 出货年月
     */
    private String shipmentYearMonth;

    /**
     * BU
     */
    private String bu;

    /**
     * PDT
     */
    private String pdt;

    /**
     * 产品型号
     */
    private String product;

    /**
     * 数量
     */
    private Integer qty;

    /**
     * 销售价格
     */
    private BigDecimal salesPrice;

    /**
     * OLD PRICE
     */
    private BigDecimal poPrice;

    /**
     * 真实价格
     */
    private BigDecimal actualPrice;

    /**
     * 平台
     */
    private String platform;

    /**
     * rebate金额
     */
    private BigDecimal rebateAmount;
    /**
     * 释放金额
     */
    private BigDecimal releaseAmount;
    /**
     * 剩余释放金额
     */
    private BigDecimal surplusRebateAmount;
    /**
     * 状态 1-已提交 2-已结束
     */
    private Integer status;

    private String dealerCode;

    private String customerCode;
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


    List<BusinessSalesDetail> salesDetails;

    List<BusinessPriceRole> priceRoles;

    List<BusinessRebateItem> itemList;
}