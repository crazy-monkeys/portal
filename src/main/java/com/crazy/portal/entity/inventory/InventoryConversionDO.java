package com.crazy.portal.entity.inventory;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 库存转换表
 * @author Bill
 * @date   2019-11-16 15:38::46
 */
public class InventoryConversionDO {
    /**
     * 
     */
    private Integer id;

    /**
     * 年月（版本数据Tag）
     */
    private String yearMonth;

    /**
     * 代理简称(内部客户)
     */
    private String agencyShortName;

    /**
     * BU
     */
    private String productLine;

    /**
     * PDT
     */
    private String subProductLine;

    /**
     * Product Type
     */
    private String class2;

    /**
     * Platform
     */
    private String class3;

    /**
     * 产品型号
     */
    private String product;

    /**
     * 客户属性
     */
    private String customerType;

    /**
     * 库存类别
     */
    private String inventoryType;

    /**
     * 库存单价
     */
    private BigDecimal inventoryPrice;

    /**
     * 发货公司
     */
    private String company;

    /**
     * 销售组织
     */
    private String salesOrg;

    /**
     * 期初库存数量
     */
    private Integer monthlyInitQty;

    /**
     * 代理提货时间
     */
    private String agencyPickUpDate;

    /**
     * 提货数量
     */
    private Integer agencyPickUpQty;

    /**
     * 出货数量
     */
    private Integer salesQty;

    /**
     * 期末库存数量
     */
    private Integer monthlyEndQty;

    /**
     * 期末库存周期
     */
    private String monthlyEndInventoryPeriod;

    /**
     * 金额（期末金额）
     */
    private BigDecimal inventoryTotalAmount;

    /**
     * 转换-客户属性
     */
    private String conversionCustomerType;

    /**
     * 转换-库存类别
     */
    private String conversionInventoryType;

    /**
     * 转换-年月
     */
    private String conversionYearMonth;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Integer createId;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     */
    private Integer updateId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth == null ? null : yearMonth.trim();
    }

    public String getAgencyShortName() {
        return agencyShortName;
    }

    public void setAgencyShortName(String agencyShortName) {
        this.agencyShortName = agencyShortName == null ? null : agencyShortName.trim();
    }

    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine == null ? null : productLine.trim();
    }

    public String getSubProductLine() {
        return subProductLine;
    }

    public void setSubProductLine(String subProductLine) {
        this.subProductLine = subProductLine == null ? null : subProductLine.trim();
    }

    public String getClass2() {
        return class2;
    }

    public void setClass2(String class2) {
        this.class2 = class2 == null ? null : class2.trim();
    }

    public String getClass3() {
        return class3;
    }

    public void setClass3(String class3) {
        this.class3 = class3 == null ? null : class3.trim();
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product == null ? null : product.trim();
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType == null ? null : customerType.trim();
    }

    public String getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(String inventoryType) {
        this.inventoryType = inventoryType == null ? null : inventoryType.trim();
    }

    public BigDecimal getInventoryPrice() {
        return inventoryPrice;
    }

    public void setInventoryPrice(BigDecimal inventoryPrice) {
        this.inventoryPrice = inventoryPrice;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getSalesOrg() {
        return salesOrg;
    }

    public void setSalesOrg(String salesOrg) {
        this.salesOrg = salesOrg == null ? null : salesOrg.trim();
    }

    public Integer getMonthlyInitQty() {
        return monthlyInitQty;
    }

    public void setMonthlyInitQty(Integer monthlyInitQty) {
        this.monthlyInitQty = monthlyInitQty;
    }

    public String getAgencyPickUpDate() {
        return agencyPickUpDate;
    }

    public void setAgencyPickUpDate(String agencyPickUpDate) {
        this.agencyPickUpDate = agencyPickUpDate == null ? null : agencyPickUpDate.trim();
    }

    public Integer getAgencyPickUpQty() {
        return agencyPickUpQty;
    }

    public void setAgencyPickUpQty(Integer agencyPickUpQty) {
        this.agencyPickUpQty = agencyPickUpQty;
    }

    public Integer getSalesQty() {
        return salesQty;
    }

    public void setSalesQty(Integer salesQty) {
        this.salesQty = salesQty;
    }

    public Integer getMonthlyEndQty() {
        return monthlyEndQty;
    }

    public void setMonthlyEndQty(Integer monthlyEndQty) {
        this.monthlyEndQty = monthlyEndQty;
    }

    public String getMonthlyEndInventoryPeriod() {
        return monthlyEndInventoryPeriod;
    }

    public void setMonthlyEndInventoryPeriod(String monthlyEndInventoryPeriod) {
        this.monthlyEndInventoryPeriod = monthlyEndInventoryPeriod == null ? null : monthlyEndInventoryPeriod.trim();
    }

    public BigDecimal getInventoryTotalAmount() {
        return inventoryTotalAmount;
    }

    public void setInventoryTotalAmount(BigDecimal inventoryTotalAmount) {
        this.inventoryTotalAmount = inventoryTotalAmount;
    }

    public String getConversionCustomerType() {
        return conversionCustomerType;
    }

    public void setConversionCustomerType(String conversionCustomerType) {
        this.conversionCustomerType = conversionCustomerType == null ? null : conversionCustomerType.trim();
    }

    public String getConversionInventoryType() {
        return conversionInventoryType;
    }

    public void setConversionInventoryType(String conversionInventoryType) {
        this.conversionInventoryType = conversionInventoryType == null ? null : conversionInventoryType.trim();
    }

    public String getConversionYearMonth() {
        return conversionYearMonth;
    }

    public void setConversionYearMonth(String conversionYearMonth) {
        this.conversionYearMonth = conversionYearMonth == null ? null : conversionYearMonth.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }
}