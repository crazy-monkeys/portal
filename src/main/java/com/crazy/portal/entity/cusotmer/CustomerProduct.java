package com.crazy.portal.entity.cusotmer;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品信息
 * @author weiying
 * @date   2019-08-29 16:10::00
 */
@Data
public class CustomerProduct {
    /**
     * 
     */
    private Integer proId;

    /**
     * 
     */
    private Integer custId;

    /**
     * 
     */
    private String productId;

    /**
     * 
     */
    private String product;

    /**
     * 
     */
    private String pMonth;

    /**
     * 
     */
    private Date insertTime;

    /**
     * 
     */
    private Integer insertUser;

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

    /**
     * 
     */
    private BigDecimal pNumberOne;
    private BigDecimal pNumberTwo;
    private BigDecimal pNumberThree;
    private BigDecimal pNumberFour;
    private BigDecimal pNumberFive;
    private BigDecimal pNumberSix;
}