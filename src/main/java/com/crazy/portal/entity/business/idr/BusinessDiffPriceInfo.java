package com.crazy.portal.entity.business.idr;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BusinessDiffPriceInfo implements IdrBaseEntity{
    private Integer id;

    private Integer idrInfoId;
    /**
     * 备注
     */
    private String remark;
    /**
     * 差价金额
     */
    private BigDecimal differenceAmount;

    /**
     * 代理费率
     */
    private String agencyRate;

    /**
     * 代理提货单价
     */
    private BigDecimal agentPrice;

    /**
     * 客户提货单价
     */
    private BigDecimal customerPrice;

    /**
     * 数量
     */
    private Integer num;

    /**
     *
     */
    private String shipmentDate;

    /**
     * 产品型号
     */
    private String productModel;

    /**
     * 平台
     */
    private String platfom;

    /**
     * 产品类型
     */
    private String productType;

    /**
     *
     */
    private String pdt;

    /**
     *
     */
    private String bu;

    /**
     * 客户属性
     */
    private String customerAttribute;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 发货公司
     */
    private String shipmentCompany;

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