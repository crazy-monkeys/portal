package com.crazy.portal.entity.customer;

import lombok.Data;

import java.util.Date;

/**
 * 销售团队
 */
@Data
public class CustSalesTeam {

    private Long id;
    //角色类型
    private Short roleId;
    //名称
    private String name;
    //手机号
    private String mobile;

    private Long createId;

    private Date createTime;

    private Long modifyId;

    private Date modifyTime;
}
