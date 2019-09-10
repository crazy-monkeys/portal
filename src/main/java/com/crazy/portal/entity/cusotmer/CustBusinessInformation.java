package com.crazy.portal.entity.cusotmer;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 业务介绍 年度更新(dealer)
 * @author weiying
 * @date   2019-08-29 16:10::00
 */
public class CustBusinessInformation {
    /**
     * 
     */
    private Integer busInfoId;

    /**
     * 
     */
    private Integer custId;

    /**
     * 
     */
    private String businessYear;

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
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date insertTime;

    /**
     * 
     */
    private Integer active;

    public Integer getBusInfoId() {
        return busInfoId;
    }

    public void setBusInfoId(Integer busInfoId) {
        this.busInfoId = busInfoId;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getBusinessYear() {
        return businessYear;
    }

    public void setBusinessYear(String businessYear) {
        this.businessYear = businessYear == null ? null : businessYear.trim();
    }

    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine == null ? null : productLine.trim();
    }

    public BigDecimal getRevenuePlOne() {
        return revenuePlOne;
    }

    public void setRevenuePlOne(BigDecimal revenuePlOne) {
        this.revenuePlOne = revenuePlOne;
    }

    public BigDecimal getRevenuePlTwo() {
        return revenuePlTwo;
    }

    public void setRevenuePlTwo(BigDecimal revenuePlTwo) {
        this.revenuePlTwo = revenuePlTwo;
    }

    public BigDecimal getRevenuePlThree() {
        return revenuePlThree;
    }

    public void setRevenuePlThree(BigDecimal revenuePlThree) {
        this.revenuePlThree = revenuePlThree;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }
}