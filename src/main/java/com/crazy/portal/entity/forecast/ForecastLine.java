package com.crazy.portal.entity.forecast;

/**
 * 
 * @author lee
 * @date   2019-08-31 15:40::38
 */
public class ForecastLine {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer fId;

    /**
     * 
     */
    private Integer sortNum;

    /**
     * 
     */
    private String forecastMonth;

    /**
     * 
     */
    private String lastWrite;

    /**
     * 
     */
    private String currentWrite;

    /**
     * 
     */
    private String gap;

    /**
     * 
     */
    private String remark;

    /**
     * 
     */
    private String ambAdjustment;

    /**
     * 
     */
    private String ambRemark;

    /**
     * 
     */
    private String sdAdjustment;

    /**
     * 
     */
    private String sdRemark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public String getForecastMonth() {
        return forecastMonth;
    }

    public void setForecastMonth(String forecastMonth) {
        this.forecastMonth = forecastMonth == null ? null : forecastMonth.trim();
    }

    public String getLastWrite() {
        return lastWrite;
    }

    public void setLastWrite(String lastWrite) {
        this.lastWrite = lastWrite == null ? null : lastWrite.trim();
    }

    public String getCurrentWrite() {
        return currentWrite;
    }

    public void setCurrentWrite(String currentWrite) {
        this.currentWrite = currentWrite == null ? null : currentWrite.trim();
    }

    public String getGap() {
        return gap;
    }

    public void setGap(String gap) {
        this.gap = gap == null ? null : gap.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getAmbAdjustment() {
        return ambAdjustment;
    }

    public void setAmbAdjustment(String ambAdjustment) {
        this.ambAdjustment = ambAdjustment == null ? null : ambAdjustment.trim();
    }

    public String getAmbRemark() {
        return ambRemark;
    }

    public void setAmbRemark(String ambRemark) {
        this.ambRemark = ambRemark == null ? null : ambRemark.trim();
    }

    public String getSdAdjustment() {
        return sdAdjustment;
    }

    public void setSdAdjustment(String sdAdjustment) {
        this.sdAdjustment = sdAdjustment == null ? null : sdAdjustment.trim();
    }

    public String getSdRemark() {
        return sdRemark;
    }

    public void setSdRemark(String sdRemark) {
        this.sdRemark = sdRemark == null ? null : sdRemark.trim();
    }
}