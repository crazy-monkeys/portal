package com.crazy.portal.entity.business.idr;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class BusinessInsuranceInfo implements IdrBaseEntity{
    private Integer id;

    private Integer idrInfoId;


    /**
     * 发货公司
     */
    private String shipmentCompany;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 收货时间
     */
    private String receiveGoodsDate;

    /**
     * 调价时间
     */
    private String adjustDate;

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
     * 产品型号
     */
    private String productModel;

    /**
     * 库存数量
     */
    private String num;

    /**
     * 币种
     */
    private String currency;

    /**
     * 客户属性
     */
    private String customerAttribute;

    /**
     * 代理费率
     */
    private String agencyRate;

    /**
     * 库存价格
     */
    private String price;

    /**
     * 新价格
     */
    private String newPrice;

    /**
     * 保价金额
     */
    private String insuranceAmount;

    /**
     * 调整时间
     */
    private String modifyDate;

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