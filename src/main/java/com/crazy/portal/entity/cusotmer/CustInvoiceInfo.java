package com.crazy.portal.entity.cusotmer;

import lombok.Data;

import java.util.Date;

/**
 * 
 * @author weiying
 * @date   2019-08-20 16:29::12
 */
@Data
public class CustInvoiceInfo {
    /**
     * 
     */
    private Integer invoiceId;

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
    private String purchasingUnit;

    /**
     * 
     */
    private String shippingAddress;

    /**
     * 
     */
    private String shippingMobile;

    /**
     * 
     */
    private String taxpayerRegistrationNumber;

    /**
     * 
     */
    private String currency;

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