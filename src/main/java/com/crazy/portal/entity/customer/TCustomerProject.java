package com.crazy.portal.entity.customer;

import java.util.Date;

public class TCustomerProject {
    private Integer id;

    private Integer regionId;

    private String projectName;

    private String materialName;

    private String competitor;

    private String competitorMaterialName;

    private Long expectedVolume;

    private Date estimatedProductionTime;

    private Date createTime;

    private Integer createUserId;

    private Date updateTime;

    private Integer updateUserId;

    private Short active;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName == null ? null : materialName.trim();
    }

    public String getCompetitor() {
        return competitor;
    }

    public void setCompetitor(String competitor) {
        this.competitor = competitor == null ? null : competitor.trim();
    }

    public String getCompetitorMaterialName() {
        return competitorMaterialName;
    }

    public void setCompetitorMaterialName(String competitorMaterialName) {
        this.competitorMaterialName = competitorMaterialName == null ? null : competitorMaterialName.trim();
    }

    public Long getExpectedVolume() {
        return expectedVolume;
    }

    public void setExpectedVolume(Long expectedVolume) {
        this.expectedVolume = expectedVolume;
    }

    public Date getEstimatedProductionTime() {
        return estimatedProductionTime;
    }

    public void setEstimatedProductionTime(Date estimatedProductionTime) {
        this.estimatedProductionTime = estimatedProductionTime;
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

    public Short getActive() {
        return active;
    }

    public void setActive(Short active) {
        this.active = active;
    }
}