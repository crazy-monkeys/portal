package com.crazy.portal.service.order;

import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.crazy.portal.bean.order.OrderLineEO;
import com.crazy.portal.bean.order.wsdl.price.*;
import com.crazy.portal.dao.order.OrderLineMapper;
import com.crazy.portal.dao.order.OrderMapper;
import com.crazy.portal.dao.product.ProductInfoDOMapper;
import com.crazy.portal.entity.order.Order;
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
        if(order.getSalesOrg().equals("3000")){
            order.setPaymentTerms("9994");
        }
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
    public Map<String,Object> parsingLineTmplFile(Order order){
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
        BusinessUtil.notNull(response,ErrorCodes.BusinessEnum.ORDER_PRICESIMULATION_ERROR);

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
}
