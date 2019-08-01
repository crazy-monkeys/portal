package com.crazy.portal.entity.customer;

import java.util.Date;

public class VisitRecord {
    private Integer id;

    private Integer custId;

    private Date visitDate;

    private String customerLocation;

    private String customerName;

    private Integer visitNumber;

    private String visitPurpose;

    private String projectName;

    private String projectStatus;

    private String projectDepartment;

    private String talkContent;

    private String followPlan;

    private String claimDescription;

    private String participantsZr;

    private String participantsCt;

    private String participantsDl;

    private String errorMessage;

    private Integer active;

    private Integer createUserId;

    private Date createTime;

    private Integer updateUserId;

    private Date updateTime;

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

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public String getCustomerLocation() {
        return customerLocation;
    }

    public void setCustomerLocation(String customerLocation) {
        this.customerLocation = customerLocation == null ? null : customerLocation.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public Integer getVisitNumber() {
        return visitNumber;
    }

    public void setVisitNumber(Integer visitNumber) {
        this.visitNumber = visitNumber;
    }

    public String getVisitPurpose() {
        return visitPurpose;
    }

    public void setVisitPurpose(String visitPurpose) {
        this.visitPurpose = visitPurpose == null ? null : visitPurpose.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus == null ? null : projectStatus.trim();
    }

    public String getProjectDepartment() {
        return projectDepartment;
    }

    public void setProjectDepartment(String projectDepartment) {
        this.projectDepartment = projectDepartment == null ? null : projectDepartment.trim();
    }

    public String getTalkContent() {
        return talkContent;
    }

    public void setTalkContent(String talkContent) {
        this.talkContent = talkContent == null ? null : talkContent.trim();
    }

    public String getFollowPlan() {
        return followPlan;
    }

    public void setFollowPlan(String followPlan) {
        this.followPlan = followPlan == null ? null : followPlan.trim();
    }

    public String getClaimDescription() {
        return claimDescription;
    }

    public void setClaimDescription(String claimDescription) {
        this.claimDescription = claimDescription == null ? null : claimDescription.trim();
    }

    public String getParticipantsZr() {
        return participantsZr;
    }

    public void setParticipantsZr(String participantsZr) {
        this.participantsZr = participantsZr == null ? null : participantsZr.trim();
    }

    public String getParticipantsCt() {
        return participantsCt;
    }

    public void setParticipantsCt(String participantsCt) {
        this.participantsCt = participantsCt == null ? null : participantsCt.trim();
    }

    public String getParticipantsDl() {
        return participantsDl;
    }

    public void setParticipantsDl(String participantsDl) {
        this.participantsDl = participantsDl == null ? null : participantsDl.trim();
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage == null ? null : errorMessage.trim();
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
}