package com.crazy.portal.entity.price;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 目录价格表
 * @author Bill
 * @date   2019-08-18 15:36::19
 */
public class CatalogPrice {
    /**
     * 记录序号
     */
    private Integer id;

    /**
     * 状态
     */
    private String status;

    /**
     * 
     */
    private String bu;

    /**
     * 
     */
    private String pdt;

    /**
     * 产品类型
     */
    private String productType;

    /**
     * 平台
     */
    private String platform;

    /**
     * 产品型号
     */
    private String productModel;

    /**
     * 目录价格
     */
    private BigDecimal catalogPrice;

    /**
     * 内部客户
     */
    private String insideCustomer;

    /**
     * 生效时间
     */
    private Date effectiveTime;

    /**
     * 失效时间
     */
    private Date deadTime;

    /**
     * 
     */
    private Date modifyTime;

    /**
     * 
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getBu() {
        return bu;
    }

    public void setBu(String bu) {
        this.bu = bu == null ? null : bu.trim();
    }

    public String getPdt() {
        return pdt;
    }

    public void setPdt(String pdt) {
        this.pdt = pdt == null ? null : pdt.trim();
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform == null ? null : platform.trim();
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel == null ? null : productModel.trim();
    }

    public BigDecimal getCatalogPrice() {
        return catalogPrice;
    }

    public void setCatalogPrice(BigDecimal catalogPrice) {
        this.catalogPrice = catalogPrice;
    }

    public String getInsideCustomer() {
        return insideCustomer;
    }

    public void setInsideCustomer(String insideCustomer) {
        this.insideCustomer = insideCustomer == null ? null : insideCustomer.trim();
    }

    public Date getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public Date getDeadTime() {
        return deadTime;
    }

    public void setDeadTime(Date deadTime) {
        this.deadTime = deadTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}