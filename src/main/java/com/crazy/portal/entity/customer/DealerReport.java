package com.crazy.portal.entity.customer;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

/**
 * 代理商报备
 */
@Data
public class DealerReport {
    private Integer id;
    //代理商ID
    private Integer dealerId;
    //客户ID
    private Integer custId;
    //代理商状态
    private Integer dealerStatus;
    //报备开始时间
    private Date startDate;
    //报备结束时间
    private Date endDate;
    //审批人
    private Integer approver;
    //审批时间
    private Date approverDate;
    //审批备注
    private String approverRemark;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer active;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer createUserId;
    @JsonIgnore
    @JSONField(serialize = false)
    private Date createTime;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer updateUserId;
    @JsonIgnore
    @JSONField(serialize = false)
    private Date updateTime;

}