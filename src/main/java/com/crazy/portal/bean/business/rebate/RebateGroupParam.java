package com.crazy.portal.bean.business.rebate;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Rebate分组参数
 */
@Getter
@Setter
public class RebateGroupParam {

    /**
     * 代理商简称
     */
    private String agencyShortName;

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

}
