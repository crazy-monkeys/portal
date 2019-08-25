package com.crazy.portal.entity.group;

import java.util.Date;

/**
 * 
 * @author weiying
 * @date   2019-08-25 13:29::41
 */
public class SalesGroup {
    /**
     * 
     */
    private Integer groupId;

    /**
     * 销售组织
     */
    private Integer groupCode;

    /**
     * 销售组织名称
     */
    private String groupName;

    /**
     * 渠道
     */
    private Integer channelCode;

    /**
     * 渠道名
     */
    private String channelName;

    /**
     * 产品组
     */
    private String productGroupCode;

    /**
     * 产品组名
     */
    private String productGroupName;

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

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(Integer groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public Integer getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(Integer channelCode) {
        this.channelCode = channelCode;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    public String getProductGroupCode() {
        return productGroupCode;
    }

    public void setProductGroupCode(String productGroupCode) {
        this.productGroupCode = productGroupCode == null ? null : productGroupCode.trim();
    }

    public String getProductGroupName() {
        return productGroupName;
    }

    public void setProductGroupName(String productGroupName) {
        this.productGroupName = productGroupName == null ? null : productGroupName.trim();
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