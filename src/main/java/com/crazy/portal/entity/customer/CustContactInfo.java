package com.crazy.portal.entity.customer;

import lombok.Data;

import java.util.Date;

/**
 * 联系人信息
 */
@Data
public class CustContactInfo {
    private Long id;
    //部门
    private String department;
    //姓名
    private String name;
    //联系人类型
    private Short contactType;
    //职位
    private String position;
    //联系方式
    private String phone;
    //邮箱
    private String email;
    //是否关键决策人
    private Short isKeyDecisionMakers;
    //股东占比
    private Double shareholderProportion;

    private Long createId;

    private Date createTime;

    private Long modifyId;

    private Date modifyTime;

}