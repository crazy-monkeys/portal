package com.crazy.portal.dao.test;

import com.crazy.portal.bean.order.DeliveryOrderQueryVO;
import com.crazy.portal.entity.test.DeliverOrderApproval;

import java.util.List;

public interface DeliverOrderApprovalMapper {
    int deleteByPrimaryKey(Integer deliverOrderId);

    int insert(DeliverOrderApproval record);

    int insertSelective(DeliverOrderApproval record);

    DeliverOrderApproval selectByPrimaryKey(Integer deliverOrderId);

    int updateByPrimaryKeySelective(DeliverOrderApproval record);

    int updateByPrimaryKey(DeliverOrderApproval record);

    List<DeliverOrderApproval> selectList(DeliveryOrderQueryVO vo);

    List<DeliverOrderApproval> checkApproval(String salesOrderNo);
}