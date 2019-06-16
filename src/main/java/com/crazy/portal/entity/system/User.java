package com.crazy.portal.entity.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private String firstName;

    private Date lastLoginTime;
    @JsonIgnore
    private String lastName;

    private String loginName;

    @JsonIgnore
    private String loginPwd;

    @JsonIgnore
    private String phone;

    private Date pwdInvalidTime;

    @JsonIgnore
    private String realName;
    @JsonIgnore
    private Date regTime;

    /** 正常1 冻结0**/
    private Integer userStatus;

    /**1.代理商 2.直供客户 3.销售客户**/
    private Integer userType;

    @JsonIgnore
    private Date updateTime;

    @JsonIgnore
    private Integer updateUserId;
    @JsonIgnore
    private Date createTime;

    @JsonIgnore
    private Integer createUserId;

    /** ext **/
    private List<Role> roles;
}