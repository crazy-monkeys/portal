package com.crazy.portal.entity.customer;

import com.alibaba.fastjson.annotation.JSONField;
import com.crazy.portal.entity.basic.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 客户信息表
 */
@Data
public class CustomerInfo {
    private Integer id;

    /**
     * 客户类型： Enums.CUSTOMER_TYPE
     */
    private Integer custType;

    /**
     * 客户角色 1-内部客户 2-外部客户 3-潜在客户
     */
    private Integer custRole;

    /**
     * 内部客户code
     */
    private String custInCode;

    /**
     * 外部客户code
     */
    private String custOutCode;

    /**
     * 是否License客户 1-是 0-否
     */
    private Short isLicense;

    /**
     * 是否潜在客户
     */
    private Short isPotential;
    /**
     * 业务类型  1-massMarket 2-accountMarket
     */
    private Short businessType;
    /**
     * 客户名
     */
    private String custName;

    /**
     * 客户简称
     */
    private String custAbbreviation;

    /**
     * 手机号
     */
    private String custMobile;

    /**
     * 座机
     */
    private String custPhoneNo;

    /**
     * 传真
     */
    private String custFax;

    /**
     * 邮箱
     */
    private String custEmail;

    /**
     * 网站
     */
    private String custWeb;

    /**
     * 公司资产
     */
    private BigDecimal corporateAssets;

    /**
     * 公司人数
     */
    private Integer corporateNumber;

    /**
     * 注册时间
     */
    @JsonIgnore
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date registerTime;


    private String registerTimeStr;
    /**
     * 母公司
     */
    private String corporateParents;


    /**
     * 授信额度初始值
     */
    private BigDecimal creditInitalValue;

    /**
     * 授信额度占用值
     */
    private BigDecimal creditEngagedValue;

    /**
     * 授信额度剩余值
     */
    private BigDecimal creditSurplus;

    /**
     *是否白名单 1-是 0-否
     */
    private Integer isWhiteList;

    /**
     *优势价值
     */
    private String advantageValue;

    /**
     * 优势介绍
     */
    private String advantageIntroduction;

    /**
     * 业务介绍
     */
    private String businessIntroduction;

    /**
     * 用户状态 1-生效 0-冻结
     */
    private Integer dealerStatus;

    /**
     * 客户状态 1-待提交 2-待审批 3-正常 4-被驳回
     */
    private Integer customerStatus;

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

    /**
     * 银行账号信息
     */
    private BasicBankInfo basicBank;

    /**
     * 地址信息
     */
    private List<BasicAddress> basicAddress;

    /**
     * 分级分类信息
     */
    private List<BasicLable> basicLables;

    /**
     * 附件信息
     */
    private List<BasicFile> basicFile;

    /**
     *联系人信息
     */
    private List<BasicContact> basicContact;

    /**
     * 公司关系
     */
    private List<BasicCorporateRelationship> basicShip;

    /**
     * 层次结构
     */
    private List<BasicCorporateStructure> basicStructure;

    /**
     * 开票信息
     */
    private List<BasicInvoiceInfo> basicInvoice;

    /**
     * 销售团队
     */
    private List<BasicSalesTeam> salesTeam;

    /**
     * 销售数据
     */
    private List<BasicSales> sales;
}