package com.crazy.portal.entity.business.idr;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BusinessReturnsInfo implements IdrBaseEntity{
    private Integer id;

    private Integer idrInfoId;
    /**
     * 发货公司
     */
    private String shipmentCompany;

    /**
     * 代理商
     */
    private String agencyName;

    /**
     * 提货日期
     */
    private String takeGoodsDate;

    /**
     * 退货产品线
     */
    private String returnProductLine;

    /**
     * 退货BU
     */
    private String returnBu;

    /**
     * 退货PDT
     */
    private String returnPdt;

    /**
     * 退货平台
     */
    private String returnPlatform;

    /**
     * 退货产品型号
     */
    private String returnProductModel;

    /**
     * 退货数量
     */
    private Integer returnNum;

    /**
     * 退货币种
     */
    private String returnCurrency;

    /**
     * 退货价格
     */
    private BigDecimal returnPrice;

    /**
     * 客户属性
     */
    private String returnCustomerAttribute;

    /**
     * 代理费率
     */
    private String returnAgencyRate;

    /**
     * 退货金额
     */
    private BigDecimal returnAmount;

    /**
     * 换货产品线
     */
    private String exchangeProductLine;

    /**
     * 换货BU
     */
    private String exchangeBu;

    /**
     * 换货PDT
     */
    private String exchangePdt;

    /**
     * 换货平台
     */
    private String exchangePlatform;

    /**
     * 换货产品型号
     */
    private String exchangeProductModel;

    /**
     * 换货数量
     */
    private Integer exchangeNum;

    /**
     * 换货币种
     */
    private String exchangeCurrency;

    /**
     * 换货价格
     */
    private BigDecimal exchangePrice;

    /**
     * 换货金额
     */
    private BigDecimal exchangeAmount;

    /**
     * 换货日期
     */
    private String exchangeDate;

    /**
     * 备注
     */
    private String remark;
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