package com.crazy.portal.entity.cusotmer;

import lombok.Data;

import java.util.Date;

/**
 * 
 * @author weiying
 * @date   2019-08-20 16:29::12
 */
@Data
public class CustCorporateRelationship {
    /**
     * 
     */
    private Integer shipId;

    /**
     * 
     */
    private Integer reportId;

    /**
     * 
     */
    private Integer custId;

    /**
     * 
     */
    private Integer corporateId;

    /**
     * 
     */
    private String corporateName;

    /**
     * 
     */
    private String corporateType;

    /**
     * 
     */
    private Integer createUser;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Integer updateUser;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     */
    private Integer active;
}