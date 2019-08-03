package com.crazy.portal.bean.system;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Desc:子账号基本信息
 * @Author: Bill
 * @Date: created in 21:38 2019-07-09
 * @Modified by:
 */
@Data
public class SubAgentVO {

    /**
     * 客户名称
     */
    @NotNull(message = "客户名称不能为空")
    private String customerName;

    /**
     * 子账号登录名
     */
    @NotNull(message = "登录名称不能为空")
    private String loginName;

    /**
     * 邮箱
     */
    @NotNull(message = "邮箱不能为空")
    private String mail;


    /**
     * 角色编码
     */
    @NotNull(message = "角色不能为空")
    private String roleCode;
}
