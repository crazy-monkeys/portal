package com.crazy.portal.entity.cusotmer;

import lombok.Data;

import java.util.Date;

/**
 * 
 * @author jingang
 * @date   2020-03-04
 */
@Data
public class AgentManExportVisitRecord {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer custId;


    /**
     * 拜访日期
     */
    private String visitDate;

    /**
     * 客户地址
     */
    private String customerLocation;

    /**
     * 客户编号
     */
    private String customerCode;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 拜访次数
     */
    private Integer visitNumber;

    /**
     * 拜访目的
     */
    private String visitPurpose;

    /**
     * 项目名
     */
    private String projectName;

    /**
     * 项目状态
     */
    private String projectStatus;


    /**
     * 项目部门
     */
    private String projectDepartment;

    /**
     * 会谈内容
     */
    private String talkContent;

    /**
     * 行动计划
     */
    private String followPlan;

    /**
     * 诉求描述
     */
    private String claimDescription;
    /**
     * 
     */
    private String participantsZr;

    /**
     * 
     */
    private String participantsCt;

    /**
     * 
     */
    private String participantsDl;

    /**
     * 
     */
    private String errorMessage;

    /**
     * 
     */
    private Integer active;

    /**
     * 
     */
    private Integer createUserId;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Integer updateUserId;

    /**
     * 
     */
    private Date updateTime;

    private String c4cId;

    /**
     * 代理商名称
     */
    private String agentName;
    /**
     * 代理商名称简称
     */
    private String agentAbbreviate;
    /**
     * 发生年周
     */
   // private String visitWeek;
    /**
     * 客户类别
     */
    private String businessType;
    /**
     * 销售ID
     */
    private String salesId;
    /**
     * 销售名称
     */
    private String salesName;
    /**
     * 阿米巴战队名称
     */
    private String ambTeamName;
    /**
     * 阿米巴队长ID
     */
    private String ambTeamLeaderSn;
    /**
     * 阿米巴队长
     */
    private String ambTeamLeaderName;
    /**
     * 项目背景和市场信息
     */
    //private String backInformation;
    /**
     *展锐接口回复
     */
    //private String unInterfaceReply;
}