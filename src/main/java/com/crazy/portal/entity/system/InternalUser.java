package com.crazy.portal.entity.system;

import lombok.Data;

import java.util.Date;

/**
 * 
 * @author weiying
 * @date   2019-08-26 16:29::00
 */
@Data
public class InternalUser {
    /**
     * 
     */
    private Integer inUserId;

    /**
     * 
     */
    private String userNo;

    /**
     * 
     */
    private String userName;

    /**
     * 
     */
    private String userMail;

    /**
     * 
     */
    private String userMobile;

    /**
     * 
     */
    private String userDepartmentCode;

    /**
     * 
     */
    private String userDepartmentName;

    /**
     * 
     */
    private String userPositionCode;

    /**
     * 
     */
    private String userPositionName;

    /**
     * 
     */
    private Date insertTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     */
    private Integer active;

    private User user;

    private OrganizationalStructure organizationalStructure;
}