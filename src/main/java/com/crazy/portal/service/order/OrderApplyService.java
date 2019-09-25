package com.crazy.portal.service.order;

import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.crazy.portal.bean.order.DeliveryChangeVO;
import com.crazy.portal.bean.order.DeliveryOrderCancelVO;
import com.crazy.portal.bean.order.DeliveryOrderVO;
import com.crazy.portal.bean.order.OrderLineEO;
import com.crazy.portal.bean.order.wsdl.price.*;
import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.dao.order.*;
import com.crazy.portal.dao.product.ProductInfoDOMapper;
import com.crazy.portal.entity.cusotmer.CustomerInfo;
import com.crazy.portal.entity.order.*;
import com.crazy.portal.entity.product.ProductInfoDO;
import com.crazy.portal.service.customer.CustomerInfoService;
import com.crazy.portal.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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
    private DeliverOrderLineMapper deliverOrderLineMapper;
    @Resource
    private CustomerInfoService customerInfoService;

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
    public Map<String,Object> parsingLineTmplFile(OrderApply order){
        Map<String,Object> map = new HashMap<>();
        if(order.getSalesOrg().equals("3000")){
            order.setPaymentTerms("9994");
        }
        List<OrderLineEO> records = ExcelUtils.readExcel(order.getLineFile(), OrderLineEO.class);
        //逻辑只允许出现同一个月份
        Date expectedDeliveryMonth = records.get(0).getExpectedDeliveryMonth();
        String priceDate = DateUtil.getLastDayOfMonth(DateUtil.getYear(expectedDeliveryMonth),DateUtil.getMonth(expectedDeliveryMonth));
        IsHeader isHeader = this.buildIsHeader(order);
        isHeader.setPricedate(priceDate);
        ItItems itItems = new ItItems();
        itItems.setItem(this.buildItItems(records));

        Zrfcsdpricesimulate zrfcsdpricesimulate = this.getZrfcsdpricesimulate(isHeader, itItems);
        ZrfcsdpricesimulateResponse response = orderApiService.priceSimulate(zrfcsdpricesimulate);
        BusinessUtil.notNull(response,ErrorCodes.CommonEnum.SYSTEM_EXCEPTION);

        ZpricessimulateHeaderOut esHeader = response.getEsHeader();
        List<ZpricessimulateItemOut> items = response.getEtItems().getItem();

        for(OrderLineEO orderLineEO : records){
            //设置定价
            orderLineEO.setPriceDate(priceDate);
            //设置单价
            for(ZpricessimulateItemOut item : items){
                if(item.getProductid().equals(orderLineEO.getProductId())){
                    orderLineEO.setPrice(item.getPrice());
                    orderLineEO.setNetPrice(item.getNetprice());
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
        BusinessUtil.notNull(order.getLines(), ErrorCodes.BusinessEnum.ORDER_LINES_IS_REQUIRED);
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
        orderLines.forEach(x->{
            x.setCreateId(userId);
            Date priceDate;
            try {
                priceDate = DateUtil.parseDate(order.getPriceDate(),DateUtil.MONTH_FORMAT_HLINE);
            } catch (ParseException e) {
                log.error("",e);
                throw new IllegalArgumentException("参数转换错误");
            }
            x.setExpectedDeliveryDate(DateUtil.getLastDayOfMonth(DateUtil.getYear(priceDate),DateUtil.getMonth(priceDate)));
        });
        order.setLines(order.objToLineJson(orderLines));
        orderApplyMapper.insertSelective(order);
    }

    /**
     * 订单修改申请
     * @param order
     * @param userId
     */
    @Transactional
    public void modifyOrderApply(OrderApply order, Integer userId){

    }

    /**
     * 取消订单申请
     * @param itemIds
     * @param userId
     */
    @Transactional
    public void cancelOrderApply(Set<Integer> itemIds, Integer userId) throws Exception {
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

        Integer orderId = lines.get(0).getOrderId();

        Order order = orderMapper.selectByPrimaryKey(orderId);

        BeanUtils.copyNotNullFields(order,orderApply);
        orderApply.setActive(1);
        orderApply.setCreateId(userId);
        orderApply.setCreateTime(DateUtil.getCurrentTS());
        orderApply.setAppalyType(3);
        orderApply.setLines(orderApply.objToLineJson(lines));
        orderApplyMapper.insert(orderApply);
    }


    /**
     * 变更交货日期
     */
    public void modifyDeliveryDate(List<DeliveryChangeVO> changeVOS, Integer userId){
        if(changeVOS.isEmpty()){
            return;
        }
        changeVOS.stream().forEach(x->{
            OrderLine orderLine = orderLineMapper.selectByPrimaryKey(x.getItemId());
            BusinessUtil.notNull(orderLine,ErrorCodes.BusinessEnum.ORDER_LINE_NOT_FOUND);

            Order order = orderMapper.selectByPrimaryKey(orderLine.getOrderId());
            BusinessUtil.assertTrue(userId.equals(order.getCreateId()),ErrorCodes.CommonEnum.REQ_ILLEGAL);

            orderLine.setExpectedDeliveryDate(x.getExpectedDeliveryDate());
            orderLine.setUpdateId(userId);
            orderLine.setUpdateTime(DateUtil.getCurrentTS());
            orderMapper.updateByPrimaryKeySelective(order);
        });
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
    private Zrfcsdpricesimulate getZrfcsdpricesimulate(IsHeader isHeader, ItItems itItems) {
        ZrfcsdpricesimulateContent content = new ZrfcsdpricesimulateContent(null,isHeader,itItems);
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
        isHeader.setOrdertype(orderApply.getOrderType());
        isHeader.setSalesorg(orderApply.getSalesOrg());
        isHeader.setSoldto(orderApply.getSoldTo());
        isHeader.setSendto(orderApply.getSendTo());
        return isHeader;
    }

    @Transactional
    public void submitApplyDelivery(DeliveryOrderVO bean, Integer userId){
        Order order = orderMapper.selectByPrimaryKey(bean.getOrderId());
        if(null == order){
            throw new BusinessException(ErrorCodes.BusinessEnum.ORDER_NOT_FOUND);
        }
        DeliverOrder deliverOrder = new DeliverOrder();
        deliverOrder.setSalesOrderId(order.getId());
        deliverOrder.setSapOrderNo(order.getRSapOrderId());
        deliverOrder.setSendTo(order.getSendTo());
        deliverOrder.setSoldTo(order.getSoldTo());
        deliverOrder.setDeliverDate(bean.getDeliverDate());
        deliverOrder.setShippingPoint(bean.getShippingPoint());
        deliverOrder.setApprovalStatus(Enums.OrderApprovalStatus.WAIT_APPROVAL.getValue());
        deliverOrder.setApprovalType(Enums.OrderApprovalType.CREATE.getValue());
        deliverOrder.setActive(1);
        deliverOrder.setCreateUserId(userId);
        deliverOrderMapper.insertSelective(deliverOrder);

        List<OrderLine> orderLine = orderLineMapper.selectByOrderId(order.getId());
        if(bean.getOrderLine().isEmpty()){
            throw new BusinessException("请选择需要提货的订单");
        }
        orderLine.forEach(o->{
            bean.getOrderLine().forEach(e->{
                if(o.getId().equals(e.getId())){
                    if(o.getRemainingNum() < e.getDeliveryQuantity()){
                        throw new BusinessException("");
                    }
                    DeliverOrderLine deliverOrderLine = new DeliverOrderLine();
                    deliverOrderLine.setDeliverOrderId(deliverOrder.getDeliverOrderId());
                    deliverOrderLine.setSalesOrderId(order.getId());
                    deliverOrderLine.setSapSalesOrderLineNo(order.getRSapOrderId());
                    deliverOrderLine.setSalesOrderLineId(o.getId());
                    deliverOrderLine.setSapSalesOrderLineNo(o.getRItemNo());
                    deliverOrderLine.setProductId(o.getProductId());
//                    deliverOrderLine.setProduct();
                    deliverOrderLine.setDeliveryQuantity(e.getDeliveryQuantity());

                    deliverOrderLine.setActive(1);
                    deliverOrderLine.setCreateUserId(userId);
                    deliverOrderLineMapper.insertSelective(deliverOrderLine);
                }
            });
        });
    }

    @Transactional
    public void updateDeliveryOrder(DeliverOrder order){
        if(null == order || null == order.getDeliverOrderLineList()){
            return;
        }
        order.setApprovalStatus(Enums.OrderApprovalStatus.WAIT_APPROVAL.getValue());
        order.setApprovalType(Enums.OrderApprovalType.UPDATE.getValue());
        deliverOrderMapper.updateByPrimaryKeySelective(order);
        order.getDeliverOrderLineList().forEach(e->{
            deliverOrderLineMapper.updateByPrimaryKeySelective(e);
        });
    }

    @Transactional
    public void cancelDeliveryOrder(DeliveryOrderCancelVO vo){
        DeliverOrder deliverOrder = deliverOrderMapper.selectByPrimaryKey(vo.getDeliveryOrderId());
        BusinessUtil.assertTrue(null == deliverOrder,ErrorCodes.BusinessEnum.ORDER_NOT_FOUND);
        deliverOrder.setApprovalStatus(Enums.OrderApprovalStatus.WAIT_APPROVAL.getValue());
        deliverOrder.setApprovalType(Enums.OrderApprovalType.CANCEL.getValue());
        deliverOrderMapper.updateByPrimaryKeySelective(deliverOrder);

        vo.getDeliveryOrderLineIds().forEach(e->{
            DeliverOrderLine deliverOrderLine = deliverOrderLineMapper.selectByPrimaryKey(e);
            deliverOrderLine.setActive(2);
            deliverOrderLineMapper.updateByPrimaryKeySelective(deliverOrderLine);
        });
    }

    @Transactional
    public void deleteDeliveryOrder(Integer id){
        DeliverOrder deliverOrder = deliverOrderMapper.selectByPrimaryKey(id);
        BusinessUtil.assertTrue(deliverOrder.getApprovalStatus().equals(Enums.OrderApprovalStatus.REJEC.getValue()),ErrorCodes.BusinessEnum.ORDER_NO_DELETE);
        deliverOrderMapper.deleteByPrimaryKey(id);

    }
}
