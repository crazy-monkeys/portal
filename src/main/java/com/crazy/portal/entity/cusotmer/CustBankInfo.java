package com.crazy.portal.entity.cusotmer;

import lombok.Data;

import java.util.Date;

/**
 * 
 * @author weiying
 * @date   2019-08-20 16:29::12
 */
@Data
public class CustBankInfo {
    /**
     * 
     */
    private Integer bankId;

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
    private String bankName;

    /**
     * 
     */
    private String bankAccount;

    /**
     * 
     */
    private String bankBic;

    /**
     * 
     */
    private String bankCountry;

    /**
     * 
     */
    private String bankProvince;

    /**
     * 
     */
    private String bankCity;

    /**
     * 
     */
    private String bankDistrict;

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

    /**
     * 
     */
    private String bankDetailInfo;
}