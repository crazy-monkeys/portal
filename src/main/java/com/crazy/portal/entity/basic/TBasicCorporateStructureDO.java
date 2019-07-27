package com.crazy.portal.entity.basic;

import lombok.Data;

import java.util.Date;

/**
 * 层次结构
 */
@Data
public class TBasicCorporateStructureDO implements BaseEntity{
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

    private Integer createUser;

    private Date createTime;

    private Integer updateUser;

    private Date updateTime;

    private Integer active;
}