package com.crazy.portal.entity.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;

    private Short active;

    private String country;

    private String email;

    private String firstName;

    private Date lastLoginTime;

    private String lastName;

    private String loginName;

    @JsonIgnore
    private String loginPwd;

    private String phone;

    private Date pwdInvalidTime;

    private String realName;

    private Date regTime;

    /** 正常1 冻结0**/
    private Integer userStatus;

    private Integer userType;

    @JsonIgnore
    private Date updateTime;

    @JsonIgnore
    private Integer updateUserId;
    @JsonIgnore
    private Date createTime;

    @JsonIgnore
    private Integer createUserId;
}