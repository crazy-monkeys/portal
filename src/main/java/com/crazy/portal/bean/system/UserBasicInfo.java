package com.crazy.portal.bean.system;

import lombok.Data;

/**
 * @Desc:子账号基本信息
 * @Author: Bill
 * @Date: created in 21:38 2019-07-09
 * @Modified by:
 */
@Data
public class UserBasicInfo {

    /**
     * 子账号登录名
     */
    private String loginName;
    /**
     * 父账号登陆名
     */
    private String parentLoginName;
}
