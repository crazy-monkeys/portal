package com.crazy.portal.entity.cusotmer;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品信息
 * @author weiying
 * @date   2019-08-29 16:10::00
 */
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
    private BigDecimal pNumber;

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product == null ? null : product.trim();
    }

    public String getpMonth() {
        return pMonth;
    }

    public void setpMonth(String pMonth) {
        this.pMonth = pMonth == null ? null : pMonth.trim();
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Integer getInsertUser() {
        return insertUser;
    }

    public void setInsertUser(Integer insertUser) {
        this.insertUser = insertUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public BigDecimal getpNumber() {
        return pNumber;
    }

    public void setpNumber(BigDecimal pNumber) {
        this.pNumber = pNumber;
    }
}