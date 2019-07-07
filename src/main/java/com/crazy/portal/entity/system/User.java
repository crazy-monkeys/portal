package com.crazy.portal.entity.system;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class User {
    private Integer id;

    /**1正常 0逻辑删除**/
    private Short active;

    private String country;

    private String email;
    @JSONField(serialize=false)
    private String firstName;

    private Date lastLoginTime;
    @JSONField(serialize=false)
    private String lastName;

    private String loginName;

    @JSONField(serialize=false)
    private String loginPwd;

    @JSONField(serialize=false)
    private String phone;

    private Date pwdInvalidTime;

    @JSONField(serialize=false)
    private String realName;
    @JSONField(serialize=false)
    private Date regTime;

    /** 正常1 冻结0**/
    private Integer userStatus;

    /**1.代理商 2.直供客户 3.销售客户**/
    private Integer userType;

    @JSONField(serialize=false)
    private Date updateTime;

    @JSONField(serialize=false)
    private Integer updateUserId;
    @JSONField(serialize=false)
    private Date createTime;

    @JSONField(serialize=false)
    private Integer createUserId;

    /** ext **/
    private List<Role> roles;
}