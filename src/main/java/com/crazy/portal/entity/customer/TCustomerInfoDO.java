package com.crazy.portal.entity.customer;

import com.crazy.portal.entity.basic.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 客户信息表
 */
@Data
public class TCustomerInfoDO {
    private Integer id;

    /**
     * 客户类型：1-代理商 2-终端客户 3-直供客户
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
     * 业务类型  1-massMarket 2-accountMarket
     */
    private Short businessType;
    /**
     * 客户中文名
     */
    private String custZhName;

    /**
     * 客户英文名
     */
    private String custEnName;

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
    private Date registerTime;

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
     * 客户状态 1-待提交 2-待审批 3-正常
     */
    private Integer customerStatus;

    private Integer createUser;

    private Date createTime;

    private Integer updateUser;

    private Date updateTime;

    private Integer active;

    /**
     * 银行账号信息
     */
    private TBasicBankInfoDO basicBank;

    /**
     * 地址信息
     */
    private List<TBasicAddressDO> basicAddress;

    /**
     * 分级分类信息
     */
    private List<TBasicLableDO> basicLables;

    /**
     * 附件信息
     */
    private List<TBasicFileDO> basicFile;

    /**
     *联系人信息
     */
    private List<TBasicContactDO> basicContact;

    /**
     * 公司关系
     */
    private List<TBasicCorporateRelationshipDO> basicShip;

    /**
     * 层次结构
     */
    private List<TBasicCorporateStructureDO> basicStructure;

    /**
     * 开票信息
     */
    private List<TBasicInvoiceInfoDO> basicInvoice;

    /**
     * 销售团队
     */
    private List<TBasicSalesTeamDO> salesTeam;

    /**
     * 销售数据
     */
    private List<TBasicSalesDO> sales;
}