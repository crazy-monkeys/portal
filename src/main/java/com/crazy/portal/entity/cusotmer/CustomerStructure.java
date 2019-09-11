package com.crazy.portal.entity.cusotmer;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author weiying
 * @date   2019-09-02 23:56::47
 */
public class CustomerStructure {
    /**
     * 
     */
    private Integer structureId;

    /**
     * 
     */
    private Integer custId;

    /**
     * 
     */
    private String strName;

    /**
     * 
     */
    private BigDecimal strValue;

    /**
     * 上级公司
     */
    private String strOne;

    /**
     * 股东性质
     */
    private String strTwo;

    /**
     * 公司性质
     */
    private String strThree;

    /**
     * 是否公司管理层
     */
    private String strFour;

    /**
     * 职务
     */
    private String strFive;

    /**
     * 部门
     */
    private String strSix;

    /**
     * 
     */
    private Integer active;

    /**
     * 
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 
     */
    private Integer createUserId;

    /**
     * 
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 
     */
    private Integer updateUserId;

    public Integer getStructureId() {
        return structureId;
    }

    public void setStructureId(Integer structureId) {
        this.structureId = structureId;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName == null ? null : strName.trim();
    }

    public BigDecimal getStrValue() {
        return strValue;
    }

    public void setStrValue(BigDecimal strValue) {
        this.strValue = strValue;
    }

    public String getStrOne() {
        return strOne;
    }

    public void setStrOne(String strOne) {
        this.strOne = strOne == null ? null : strOne.trim();
    }

    public String getStrTwo() {
        return strTwo;
    }

    public void setStrTwo(String strTwo) {
        this.strTwo = strTwo == null ? null : strTwo.trim();
    }

    public String getStrThree() {
        return strThree;
    }

    public void setStrThree(String strThree) {
        this.strThree = strThree == null ? null : strThree.trim();
    }

    public String getStrFour() {
        return strFour;
    }

    public void setStrFour(String strFour) {
        this.strFour = strFour == null ? null : strFour.trim();
    }

    public String getStrFive() {
        return strFive;
    }

    public void setStrFive(String strFive) {
        this.strFive = strFive == null ? null : strFive.trim();
    }

    public String getStrSix() {
        return strSix;
    }

    public void setStrSix(String strSix) {
        this.strSix = strSix == null ? null : strSix.trim();
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