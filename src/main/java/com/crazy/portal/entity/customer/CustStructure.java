package com.crazy.portal.entity.customer;

import lombok.Data;

import java.util.Date;

/**
 * 客户层次结构
 */
@Data
public class CustStructure {

    private Long id;
    //名称
    private String name;
    //地址
    private String address;
    //主要联系人
    private String primaryContact;

    private Long createId;

    private Date createTime;

    private Long modifyId;

    private Date modifyTime;
}
