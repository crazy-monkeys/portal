package com.crazy.portal.entity.order;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author weiying
 * @date   2019-09-22 23:29::03
 */
public class OrderInvoice {
    /**
     * 
     */
    private Integer invoiceId;

    /**
     * 
     */
    private String sapInvoiceId;

    /**
     * 
     */
    private String slodTo;

    /**
     * 
     */
    private String sendTo;

    /**
     * 
     */
    private String accDoc;

    /**
     * 
     */
    private String accYear;

    /**
     * 
     */
    private String reInvoiceId;

    /**
     * 
     */
    private String reFlag;

    /**
     * 
     */
    private BigDecimal netAmount;

    /**
     * 
     */
    private BigDecimal taxAmount;

    /**
     * 
     */
    private String currency;

    /**
     * 
     */
    private String createdOn;

    /**
     * 
     */
    private String changedOn;

    /**
     * 
     */
    private String invoiceitemId;

    /**
     * 
     */
    private String sapOrderId;

    /**
     * 
     */
    private String sapOrderitemNo;

    /**
     * 
     */
    private String sapDeliveryId;

    /**
     * 
     */
    private String deliveryItemNo;

    /**
     * 
     */
    private String productId;

    /**
     * 
     */
    private String productText;

    /**
     * 
     */
    private Integer billQuantity;

    /**
     * 
     */
    private BigDecimal itemNetAmount;

    /**
     * 
     */
    private BigDecimal itemTaxAmount;

    /**
     * 
     */
    private Date insertTime;

    /**
     * 
     */
    private Integer active;

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getSapInvoiceId() {
        return sapInvoiceId;
    }

    public void setSapInvoiceId(String sapInvoiceId) {
        this.sapInvoiceId = sapInvoiceId == null ? null : sapInvoiceId.trim();
    }

    public String getSlodTo() {
        return slodTo;
    }

    public void setSlodTo(String slodTo) {
        this.slodTo = slodTo == null ? null : slodTo.trim();
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo == null ? null : sendTo.trim();
    }

    public String getAccDoc() {
        return accDoc;
    }

    public void setAccDoc(String accDoc) {
        this.accDoc = accDoc == null ? null : accDoc.trim();
    }

    public String getAccYear() {
        return accYear;
    }

    public void setAccYear(String accYear) {
        this.accYear = accYear == null ? null : accYear.trim();
    }

    public String getReInvoiceId() {
        return reInvoiceId;
    }

    public void setReInvoiceId(String reInvoiceId) {
        this.reInvoiceId = reInvoiceId == null ? null : reInvoiceId.trim();
    }

    public String getReFlag() {
        return reFlag;
    }

    public void setReFlag(String reFlag) {
        this.reFlag = reFlag == null ? null : reFlag.trim();
    }

    public BigDecimal getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(BigDecimal netAmount) {
        this.netAmount = netAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn == null ? null : createdOn.trim();
    }

    public String getChangedOn() {
        return changedOn;
    }

    public void setChangedOn(String changedOn) {
        this.changedOn = changedOn == null ? null : changedOn.trim();
    }

    public String getInvoiceitemId() {
        return invoiceitemId;
    }

    public void setInvoiceitemId(String invoiceitemId) {
        this.invoiceitemId = invoiceitemId == null ? null : invoiceitemId.trim();
    }

    public String getSapOrderId() {
        return sapOrderId;
    }

    public void setSapOrderId(String sapOrderId) {
        this.sapOrderId = sapOrderId == null ? null : sapOrderId.trim();
    }

    public String getSapOrderitemNo() {
        return sapOrderitemNo;
    }

    public void setSapOrderitemNo(String sapOrderitemNo) {
        this.sapOrderitemNo = sapOrderitemNo == null ? null : sapOrderitemNo.trim();
    }

    public String getSapDeliveryId() {
        return sapDeliveryId;
    }

    public void setSapDeliveryId(String sapDeliveryId) {
        this.sapDeliveryId = sapDeliveryId == null ? null : sapDeliveryId.trim();
    }

    public String getDeliveryItemNo() {
        return deliveryItemNo;
    }

    public void setDeliveryItemNo(String deliveryItemNo) {
        this.deliveryItemNo = deliveryItemNo == null ? null : deliveryItemNo.trim();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getProductText() {
        return productText;
    }

    public void setProductText(String productText) {
        this.productText = productText == null ? null : productText.trim();
    }

    public Integer getBillQuantity() {
        return billQuantity;
    }

    public void setBillQuantity(Integer billQuantity) {
        this.billQuantity = billQuantity;
    }

    public BigDecimal getItemNetAmount() {
        return itemNetAmount;
    }

    public void setItemNetAmount(BigDecimal itemNetAmount) {
        this.itemNetAmount = itemNetAmount;
    }

    public BigDecimal getItemTaxAmount() {
        return itemTaxAmount;
    }

    public void setItemTaxAmount(BigDecimal itemTaxAmount) {
        this.itemTaxAmount = itemTaxAmount;
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