package com.crazy.portal.entity.basic;

import com.alibaba.fastjson.annotation.JSONField;
import com.crazy.portal.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class BasicBankInfo implements BaseEntity {
    private Integer id;

    private Integer custId;
    //银行名
    private String bankName;
    //银行账户
    private String bankAccount;
    //银行识别码
    private String bankBic;
    //银行号国家
    private String bankCountry;
    //银行省份
    private String bankProvince;
    //银行城市
    private String bankCity;
    //银行地区
    private String bankDistrict;
    //银行详细地址
    private String bankDetailInfo;
    //银行地址
    private String bankAddress;

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


}