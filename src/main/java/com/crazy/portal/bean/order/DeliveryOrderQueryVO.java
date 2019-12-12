package com.crazy.portal.bean.order;

import com.crazy.portal.bean.common.PageBean;
import lombok.Data;

import java.util.List;

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
    private String salesOrderId;
    private String actualDeliveryStartDate;
    private String actualDeliveryEndDate;
    private String deliveryStartDate;
    private String deliveryEndDate;
    private String createStartDate;
    private String createEndDate;

    private Integer dealerId;
    /**1-审批查询 2-申请查询**/
    private Integer queryType;

    private List<Integer> userIds;
}
