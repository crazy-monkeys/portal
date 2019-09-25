package com.crazy.portal.service.order;

import com.crazy.portal.bean.order.DeliveryApproveVO;
import com.crazy.portal.bean.order.DeliveryOrderQueryVO;
import com.crazy.portal.bean.order.OrderQueryBean;
import com.crazy.portal.bean.order.wsdl.delivery.create.ZrfcsdDeliveryCreate;
import com.crazy.portal.bean.order.wsdl.delivery.create.ZrfcsdDeliveryCreateBody;
import com.crazy.portal.bean.order.wsdl.delivery.create.ZrfcsdDeliveryCreateContent;
import com.crazy.portal.bean.order.wsdl.delivery.create.ZrfcsddeliverycreateResponse;
import com.crazy.portal.bean.order.wsdl.delivery.update.*;
import com.crazy.portal.dao.order.*;
import com.crazy.portal.entity.order.DeliverOrder;
import com.crazy.portal.entity.order.DeliverOrderLine;
import com.crazy.portal.entity.order.Order;
import com.crazy.portal.entity.order.OrderInvoice;
import com.crazy.portal.util.BusinessUtil;
import com.crazy.portal.util.DateUtil;
import com.crazy.portal.util.Enums;
import com.crazy.portal.util.ErrorCodes;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单管理
 */
@Slf4j
@Service
public class OrderService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderLineMapper orderLineMapper;
    @Resource
    private DeliverOrderMapper deliverOrderMapper;
    @Resource
    private DeliverOrderLineMapper deliverOrderLineMapper;
    @Resource
    private OrderInvoiceMapper orderInvoiceMapper;
    @Resource
    private OrderApiService orderApiService;

    /**
     * 订单列表查询
     * @param bean
     * @return
     */
    public PageInfo<Order> list(OrderQueryBean bean){
        PageHelper.startPage(bean.getPageIndex(), bean.getPageSize());
        List<Order> list = orderMapper.selectByPage(bean);
        return new PageInfo<>(list);
    }

    /**
     * 提货单列表
     * @param vo
     * @return
     */
    public PageInfo<DeliverOrder> deliveryOrderList(DeliveryOrderQueryVO vo){
        PageHelper.startPage(vo.getPageIndex(), vo.getPageSize());
        List<DeliverOrder> list = deliverOrderMapper.selectList(vo);
        return new PageInfo<>(list);
    }

    /**
     * 订单明细
     * @param id
     * @return
     */
    public Order detail(Integer id){
        BusinessUtil.notNull(id, ErrorCodes.BusinessEnum.ORDER_ID_IS_REQUIRED);
        Order order = orderMapper.selectByPrimaryKey(id);
        BusinessUtil.notNull(order, ErrorCodes.BusinessEnum.ORDER_NOT_FOUND);
        order.setLines(orderLineMapper.selectByOrderId(id));
        return order;
    }

    /**
     * 订单明细
     * @param id
     * @return
     */
    public Order findOrder(Integer id){
        return orderMapper.selectByPrimaryKey(id);
    }

    /**
     * 提货单明细
     * @param deliveryOrderId
     * @return
     */
    public DeliverOrder deliveryDetail(Integer deliveryOrderId){
        Integer active = 1;
        DeliverOrder deliverOrder = deliverOrderMapper.selectByPrimaryKey(deliveryOrderId);
        if(deliverOrder.getApprovalType().equals(Enums.OrderApprovalType.CANCEL.getValue()) &&
                deliverOrder.getApprovalStatus()==Enums.OrderApprovalStatus.WAIT_APPROVAL.getValue()){
            active = 2;
        }
        List<DeliverOrderLine> deliverOrderLines = deliverOrderLineMapper.selectByDeliveryOrderId(deliveryOrderId, active);
        deliverOrder.setDeliverOrderLineList(deliverOrderLines);
        List<OrderInvoice> orderInvoiceList = orderInvoiceMapper.selectByDeliveryOrderId(deliverOrder.getSapDeliverOrderNo());
        deliverOrder.setOrderInvoiceList(orderInvoiceList);

        return deliverOrder;
    }

    @Transactional
    public void deliveryApprove(DeliveryApproveVO vo, Integer userId){
        DeliverOrder deliverOrder = deliverOrderMapper.selectByPrimaryKey(vo.getDeliveryOrderId());
        BusinessUtil.assertFlase(null == deliverOrder, ErrorCodes.BusinessEnum.ORDER_NOT_FOUND);
        List<DeliverOrderLine> deliverOrderLines = new ArrayList<>();
        if(deliverOrder.getApprovalType().equals(Enums.OrderApprovalType.CREATE.getValue())){
            //新建审批
            getApproalList(deliverOrder.getDeliverOrderId(),1);
            //approvalCreate(vo, deliverOrder, deliverOrderLines);
        }else if(deliverOrder.getApprovalType().equals(Enums.OrderApprovalType.UPDATE.getValue())){
            //修改审批
            getApproalList(deliverOrder.getDeliverOrderId(),1);
            approvalUpdate(vo, deliverOrder, deliverOrderLines);
        }else{
            //取消审批
            deliverOrderLines = getApproalList(deliverOrder.getDeliverOrderId(),2);
            approvalCancel(vo, deliverOrder, deliverOrderLines);
        }

        deliverOrder.setApprovalUser(1);
        deliverOrder.setApprovalStatus(vo.getApprovalStatus());
        deliverOrder.setApprovalRemark(vo.getRemark());
        deliverOrder.setApprovalDate(new Date());
        deliverOrderMapper.updateByPrimaryKeySelective(deliverOrder);
    }

    private List<DeliverOrderLine> getApproalList(Integer deliveryOrderId, Integer active){
        List<DeliverOrderLine> deliverOrderLines = deliverOrderLineMapper.selectByDeliveryOrderId(deliveryOrderId, active);
        BusinessUtil.assertFlase(null == deliverOrderLines, ErrorCodes.BusinessEnum.ORDER_LINE_NOT_FOUND);
        return deliverOrderLines;
    }

    private void approvalCreate(DeliveryApproveVO vo, DeliverOrder deliverOrder, List<DeliverOrderLine> deliverOrderLines){
        //如果是通过，调用ECC创单接口
        if(vo.getApprovalStatus().equals(Enums.OrderApprovalStatus.ADOPT.getValue())){
            ZrfcsddeliverycreateResponse response = eccDeliveryCreate(deliverOrder, deliverOrderLines);
            if(response.getResulttype().equals("0")){
                deliverOrder.setSapDeliverOrderNo(response.getSapdeliveryid());
                /*deliverOrderLines.forEach(e->{
                    e.setSapDeliverOrderLineNo("");
                    deliverOrderLineMapper.updateByPrimaryKeySelective(e);
                });*/
            }
        }
    }

    private void approvalUpdate(DeliveryApproveVO vo, DeliverOrder deliverOrder, List<DeliverOrderLine> deliverOrderLines){
        //如果是通过，调用ECC修改接口
        if(vo.getApprovalStatus().equals(Enums.OrderApprovalStatus.ADOPT.getValue())){
            /*ZrfcsddeliverychangeResponse response = eccDeliveryUpdate(deliverOrder, deliverOrderLines);
            if(response.getResulttype().equals("0")){

            }*/
        }else{
            //撤回修改

        }
    }

    private void approvalCancel(DeliveryApproveVO vo, DeliverOrder deliverOrder, List<DeliverOrderLine> deliverOrderLines){
        //如果是通过，调用ECC修改接口
        if(vo.getApprovalStatus().equals(Enums.OrderApprovalStatus.ADOPT.getValue())){
            List<DeliverOrderLine> oldList = deliverOrderLineMapper.selectByDeliveryOrderId(deliverOrder.getDeliverOrderId(), null);
            if(oldList.size() == deliverOrderLines.size()){
                deliverOrder.setActive(0);
            }
            deliverOrderLines.forEach(e->{
                e.setActive(0);
                deliverOrderLineMapper.updateByPrimaryKeySelective(e);
            });
            /*ZrfcsddeliverychangeResponse response = eccDeliveryUpdate(deliverOrder, deliverOrderLines);
            if(response.getResulttype().equals("0")){

            }*/
        }else{
            deliverOrderLines.forEach(e->{
                e.setActive(1);
                deliverOrderLineMapper.updateByPrimaryKeySelective(e);
            });
        }
    }

    private ZrfcsddeliverycreateResponse eccDeliveryCreate(DeliverOrder deliverOrder, List<DeliverOrderLine> deliverOrderLines){
        ZrfcsdDeliveryCreateContent content = new ZrfcsdDeliveryCreateContent();
        content.setDeliverydate(DateUtil.format(deliverOrder.getDeliverDate(),DateUtil.SHORT_FORMAT));
        content.setDeliveryIoc(deliverOrder.getShippingPoint());
        content.setPortalDeliveryId(String.valueOf(deliverOrder.getDeliverOrderId()));
        content.setSapOrderId(deliverOrder.getSapOrderNo());
        content.setTItem(gettItemcreate(deliverOrderLines));
        ZrfcsdDeliveryCreateBody body = new ZrfcsdDeliveryCreateBody(content);
        ZrfcsdDeliveryCreate create = new ZrfcsdDeliveryCreate(body);
        return orderApiService.deliveryCreate(create);
    }

    private com.crazy.portal.bean.order.wsdl.delivery.create.TItem gettItemcreate(List<DeliverOrderLine> deliverOrderLineList){
        com.crazy.portal.bean.order.wsdl.delivery.create.TItem tItem = new com.crazy.portal.bean.order.wsdl.delivery.create.TItem();
        List<com.crazy.portal.bean.order.wsdl.delivery.create.Item> items = new ArrayList<>();
        deliverOrderLineList.forEach(e->{
            com.crazy.portal.bean.order.wsdl.delivery.create.Item item = new com.crazy.portal.bean.order.wsdl.delivery.create.Item();
            item.setDeliveryQuantity(String.valueOf(e.getDeliveryQuantity()));
            item.setItemNo(e.getSapSalesOrderLineNo());
            item.setProductId(e.getProductId());
            items.add(item);
        });
        tItem.setItems(items);
        return tItem;
    }

    private ZrfcsddeliverychangeResponse eccDeliveryUpdate(DeliverOrder order, List<DeliverOrderLine> deliverOrderLineList){
        ZrfcsdDeliveryUpdateContent content = new ZrfcsdDeliveryUpdateContent();
        content.setDeliverydate(DateUtil.format(order.getDeliverDate(),DateUtil.SHORT_FORMAT));
        content.setSapDeliveryId(order.getSapDeliverOrderNo());
        content.setTItem(gettItem(deliverOrderLineList));
        ZrfcsdDeliveryUpdateBody body = new ZrfcsdDeliveryUpdateBody(content);
        ZrfcsdDeliveryUpdate update = new ZrfcsdDeliveryUpdate(body);
        return orderApiService.deliveryUpdate(update);
    }

    private TItem gettItem(List<DeliverOrderLine> deliverOrderLineList){
        TItem tItem = new TItem();
        List<Item> items = new ArrayList<>();
        deliverOrderLineList.forEach(e->{
            Item item = new Item();
            item.setOperationType("U");
            item.setDeliveryItemNo(e.getSapDeliverOrderLineNo());
            item.setDeliveryQuantity(String.valueOf(e.getDeliveryQuantity()));
            items.add(item);
        });
        tItem.setItems(items);
        return tItem;
    }
}
