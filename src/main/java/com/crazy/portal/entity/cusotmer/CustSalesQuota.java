package com.crazy.portal.entity.cusotmer;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author weiying
 * @date   2019-09-07 14:54::59
 */
public class CustSalesQuota {
    /**
     * 
     */
    private Integer quoatId;

    /**
     * 
     */
    private Integer custId;

    /**
     * 
     */
    private String salesYear;

    /**
     * 
     */
    private String currency;

    /**
     * 
     */
    private String unit;

    /**
     * 
     */
    private BigDecimal salesNumber;

    /**
     * 
     */
    private Integer active;

    /**
     * 
     */
    private Integer createUserId;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Integer updateUserId;

    /**
     * 
     */
    private Date updateTime;

    public Integer getQuoatId() {
        return quoatId;
    }

    public void setQuoatId(Integer quoatId) {
        this.quoatId = quoatId;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getSalesYear() {
        return salesYear;
    }

    public void setSalesYear(String salesYear) {
        this.salesYear = salesYear == null ? null : salesYear.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public BigDecimal getSalesNumber() {
        return salesNumber;
    }

    public void setSalesNumber(BigDecimal salesNumber) {
        this.salesNumber = salesNumber;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}