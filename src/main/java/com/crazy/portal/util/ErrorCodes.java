package com.crazy.portal.util;

import com.crazy.portal.config.exception.ErrorInfo;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 17:15 2019/6/8
 * @Modified by:
 */
public class ErrorCodes {

    public enum CommonEnum implements ErrorInfo {
        SYSTEM_EXCEPTION(-1,"系统异常","system error"),
        SYSTEM_TIMEOUT(0,"系统超时","system timeout"),
        SUCCESS(1,"操作成功","operate successfully"),
        REQ_PARAM_FORMAT_ERROR(-2, "请求参数格式异常", "");

        private final int code;
        private final String zhMsg;
        private final String enMsg;

        CommonEnum(int code, String zhMsg, String enMsg) {
            this.code = code;
            this.zhMsg = zhMsg;
            this.enMsg = enMsg;
        }

        public int getCode() {
            return code;
        }

        public String getZhMsg() {
            return zhMsg;
        }

        public String getEnMsg() {
            return enMsg;
        }
    }

    /**
     * 系统管理状态码
     */
    public enum SystemManagerEnum implements ErrorInfo {
        ACCOUNT_ERROR(10001,"用户名或密码错误",""),
        LOCKED(10002,"账户锁定",""),
        PASSWORD_INVALID(10003,"密码过期",""),
        AUTH_ERROR(10004,"权限不足",""),
        TOKEN_INVALID(10005,"令牌失效,请重新登录",""),
        USER_EXISTS(10010,"用户已经存在",""),
        USER_EMPTY_USER_NAME(10011,"用户名不能为空",""),
        USER_NOT_EXISTS(10012,"用户不存在",""),
        USER_INVALID_PASSWORD(10013,"原密码不正确",""),
        USER_SAVE_FAILED(10014,"用户保存失败",""),


        ROLE_EXISTS(10020,"角色名已经存在",""),
        ROLE_NOT_EXIST(10021,"角色不存在",""),
        ROLE_EMPTY_NAME(10022,"角色名不能为空",""),
        ROLE_EMPTY_CODE(10023,"角色编码不能为空",""),
        ROLE_EMPTY_ID(10024,"角色ID不能为空",""),
        ROLE_SAVE_FAILED(10025,"角色保存失败",""),
        ROLE_CODE_EXISTS(10026,"角色编码已经存在",""),
        ROLE_EMPTY_TYPE(10027,"角色类型不能为空",""),

        RESOURCE_ILLEGAL(10040,"资源必填项不能为空",""),
        RESOURCE_NOT_EXIST(10041,"资源不存在",""),
        RESOURCE_PARENT_NOT_EXIST(10042,"父级资源不存在",""),
        RESOURCE_EMPTY_LIST(10043,"资源列表为空",""),
        RESOURCE_USED(10045,"该资源已经绑定在角色上，不允许删除",""),
        RESOURCE_TYPE_NOT_EXIST(10046,"类型不存在",""),
        RESOURCE_EXIST(10047,"资源名称已经存在",""),

        SYS_PARAM_VALUE(10048,"该选项下value已存在",""),
        SYS_BEAN_ERROR(10049,"对象映射异常","")
        ;


        private final int code;
        private final String zhMsg;
        private final String enMsg;

        SystemManagerEnum(int code, String zhMsg, String enMsg) {
            this.code = code;
            this.zhMsg = zhMsg;
            this.enMsg = enMsg;
        }

        public static String getZhMsgByCode(int code){
            for(SystemManagerEnum systemManagerEnum : SystemManagerEnum.values()){
                if(systemManagerEnum.code == code){
                    return systemManagerEnum.zhMsg;
                }
            }
            return null;
        }

        public int getCode() {
            return code;
        }

        public String getZhMsg() {
            return zhMsg;
        }

        public String getEnMsg() {
            return enMsg;
        }
    }

    public enum BusinessEnum implements ErrorInfo {

        //文件Util
        FILE_UPLOAD_PARAM_EMPTY(20000, "上传文件必填参数缺失", ""),
        FILE_DOWNLOAD_PARAM_EMPTY(20001, "下载文件必填参数缺失", ""),
        FILE_DOWNLOAD_FILE_NOT_FOUND(20002, "未找到需要下载的文件", ""),
        EXCEL_READ_ERROR(21001, "Excel数据读取异常", ""),
        EXCEL_TYPE_ERROR(21002, "请上传正确Excel文件", ""),

        //公告模块
        ANNOUNCEMENT_FILE_LIST_PARAM_EMPTY(30000, "请上传正确文件参数", ""),
        ANNOUNCEMENT_FILE_SIZE_ERROR(30001, "请勿上传多个文件", ""),
        ANNOUNCEMENT_FILE_PARAM_EMPTY(30002, "请先上传文件", ""),
        ANNOUNCEMENT_FILE_NOT_FOUND_BY_ID(30003, "未找到公告文件", ""),
        ANNOUNCEMENT_FILE_ERROR_BY_ID(30004, "公告文件数据异常", ""),

        //交付模块
        //交付模块 - 出货管理
        HANDOVER_PARAM_TYPE_ERROR(40000, "请使用正确参数值进行接口调用", ""),
        HANDOVER_DELIVER_NOT_DEALER(40001, "非代理商禁止操作", ""),
        HANDOVER_DELIVER_EXISTS_DATA_ERROR(40002, "存在错误数据，请重试", ""),
        HANDOVER_DELIVER_WAITING_CONFIRM(40003, "已超过提交时间，请联系【销售运作部】进行数据确认", ""),
        HANDOVER_DELIVER_ALREADY_CONFIRM(40004, "数据已提交，请勿重复操作", ""),
        HANDOVER_DELIVER_NOT_CONFIRM(40005, "请勿操作非【待确认】状态数据", ""),
        HANDOVER_DELIVER_INVALID_PARAM(40006, "请使用有效参数进行操作", ""),
        HANDOVER_DELIVER_DATA_NOT_EXISTS(40007, "未找到数据记录，请重试", ""),
        //交付模块 - 收货管理

        //客户模块
        CUSTOMER_IS_EMPYT(50000, "没有对应的客户信息", ""),
        CUSTOMER_NAME_EXISTS(50001, "该客户名已存在", ""),
        CUSTOMER_CREDIT(50002,"获取代理商授信额度异常",""),


        RATE_BATCH_IS_NOT_NULL(60001,"批次号已存在",""),
        //代理费率
        AGENCY_RATE_UPLOAD_EXCEPTION(70001, "代理费率上传异常", ""),
        //商务管理
        BUSINESS_TYPE_IS_NULL(70002, "保差退类型不能为空", ""),
        BUSINESS_FILE_TYPE_IS_NULL(70003, "文件类型不能为空", ""),
        BUSINESS_FILE_IS_NULL(70004, "文件不能为空", ""),
        BUSINESS_IDR_ID_IS_NULL(70005, "记录ID不能为空", ""),
        BUSINESS_IDRINFO_IS_NULL(70006, "信息不存在", ""),

        PRODUCT_QUERY_ERROR(70001,"产品查询异常",""),
        PRODUCT_SYNC_ERROR(70002,"产品同步异常","");

        private final int code;
        private final String zhMsg;
        private final String enMsg;

        BusinessEnum(int code, String zhMsg, String enMsg){
            this.code = code;
            this.zhMsg = zhMsg;
            this.enMsg = enMsg;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String getZhMsg() {
            return zhMsg;
        }

        public String getEnMsg() {
            return enMsg;
        }
    }
}
