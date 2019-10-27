package com.crazy.portal.bean.business.idr;

import lombok.Data;

/**
 * 保差退提交审批返回结果
 */
@Data
public class IdrApprovalSubmitResultBean {
    /** 执行结果 **/
    private String result;
    /** 单号 **/
    private String orderNO;
    /** 当前审批人 **/
    private String reviewer;
    /** 详细信息 **/
    private String message;
    /** 提交类型：KP（保价），CP（差价），TH（退货） **/
    private String type;

}
