package com.crazy.portal.dao.order;

import com.crazy.portal.entity.order.OrderInvoice;

public interface OrderInvoiceMapper {
    int deleteByPrimaryKey(Integer invoiceId);

    int insert(OrderInvoice record);

    int insertSelective(OrderInvoice record);

    OrderInvoice selectByPrimaryKey(Integer invoiceId);

    int updateByPrimaryKeySelective(OrderInvoice record);

    int updateByPrimaryKey(OrderInvoice record);

    OrderInvoice selectByDeliveryOrderId(String deliveryOrderId);
}