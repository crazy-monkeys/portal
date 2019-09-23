package com.crazy.portal.service.order;

import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.crazy.portal.bean.order.OrderLineEO;
import com.crazy.portal.bean.order.DeliveryOrderVO;
import com.crazy.portal.bean.order.wsdl.price.*;
import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.dao.order.DeliverOrderLineMapper;
import com.crazy.portal.dao.order.DeliverOrderMapper;
import com.crazy.portal.dao.order.OrderLineMapper;
import com.crazy.portal.dao.order.OrderMapper;
import com.crazy.portal.dao.product.ProductInfoDOMapper;
import com.crazy.portal.entity.cusotmer.CustomerInfo;
import com.crazy.portal.entity.order.DeliverOrder;
import com.crazy.portal.entity.order.DeliverOrderLine;
import com.crazy.portal.entity.order.Order;
import com.crazy.portal.entity.order.OrderLine;
import com.crazy.portal.entity.product.ProductInfoDO;
import com.crazy.portal.service.customer.CustomerInfoService;
import com.crazy.portal.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

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
     * 订单申请
     * @param order
     * @param userId
     */
    @Transactional
    public void submitApply(Order order, Integer userId){
        BusinessUtil.notNull(order, ErrorCodes.BusinessEnum.ORDER_INFO_IS_REQUIRED);
        BusinessUtil.notNull(order.getLines(), ErrorCodes.BusinessEnum.ORDER_LINES_IS_REQUIRED);
        CustomerInfo dealerByUser = customerInfoService.getDealerByUser(userId);
        BusinessUtil.notNull(dealerByUser, ErrorCodes.BusinessEnum.CUSTOMER_IS_EMPYT);
        order.setDealerId(dealerByUser.getId());
        order.setApprovalStatus(Enums.OrderApprovalStatus.WAIT_APPROVAL.getValue());
        order.setCreateId(userId);
        order.setCreateTime(DateUtil.getCurrentTS());
        order.setActive(1);
        //TODO 建立枚举
        if(order.getSalesOrg().equals("3000")){
            order.setPaymentTerms("9994");
        }
        orderMapper.insertSelective(order);
        order.getLines().forEach(line->{
            line.setOrderId(order.getId());
            line.setCreateId(userId);
            line.setCreateTime(DateUtil.getCurrentTS());
            Date priceDate = order.getPriceDate();
            line.setExpectedDeliveryDate(DateUtil.getLastDayOfMonth(DateUtil.getYear(priceDate),DateUtil.getMonth(priceDate)));
            orderLineMapper.insertSelective(line);
        });
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
    public Map<String,Object> parsingLineTmplFile(Order order){
        Map<String,Object> map = new HashMap<>();
        if(order.getSalesOrg().equals("3000")){
            order.setPaymentTerms("9994");
        }
        List<OrderLineEO> records = ExcelUtils.readExcel(order.getLineFile(), OrderLineEO.class);
//
//        //逻辑只允许出现同一个月份
        Date expectedDeliveryMonth = records.get(0).getExpectedDeliveryMonth();
        String priceDate = DateUtil.getLastDayOfMonth(DateUtil.getYear(expectedDeliveryMonth),DateUtil.getMonth(expectedDeliveryMonth));
//        IsHeader isHeader = this.buildIsHeader(order);
//        isHeader.setPricedate(priceDate);
//        ItItems itItems = new ItItems();
//        itItems.setItem(this.buildItItems(records));
//
//        Zrfcsdpricesimulate zrfcsdpricesimulate = this.getZrfcsdpricesimulate(isHeader, itItems);
//        ZrfcsdpricesimulateResponse response = orderApiService.priceSimulate(zrfcsdpricesimulate);
//        BusinessUtil.notNull(response,ErrorCodes.BusinessEnum.ORDER_PRICESIMULATION_ERROR);
//
//        ZpricessimulateHeaderOut esHeader = response.getEsHeader();
//        List<ZpricessimulateItemOut> items = response.getEtItems().getItem();
//
//        for(OrderLineEO orderLineEO : records){
//            //设置定价
//            orderLineEO.setPriceDate(priceDate);
//            //设置单价
//            for(ZpricessimulateItemOut item : items){
//                if(item.getProductid().equals(orderLineEO.getProductId())){
//                    orderLineEO.setPrice(item.getPrice());
//                    orderLineEO.setNetPrice(item.getNetprice());
//                }
//            }
//        }

        //mock
        for(OrderLineEO orderLineEO : records){
            orderLineEO.setPriceDate(priceDate);
            orderLineEO.setNetPrice(new BigDecimal(1));
            orderLineEO.setPrice(new BigDecimal(1));
            orderLineEO.setPlatform("SC7731E");
        }
        map.put("lines",records);
        map.put("grossValue",new BigDecimal(1));
        map.put("netValue",new BigDecimal(1));
        return map;
    }

    private List<ItItem> buildItItems(List<OrderLineEO> records) {
        List<ItItem> items = new ArrayList<>();
        for(int i = 0;i<records.size();i++){
            OrderLineEO orderLineEO = records.get(i);
            String productId = orderLineEO.getProductId();
            String num = orderLineEO.getNum();
            boolean paramsCheck = StringUtil.isEmpty(productId)
                                        || StringUtil.isEmpty(num);

            BusinessUtil.assertFlase(paramsCheck, ErrorCodes.BusinessEnum.ORDER_LINE_FILE_ERROR);
            ItItem itItem = new ItItem();
            itItem.setSequenceno((i+1)+"");
            itItem.setProductid(productId);
            itItem.setOrderquantity(num);

            //根据物料号获取平台
            ProductInfoDO params = new ProductInfoDO();
            params.setSapMid(productId);
            List<ProductInfoDO> productInfoDOS = productInfoDOMapper.selectProductInfo(params);
            BusinessUtil.assertFlase(productInfoDOS.isEmpty(), ErrorCodes.BusinessEnum.ORDER_NOT_EXISTS_PRODUCT_ID);
            ProductInfoDO productInfo = productInfoDOS.get(0);
            itItem.setPlatform(productInfo.getPlatform());

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
     * @param order
     * @param order
     * @return
     */
    private IsHeader buildIsHeader(Order order) {
        IsHeader isHeader = new IsHeader();
        isHeader.setOrdertype(order.getOrderType());
        isHeader.setSalesorg(order.getSalesOrg());
        isHeader.setSoldto(order.getSoldTo());
        isHeader.setSendto(order.getSendTo());
        return isHeader;
    }
    @Transactional
    public void submitApplyDelivery(DeliveryOrderVO bean, Integer userId){
        Order order = orderMapper.selectByPrimaryKey(bean.getOrderId());
        if(null == order){
            throw new BusinessException(ErrorCodes.BusinessEnum.ORDER_INFO_NOT_FOUND);
        }
        DeliverOrder deliverOrder = new DeliverOrder();
        deliverOrder.setSalesOrderId(order.getId());
        deliverOrder.setSapOrderNo(order.getRSapOrderId());
        deliverOrder.setSendTo(order.getSendTo());
        deliverOrder.setSoldTo(order.getSoldTo());
        deliverOrder.setDeliverDate(bean.getDeliverDate());
        deliverOrder.setShippingPoint(bean.getShippingPoint());
        deliverOrder.setApprovalStatus(Enums.OrderApprovalStatus.WAIT_APPROVAL.getValue());
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
}
