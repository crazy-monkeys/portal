package com.crazy.portal.entity.cusotmer;

import lombok.Data;

import java.util.Date;

/**
 * 
 * @author weiying
 * @date   2019-08-26 20:53::16
 */
@Data
public class VisitRecord {
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
     * 
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
}