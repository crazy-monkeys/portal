package com.crazy.portal.entity.basic;

import lombok.Data;

import java.util.Date;

/**
 * 地址信息
 */
@Data
public class TBasicAddressDO {
    private Integer id;

    /**
     * 客户id
     */
    private Integer custId;

    /**
     * 是否主要地址 1-是 0-否
     */
    private Integer isDefault;

    /**
     * 地址类型 注册地址、办公地址
     */
    private Integer addressType;

    /**
     * 国家
     */
    private String country;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String district;

    /**
     * 详细地址信息
     */
    private String detailInfo;

    private Integer createUser;

    private Date createTime;

    private Integer updateUser;

    private Date updateTime;

    private Integer active;
}