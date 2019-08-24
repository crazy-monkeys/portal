package com.crazy.portal.entity.product;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author weiying
 * @date   2019-08-24 16:13::56
 */
public class ProductInfoMpq {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String product;

    /**
     * 
     */
    private String mpqFormula;

    /**
     * 
     */
    private BigDecimal mpq;

    /**
     * 
     */
    private Date insertTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     */
    private Integer active;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product == null ? null : product.trim();
    }

    public String getMpqFormula() {
        return mpqFormula;
    }

    public void setMpqFormula(String mpqFormula) {
        this.mpqFormula = mpqFormula == null ? null : mpqFormula.trim();
    }

    public BigDecimal getMpq() {
        return mpq;
    }

    public void setMpq(BigDecimal mpq) {
        this.mpq = mpq;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }
}