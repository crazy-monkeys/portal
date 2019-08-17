package com.crazy.portal.entity.business.rebate;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class BusinessRebate {
    private Integer id;

    private String dealerName;

    private String customerName;

    private BigDecimal rebateAmount;

    private Date noticeDate;

    private String executor;

    private BigDecimal releaseAmount;

    private BigDecimal surplusRebateAmount;

    private String executeStyle;

    private String remark;

    private Integer status;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer active;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer createId;
    @JsonIgnore
    @JSONField(serialize = false)
    private Date createTime;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer updateId;
    @JsonIgnore
    @JSONField(serialize = false)
    private Date updateTime;


    List<BusinessAccountDetail> accountDetailList;

    List<BusinessStrategy> strategyList;

    BusinessRebateFile file;
}