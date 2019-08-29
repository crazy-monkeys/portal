package com.crazy.portal.entity.cusotmer;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 客户主信息表
 * @author weiying
 * @date   2019-08-29 16:28::04
 */
@Data
public class CustomerInfo {
    /**
     * 
     */
    private Integer id;

    /**
     * 用户类型 0-潜在客户 1-未报备 2-已报备
     */
    private Integer custType;

    /**
     * 内部客户号
     */
    private String inCode;

    /**
     * 外部客户号
     */
    private String outCode;

    /**
     * 客户名称
     */
    private String custName;

    /**
     * 英文名
     */
    private String custEnName;

    /**
     * 简称
     */
    private String custAbbreviation;

    /**
     * 销售支持冻结 0 否 1 是
     */
    private Integer frozenSales;

    /**
     * 交货冻结 0 否 1 是
     */
    private Integer frozenDelivery;

    /**
     * 订单冻结 0 否 1 是
     */
    private Integer frozenOrder;

    /**
     * 开票冻结 0 否 1 是
     */
    private Integer frozenInvoice;

    /**
     * A01 - Account Market（直供）；A02 - Account Market（非直供）；A03 - Mass Market；A04 - 代理商
     */
    private String businessType;

    /**
     * 是否License(customer)
     */
    private Integer isLicense;

    /**
     * Z001 - 中国客户(Chinese Account); Z002 - 亚太客户(Asia-Pacific Account); Z003 - 北美客户(North American Account); Z010 - 内部客户(Customer); 
     */
    private String custRole;

    /**
     * 电话
     */
    private String custMobile;

    /**
     * 邮箱
     */
    private String custEmail;

    /**
     * 网址
     */
    private String custWeb;

    /**
     * 是否白名单(dealer)
     */
    private Integer isWhite;

    /**
     * 注册时间
     */
    private Date registTime;

    /**
     * 公司资产(customer)
     */
    private BigDecimal corportaeAssets;

    /**
     * 员工人数
     */
    private Integer staffNumber;

    /**
     * 研发人数(customer)
     */
    private Integer developersNumber;

    /**
     * 业务介绍(customer)
     */
    private String businessIntroduction;

    /**
     * 优势介绍(dealer)
     */
    private String advantagesIntroduction;

    /**
     * 审批人
     */
    private Integer approveUser;

    /**
     * 审批状态
     */
    private Integer approveStatus;

    /**
     * 
     */
    private Date approveTime;

    /**
     * 审批意见
     */
    private String approveRemark;

    /**
     * 
     */
    private Integer active;

    /**
     * 
     */
    private Integer createUser;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Integer updateUser;

    /**
     * 
     */
    private Date updateTime;


    /*资产信息 季度更新 dealer*/
    private List<CustAssetsInformation> assetsInformations;

    /*业务介绍 年度更新 dealer*/
    private List<CustBusinessInformation> businessInformations;

    /*分级分类信息 dealer*/
    private List<CustomerAgent> customerAgents;

    /*联系人信息*/
    private List<CustomerContact> customerContacts;

    /*产品信息*/
    private List<CustomerProduct> customerProducts;

    /*客户附件*/
    private List<CustomerFile> files;

    /*银行账号信息*/
    private CustBankInfo custBankInfo;

    /**关系**/
    private List<CustCorporateRelationship> relationships;

    /**开票信息**/
    private List<CustInvoiceInfo> invoiceInfos;

    /**销售信息**/
    private List<CustSales> sales;

    /**地址信息**/
    private List<CustomerAddress> addresses;

    /*客户团队*/
    private List<CustomerAccountTeam> accountTeams;


    //授信额度初始值
    private BigDecimal credit;
    //授信额度占用值
    private BigDecimal creditUSE;
    //授信额度剩余值
    private BigDecimal creditUnUSE;
}