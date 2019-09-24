package com.crazy.portal.dao.order;

import com.crazy.portal.entity.order.OrderApply;

public interface OrderApplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderApply record);

    int insertSelective(OrderApply record);

    OrderApply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderApply record);

    int updateByPrimaryKey(OrderApply record);
}