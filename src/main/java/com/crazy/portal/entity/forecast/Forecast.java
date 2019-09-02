package com.crazy.portal.entity.forecast;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author lee
 * @date   2019-08-31 17:17::16
 */

@Data
public class Forecast {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String company;

    /**
     * 
     */
    private String operationYearMonth;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 客户简称
     */
    private String customerAbbreviation;

    /**
     * 预测类别
     */
    private String forecastType;

    /**
     * 虚拟料号
     */
    private String vmNumber;

    /**
     * 版本要求
     */
    private String version;

    /**
     * 客户专货库存截止日期
     */
    private String closeDate;

    /**
     * 客户未完成的所有专货库存
     */
    private String delayStock;

    /**
     * 
     */
    private String customerType;

    /**
     * 
     */
    private String channel;

    /**
     * 
     */
    private String ambLeader;

    /**
     * 
     */
    private String salePeople;

    /**
     * 
     */
    private String sdPeople;

    /**
     * 
     */
    private String bu;

    /**
     * 
     */
    private String pdt;

    /**
     * 
     */
    private String productType;

    /**
     * 
     */
    private String platform;

    /**
     * 
     */
    private String productModel;

    /**
     * 
     */
    private Integer status;

    /**
     * 
     */
    private Integer agencyStatusType;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Integer createUserId;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     */
    private Integer updateUserId;

    /**
     * 
     */
    private Date operationTime;

    /**
     * 
     */
    private Integer operationUserId;

    private String errorMsg;

    private String biId;

    private String batchNo;

    private List<ForecastLine> lines;

}