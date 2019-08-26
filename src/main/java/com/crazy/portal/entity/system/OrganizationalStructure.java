package com.crazy.portal.entity.system;

import java.util.Date;

/**
 * 
 * @author weiying
 * @date   2019-08-26 17:04::15
 */
public class OrganizationalStructure {
    /**
     * 
     */
    private Integer id;

    /**
     * C4C序号，唯一识别码
     */
    private Integer seq;

    /**
     * 组织名称
     */
    private String orgName;

    /**
     * 生效时间
     */
    private String validTime;

    /**
     * 失效时间
     */
    private String invalidTime;

    /**
     * 上级单位
     */
    private Integer parentOrg;

    /**
     * 上级单位名称
     */
    private String parentOrgName;

    /**
     * 经理
     */
    private String pm;

    /**
     * 是否销售组织
     */
    private String isSales;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getValidTime() {
        return validTime;
    }

    public void setValidTime(String validTime) {
        this.validTime = validTime == null ? null : validTime.trim();
    }

    public String getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(String invalidTime) {
        this.invalidTime = invalidTime == null ? null : invalidTime.trim();
    }

    public Integer getParentOrg() {
        return parentOrg;
    }

    public void setParentOrg(Integer parentOrg) {
        this.parentOrg = parentOrg;
    }

    public String getParentOrgName() {
        return parentOrgName;
    }

    public void setParentOrgName(String parentOrgName) {
        this.parentOrgName = parentOrgName == null ? null : parentOrgName.trim();
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm == null ? null : pm.trim();
    }

    public String getIsSales() {
        return isSales;
    }

    public void setIsSales(String isSales) {
        this.isSales = isSales == null ? null : isSales.trim();
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