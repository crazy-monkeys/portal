package com.crazy.portal.entity.cusotmer;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 联系人
 * @author weiying
 * @date   2019-08-29 16:10::00
 */
public class CustomerContact {
    /**
     * 
     */
    private Integer contactId;

    /**
     * 
     */
    private Integer custId;

    /**
     * 
     */
    private String c4cId;

    /**
     * （customer）C01 - 出货数据确认人(Shipment Data Contact); C02 - Rebate确认人(Rebate Contact);
     */
    private String type;

    /**
     * 姓名
     */
    private String contactName;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 部门
     */
    private String department;

    /**
     * 二级部门
     */
    private String subDepartment;

    /**
     * 职位
     */
    private String position;

    /**
     * 股权占比
     */
    private BigDecimal equityRatio;

    /**
     * 
     */
    private Date insertTime;

    /**
     * 
     */
    private Integer insertUser;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     */
    private Integer updateUser;

    /**
     * 
     */
    private Integer active;

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getC4cId() {
        return c4cId;
    }

    public void setC4cId(String c4cId) {
        this.c4cId = c4cId == null ? null : c4cId.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getSubDepartment() {
        return subDepartment;
    }

    public void setSubDepartment(String subDepartment) {
        this.subDepartment = subDepartment == null ? null : subDepartment.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public BigDecimal getEquityRatio() {
        return equityRatio;
    }

    public void setEquityRatio(BigDecimal equityRatio) {
        this.equityRatio = equityRatio;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Integer getInsertUser() {
        return insertUser;
    }

    public void setInsertUser(Integer insertUser) {
        this.insertUser = insertUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }
}