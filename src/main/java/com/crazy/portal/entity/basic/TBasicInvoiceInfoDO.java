package com.crazy.portal.entity.basic;

import lombok.Data;

import java.util.Date;

/**
 * 开票信息
 */
@Data
public class TBasicInvoiceInfoDO implements BaseEntity{
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

    private Integer createUser;

    private Date createTime;

    private Integer updateUser;

    private Date updateTime;

    private Integer active;
}