package com.crazy.portal.bean.order;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class DeliveryChangeVO {

    /** 订单ID集合 **/
    @NotNull(message = "订单行ID不能为空")
    private Integer itemId;
    /** 期望交货日期**/

    @NotEmpty(message = "期望交货日期不能为空")
    @JSONField(format="yyyyMMdd")
    private Date expectedDeliveryDate;
}
