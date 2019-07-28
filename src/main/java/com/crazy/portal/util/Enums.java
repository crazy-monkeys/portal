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

    public enum SYSTEM_MANAGER_MODEL{
        sys_announcement(1),   //公告
        sys_customer(2);       //客户

        private final int model;

        SYSTEM_MANAGER_MODEL(int model){
            this.model = model;
        }

        public int getModel() {
            return model;
        }
    }

    public enum SYSTEM_MANAGER_TYPE{
        type_mail(1),           //邮件通知
        type_file_type(2),      //附件
        type_status(3);         //状态
        private final int model;

        SYSTEM_MANAGER_TYPE(int model){
            this.model = model;
        }

        public int getModel() {
            return model;
        }
    }


    /**
     * 系统参数维护
     */
    public enum SYSTEM_MANAGER{
        sys_file(1,1,"附件类型","");

        private final int model;
        private final int function;
        private final String zhCode;
        private final String enCode;

        SYSTEM_MANAGER(int model, int function, String zhCode, String enCode){
            this.model = model;
            this.function = function;
            this.zhCode = zhCode;
            this.enCode = enCode;
        }

        public int getModel() {
            return model;
        }

        public int getFunction() {
            return function;
        }

        public String getZhCode() {
            return zhCode;
        }

        public String getEnCode() {
            return enCode;
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
        mass_market(1),
        account_market(2);

        private final int code;
        CUSTOMER_BUSINESS_TYPE(int code){
            this.code = code;
        }
        public int getCode(){
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
