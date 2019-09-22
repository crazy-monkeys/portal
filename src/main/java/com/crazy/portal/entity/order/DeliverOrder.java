package com.crazy.portal.entity.order;

import java.util.Date;

/**
 * 
 * @author weiying
 * @date   2019-09-22 22:40::15
 */
public class DeliverOrder {
    /**
     * 
     */
    private Integer deliverOrderId;

    /**
     * 
     */
    private String sapDeliverOrderNo;

    /**
     * 
     */
    private Integer salesOrderId;

    /**
     * 
     */
    private String sapOrderNo;

    /**
     * 
     */
    private String soldTo;

    /**
     * 
     */
    private String sendTo;

    /**
     * 
     */
    private Date deliverDate;

    /**
     * 
     */
    private String actualDeliveryDate;

    /**
     * 
     */
    private String shippingPoint;

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

    /**
     * 
     */
    private Integer approvalStatus;

    /**
     * 
     */
    private Date approvalDate;

    /**
     * 
     */
    private String approvalRemark;

    /**
     * 
     */
    private Integer approvalUser;

    public Integer getDeliverOrderId() {
        return deliverOrderId;
    }

    public void setDeliverOrderId(Integer deliverOrderId) {
        this.deliverOrderId = deliverOrderId;
    }

    public String getSapDeliverOrderNo() {
        return sapDeliverOrderNo;
    }

    public void setSapDeliverOrderNo(String sapDeliverOrderNo) {
        this.sapDeliverOrderNo = sapDeliverOrderNo == null ? null : sapDeliverOrderNo.trim();
    }

    public Integer getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(Integer salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public String getSapOrderNo() {
        return sapOrderNo;
    }

    public void setSapOrderNo(String sapOrderNo) {
        this.sapOrderNo = sapOrderNo == null ? null : sapOrderNo.trim();
    }

    public String getSoldTo() {
        return soldTo;
    }

    public void setSoldTo(String soldTo) {
        this.soldTo = soldTo == null ? null : soldTo.trim();
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo == null ? null : sendTo.trim();
    }

    public Date getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(Date deliverDate) {
        this.deliverDate = deliverDate;
    }

    public String getActualDeliveryDate() {
        return actualDeliveryDate;
    }

    public void setActualDeliveryDate(String actualDeliveryDate) {
        this.actualDeliveryDate = actualDeliveryDate == null ? null : actualDeliveryDate.trim();
    }

    public String getShippingPoint() {
        return shippingPoint;
    }

    public void setShippingPoint(String shippingPoint) {
        this.shippingPoint = shippingPoint == null ? null : shippingPoint.trim();
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

    public Integer getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Integer approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getApprovalRemark() {
        return approvalRemark;
    }

    public void setApprovalRemark(String approvalRemark) {
        this.approvalRemark = approvalRemark == null ? null : approvalRemark.trim();
    }

    public Integer getApprovalUser() {
        return approvalUser;
    }

    public void setApprovalUser(Integer approvalUser) {
        this.approvalUser = approvalUser;
    }
}