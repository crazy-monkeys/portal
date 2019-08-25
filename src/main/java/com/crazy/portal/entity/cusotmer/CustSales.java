package com.crazy.portal.entity.cusotmer;

import lombok.Data;

import java.util.Date;

/**
 * 
 * @author weiying
 * @date   2019-08-20 16:29::12
 */
@Data
public class CustSales {
    /**
     * 
     */
    private Integer salesId;

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
    private String salesOrganize;

    /**
     * 
     */
    private String distributionChannel;

    /**
     * 
     */
    private String productGroup;

    /**
     * 
     */
    private String currency;

    /**
     * 
     */
    private String shippingConditions;

    /**
     * 
     */
    private String deliveryPlant;

    /**
     * 
     */
    private String maxPartialDelivery;

    /**
     * 
     */
    private String internalCustomers;

    /**
     * 
     */
    private String accountGroup;

    /**
     * 
     */
    private String custGroup;

    /**
     * 
     */
    private String taxClassification;

    /**
     * 
     */
    private Integer active;

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
}