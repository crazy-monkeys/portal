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
import com.crazy.portal.entity.system.SysParameter;
import com.crazy.portal.entity.system.User;
import com.crazy.portal.service.system.SysParamService;
import com.crazy.portal.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
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
public class OrderApproveService extends CommonOrderService{

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
    @Resource
    private SysParamService sysParamService;

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
        Order order = this.buildOrder(expectedDeliveryDate, orderApply, userId, response);
        //获取申请单行对象
        List<OrderLine> lines = orderApply.lineJsonToObj(orderApply.getJsonLines());

        //计算定价日期
        this.calculatePriceDate(order, lines);

        //保存订单头
        this.orderMapper.insertSelective(order);

        List<ZsalesordercreateOutItem> outItems = response.getEtItems().getItem();

        //返回的实体+虚拟的行
        a:for(ZsalesordercreateOutItem eccLine : outItems){

            b:for(OrderLine line : lines){
                //ecc物料号如果前面有0 直接替换成空字符
                String eccProductID = eccLine.getProductid().replaceAll("^(0+)", "");
                //主物料信息保存
                String portalProductId = line.getProductId();
                if(eccProductID.equals(portalProductId)){
                    //设置剩余数量
                    line.setRemainingNum(line.getNum());
                    //计算组合物料价格
                    this.calculatePrice(outItems, line, portalProductId);
                    //保存虚拟订单行
                    this.insertOrderLine(userId,order,line,eccLine);
                    //虚拟物料计算价格并成功保存,跳至外层循环处理其他组合物料
                    continue a;
                }
            }
            //保存其余物料,需要构建新订单行
            OrderLine orderLine = new OrderLine();
            orderLine.setRPrice(eccLine.getPrice());
            orderLine.setRNetPrice(eccLine.getNetprice());
            this.insertOrderLine(userId,order,orderLine,eccLine);
        }
    }

    /**
     * 计算组合物料价格
     * @param outItems
     * @param line
     * @param portalProductId
     */
    private void calculatePrice(List<ZsalesordercreateOutItem> outItems, OrderLine line, String portalProductId) {
        //过滤出主物料对应的组合物料信息
        List<ZsalesordercreateOutItem> currProductItems = outItems.stream()
                .filter(f -> f.getRefitemproductid().replaceAll("^(0+)", "").equals(portalProductId)
                        || f.getProductid().replaceAll("^(0+)", "").equals(portalProductId))
                .collect(Collectors.toList());

        //主物料计算总价
        line.setRNetPrice(currProductItems.stream()
                .map(ZsalesordercreateOutItem::getNetprice)
                .reduce(BigDecimal.ZERO,BigDecimal::add));

        line.setRPrice(currProductItems.stream()
                .map(ZsalesordercreateOutItem::getPrice)
                .reduce(BigDecimal.ZERO,BigDecimal::add));
    }

    /**
     * 计算定价日期
     * @param order
     * @param lines
     * @throws ParseException
     */
    private void calculatePriceDate(Order order, List<OrderLine> lines) throws ParseException {
        //默认取出第一个订单行的期望交货月份
        String expectedDeliveryMonth = lines.get(0).getExpectedDeliveryMonth();
        if(StringUtil.isEmpty(order.getPriceDate())){
            //设置月份的最后一天
            Date month = DateUtil.parseDate(expectedDeliveryMonth,DateUtil.MONTH_FORMAT_HLINE);
            order.setPriceDate(DateUtil.getLastDayOfMonth(DateUtil.getYear(month),DateUtil.getMonth(month)));
        }
    }

    /**
     * 调用创单接口成功,构建申请单头对象
     * @param expectedDeliveryDate
     * @param orderApply
     * @param userId
     * @param response
     * @return
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private Order buildOrder(String expectedDeliveryDate, OrderApply orderApply, Integer userId, ZrfcsdsalesordercreateResponse response) throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        ZsalesordercreateOutHeader esHeader = response.getEsHeader();
        Order order = new Order();
        BeanUtils.copyNotNullFields(orderApply,order);
        order.setId(null);
        order.setCreateTime(DateUtil.getCurrentTS());
        order.setCreateId(userId);
        order.setRGrossValue(esHeader.getGrossvalue());
        order.setRNetValue(esHeader.getNetvalue());
        order.setRSapOrderId(esHeader.getSaporderid());
        order.setPaymentTerms(esHeader.getPaymentterms());

        order.setPriceDate(expectedDeliveryDate);
        order.setUpdateTime(null);
        order.setUpdateId(null);
        return order;
    }

    /**
     * 保存订单行
     * @param userId
     * @param order
     * @param line
     * @param etItem
     */
    private void insertOrderLine(Integer userId, Order order, OrderLine line, ZsalesordercreateOutItem etItem) {
        line.setOrderId(order.getId());
        line.setExpectedDeliveryDate(order.getPriceDate());
        line.setRPrice(line.getRPrice());
        line.setRNetPrice(line.getRNetPrice());
        line.setRCurrency(etItem.getCurrency());
        line.setRProductId(etItem.getProductid().replaceAll("^(0+)", ""));
        line.setRItemCategory(etItem.getItemcategory());
        line.setRRefItemNo(etItem.getRefitemno().replaceAll("^(0+)", ""));
        line.setRRefItemProductId(etItem.getRefitemproductid().replaceAll("^(0+)", ""));
        line.setProductId(etItem.getProductid().replaceAll("^(0+)", ""));
        line.setRItemNo(etItem.getItemno().replaceAll("^(0+)", ""));
        line.setRSapQty(etItem.getSapquantity().intValue());
        line.setNum(etItem.getSapquantity().intValue());
        line.setCreateId(userId);
        line.setCreateTime(DateUtil.getCurrentTS());
        line.setActice(1);
        orderLineMapper.insertSelective(line);
    }

    /**
     * 审批通过-取消订单
     * @param sapOrderId
     * @param userId
     */
    private void cancelOrder(String sapOrderId,Integer userId) throws Exception{

        Order order = this.getOrderBySapOrderId(sapOrderId);
        List<OrderLine> orderLines = orderLineMapper.selectByOrderId(order.getId());
        //取消接口无需传递申请信息
        ZrfcsdsalesorderchangeResponse response = this.invokeEccModifyOrder(order,null,null,"D");
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

        //获取申请的行信息
        List<OrderLine> applyLines = orderApply.lineJsonToObj(orderApply.getJsonLines());

        //封装申请的物料号对应的物料信息,方便下面使用
        Map<String,OrderLine> applyLineMap = applyLines.stream().collect(
                Collectors.toMap(OrderLine::getProductId, Function.identity()));

        //里面会校验是否调用成功
        ZrfcsdsalesorderchangeResponse response = this.invokeEccModifyOrder(order,orderApply,applyLineMap,"I");

        List<OrderLine> orderLines = orderLineMapper.selectByOrderId(order.getId());

        //修改订单头
        order.setSendTo(orderApply.getSendTo());
        order.setSalesOrg(orderApply.getSalesOrg());
        order.setPurchaseNo(orderApply.getPurchaseNo());
        order.setRGrossValue(response.getEsHeader().getGrossvalue());
        order.setRNetValue(response.getEsHeader().getNetvalue());
        order.setPurchaseDate(orderApply.getPurchaseDate());
        order.setCustomerAttr(orderApply.getCustomerAttr());
        order.setUpdateId(userId);
        order.setUpdateTime(DateUtil.getCurrentTS());
        orderMapper.updateByPrimaryKeySelective(order);
        //如果ecc无异常,直接修改订单行
        orderLines.forEach(line->{
            String productId = line.getProductId();
            //目前订单行只能修改数量
            line.setNum(applyLineMap.get(productId).getNum());
            line.setUpdateId(userId);
            line.setUpdateTime(DateUtil.getCurrentTS());
            orderLineMapper.updateByPrimaryKeySelective(line);
        });
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
            Order order,OrderApply orderApply,Map<String,OrderLine> applyLineMap,String operationType) throws Exception{

        com.crazy.portal.bean.order.wsdl.change.IsHeader isHeader = this.buildChangeIsHeader(order,orderApply);

        List<OrderLine> orderLines = orderLineMapper.selectByOrderId(order.getId());
        com.crazy.portal.bean.order.wsdl.change.ItItems itItems = this.buildChangeItItems(orderLines,applyLineMap,operationType);

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
    private com.crazy.portal.bean.order.wsdl.change.IsHeader buildChangeIsHeader(Order order,OrderApply orderApply){

        com.crazy.portal.bean.order.wsdl.change.IsHeader isHeader = new com.crazy.portal.bean.order.wsdl.change.IsHeader();
        isHeader.setSaporderid(order.getRSapOrderId());
        isHeader.setSalesoffice(order.getSalesOffice());
        isHeader.setPurchasedate(order.getPriceDate());
//        isHeader.setPaymentterms(order.getPaymentTerms());
        isHeader.setIncoterms1(order.getIncoterms1());
        isHeader.setIncoterms2(order.getIncoterms2());
        isHeader.setPricedate(order.getPriceDate());

        //修改订单传递用户申请的信息,取消订单传递原订单信息
        isHeader.setSalesgroup(orderApply == null?order.getSalesGroup():orderApply.getSalesGroup());
        if(orderApply != null){
            CustomerInfo customer = this.getCustomerInfo(orderApply.getSendTo());
            isHeader.setSendto(customer.getOutCode());
        }else{
            CustomerInfo customer = this.getCustomerInfo(order.getSendTo());
            isHeader.setSendto(customer.getOutCode());
        }
        isHeader.setPurchaseno(orderApply == null?order.getPurchaseNo():orderApply.getPurchaseNo());
        isHeader.setCustomergroup1(orderApply == null?order.getOrderType():orderApply.getOrderType());
        isHeader.setCustomergroup2(orderApply == null?order.getCustomerAttr():orderApply.getCustomerAttr());
        return isHeader;
    }

    private CustomerInfo getCustomerInfo(String customerID) {
        return customerInfoMapper.selectByPrimaryKey(Integer.parseInt(customerID));
    }

    /**
     * 构建修改订单行
     * @param orderLines
     * @param operationType 操作类型 I:更新 D:删除 R:拒绝
     * @return
     */
    private com.crazy.portal.bean.order.wsdl.change.ItItems buildChangeItItems(
            List<OrderLine> orderLines,Map<String,OrderLine> applyLineMap, String operationType){

        Integer line_no = 1;
        List<com.crazy.portal.bean.order.wsdl.change.ItItem> items = new ArrayList<>();
        for (OrderLine line : orderLines) {
            com.crazy.portal.bean.order.wsdl.change.ItItem item = new com.crazy.portal.bean.order.wsdl.change.ItItem();
            item.setOperationtype(operationType);
            item.setSequenceno(String.valueOf(line_no));
            item.setItemno(line.getRItemNo());
            item.setProductid(line.getProductId());
            item.setPlatform(line.getPlatform());
            item.setRequestdate(line.getExpectedDeliveryDate());

            //如果订单申请信息为空,传递原订单数量，否则传递申请信息中的数量
            if(applyLineMap == null){
                item.setOrderquantity(line.getNum().toString());
            }else{
                item.setOrderquantity(applyLineMap.get(line.getProductId()).getNum().toString());
            }
            String customerCode = super.getInCodeByAbbreviation(line.getCustAbbreviation());
            if(StringUtils.isNotEmpty(customerCode)){
                item.setCustomercode(String.format("%0" + 10 + "d", Integer.parseInt(customerCode)));
            }
            items.add(item);
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
            SysParameter sysParameter = sysParamService.selectParam("4","1",productInfoDO.getBu());
            item.setKondm(sysParameter.getZhName());

            String customerCode = super.getInCodeByAbbreviation(line.getCustAbbreviation());
            if(StringUtils.isNotEmpty(customerCode)){
                item.setCustomercode(String.format("%0" + 10 + "d", Integer.parseInt(customerCode)));
            }
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
