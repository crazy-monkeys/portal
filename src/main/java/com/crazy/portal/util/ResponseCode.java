package com.crazy.portal.util;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 17:15 2019/6/8
 * @Modified by:
 */
public class ResponseCode {

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
        INVALID(10003,"账户失效",""),
        AUTH_ERROR(10004,"权限不足",""),
        TOKEN_INVALID(10005,"TOKEN 失效","");


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
