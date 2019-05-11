package com.crazy.portal.entity.company;

import lombok.Data;

import java.util.Date;

@Data
public class TSolutionInfoVO {
    private Integer id;

    private Integer companyId;

    private String oneIndustry;

    private String twoIndustry;

    private String recommendedUser;

    private Date createTime;

    private Integer createUserId;

    private Date updateTime;

    private Integer updateUserId;

    private Short active;
}