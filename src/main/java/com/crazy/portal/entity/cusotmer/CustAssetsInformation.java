package com.crazy.portal.entity.cusotmer;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 资产信息 季度更新(dealer)
 * @author weiying
 * @date   2019-08-29 16:10::00
 */
public class CustAssetsInformation {
    /**
     * 
     */
    private Integer asseteInfoId;

    /**
     * 
     */
    private Integer custId;

    /**
     * 资产统计年
     */
    private String assetsYear;

    /**
     * 资产统计季度
     */
    private String assetsSeason;

    /**
     * 总资产
     */
    private BigDecimal assetsTotal;

    /**
     * 净资产
     */
    private BigDecimal assetsNet;

    /**
     * 营业额
     */
    private BigDecimal revenue;

    /**
     * 总人数
     */
    private Integer totalStaff;

    /**
     * 
     */
    private Date insertTime;

    /**
     * 
     */
    private Integer active;

    public Integer getAsseteInfoId() {
        return asseteInfoId;
    }

    public void setAsseteInfoId(Integer asseteInfoId) {
        this.asseteInfoId = asseteInfoId;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getAssetsYear() {
        return assetsYear;
    }

    public void setAssetsYear(String assetsYear) {
        this.assetsYear = assetsYear == null ? null : assetsYear.trim();
    }

    public String getAssetsSeason() {
        return assetsSeason;
    }

    public void setAssetsSeason(String assetsSeason) {
        this.assetsSeason = assetsSeason == null ? null : assetsSeason.trim();
    }

    public BigDecimal getAssetsTotal() {
        return assetsTotal;
    }

    public void setAssetsTotal(BigDecimal assetsTotal) {
        this.assetsTotal = assetsTotal;
    }

    public BigDecimal getAssetsNet() {
        return assetsNet;
    }

    public void setAssetsNet(BigDecimal assetsNet) {
        this.assetsNet = assetsNet;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }

    public Integer getTotalStaff() {
        return totalStaff;
    }

    public void setTotalStaff(Integer totalStaff) {
        this.totalStaff = totalStaff;
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