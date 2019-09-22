package com.crazy.portal.dao.order;

import com.crazy.portal.entity.order.DeliverOrderLine;

import java.util.List;

public interface DeliverOrderLineMapper {
    int deleteByPrimaryKey(Integer deliverOrderLineId);

    int insert(DeliverOrderLine record);

    int insertSelective(DeliverOrderLine record);

    DeliverOrderLine selectByPrimaryKey(Integer deliverOrderLineId);

    int updateByPrimaryKeySelective(DeliverOrderLine record);

    int updateByPrimaryKey(DeliverOrderLine record);

    List<DeliverOrderLine> selectByDeliveryOrderId(Integer deliveryOrderId);
}