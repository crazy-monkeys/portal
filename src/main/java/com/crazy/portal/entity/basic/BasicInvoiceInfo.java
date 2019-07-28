package com.crazy.portal.entity.basic;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

/**
 * 开票信息
 */
@Data
public class BasicInvoiceInfo implements BaseEntity{
    private Integer id;

    private Integer custId;

    /**
     * 购票单位
     */
    private String purchasingUnit;

    /**
     * 收货地址
     */
    private String shippingAddress;

    /**
     * 收货手机
     */
    private String shippingMobile;

    /**
     * 纳税人登记号
     */
    private String taxpayerRegistrationNumber;

    /**
     * 币种
     */
    private String currency;

    @JsonIgnore
    @JSONField(serialize = false)
    private Integer createUser;
    @JsonIgnore
    @JSONField(serialize = false)
    private Date createTime;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer updateUser;
    @JsonIgnore
    @JSONField(serialize = false)
    private Date updateTime;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer active;
}