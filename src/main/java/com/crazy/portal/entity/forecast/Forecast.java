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

    private String operationRemark;

    private String agencyAbbreviation;
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

    //代表处
    private String representative;

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
     * 状态 -1：驳回，1：新增待处理 2：已提交 3：删除待处理 4：修改待处理
     */
    private Integer status;

    /**
     *  代理商操作状态：-1:删除 1:新增 2:修改
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

    private ForecastLine line;

    private String statusDesc;

    private String createTimeStr;

    private String poPrice;

    private String lastMonth;

    public Forecast() {
    }

    public Forecast(Integer createUserId) {
        this.createUserId = createUserId;
        this.createTime = new Date();

    }

}