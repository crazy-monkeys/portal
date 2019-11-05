package com.crazy.portal.service.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.crazy.portal.bean.order.*;
import com.crazy.portal.bean.order.wsdl.price.*;
import com.crazy.portal.bean.price.CatalogPriceVO;
import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.dao.cusotmer.CustomerInfoMapper;
import com.crazy.portal.dao.order.*;
import com.crazy.portal.dao.product.ProductInfoDOMapper;
import com.crazy.portal.entity.cusotmer.CustomerInfo;
import com.crazy.portal.entity.handover.ReceiveDetail;
import com.crazy.portal.entity.order.*;
import com.crazy.portal.entity.price.CatalogPrice;
import com.crazy.portal.entity.product.ProductInfoDO;
import com.crazy.portal.entity.system.SysParameter;
import com.crazy.portal.entity.system.User;
import com.crazy.portal.service.customer.CustomerInfoService;
import com.crazy.portal.service.handover.ReceiveService;
import com.crazy.portal.service.price.CatalogPriceService;
import com.crazy.portal.service.system.SysParamService;
import com.crazy.portal.util.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
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
public class OrderApplyService extends CommonOrderService{

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
    @Resource
    private ReceiveService receiveService;
    @Resource
    private SysParamService sysParamService;
    @Resource
    private CatalogPriceService catalogPriceService;

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

    public OrderApply detail(Integer id){
        OrderApply orderApply = orderApplyMapper.selectByPrimaryKey(id);
        List<OrderLine> lines = orderApply.lineJsonToObj(orderApply.getJsonLines());
        super.resetLines(lines);
        orderApply.setJsonLines(JSONArray.parseArray(JSON.toJSONString(lines)));
        return orderApply;
    }

    /**
     * 解析附件并作调价试算
     * @return
     */
    public Map<String,Object> parsingLineTmplFile(OrderApply order, User user) throws Exception{
        Map<String,Object> map = new HashMap<>();
       /* if(order.getSalesOrg().equals("3000")){
            order.setPaymentTerms("9994");
        }*/
        //解析excel
        List<OrderLineEO> records = ExcelUtils.readExcel(order.getLineFile(), OrderLineEO.class);
        log.info("records -> "+ records.toString());
        //检查
        this.checkLines(user, records);

        //逻辑只允许出现同一个月份,直接用第一个元素为标准
        String expectedDeliveryMonthStr = records.get(0).getExpectedDeliveryMonth();
        BusinessUtil.notNull(expectedDeliveryMonthStr,ErrorCodes.BusinessEnum.ORDER_EMPTY_EXPECTEDDELIVERYMONTH);

        Date expectedDeliveryMonth = DateUtil.parseDate(expectedDeliveryMonthStr,DateUtil.MONTH_FORMAT_HLINE);
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

        for(OrderLineEO orderLineEO : records){
            BigDecimal price = BigDecimal.ZERO;
            BigDecimal netprice = BigDecimal.ZERO;

            for(ZpricessimulateItemOut item : items){
                String refitemProductID = item.getRefitemproductid().replaceAll("^(0+)", "");
                String productId = item.getProductid().replaceAll("^(0+)", "");

                if(refitemProductID.equals(orderLineEO.getProductId()) &&
                        !productId.equals(orderLineEO.getProductId())){

                    price = price.add(item.getPrice());
                    netprice = netprice.add(item.getNetprice());
                }
            }
            orderLineEO.setRPrice(price == null ? BigDecimal.ZERO : price);
            orderLineEO.setRNetPrice(netprice == null ? BigDecimal.ZERO : netprice);
            //设置定价
            orderLineEO.setPriceDate(priceDate);

            ProductInfoDO productInfo = super.getProductInfo(orderLineEO.getProductId(), orderLineEO.getPlatform());
            if(productInfo != null){
                orderLineEO.setProduct(productInfo.getProduct());
                orderLineEO.setBu(productInfo.getBu());
                orderLineEO.setPdt(productInfo.getPdt());
            }
        }

        map.put("lines",records);
        map.put("grossValue",esHeader.getGrossvalue());
        map.put("netValue",esHeader.getNetvalue());
        return map;
    }


    /**
     * 检查订单行中数据是否有与之匹配的价格
     * @param user
     * @param records
     */
    private void checkLines(User user, List<OrderLineEO> records) {
        //获取当前登录人价格列表
        List<CatalogPrice> currentCatalogPrices = this.getCurrentCatalogPrice(user);

        //判断当前物料是否在申请人的能看到的价格列表中,不在的话抛出异常，在的话提取外部客户编码进行价格模拟计算
        records.stream().forEach(x -> {
            String custAbbreviation = x.getCustAbbreviation();

            //检查客户简称是否存在,如果为空放行
            if(StringUtil.isNotEmpty(custAbbreviation)){
                CustomerInfo customerInfo = customerInfoMapper.selectInCustomerByAbb(custAbbreviation);
                BusinessUtil.notNull(customerInfo,ErrorCodes.BusinessEnum.ORDER_INVALID_CUSTABBREVIATION);
            }
            //检查申请的物料信息是否在自己的物料目录价中存在
            boolean isExists = this.isExistsCatalogPrice(currentCatalogPrices, x);

            //如果不存在,提示错误
            if(!isExists){
                String hint = String.format(ErrorCodes.BusinessEnum.ORDER_NO_MAPPING_CUST.getZhMsg()
                        ,x.getProductId(),x.getCustAbbreviation());

                throw new BusinessException(ErrorCodes.BusinessEnum.ORDER_NO_MAPPING_CUST.getCode(),hint);
            }
        });

        //转换orderLines
        List<OrderLine> lines = records.stream().map(lineEO -> {
            OrderLine orderLine = new OrderLine();
            orderLine.setProductId(lineEO.getProductId());
            orderLine.setPlatform(lineEO.getPlatform());
            return orderLine;
        }).collect(Collectors.toList());

        //处理重复订单行
        super.processRepeatedLine(lines);


    }

    /**
     * 校验当前物料是否存在于价格列表中
     * @param currentCatalogPrices
     * @param x
     * @return
     */
    private boolean isExistsCatalogPrice(List<CatalogPrice> currentCatalogPrices, OrderLineEO x) {
        for(CatalogPrice catalogPrice : currentCatalogPrices){
            ProductInfoDO productInfoDO = productInfoDOMapper.selectBySapMidAndPlatForm(x.getProductId(),x.getPlatform());
            if(null == productInfoDO){
                return false;
            }
            if(catalogPrice.getSapCode().equals(x.getProductId())
                    && catalogPrice.getBu().equals(productInfoDO.getBu())){

                if(StringUtil.isEmpty(catalogPrice.getInCustomer())||catalogPrice.getInCustomer().equals(x.getCustAbbreviation())){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 查询当前登录人能看到的所有目录价格
     * @param user
     * @return
     */
    private List<CatalogPrice> getCurrentCatalogPrice(User user) {
        CatalogPriceVO catalogPriceVO = new CatalogPriceVO();
        catalogPriceVO.setPageSize(Integer.MAX_VALUE);
        PageInfo<CatalogPrice> catalogPricesWithPage = catalogPriceService.findCatalogPricesWithPage(catalogPriceVO, user);
        return catalogPricesWithPage.getList();
    }

    /**
     * 订单申请
     * @param order
     * @param userId
     */
    @Transactional
    public void createOrderApply(OrderApply order, Integer userId){
        //参数校验
        this.checkParameter(order);

        CustomerInfo dealerByUser = customerInfoService.getDealerByUser(userId);
        BusinessUtil.notNull(dealerByUser, ErrorCodes.BusinessEnum.CUSTOMER_IS_EMPYT);
        order.setDealerId(dealerByUser.getId());
        order.setApprovalStatus(Enums.OrderApprovalStatus.WAIT_APPROVAL.getValue());
        order.setCreateId(userId);
        order.setCreateTime(DateUtil.getCurrentTS());
        order.setActive(1);
        order.setAppalyType(1);

        List<OrderLine> orderLines = order.getOrderLines();
        Date priceDate = this.getPriceDate(orderLines.get(0).getExpectedDeliveryMonth());
        order.setPriceDate(DateUtil.getLastDayOfMonth(DateUtil.getYear(priceDate),DateUtil.getMonth(priceDate)));

        orderLines.forEach(x->{
            ProductInfoDO productInfoDO = super.getProductInfo(x.getProductId(),x.getPlatform());
            if(productInfoDO != null){
                x.setProduct(productInfoDO.getProduct());
                x.setBu(productInfoDO.getBu());
                x.setPdt(productInfoDO.getPdt());
            }
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
     * 参数检查
     * @param order
     */
    private void checkParameter(OrderApply order) {
        BusinessUtil.notNull(order, ErrorCodes.BusinessEnum.ORDER_INFO_IS_REQUIRED);
        List<OrderLine> lines = order.getOrderLines();
        BusinessUtil.notNull(lines, ErrorCodes.BusinessEnum.ORDER_LINES_IS_REQUIRED);
        BusinessUtil.assertFlase(lines.isEmpty(), ErrorCodes.BusinessEnum.ORDER_LINES_IS_REQUIRED);

        //检查金额是否为0
        lines.stream().forEach(x->{
            BusinessUtil.assertEmpty(x.getProductId(),ErrorCodes.BusinessEnum.ORDER_EMPTY_PRODUCT);
            boolean priceCheck = Objects.isNull(x.getRPrice()) || BigDecimal.ZERO.equals(x.getRPrice()) ||
                                 Objects.isNull(x.getRNetPrice()) || BigDecimal.ZERO.equals(x.getRNetPrice());

            if(priceCheck && !order.getUnderOrderType().equals("ZFD")){
                throw new BusinessException(ErrorCodes.BusinessEnum.ORDER_INVALID_PRODUCT.getCode(),
                        String.format(ErrorCodes.BusinessEnum.ORDER_INVALID_PRODUCT.getZhMsg(),x.getProductId()));
            }
        });

        //处理重复的订单行
        super.processRepeatedLine(lines);
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
        orderApply.setGrossValue(order.getRGrossValue());
        orderApply.setNetValue(order.getRNetValue());
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
        orderApply.setGrossValue(order.getRGrossValue());
        orderApply.setNetValue(order.getRNetValue());
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

        boolean result = this.hasApprovalPendingOrder(order.getRSapOrderId());
        BusinessUtil.assertFlase(result, ErrorCodes.BusinessEnum.ORDER_PENDING_ORDER);

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
            //逻辑只允许出现同一个月份
            String expectedDeliveryMonthStr = orderLineEO.getExpectedDeliveryMonth();
            BusinessUtil.assertEmpty(productId,ErrorCodes.BusinessEnum.ORDER_EMPTY_PRODUCT_ID);
            BusinessUtil.assertEmpty(num,ErrorCodes.BusinessEnum.ORDER_EMPTY_NUM);
            BusinessUtil.assertEmpty(platform,ErrorCodes.BusinessEnum.ORDER_EMPTY_PLATFORM);
            BusinessUtil.notNull(expectedDeliveryMonthStr,ErrorCodes.BusinessEnum.ORDER_EMPTY_EXPECTEDDELIVERYMONTH);

            ProductInfoDO params = new ProductInfoDO();
            params.setSapMid(productId);
            params.setPlatform(platform);
            ProductInfoDO productInfoDO = productInfoDOMapper.selectBySapMidAndPlatForm(productId,platform);
            BusinessUtil.notNull(productInfoDO, ErrorCodes.BusinessEnum.ORDER_NOT_EXISTS_PRODUCT_ID);

            ItItem itItem = new ItItem();
            itItem.setSequenceno((i+1)+"");
            itItem.setProductid(productId);
            itItem.setOrderquantity(num);
            SysParameter sysParameter = sysParamService.selectParam("4","1",productInfoDO.getBu());
            itItem.setKondm(sysParameter.getZhName());
            //根据物料号获取平台
            itItem.setPlatform(productInfoDO.getPlatform());
            String customerCode = this.getInCodeByAbbreviation(orderLineEO.getCustAbbreviation());
            if(StringUtils.isNotEmpty(customerCode)){
                itItem.setCustomercode(String.format("%0" + 10 + "d", Integer.parseInt(customerCode)));
            }
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
        boolean result = this.hasApprovalPendingOrder(order.getRSapOrderId());
        BusinessUtil.assertFlase(result, ErrorCodes.BusinessEnum.ORDER_PENDING_ORDER);

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

        List<OrderLine> orderLine = orderLineMapper.selectByOrderIdForVirtual(order.getId());
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
                    deliverOrderLine.setSapDeliverOrderLineNo(o.getRItemNo());
                    deliverOrderLine.setProductId(o.getProductId());
                    deliverOrderLine.setDeliveryQuantity(e.getDeliveryQuantity());
                    deliverOrderLine.setActive(1);
                    deliverOrderLine.setCreateUserId(userId);

                    ProductInfoDO productInfo = productInfoDOMapper.selectBySapMidAndPlatForm(o.getProductId(),o.getPlatform());
                    if(null != productInfo){
                        deliverOrderLine.setProduct(productInfo.getProduct());
                        deliverOrderLine.setBu(productInfo.getBu());
                        deliverOrderLine.setPdt(productInfo.getPdt());
                        deliverOrderLine.setPlatform(productInfo.getPlatform());
                    }
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
        List<DeliverOrderLine> deliverOrderLines = deliverOrderLineMapper.selectByDeliveryOrderId(order.getDeliverOrderId());
        deliverOrderLines.forEach(e->{
            BusinessUtil.assertFlase(e.getReceiveQuantity()>0,ErrorCodes.BusinessEnum.DELIVERY_ORDER_IS_RECEVE);
        });
        //生成修改审批单
        saveDeliverOrderUpdateApproval(order, deliverOrder, Enums.OrderApprovalType.UPDATE.getValue(), userId);
    }

    @Transactional
    public void cancelDeliveryOrder(DeliveryOrderCancelVO vo, Integer userId){
        //获取对应的提货单
        DeliverOrder deliverOrder = deliverOrderMapper.selectByPrimaryKey(vo.getDeliveryOrderId());
        BusinessUtil.assertFlase(null == deliverOrder,ErrorCodes.BusinessEnum.ORDER_NOT_FOUND);

        //校验是否有审批中的提货单
        List<DeliverOrderApproval> approval = deliverOrderApprovalMapper.checkApproval(deliverOrder.getSapOrderNo());
        BusinessUtil.assertFlase(null != approval && !approval.isEmpty(), ErrorCodes.BusinessEnum.ORDER_IS_INACTIVE);

        //获取提货的明细行
        List<DeliverOrderLine> orderLines = deliverOrderLineMapper.selectByDeliveryOrderId(deliverOrder.getDeliverOrderId());
        orderLines.forEach(e->{
            BusinessUtil.assertFlase(e.getReceiveQuantity()>0,ErrorCodes.BusinessEnum.DELIVERY_ORDER_IS_RECEVE);
        });
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

    /**
     * 提货取消
     * @param deliverOrder  对应的提货单
     * @param cancelDeliverOrderLineIds  取消的提货单行
     * @param userId
     */
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

    public void receiving(DeliveryOrderVO bean, Integer userId){
        List<ReceiveDetail> receiveData = new ArrayList<>();
        List<DeliveryOrderLineVO> orderLineVOS = bean.getOrderLine();

        orderLineVOS.forEach(e->{
            DeliverOrderLine deliverOrderLine = deliverOrderLineMapper.selectByPrimaryKey(e.getId());
            Integer qty = 0;
            if(null == e.getDeliveryQuantity()){
                qty = deliverOrderLine.getDeliveryQuantity() - deliverOrderLine.getReceiveQuantity();
            }else{
                qty = e.getDeliveryQuantity();
            }
            BusinessUtil.assertFlase(qty==0 , ErrorCodes.BusinessEnum.QTY_IS_WHOLE);
            BusinessUtil.assertFlase(deliverOrderLine.getDeliveryQuantity() < qty+deliverOrderLine.getReceiveQuantity() , ErrorCodes.BusinessEnum.QTY_IS_NOT);

            DeliverOrder deliverOrder = deliverOrderMapper.selectByPrimaryKey(deliverOrderLine.getDeliverOrderId());
            Order order = orderMapper.selectByPrimaryKey(deliverOrder.getSalesOrderId());
            CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(order.getDealerId());
            receiveData.add(mappingRecive(deliverOrderLine, qty, order, deliverOrder,customerInfo));
        });
        boolean flg = receiveService.pushReceiveDataToBi(receiveData, userId);
        if(flg){
            receiveData.forEach(e->{
                deliverOrderLineMapper.updateReciveQty(e.getDeliveryOrderId(),new BigDecimal(e.getDeliveryNum()));
            });
        }
    }

    private ReceiveDetail mappingRecive(DeliverOrderLine deliverOrderLine, Integer qty, Order order, DeliverOrder deliverOrder, CustomerInfo customerInfo){
        OrderLine orderLine = orderLineMapper.selectByPrimaryKey(deliverOrderLine.getSalesOrderLineId());
        ProductInfoDO productInfoDO = productInfoDOMapper.selectBySapMidAndPlatForm(orderLine.getProductId(), orderLine.getPlatform());
        ReceiveDetail detail = new ReceiveDetail();
        detail.setDeliveryOrderId(deliverOrderLine.getDeliverOrderLineId());
        detail.setDealerName(customerInfo.getCustName());
        detail.setCustomerType(order.getCustomerAttr().equals("B2")?"Mass Market":"Account Market");
        detail.setDeliveryNum(String.valueOf(qty));
        detail.setProductModel(productInfoDO.getProduct());
        detail.setPlatform(productInfoDO.getPlatform());
        String category = "";
        if(order.getOrderType().equals("A01")){
            category = "客户专货";
        }else if (order.getOrderType().equals("A02")){
            category = "Buffer";
        }else if (order.getOrderType().equals("A03")){
            category = "新产品";
        }else if (order.getOrderType().equals("A04")){
            category = "样品";
        }else if (order.getOrderType().equals("A05")){
            category = "Last Buy";
        }else if (order.getOrderType().equals("A06")){
            category = "分销商专货";
        }
        detail.setInventoryCategory(category);
        detail.setInventoryUnitPrice(orderLine.getRPrice());
        detail.setSalesOrganization("3000");
        detail.setDeliveryTime(DateUtil.format(new Date(),DateUtil.WEB_FORMAT));
        detail.setDeliveryCompany(deliverOrder.getShippingPoint());
        detail.setPurchaseNumber(order.getPurchaseNo());
        return detail;
    }
}
