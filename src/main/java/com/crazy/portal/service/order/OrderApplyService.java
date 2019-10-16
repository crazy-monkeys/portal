package com.crazy.portal.service.order;

import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.crazy.portal.bean.order.*;
import com.crazy.portal.bean.order.wsdl.price.*;
import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.dao.cusotmer.CustomerInfoMapper;
import com.crazy.portal.dao.order.*;
import com.crazy.portal.dao.product.ProductInfoDOMapper;
import com.crazy.portal.entity.cusotmer.CustomerInfo;
import com.crazy.portal.entity.order.*;
import com.crazy.portal.entity.product.ProductInfoDO;
import com.crazy.portal.service.customer.CustomerInfoService;
import com.crazy.portal.util.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 10:54 2019-09-21
 * @Modified by:
 */
@Slf4j
@Service
public class OrderApplyService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderLineMapper orderLineMapper;
    @Resource
    private OrderApplyMapper orderApplyMapper;
    @Resource
    private ProductInfoDOMapper productInfoDOMapper;
    @Resource
    private OrderApiService orderApiService;
    @Resource
    private DeliverOrderMapper deliverOrderMapper;
    @Resource
    private DeliverOrderApprovalMapper deliverOrderApprovalMapper;
    @Resource
    private DeliverOrderLineMapper deliverOrderLineMapper;
    @Resource
    private CustomerInfoMapper customerInfoMapper;
    @Resource
    private CustomerInfoService customerInfoService;

    /**
     * 分页查询
     * @param bean
     * @return
     */
    public PageInfo<OrderApply> list(OrderQueryBean bean){
        PageHelper.startPage(bean.getPageIndex(), bean.getPageSize());
        List<OrderApply> list = orderApplyMapper.selectByPage(bean);
        return new PageInfo<>(list);
    }


    /**
     * 模板下载
     */
    public void downloadLineTmpl(HttpServletResponse response) throws Exception{
        Map<String, List<? extends BaseRowModel>> resultMap = new HashMap<>();
        resultMap.put("sheet1", Collections.singletonList(new OrderLineEO()));
        ExcelUtils.createExcelStreamMutilByEaysExcel(response, resultMap, "订单行模板", ExcelTypeEnum.XLSX);
    }

    /**
     * 解析附件并作调价试算
     * @return
     */
    public Map<String,Object> parsingLineTmplFile(OrderApply order) throws Exception{
        Map<String,Object> map = new HashMap<>();
        if(order.getSalesOrg().equals("3000")){
            order.setPaymentTerms("9994");
        }
        List<OrderLineEO> records = ExcelUtils.readExcel(order.getLineFile(), OrderLineEO.class);
        //逻辑只允许出现同一个月份
        Date expectedDeliveryMonth = records.get(0).getExpectedDeliveryMonth();
        BusinessUtil.notNull(expectedDeliveryMonth,ErrorCodes.BusinessEnum.ORDER_EMPTY_EXPECTEDDELIVERYMONTH);

        String priceDate = DateUtil.getLastDayOfMonth(DateUtil.getYear(expectedDeliveryMonth),DateUtil.getMonth(expectedDeliveryMonth));
        IsHeader isHeader = this.buildIsHeader(order);
        isHeader.setPricedate(priceDate);
        ItItems itItems = new ItItems();
        itItems.setItem(this.buildItItems(records));

        Zrfcsdpricesimulate zrfcsdpricesimulate = this.getZrfcsdpricesimulate(isHeader, new EtItems(),itItems);
        ZrfcsdpricesimulateResponse response = orderApiService.priceSimulate(zrfcsdpricesimulate);
        BusinessUtil.notNull(response,ErrorCodes.CommonEnum.SYSTEM_EXCEPTION);

        ZpricessimulateHeaderOut esHeader = response.getEsHeader();
        List<ZpricessimulateItemOut> items = response.getEtItems().getItem();

        for(ZpricessimulateItemOut item : items){
            for(OrderLineEO orderLineEO : records){
                //设置定价
                orderLineEO.setPriceDate(priceDate);
                //对方返回默认追加7个0,此处兼容
                if(item.getProductid().substring(7).equals(orderLineEO.getProductId())){
                    BigDecimal price = item.getPrice();
                    orderLineEO.setRPrice(price == null ? BigDecimal.ZERO : price);
                    BigDecimal netprice = item.getNetprice();
                    orderLineEO.setRNetPrice(netprice == null ? BigDecimal.ZERO : netprice);
                }
            }
        }

        map.put("lines",records);
        map.put("grossValue",esHeader.getGrossvalue());
        map.put("netValue",esHeader.getNetvalue());
        return map;
    }

    /**
     * 订单申请
     * @param order
     * @param userId
     */
    @Transactional
    public void createOrderApply(OrderApply order, Integer userId){
        BusinessUtil.notNull(order, ErrorCodes.BusinessEnum.ORDER_INFO_IS_REQUIRED);
        BusinessUtil.notNull(order.getOrderLines(), ErrorCodes.BusinessEnum.ORDER_LINES_IS_REQUIRED);

        CustomerInfo dealerByUser = customerInfoService.getDealerByUser(userId);
        BusinessUtil.notNull(dealerByUser, ErrorCodes.BusinessEnum.CUSTOMER_IS_EMPYT);
        order.setDealerId(dealerByUser.getId());
        order.setApprovalStatus(Enums.OrderApprovalStatus.WAIT_APPROVAL.getValue());
        order.setCreateId(userId);
        order.setCreateTime(DateUtil.getCurrentTS());
        order.setActive(1);
        order.setAppalyType(1);
        if(order.getSalesOrg().equals("3000")){
            order.setPaymentTerms("9994");
        }

        List<OrderLine> orderLines = order.getOrderLines();
        Date priceDate = this.getPriceDate(orderLines.get(0).getExpectedDeliveryMonth());
        order.setPriceDate(DateUtil.getLastDayOfMonth(DateUtil.getYear(priceDate),DateUtil.getMonth(priceDate)));

        orderLines.forEach(x->{
            x.setCreateId(userId);
            x.setCreateTime(DateUtil.getCurrentTS());
            x.setActice(1);
            Date expectedDeliveryMonth = this.getPriceDate(x.getExpectedDeliveryMonth());
            x.setExpectedDeliveryDate(DateUtil.getLastDayOfMonth(DateUtil.getYear(expectedDeliveryMonth),DateUtil.getMonth(expectedDeliveryMonth)));
        });
        order.setJsonLines(order.objToLineJson(orderLines));
        orderApplyMapper.insertSelective(order);
    }

    /**
     * 订单修改
     * @param order
     * @param userId
     */
    @Transactional
    public void update(OrderApply order, Integer userId) throws Exception{
        Integer applyId = order.getId();
        BusinessUtil.notNull(applyId,ErrorCodes.BusinessEnum.ORDER_NOT_FOUND);

        OrderApply orderApply = orderApplyMapper.selectByPrimaryKey(applyId);
        BusinessUtil.notNull(orderApply,ErrorCodes.BusinessEnum.ORDER_NOT_FOUND);

        //只允许修改被驳回订单
        boolean isRejec = orderApply.getApprovalStatus().equals(Enums.OrderApprovalStatus.REJEC.getValue());
        BusinessUtil.assertTrue(isRejec,ErrorCodes.BusinessEnum.ORDER_APPROVE_REJECT_ORDER);

        CustomerInfo dealerByUser = customerInfoService.getDealerByUser(userId);
        BusinessUtil.notNull(dealerByUser, ErrorCodes.BusinessEnum.CUSTOMER_IS_EMPYT);

        BeanUtils.copyNotNullFields(order,orderApply);
        order.setDealerId(dealerByUser.getId());
        order.setApprovalStatus(Enums.OrderApprovalStatus.WAIT_APPROVAL.getValue());
        order.setUpdateId(userId);
        order.setUpdateTime(DateUtil.getCurrentTS());
        order.setApprovalStatus(Enums.OrderApprovalStatus.WAIT_APPROVAL.getValue());

        List<OrderLine> orderLines = order.getOrderLines();
        orderLines.forEach(x->{
            Date priceDate = this.getPriceDate(x.getExpectedDeliveryMonth());
            x.setExpectedDeliveryDate(DateUtil.getLastDayOfMonth(DateUtil.getYear(priceDate),DateUtil.getMonth(priceDate)));
            x.setUpdateId(userId);
            x.setUpdateTime(DateUtil.getCurrentTS());
        });
        order.setJsonLines(order.objToLineJson(orderLines));
        orderApplyMapper.updateByPrimaryKeySelective(order);
    }

    private Date getPriceDate(String expectedDeliveryMonth) {
        Date priceDate;
        try {
            priceDate = DateUtil.parseDate(expectedDeliveryMonth,DateUtil.MONTH_FORMAT_HLINE);
        } catch (ParseException e) {
            log.error("",e);
            throw new IllegalArgumentException("参数转换错误");
        }
        return priceDate;
    }

    /**
     * 审批通过-订单修改申请
     * @param userId
     */
    @Transactional
    public void modifyOrderApply(Integer orderId,OrderApply orderApply, Integer userId){

        Order order = orderMapper.selectByPrimaryKey(orderId);
        BusinessUtil.notNull(order, ErrorCodes.BusinessEnum.ORDER_NOT_FOUND);
        String rSapOrderId = order.getRSapOrderId();
        boolean result = this.hasApprovalPendingOrder(rSapOrderId);
        BusinessUtil.assertFlase(result, ErrorCodes.BusinessEnum.ORDER_PENDING_ORDER);

        orderApply.setId(null);
        orderApply.setActive(1);
        orderApply.setCreateId(userId);
        orderApply.setCreateTime(DateUtil.getCurrentTS());
        orderApply.setAppalyType(2);
        orderApply.setApprovalStatus(Enums.OrderApprovalStatus.WAIT_APPROVAL.getValue());
        orderApply.setJsonLines(orderApply.objToLineJson(orderApply.getOrderLines()));

        orderApply.setRSapOrderId(rSapOrderId);
        orderApplyMapper.insertSelective(orderApply);
    }

    /**
     * 检查是否已经存在待审批的订单
     * @param rSapOrderId
     * @return
     */
    private boolean hasApprovalPendingOrder(String rSapOrderId){
        List<OrderApply> orderApplies = orderApplyMapper.selectBySapOrderId(rSapOrderId);
        orderApplies = orderApplies.stream()
                .filter(x -> x.getApprovalStatus().equals(Enums.OrderApprovalStatus.WAIT_APPROVAL.getValue()))
                .collect(Collectors.toList());

        return !orderApplies.isEmpty();
    }
    /**
     * 审批通过-取消订单申请
     * @param itemIds
     * @param userId
     */
    @Transactional
    public void cancelOrderApply(Integer orderId,Set<Integer> itemIds, Integer userId) throws Exception {

        Order order = orderMapper.selectByPrimaryKey(orderId);
        BusinessUtil.notNull(order, ErrorCodes.BusinessEnum.ORDER_NOT_FOUND);
        String rSapOrderId = order.getRSapOrderId();
        boolean result = this.hasApprovalPendingOrder(rSapOrderId);
        BusinessUtil.assertFlase(result, ErrorCodes.BusinessEnum.ORDER_PENDING_ORDER);

        OrderApply orderApply = new OrderApply();
        List<OrderLine> lines = itemIds.stream()
                .map(x->{
                    OrderLine orderLine = orderLineMapper.selectByPrimaryKey(x);
                    orderLine.setActice(0);
                    return orderLine;
                }).collect(Collectors.toList());

        BusinessUtil.assertFlase(itemIds.size() != lines.size(),ErrorCodes.BusinessEnum.ORDER_LINE_NOT_FOUND);

        Set<Integer> orderIds = lines.stream().map(x->x.getOrderId()).collect(Collectors.toSet());
        BusinessUtil.assertFlase(orderIds.size() > 1,ErrorCodes.BusinessEnum.ORDER_LINE_NOT_FOUND);

        BeanUtils.copyNotNullFields(order,orderApply);
        orderApply.setId(null);
        orderApply.setActive(1);
        orderApply.setCreateId(userId);
        orderApply.setCreateTime(DateUtil.getCurrentTS());
        orderApply.setAppalyType(3);
        orderApply.setApprovalStatus(Enums.OrderApprovalStatus.WAIT_APPROVAL.getValue());
        orderApply.setJsonLines(orderApply.objToLineJson(lines));
        orderApplyMapper.insertSelective(orderApply);
    }

    /**
     * 变更交货日期
     */
    public void modifyDeliveryDateApply(Integer orderId, List<DeliveryChangeVO> changeVOS, Integer userId) throws Exception{

        BusinessUtil.assertTrue(!changeVOS.isEmpty(),ErrorCodes.BusinessEnum.ORDER_LINES_IS_REQUIRED);

        OrderApply orderApply = new OrderApply();
        Order order = orderMapper.selectByPrimaryKey(orderId);
        BusinessUtil.notNull(order, ErrorCodes.BusinessEnum.ORDER_NOT_FOUND);

        List<OrderLine> lines = new ArrayList<>();
        changeVOS.forEach(x->{
            OrderLine orderLine = orderLineMapper.selectByPrimaryKey(x.getItemId());
            BusinessUtil.notNull(orderLine,ErrorCodes.BusinessEnum.ORDER_LINE_NOT_FOUND);

            orderLine.setExpectedDeliveryDate(DateUtil.format(x.getExpectedDeliveryDate(),DateUtil.SHORT_FORMAT));
            orderLine.setUpdateId(userId);
            orderLine.setUpdateTime(DateUtil.getCurrentTS());
            lines.add(orderLine);
        });

        BeanUtils.copyNotNullFields(order,orderApply);
        orderApply.setId(null);
        orderApply.setActive(1);
        orderApply.setCreateId(userId);
        orderApply.setCreateTime(DateUtil.getCurrentTS());
        orderApply.setApprovalStatus(Enums.OrderApprovalStatus.WAIT_APPROVAL.getValue());
        orderApply.setAppalyType(4);
        orderApply.setJsonLines(orderApply.objToLineJson(lines));
        orderApplyMapper.insertSelective(orderApply);
    }

    private List<ItItem> buildItItems(List<OrderLineEO> records) {
        List<ItItem> items = new ArrayList<>();
        for(int i = 0;i<records.size();i++){

            OrderLineEO orderLineEO = records.get(i);
            String productId = orderLineEO.getProductId();
            String num = orderLineEO.getNum();
            String platform = orderLineEO.getPlatform();
            Date expectedDeliveryMonth = orderLineEO.getExpectedDeliveryMonth();

            BusinessUtil.assertEmpty(productId,ErrorCodes.BusinessEnum.ORDER_EMPTY_PRODUCT_ID);
            BusinessUtil.assertEmpty(num,ErrorCodes.BusinessEnum.ORDER_EMPTY_NUM);
            BusinessUtil.assertEmpty(platform,ErrorCodes.BusinessEnum.ORDER_EMPTY_PLATFORM);
            BusinessUtil.notNull(expectedDeliveryMonth,ErrorCodes.BusinessEnum.ORDER_EMPTY_EXPECTEDDELIVERYMONTH);

            ProductInfoDO params = new ProductInfoDO();
            params.setSapMid(productId);
            params.setPlatform(platform);
            ProductInfoDO productInfoDO = productInfoDOMapper.selectBySapMidAndPlatForm(productId,platform);
            BusinessUtil.notNull(productInfoDO, ErrorCodes.BusinessEnum.ORDER_NOT_EXISTS_PRODUCT_ID);

            ItItem itItem = new ItItem();
            itItem.setSequenceno((i+1)+"");
            itItem.setProductid(productId);
            itItem.setOrderquantity(num);
            itItem.setKondm(productInfoDO.getBu());
            //根据物料号获取平台
            itItem.setPlatform(productInfoDO.getPlatform());
            items.add(itItem);
        }
        return items;
    }

    /**
     * 组装wsdl传输结构
     * @param isHeader
     * @param itItems
     * @return
     */
    private Zrfcsdpricesimulate getZrfcsdpricesimulate(IsHeader isHeader, EtItems etItems,ItItems itItems) {
        ZrfcsdpricesimulateContent content = new ZrfcsdpricesimulateContent(etItems,isHeader,itItems);
        ZrfcsdpricesimulateBody body = new ZrfcsdpricesimulateBody(content);
        return new Zrfcsdpricesimulate(body);
    }

    /**
     * 组装订单头入参
     * @param orderApply
     * @param orderApply
     * @return
     */
    private IsHeader buildIsHeader(OrderApply orderApply) {
        IsHeader isHeader = new IsHeader();
        isHeader.setOrdertype(orderApply.getUnderOrderType());
        isHeader.setSalesorg(orderApply.getSalesOrg());

        CustomerInfo soldToCustomer = this.getCustomerOutCode(orderApply.getSoldTo());
        BusinessUtil.notNull(soldToCustomer,ErrorCodes.BusinessEnum.CUSTOMER_IS_EMPYT);
        isHeader.setSoldto(soldToCustomer.getOutCode());

        CustomerInfo sendToCustomer = this.getCustomerOutCode(orderApply.getSendTo());
        BusinessUtil.notNull(sendToCustomer,ErrorCodes.BusinessEnum.CUSTOMER_IS_EMPYT);
        isHeader.setSendto(sendToCustomer.getOutCode());
        return isHeader;
    }

    private CustomerInfo getCustomerOutCode(String soldTo) {
        return customerInfoMapper.selectByPrimaryKey(Integer.parseInt(soldTo));
    }

    @Transactional
    public void submitApplyDelivery(DeliveryOrderVO bean, Integer userId){
        Order order = orderMapper.selectByPrimaryKey(bean.getOrderId());
        if(null == order || order.getActive() == 0){
            throw new BusinessException(ErrorCodes.BusinessEnum.ORDER_NOT_FOUND);
        }
        saveDeliverOrderApproval(bean, order, userId);
    }

    private void saveDeliverOrderApproval(DeliveryOrderVO bean, Order order, Integer userId){
        DeliverOrderApproval approval = new DeliverOrderApproval();
        approval.setSalesOrderId(order.getId());
        approval.setSapOrderNo(order.getRSapOrderId());
        approval.setSendTo(order.getSendTo());
        approval.setSoldTo(order.getSoldTo());
        approval.setDeliverDate(bean.getDeliverDate());
        approval.setShippingPoint(bean.getShippingPoint());
        approval.setApprovalStatus(Enums.OrderApprovalStatus.WAIT_APPROVAL.getValue());
        approval.setApprovalType(Enums.OrderApprovalType.CREATE.getValue());
        approval.setActive(1);
        approval.setCreateUserId(userId);

        List<OrderLine> orderLine = orderLineMapper.selectByOrderId(order.getId());
        if(bean.getOrderLine().isEmpty()){
            throw new BusinessException("请选择需要提货的订单");
        }
        List<DeliverOrderLine> lines = new ArrayList<>();
        orderLine.forEach(o->{
            bean.getOrderLine().forEach(e->{
                if(o.getId().equals(e.getId())){
                    BusinessUtil.assertFlase(o.getRemainingNum() < e.getDeliveryQuantity(),ErrorCodes.BusinessEnum.ORDER_QTY_IS_ENOUGH);
                    DeliverOrderLine deliverOrderLine = new DeliverOrderLine();
                    deliverOrderLine.setSalesOrderId(order.getId());
                    deliverOrderLine.setSapSalesOrderLineNo(order.getRSapOrderId());
                    deliverOrderLine.setSalesOrderLineId(o.getId());
                    deliverOrderLine.setSapSalesOrderLineNo(o.getRItemNo());
                    deliverOrderLine.setProductId(o.getProductId());
                    deliverOrderLine.setDeliveryQuantity(e.getDeliveryQuantity());
                    deliverOrderLine.setActive(1);
                    deliverOrderLine.setCreateUserId(userId);
                    lines.add(deliverOrderLine);
                }
            });
        });

        approval.setDeliveryOrderLine(approval.setSerializelDeliveryOrderLine(lines));
        deliverOrderApprovalMapper.insertSelective(approval);
    }

    @Transactional
    public void updateDeliveryOrder(DeliverOrder order, Integer userId){
        DeliverOrder deliverOrder = deliverOrderMapper.selectByPrimaryKey(order.getDeliverOrderId());
        BusinessUtil.assertFlase(null == deliverOrder, ErrorCodes.BusinessEnum.ORDER_NOT_FOUND);
        List<DeliverOrderApproval> approval = deliverOrderApprovalMapper.checkApproval(deliverOrder.getSapOrderNo());
        BusinessUtil.assertFlase(null != approval && !approval.isEmpty(), ErrorCodes.BusinessEnum.ORDER_IS_INACTIVE);
        //生成修改审批单
        saveDeliverOrderUpdateApproval(order, deliverOrder, Enums.OrderApprovalType.UPDATE.getValue(), userId);
    }

    @Transactional
    public void cancelDeliveryOrder(DeliveryOrderCancelVO vo, Integer userId){
        DeliverOrder deliverOrder = deliverOrderMapper.selectByPrimaryKey(vo.getDeliveryOrderId());
        BusinessUtil.assertFlase(null == deliverOrder,ErrorCodes.BusinessEnum.ORDER_NOT_FOUND);
        List<DeliverOrderApproval> approval = deliverOrderApprovalMapper.checkApproval(deliverOrder.getSapOrderNo());
        BusinessUtil.assertFlase(null != approval && !approval.isEmpty(), ErrorCodes.BusinessEnum.ORDER_IS_INACTIVE);

        List<DeliverOrderLine> orderLines = deliverOrderLineMapper.selectByDeliveryOrderId(deliverOrder.getDeliverOrderId());
        deliverOrder.setDeliverOrderLineList(orderLines);
        saveDeliverOrderCancelApproval(deliverOrder, vo.getDeliveryOrderLineIds(), userId);
    }

    private void saveDeliverOrderUpdateApproval(DeliverOrder order, DeliverOrder oldOrder, String approvalType, Integer userId){
        DeliverOrderApproval approval = getDeliverOrderApproval(oldOrder, approvalType, userId);
        order.getDeliverOrderLineList().forEach(e->{
            OrderLine orderLine = orderLineMapper.selectByPrimaryKey(e.getSalesOrderLineId());
            BusinessUtil.assertFlase(orderLine.getRemainingNum() < e.getDeliveryQuantity(), ErrorCodes.BusinessEnum.ORDER_QTY_IS_ENOUGH);
            BusinessUtil.assertFlase(e.getActive()==0, ErrorCodes.BusinessEnum.ORDER_IS_INACTIVE);
        });
        approval.setDeliveryOrderLine(approval.setSerializelDeliveryOrderLine(order.getDeliverOrderLineList()));
        deliverOrderApprovalMapper.insertSelective(approval);
    }

    private void saveDeliverOrderCancelApproval(DeliverOrder deliverOrder, List<Integer> cancelDeliverOrderLineIds, Integer userId){
        DeliverOrderApproval approval = getDeliverOrderApproval(deliverOrder, Enums.OrderApprovalType.CANCEL.getValue(), userId);

        List<DeliverOrderLine> deliveryOrderLine = new ArrayList<>();
        deliverOrder.getDeliverOrderLineList().forEach(e->{
            cancelDeliverOrderLineIds.forEach(o->{
                if(e.getDeliverOrderLineId().equals(o)){
                    BusinessUtil.assertFlase(e.getActive()==0, ErrorCodes.BusinessEnum.ORDER_IS_INACTIVE);
                    deliveryOrderLine.add(e);
                }
            });
        });
        approval.setDeliveryOrderLine(approval.setSerializelDeliveryOrderLine(deliveryOrderLine));
        deliverOrderApprovalMapper.insertSelective(approval);
    }

    private DeliverOrderApproval getDeliverOrderApproval(DeliverOrder order, String approvalType, Integer userId){
        DeliverOrderApproval approval = new DeliverOrderApproval();
        approval.setDeliverOrderId(null);
        approval.setShippingPoint(order.getShippingPoint());
        approval.setDeliverDate(order.getDeliverDate());
        approval.setSalesOrderId(order.getSalesOrderId());
        approval.setSapOrderNo(order.getSapOrderNo());
        approval.setSoldTo(order.getSoldTo());
        approval.setSendTo(order.getSendTo());
        approval.setApprovalStatus(Enums.OrderApprovalStatus.WAIT_APPROVAL.getValue());
        approval.setApprovalType(approvalType);
        approval.setActive(1);
        approval.setCreateUserId(userId);
        approval.setCreateTime(new Date());
        return approval;
    }

    @Transactional
    public void deleteDeliveryOrder(Integer id){
        DeliverOrderApproval deliverOrder = deliverOrderApprovalMapper.selectByPrimaryKey(id);
        BusinessUtil.assertTrue(deliverOrder.getApprovalStatus().equals(Enums.OrderApprovalStatus.REJEC.getValue()),ErrorCodes.BusinessEnum.ORDER_NO_DELETE);
        deliverOrderApprovalMapper.deleteByPrimaryKey(id);
    }
}
