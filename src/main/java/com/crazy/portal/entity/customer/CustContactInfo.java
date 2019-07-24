package com.crazy.portal.entity.customer;

import lombok.Data;

import java.util.Date;

@Data
public class CustContactInfo {
    private Long id;

    private String department;

    private String name;

    private String position;

    private String phone;

    private String email;

    private String remark;

    private Long createId;

    private Date createTime;

    private Long modifyId;

    private Date modifyTime;

}