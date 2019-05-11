package com.crazy.portal.entity.system;

import lombok.Data;

import java.util.Date;

@Data
public class UserRoleDO {
    private Integer id;

    private Integer userId;

    private Integer roleId;

    private Date createTime;

    private Integer createId;

    private Date updateTime;

    private Integer updateId;
}