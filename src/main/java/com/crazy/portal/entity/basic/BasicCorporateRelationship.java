package com.crazy.portal.entity.basic;

import com.alibaba.fastjson.annotation.JSONField;
import com.crazy.portal.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

/**
 * 公司关系
 */
@Data
public class BasicCorporateRelationship implements BaseEntity {
    private Integer id;

    /**
     * 用户id
     */
    private Integer custId;

    /**
     *关系客户id
     */
    private Integer corporateId;

    /**
     * 名称
     */
    private String corporateName;

    /**
     * 关系类型 关联客户，关联内部客户，关联公司
     */
    private String corporateType;
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