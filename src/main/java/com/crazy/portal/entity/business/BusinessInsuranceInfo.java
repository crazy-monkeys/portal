package com.crazy.portal.entity.business;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class BusinessInsuranceInfo implements IdrBaseEntity{
    private Integer id;

    private Integer idrInfoId;

    private String customerName;

    private String adjustDate;

    private String receiveGoodsDate;

    private String bu;

    private String pdt;

    private String productType;

    private String platform;

    private String productModel;

    private String num;

    private String price;

    private String currency;

    private String newPrice;

    private String insuranceAmount;

    private String modifyDate;

    private String remark;

    @JsonIgnore
    @JSONField(serialize = false)
    private Integer createId;
    @JsonIgnore
    @JSONField(serialize = false)
    private Date createTime;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer updateId;
    @JsonIgnore
    @JSONField(serialize = false)
    private Date updateTime;



}