package com.crazy.portal.service.order;

import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.crazy.portal.annotation.OperationLog;
import com.crazy.portal.bean.order.OrderLineEO;
import com.crazy.portal.dao.order.OrderLineMapper;
import com.crazy.portal.dao.order.OrderMapper;
import com.crazy.portal.entity.order.Order;
import com.crazy.portal.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 模板下载
     */
    public void downloadLineTmpl(HttpServletResponse response) throws Exception{
        Map<String, List<? extends BaseRowModel>> resultMap = new HashMap<>();
        resultMap.put("sheet1", Collections.singletonList(new OrderLineEO()));
        ExcelUtils.createExcelStreamMutilByEaysExcel(response, resultMap, "订单行模板", ExcelTypeEnum.XLSX);
    }

    /**
     * 解析附件
     * @return
     */
    public List<OrderLineEO> parsingLineTmplFile(Order order,MultipartFile file){
        List<OrderLineEO> records = ExcelUtils.readExcel(file, OrderLineEO.class);

//        records.forEach(x->{
//            IsHeader isHeader = new IsHeader();
//            isHeader.setPortalorderid("1");
//            isHeader.setOrdertype("ZOR");
//            isHeader.setSalesorg("7100");
//            isHeader.setChannel("10");
//            isHeader.setDivision("00");
//            isHeader.setSalesoffice("0003");
//            isHeader.setSalesgroup("031");
//            isHeader.setSoldto("300184");
//            isHeader.setSendto("300184");
//            isHeader.setPricedate("20190822");
//
//            com.crazy.portal.bean.order.wsdl.price.ItItems itItems = new com.crazy.portal.bean.order.wsdl.price.ItItems();
//            com.crazy.portal.bean.order.wsdl.price.ItItem itItem = new com.crazy.portal.bean.order.wsdl.price.ItItem();
//            //序号 从1递增
//            itItem.setSequenceno("1");
//            //物料号 需要校验是否
//            itItem.setProductid("18000000017");
//            itItem.setOrderquantity("10");
//            itItem.setPlatform("1");
//            itItems.setItem(Arrays.asList(itItem));
//
//            ZrfcsdpricesimulateContent content = new ZrfcsdpricesimulateContent(null,isHeader,itItems);
//            ZrfcsdpricesimulateBody body = new ZrfcsdpricesimulateBody(content);
//            Zrfcsdpricesimulate zrfcsdpricesimulate = new Zrfcsdpricesimulate(body);
//            ZrfcsdpricesimulateResponse response = eccApiService.priceSimulate(zrfcsdpricesimulate);
//        });
        return records;
    }
}
