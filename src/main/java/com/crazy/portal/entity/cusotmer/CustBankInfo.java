package com.crazy.portal.entity.cusotmer;

import java.util.Date;

/**
 * 
 * @author weiying
 * @date   2019-08-29 16:10::00
 */
public class CustBankInfo {
    /**
     * 
     */
    private Integer bankId;

    /**
     * 
     */
    private Integer custId;

    /**
     * 
     */
    private String bankName;

    /**
     * 
     */
    private String bankAccount;

    /**
     * 
     */
    private String bankBic;

    /**
     * 
     */
    private String bankCountry;

    /**
     * 
     */
    private String bankProvince;

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

    /**
     * 
     */
    private Integer active;

    /**
     * 
     */
    private String bankDetailInfo;

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount == null ? null : bankAccount.trim();
    }

    public String getBankBic() {
        return bankBic;
    }

    public void setBankBic(String bankBic) {
        this.bankBic = bankBic == null ? null : bankBic.trim();
    }

    public String getBankCountry() {
        return bankCountry;
    }

    public void setBankCountry(String bankCountry) {
        this.bankCountry = bankCountry == null ? null : bankCountry.trim();
    }

    public String getBankProvince() {
        return bankProvince;
    }

    public void setBankProvince(String bankProvince) {
        this.bankProvince = bankProvince == null ? null : bankProvince.trim();
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

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getBankDetailInfo() {
        return bankDetailInfo;
    }

    public void setBankDetailInfo(String bankDetailInfo) {
        this.bankDetailInfo = bankDetailInfo == null ? null : bankDetailInfo.trim();
    }
}