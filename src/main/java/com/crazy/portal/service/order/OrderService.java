package com.crazy.portal.service.order;

import com.crazy.portal.annotation.OperationLog;
import com.crazy.portal.bean.order.BatchModifyOrderBean;
import com.crazy.portal.bean.order.OrderApprovalBean;
import com.crazy.portal.bean.order.OrderCreditInfoBean;
import com.crazy.portal.bean.order.OrderQueryBean;
import com.crazy.portal.bean.order.wsdl.create.*;
import com.crazy.portal.dao.order.OrderLineMapper;
import com.crazy.portal.dao.order.OrderMapper;
import com.crazy.portal.entity.order.Order;
import com.crazy.portal.entity.order.OrderLine;
import com.crazy.portal.util.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 订单明细
     * @param id
     * @return
     */
    public Order detail(Integer id){
        BusinessUtil.notNull(id, ErrorCodes.BusinessEnum.ORDER_ID_IS_REQUIRED);
        Order order = orderMapper.selectByPrimaryKey(id);
        BusinessUtil.notNull(order, ErrorCodes.BusinessEnum.ORDER_INFO_NOT_FOUND);
        order.setLines(orderLineMapper.selectByOrderId(id));
        return order;
    }

    /**
     * 变更交货日期
     */
    @OperationLog
    public void modifyDeliveryDate(BatchModifyOrderBean bean, Integer userId) throws Exception{
        for (Integer id : bean.getOrderIds()) {
            BusinessUtil.notNull(id, ErrorCodes.BusinessEnum.ORDER_ID_IS_REQUIRED);
            BusinessUtil.assertTrue(DateUtil.isValidDateFormat(bean.getDeliveryDate(), DateUtil.WEB_FORMAT), ErrorCodes.BusinessEnum.ORDER_DELIVERY_DATE_FORMAT_FAIL);
            Order order = new Order();
            order.setId(id);
            order.setPriceDate(bean.getDeliveryDate());
            order.setUpdateId(userId);
            order.setUpdateTime(DateUtil.getCurrentTS());
            orderMapper.updateByPrimaryKeySelective(order);
        }
    }

    /**
     * 提货
     * @param bean
     * @param userId
     */
    @OperationLog
    public void takeGoods(BatchModifyOrderBean bean, Integer userId){
        //TODO
    }

    /**
     * 取消
     * @param bean
     * @param userId
     */
    @OperationLog
    public void cancel(BatchModifyOrderBean bean, Integer userId){
        for(Integer id : bean.getOrderIds()){
            Order order = new Order();
            order.setId(id);
            order.setApprovalStatus(Enums.OrderApprovalStatus.CANCEL.getValue());
            order.setUpdateId(userId);
            order.setUpdateTime(DateUtil.getCurrentTS());
            orderMapper.updateByPrimaryKeySelective(order);
        }
    }

    /**
     * 订单审批
     * @param bean
     */
    @OperationLog
    @Transactional
    public void approval(OrderApprovalBean bean, Integer userId){
        Order order = orderMapper.selectByPrimaryKey(bean.getOrderId());
        BusinessUtil.notNull(order, ErrorCodes.BusinessEnum.ORDER_INFO_NOT_FOUND);
        order.setLines(orderLineMapper.selectByOrderId(order.getId()));

        BusinessUtil.assertTrue(!order.getApprovalStatus().equals(Enums.OrderApprovalStatus.WAIT_APPROVAL),
                ErrorCodes.BusinessEnum.ORDER_NO_PENDING);

        //如果是通过，调用ECC创单接口
        if(bean.getApprovalStatus().equals(Enums.OrderApprovalStatus.ADOPT.getValue())){
            this.sendOrderCreateRequest(order);
        }
        order.setApprovalStatus(bean.getApprovalStatus());
        order.setOrderReason(bean.getReason());
        order.setUpdateId(userId);
        order.setUpdateTime(DateUtil.getCurrentTS());
        orderMapper.updateByPrimaryKeySelective(order);
    }

    /**
     * 信用额度查询
     */
    public OrderCreditInfoBean creditInfoQuery(Integer orderId){
        OrderCreditInfoBean info = new OrderCreditInfoBean();
        //TODO
        return info;
    }


    public void sendOrderCreateRequest(Order order){
        IsHeader isHeader = this.getIsHeader(order);

        Map<Integer, Integer> lineMap = new HashMap<>();
        ItItems itItems = this.getItItems(order, lineMap);

        ZrfcsdsalesordercreateContent content = new ZrfcsdsalesordercreateContent(null,isHeader,itItems);
        ZrfcsdsalesordercreateBody zrfcsdsalesordercreateBody = new ZrfcsdsalesordercreateBody(content);
        Zrfcsdsalesordercreate request = new Zrfcsdsalesordercreate(zrfcsdsalesordercreateBody);
        ZrfcsdsalesordercreateResponse response = orderApiService.createSalesOrder(request);

        order.setRGrossValue(response.getEsHeader().getGrossvalue());
        order.setRNetValue(response.getEsHeader().getNetvalue());
        for(ZsalesordercreateOutItem etItem : response.getEtItems().getItem()){
            OrderLine orderLine = new OrderLine();
            orderLine.setId(lineMap.get(etItem.getSequenceno()));
            orderLine.setRItemNo(etItem.getItemno());
            orderLine.setRPrice(etItem.getPrice());
            orderLine.setRNetPrice(etItem.getNetprice());
            orderLine.setRCurrency(etItem.getCurrency());
            orderLine.setRProductId(etItem.getProductid());
            orderLine.setRItemCategory(etItem.getItemcategory());
            orderLine.setRRefItemNo(etItem.getRefitemno());
            orderLine.setRRefItemProductId(etItem.getRefitemproductid());
            orderLineMapper.updateByPrimaryKeySelective(orderLine);
        }

    }

    private ItItems getItItems(Order order, Map<Integer, Integer> lineMap) {
        Integer line_no = 1;
        List<ItItem> items = new ArrayList<>();
        for (OrderLine line : order.getLines()) {
            ItItem item = new ItItem();
            item.setSequenceno(String.valueOf(line_no));
            item.setProductid(line.getProductId());
            item.setOrderquantity(String.valueOf(line.getNum()));
            item.setPlatform(line.getPlatform());
            item.setRequestdate(line.getExpectedDeliveryDate());
            items.add(item);
            lineMap.put(line_no, line.getId());
            line_no ++;
        }
        ItItems itItems = new ItItems();
        itItems.setItem(items);
        return itItems;
    }

    private IsHeader getIsHeader(Order order) {
        IsHeader isHeader = new IsHeader();
        isHeader.setPortalorderid(order.getId().toString());
        isHeader.setOrdertype(order.getOrderType());
        isHeader.setSalesorg(order.getSalesOrg());
        isHeader.setSoldto(order.getSoldTo());
        isHeader.setSendto(order.getSendTo());
        isHeader.setPurchaseno(order.getPurchaseNo());
        isHeader.setPurchasedate(order.getPurchaseDate());
        isHeader.setPaymentterms(order.getPaymentTerms());
        isHeader.setCustomergroup1(order.getOrderType());
        isHeader.setCustomergroup2(order.getCustomerAttr());
        String priceDate = order.getPriceDate();
        BusinessUtil.assertTrue(StringUtil.isNotEmpty(priceDate), ErrorCodes.BusinessEnum.ORDER_EMPTY_PRICE_DATE);
        isHeader.setPricedate(priceDate);
        isHeader.setInco1(order.getIncoterms1());
        isHeader.setInco2(order.getIncoterms2());
        return isHeader;
    }
}
