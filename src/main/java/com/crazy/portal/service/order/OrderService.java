package com.crazy.portal.service.order;

import com.crazy.portal.bean.customer.wsdl.customer.detail.ProductInfo;
import com.crazy.portal.bean.order.DeliveryApproveVO;
import com.crazy.portal.bean.order.DeliveryOrderQueryVO;
import com.crazy.portal.bean.order.OrderLineEO;
import com.crazy.portal.bean.order.OrderQueryBean;
import com.crazy.portal.bean.order.wsdl.delivery.create.ZrfcsdDeliveryCreate;
import com.crazy.portal.bean.order.wsdl.delivery.create.ZrfcsdDeliveryCreateBody;
import com.crazy.portal.bean.order.wsdl.delivery.create.ZrfcsdDeliveryCreateContent;
import com.crazy.portal.bean.order.wsdl.delivery.create.ZrfcsddeliverycreateResponse;
import com.crazy.portal.bean.order.wsdl.delivery.update.*;
import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.dao.order.*;
import com.crazy.portal.dao.order.DeliverOrderApprovalMapper;
import com.crazy.portal.dao.product.ProductInfoDOMapper;
import com.crazy.portal.entity.order.*;
import com.crazy.portal.entity.order.DeliverOrderApproval;
import com.crazy.portal.entity.product.ProductInfoDO;
import com.crazy.portal.util.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.Bus;
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
    private DeliverOrderApprovalMapper deliverOrderApprovalMapper;
    @Resource
    private DeliverOrderLineMapper deliverOrderLineMapper;
    @Resource
    private OrderInvoiceMapper orderInvoiceMapper;
    @Resource
    private OrderApiService orderApiService;
    @Resource
    private ProductInfoDOMapper productInfoDOMapper;

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
     * 提货单审批列表
     * @param vo
     * @return
     */
    public PageInfo<DeliverOrderApproval> deliveryOrderApprovalList(DeliveryOrderQueryVO vo, Integer userId){
        PageHelper.startPage(vo.getPageIndex(), vo.getPageSize());
        if(vo.getQueryType()==2){
            vo.setCreateUserId(userId);
        }
        List<DeliverOrderApproval> list = deliverOrderApprovalMapper.selectList(vo);
        return new PageInfo<>(list);
    }

    public DeliverOrderApproval deliveryApprovalDetail(Integer deliveryId){
        return deliverOrderApprovalMapper.selectByPrimaryKey(deliveryId);
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
        DeliverOrder deliverOrder = deliverOrderMapper.selectByPrimaryKey(deliveryOrderId);
        List<DeliverOrderLine> deliverOrderLines = deliverOrderLineMapper.selectByDeliveryOrderId(deliveryOrderId);
        deliverOrderLines.forEach(e->{
            OrderLine orderLine = orderLineMapper.selectByPrimaryKey(e.getSalesOrderLineId());
            if(null == orderLine){
                return;
            }
            ProductInfoDO productInfo = productInfoDOMapper.selectBySapMidAndPlatForm(orderLine.getProductId(), orderLine.getPlatform());
            if(null == productInfo){
                return;
            }
            e.setProduct(productInfo.getProduct());
            e.setBu(productInfo.getBu());
            e.setPlatform(productInfo.getPlatform());
            e.setPdt(productInfo.getPdt());
        });

        deliverOrder.setDeliverOrderLineList(deliverOrderLines);
        List<OrderInvoice> orderInvoiceList = orderInvoiceMapper.selectByDeliveryOrderId(deliverOrder.getSapDeliverOrderNo());
        deliverOrder.setOrderInvoiceList(orderInvoiceList);

        return deliverOrder;
    }

    @Transactional
    public void deliveryApprove(DeliveryApproveVO vo, Integer userId){
        DeliverOrderApproval approval = deliverOrderApprovalMapper.selectByPrimaryKey(vo.getDeliveryOrderApprovalId());
        BusinessUtil.assertFlase(null == approval, ErrorCodes.BusinessEnum.ORDER_NOT_FOUND);
        if(approval.getApprovalType().equals(Enums.OrderApprovalType.CREATE.getValue())){
            //新建审批
            approvalCreate(vo, approval);
        }else if(approval.getApprovalType().equals(Enums.OrderApprovalType.UPDATE.getValue())){
            //修改审批
            approvalUpdate(vo, approval);
        }else if(approval.getApprovalType().equals(Enums.OrderApprovalType.CANCEL.getValue())){
            //取消审批
            approvalCancel(vo, approval);
        }else{
            throw new BusinessException("单据类型错误");
        }
        approval.setApprovalUser(userId);
        approval.setApprovalStatus(vo.getApprovalStatus());
        approval.setApprovalRemark(vo.getRemark());
        approval.setApprovalDate(new Date());
        deliverOrderApprovalMapper.updateByPrimaryKeySelective(approval);
    }

    private void approvalCreate(DeliveryApproveVO vo, DeliverOrderApproval approval){
        //如果是通过，调用ECC创单接口
        try{
            if(vo.getApprovalStatus().equals(Enums.OrderApprovalStatus.ADOPT.getValue())){
                ZrfcsddeliverycreateResponse response = eccDeliveryCreate(approval, approval.getSerializelDeliveryOrderLine());
                if(response.getResulttype().equals("0")){
                    DeliverOrder deliverOrder = new DeliverOrder();
                    BeanUtils.copyNotNullFields(approval, deliverOrder);
                    deliverOrder.setSapDeliverOrderNo(response.getSapdeliveryid());
                    deliverOrderMapper.insertSelective(deliverOrder);
                    for(DeliverOrderLine line: approval.getSerializelDeliveryOrderLine()){
                        OrderLine orderLine = orderLineMapper.selectByPrimaryKey(line.getSalesOrderLineId());
                        minusOrderLineNumber(orderLine, line.getDeliveryQuantity());

                        DeliverOrderLine deliverOrderLine = new DeliverOrderLine();
                        BeanUtils.copyNotNullFields(line, deliverOrderLine);
                        deliverOrderLine.setDeliverOrderId(deliverOrder.getDeliverOrderId());
                        deliverOrderLine.setSapDeliverOrderLineNo(orderLine.getRRefItemNo());
                        deliverOrderLineMapper.insertSelective(deliverOrderLine);
                    }
                }else{
                    throw new BusinessException(response.getResultmessage());
                }
            }
        }catch (Exception e){
            log.error("",e);
            throw new BusinessException(e.getMessage());
        }
    }

    //TODO 取消逻辑
    private void approvalUpdate(DeliveryApproveVO vo, DeliverOrderApproval approval){
        //如果是通过，调用ECC修改接口
        if(vo.getApprovalStatus().equals(Enums.OrderApprovalStatus.ADOPT.getValue())){
            DeliverOrder deliverOrder = deliverOrderMapper.selectByPrimaryKey(approval.getSerializelDeliveryOrderLine().get(0).getDeliverOrderId());
            BusinessUtil.assertFlase(null == deliverOrder,ErrorCodes.BusinessEnum.ORDER_NOT_FOUND);
            deliverOrder.setDeliverDate(approval.getDeliverDate());
            deliverOrder.setShippingPoint(approval.getShippingPoint());

            ZrfcsddeliverychangeResponse response = eccDeliveryUpdate(deliverOrder, approval.getSerializelDeliveryOrderLine(),"U");
            if(response.getResulttype().equals("0")){
                deliverOrder.setActualDeliveryDate("");
                deliverOrderMapper.updateByPrimaryKeySelective(deliverOrder);

                approval.getSerializelDeliveryOrderLine().forEach(e->{
                    DeliverOrderLine deliverOrderLine = deliverOrderLineMapper.selectByPrimaryKey(e.getDeliverOrderLineId());
                    OrderLine orderLine = orderLineMapper.selectByPrimaryKey(e.getSalesOrderLineId());
                    Integer deliveryQuantity = e.getDeliveryQuantity() - deliverOrderLine.getDeliveryQuantity();
                    if(e.getDeliveryQuantity() > deliverOrderLine.getDeliveryQuantity()){
                        deliveryQuantity = 0-deliveryQuantity;
                    }
                    minusOrderLineNumber(orderLine, deliveryQuantity);
                    deliverOrderLine.setDeliveryQuantity(e.getDeliveryQuantity());
                    deliverOrderLineMapper.updateByPrimaryKeySelective(deliverOrderLine);
                });
            }
        }
    }

    private void minusOrderLineNumber(OrderLine o, Integer deliveryQuantity){
        BusinessUtil.assertFlase(o.getActice() == 0, ErrorCodes.BusinessEnum.ORDER_IS_INACTIVE);
        BusinessUtil.assertFlase(deliveryQuantity > 0 && o.getRemainingNum() < deliveryQuantity, ErrorCodes.BusinessEnum.ORDER_QTY_IS_ENOUGH);
        o.setRemainingNum(o.getRemainingNum()-deliveryQuantity);
        orderLineMapper.updateByPrimaryKeySelective(o);
    }

    private void approvalCancel(DeliveryApproveVO vo, DeliverOrderApproval approval){
        //如果是通过，调用ECC修改接口
        if(vo.getApprovalStatus().equals(Enums.OrderApprovalStatus.ADOPT.getValue())){
            DeliverOrder deliverOrder = deliverOrderMapper.selectByPrimaryKey(approval.getSerializelDeliveryOrderLine().get(0).getDeliverOrderId());
            BusinessUtil.assertFlase(null == deliverOrder,ErrorCodes.BusinessEnum.ORDER_NOT_FOUND);

            ZrfcsddeliverychangeResponse response = eccDeliveryUpdate(deliverOrder, approval.getSerializelDeliveryOrderLine(),"D");
            if(response.getResulttype().equals("0")){
                List<DeliverOrderLine> deliverOrderLines = deliverOrderLineMapper.selectByDeliveryOrderId(deliverOrder.getDeliverOrderId());
                if(approval.getDeliveryOrderLine().size() == deliverOrderLines.size()){
                    deliverOrder.setActive(0);
                    deliverOrderMapper.updateByPrimaryKeySelective(deliverOrder);
                }
                approval.getSerializelDeliveryOrderLine().forEach(e->{
                    DeliverOrderLine deliverOrderLine = deliverOrderLineMapper.selectByPrimaryKey(e.getDeliverOrderLineId());
                    OrderLine orderLine = orderLineMapper.selectByPrimaryKey(deliverOrderLine.getSalesOrderLineId());
                    orderLine.setRemainingNum(orderLine.getRemainingNum()+deliverOrderLine.getDeliveryQuantity());
                    orderLineMapper.updateByPrimaryKeySelective(orderLine);
                    deliverOrderLine.setActive(0);
                    deliverOrderLineMapper.updateByPrimaryKeySelective(deliverOrderLine);
                });
            }
        }
    }

    private ZrfcsddeliverycreateResponse eccDeliveryCreate(DeliverOrderApproval deliverOrder, List<DeliverOrderLine> deliverOrderLines) throws Exception{
        ZrfcsdDeliveryCreateContent content = new ZrfcsdDeliveryCreateContent();
        content.setDeliverydate(DateUtil.format(deliverOrder.getDeliverDate(),DateUtil.WEB_FORMAT));
        content.setDeliveryIoc(deliverOrder.getShippingPoint());
        content.setPortalDeliveryId(String.valueOf(deliverOrder.getDeliverOrderId()));
        content.setSapOrderId(deliverOrder.getSapOrderNo().replaceAll("^(0+)", ""));
        content.setTItem(gettItemcreate(deliverOrderLines));
        ZrfcsdDeliveryCreateBody body = new ZrfcsdDeliveryCreateBody(content);
        ZrfcsdDeliveryCreate create = new ZrfcsdDeliveryCreate(body);
        return orderApiService.deliveryCreate(create);
    }

    private com.crazy.portal.bean.order.wsdl.delivery.create.TItem gettItemcreate(List<DeliverOrderLine> deliverOrderLineList){
        com.crazy.portal.bean.order.wsdl.delivery.create.TItem tItem = new com.crazy.portal.bean.order.wsdl.delivery.create.TItem();
        List<com.crazy.portal.bean.order.wsdl.delivery.create.Item> items = new ArrayList<>();
        deliverOrderLineList.forEach(e->{
            List<OrderLine> liens = orderLineMapper.selectByProduct(e.getSalesOrderId(), e.getProductId());
            liens.forEach(o->{
                com.crazy.portal.bean.order.wsdl.delivery.create.Item item = new com.crazy.portal.bean.order.wsdl.delivery.create.Item();
                item.setDeliveryQuantity(String.valueOf(e.getDeliveryQuantity()));
                item.setDeliveryItemNo("");
                item.setItemNo(o.getRItemNo().replaceAll("^(0+)", ""));
                item.setProductId(o.getProductId());
                items.add(item);
            });
        });
        tItem.setItems(items);
        return tItem;
    }

    public ZrfcsddeliverychangeResponse eccDeliveryUpdate(DeliverOrder order, List<DeliverOrderLine> deliverOrderLineList,String type){
        ZrfcsdDeliveryUpdateContent content = new ZrfcsdDeliveryUpdateContent();
        content.setDeliverydate(DateUtil.format(order.getDeliverDate(),DateUtil.WEB_FORMAT));
        content.setSapDeliveryId(order.getSapDeliverOrderNo());
        content.setIType(type);
        content.setTItem(gettItem(deliverOrderLineList,type));
        ZrfcsdDeliveryUpdateBody body = new ZrfcsdDeliveryUpdateBody(content);
        ZrfcsdDeliveryUpdate update = new ZrfcsdDeliveryUpdate(body);
        return orderApiService.deliveryUpdate(update);
    }

    private TItem gettItem(List<DeliverOrderLine> deliverOrderLineList,String type){
        TItem tItem = new TItem();
        List<Item> items = new ArrayList<>();
        deliverOrderLineList.forEach(e->{
            Item item = new Item();
            item.setOperationType(type);
            item.setDeliveryItemNo(e.getSapSalesOrderLineNo());
            item.setDeliveryQuantity(String.valueOf(e.getDeliveryQuantity()));
            items.add(item);
        });
        tItem.setItems(items);
        return tItem;
    }
}
