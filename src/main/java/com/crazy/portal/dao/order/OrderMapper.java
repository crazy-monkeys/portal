package com.crazy.portal.dao.order;

import com.crazy.portal.bean.order.OrderQueryBean;
import com.crazy.portal.entity.order.Order;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    List<Order> selectByPage(OrderQueryBean bean);

}