package com.crazy.portal.entity.basic;

import lombok.Data;

import java.util.Date;

@Data
public class TBasicSalesTeamDO implements BaseEntity{
    private Integer id;
    //客户ID
    private Integer custId;
    //角色ID
    private Integer roleId;
    //名称
    private String name;
    //手机号
    private String mobile;

    private Integer active;

    private Integer createUserId;

    private Date createTime;

    private Integer modifyUserId;

    private Date modifyTime;

}