package com.crazy.portal.entity.basic;

import lombok.Data;

import java.util.Date;

/**
 * 公司关系
 */
@Data
public class TBasicCorporateRelationshipDO implements BaseEntity{
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

    private Integer createUser;

    private Date createTime;

    private Integer updateUser;

    private Date updateTime;

    private Integer active;
}