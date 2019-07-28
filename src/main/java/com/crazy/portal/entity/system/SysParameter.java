package com.crazy.portal.entity.system;

import java.util.Date;

public class SysParameter {
    private Integer id;

    private String pModel;

    private String pFunction;

    private Integer pValue;

    private String zhName;

    private String enName;

    private Integer active;

    private Integer insertUser;

    private Date insertTime;

    private Integer updateUser;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getpModel() {
        return pModel;
    }

    public void setpModel(String pModel) {
        this.pModel = pModel == null ? null : pModel.trim();
    }

    public String getpFunction() {
        return pFunction;
    }

    public void setpFunction(String pFunction) {
        this.pFunction = pFunction == null ? null : pFunction.trim();
    }

    public Integer getpValue() {
        return pValue;
    }

    public void setpValue(Integer pValue) {
        this.pValue = pValue;
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName == null ? null : zhName.trim();
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName == null ? null : enName.trim();
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Integer getInsertUser() {
        return insertUser;
    }

    public void setInsertUser(Integer insertUser) {
        this.insertUser = insertUser;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}