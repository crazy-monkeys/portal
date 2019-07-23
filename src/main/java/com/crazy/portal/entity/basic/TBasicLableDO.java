package com.crazy.portal.entity.basic;

import lombok.Data;

import java.util.Date;

/**
 * 分级分类信息
 */
@Data
public class TBasicLableDO {
    private Integer id;

    private Integer custId;

    /**
     * 纬度
     */
    private String lableDimension;

    /**
     * BU
     */
    private String lableBu;

    /**
     * PDT
     */
    private String lablePdt;

    /**
     * 产品型号
     */
    private String lableProductNumber;

    /**
     * 区域
     */
    private String lableRegion;

    /**
     * 性质
     */
    private String lableSalesModel;

    /**
     * 等级
     */
    private String lableLevel;

    /**
     * 开始时间
     */
    private Date lableStartTime;

    /**
     * 结束时间
     */
    private Date lableEndTime;

    private Integer createUser;

    private Date createTime;

    private Integer updateUser;

    private Date updateTime;

    private Integer active;
}