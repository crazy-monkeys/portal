package com.crazy.portal.entity.cusotmer;

import java.util.Date;

/**
 * 
 * @author weiying
 * @date   2019-08-29 16:10::00
 */
public class CustSales {
    /**
     * 
     */
    private Integer salesId;

    /**
     * 
     */
    private Integer custId;

    /**
     * 
     */
    private String salesOrganize;

    /**
     * 
     */
    private String distributionChannel;

    /**
     * 
     */
    private String productGroup;

    /**
     * 
     */
    private String currency;

    /**
     * 
     */
    private String shippingConditions;

    /**
     * 
     */
    private String deliveryPlant;

    /**
     * 
     */
    private String maxPartialDelivery;

    /**
     * 
     */
    private String internalCustomers;

    /**
     * 
     */
    private String accountGroup;

    /**
     * 
     */
    private String custGroup;

    /**
     * 
     */
    private String taxClassification;

    /**
     * 
     */
    private Integer active;

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

    public Integer getSalesId() {
        return salesId;
    }

    public void setSalesId(Integer salesId) {
        this.salesId = salesId;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getSalesOrganize() {
        return salesOrganize;
    }

    public void setSalesOrganize(String salesOrganize) {
        this.salesOrganize = salesOrganize == null ? null : salesOrganize.trim();
    }

    public String getDistributionChannel() {
        return distributionChannel;
    }

    public void setDistributionChannel(String distributionChannel) {
        this.distributionChannel = distributionChannel == null ? null : distributionChannel.trim();
    }

    public String getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(String productGroup) {
        this.productGroup = productGroup == null ? null : productGroup.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getShippingConditions() {
        return shippingConditions;
    }

    public void setShippingConditions(String shippingConditions) {
        this.shippingConditions = shippingConditions == null ? null : shippingConditions.trim();
    }

    public String getDeliveryPlant() {
        return deliveryPlant;
    }

    public void setDeliveryPlant(String deliveryPlant) {
        this.deliveryPlant = deliveryPlant == null ? null : deliveryPlant.trim();
    }

    public String getMaxPartialDelivery() {
        return maxPartialDelivery;
    }

    public void setMaxPartialDelivery(String maxPartialDelivery) {
        this.maxPartialDelivery = maxPartialDelivery == null ? null : maxPartialDelivery.trim();
    }

    public String getInternalCustomers() {
        return internalCustomers;
    }

    public void setInternalCustomers(String internalCustomers) {
        this.internalCustomers = internalCustomers == null ? null : internalCustomers.trim();
    }

    public String getAccountGroup() {
        return accountGroup;
    }

    public void setAccountGroup(String accountGroup) {
        this.accountGroup = accountGroup == null ? null : accountGroup.trim();
    }

    public String getCustGroup() {
        return custGroup;
    }

    public void setCustGroup(String custGroup) {
        this.custGroup = custGroup == null ? null : custGroup.trim();
    }

    public String getTaxClassification() {
        return taxClassification;
    }

    public void setTaxClassification(String taxClassification) {
        this.taxClassification = taxClassification == null ? null : taxClassification.trim();
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}