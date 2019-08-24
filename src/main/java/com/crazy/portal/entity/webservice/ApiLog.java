package com.crazy.portal.entity.webservice;

import java.util.Date;

/**
 * 
 * @author xsh12148
 * @date   2019-08-24 18:40::14
 */
public class ApiLog {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String requestMessage;

    /**
     * 
     */
    private String responseMessage;

    /**
     * 
     */
    private String reqCode;

    /**
     * 
     */
    private String errorMessage;

    /**
     * 
     */
    private Integer reqTime;

    /**
     * 
     */
    private Integer createId;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Integer updateId;

    /**
     * 
     */
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(String requestMessage) {
        this.requestMessage = requestMessage == null ? null : requestMessage.trim();
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage == null ? null : responseMessage.trim();
    }

    public String getReqCode() {
        return reqCode;
    }

    public void setReqCode(String reqCode) {
        this.reqCode = reqCode == null ? null : reqCode.trim();
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage == null ? null : errorMessage.trim();
    }

    public Integer getReqTime() {
        return reqTime;
    }

    public void setReqTime(Integer reqTime) {
        this.reqTime = reqTime;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}