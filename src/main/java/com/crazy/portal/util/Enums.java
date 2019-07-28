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
     * 用户类型
     * agent.代理商
     * subAgent.子代理商
     * internal.内部用户(展锐)
     */
    public enum USER_TYPE{
        agent,subAgent,internal
    }

    /**
     * 用户状态：初始化、正常、冻结
     */
    public enum USER_STATUS{
        normal(1),freeze(0);

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

    //<editor-fold desc="客户枚举">
    /**
     * 用户类型
     */
    public enum CUSTOMER_TYPE{
        dealer(1),   //代理商
        customer(2); //客户

        private final int code;
        CUSTOMER_TYPE(int code){
            this.code = code;
        }
        public int getCode(){
            return code;
        }
    }

    /**
     * 客户业务类型
     */
    public enum CUSTOMER_BUSINESS_TYPE{
        mass_market((short)1),
        account_market((short)2);

        private final Short code;
        CUSTOMER_BUSINESS_TYPE(Short code){
            this.code = code;
        }
        public Short getCode(){
            return code;
        }
    }

    /**
     * 客户角色
     */
    public enum CUSTOMER_ROLE{
        potential_customer(0),      //潜在客户
        in_customer(1),             //内部客户
        out_customer(2);            //外部客户

        private final int code;
        CUSTOMER_ROLE(int code){
            this.code = code;
        }
        public int getCode(){
            return code;
        }
    }

    /**
     * 客户状态
     */
    public enum CustomerStatus{

        WAIT_SUBMIT(1, "待提交"),
        WAIT_APPROVAL(2, "待审批"),
        NORMAL(3, "正常"),
        REJECT(4, "被驳回");

        private Integer code;
        private String desc;
        CustomerStatus(Integer code, String desc){
            this.code = code;
            this.desc = desc;
        }
        public Integer getCode(){
            return code;
        }
        public String getDesc(){
            return desc;
        }
    }
    //</editor-fold>
}
