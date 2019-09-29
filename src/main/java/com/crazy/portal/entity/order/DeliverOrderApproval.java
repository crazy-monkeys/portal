package com.crazy.portal.entity.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;
import com.crazy.portal.bean.order.DeliveryOrderLineVO;
import com.crazy.portal.entity.order.DeliverOrderLine;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author weiying
 * @date   2019-09-25 00:43::40
 */
public class DeliverOrderApproval {
    /**
     * 
     */
    private Integer deliverOrderId;

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
    @JSONField(format="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date deliverDate;

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
    @JSONField(format="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
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
    @JSONField(format="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date approvalDate;

    /**
     * 
     */
    private String approvalRemark;

    /**
     * 
     */
    private Integer approvalUser;

    /**
     * 
     */
    private String approvalType;

    /**
     * 
     */
    private JSONArray deliveryOrderLine;

    public Integer getDeliverOrderId() {
        return deliverOrderId;
    }

    public void setDeliverOrderId(Integer deliverOrderId) {
        this.deliverOrderId = deliverOrderId;
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
        this.sapOrderNo = sapOrderNo;
    }

    public String getSoldTo() {
        return soldTo;
    }

    public void setSoldTo(String soldTo) {
        this.soldTo = soldTo;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    public Date getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(Date deliverDate) {
        this.deliverDate = deliverDate;
    }

    public String getShippingPoint() {
        return shippingPoint;
    }

    public void setShippingPoint(String shippingPoint) {
        this.shippingPoint = shippingPoint;
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
        this.approvalRemark = approvalRemark;
    }

    public Integer getApprovalUser() {
        return approvalUser;
    }

    public void setApprovalUser(Integer approvalUser) {
        this.approvalUser = approvalUser;
    }

    public String getApprovalType() {
        return approvalType;
    }

    public void setApprovalType(String approvalType) {
        this.approvalType = approvalType;
    }

    public JSONArray getDeliveryOrderLine() {
        return deliveryOrderLine;
    }

    public void setDeliveryOrderLine(JSONArray deliveryOrderLine) {
        this.deliveryOrderLine = deliveryOrderLine;
    }

    public JSONArray setSerializelDeliveryOrderLine(List<DeliverOrderLine> deliveryOrderLine) {
        return JSON.parseArray(JSON.toJSONString(deliveryOrderLine));
    }

    public List<DeliverOrderLine> getSerializelDeliveryOrderLine() {
        return JSON.parseArray(JSON.toJSONString(deliveryOrderLine),DeliverOrderLine.class);
    }
}