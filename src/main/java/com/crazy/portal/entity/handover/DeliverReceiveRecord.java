package com.crazy.portal.entity.handover;

import lombok.Data;

import java.util.Date;

@Data
public class DeliverReceiveRecord {
    private Integer id;

    private Integer dealerId;

    private String dealerName;

    private Integer type;

    private Date uploadTime;

    private Integer status;

    private Date createTime;

    private Integer approvalUserId;

    private Date approvalTime;

    private String remark;

    private String typeDesc;

    private String statusDesc;

    private String uploadTimeStr;

}