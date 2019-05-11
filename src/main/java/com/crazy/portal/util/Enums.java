package com.crazy.portal.util;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 22:44 2019/4/8
 * @Modified by:
 */
public class Enums {

    /**
     * 用户类型，不同用户需要不同密码强度
     * 一般的，高级的
     */
    public enum USER_TYPE{
        general,advanced
    }

    /**
     * 用户状态：初始化、正常、冻结
     */
    public enum USER_STATUS{
        init(1),normal(2),freeze(0);

        private final int code;

        USER_STATUS(int code){
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    /**
     * 资源类型: 菜单、按钮、接口api
     */
    public enum RESOURCE_TYPE{
        menu,button,api
    }
}
