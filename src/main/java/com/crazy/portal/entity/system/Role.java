package com.crazy.portal.entity.system;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

@Data
public class Role {
    private Integer id;

    @JSONField(serialize=false)
    private Short active;
    @JSONField(serialize=false)
    private Date createTime;
    @JSONField(serialize=false)
    private Integer createUserId;

    private String roleCode;
    private String roleName;
    @JSONField(serialize=false)
    private Date updateTime;
    @JSONField(serialize=false)
    private Integer updateUserId;
}