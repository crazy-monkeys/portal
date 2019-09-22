package com.crazy.portal.bean.order;

import com.crazy.portal.bean.common.PageBean;
import lombok.Data;

/**
 * @ClassName: DeliveryOrderQueryVO
 * @Author: God Man Qiu~
 * @Date: 2019/9/22 22:09
 */
@Data
public class DeliveryOrderQueryVO extends PageBean {
    private Integer createUserId;
    private Integer approvalStatus;
    private String approvalStartDate;
    private String approvalEndDate;
    private String deliveryOrderNo;
    private String salesOrderNo;
    private String actualDeliveryStartDate;
    private String actualDeliveryEndDate;

    private Integer dealerId;
}
