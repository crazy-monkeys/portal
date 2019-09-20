package com.crazy.portal.service.order;

import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.crazy.portal.annotation.OperationLog;
import com.crazy.portal.bean.business.idr.BusinessFileUploadBean;
import com.crazy.portal.bean.customer.basic.FileVO;
import com.crazy.portal.bean.order.*;
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
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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
     * 订单申请
     * @param order
     * @param userId
     */
    @OperationLog
    @Transactional
    public void apply(Order order, Integer userId){
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
     * 变更交货日期
     */
    @OperationLog
    public void modifyDeliveryDate(BatchModifyOrderBean bean, Integer userId) throws Exception{
        for (Integer id : bean.getOrderIds()) {
            BusinessUtil.notNull(id, ErrorCodes.BusinessEnum.ORDER_ID_IS_REQUIRED);
            BusinessUtil.assertTrue(DateUtil.isValidDateFormat(bean.getDeliveryDate(), DateUtil.WEB_FORMAT), ErrorCodes.BusinessEnum.ORDER_DELIVERY_DATE_FORMAT_FAIL);
            Order order = new Order();
            order.setId(id);
            order.setDeliveryDate(DateUtil.parseDate(bean.getDeliveryDate(), DateUtil.WEB_FORMAT));
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
        if(bean.getApprovalStatus().equals(Enums.OrderApprovalStatus.ADOPT.getValue())){
            sendOrderCreateRequest(order);
        }
        order.setApprovalStatus(bean.getApprovalStatus());
        order.setRejectReason(bean.getRejectReason());
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
        IsHeader isHeader = new IsHeader();
        isHeader.setPortalorderid(order.getId().toString());
        isHeader.setOrdertype(order.getOrderType());
        isHeader.setSalesorg(order.getSalesOrgId().toString());
        isHeader.setChannel("10");
        isHeader.setDivision("00");
        isHeader.setSalesoffice("0003");
        isHeader.setSalesgroup("031");
        isHeader.setSoldto(order.getSoldParty());
        isHeader.setSendto(order.getShipParty());
        isHeader.setPurchaseno(order.getPurchaseOrderNo());
        isHeader.setPurchasedate(DateUtil.format(order.getPurchaseOrderDate(), DateUtil.SHORT_FORMAT));
        isHeader.setPaymentterms(order.getPayCondition());
        isHeader.setCustomergroup1(order.getCustomerGroup1());
        isHeader.setCustomergroup2(order.getCustomerGroup2());
        isHeader.setPricedate(DateUtil.format(order.getDeliveryDate(), DateUtil.SHORT_FORMAT));
        isHeader.setRefsaporderid("");
        isHeader.setInco1(order.getInco1());
        isHeader.setInco2(order.getInco2());
        isHeader.setAugru("005");

        Integer line_no = 1;
        Map<Integer, Integer> lineMap = new HashMap<>();
        List<ItItem> items = new ArrayList<>();
        for (OrderLine line : order.getLines()) {
            ItItem item = new ItItem();
            item.setSequenceno(String.valueOf(line_no));
            item.setProductid(line.getMaterialNumber());
            item.setOrderquantity(String.valueOf(line.getNum()));
            item.setPlatform(line.getPlatform());
            item.setRequestdate(DateUtil.format(line.getExpectedDeliveryDate(), DateUtil.SHORT_FORMAT));
            item.setRefsaporderitemno("");
            items.add(item);
            lineMap.put(line_no, line.getId());
            line_no ++;
        }
        ItItems itItems = new ItItems();
        itItems.setItem(items);

        ZrfcsdsalesordercreateContent content = new ZrfcsdsalesordercreateContent(null,isHeader,itItems);
        ZrfcsdsalesordercreateBody zrfcsdsalesordercreateBody = new ZrfcsdsalesordercreateBody(content);
        Zrfcsdsalesordercreate request = new Zrfcsdsalesordercreate(zrfcsdsalesordercreateBody);
        log.info("提交订单申请：{}", request);
        ZrfcsdsalesordercreateResponse response = orderApiService.createSalesOrder(request);
        log.info("订单申请返回：{}", response);

        order.setRTotalAmountIncludTax(response.getEsHeader().getGrossvalue());
        order.setRTotalAmountExcludeTax(response.getEsHeader().getNetvalue());
        order.setRSuccessFlag(response.getEsHeader().getResulttype());
        order.setRErrorMsg(response.getEsHeader().getResultmessage());
        for(ZsalesordercreateOutItem etItem : response.getEtItems().getItem()){
            OrderLine orderLine = new OrderLine();
            orderLine.setId(lineMap.get(etItem.getSequenceno()));
            orderLine.setRSapOrderLineItemNumber(etItem.getItemno());
            orderLine.setRPriceIncludTax(etItem.getPrice());
            orderLine.setRPriceExcludeTax(etItem.getNetprice());
            orderLine.setRCurrency(etItem.getCurrency());
            orderLine.setRMaterialNumber(etItem.getProductid());
            orderLine.setRLineItemCategories(etItem.getItemcategory());
            orderLine.setRRelevanceLineNumber(etItem.getRefitemno());
            orderLine.setRRelevanceMaterialNumber(etItem.getRefitemproductid());
            orderLineMapper.updateByPrimaryKeySelective(orderLine);
        }

    }

    /**
     * 模板下载
     */
    public void templateDownload(HttpServletResponse response) throws Exception{
        Map<String, List<? extends BaseRowModel>> resultMap = new HashMap<>();
        resultMap.put("sheet1", Collections.singletonList(new OrderLineEO()));
        ExcelUtils.createExcelStreamMutilByEaysExcel(response, resultMap, "订单模板", ExcelTypeEnum.XLSX);
    }

    /**
     * 上传附件
     * @return
     */
    public List<BaseRowModel> upload(MultipartFile file, Integer userId){
        List<BaseRowModel> records = ExcelUtils.readExcel(file, OrderLineEO.class);
        return records;
    }
}
