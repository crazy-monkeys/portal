package com.crazy.portal.entity.customer;

import lombok.Data;

import java.util.Date;

@Data
public class DealerReport {
    private Integer id;

    private Integer dealerId;

    private Integer custId;

    private Integer dealerStatus;

    private Date startDate;

    private Date endDate;

    private Integer approver;

    private Date approverDate;

    private String approverRemark;

    private Integer active;

    private Integer createUserId;

    private Date createTime;

    private Integer updateUserId;

    private Date updateTime;

}