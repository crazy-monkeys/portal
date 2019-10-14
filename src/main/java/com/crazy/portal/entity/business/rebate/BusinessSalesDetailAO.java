package com.crazy.portal.entity.business.rebate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 接口API OBJECT
 * @author Shawn
 * @date   2019-08-28 21:40::50
 */
@Data
public class BusinessSalesDetailAO {
    /**
     * ID
     */
    @JsonProperty(value = "sales_report_id")
    private Integer id;

    /**
     * 代理简称
     */
    @JsonProperty(value = "agency_short_name")
    private String agencyShortName;

    /**
     * 代理全称
     */
    @JsonProperty(value = "agency_name")
    private String agencyName;

    /**
     * 客户简称
     */
    @JsonProperty(value = "customer_short_name")
    private String customerShortName;

    /**
     * 客户外部号
     */
    @JsonProperty(value = "customer_code")
    private String customerCode;

    /**
     * 客户类别
     */
    @JsonProperty(value = "customer_type")
    private String customerType;

    /**
     * 阿米巴队长
     */
    @JsonProperty(value = "ameba_header")
    private String amebaHeader;

    /**
     * 阿米巴部门
     */
    @JsonProperty(value = "ameba_department")
    private String amebaDepartment;

    /**
     * BU
     */
    @JsonProperty(value = "bu")
    private String bu;

    /**
     * pdt（对方暂未提供）
     */
    @JsonProperty(value = "pdt")
    private String pdt;
    /**
     * 产品类型
     */
    @JsonProperty(value = "product")
    private String product;

    /**
     * 出货类型
     */
    @JsonProperty(value = "shipment_type")
    private String shipmentType;

    /**
     * 数量
     */
    @JsonProperty(value = "qty")
    private Integer qty;

    /**
     * 销售价格
     */
    @JsonProperty(value = "sales_price")
    private BigDecimal salesPrice;

    /**
     * po价格
     */
    @JsonProperty(value = "po_price")
    private BigDecimal poPrice;

    /**
     * 实际价格
     */
    @JsonProperty(value = "actual_price")
    private BigDecimal actualPrice;

    /**
     * rebate金额
     */
    @JsonProperty(value = "rebate_amount")
    private BigDecimal rebateAmount;

    /**
     * 核算年月
     */
    @JsonProperty(value = "year_month")
    private String accountYearMonth;

    /**
     * 出货年月
     */
    @JsonProperty(value = "shipment_year_month")
    private String shipmentYearMonth;

    /**
     * 出货日期
     */
    @JsonProperty(value = "shipment_date")
    private String shipmentDate;

    /**
     * 订单月份
     */
    @JsonProperty(value = "order_month")
    private String orderMonth;

    /**
     * 价格策略ID
     */
    @JsonProperty(value = "price_role_id")
    private String priceRoleId;

    /**
     * 价格策略序号（对方暂时没提供）
     */
    @JsonProperty(value = "price_strategy_number")
    private String priceStrategyNumber;

    /**
     * 出货公司
     */
    @JsonProperty(value = "shipment_company")
    private String shipmentCompany;

    /**
     * rebate类型
     */
    @JsonProperty(value = "rebate_type")
    private String rebateType;

    /**
     * 平台
     */
    @JsonProperty(value = "class3")
    private String class3;
    /**
     * 销售名称（对方暂未提供）
     */
    @JsonProperty(value = "sales_name")
    private String salesName;


}