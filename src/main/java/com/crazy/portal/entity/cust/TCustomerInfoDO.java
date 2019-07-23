package com.crazy.portal.entity.cust;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

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
     * 用户状态 1-生效 0-冻结
     */
    private Integer dealerStatus;

    private Integer createUser;

    private Date createTime;

    private Integer updateUser;

    private Date updateTime;

    private Integer active;
}