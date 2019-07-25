package com.crazy.portal.entity.customer;

import lombok.Data;

import java.util.Date;

/***
 * 销售数据
 */
@Data
public class CustSales {

    private Long id;
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

    private Long createId;

    private Date createTime;

    private Long modifyId;

    private Date modifyTime;
}
