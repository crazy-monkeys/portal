package com.crazy.portal.entity.cusotmer;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 业务介绍 年度更新(dealer)
 * @author weiying
 * @date   2019-08-19 22:19::06
 */
@Data
public class BusinessInformation {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer custId;

    /**
     * 产品线
     */
    private String productLine;

    /**
     * 产品线营业额
     */
    private BigDecimal revenuePlOne;

    /**
     * 
     */
    private BigDecimal revenuePlTwo;

    /**
     * 
     */
    private BigDecimal revenuePlThree;

    /**
     * 
     */
    private Date insertTime;

    /**
     * 
     */
    private Integer active;

    /**
     * 
     */
    private String businessYear;
}