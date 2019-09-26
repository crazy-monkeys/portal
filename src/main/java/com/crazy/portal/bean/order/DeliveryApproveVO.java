package com.crazy.portal.bean.order;

import lombok.Data;

/**
 * @ClassName: DeliveryApproveVO
 * @Author: God Man Qiu~
 * @Date: 2019/9/22 21:13
 */
@Data
public class DeliveryApproveVO {
    private Integer deliveryOrderApprovalId;
    private String sapDeliveryOrderNo;
    private Integer approvalStatus;
    private String remark;
}
