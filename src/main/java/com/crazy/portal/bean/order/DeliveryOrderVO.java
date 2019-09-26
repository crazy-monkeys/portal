package com.crazy.portal.bean.order;

import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: DeliveryOrderVO
 * @Author: God Man Qiu~
 * @Date: 2019/9/22 19:19
 */
@Data
public class DeliveryOrderVO {
    private Integer orderId;
    private Integer deliveryOrderId;
    private Date deliverDate;
    private String shippingPoint;
    private List<DeliveryOrderLineVO> orderLine;
}
