package com.crazy.portal.entity.customer;

import lombok.Data;

import java.util.Date;

/**
 * 开票信息
 */
@Data
public class CustInvoiceInfo {
    private Long id;
    //购货单位
    private String purchaser;
    //收货地址
    private String receivingAddress;
    //收货人手机号
    private String receivingPhone;
    //纳税人登记号
    private String taxpayerRegisNumber;
    //币种
    private String currency;
    //银行名称
    private String bankName;
    //银行地址
    private String bankAddress;
    //银行账号
    private String account;
    //银行识别码
    private String identifier;

    private Long createId;

    private Date createTime;

    private Long modifyId;

    private Date modifyTime;


}