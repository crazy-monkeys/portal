package com.crazy.portal.dao.order;

import com.crazy.portal.entity.order.OrderLine;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface OrderLineMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(OrderLine record);

    OrderLine selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderLine record);

    List<OrderLine> selectByOrderId(Integer orderId);

    List<OrderLine> selectByProduct(@Param("orderId") Integer orderId, @Param("product") String product);
}