package com.crazy.portal.entity.handover;

import java.util.Date;

public class DeliverDetail {
    private Integer id;

    private Date uploadTime;

    private String customerExternalNumber;

    private String customerFullName;

    private String sales;

    private String categoryOne;

    private String categoryTow;

    private String categoryThree;

    private String productModel;

    private Date deliveryDate;

    private Integer deliverNumber;

    private Long salePrice;

    private Long poPrice;

    private String margin;

    private String currency;

    private Integer customerOrderNumber;

    private Integer deliveryType;

    private String orderMonth;

    private String deliveryCompany;

    private String remark;

    private String errorMsg;

    private Integer recordId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getCustomerExternalNumber() {
        return customerExternalNumber;
    }

    public void setCustomerExternalNumber(String customerExternalNumber) {
        this.customerExternalNumber = customerExternalNumber == null ? null : customerExternalNumber.trim();
    }

    public String getCustomerFullName() {
        return customerFullName;
    }

    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName == null ? null : customerFullName.trim();
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales == null ? null : sales.trim();
    }

    public String getCategoryOne() {
        return categoryOne;
    }

    public void setCategoryOne(String categoryOne) {
        this.categoryOne = categoryOne == null ? null : categoryOne.trim();
    }

    public String getCategoryTow() {
        return categoryTow;
    }

    public void setCategoryTow(String categoryTow) {
        this.categoryTow = categoryTow == null ? null : categoryTow.trim();
    }

    public String getCategoryThree() {
        return categoryThree;
    }

    public void setCategoryThree(String categoryThree) {
        this.categoryThree = categoryThree == null ? null : categoryThree.trim();
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel == null ? null : productModel.trim();
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Integer getDeliverNumber() {
        return deliverNumber;
    }

    public void setDeliverNumber(Integer deliverNumber) {
        this.deliverNumber = deliverNumber;
    }

    public Long getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Long salePrice) {
        this.salePrice = salePrice;
    }

    public Long getPoPrice() {
        return poPrice;
    }

    public void setPoPrice(Long poPrice) {
        this.poPrice = poPrice;
    }

    public String getMargin() {
        return margin;
    }

    public void setMargin(String margin) {
        this.margin = margin == null ? null : margin.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public Integer getCustomerOrderNumber() {
        return customerOrderNumber;
    }

    public void setCustomerOrderNumber(Integer customerOrderNumber) {
        this.customerOrderNumber = customerOrderNumber;
    }

    public Integer getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(Integer deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getOrderMonth() {
        return orderMonth;
    }

    public void setOrderMonth(String orderMonth) {
        this.orderMonth = orderMonth == null ? null : orderMonth.trim();
    }

    public String getDeliveryCompany() {
        return deliveryCompany;
    }

    public void setDeliveryCompany(String deliveryCompany) {
        this.deliveryCompany = deliveryCompany == null ? null : deliveryCompany.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg == null ? null : errorMsg.trim();
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }
}