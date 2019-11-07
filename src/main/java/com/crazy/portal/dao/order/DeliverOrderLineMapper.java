package com.crazy.portal.dao.order;

import com.crazy.portal.entity.order.DeliverOrderLine;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface DeliverOrderLineMapper {
    int deleteByPrimaryKey(Integer deliverOrderLineId);

    int insert(DeliverOrderLine record);

    int insertSelective(DeliverOrderLine record);

    DeliverOrderLine selectByPrimaryKey(Integer deliverOrderLineId);

    int updateByPrimaryKeySelective(DeliverOrderLine record);

    int updateByPrimaryKey(DeliverOrderLine record);

    List<DeliverOrderLine> selectByDeliveryOrderId(@Param("deliveryOrderId") Integer deliveryOrderId);

    DeliverOrderLine selectBySapDeliveryOrderLineNo(String sapDeliveryOrderLineNo);

    int updateReciveQty(@Param("deliverOrderId") Integer deliverOrderId, @Param("qty")BigDecimal qty);

    int cancelRecive(@Param("deliverOrderId") Integer deliverOrderId, @Param("qty")Integer qty);
}