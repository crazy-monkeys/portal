package com.crazy.portal.util;

import com.alibaba.excel.metadata.BaseRowModel;
import com.crazy.portal.bean.business.idr.DiffPriceEO;
import com.crazy.portal.bean.business.idr.InsuranceEO;
import com.crazy.portal.bean.business.idr.ReturnsEO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 22:44 2019/4/8
 * @Modified by:
 */
public class Enums {

    public enum SYS_MODEL{
        FORECAST, //上传预测
        FORECAST_APPROVE; //上传、修改调整和最终审批
    }

    public enum MAIL_TEMPLATE {
        USER_CREATE("createSubAgent"),
        RESET_PWD("resetPwdTemplate"),
        REBATE_CONFIRM("confirmLetter");

        private String templateName;

        MAIL_TEMPLATE(String templateName){
            this.templateName = templateName;
        }

        public String getTemplateName() {
            return templateName;
        }
    }

    /**
     * 审批状态
     * pending:待审批
     * pass:通过
     * reject:驳回
     */
    public enum APPROVAL_STATUS {
        pending,pass,reject
    }

    public enum YES_NO {
        YES(1),
        NO(2);

        private int code;

        YES_NO(Integer code){
            this.code = code;
        }

        public int getCode() {
            return code;
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
     * 用户状态：正常、冻结
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
     * A01 - Account Market（直供）；A02 - Account Market（非直供）；A03 - Mass Market；A04 - 代理商
     */
    public enum CUSTOMER_BUSINESS_TYPE{
        account_market_z("A01"),
        account_market("A02"),
        mass_market("A03"),
        dealer("A04");


        private final String code;
        CUSTOMER_BUSINESS_TYPE(String code){
            this.code = code;
        }
        public String getCode(){
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
     *报备状态 0-初始化 1-已报备 2-可报备 3-报备中 4-驳回
     */
    public enum CUSTOMER_APPROVE_STATUS{
        WAIT_SUBMIT(0, "初始化"),
        WAIT_APPROVAL(3, "报备中"),
        REPORT(1," 已报备"),
        WAIT_REPORT(2,"可报备"),
        REJECT(4, "被驳回");

        private Integer code;
        private String desc;
        CUSTOMER_APPROVE_STATUS(Integer code, String desc){
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

    public enum RATE_TYPE{
        INI(-1),ACTIVE(1),INACTIVE(0);

        private final int code;

        RATE_TYPE(int code){
            this.code = code;
        }

        public int getCode() {
            return code;
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
        RMB,
        USD,
        HDK;

        Currency(){

        }
    }

    public enum BusinessIdrType{
        INSURANCE(1, "保价", new InsuranceEO()),
        DIFF_PRICE(2, "差价补偿", new DiffPriceEO()),
        RETURNS(3, "退换货", new ReturnsEO());

        private Integer code;
        private String desc;
        private BaseRowModel type;

        BusinessIdrType(Integer code, String desc, BaseRowModel type){
            this.code = code;
            this.desc = desc;
            this.type = type;
        }
        public Integer getCode(){
            return code;
        }
        public String getDesc(){
            return desc;
        }
        public BaseRowModel getType(){return type;}
        public static BusinessIdrType getDescByCode(Integer code){
            for(BusinessIdrType e : BusinessIdrType.values()){
                if(code == e.getCode()){
                    return e;
                }
            }
            return null;
        }
    }

    public enum BusinessIdrStatus{
        SUBMIT(1, "已提交"),
        FINISHED(2, "已完结");

        private Integer code;
        private String desc;

        BusinessIdrStatus(Integer code, String desc){
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

    public enum BusinessFileType{
        COMMON(1, "普通附件"),
        IDR(2, "保差退附件"),
        FINANCIAL_CLOSURE(3, "财务完结附件");



        private Integer code;
        private String desc;

        BusinessFileType(Integer code, String desc){
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

    public enum BusinessRebateItemStatus{

        IN_APPROVAL(1, "审核中"),
        WAIT_CONFIRM(2, "客户待确认"),
        USED_CONFIRM(3, "客户已确认"),
        FINISHED(4, "执行完成");

        private Integer code;
        private String desc;

        BusinessRebateItemStatus(Integer code, String desc){
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

    public enum BusinessRebateStatus{

        EXECUTE_PROCESS(1, "执行中"),
        FINISHED(4, "执行完成");

        private Integer code;
        private String desc;

        BusinessRebateStatus(Integer code, String desc){
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

    public enum ProductStatus{
        ACTIVE(1),INACTIVE(0);
        private Integer status;
        ProductStatus(Integer status){
            this.status = status;
        }

        public Integer getStatus() {
            return status;
        }
    }

    public enum BI_FUNCTION_CODE{
        CHECK_INVENTORY_IMPORT_FILE,SAVE_INVENTORY_IMPORT_FILE,CHECK_SALES_IMPORT_FILE,SAVE_SALES_IMPORT_FILE;
    }
}
