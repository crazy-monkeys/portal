package com.crazy.portal.entity.customer;

import java.util.Date;

public class CustIntercourseInfo {
    private Long id;

    private String majorSupplier;

    private String majorClient;

    private Long createId;

    private Date createTime;

    private Long modifyId;

    private Date modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMajorSupplier() {
        return majorSupplier;
    }

    public void setMajorSupplier(String majorSupplier) {
        this.majorSupplier = majorSupplier == null ? null : majorSupplier.trim();
    }

    public String getMajorClient() {
        return majorClient;
    }

    public void setMajorClient(String majorClient) {
        this.majorClient = majorClient == null ? null : majorClient.trim();
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getModifyId() {
        return modifyId;
    }

    public void setModifyId(Long modifyId) {
        this.modifyId = modifyId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}