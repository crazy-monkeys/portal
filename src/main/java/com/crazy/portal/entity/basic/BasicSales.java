package com.crazy.portal.entity.basic;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class BasicSales implements BaseEntity{
    private Integer id;
    //客户ID
    private Integer custId;
    //销售组织
    private String salesOrganize;
    //分销渠道
    private String distributionChannel;
    //产品组
    private String productGroup;
    //货币
    private String currency;
    //装运条件
    private String shippingConditions;
    //交货工厂
    private String deliveryPlant;
    //最大部分交货
    private String maxPartialDelivery;
    //内部客户
    private String internalCustomers;
    //账户分配组
    private String accountGroup;
    //客户组
    private String custGroup;
    //税分类
    private String taxClassification;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer active;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer createUser;
    @JsonIgnore
    @JSONField(serialize = false)
    private Date createTime;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer modifyUser;
    @JsonIgnore
    @JSONField(serialize = false)
    private Date modifyTime;

}