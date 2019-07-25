package com.crazy.portal.entity.customer;

import lombok.Data;

import java.util.Date;

/**
 * 客户基础信息
 */
@Data
public class CustBasicInfo {
    private Long id;
    //客户名称
    private String customerName;
    //客户类型
    private Integer customerType;
    //是否License客户
    private Short isLicense;
    //销售ID
    private Integer salesId;
    //角色
    private Integer roleId;
    //公司资产
    private String companyAssets;
    //员工人数
    private Integer workersNumber;
    //传真
    private String fax;
    //邮箱
    private String email;
    //公司网站
    private String companyWebsite;
    //母公司
    private String parentCompany;
    //公司总机
    private String companySwitchboard;
    //注册时间
    private Date registerTime;
    //注册地址-省份
    private String registerProvince;
    //注册地址-城市
    private String registerCity;
    //注册地址-地区
    private String registerDistrict;
    //注册地址-详细地址
    private String registerAddress;
    //办公地址-省份
    private String officeProvince;
    //办公地址-城市
    private String officeCity;
    //办公地址-地区
    private String officeDistrict;
    //办公地址-详细地址
    private String officeAddress;
    //业务介绍
    private String businessIntroduction;
    //状态
    private Short status;

    private Long createId;

    private Date crateTime;

    private Long modifyId;

    private Date modifyTime;


}