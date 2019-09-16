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
     * 
     */
    private String visitDate;

    /**
     * 
     */
    private String customerLocation;

    /**
     * 
     */
    private String customerCode;

    /**
     * 
     */
    private Integer visitNumber;

    /**
     * 
     */
    private String visitPurpose;

    /**
     * 
     */
    private String projectName;

    /**
     * 
     */
    private String projectStatus;

    /**
     * 
     */
    private String projectDepartment;

    /**
     * 
     */
    private String talkContent;

    /**
     * 
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