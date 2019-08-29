package com.crazy.portal.entity.cusotmer;

import java.util.Date;

/**
 * 客户附件
 * @author weiying
 * @date   2019-08-29 16:10::00
 */
public class CustomerFile {
    /**
     * 
     */
    private Integer fileId;

    /**
     * 
     */
    private Integer custId;

    /**
     * 
     */
    private String custCode;

    /**
     * A001 - 营业执照/商业登记证，A002 - 开票信息，A003 - 关联公司证明，A004 - 法人身份证，A005 - 收货委托证明，A006 - 其他；
客户(Account): C001 - 营业执照/商业登记证，C002 - 银行开户证明，C003 - 组织架构图，C004 - 母公司营业执照/商业登记证，C005 - 其他
     */
    private String type;

    /**
     * 
     */
    private String title;

    /**
     * 
     */
    private Date uploadTime;

    /**
     * 
     */
    private Integer active;

    /**
     * 
     */
    private String fileName;

    /**
     * 
     */
    private String filePath;

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode == null ? null : custCode.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }
}