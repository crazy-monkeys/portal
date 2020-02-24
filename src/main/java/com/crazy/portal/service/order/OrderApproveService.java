package com.crazy.portal.service.order;

import com.alibaba.fastjson.JSON;
import com.crazy.portal.bean.order.OrderApprovalBean;
import com.crazy.portal.bean.order.wsdl.change.*;
import com.crazy.portal.bean.order.wsdl.create.EtItems;
import com.crazy.portal.bean.order.wsdl.create.IsHeader;
import com.crazy.portal.bean.order.wsdl.create.ItItem;
import com.crazy.portal.bean.order.wsdl.create.ItItems;
import com.crazy.portal.bean.order.wsdl.create.*;
import com.crazy.portal.dao.cusotmer.CustCorporateRelationshipMapper;
import com.crazy.portal.dao.cusotmer.CustomerInfoMapper;
import com.crazy.portal.dao.order.OrderApplyMapper;
import com.crazy.portal.dao.order.OrderLineMapper;
import com.crazy.portal.dao.order.OrderMapper;
import com.crazy.portal.dao.product.ProductInfoDOMapper;
import com.crazy.portal.dao.system.UserMapper;
import com.crazy.portal.entity.cusotmer.CustCorporateRelationship;
import com.crazy.portal.entity.cusotmer.CustomerInfo;
import com.crazy.portal.entity.order.*;
import com.crazy.portal.entity.product.ProductInfoDO;
import com.crazy.portal.entity.system.SysParameter;
import com.crazy.portal.entity.system.User;
import com.crazy.portal.service.system.SysParamService;
import com.crazy.portal.service.webservice.ApiUsersService;
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
    private UserMapper userMapper;
    @Resource
    private ProductInfoDOMapper productInfoDOMapper;
    @Resource
    private CustomerInfoMapper customerInfoMapper;
    @Resource
    private SysParamService sysParamService;
    @Resource
    private ApiUsersService apiUsersService;
    @Resource
    private CustCorporateRelationshipMapper custCorporateRelationshipMapper;
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
                    Order createOrder = this.createOrder(bean.getExpectedDeliveryDate(), orderApply, userId);
                    this.savePoAdditionalOrder(createOrder,orderApply);
                    break;
                case 2:
                    Order modifyOrder = this.modifyOrder(orderApply, user);
                    this.savePoAdditionalOrder(modifyOrder,orderApply);
                    break;
                case 3:
                    this.checkCancelOrder(orderApply, user);
                    Order cancelOrder = this.cancelOrder(orderApply.getRSapOrderId(),userId);
                    this.deletePoOrder(cancelOrder);
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
     * 检查取消订单
     * @param orderApply
     */
    private void checkCancelOrder(OrderApply orderApply, User user) {
        //代理商只可以取消没有提货单的销售单行
        List<Integer> ids = orderApply.lineJsonToObj(orderApply.getJsonLines())
                .stream().map(x -> x.getId())
                .collect(Collectors.toList());

        super.checkCancelOrder(user.getUserType(),ids);
    }

    /**
     * 删除po订单
     * @param cancelOrder
     */
    private void deletePoOrder(Order cancelOrder) {
        String response = "";
        String message = "";
        List<OrderLine> lines = new ArrayList<>();
        try {
            lines = this.getPoOrderLines(cancelOrder);

            if (lines == null) return;

            String portalIds = lines.stream().map(x->x.getId().toString()).collect(Collectors.joining(","));
            orderApiService.deletePOAdditionalOrder(portalIds);
        } catch (Exception e) {
            message = e.getMessage();
            log.error("deletePoOrder error",e);
        }finally {
            apiUsersService.saveLog(String.valueOf(cancelOrder.getDealerId()), JSON.toJSONString(lines),response,new Date().getTime(),"Po 加单",message);
        }
    }

    /**
     * 获取po订单行
     * @param order
     * @return
     */
    private List<OrderLine> getPoOrderLines(Order order) {
        //只有订单类型为客户专货订单 并且 选择了客户的订单 才进行po加单
        if(order.getOrderType().equals("A01") && StringUtil.isNotEmpty(order.getOutCode())){
            List<OrderLine> lines = orderLineMapper.selectByOrderIdForVirtual(order.getId());
            if (lines.isEmpty()) {
                log.error("The order is empty");
                return null;
            }
            return lines;
        }
        return null;
    }

    /**
     * 同步Po加单数据
     * @param order
     */
    private void savePoAdditionalOrder(Order order,OrderApply orderApply){
        String response = "";
        String message = "";
        List<PoAdditionalOrderReq> poReqs = new ArrayList<>();
        try {
            //只有订单类型为客户专货订单才进行po加单同步
            List<OrderLine> lines = this.getPoOrderLines(order);
            if (lines == null) return;

            for(OrderLine line : lines){
                PoAdditionalOrderReq req = new PoAdditionalOrderReq();
                req.setPortalId(String.valueOf(line.getId()));
                req.setYearMonth(order.getPriceDate().substring(0,6));
                req.setCompany(order.getSalesOrg());

                CustCorporateRelationship rs = custCorporateRelationshipMapper.selectInCustomer(order.getDealerId());
                BusinessUtil.assertFlase(null == rs ,ErrorCodes.BusinessEnum.IN_CUSTOMER_IS_NULL);
                CustomerInfo inCustomerInfo = customerInfoMapper.selectByInCode(rs.getCorporateId());
                BusinessUtil.assertFlase(null == inCustomerInfo ,ErrorCodes.BusinessEnum.IN_CUSTOMER_IS_NULL);

                req.setAgencyIncode(inCustomerInfo.getOutCode());
                req.setCustomerIncode(orderApply.getOutCode());
                req.setSapCode(line.getProductId());
                req.setPoPrice(line.getUnitPrice().toString());
                req.setQty(line.getNum().toString());
                req.setClass3(line.getPlatform());
                poReqs.add(req);
            }
            response = orderApiService.savePOAdditionalOrderFromCRM(poReqs);
        } catch (Exception e) {
            message = e.getMessage();
            log.error("savePoAdditionalOrder error",e);
        }finally {
            apiUsersService.saveLog(String.valueOf(order.getDealerId()), JSON.toJSONString(poReqs),response,new Date().getTime(),"Po 加单",message);
        }
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
    private Order createOrder(String expectedDeliveryDate,OrderApply orderApply,Integer userId) throws Exception {

        ZrfcsdsalesordercreateResponse response = this.invokeEccCreateOrder(expectedDeliveryDate,orderApply);

        List<ZsalesordercreateOutItem> outItems = response.getEtItems().getItem();

        //获取申请单行对象
        List<OrderLine> lines = orderApply.lineJsonToObj(orderApply.getJsonLines());

        //第三方接口调用成功,将审批信息同步到结果表
        Order order = this.buildOrder(expectedDeliveryDate, orderApply, userId, response);

        //计算定价日期
        this.calculatePriceDate(order, lines);

        //保存订单头
        this.orderMapper.insertSelective(order);

        //返回的实体+虚拟的行
        a:for(ZsalesordercreateOutItem eccLine : outItems){
            //ecc物料号如果前面有0 直接替换成空字符
            String eccProductID = eccLine.getProductid().replaceAll("^(0+)", "");
            String eccRefProductId = eccLine.getRefitemproductid().replaceAll("^(0+)", "");
            String eccPlatform = eccLine.getPlatform();

            BigDecimal netprice = eccLine.getNetprice();
            BigDecimal price = eccLine.getPrice();

            b:for(OrderLine line : lines){
                String portalProductId = line.getProductId();
                String portalPlatform = line.getPlatform();
                //虚拟料信息保存
                if(eccProductID.equals(portalProductId) && eccPlatform.equals(portalPlatform) && StringUtil.isEmpty(eccRefProductId)){
                    if(!order.getUnderOrderType().equals("ZFD")){
                        super.checkPrice(eccProductID, netprice, price);
                    }
                    //设置剩余数量
                    line.setRemainingNum(line.getNum());
                    //计算组合物料价格
                    this.calculatePrice(outItems, line);
                    //设置product
                    this.setProduct(line, portalProductId);
                    //保存虚拟订单行
                    this.insertOrderLine(userId,order,line,eccLine);
                    order.getLines().add(line);
                    continue a;
                }
            }
            //实体料信息保存,需要构建新订单行
            OrderLine orderLine = new OrderLine();
            orderLine.setRPrice(price);
            orderLine.setRNetPrice(netprice);
            orderLine.setProduct(eccLine.getProductid());
            if(eccLine.getNetpr().equals(BigDecimal.ZERO) || eccLine.getKpein().equals(BigDecimal.ZERO)){
                orderLine.setUnitPrice(BigDecimal.ZERO);
            }else{
                orderLine.setUnitPrice(eccLine.getNetpr().divide(eccLine.getKpein()).setScale(6, BigDecimal.ROUND_HALF_UP));
            }
            this.insertOrderLine(userId,order,orderLine,eccLine);
            order.getLines().add(orderLine);
        }
        return order;
    }

    /**
     * 计算组合物料价格
     * @param outItems
     * @param line
     */
    private void calculatePrice(List<ZsalesordercreateOutItem> outItems, OrderLine line) {
        final String portalProductId = line.getProductId();
        final String portalPlatform = line.getPlatform();
        //过滤出主物料对应的组合物料信息
        List<ZsalesordercreateOutItem> currProductItems = outItems.stream()
                .filter(f -> {
                    String eccRefProductId = f.getRefitemproductid().replaceAll("^(0+)", "");
                    String eccProductId = f.getProductid().replaceAll("^(0+)", "");
                    String eccPlatform = f.getPlatform();
                    return (eccRefProductId.equals(portalProductId) || eccProductId.equals(portalProductId)) && eccPlatform.equals(portalPlatform);
                })
                .collect(Collectors.toList());

        //主物料计算总价
        line.setRNetPrice(currProductItems.stream()
                .map(ZsalesordercreateOutItem::getNetprice)
                .reduce(BigDecimal.ZERO,BigDecimal::add));

        line.setRPrice(currProductItems.stream()
                .map(ZsalesordercreateOutItem::getPrice)
                .reduce(BigDecimal.ZERO,BigDecimal::add));

        line.setUnitPrice(currProductItems.stream().map(x->{
            BigDecimal netpr = x.getNetpr();
            BigDecimal kpein = x.getKpein();
            if(netpr.equals(BigDecimal.ZERO) || kpein.equals(BigDecimal.ZERO)){
                return BigDecimal.ZERO;
            }
            return netpr.divide(kpein).setScale(6, BigDecimal.ROUND_HALF_UP);
        }).reduce(BigDecimal.ZERO,BigDecimal::add));
    }

    /**
     * 设置物料
     * @param line
     * @param portalProductId
     */
    private void setProduct(OrderLine line, String portalProductId) {
        ProductInfoDO productInfoDO = productInfoDOMapper.selectBySapMidAndPlatForm(portalProductId,line.getPlatform());
        BusinessUtil.notNull(productInfoDO, ErrorCodes.BusinessEnum.ORDER_NOT_EXISTS_PRODUCT_ID);

        line.setProduct(productInfoDO.getProduct());
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
            //设置月份的第一天
            Date month = DateUtil.parseDate(expectedDeliveryMonth,DateUtil.MONTH_FORMAT_HLINE);
            order.setPriceDate(DateUtil.getFirstDayOfMonth(DateUtil.getYear(month),DateUtil.getMonth(month)));
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
        line.setRCurrency(etItem.getCurrency());
        line.setRProductId(etItem.getProductid().replaceAll("^(0+)", ""));
        line.setRItemCategory(etItem.getItemcategory());
        line.setRRefItemNo(etItem.getRefitemno().replaceAll("^(0+)", ""));
        line.setRRefItemProductId(etItem.getRefitemproductid().replaceAll("^(0+)", ""));
        line.setProductId(etItem.getProductid().replaceAll("^(0+)", ""));
        line.setRItemNo(etItem.getItemno().replaceAll("^(0+)", ""));
        line.setRSapQty(etItem.getSapquantity().intValue());
        line.setRSapOrderId(etItem.getSaporderid().replaceAll("^(0+)", ""));
        line.setRPlatform(etItem.getPlatform());
        line.setPlatform(line.getPlatform() == null ? etItem.getPlatform() : line.getPlatform());
        line.setNum(etItem.getSapquantity().intValue());

        line.setKbetr(etItem.getKbetr());
        line.setWaers(etItem.getWaers());
        line.setKpein(etItem.getKpein());
        line.setNetpr(etItem.getNetpr());
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
    private Order cancelOrder(String sapOrderId,Integer userId) throws Exception{

        Order order = this.getOrderBySapOrderId(sapOrderId);
        List<OrderLine> orderLines = orderLineMapper.selectByOrderIdForVirtual(order.getId());
        //取消接口无需传递申请信息
        ZrfcsdsalesorderchangeResponse response = this.invokeEccModifyOrder(order,null,null,"D");
        TableOfZsalesorderchangeOutItem etItems = response.getEtItems();
        if(null != etItems) {
            List<ZsalesorderchangeOutItem> items = etItems.getItem();
            if (null != items && !items.isEmpty()) {
                items.forEach(sapLine ->{
                    String eccProductid = sapLine.getProductid().replaceAll("^(0+)", "");
                    orderLines.forEach(line -> {
                        if (line.getRProductId().equals(eccProductid)) {
                            //修改为失效
                            line.setActice(0);
                            line.setUpdateId(userId);
                            line.setUpdateTime(DateUtil.getCurrentTS());
                            orderLineMapper.updateByPrimaryKeySelective(line);
                            order.getLines().add(line);
                        }
                    });
                });

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
        return order;
    }

    /**
     * 执行修改订单
     * @param user
     */
    private Order modifyOrder(OrderApply orderApply,User user) throws Exception{
        Integer userId = user.getId();
        String sapOrderId = orderApply.getRSapOrderId();
        Order order = this.getOrderBySapOrderId(sapOrderId);

        //获取申请的行信息
        List<OrderLine> applyLines = orderApply.lineJsonToObj(orderApply.getJsonLines());
        //获取原订单行信息
        List<OrderLine> orderLines = orderLineMapper.selectByOrderId(order.getId());
        //检查订单数量
        super.checkApplyQuantity(user,applyLines,orderLines);

        //封装申请的物料号对应的物料信息,方便下面使用
        Map<String,OrderLine> applyLineMap = applyLines.stream().collect(
                Collectors.toMap(OrderLine::getProductIDAndPlatform, Function.identity()));

        //里面会校验是否调用成功
        ZrfcsdsalesorderchangeResponse response = this.invokeEccModifyOrder(order,orderApply,applyLineMap,"I");

        //修改订单头
        this.modifyOrderHead(orderApply, userId, order, response);
        //修改订单行
        List<ZsalesorderchangeOutItem> items = response.getEtItems().getItem();
        this.modifyOrderLines(userId, order, orderLines, applyLineMap, items);

        //重新计算订单行的剩余数量
        orderLineMapper.updateOrderNum(order.getId());
        return order;
    }

    /**
     * 修改订单行信息
     * @param userId
     * @param order
     * @param orderLines
     * @param applyLineMap
     * @param items
     */
    private void modifyOrderLines(Integer userId, Order order, List<OrderLine> orderLines, Map<String, OrderLine> applyLineMap, List<ZsalesorderchangeOutItem> items) {
        items.forEach(eccLine->{
            String eccProductId = eccLine.getProductid().replaceAll("^(0+)", "");
            //遍历portal 订单行
            orderLines.forEach(line->{
                final String portalProductId = line.getProductId();
                final String portalPlatform = line.getPlatform();
                //目前订单行只能修改数量
                OrderLine orderLine = applyLineMap.get(portalProductId + portalPlatform);
                line.setNum(orderLine == null ? eccLine.getSapquantity().intValue():orderLine.getNum());
                line.setUpdateId(userId);
                line.setUpdateTime(DateUtil.getCurrentTS());

                if(portalProductId.equals(eccProductId)){
                    if(!order.getUnderOrderType().equals("ZFD")){
                        super.checkPrice(eccProductId,eccLine.getNetprice(),eccLine.getPrice());
                    }
                    if(StringUtils.isEmpty(line.getRRefItemProductId())){
                        //虚拟料需要计算价格
                        this.calVirtualMaterialPrice(items, line, portalProductId, portalPlatform);
                    }else{
                        //实体料取自己的价格
                        line.setRPrice(eccLine.getPrice());
                        line.setRNetPrice(eccLine.getNetprice());
                    }
                    orderLineMapper.updateByPrimaryKeySelective(line);
                    order.getLines().add(line);
                }
            });
        });
    }

    /**
     * 计算虚拟物料的价格=(实体料价格+虚拟料价格),这里去实体料就行了,虚拟料价格是0
     * @param items
     * @param line
     * @param portalProductId
     * @param portalPlatform
     */
    private void calVirtualMaterialPrice(List<ZsalesorderchangeOutItem> items, OrderLine line, String portalProductId, String portalPlatform) {
        //提取虚拟物料和实体物料信息
        List<ZsalesorderchangeOutItem> currProductItems = this.getZsalesorderchangeOutItems(items, portalProductId, portalPlatform);

        line.setRPrice(currProductItems.stream()
                .map(ZsalesorderchangeOutItem::getPrice)
                .reduce(BigDecimal.ZERO,BigDecimal::add));

        line.setRNetPrice(currProductItems.stream()
                .map(ZsalesorderchangeOutItem::getNetprice)
                .reduce(BigDecimal.ZERO,BigDecimal::add));
    }

    private void modifyOrderHead(OrderApply orderApply, Integer userId, Order order, ZrfcsdsalesorderchangeResponse response) {
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
    }

    /**
     * 提取虚拟物料和实体物料信息
     * @param items
     * @param portalProductId
     * @param portalPlatform
     * @return
     */
    private List<ZsalesorderchangeOutItem> getZsalesorderchangeOutItems(List<ZsalesorderchangeOutItem> items,
                                                                        String portalProductId, String portalPlatform) {

        return items.stream().filter(f -> {
                    String eccRefProductId = f.getRefitemproductid().replaceAll("^(0+)", "");
                    String eccProductId = f.getProductid().replaceAll("^(0+)", "");
                    boolean isSameProduct = eccRefProductId.equals(portalProductId) || eccProductId.equals(portalProductId);
                    if(StringUtil.isEmpty(f.getPlatform())){
                        log.error("修改订单返回的平台为空");
                        return isSameProduct;
                    }
                    return isSameProduct && f.getPlatform().equals(portalPlatform);
                }).collect(Collectors.toList());
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
        List<OrderLine> lines = orderLineMapper.selectByOrderIdForVirtual(order.getId());

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

        List<OrderLine> orderLines = orderLineMapper.selectByOrderIdForVirtual(order.getId());
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
                item.setOrderquantity(applyLineMap.get(line.getProductId()+line.getPlatform()).getNum().toString());
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

        switch (orderApply.getUnderOrderType()){
            case "ZFD" :
                orderApply.setOrderReason("10");
                break;
            default:
                break;
        }
        return isHeader;
    }
}
