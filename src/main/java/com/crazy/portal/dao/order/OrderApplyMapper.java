package com.crazy.portal.dao.order;

import com.crazy.portal.bean.order.OrderQueryBean;
import com.crazy.portal.entity.order.OrderApply;

import java.util.List;

public interface OrderApplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(OrderApply record);

    OrderApply selectByPrimaryKey(Integer id);

    List<OrderApply> selectByPage(OrderQueryBean bean);

    int updateByPrimaryKeySelective(OrderApply record);
}