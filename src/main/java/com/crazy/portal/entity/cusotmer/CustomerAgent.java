package com.crazy.portal.entity.cusotmer;

import lombok.Data;

import java.util.Date;

/**
 * dealer:代理商分级分类信息
 * @author weiying
 * @date   2019-08-20 13:26::47
 */
@Data
public class CustomerAgent {
    /**
     * 
     */
    private Integer agentId;

    /**
     * 
     */
    private Integer reportId;

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
    private Date startDate;

    /**
     * 
     */
    private Date endDate;

    /**
     * 
     */
    private Date insertTime;

    /**
     * 
     */
    private Integer active;
}