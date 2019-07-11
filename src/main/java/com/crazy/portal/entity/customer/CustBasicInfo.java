package com.crazy.portal.entity.customer;

import java.util.Date;

public class CustBasicInfo {
    private Long id;

    private String customerName;

    private String chineseName;

    private String englishName;

    private Integer customerType;

    private Integer salesId;

    private String amb;

    private String companyAssets;

    private Integer workersNumber;

    private String fax;

    private String parentCompany;

    private String companySwitchboard;

    private Date registerTime;

    private String registerProvince;

    private String registerCity;

    private String registerDistrict;

    private String registerAddress;

    private String officeProvince;

    private String officeCity;

    private String officeDistrict;

    private String officeAddress;

    private String businessIntroduction;

    private Short status;

    private Long createId;

    private Date crateTime;

    private Long modifyId;

    private Date modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName == null ? null : chineseName.trim();
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName == null ? null : englishName.trim();
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public Integer getSalesId() {
        return salesId;
    }

    public void setSalesId(Integer salesId) {
        this.salesId = salesId;
    }

    public String getAmb() {
        return amb;
    }

    public void setAmb(String amb) {
        this.amb = amb == null ? null : amb.trim();
    }

    public String getCompanyAssets() {
        return companyAssets;
    }

    public void setCompanyAssets(String companyAssets) {
        this.companyAssets = companyAssets == null ? null : companyAssets.trim();
    }

    public Integer getWorkersNumber() {
        return workersNumber;
    }

    public void setWorkersNumber(Integer workersNumber) {
        this.workersNumber = workersNumber;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getParentCompany() {
        return parentCompany;
    }

    public void setParentCompany(String parentCompany) {
        this.parentCompany = parentCompany == null ? null : parentCompany.trim();
    }

    public String getCompanySwitchboard() {
        return companySwitchboard;
    }

    public void setCompanySwitchboard(String companySwitchboard) {
        this.companySwitchboard = companySwitchboard == null ? null : companySwitchboard.trim();
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getRegisterProvince() {
        return registerProvince;
    }

    public void setRegisterProvince(String registerProvince) {
        this.registerProvince = registerProvince == null ? null : registerProvince.trim();
    }

    public String getRegisterCity() {
        return registerCity;
    }

    public void setRegisterCity(String registerCity) {
        this.registerCity = registerCity == null ? null : registerCity.trim();
    }

    public String getRegisterDistrict() {
        return registerDistrict;
    }

    public void setRegisterDistrict(String registerDistrict) {
        this.registerDistrict = registerDistrict == null ? null : registerDistrict.trim();
    }

    public String getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress == null ? null : registerAddress.trim();
    }

    public String getOfficeProvince() {
        return officeProvince;
    }

    public void setOfficeProvince(String officeProvince) {
        this.officeProvince = officeProvince == null ? null : officeProvince.trim();
    }

    public String getOfficeCity() {
        return officeCity;
    }

    public void setOfficeCity(String officeCity) {
        this.officeCity = officeCity == null ? null : officeCity.trim();
    }

    public String getOfficeDistrict() {
        return officeDistrict;
    }

    public void setOfficeDistrict(String officeDistrict) {
        this.officeDistrict = officeDistrict == null ? null : officeDistrict.trim();
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress == null ? null : officeAddress.trim();
    }

    public String getBusinessIntroduction() {
        return businessIntroduction;
    }

    public void setBusinessIntroduction(String businessIntroduction) {
        this.businessIntroduction = businessIntroduction == null ? null : businessIntroduction.trim();
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Date getCrateTime() {
        return crateTime;
    }

    public void setCrateTime(Date crateTime) {
        this.crateTime = crateTime;
    }

    public Long getModifyId() {
        return modifyId;
    }

    public void setModifyId(Long modifyId) {
        this.modifyId = modifyId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}