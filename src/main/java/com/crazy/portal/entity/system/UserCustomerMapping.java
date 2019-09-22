package com.crazy.portal.entity.system;

import lombok.Data;

import java.util.Date;

/**
 * 
 * @author weiying
 * @date   2019-08-26 22:57::08
 */
@Data
public class UserCustomerMapping {
    /**
     * 
     */
    private Integer mappingId;

    /**
     * 模块
     */
    private String mappingModel;

    /**
     * 营销运作部
     */
    private Integer cdUserId;

    /**
     * 代理商经营部
     */
    private Integer dealerUserId;

    /**
     * 负责的客户id
     */
    private String custId;

    /**
     * 
     */
    private Integer insertUserId;

    /**
     * 
     */
    private Date insertTime;

    /**
     * 
     */
    private Integer updateUserId;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     */
    private Integer active;
}