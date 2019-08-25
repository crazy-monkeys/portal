package com.crazy.portal.entity.cusotmer;

import lombok.Data;

import java.util.Date;

/**
 * 客户团队
 * @author weiying
 * @date   2019-08-20 13:26::47
 */
@Data
public class CustomerAccountTeam {
    /**
     * 
     */
    private Integer teamId;

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
    private String roleType;

    /**
     * 
     */
    private String accountName;

    /**
     * 
     */
    private String accountMobile;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Integer createUser;

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