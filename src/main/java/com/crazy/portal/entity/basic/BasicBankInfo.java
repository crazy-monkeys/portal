package com.crazy.portal.entity.basic;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class BasicBankInfo implements BaseEntity{
    private Integer id;

    private Integer custId;

    private String bankName;

    private String bankAccount;

    private String bankBic;

    private String bankCountry;

    private String bankProvince;

    private String bankCity;

    private String bankDistrict;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer createUser;
    @JsonIgnore
    @JSONField(serialize = false)
    private Date createTime;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer updateUser;
    @JsonIgnore
    @JSONField(serialize = false)
    private Date updateTime;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer active;

    private String bankDetailInfo;

    private String bankAddress;
}