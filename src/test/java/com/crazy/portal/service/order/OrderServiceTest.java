package com.crazy.portal.service.order;

import com.alibaba.fastjson.JSON;
import com.crazy.portal.bean.order.wsdl.createOrder.request.*;
import com.crazy.portal.bean.order.wsdl.createOrder.responce.ZrfcsdsalesordercreateResponse;
import com.crazy.portal.bean.order.wsdl.customerRate.responce.ZrfcsdcustomercrrateResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 00:33 2019-09-11
 * @Modified by:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class OrderServiceTest {

    @Resource
    private OrderService eccApiService;

    @Test
    public void getCustomerRate() {
        ZrfcsdcustomercrrateResponse response = eccApiService.getCustomerRate("100217");
        log.info(JSON.toJSONString(response));
    }

    @Test
    public void createSalesOrder() {
        IsHeader isHeader = new IsHeader();
        isHeader.setPortalorderid("4");
        isHeader.setOrdertype("ZOR");
        isHeader.setSalesorg("3000");
        isHeader.setChannel("10");
        isHeader.setDivision("00");
        isHeader.setSalesoffice("0001");
        isHeader.setSalesgroup("002");
        isHeader.setSoldto("100158");
        isHeader.setSendto("100158");
        isHeader.setPurchaseno("100158");
        isHeader.setPurchasedate("20190829");
        isHeader.setPaymentterms("0001");
        isHeader.setCustomergroup1("A02");
        isHeader.setCustomergroup2("B1");
        isHeader.setPricedate("20190829");
        isHeader.setRefsaporderid("");
        isHeader.setInco1("FH");
        isHeader.setInco2("123");
        isHeader.setAugru("005");

        ItItems itItems = new ItItems();
        ItItem itItem = new ItItem();
        itItem.setSequenceno("1");
        itItem.setProductid("18000000017");
        itItem.setOrderquantity("1");
        itItem.setPlatform("C216B");
        itItem.setRequestdate("20190829");
        itItem.setRefsaporderitemno("");
        itItems.setItem(Arrays.asList(itItem));

        ZrfcsdsalesordercreateContent content = new ZrfcsdsalesordercreateContent(null,isHeader,itItems);
        ZrfcsdsalesordercreateBody zrfcsdsalesordercreateBody = new ZrfcsdsalesordercreateBody(content);
        Zrfcsdsalesordercreate order = new Zrfcsdsalesordercreate(zrfcsdsalesordercreateBody);

        ZrfcsdsalesordercreateResponse response = eccApiService.createSalesOrder(order);
        log.info(JSON.toJSONString(response));
    }
}