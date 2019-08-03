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


    public enum MAIL_TEMPLATE {
        USER_CREATE("createSubAgent"),
        RESET_PWD("resetPwdTemplate");

        private String templateName;

        MAIL_TEMPLATE(String templateName){
            this.templateName = templateName;
        }

        public String getTemplateName() {
            return templateName;
        }
    }

    /**
     * 角色类型
     */
    public enum ROLE_TYPE {
        USER(1),
        SUB_USER(2);

        private int roleType;

        ROLE_TYPE(Integer roleType){
            this.roleType = roleType;
        }

        public int getRoleType() {
            return roleType;
        }
    }

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
        REJECT(4, "被驳回"),
        SALES_FROZEN(5, "销售支持冻结"),
        DELIVERY_FROZEN(6, "交货冻结"),
        ORDER(7, "订单冻结"),
        INVOICE(8, "开票冻结");

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

    /**
     * 代理状态
     */
    public enum DealerStatus{
        TAKE_EFFECT(1, "生效"),
        FROZEN(0, "冻结");

        private Integer code;
        private String desc;

        DealerStatus(Integer code, String desc){
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

    /**
     * 联系人类型
     */
    public enum ContactType{
        C01("CO1", "出货数据确认人"),
        C02("C02", "Rebate确认人");

        private String code;
        private String desc;

        ContactType(String code, String desc){
            this.code = code;
            this.desc = desc;
        }
        public String getCode(){
            return code;
        }
        public String getDesc(){
            return desc;
        }
    }

    /**
     * 币种
     */
    public enum Currency{
        RMB("RMB", ""),
        USD("USD", ""),
        HDK("HDK", "");

        private String code;
        private String desc;

        Currency(String code, String desc){
            this.code = code;
            this.desc = desc;
        }
        public String getCode(){
            return code;
        }
        public String getDesc(){
            return desc;
        }
    }
}
