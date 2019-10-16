package com.crazy.portal.service.order;

import com.crazy.portal.bean.order.OrderApprovalBean;
import com.crazy.portal.bean.order.wsdl.change.*;
import com.crazy.portal.bean.order.wsdl.create.EtItems;
import com.crazy.portal.bean.order.wsdl.create.IsHeader;
import com.crazy.portal.bean.order.wsdl.create.ItItem;
import com.crazy.portal.bean.order.wsdl.create.ItItems;
import com.crazy.portal.bean.order.wsdl.create.*;
import com.crazy.portal.dao.cusotmer.CustomerInfoMapper;
import com.crazy.portal.dao.order.OrderApplyMapper;
import com.crazy.portal.dao.order.OrderLineMapper;
import com.crazy.portal.dao.order.OrderMapper;
import com.crazy.portal.dao.product.ProductInfoDOMapper;
import com.crazy.portal.entity.cusotmer.CustomerInfo;
import com.crazy.portal.entity.order.Order;
import com.crazy.portal.entity.order.OrderApply;
import com.crazy.portal.entity.order.OrderLine;
import com.crazy.portal.entity.product.ProductInfoDO;
import com.crazy.portal.entity.system.User;
import com.crazy.portal.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 23:15 2019-09-25
 * @Modified by:
 */
@Service
@Slf4j
public class OrderApproveService {

    @Resource
    private OrderApiService orderApiService;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderLineMapper orderLineMapper;
    @Resource
    private OrderApplyMapper orderApplyMapper;
    @Resource
    private ProductInfoDOMapper productInfoDOMapper;
    @Resource
    private CustomerInfoMapper customerInfoMapper;

    /**
     * 订单审批
     * @param bean
     */
    @Transactional
    public void approval(OrderApprovalBean bean, User user) throws Exception{
        OrderApply orderApply = orderApplyMapper.selectByPrimaryKey(bean.getApplyId());
        BusinessUtil.notNull(orderApply, ErrorCodes.BusinessEnum.ORDER_APPLY_ORDER_NOT_FOUND);

        Integer approvalStatusInDB = orderApply.getApprovalStatus();
        BusinessUtil.assertTrue(!approvalStatusInDB.equals(Enums.OrderApprovalStatus.WAIT_APPROVAL),
                ErrorCodes.BusinessEnum.ORDER_NO_PENDING);

        Integer userId = user.getId();
        Integer approvalStatus = bean.getApprovalStatus();

        //审批通过
        if(approvalStatus.equals(Enums.OrderApprovalStatus.ADOPT.getValue())){
            //如果是创单申请
            Integer appalyType = orderApply.getAppalyType();

            switch (appalyType){
                case 1:
                    this.createOrder(bean.getExpectedDeliveryDate(),orderApply, userId);
                    break;
                case 2:
                    this.modifyOrder(orderApply, userId);
                    break;
                case 3:
                    this.cancelOrder(orderApply.getRSapOrderId(),userId);
                    break;
                case 4:
                    this.modifyDeliveryDate(orderApply, userId);
                    break;
                default:
                    break;
            }
        }
        orderApply.setApprovalStatus(approvalStatus);
        orderApply.setUpdateId(userId);
        orderApply.setUpdateTime(DateUtil.getCurrentTS());
        orderApply.setApprover(user.getLoginName());
        orderApply.setApprovalTime(DateUtil.getCurrentTS());
        orderApply.setApprovalOpinions(bean.getReason());
        orderApplyMapper.updateByPrimaryKeySelective(orderApply);
    }

    /**
     * 审批通过-创单
     * @param expectedDeliveryDate 需求交货日期，如果审批人填写则以此为交货日期，否则以月份最后一天为交货日期
     * @param userId
     * @param orderApply
     * @throws ParseException
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private void createOrder(String expectedDeliveryDate,OrderApply orderApply,Integer userId) throws Exception {

        ZrfcsdsalesordercreateResponse response = this.invokeEccCreateOrder(expectedDeliveryDate,orderApply);
        //第三方接口调用成功,将审批信息同步到结果表
        ZsalesordercreateOutHeader esHeader = response.getEsHeader();
        Order order = new Order();
        BeanUtils.copyNotNullFields(orderApply,order);
        order.setCreateTime(DateUtil.getCurrentTS());
        order.setCreateId(userId);
        order.setRGrossValue(esHeader.getGrossvalue());
        order.setRNetValue(esHeader.getNetvalue());
        order.setRSapOrderId(esHeader.getSaporderid());
        order.setPaymentTerms(esHeader.getPaymentterms());

        order.setPriceDate(expectedDeliveryDate);
        order.setUpdateTime(null);
        order.setUpdateId(null);
        orderMapper.insertSelective(order);


        List<OrderLine> lines = orderApply.lineJsonToObj(orderApply.getJsonLines());
        //默认取出第一个订单行的期望交货月份
        String expectedDeliveryMonth = lines.get(0).getExpectedDeliveryMonth();
        if(StringUtil.isEmpty(order.getPriceDate())){
            //设置月份的最后一天
            Date month = DateUtil.parseDate(expectedDeliveryMonth,DateUtil.MONTH_FORMAT);
            order.setPriceDate(DateUtil.getLastDayOfMonth(DateUtil.getYear(month),DateUtil.getMonth(month)));
        }
        for(OrderLine line : lines){

            for(ZsalesordercreateOutItem etItem : response.getEtItems().getItem()){
                //去掉默认返回的7个0
                String productid = etItem.getProductid().substring(7);
                if(productid.equals(line.getProductId())){
                    line.setRItemNo(etItem.getItemno());
                    line.setRPrice(etItem.getPrice());
                    line.setRNetPrice(etItem.getNetprice());
                    line.setRCurrency(etItem.getCurrency());
                    line.setRProductId(productid);
                    line.setRItemCategory(etItem.getItemcategory());
                    line.setRRefItemNo(etItem.getRefitemno());
                    line.setRRefItemProductId(etItem.getRefitemproductid());
                    line.setExpectedDeliveryDate(order.getPriceDate());
                    line.setCreateId(userId);
                    line.setCreateTime(DateUtil.getCurrentTS());
                    line.setActice(1);
                    line.setOrderId(order.getId());
                    line.setRemainingNum(line.getNum());
                    orderLineMapper.insertSelective(line);
                }
            }
        }
    }

    /**
     * 审批通过-取消订单
     * @param sapOrderId
     * @param userId
     */
    private void cancelOrder(String sapOrderId,Integer userId) throws Exception{

        Order order = this.getOrderBySapOrderId(sapOrderId);
        List<OrderLine> orderLines = orderLineMapper.selectByOrderId(order.getId());
        ZrfcsdsalesorderchangeResponse response = this.invokeEccModifyOrder(order,"D");
        TableOfZsalesorderchangeOutItem etItems = response.getEtItems();
        if(null != etItems) {
            List<ZsalesorderchangeOutItem> items = etItems.getItem();
            if (null != items && !items.isEmpty()) {
                items.forEach(sapLine ->
                        orderLines.forEach(line -> {
                            if (line.getRProductId().equals(sapLine.getProductid())) {
                                //修改为失效
                                line.setActice(0);
                                line.setUpdateId(userId);
                                line.setUpdateTime(DateUtil.getCurrentTS());
                                orderLineMapper.updateByPrimaryKeySelective(line);
                            }
                        })
                );

                //最终检查:如果所有订单行都被设置为取消,将订单头设置为失效
                List<OrderLine> results = orderLines.stream().filter(x -> x.getActice().equals(1)).collect(Collectors.toList());
                if (results.isEmpty()) {
                    order.setActive(0);
                    order.setUpdateId(userId);
                    order.setUpdateTime(DateUtil.getCurrentTS());
                    orderMapper.updateByPrimaryKeySelective(order);
                }
            }
        }
    }

    /**
     * 执行修改订单
     * @param userId
     */
    private void modifyOrder(OrderApply orderApply,Integer userId) throws Exception{

        String sapOrderId = orderApply.getRSapOrderId();
        Order order = this.getOrderBySapOrderId(sapOrderId);

        ZrfcsdsalesorderchangeResponse response = this.invokeEccModifyOrder(order,"I");
        List<OrderLine> orderLines = orderLineMapper.selectByOrderId(order.getId());
        List<OrderLine> applyLines = orderApply.lineJsonToObj(orderApply.getJsonLines());

        Map<String,OrderLine> applyLineMap = applyLines.stream().collect(
                Collectors.toMap(OrderLine::getProductId, Function.identity()));

        //修改订单头
        order.setSendTo(orderApply.getSendTo());
        order.setPurchaseNo(orderApply.getPurchaseNo());
        order.setPurchaseDate(orderApply.getPurchaseDate());
        order.setIncoterms1(orderApply.getIncoterms1());
        order.setIncoterms2(orderApply.getIncoterms2());
        order.setOrderType(orderApply.getOrderType());
        order.setCustomerAttr(orderApply.getCustomerAttr());
        order.setUpdateId(userId);
        order.setUpdateTime(DateUtil.getCurrentTS());
        orderMapper.updateByPrimaryKeySelective(order);
        //修改订单行
        List<ZsalesorderchangeOutItem> items = response.getEtItems().getItem();
        items.forEach(sapLine->
            orderLines.forEach(line->{
                String productId = line.getProductId();
                if(line.getRProductId().equals(sapLine.getProductid())){
                    //目前订单行只能修改数量
                    line.setNum(applyLineMap.get(productId).getNum());
                    line.setUpdateId(userId);
                    line.setUpdateTime(DateUtil.getCurrentTS());
                    orderLineMapper.updateByPrimaryKeySelective(line);
                }
            })
        );
    }

    /**
     * 根据sapOrderId获取订单信息
     * @param sapOrderId
     * @return
     */
    private Order getOrderBySapOrderId(String sapOrderId) {
        BusinessUtil.assertEmpty(sapOrderId, ErrorCodes.BusinessEnum.ORDER_NOT_FOUND);
        Order order = orderMapper.selectBySapOrderId(sapOrderId);
        BusinessUtil.notNull(order, ErrorCodes.BusinessEnum.ORDER_NOT_FOUND);
        return order;
    }


    /**
     * 审批通过-变更交期
     * @param orderApply
     * @param userId
     */
    private void modifyDeliveryDate(OrderApply orderApply,Integer userId){
        List<OrderLine> applyLines = orderApply.lineJsonToObj(orderApply.getJsonLines());

        Order order = orderMapper.selectBySapOrderId(orderApply.getRSapOrderId());
        List<OrderLine> lines = orderLineMapper.selectByOrderId(order.getId());

        lines.forEach(x->
            applyLines.forEach(applyLine->{
                if(applyLine.getProductId().equals(x.getProductId())){
                    x.setExpectedDeliveryDate(applyLine.getExpectedDeliveryDate());
                    x.setUpdateId(userId);
                    x.setUpdateTime(DateUtil.getCurrentTS());
                    orderLineMapper.updateByPrimaryKeySelective(x);
                }
            })
        );

    }
    /**
     * 调用Ecc修改订单接口
     * @param order
     * @param operationType
     * @return
     */
    private ZrfcsdsalesorderchangeResponse invokeEccModifyOrder (
            Order order,String operationType) throws Exception{

        com.crazy.portal.bean.order.wsdl.change.IsHeader isHeader = this.buildChangeIsHeader(order);

        List<OrderLine> orderLines = orderLineMapper.selectByOrderId(order.getId());
        com.crazy.portal.bean.order.wsdl.change.ItItems itItems = this.buildChangeItItems(orderLines,operationType);

        ZrfcsdsalesorderchangeContent content = new ZrfcsdsalesorderchangeContent(new com.crazy.portal.bean.order.wsdl.change.EtItems(),isHeader,itItems);
        ZrfcsdsalesorderchangeBody zrfcsdsalesordercreateBody = new ZrfcsdsalesorderchangeBody(content);
        Zrfcsdsalesorderchange request = new Zrfcsdsalesorderchange(zrfcsdsalesordercreateBody);
        ZrfcsdsalesorderchangeResponse response = orderApiService.changeSalesOrder(request);
        BusinessUtil.notNull(response, ErrorCodes.CommonEnum.SYSTEM_EXCEPTION);
        return response;
    }


    /**
     * 调用ecc创单接口
     * @param orderApply
     */
    private ZrfcsdsalesordercreateResponse invokeEccCreateOrder(
            String expectedDeliveryDate,OrderApply orderApply) throws Exception{

        IsHeader isHeader = this.buildCreateIsHeader(orderApply);

        ItItems itItems = this.buildCreateItItems(expectedDeliveryDate,orderApply);

        ZrfcsdsalesordercreateContent content = new ZrfcsdsalesordercreateContent(new EtItems(),isHeader,itItems);
        ZrfcsdsalesordercreateBody zrfcsdsalesordercreateBody = new ZrfcsdsalesordercreateBody(content);
        Zrfcsdsalesordercreate request = new Zrfcsdsalesordercreate(zrfcsdsalesordercreateBody);
        ZrfcsdsalesordercreateResponse response = orderApiService.createSalesOrder(request);
        BusinessUtil.notNull(response, ErrorCodes.CommonEnum.SYSTEM_EXCEPTION);
        return response;
    }

    /**
     * 构建修改订单头
     * @param order
     * @return
     */
    private com.crazy.portal.bean.order.wsdl.change.IsHeader buildChangeIsHeader(Order order){

        com.crazy.portal.bean.order.wsdl.change.IsHeader isHeader = new com.crazy.portal.bean.order.wsdl.change.IsHeader();
        isHeader.setSaporderid(order.getRSapOrderId());
        isHeader.setSalesoffice(order.getSalesOffice());
        isHeader.setSalesgroup(order.getSalesGroup());
        isHeader.setSendto(order.getSendTo());
        isHeader.setPurchaseno(order.getPurchaseNo());
        isHeader.setPurchasedate(order.getPriceDate());
        isHeader.setPaymentterms(order.getPaymentTerms());
        isHeader.setIncoterms1(order.getIncoterms1());
        isHeader.setIncoterms2(order.getIncoterms2());
        isHeader.setCustomergroup1(order.getOrderType());
        isHeader.setCustomergroup2(order.getCustomerAttr());
        return isHeader;
    }

    /**
     * 构建修改订单行
     * @param orderLines
     * @param operationType 操作类型 I:更新 D:删除 R:拒绝
     * @return
     */
    private com.crazy.portal.bean.order.wsdl.change.ItItems buildChangeItItems(List<OrderLine> orderLines, String operationType){
        Integer line_no = 1;
        List<com.crazy.portal.bean.order.wsdl.change.ItItem> items = new ArrayList<>();
        for (OrderLine line : orderLines) {
            com.crazy.portal.bean.order.wsdl.change.ItItem item = new com.crazy.portal.bean.order.wsdl.change.ItItem();
            item.setOperationtype(operationType);
            item.setSequenceno(String.valueOf(line_no));
            item.setItemno(line.getRItemNo());
            line_no ++;
        }
        com.crazy.portal.bean.order.wsdl.change.ItItems itItems = new com.crazy.portal.bean.order.wsdl.change.ItItems();
        itItems.setItem(items);
        return itItems;
    }

    private ItItems buildCreateItItems(String expectedDeliveryDate,OrderApply orderApply) {
        Integer line_no = 1;
        List<ItItem> items = new ArrayList<>();
        for (OrderLine line : orderApply.lineJsonToObj(orderApply.getJsonLines())) {
            ItItem item = new ItItem();
            String productId = line.getProductId();
            String platform = line.getPlatform();

            item.setSequenceno(String.valueOf(line_no));
            item.setProductid(productId);
            item.setOrderquantity(String.valueOf(line.getNum()));
            item.setPlatform(line.getPlatform());
            String requestDate = StringUtil.isNotEmpty(expectedDeliveryDate)?expectedDeliveryDate:line.getExpectedDeliveryDate();
            item.setRequestdate(requestDate);
            ProductInfoDO productInfoDO = productInfoDOMapper.selectBySapMidAndPlatForm(productId, platform);
            item.setKondm(productInfoDO.getBu());
            items.add(item);
            line_no ++;
        }
        ItItems itItems = new ItItems();
        itItems.setItem(items);
        return itItems;
    }

    private IsHeader buildCreateIsHeader(OrderApply orderApply) {
        IsHeader isHeader = new IsHeader();
        isHeader.setPortalorderid(orderApply.getId().toString() + System.currentTimeMillis());
        isHeader.setOrdertype(orderApply.getUnderOrderType());
        isHeader.setSalesorg(orderApply.getSalesOrg());
        isHeader.setChannel(orderApply.getChannel());
        isHeader.setDivision(orderApply.getDivision());
        isHeader.setSalesoffice(orderApply.getSalesOffice());
        isHeader.setSalesgroup(orderApply.getSalesGroup());
        CustomerInfo soldToCustomer = customerInfoMapper.selectByPrimaryKey(Integer.parseInt(orderApply.getSoldTo()));
        isHeader.setSoldto(soldToCustomer.getOutCode());
        CustomerInfo sendToCustomer = customerInfoMapper.selectByPrimaryKey(Integer.parseInt(orderApply.getSendTo()));
        isHeader.setSendto(sendToCustomer.getOutCode());
        isHeader.setPurchaseno(orderApply.getPurchaseNo());
        isHeader.setPurchasedate(orderApply.getPurchaseDate());
        isHeader.setPaymentterms(orderApply.getPaymentTerms());
        isHeader.setCustomergroup1(orderApply.getOrderType());
        isHeader.setCustomergroup2(orderApply.getCustomerAttr());
        isHeader.setPricedate(orderApply.getPriceDate());
        isHeader.setInco1(orderApply.getIncoterms1());
        isHeader.setInco2(orderApply.getIncoterms2());
        return isHeader;
    }

}
