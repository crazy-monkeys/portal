package com.crazy.portal.entity.forecast;

import com.crazy.portal.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * 
 * @author lee
 * @date   2019-09-03 21:16::32
 */

@Slf4j
public class ForecastLine {
    /**
     * 
     */
    private Integer lineId;

    /**
     * 
     */
    private Integer fId;

    /**
     * 
     */
    private String forecastMonthOne;

    /**
     * 
     */
    private String lastWriteOne;

    /**
     * 
     */
    private String currentWriteOne;

    /**
     * 
     */
    private String gapOne;

    /**
     * 
     */
    private String remarkOne;

    /**
     * 
     */
    private String ambAdjustmentOne;

    /**
     * 
     */
    private String ambRemarkOne;

    /**
     * 
     */
    private String sdAdjustmentOne;

    /**
     * 
     */
    private String sdRemarkOne;

    /**
     * 
     */
    private String forecastMonthTwo;

    /**
     * 
     */
    private String lastWriteTwo;

    /**
     * 
     */
    private String currentWriteTwo;

    /**
     * 
     */
    private String gapTwo;

    /**
     * 
     */
    private String remarkTwo;

    /**
     * 
     */
    private String ambAdjustmentTwo;

    /**
     * 
     */
    private String ambRemarkTwo;

    /**
     * 
     */
    private String sdAdjustmentTwo;

    /**
     * 
     */
    private String sdRemarkTwo;

    /**
     * 
     */
    private String forecastMonthThree;

    /**
     * 
     */
    private String lastWriteThree;

    /**
     * 
     */
    private String currentWriteThree;

    /**
     * 
     */
    private String gapThree;

    /**
     * 
     */
    private String remarkThree;

    /**
     * 
     */
    private String ambAdjustmentThree;

    /**
     * 
     */
    private String ambRemarkThree;

    /**
     * 
     */
    private String sdAdjustmentThree;

    /**
     * 
     */
    private String sdRemarkThree;

    /**
     * 
     */
    private String forecastMonthFour;

    /**
     * 
     */
    private String lastWriteFour;

    /**
     * 
     */
    private String currentWriteFour;

    /**
     * 
     */
    private String gapFour;

    /**
     * 
     */
    private String remarkFour;

    /**
     * 
     */
    private String ambAdjustmentFour;

    /**
     * 
     */
    private String ambRemarkFour;

    /**
     * 
     */
    private String sdAdjustmentFour;

    /**
     * 
     */
    private String sdRemarkFour;

    /**
     * 
     */
    private String forecastMonthFive;

    /**
     * 
     */
    private String lastWriteFive;

    /**
     * 
     */
    private String currentWriteFive;

    /**
     * 
     */
    private String gapFive;

    /**
     * 
     */
    private String remarkFive;

    /**
     * 
     */
    private String ambAdjustmentFive;

    /**
     * 
     */
    private String ambRemarkFive;

    /**
     * 
     */
    private String sdAdjustmentFive;

    /**
     * 
     */
    private String sdRemarkFive;

    /**
     * 
     */
    private String forecastMonthSix;

    /**
     * 
     */
    private String lastWriteSix;

    /**
     * 
     */
    private String currentWriteSix;

    /**
     * 
     */
    private String gapSix;

    /**
     * 
     */
    private String remarkSix;

    /**
     * 
     */
    private String ambAdjustmentSix;

    /**
     * 
     */
    private String ambRemarkSix;

    /**
     * 
     */
    private String sdAdjustmentSix;

    /**
     * 
     */
    private String sdRemarkSix;

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    public String getForecastMonthOne() {
        return forecastMonthOne;
    }

    public void setForecastMonthOne(String forecastMonthOne) {
        this.forecastMonthOne = forecastMonthOne == null ? null : forecastMonthOne.trim();
    }

    public String getLastWriteOne() {
        return lastWriteOne;
    }

    public void setLastWriteOne(String lastWriteOne) {
        this.lastWriteOne = lastWriteOne == null ? null : lastWriteOne.trim();
    }

    public String getCurrentWriteOne() {
        return currentWriteOne;
    }

    public void setCurrentWriteOne(String currentWriteOne) {
        this.currentWriteOne = currentWriteOne == null ? null : currentWriteOne.trim();
    }

    public String getGapOne() {
        return calculateGap(lastWriteOne, currentWriteOne, gapOne);
    }

    public void setGapOne(String gapOne) {
        this.gapOne = gapOne == null ? null : gapOne.trim();
    }

    public String getRemarkOne() {
        return remarkOne;
    }

    public void setRemarkOne(String remarkOne) {
        this.remarkOne = remarkOne == null ? null : remarkOne.trim();
    }

    public String getAmbAdjustmentOne() {
        return ambAdjustmentOne;
    }

    public void setAmbAdjustmentOne(String ambAdjustmentOne) {
        this.ambAdjustmentOne = ambAdjustmentOne == null ? null : ambAdjustmentOne.trim();
    }

    public String getAmbRemarkOne() {
        return ambRemarkOne;
    }

    public void setAmbRemarkOne(String ambRemarkOne) {
        this.ambRemarkOne = ambRemarkOne == null ? null : ambRemarkOne.trim();
    }

    public String getSdAdjustmentOne() {
        return sdAdjustmentOne;
    }

    public void setSdAdjustmentOne(String sdAdjustmentOne) {
        this.sdAdjustmentOne = sdAdjustmentOne == null ? null : sdAdjustmentOne.trim();
    }

    public String getSdRemarkOne() {
        return sdRemarkOne;
    }

    public void setSdRemarkOne(String sdRemarkOne) {
        this.sdRemarkOne = sdRemarkOne == null ? null : sdRemarkOne.trim();
    }

    public String getForecastMonthTwo() {
        return forecastMonthTwo;
    }

    public void setForecastMonthTwo(String forecastMonthTwo) {
        this.forecastMonthTwo = forecastMonthTwo == null ? null : forecastMonthTwo.trim();
    }

    public String getLastWriteTwo() {
        return lastWriteTwo;
    }

    public void setLastWriteTwo(String lastWriteTwo) {
        this.lastWriteTwo = lastWriteTwo == null ? null : lastWriteTwo.trim();
    }

    public String getCurrentWriteTwo() {
        return currentWriteTwo;
    }

    public void setCurrentWriteTwo(String currentWriteTwo) {
        this.currentWriteTwo = currentWriteTwo == null ? null : currentWriteTwo.trim();
    }

    public String getGapTwo() {
        return calculateGap(lastWriteTwo, currentWriteTwo, gapTwo);
    }

    public void setGapTwo(String gapTwo) {
        this.gapTwo = gapTwo == null ? null : gapTwo.trim();
    }

    public String getRemarkTwo() {
        return remarkTwo;
    }

    public void setRemarkTwo(String remarkTwo) {
        this.remarkTwo = remarkTwo == null ? null : remarkTwo.trim();
    }

    public String getAmbAdjustmentTwo() {
        return ambAdjustmentTwo;
    }

    public void setAmbAdjustmentTwo(String ambAdjustmentTwo) {
        this.ambAdjustmentTwo = ambAdjustmentTwo == null ? null : ambAdjustmentTwo.trim();
    }

    public String getAmbRemarkTwo() {
        return ambRemarkTwo;
    }

    public void setAmbRemarkTwo(String ambRemarkTwo) {
        this.ambRemarkTwo = ambRemarkTwo == null ? null : ambRemarkTwo.trim();
    }

    public String getSdAdjustmentTwo() {
        return sdAdjustmentTwo;
    }

    public void setSdAdjustmentTwo(String sdAdjustmentTwo) {
        this.sdAdjustmentTwo = sdAdjustmentTwo == null ? null : sdAdjustmentTwo.trim();
    }

    public String getSdRemarkTwo() {
        return sdRemarkTwo;
    }

    public void setSdRemarkTwo(String sdRemarkTwo) {
        this.sdRemarkTwo = sdRemarkTwo == null ? null : sdRemarkTwo.trim();
    }

    public String getForecastMonthThree() {
        return forecastMonthThree;
    }

    public void setForecastMonthThree(String forecastMonthThree) {
        this.forecastMonthThree = forecastMonthThree == null ? null : forecastMonthThree.trim();
    }

    public String getLastWriteThree() {
        return lastWriteThree;
    }

    public void setLastWriteThree(String lastWriteThree) {
        this.lastWriteThree = lastWriteThree == null ? null : lastWriteThree.trim();
    }

    public String getCurrentWriteThree() {
        return currentWriteThree;
    }

    public void setCurrentWriteThree(String currentWriteThree) {
        this.currentWriteThree = currentWriteThree == null ? null : currentWriteThree.trim();
    }

    public String getGapThree() {
        return calculateGap(lastWriteThree, currentWriteThree, gapThree);
    }

    public void setGapThree(String gapThree) {
        this.gapThree = gapThree == null ? null : gapThree.trim();
    }

    public String getRemarkThree() {
        return remarkThree;
    }

    public void setRemarkThree(String remarkThree) {
        this.remarkThree = remarkThree == null ? null : remarkThree.trim();
    }

    public String getAmbAdjustmentThree() {
        return ambAdjustmentThree;
    }

    public void setAmbAdjustmentThree(String ambAdjustmentThree) {
        this.ambAdjustmentThree = ambAdjustmentThree == null ? null : ambAdjustmentThree.trim();
    }

    public String getAmbRemarkThree() {
        return ambRemarkThree;
    }

    public void setAmbRemarkThree(String ambRemarkThree) {
        this.ambRemarkThree = ambRemarkThree == null ? null : ambRemarkThree.trim();
    }

    public String getSdAdjustmentThree() {
        return sdAdjustmentThree;
    }

    public void setSdAdjustmentThree(String sdAdjustmentThree) {
        this.sdAdjustmentThree = sdAdjustmentThree == null ? null : sdAdjustmentThree.trim();
    }

    public String getSdRemarkThree() {
        return sdRemarkThree;
    }

    public void setSdRemarkThree(String sdRemarkThree) {
        this.sdRemarkThree = sdRemarkThree == null ? null : sdRemarkThree.trim();
    }

    public String getForecastMonthFour() {
        return forecastMonthFour;
    }

    public void setForecastMonthFour(String forecastMonthFour) {
        this.forecastMonthFour = forecastMonthFour == null ? null : forecastMonthFour.trim();
    }

    public String getLastWriteFour() {
        return lastWriteFour;
    }

    public void setLastWriteFour(String lastWriteFour) {
        this.lastWriteFour = lastWriteFour == null ? null : lastWriteFour.trim();
    }

    public String getCurrentWriteFour() {
        return currentWriteFour;
    }

    public void setCurrentWriteFour(String currentWriteFour) {
        this.currentWriteFour = currentWriteFour == null ? null : currentWriteFour.trim();
    }

    public String getGapFour() {
        return calculateGap(lastWriteFour, currentWriteFour, gapFour);
    }

    public void setGapFour(String gapFour) {
        this.gapFour = gapFour == null ? null : gapFour.trim();
    }

    public String getRemarkFour() {
        return remarkFour;
    }

    public void setRemarkFour(String remarkFour) {
        this.remarkFour = remarkFour == null ? null : remarkFour.trim();
    }

    public String getAmbAdjustmentFour() {
        return ambAdjustmentFour;
    }

    public void setAmbAdjustmentFour(String ambAdjustmentFour) {
        this.ambAdjustmentFour = ambAdjustmentFour == null ? null : ambAdjustmentFour.trim();
    }

    public String getAmbRemarkFour() {
        return ambRemarkFour;
    }

    public void setAmbRemarkFour(String ambRemarkFour) {
        this.ambRemarkFour = ambRemarkFour == null ? null : ambRemarkFour.trim();
    }

    public String getSdAdjustmentFour() {
        return sdAdjustmentFour;
    }

    public void setSdAdjustmentFour(String sdAdjustmentFour) {
        this.sdAdjustmentFour = sdAdjustmentFour == null ? null : sdAdjustmentFour.trim();
    }

    public String getSdRemarkFour() {
        return sdRemarkFour;
    }

    public void setSdRemarkFour(String sdRemarkFour) {
        this.sdRemarkFour = sdRemarkFour == null ? null : sdRemarkFour.trim();
    }

    public String getForecastMonthFive() {
        return forecastMonthFive;
    }

    public void setForecastMonthFive(String forecastMonthFive) {
        this.forecastMonthFive = forecastMonthFive == null ? null : forecastMonthFive.trim();
    }

    public String getLastWriteFive() {
        return lastWriteFive;
    }

    public void setLastWriteFive(String lastWriteFive) {
        this.lastWriteFive = lastWriteFive == null ? null : lastWriteFive.trim();
    }

    public String getCurrentWriteFive() {
        return currentWriteFive;
    }

    public void setCurrentWriteFive(String currentWriteFive) {
        this.currentWriteFive = currentWriteFive == null ? null : currentWriteFive.trim();
    }

    public String getGapFive() {
        return calculateGap(lastWriteFive, currentWriteFive, gapFive);
    }

    public void setGapFive(String gapFive) {
        this.gapFive = gapFive == null ? null : gapFive.trim();
    }

    public String getRemarkFive() {
        return remarkFive;
    }

    public void setRemarkFive(String remarkFive) {
        this.remarkFive = remarkFive == null ? null : remarkFive.trim();
    }

    public String getAmbAdjustmentFive() {
        return ambAdjustmentFive;
    }

    public void setAmbAdjustmentFive(String ambAdjustmentFive) {
        this.ambAdjustmentFive = ambAdjustmentFive == null ? null : ambAdjustmentFive.trim();
    }

    public String getAmbRemarkFive() {
        return ambRemarkFive;
    }

    public void setAmbRemarkFive(String ambRemarkFive) {
        this.ambRemarkFive = ambRemarkFive == null ? null : ambRemarkFive.trim();
    }

    public String getSdAdjustmentFive() {
        return sdAdjustmentFive;
    }

    public void setSdAdjustmentFive(String sdAdjustmentFive) {
        this.sdAdjustmentFive = sdAdjustmentFive == null ? null : sdAdjustmentFive.trim();
    }

    public String getSdRemarkFive() {
        return sdRemarkFive;
    }

    public void setSdRemarkFive(String sdRemarkFive) {
        this.sdRemarkFive = sdRemarkFive == null ? null : sdRemarkFive.trim();
    }

    public String getForecastMonthSix() {
        return forecastMonthSix;
    }

    public void setForecastMonthSix(String forecastMonthSix) {
        this.forecastMonthSix = forecastMonthSix == null ? null : forecastMonthSix.trim();
    }

    public String getLastWriteSix() {
        return lastWriteSix;
    }

    public void setLastWriteSix(String lastWriteSix) {
        this.lastWriteSix = lastWriteSix == null ? null : lastWriteSix.trim();
    }

    public String getCurrentWriteSix() {
        return currentWriteSix;
    }

    public void setCurrentWriteSix(String currentWriteSix) {
        this.currentWriteSix = currentWriteSix == null ? null : currentWriteSix.trim();
    }

    public String getGapSix() {
        return calculateGap(lastWriteSix, currentWriteSix, gapSix);
    }

    public void setGapSix(String gapSix) {
        this.gapSix = gapSix == null ? null : gapSix.trim();
    }

    public String getRemarkSix() {
        return remarkSix;
    }

    public void setRemarkSix(String remarkSix) {
        this.remarkSix = remarkSix == null ? null : remarkSix.trim();
    }

    public String getAmbAdjustmentSix() {
        return ambAdjustmentSix;
    }

    public void setAmbAdjustmentSix(String ambAdjustmentSix) {
        this.ambAdjustmentSix = ambAdjustmentSix == null ? null : ambAdjustmentSix.trim();
    }

    public String getAmbRemarkSix() {
        return ambRemarkSix;
    }

    public void setAmbRemarkSix(String ambRemarkSix) {
        this.ambRemarkSix = ambRemarkSix == null ? null : ambRemarkSix.trim();
    }

    public String getSdAdjustmentSix() {
        return sdAdjustmentSix;
    }

    public void setSdAdjustmentSix(String sdAdjustmentSix) {
        this.sdAdjustmentSix = sdAdjustmentSix == null ? null : sdAdjustmentSix.trim();
    }

    public String getSdRemarkSix() {
        return sdRemarkSix;
    }

    public void setSdRemarkSix(String sdRemarkSix) {
        this.sdRemarkSix = sdRemarkSix == null ? null : sdRemarkSix.trim();
    }

    /**
     * Gap 计算
     * @param lastWriteValue
     * @param currentWriteValue
     * @param gapValue
     * @return
     */
    public static String calculateGap(String lastWriteValue, String currentWriteValue, String gapValue) {
        //上次填写值
        BigDecimal a = StringUtils.isEmpty(lastWriteValue) ? BigDecimal.ZERO : new BigDecimal(lastWriteValue);
        //本次填写值
        BigDecimal b = StringUtils.isEmpty(currentWriteValue) ? BigDecimal.ZERO : new BigDecimal(currentWriteValue);
        try {
            if(a.equals(BigDecimal.ZERO) && b.equals(BigDecimal.ZERO)){
                return "0%";
            }
            if(a.equals(BigDecimal.ZERO)){
                return "100%";
            }
            if(a.compareTo(BigDecimal.ZERO)==1){

                BigDecimal percent = (b.subtract(a)).divide(a, 4, BigDecimal.ROUND_DOWN);
                return String.format("%s%s", percent.setScale(0,BigDecimal.ROUND_HALF_UP),"%");
            }
        }catch (Exception ex) {
            log.error("【销售预测】计算Gap值异常，lastWriteOne : " + a + ", currentWriteOne : " + b, ex);
            return gapValue;
        }
        return gapValue;
    }
}