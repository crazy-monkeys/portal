package com.crazy.portal.entity.business.rebate;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BusinessRebateItem {
    private Integer id;

    private Integer rebateId;

    private String dealerName;

    private String customerName;

    private BigDecimal rebateAmount;

    private String executor;

    private String executeStyle;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date noticeDate;

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

}