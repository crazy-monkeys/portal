package com.crazy.portal.entity.group;

import java.util.Date;

/**
 * 
 * @author weiying
 * @date   2019-08-25 13:29::41
 */
public class SalesGroupOffice {
    /**
     * 
     */
    private Integer officeId;

    /**
     * 销售办公室
     */
    private String officeCode;

    /**
     * 销售办公室名
     */
    private String officeName;

    /**
     * 销售组
     */
    private String officeGroupCode;

    /**
     * 销售组名称
     */
    private String officeGroupName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 
     */
    private Date insertTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     */
    private Integer active;

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode == null ? null : officeCode.trim();
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName == null ? null : officeName.trim();
    }

    public String getOfficeGroupCode() {
        return officeGroupCode;
    }

    public void setOfficeGroupCode(String officeGroupCode) {
        this.officeGroupCode = officeGroupCode == null ? null : officeGroupCode.trim();
    }

    public String getOfficeGroupName() {
        return officeGroupName;
    }

    public void setOfficeGroupName(String officeGroupName) {
        this.officeGroupName = officeGroupName == null ? null : officeGroupName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
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
}