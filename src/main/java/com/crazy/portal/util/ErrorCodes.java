package com.crazy.portal.util;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 17:15 2019/6/8
 * @Modified by:
 */
public class ErrorCodes {

    public enum CommonEnum {
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
    public enum SystemManagerEnum {
        ACCOUNT_ERROR(10001,"用户名或密码错误",""),
        LOCKED(10002,"账户锁定",""),
        PASSWORD_INVALID(10003,"密码过期",""),
        AUTH_ERROR(10004,"权限不足",""),
        TOKEN_INVALID(10005,"TOKEN 失效",""),


        ROLE_EXISTS(10020,"角色已经存在",""),
        ROLE_NOT_EXIST(10021,"角色不存在",""),
        ROLE_EMPTY_NAME(10022,"角色名不能为空",""),
        ROLE_EMPTY_CODE(10023,"角色编码不能为空",""),
        ROLE_EMPTY_ID(10024,"角色ID不能为空",""),
        ROLE_SAVE_FAILED(10025,"角色保存失败",""),

        PERMISSION_ILLEGAL(10040,"资源必填项不能为空",""),
        PERMISSION_NOT_EXIST(10041,"(父级/当前)资源ID不存在",""),
        PERMISSION_EMPTY_AND_ROLE(10042,"添加/移除 列表不能同时为空",""),
        PERMISSION_USED(10043,"该权限已配置角色，请先删除该权限的角色信息",""),
        PERMISSION_UN_TYPE_OPTIONAL(10044,"类型不存在","");


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
}
