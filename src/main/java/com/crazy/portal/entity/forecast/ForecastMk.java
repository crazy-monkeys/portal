package com.crazy.portal.entity.forecast;

/**
 * 
 * @author weiying
 * @date   2020-01-15 18:27::06
 */
public class ForecastMk {
    /**
     * 
     */
    private Integer mkId;

    /**
     * 
     */
    private String dealerName;

    /**
     * 
     */
    private Integer dealerId;

    /**
     * 
     */
    private String updateM;

    /**
     * 
     */
    private String insertM;

    /**
     * 
     */
    private Integer updateS;

    /**
     * 
     */
    private Integer insertS;

    /**
     * 
     */
    private Integer active;

    public Integer getMkId() {
        return mkId;
    }

    public void setMkId(Integer mkId) {
        this.mkId = mkId;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName == null ? null : dealerName.trim();
    }

    public Integer getDealerId() {
        return dealerId;
    }

    public void setDealerId(Integer dealerId) {
        this.dealerId = dealerId;
    }

    public String getUpdateM() {
        return updateM;
    }

    public void setUpdateM(String updateM) {
        this.updateM = updateM == null ? null : updateM.trim();
    }

    public String getInsertM() {
        return insertM;
    }

    public void setInsertM(String insertM) {
        this.insertM = insertM == null ? null : insertM.trim();
    }

    public Integer getUpdateS() {
        return updateS;
    }

    public void setUpdateS(Integer updateS) {
        this.updateS = updateS;
    }

    public Integer getInsertS() {
        return insertS;
    }

    public void setInsertS(Integer insertS) {
        this.insertS = insertS;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }
}