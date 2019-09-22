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
import com.crazy.portal.entity.order.DeliverOrder;
import com.crazy.portal.entity.order.DeliverOrderLine;
import com.crazy.portal.entity.order.Order;
import com.crazy.portal.entity.order.OrderLine;
import com.crazy.portal.entity.product.ProductInfoDO;
import com.crazy.portal.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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

    /**
     * 订单申请
     * @param order
     * @param userId
     */
    @Transactional
    public void submitApply(Order order, Integer userId){
        BusinessUtil.notNull(order, ErrorCodes.BusinessEnum.ORDER_INFO_IS_REQUIRED);
        BusinessUtil.notNull(order.getLines(), ErrorCodes.BusinessEnum.ORDER_LINES_IS_REQUIRED);
        order.setApprovalStatus(Enums.OrderApprovalStatus.WAIT_APPROVAL.getValue());
        order.setCreateId(userId);
        order.setCreateTime(DateUtil.getCurrentTS());
        orderMapper.insertSelective(order);
        order.getLines().forEach(line->{
            line.setOrderId(order.getId());
            line.setCreateId(userId);
            line.setCreateTime(DateUtil.getCurrentTS());
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
    public List<OrderLineEO> parsingLineTmplFile(Order order){
        List<OrderLineEO> records = ExcelUtils.readExcel(order.getLineFile(), OrderLineEO.class);

        for(int i = 0;i<records.size();i++){
            OrderLineEO orderLineEO = records.get(i);

            String productId = orderLineEO.getProductId();
            String unit = orderLineEO.getUnit();
            String num = orderLineEO.getNum();
            Date expectedDeliveryMonth = orderLineEO.getExpectedDeliveryMonth();

            boolean paramsCheck = StringUtil.isEmpty(productId)
                                        || StringUtil.isEmpty(unit)
                                        || StringUtil.isEmpty(num)
                                        || expectedDeliveryMonth == null;

            BusinessUtil.assertFlase(paramsCheck,ErrorCodes.BusinessEnum.ORDER_LINE_FILE_ERROR);

            IsHeader isHeader = this.getIsHeader(order, orderLineEO, expectedDeliveryMonth);
            ItItems itItems = this.getItItems(i, productId, num);

            Zrfcsdpricesimulate zrfcsdpricesimulate = this.getZrfcsdpricesimulate(isHeader, itItems);
            ZrfcsdpricesimulateResponse response = orderApiService.priceSimulate(zrfcsdpricesimulate);
            BusinessUtil.notNull(response,ErrorCodes.BusinessEnum.ORDER_PRICESIMULATION_ERROR);

            ZpricessimulateHeaderOut esHeader = response.getEsHeader();
            orderLineEO.setGrossValue(esHeader.getGrossvalue());
            orderLineEO.setNetVale(esHeader.getNetvalue());
        }
        return records;
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
     * 组装订单行入参
     * @param i
     * @param productId
     * @param num
     * @return
     */
    private ItItems getItItems(int i, String productId, String num) {
        ItItems itItems = new ItItems();
        ItItem itItem = new ItItem();

        itItem.setSequenceno((i+1)+"");
        itItem.setProductid(productId);
        itItem.setOrderquantity(num);

        ProductInfoDO params = new ProductInfoDO();
        params.setSapMid(productId);
        List<ProductInfoDO> productInfoDOS = productInfoDOMapper.selectProductInfo(params);
        BusinessUtil.assertFlase(productInfoDOS.isEmpty(), ErrorCodes.BusinessEnum.ORDER_NOT_EXISTS_PRODUCT_ID);

        ProductInfoDO productInfo = productInfoDOS.get(0);
        itItem.setPlatform(productInfo.getPlatform());
        itItems.setItem(Arrays.asList(itItem));
        return itItems;
    }

    /**
     * 组装订单头入参
     * @param order
     * @param orderLineEO
     * @param expectedDeliveryMonth
     * @return
     */
    private IsHeader getIsHeader(Order order, OrderLineEO orderLineEO, Date expectedDeliveryMonth) {
        IsHeader isHeader = new IsHeader();
        isHeader.setOrdertype(order.getOrderType());
        isHeader.setSalesorg(order.getSalesOrg());
        isHeader.setSoldto(order.getSoldTo());
        isHeader.setSendto(order.getSendTo());
        String priceDate = DateUtil.getLastDayOfMonth(DateUtil.getYear(expectedDeliveryMonth),DateUtil.getMonth(expectedDeliveryMonth));
        isHeader.setPricedate(priceDate);
        orderLineEO.setPriceDate(priceDate);
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
