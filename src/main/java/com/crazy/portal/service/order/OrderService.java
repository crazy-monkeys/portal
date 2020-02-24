package com.crazy.portal.service.order;

import com.crazy.portal.bean.order.DeliveryApproveVO;
import com.crazy.portal.bean.order.DeliveryOrderQueryVO;
import com.crazy.portal.bean.order.OrderQueryBean;
import com.crazy.portal.bean.order.wsdl.delivery.create.ZrfcsdDeliveryCreate;
import com.crazy.portal.bean.order.wsdl.delivery.create.ZrfcsdDeliveryCreateBody;
import com.crazy.portal.bean.order.wsdl.delivery.create.ZrfcsdDeliveryCreateContent;
import com.crazy.portal.bean.order.wsdl.delivery.create.ZrfcsddeliverycreateResponse;
import com.crazy.portal.bean.order.wsdl.delivery.update.*;
import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.dao.order.*;
import com.crazy.portal.dao.product.ProductInfoDOMapper;
import com.crazy.portal.entity.order.*;
import com.crazy.portal.entity.product.ProductInfoDO;
import com.crazy.portal.entity.system.User;
import com.crazy.portal.service.system.UserCustomerMappingService;
import com.crazy.portal.util.*;
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
public class OrderService extends CommonOrderService {
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
    @Resource
    private UserCustomerMappingService userCustomerMappingService;

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
    public PageInfo<DeliverOrder> deliveryOrderList(DeliveryOrderQueryVO vo, User user){
        if(user.getUserType().equals(Enums.USER_TYPE.agent.toString())){
            vo.setDealerId(user.getDealerId());
        }else{
            //内部人员查询 限制销售只能查询自己管理的客户的提货单

        }
        PageHelper.startPage(vo.getPageIndex(), vo.getPageSize());
        List<DeliverOrder> list = deliverOrderMapper.selectList(vo);
        return new PageInfo<>(list);
    }

    /**
     * 提货单审批列表
     * @param vo
     * @return
     */
    public PageInfo<DeliverOrderApproval> deliveryOrderApprovalList(DeliveryOrderQueryVO vo, User user){
        List<Integer> userList = new ArrayList<>();
        if(user.getUserType().equals(Enums.USER_TYPE.internal.toString())){
            userList = userCustomerMappingService.selectUserMapping(user.getId(), Enums.CustomerMappingModel.Order.getValue());
            Integer[] userIds = new Integer[userList.size()];
            userList.toArray(userIds);
        }
        vo.setUserIds(userList);

        if(vo.getQueryType()==2){
            vo.setCreateUserId(user.getDealerId());
        }
        PageHelper.startPage(vo.getPageIndex(), vo.getPageSize());
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

        if(StringUtil.isNotEmpty(order.getOutCode())){
            order.setIsAdd("是");
        }
        List<OrderLine> lines = orderLineMapper.selectByOrderIdForVirtual(id);
        super.resetLines(lines);
        order.setLines(lines);
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
        deliverOrder.setDeliverOrderLineList(deliverOrderLines);
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
            approvalCreate(vo, approval,Enums.OrderApprovalType.CREATE.getValue());
        }else if(approval.getApprovalType().equals(Enums.OrderApprovalType.UPDATE.getValue())){
            //修改审批
            approvalUpdate(vo, approval,Enums.OrderApprovalType.UPDATE.getValue());
        }else if(approval.getApprovalType().equals(Enums.OrderApprovalType.CANCEL.getValue())){
            //取消审批
            approvalCancel(vo, approval,Enums.OrderApprovalType.CANCEL.getValue());
        }else{
            throw new BusinessException("单据类型错误");
        }
        approval.setApprovalUser(userId);
        approval.setApprovalStatus(vo.getApprovalStatus());
        approval.setApprovalRemark(vo.getRemark());
        approval.setApprovalDate(new Date());
        deliverOrderApprovalMapper.updateByPrimaryKeySelective(approval);
    }

    private void approvalCreate(DeliveryApproveVO vo, DeliverOrderApproval approval,String ope){
        //如果是通过，调用ECC创单接口
        try{
            if(vo.getApprovalStatus().equals(Enums.OrderApprovalStatus.ADOPT.getValue())){
                ZrfcsddeliverycreateResponse response = eccDeliveryCreate(approval, approval.getSerializelDeliveryOrderLine(),ope);
                if(response.getResulttype().equals("0")){
                    DeliverOrder deliverOrder = new DeliverOrder();
                    BeanUtils.copyNotNullFields(approval, deliverOrder);
                    deliverOrder.setDeliverOrderId(null);
                    deliverOrder.setSapDeliverOrderNo(response.getSapdeliveryid());
                    deliverOrderMapper.insertSelective(deliverOrder);
                    for(DeliverOrderLine line: approval.getSerializelDeliveryOrderLine()){
                        OrderLine orderLine = orderLineMapper.selectByPrimaryKey(line.getSalesOrderLineId());
                        DeliverOrderLine deliverOrderLine = new DeliverOrderLine();
                        BeanUtils.copyNotNullFields(line, deliverOrderLine);
                        deliverOrderLine.setDeliverOrderId(deliverOrder.getDeliverOrderId());
                        if(null != response.getTitem().getItem()){
                            response.getTitem().getItem().forEach(e->{
                                if(e.getItemno().replaceAll("^(0+)", "").equals(orderLine.getRItemNo())){
                                    deliverOrderLine.setSapDeliverOrderLineNo(e.getDeliveryitemno().replaceAll("^(0+)", ""));
                                }
                            });
                        }
                        deliverOrderLine.setReceiveQuantity(0);
                        deliverOrderLineMapper.insertSelective(deliverOrderLine);
                    }
                    orderLineMapper.updateOrderNum(deliverOrder.getSalesOrderId());
                }else{
                    throw new BusinessException(response.getResultmessage());
                }
            }
        }catch (Exception e){
            log.error("",e);
            throw new BusinessException(e.getMessage());
        }
    }

    private void approvalUpdate(DeliveryApproveVO vo, DeliverOrderApproval approval,String ope){
        //如果是通过，调用ECC修改接口
        if(vo.getApprovalStatus().equals(Enums.OrderApprovalStatus.ADOPT.getValue())){
            DeliverOrder deliverOrder = deliverOrderMapper.selectByPrimaryKey(approval.getSerializelDeliveryOrderLine().get(0).getDeliverOrderId());
            BusinessUtil.assertFlase(null == deliverOrder,ErrorCodes.BusinessEnum.ORDER_NOT_FOUND);
            deliverOrder.setDeliverDate(approval.getDeliverDate());
            deliverOrder.setShippingPoint(approval.getShippingPoint());

            ZrfcsddeliverychangeResponse response = eccDeliveryUpdate(deliverOrder, approval.getSerializelDeliveryOrderLine(),"U",ope);
            if(response.getResulttype().equals("0")){
                deliverOrder.setActualDeliveryDate("");
                deliverOrderMapper.updateByPrimaryKeySelective(deliverOrder);

                approval.getSerializelDeliveryOrderLine().forEach(e->{
                    DeliverOrderLine deliverOrderLine = deliverOrderLineMapper.selectByPrimaryKey(e.getDeliverOrderLineId());
                    deliverOrderLine.setDeliveryQuantity(e.getDeliveryQuantity());
                    deliverOrderLineMapper.updateByPrimaryKeySelective(deliverOrderLine);
                });
                orderLineMapper.updateOrderNum(deliverOrder.getSalesOrderId());
            }else{
                throw new BusinessException(response.getResultmessage());
            }
        }
    }

    private void approvalCancel(DeliveryApproveVO vo, DeliverOrderApproval approval,String ope){
        //如果是通过，调用ECC修改接口
        if(vo.getApprovalStatus().equals(Enums.OrderApprovalStatus.ADOPT.getValue())){
            DeliverOrder deliverOrder = deliverOrderMapper.selectByPrimaryKey(approval.getSerializelDeliveryOrderLine().get(0).getDeliverOrderId());
            BusinessUtil.assertFlase(null == deliverOrder,ErrorCodes.BusinessEnum.ORDER_NOT_FOUND);
            List<DeliverOrderLine> deliverOrderLines = deliverOrderLineMapper.selectByDeliveryOrderId(deliverOrder.getDeliverOrderId());

            String type = "U";
            if(approval.getDeliveryOrderLine().size() == deliverOrderLines.size()){
                type = "D";
            }

            ZrfcsddeliverychangeResponse response = eccDeliveryUpdate(deliverOrder, approval.getSerializelDeliveryOrderLine(),type,ope);
            if(response.getResulttype().equals("0")){
                if(type.equals("D")){
                    deliverOrder.setActive(0);
                    deliverOrderMapper.updateByPrimaryKeySelective(deliverOrder);
                }
                approval.getSerializelDeliveryOrderLine().forEach(e->{
                    DeliverOrderLine deliverOrderLine = deliverOrderLineMapper.selectByPrimaryKey(e.getDeliverOrderLineId());
                    deliverOrderLine.setActive(0);
                    deliverOrderLineMapper.updateByPrimaryKeySelective(deliverOrderLine);
                });
                orderLineMapper.updateOrderNum(deliverOrder.getSalesOrderId());
            }else{
                throw new BusinessException(response.getResultmessage());
            }
        }
    }

    private ZrfcsddeliverycreateResponse eccDeliveryCreate(DeliverOrderApproval deliverOrder, List<DeliverOrderLine> deliverOrderLines,String ope) throws Exception{
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
            //找出对应订单行的信息
            OrderLine orderLine = orderLineMapper.selectByPrimaryKey(e.getSalesOrderLineId());
            items.add(mappingItem(orderLine, e.getDeliveryQuantity()));

            //找出订单行对应的实体料行信息
            List<OrderLine> liens = orderLineMapper.selectByProduct(e.getSalesOrderId(), e.getProductId(), orderLine.getRItemNo());
            liens.forEach(o->{
                items.add(mappingItem(o, e.getDeliveryQuantity()));
            });
        });
        tItem.setItems(items);
        return tItem;
    }

    private com.crazy.portal.bean.order.wsdl.delivery.create.Item mappingItem(OrderLine orderLine, Integer delivery){
        com.crazy.portal.bean.order.wsdl.delivery.create.Item item = new com.crazy.portal.bean.order.wsdl.delivery.create.Item();
        item.setDeliveryQuantity(String.valueOf(delivery));
        item.setDeliveryItemNo("");
        item.setItemNo(orderLine.getRItemNo().replaceAll("^(0+)", ""));
        item.setProductId(orderLine.getProductId());
        return item;
    }

    public ZrfcsddeliverychangeResponse eccDeliveryUpdate(DeliverOrder order, List<DeliverOrderLine> deliverOrderLineList,String type,String ope){
        ZrfcsdDeliveryUpdateContent content = new ZrfcsdDeliveryUpdateContent();
        content.setDeliverydate(DateUtil.format(order.getDeliverDate(),DateUtil.WEB_FORMAT));
        content.setSapDeliveryId(order.getSapDeliverOrderNo());
        content.setIType(type);
        content.setTItem(gettItem(deliverOrderLineList,ope));
        ZrfcsdDeliveryUpdateBody body = new ZrfcsdDeliveryUpdateBody(content);
        ZrfcsdDeliveryUpdate update = new ZrfcsdDeliveryUpdate(body);
        return orderApiService.deliveryUpdate(update);
    }

    private TItem gettItem(List<DeliverOrderLine> deliverOrderLineList,String ope){
        TItem tItem = new TItem();
        List<Item> items = new ArrayList<>();
        deliverOrderLineList.forEach(e->{
            OrderLine orderLine = orderLineMapper.selectByPrimaryKey(e.getSalesOrderLineId());
            items.add(mappingUpdateItem(e.getSapDeliverOrderLineNo(),String.valueOf(e.getDeliveryQuantity()),ope));

            //找出订单行对应的实体料行信息
            List<OrderLine> liens = orderLineMapper.selectByProduct(e.getSalesOrderId(), e.getProductId(), orderLine.getRItemNo());
            Integer no = Integer.valueOf(e.getSapDeliverOrderLineNo());
            for(OrderLine o : liens){
                no = no+10;
                items.add(mappingUpdateItem(String.valueOf(no),String.valueOf(e.getDeliveryQuantity()),ope));
            }
        });
        tItem.setItems(items);
        return tItem;
    }

    private Item mappingUpdateItem(String orderLineNo, String qty, String ope){
        Item item = new Item();
        if(ope.equals("UPDATE")){
            item.setOperationType("U");
        }else{
            item.setOperationType("D");
        }

        item.setDeliveryItemNo(orderLineNo);
        item.setDeliveryQuantity(qty);
        return item;
    }
}
