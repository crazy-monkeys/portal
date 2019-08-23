package com.crazy.portal.bean.business.idr.call;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description TODO
 * @Author Shawn
 * @Date 2019-08-23 23:15
 * @Modify by
 */
@Getter
@Setter
public class ApprovalRequestBean {

    //单据类型,KP(报价),CP（差价）,TH（退货）,HH（换货）
    private String type;
    //单号编码
    private String orderNumber;
    //审批人员，返回用户名称
    private String reviewedPeople;
    //审批状态Agree(同意)，Reject(驳回)
    private String reviewStatus;
    //审批意见comment
    private String comment;
    //下一环节审批人状态，返回用户名称，返回人员为空，表示审批结束或驳回。
    private String currentReviewer;

}
