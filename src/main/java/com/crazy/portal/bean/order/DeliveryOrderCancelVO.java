package com.crazy.portal.bean.order;

import lombok.Data;
import java.util.List;

/**
 * @ClassName: DeliveryOrderCancelVO
 * @Author: God Man Qiu~
 * @Date: 2019/9/23 23:56
 */
@Data
public class DeliveryOrderCancelVO {
    private Integer deliveryOrderId;
    private List<Integer> deliveryOrderLineIds;
}
