package com.crazy.portal.entity.basic;

import com.alibaba.fastjson.annotation.JSONField;
import com.crazy.portal.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

/**
 * 层次结构
 */
@Data
public class BasicCorporateStructure implements BaseEntity {
    private Integer id;

    /**
     * 客户id
     */
    private Integer custId;

    /**
     * 名称
     */
    private String corporateStructureName;

    /**
     * 地址
     */
    private String corporateStructureAddress;

    /**
     * 主要联系人
     */
    private String isDefaultContact;
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