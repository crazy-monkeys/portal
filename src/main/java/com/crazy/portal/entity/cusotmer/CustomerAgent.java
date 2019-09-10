package com.crazy.portal.entity.cusotmer;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * dealer:代理商分级分类信息
 * @author weiying
 * @date   2019-08-29 16:10::00
 */
public class CustomerAgent {
    /**
     * 
     */
    private Integer agentId;

    /**
     * 
     */
    private Integer custId;

    /**
     * B001 - 智能终端(Intelligence Device BU); B002 - 通信终端(IOT Device BU); B003 - 泛连接(Connectivity Device BU);
     */
    private String bu;

    /**
     * P011 - FP, P012 - NBC, P013 - SP_1, P014 - SP_2, P015 - TV; P021 - GRID, P022 - IRT, P023 - MBB, P024 - WAN IOT; P031 - BT, P032 - RFFE, P033 - WIFI;
     */
    private String pdt;

    /**
     * 区域
     */
    private String territory;

    /**
     * 性质（选项：代理商，分销商，其他）
     */
    private String salesModel;

    /**
     * 等级
     */
    private String grade;

    /**
     * 
     */
    @JSONField(format="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startDate;

    /**
     * 
     */
    @JSONField(format="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;

    /**
     * 
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date insertTime;

    /**
     * 
     */
    private Integer active;

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getBu() {
        return bu;
    }

    public void setBu(String bu) {
        this.bu = bu == null ? null : bu.trim();
    }

    public String getPdt() {
        return pdt;
    }

    public void setPdt(String pdt) {
        this.pdt = pdt == null ? null : pdt.trim();
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory == null ? null : territory.trim();
    }

    public String getSalesModel() {
        return salesModel;
    }

    public void setSalesModel(String salesModel) {
        this.salesModel = salesModel == null ? null : salesModel.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }
}