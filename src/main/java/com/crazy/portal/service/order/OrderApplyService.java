package com.crazy.portal.service.order;

import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.crazy.portal.annotation.OperationLog;
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
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.*;
import java.util.regex.Pattern;

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
    @OperationLog
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
            IsHeader isHeader = new IsHeader();
            isHeader.setOrdertype(order.getOrderType());
            isHeader.setSalesorg(order.getSalesOrg());
            isHeader.setSoldto(order.getSoldTo());
            isHeader.setSendto(order.getSendTo());

            String expectedDeliveryMonth = orderLineEO.getExpectedDeliveryMonth();
            Pattern p = Pattern.compile("\\d{4}/(0\\d|1[0-2])");
            BusinessUtil.assertTrue(p.matcher(expectedDeliveryMonth).matches(),ErrorCodes.BusinessEnum.ORDER_DELIVERY_MONTH_ERROR);

            try {
                Date date = DateUtil.parseDate(expectedDeliveryMonth,DateUtil.MONTH_FORMAT_HLINE);
                String priceDate = DateUtil.getLastDayOfMonth(DateUtil.getYear(date),DateUtil.getMonth(date));
                isHeader.setPricedate(priceDate);
            } catch (ParseException e) {
                log.error("",e);
            }
            ItItems itItems = new ItItems();
            ItItem itItem = new ItItem();

            itItem.setSequenceno(i+"");
            itItem.setProductid(orderLineEO.getMaterialNumber());
            itItem.setOrderquantity(orderLineEO.getNum());

            ProductInfoDO params = new ProductInfoDO();
            params.setSapMid(orderLineEO.getMaterialNumber());
            List<ProductInfoDO> productInfoDOS = productInfoDOMapper.selectProductInfo(params);
            ProductInfoDO productInfo = productInfoDOS.get(0);
            Assert.notNull(productInfo,"根据物料号查询不到商品信息");

            itItem.setPlatform(productInfo.getPlatform());
            itItems.setItem(Arrays.asList(itItem));
            ZrfcsdpricesimulateContent content = new ZrfcsdpricesimulateContent(null,isHeader,itItems);
            ZrfcsdpricesimulateBody body = new ZrfcsdpricesimulateBody(content);
            Zrfcsdpricesimulate zrfcsdpricesimulate = new Zrfcsdpricesimulate(body);
            ZrfcsdpricesimulateResponse response = orderApiService.priceSimulate(zrfcsdpricesimulate);

            ZpricessimulateHeaderOut esHeader = response.getEsHeader();
            orderLineEO.setGrossValue(esHeader.getGrossvalue());
            orderLineEO.setNetVale(esHeader.getNetvalue());
        }
        return records;
    }
}
