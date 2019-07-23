package com.crazy.portal.entity.basic;

import lombok.Data;

import java.util.Date;

/**
 * 银行信息
 */
@Data
public class TBasicBankInfoDO {
    private Integer id;

    /**
     * 客户id
     */
    private Integer custId;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 银行账号
     */
    private String bankAccount;

    /**
     * 识别码
     */
    private String bankBic;

    /**
     * 银行地址
     */
    private String address;

    private Integer createUser;

    private Date createTime;

    private Integer updateUser;

    private Date updateTime;

    private Integer active;
}