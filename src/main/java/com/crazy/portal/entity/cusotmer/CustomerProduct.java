package com.crazy.portal.entity.cusotmer;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品信息
 * @author weiying
 * @date   2019-08-20 13:26::47
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
    private Integer reportId;

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

    private BigDecimal pNumber;

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
}