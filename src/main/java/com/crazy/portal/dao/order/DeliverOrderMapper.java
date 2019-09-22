package com.crazy.portal.dao.order;

import com.crazy.portal.bean.order.DeliveryOrderQueryVO;
import com.crazy.portal.entity.order.DeliverOrder;

import java.util.List;

public interface DeliverOrderMapper {
    int deleteByPrimaryKey(Integer deliverOrderId);

    int insert(DeliverOrder record);

    int insertSelective(DeliverOrder record);

    DeliverOrder selectByPrimaryKey(Integer deliverOrderId);

    int updateByPrimaryKeySelective(DeliverOrder record);

    int updateByPrimaryKey(DeliverOrder record);

    List<DeliverOrder> selectList(DeliveryOrderQueryVO vo);
}