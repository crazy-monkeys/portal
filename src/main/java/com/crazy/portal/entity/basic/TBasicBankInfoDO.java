package com.crazy.portal.entity.basic;

import lombok.Data;

import java.util.Date;

@Data
public class TBasicBankInfoDO {
    private Integer id;

    private Integer custId;

    private String bankName;

    private String bankAccount;

    private String bankBic;

    private String bankCountry;

    private String bankProvince;

    private String bankCity;

    private String bankDistrict;

    private Integer createUser;

    private Date createTime;

    private Integer updateUser;

    private Date updateTime;

    private Integer active;

    private String bankDetailInfo;

    private String bankAddress;
}