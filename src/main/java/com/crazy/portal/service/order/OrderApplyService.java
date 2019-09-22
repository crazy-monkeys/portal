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

            String productId = orderLineEO.getProductId();
            String unit = orderLineEO.getUnit();
            String num = orderLineEO.getNum();
            Date expectedDeliveryMonth = orderLineEO.getExpectedDeliveryMonth();

            boolean paramsCheck = StringUtil.isEmpty(productId)
                                        || StringUtil.isEmpty(unit)
                                        || StringUtil.isEmpty(num)
                                        || expectedDeliveryMonth == null;

            BusinessUtil.assertFlase(paramsCheck,ErrorCodes.BusinessEnum.ORDER_LINE_FILE_ERROR);

            IsHeader isHeader = new IsHeader();
            isHeader.setOrdertype(order.getOrderType());
            isHeader.setSalesorg(order.getSalesOrg());
            isHeader.setSoldto(order.getSoldTo());
            isHeader.setSendto(order.getSendTo());
            String priceDate = DateUtil.getLastDayOfMonth(DateUtil.getYear(expectedDeliveryMonth),DateUtil.getMonth(expectedDeliveryMonth));
            isHeader.setPricedate(priceDate);
            orderLineEO.setPriceDate(priceDate);

            ItItems itItems = new ItItems();
            ItItem itItem = new ItItem();

            itItem.setSequenceno((i+1)+"");
            itItem.setProductid(productId);
            itItem.setOrderquantity(num);

            ProductInfoDO params = new ProductInfoDO();
            params.setSapMid(productId);
            List<ProductInfoDO> productInfoDOS = productInfoDOMapper.selectProductInfo(params);
            BusinessUtil.assertFlase(productInfoDOS.isEmpty(),ErrorCodes.BusinessEnum.ORDER_NOT_EXISTS_PRODUCT_ID);

            ProductInfoDO productInfo = productInfoDOS.get(0);
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
