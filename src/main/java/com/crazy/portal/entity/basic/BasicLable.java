package com.crazy.portal.entity.basic;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

/**
 * 分级分类信息
 */
@Data
public class BasicLable {
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