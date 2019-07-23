package com.crazy.portal.entity.basic;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TBasicContactDO {
    private Integer id;

    /**
     * 客户id
     */
    private Integer custId;

    /**
     * 是否主要
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

    private Integer createUser;

    private Date createTime;

    private Integer updateUser;

    private Date updateTime;

    private Integer active;
}