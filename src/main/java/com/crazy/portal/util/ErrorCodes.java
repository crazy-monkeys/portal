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
        REQ_PARAM_FORMAT_ERROR(-2, "请求参数格式异常", ""),
        REQ_ILLEGAL(-3, "非法请求", "");

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
        ACCOUNT_ERROR(10001,"登录名或密码错误",""),
        LOCKED(10002,"账户锁定",""),
        PASSWORD_INVALID(10003,"密码过期,请联系管理员重置",""),
        AUTH_ERROR(10004,"权限不足",""),
        TOKEN_INVALID(10005,"会话已经过期,请重新登录",""),
        USER_EXISTS(10010,"登录名已经存在",""),
        USER_EMPTY_USER_NAME(10011,"登录名不能为空",""),
        USER_NOT_EXISTS(10012,"用户不存在",""),
        USER_INVALID_PASSWORD(10013,"原密码不正确",""),
        USER_SAVE_FAILED(10014,"用户保存失败",""),
        USER_FOGET_EMAIL_URL_INVALID(10015,"链接已经失效",""),
        USER_EMAIL_INVALID(10016,"用户邮箱不合法",""),
        ROLE_NAME_EXISTS(10020,"角色名已经存在",""),
        ROLE_NOT_EXIST(10021,"角色不存在",""),
        ROLE_EMPTY_NAME(10022,"角色名不能为空",""),
        ROLE_EMPTY_CODE(10023,"角色编码不能为空",""),
        ROLE_EMPTY_ID(10024,"角色ID不能为空",""),
        ROLE_SAVE_FAILED(10025,"角色保存失败",""),
        ROLE_CODE_EXISTS(10026,"角色编码已经存在",""),
        ROLE_EMPTY_TYPE(10027,"角色类型不能为空",""),
        ROLE_BIND_USER(10028,"该角色已经绑定在用户上,不允许删除",""),
        ROLE_BIND_RES(10028,"该角色已经绑定在资源上,不允许删除",""),
        RESOURCE_ILLEGAL(10040,"资源必填项不能为空",""),
        RESOURCE_NOT_EXIST(10041,"资源不存在",""),
        RESOURCE_PARENT_NOT_EXIST(10042,"父级资源不存在",""),
        RESOURCE_HAS_CHILDREN(10044,"该资源下面有子资源,不允许删除",""),
        RESOURCE_USED(10045,"该资源已经绑定在角色上，不允许删除",""),
        RESOURCE_TYPE_NOT_EXIST(10046,"资源类型不存在",""),
        RESOURCE_EXIST(10047,"资源名称已经存在",""),

        SYS_PARAM_VALUE(10048,"该选项下Code已存在",""),
        SYS_BEAN_ERROR(10049,"对象映射异常",""),
        SYS_IN_USER_ERROR(10050,"没有对应的内部员工信息",""),

        VERIFY_CODE_ERROR(10051, "验证码错误", "")
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
        EXCEL_READ_ERROR(21001, "Excel数据不符合格式要求，请检查", ""),
        EXCEL_TYPE_ERROR(21002, "请上传正确Excel文件", ""),
        EXCEL_WRITE_ERROR(21003, "Excel数据写入异常", ""),
        EXCEL_PROPERTY_DIFF(21004, "Excel数据与此功能接收不一致，请核对", ""),
        EXCEL_PARAM_FAIL(21005, "Excel数据类型错误", ""),
        EXCEL_PARAM_DATE_FAIL(21006, "Excel日期格式无效，请核对", ""),
        EXCEL_TEMPLATE_DOWNLOAD_FAIL(21007, "Excel模板下载异常", ""),
        //公告模块
        ANNOUNCEMENT_FILE_LIST_PARAM_EMPTY(30000, "请上传正确文件参数", ""),
        ANNOUNCEMENT_FILE_SIZE_ERROR(30001, "请勿上传多个文件", ""),
        ANNOUNCEMENT_FILE_PARAM_EMPTY(30002, "请先上传文件", ""),
        ANNOUNCEMENT_FILE_NOT_FOUND_BY_ID(30003, "未找到公告文件", ""),
        ANNOUNCEMENT_FILE_ERROR_BY_ID(30004, "公告文件数据异常", ""),
        ANNOUNCEMENT_ROLE_EMPTY(30005, "未选择角色信息，请重新选择", ""),
        NOT_DELETE(30005, "该类型下已有公告，不允许删除", ""),

        //交付模块
        //交付模块 - 出货管理 & 收货管理
        HANDOVER_PARAM_TYPE_ERROR(40000, "请使用正确参数值进行接口调用", ""),
        HANDOVER_NOT_DEALER(40001, "非代理商禁止操作", ""),
        HANDOVER_EXISTS_DATA_ERROR(40002, "存在错误数据，请重试", ""),
        HANDOVER_WAITING_CONFIRM(40003, "已超过提交时间，请联系【销售运作部】进行数据确认", ""),
        HANDOVER_ALREADY_CONFIRM(40004, "数据已提交，请勿重复操作", ""),
        HANDOVER_NOT_CONFIRM(40005, "请勿操作非【待确认】状态数据", ""),
        HANDOVER_INVALID_PARAM(40006, "请使用有效参数进行操作", ""),
        HANDOVER_DATA_NOT_EXISTS(40007, "未找到数据记录，请重试", ""),
        HANDOVER_NOT_REJECT(40008, "数据已提交，无法驳回", ""),
        HANDOVER_BI_SERVER_EXCEPTION(40009, "BI服务异常，请联系管理员", ""),
        HANDOVER_BI_RESPONSE_EXCEPTION(40010, "BI处理结果解析异常，请联系管理员", ""),
        HANDOVER_REJECT_REPEAT_ERROR(40011, "数据已驳回，请勿重复操作", ""),
        HANDOVER_REJECT_REMARK_NOT_EMPTY(40012, "请填写驳回原因", ""),
        HANDOVER_DELETE_ERROR(40013, "数据删除失败，请联系管理员", ""),
        HANDOVER_EXCEL_EMPTY_ERROR(40014, "Excel数据不能为空，请重新上传", ""),
        HANDOVER_UPDATE_ERROR(40015, "数据修改存在错误数据，请重新修正", ""),
        HANDOVER_UPDATE_STATUS_ERROR(40016, "数据未审批，请联系上级进行操作", ""),
        CRM_AMOUNT_ERROR(40017, "请输入有效的CR金额", ""),
        HANDOVER_DATA_EMAIL_ERROR(40018, "获取客户邮箱失败，请联系管理员", ""),
        HANDOVER_SEND_EMAIL_ERROR(40018, "邮件发送失败，请联系管理员", ""),

        //客户模块
        CUSTOMER_IS_EMPYT(50000, "没有对应的客户信息", ""),
        CUSTOMER_NAME_EXISTS(50001, "该客户名已存在", ""),
        CUSTOMER_CREDIT(50002,"获取代理商授信额度异常",""),
        CUSTOMER_IS_APPEROVE(50003,"该客户已被报备",""),
        CUSTOMER_IS_REPORT(50003,"您已提交报备申请，请等待审批",""),
        CUSTOMER_REPORT_ERROR(50004,"非Mass Market 客户",""),
        CUSTOMER_REPORT_ERROR1(50005,"客户正在报备中",""),
        CUSTOMER_SYNC_ERROR(50006,"客户同步异常",""),
        CUSTOMER_ORG_ERROR(50007,"没有审批权限",""),
        CUSTOMER_NO_DEALER(50008,"该用户非有效代理商",""),
        CUSTOMER_APPROVAL_IS_NOT(50009,"不支持的操作，只能删除驳回状态的客户",""),
        CUSTOMER_MOBILE_IS_INACTIVE(50010,"手机号格式不正确",""),
        CUSTOMER_EMAIL_IS_INACTIVE(50011,"邮箱格式不正确",""),
        CUSTOMER_WEB_IS_INACTIVE(50013,"网站格式不正确",""),
        CUSTOMER_FILE_TYPE_IS_NOT_NULL(50012,"附件类型不能为空",""),

        RATE_BATCH_IS_NOT_NULL(60001,"批次号已存在",""),
        //代理费率
        AGENCY_RATE_UPLOAD_EXCEPTION(70001, "代理费率上传异常", ""),
        AGENCY_RATE_CUST_TYPE_ERROR(70106,"客户分类只能为B1（Account Market）或 B2（Mass Market）",""),
        //商务管理-保差退
        BUSINESS_TYPE_IS_NULL(70002, "保差退类型不能为空", ""),
        BUSINESS_FILE_TYPE_IS_NULL(70003, "文件类型不能为空", ""),
        BUSINESS_FILE_IS_NULL(70004, "文件数据不能为空", ""),
        BUSINESS_IDR_ID_IS_NULL(70005, "记录ID不能为空", ""),
        BUSINESS_IDRINFO_IS_NULL(70006, "信息不存在", ""),
        BUSINESS_IDR_STATUS_NOT_APPROVAL(70007, "该申请还未通过审核，请成功后再进行上传", ""),
        BUSINESS_IDR_SUBMIT_RESULT_IS_NULL(70008, "提交至BPM审批返回为空", ""),
        BUSINESS_IDR_SUBMIT_RESULT_FAIL(70009, "提交至BPM审批返回失败", ""),
        BUSINESS_IDR_APPROVAL_ORDERNO_NOT_FOUND(70010, "审批结果同步[orderNo不存在]", ""),
        BUSINESS_IDR_APPROVAL_DATE_PARSE_EXCEPTION(70011, "保差退提交BPM[不是合法的日期格式]", ""),
        BUSINESS_IDR_APPROVAL_API_EXCEPTION(70012, "保差退提交BPM[接口异常]", ""),
        BUSINESS_IDR_APPROVAL_IDR_INFO_NOT_FOUND_EXCEPTION(70013, "审批结果同步[保差退信息不存在]", ""),
        //商务管理-客户rebate
        REBATE_RECORD_NOT_FOUND(70101, "Rebate信息不存在", ""),
        REBATE_SURPLUS_AMOUNT_BIG(70103, "释放金额不能大于rebate金额", ""),
        REBATE_FILE_NOT_FOUND(70103, "文件不存在", ""),
        REBATE_ITEM_ID_IS_NULL(70104, "rebateItemId不能为空", ""),
        REBATE_EXECUTOR_EMAIL_NOT_FOUND(70105, "rebate执行人邮箱不能为空", ""),
        REBATE_SEND_EMAIL_EXCEPTION(70106, "发送确认函异常", ""),
        REBATE_ITEM_CONFIRM_FILE_UPLOAD_EXCEPTION(70107, "上传确认文件必须是代理商", ""),
        REBATE_ITEM_FINISHED_EXCEPTION(70108, "该Rebate已完结，不可上传文件", ""),
        REBATE_ITEM_DL_PROHIBIT_UPLOAD_EXCEPTION(70109, "完结文件不可由代理商上传", ""),
        //产品管理
        PRODUCT_QUERY_ERROR(70501,"产品查询异常",""),
        PRODUCT_SYNC_ERROR(70502,"产品同步异常",""),

        //预测管理
        FORECAST_YEAR_MONTH_FORMAT_ERROR(80000, "月份格式不符合要求，请重新输入", ""),
        FORECAST_EXCEL_CHECK_ERROR(80001, "请使用Excel文件上传数据", ""),
        FORECAST_DATA_NOT_EMPTY(80002, "Excel数据解析为空，请重新输入", ""),
        FORECAST_REQ_PARAM_NOT_EMPTY(80003, "请求参数缺失，请检查", ""),
        FORECAST_DB_DATA_MISMATCH(80004, "原数据与参数不匹配，请重新输入", ""),
        FORECAST_ERROR_DATA_EXISTS(80005, "存在错误数据，请修正后再进行操作", ""),
        FORECAST_BI_SERVER_EXCEPTION(80006, "BI服务异常，请联系管理员", ""),
        FORECAST_BI_RESPONSE_EXCEPTION(80007, "BI处理结果解析异常，请联系管理员", ""),
        FORECAST_BI_DELETE_FAIL(80008, "数据删除失败，请联系管理员", ""),
        FORECAST_BI_CHECK_RESPONSE_ID_NOT_EXISTS(80009, "BI Excel数据 - Portal ID未正确返回", ""),
        FORECAST_NOT_FOUND_CUSTOMER_INFO(80010, "请填写正确的客户简称信息", ""),
        FORECAST_AGENCY_INFO_ERROR(80011, "获取代理商信息失败，请联系管理员", ""),
        FORECAST_SD_DATA_COMMIT_ERROR(80012, "预测汇总数据提交BI校验不通过", ""),
        FORECAST_BI_ID_NOT_EXISTS(80013, "BI数据ID无法匹配数据，请检查数据", ""),
        FORECAST_AGENCY_MATCH_ERROR(80014, "无法匹配代理商信息，请联系管理员", ""),
        FORECAST_CUSTOMER_MATCH_ERROR(80015, "无法匹配用户上下级关系，请联系管理员", ""),
        FORECAST_REJECT_DATA_NOT_DELETE(80016, "数据已驳回，无法删除", ""),
        FORECAST_ALREADY_COMMIT_NOT_REJECT(80017, "数据已提交BI，无法驳回", ""),
        FORECAST_DATA_TYPE_DISUNITY(80018, "请操作同一类型数据", ""),
        FORECAST_DATA_OPERATION_ERROR(80019, "请求类型暂不支持，请重新选择数据操作", ""),
        FORECAST_CHECK_IDENTICAL_MONTH_ERROR(80020, "请操作同一个月份数据", ""),
        FORECAST_DATA_TOTAL_CHECK_ERROR(80021, "数据未全部选中，请重新选择", ""),
        FORECAST_AMB_ADJUSTMENT_NUM_ERROR(80022, "存在阿米巴队长未调整数据，请先调整数据", ""),
        FORECAST_AGENCY_QUERY_ERROR(80023, "获取当前用户的代理商失败，请联系管理员", ""),
        FORECAST_SD_BUFFER_ADJUSTMENT_NUM_ERROR(80024, "存在首代Buffer未调整数据，请先调整数据", ""),
        FORECAST_PRODUCT_INFO_GET_ERROR(80025, "产品信息获取失败，请联系管理员", ""),
        FORECAST_OPERATION_DELETE_REPEAT(80026, "删除记录未审批，请勿重复操作", ""),
        FORECAST_OPERATION_UPDATE_REPEAT(80027, "修改记录未审批，请勿重复操作", ""),
        FORECAST_DATA_REPEAT_ERROR(80028, "检测到数据已存在，请重新上传", ""),
        FORECAST_PO_PRICE_GET_ERROR(80029, "获取产品价格信息失败，请联系管理员", ""),

        //订单管理
        ORDER_NOT_FOUND(90001, "订单信息不存在", ""),
        ORDER_LINE_NOT_FOUND(90002, "订单行信息不存在", ""),
        ORDER_ID_IS_REQUIRED(90003, "订单ID不能为空", ""),
        ORDER_IS_FAILURE(90004, "该订单已经失效", ""),
        ORDER_UN_APPROVE_PASS_ORDER(90004, "未审批通过订单不允许继续操作", ""),
        ORDER_APPROVE_REJECT_ORDER(90004, "只允许修改被驳回的订单", ""),
        ORDER_PENDING_ORDER(90004, "该订单存在待审批记录,请等待审批结束后再发起申请", ""),
        ORDER_INFO_IS_REQUIRED(90005, "订单信息不能为空", ""),
        ORDER_LINES_IS_REQUIRED(90006, "订单行信息不能为空", ""),
        ORDER_APPROCAL_STATUS_IS_REQUIRED(90007, "审批状态不能为空", ""),
        ORDER_DELIVERY_DATE_FORMAT_FAIL(90008, "订单发货日期格式错误", ""),
        ORDER_NO_PENDING(90009, "当前订单已经被审批不允许继续操作", ""),
        ORDER_LINE_FILE_ERROR(900010, "订单行附件参数格式不正确", ""),
        ORDER_EMPTY_PRICE_DATE(900011, "定价日期不能为空", ""),
        ORDER_APPLY_ORDER_NOT_FOUND(900012, "订单信息不存在", ""),
        ORDER_NO_DELETE(900013,"非驳回订单不允许删除",""),
        ORDER_NOT_EXISTS_PRODUCT_ID(900014, "行附件平台物料号不存在", ""),
        ORDER_EMPTY_PRODUCT_ID(900015, "行附件物料号不能为空", ""),
        ORDER_EMPTY_PLATFORM(900016, "行附件平台不能为空", ""),
        ORDER_EMPTY_NUM(900017, "行附件数量不能为空", ""),
        ORDER_EMPTY_EXPECTEDDELIVERYMONTH(900018, "行附件期望交货月份不能为空", ""),
        ORDER_ID_NOT_FOUND(900019,"订单不存在",""),
        ORDER_IS_CANCEL(900020,"不允许的操作,提货单已取消",""),
        ORDER_IS_INACTIVE(900021,"不允许的操作,订单状态不满足",""),
        ORDER_QTY_IS_ENOUGH(900022,"订单可提数量不足",""),
        ORDER_PRICE_ERROR(900023,"定价失败",""),

        //文档管理
        ARCHIVE_FILE_NOT_EMPTY(100001, "请选择需要上传的文件", ""),
        ARCHIVE_DB_RECORD_NOT_FOUND(100002, "未找到对应数据记录，请重新操作", ""),
        ARCHIVE_DB_RECORD_STATUS_ERROR(100003, "记录未发布或已经撤销，请重新操作", ""),
        ;
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


    public enum PriceEnum implements ErrorInfo {

        PRICE_ENQUIEY_NOT_EXISTS(80000,"询价申请单未存在"),
        PRICE_ENQUIEY_APPROVAL_STATUS_NOT_EXISTS(80001,"审批状态不存在"),
        PRICE_CATALOG_NOT_EXISTS(80002,"产品型号不存在"),
        PRICE_CATALOG_PRICE_NOT_EXISTS(80003,"目录价不存在"),
        PRICE_EMPTY_APPLYIDS(80004,"询价申请单ID不能为空"),
        PRICE_EMPTY_STATUS(80004,"询价申请单审批状态不能为空"),
        PRICE_NON_PENGDING_STATUS(80005,"非待审批状态询价单不允许再进行审批"),
        PRICE_EMPTY_MODEL_INCUSTOMER(80005,"内部客户跟产品型号不能同时为空"),
        PRICE_EMPTY_BU(80002,"BU 值不能为空"),
        ;

        private final int code;
        private final String zhMsg;

        PriceEnum(int code, String zhMsg){
            this.code = code;
            this.zhMsg = zhMsg;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String getZhMsg() {
            return zhMsg;
        }
    }
}
