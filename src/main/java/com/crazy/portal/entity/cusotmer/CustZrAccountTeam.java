package com.crazy.portal.entity.cusotmer;

import java.util.Date;

/**
 * 
 * @author weiying
 * @date   2019-09-08 17:07::55
 */
public class CustZrAccountTeam {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer custId;

    /**
     * 
     */
    private String roleType;

    /**
     * 
     */
    private String employeeId;

    /**
     * 
     */
    private Integer active;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Integer createUserId;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     */
    private Integer updateUserId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType == null ? null : roleType.trim();
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId == null ? null : employeeId.trim();
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }
}