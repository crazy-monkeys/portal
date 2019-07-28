package com.crazy.portal.entity.basic;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 联系人
 */
@Data
public class BasicContact implements BaseEntity{
    private Integer id;

    /**
     * 客户id
     */
    private Integer custId;

    /**
     * 是否主要 1-是 0-否
     */
    private Integer isDefault;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 联系人手机号
     */
    private String contactMobile;

    /**
     * 联系人邮箱
     */
    private String contactEmail;

    /**
     * 部门
     */
    private String contactDepartment;

    /**
     * 职位
     */
    private String contactPosition;

    /**
     * 是否主要决策人 1-是 0-否
     */
    private Integer isDecisionMaker;

    /**
     * 股权占比
     */
    private BigDecimal equityRatio;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer createUser;
    @JsonIgnore
    @JSONField(serialize = false)
    private Date createTime;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer updateUser;
    @JsonIgnore
    @JSONField(serialize = false)
    private Date updateTime;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer active;
}