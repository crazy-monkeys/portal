package com.crazy.portal.entity.cusotmer;

import lombok.Data;

import java.util.Date;

/**
 * 
 * @author weiying
 * @date   2019-08-20 13:26::47
 */
@Data
public class CustomerAddress {
    /**
     * 
     */
    private Integer addressId;

    /**
     * 
     */
    private Integer reportId;

    /**
     * 
     */
    private Integer custId;

    /**
     * A01-注册地址(Registration Address); A02-办公地址/通讯地址(Communication Address)
     */
    private String addressType;

    /**
     * 
     */
    private String country;

    /**
     * 
     */
    private String province;

    /**
     * 
     */
    private String city;

    /**
     * 
     */
    private String district;

    /**
     * 
     */
    private String addressDetail;

    /**
     * 
     */
    private Integer isDefault;

    /**
     * 
     */
    private String contact;

    /**
     * 
     */
    private String mobile;

    /**
     * 
     */
    private String eamil;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Integer crateUser;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     */
    private Integer updateUser;

    /**
     * 
     */
    private Integer active;
}