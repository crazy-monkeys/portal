package com.crazy.portal.entity.webservice;

import lombok.Data;

import java.util.Date;

/**
 * 
 * @author xsh12148
 * @date   2019-08-24 18:01::10
 */
@Data
public class ApiUsers {
    /**
     * ID
     */
    private Integer apiUserId;

    /**
     * 接口用户名
     */
    private String apiUsersName;

    /**
     * 
     */
    private String apiSecret;

    /**
     * 
     */
    private Integer active;

    /**
     * 
     */
    private Integer createId;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Integer updateId;

    /**
     * 
     */
    private Date updateTime;

}