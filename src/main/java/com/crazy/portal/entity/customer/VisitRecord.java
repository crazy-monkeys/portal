package com.crazy.portal.entity.customer;

import lombok.Data;

import java.util.Date;

@Data
public class VisitRecord {
    private Integer id;

    private Integer custId;

    private Date visitDate;

    private String customerLocation;

    private String customerName;

    private Integer visitNumber;

    private String visitPurpose;

    private String projectName;

    private String projectStatus;

    private String projectDepartment;

    private String talkContent;

    private String followPlan;

    private String claimDescription;

    private String participantsZr;

    private String participantsCt;

    private String participantsDl;

    private String errorMessage;

    private Integer active;

    private Integer createUserId;

    private Date createTime;

    private Integer updateUserId;

    private Date updateTime;

}