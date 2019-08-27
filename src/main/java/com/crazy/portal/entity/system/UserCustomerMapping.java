package com.crazy.portal.entity.system;

import java.util.Date;

/**
 * 
 * @author weiying
 * @date   2019-08-26 22:57::08
 */
public class UserCustomerMapping {
    /**
     * 
     */
    private Integer mappingId;

    /**
     * 模块
     */
    private Integer mappingModel;

    /**
     * 营销运作部
     */
    private Integer cdUserId;

    /**
     * 代理商经营部
     */
    private Integer dealerUserId;

    /**
     * 负责的客户id
     */
    private String custId;

    /**
     * 
     */
    private Integer insertUserId;

    /**
     * 
     */
    private Date insertTime;

    /**
     * 
     */
    private Integer updateUserId;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     */
    private Integer active;

    public Integer getMappingId() {
        return mappingId;
    }

    public void setMappingId(Integer mappingId) {
        this.mappingId = mappingId;
    }

    public Integer getMappingModel() {
        return mappingModel;
    }

    public void setMappingModel(Integer mappingModel) {
        this.mappingModel = mappingModel;
    }

    public Integer getCdUserId() {
        return cdUserId;
    }

    public void setCdUserId(Integer cdUserId) {
        this.cdUserId = cdUserId;
    }

    public Integer getDealerUserId() {
        return dealerUserId;
    }

    public void setDealerUserId(Integer dealerUserId) {
        this.dealerUserId = dealerUserId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId == null ? null : custId.trim();
    }

    public Integer getInsertUserId() {
        return insertUserId;
    }

    public void setInsertUserId(Integer insertUserId) {
        this.insertUserId = insertUserId;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
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