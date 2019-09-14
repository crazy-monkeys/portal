package com.crazy.portal.dao.order;

import com.crazy.portal.entity.order.OrderLine;

import java.util.List;

public interface OrderLineMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(OrderLine record);

    OrderLine selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderLine record);

    List<OrderLine> selectByOrderId(Integer orderId);

}