package com.crazy.portal.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 22:44 2019/4/8
 * @Modified by:
 */
public class Enums {

    /**
     * 用户类型，不同用户需要不同密码强度
     * 1.代理商 2.直供客户 3.销售客户
     */
    public enum USER_TYPE{
        agent(1),straight(2),sales(3);

        private final int code;

        USER_TYPE(int code){
            this.code = code;
        }

        public int getCode() {
            return code;
        }
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
    public enum RESOURCE_TYPE_ENUM{
        MENU(1),BUTTON(2),API(3);

        private final int type;

        RESOURCE_TYPE_ENUM(int type){
            this.type = type;
        }
        public int getType() {
            return type;
        }

        public static List<Integer> getResourceTypes(){
            List<Integer> types = new ArrayList<>();
            for(RESOURCE_TYPE_ENUM type : RESOURCE_TYPE_ENUM.values()){
                types.add(type.getType());
            }
            return types;
        }
    }
}
