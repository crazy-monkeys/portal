package com.crazy.portal.entity.customer;

import lombok.Data;

import java.util.Date;

@Data
public class CustBankInfo {
    private Long id;

    private String bankName;

    private String bankAddress;

    private String account;

    private String identifier;

    private Long createId;

    private Date createTime;

    private Long modifyId;

    private Date modifyTime;


}