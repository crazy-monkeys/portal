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
        TOKEN_INVALID(10005,"TOKEN 失效",""),
        USER_EXISTS(10010,"用户已经存在",""),
        USER_EMPTY_USER_NAME(10011,"用户名不能为空",""),
        USER_NOT_EXISTS(10012,"用户不存在",""),
        USER_INVALID_PASSWORD(10013,"原密码不正确",""),


        ROLE_EXISTS(10020,"角色名已经存在",""),
        ROLE_NOT_EXIST(10021,"角色不存在",""),
        ROLE_EMPTY_NAME(10022,"角色名不能为空",""),
        ROLE_EMPTY_CODE(10023,"角色编码不能为空",""),
        ROLE_EMPTY_ID(10024,"角色ID不能为空",""),
        ROLE_SAVE_FAILED(10025,"角色保存失败",""),
        ROLE_CODE_EXISTS(10026,"角色编码已经存在",""),

        PERMISSION_ILLEGAL(10040,"资源必填项不能为空",""),
        PERMISSION_NOT_EXIST(10041,"(父级/当前)资源ID不存在",""),
        PERMISSION_EMPTY_AND_ROLE(10042,"添加/移除权限ID不能同时为空",""),
        PERMISSION_USED(10043,"该权限已配置角色，请先删除该权限的角色信息",""),
        PERMISSION_UN_TYPE_OPTIONAL(10044,"类型不存在",""),

        SYS_PARAM_VALUE(10045,"该选项下value已存在","")
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

        //公告模块
        ANNOUNCEMENT_FILE_LIST_PARAM_EMPTY(30000, "请上传正确文件参数", ""),
        ANNOUNCEMENT_FILE_SIZE_ERROR(30001, "请勿上传多个文件", ""),
        ANNOUNCEMENT_FILE_PARAM_EMPTY(30002, "请先上传文件", ""),
        ANNOUNCEMENT_FILE_NOT_FOUND_BY_ID(30003, "未找到公告文件", ""),
        ANNOUNCEMENT_FILE_ERROR_BY_ID(30004, "公告文件数据异常", ""),

        //客户模块
        CUSTOMER_IS_EMPYT(50000, "没有对应的客户信息", ""),
        CUSTOMER_NAME_EXISTS(50001, "该客户名已存在", ""),
        CUSTOMER_CREDIT(50002,"获取代理商授信额度异常","");

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
