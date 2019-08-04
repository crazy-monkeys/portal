package com.crazy.portal.entity.customer;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class VisitRecord {
    private Integer id;

    private Integer custId;
    //访问时间
    private String visitDate;
    //客户地址
    private String customerLocation;
    //客户编号
    private String customerCode;
    //访问次数
    private Integer visitNumber;
    //访问目的
    private String visitPurpose;
    //项目名称
    private String projectName;
    //项目状态
    private String projectStatus;
    //项目所属展锐事业部
    private String projectDepartment;
    //访问内容
    private String talkContent;
    //后续行动计划
    private String followPlan;
    //诉求描述
    private String claimDescription;
    //参与人员：展锐
    private String participantsZr;
    //参与人员：客户
    private String participantsCt;
    //参与人员：代理
    private String participantsDl;
    //错误信息
    private String errorMessage;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer active;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer createUserId;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer updateUserId;
    @JsonIgnore
    @JSONField(serialize = false)
    private Date updateTime;

}