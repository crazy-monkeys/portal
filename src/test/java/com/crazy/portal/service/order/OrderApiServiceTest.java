package com.crazy.portal.service.order;

import com.alibaba.fastjson.JSON;
import com.crazy.portal.bean.order.wsdl.change.Zrfcsdsalesorderchange;
import com.crazy.portal.bean.order.wsdl.change.ZrfcsdsalesorderchangeBody;
import com.crazy.portal.bean.order.wsdl.change.ZrfcsdsalesorderchangeContent;
import com.crazy.portal.bean.order.wsdl.change.ZrfcsdsalesorderchangeResponse;
import com.crazy.portal.bean.order.wsdl.create.*;
import com.crazy.portal.bean.order.wsdl.delivery.*;
import com.crazy.portal.bean.order.wsdl.delivery.Item;
import com.crazy.portal.bean.order.wsdl.delivery.create.*;
import com.crazy.portal.bean.order.wsdl.delivery.create.TItem;
import com.crazy.portal.bean.order.wsdl.delivery.update.*;
import com.crazy.portal.bean.order.wsdl.price.Zrfcsdpricesimulate;
import com.crazy.portal.bean.order.wsdl.price.ZrfcsdpricesimulateBody;
import com.crazy.portal.bean.order.wsdl.price.ZrfcsdpricesimulateContent;
import com.crazy.portal.bean.order.wsdl.price.ZrfcsdpricesimulateResponse;
import com.crazy.portal.bean.order.wsdl.rate.ZrfcsdcustomercrrateResponse;
import com.crazy.portal.util.JaxbXmlUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 00:33 2019-09-11
 * @Modified by:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class OrderApiServiceTest {

    @Resource
    private OrderApiService eccApiService;

    @Test
    public void getCustomerRate() throws Exception{
        ZrfcsdcustomercrrateResponse response = eccApiService.getCustomerRate("100217");
        Assert.assertNotNull(response);
    }

    @Test
    public void createSalesOrder() throws Exception{
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

        ZrfcsdsalesordercreateContent content = new ZrfcsdsalesordercreateContent(new EtItems(),isHeader,itItems);
        ZrfcsdsalesordercreateBody zrfcsdsalesordercreateBody = new ZrfcsdsalesordercreateBody(content);
        Zrfcsdsalesordercreate order = new Zrfcsdsalesordercreate(zrfcsdsalesordercreateBody);

        ZrfcsdsalesordercreateResponse response = eccApiService.createSalesOrder(order);
        Assert.assertNotNull(response);
    }

    @Test
    public void changeSalesOrder() throws Exception{
        com.crazy.portal.bean.order.wsdl.change.IsHeader isHeader = new com.crazy.portal.bean.order.wsdl.change.IsHeader();
        isHeader.setSalesoffice("0005");
        isHeader.setSalesgroup("002");
        isHeader.setSendto("100017");
        isHeader.setPurchaseno("178765432N");
        isHeader.setPurchasedate("20190808");
        isHeader.setOrderreason("003");
        isHeader.setPaymentterms("0001");
        isHeader.setIncoterms1("FH");
        isHeader.setIncoterms2("123");
        isHeader.setCustomergroup1("A02");
        isHeader.setCustomergroup2("B1");
        isHeader.setPricedate("20190903");

        com.crazy.portal.bean.order.wsdl.change.ItItems itItems = new com.crazy.portal.bean.order.wsdl.change.ItItems();
        com.crazy.portal.bean.order.wsdl.change.ItItem itItem = new com.crazy.portal.bean.order.wsdl.change.ItItem();
        itItem.setOperationtype("I");
        itItem.setSequenceno("1");
        itItem.setItemno("10");
        itItem.setProductid("18000000000");
        itItem.setOrderquantity("10");
        itItem.setPlatform("123");
        itItem.setRequestdate("20191001");
        itItem.setRejectreason("");
        itItems.setItem(Arrays.asList(itItem));


        ZrfcsdsalesorderchangeContent content = new ZrfcsdsalesorderchangeContent(new com.crazy.portal.bean.order.wsdl.change.EtItems(),isHeader,itItems);
        ZrfcsdsalesorderchangeBody body = new ZrfcsdsalesorderchangeBody(content);
        Zrfcsdsalesorderchange zrfcsdsalesorderchange = new Zrfcsdsalesorderchange(body);
        ZrfcsdsalesorderchangeResponse response = eccApiService.changeSalesOrder(zrfcsdsalesorderchange);
        log.info(JSON.toJSONString(response));
        Assert.assertNotNull(response);
    }

    @Test
    public void priceSimulate() throws Exception{

        com.crazy.portal.bean.order.wsdl.price.IsHeader isHeader = new com.crazy.portal.bean.order.wsdl.price.IsHeader();
        isHeader.setPortalorderid("1");
        isHeader.setOrdertype("ZOR");
        isHeader.setSalesorg("7100");
        isHeader.setChannel("10");
        isHeader.setDivision("00");
        isHeader.setSalesoffice("0003");
        isHeader.setSalesgroup("031");
        isHeader.setSoldto("300184");
        isHeader.setSendto("300184");
        isHeader.setPricedate("20190822");

        com.crazy.portal.bean.order.wsdl.price.ItItems itItems = new com.crazy.portal.bean.order.wsdl.price.ItItems();
        com.crazy.portal.bean.order.wsdl.price.ItItem itItem = new com.crazy.portal.bean.order.wsdl.price.ItItem();
        //序号 从1递增
        itItem.setSequenceno("1");
        //物料号 需要校验是否
        itItem.setProductid("18000000017");
        itItem.setOrderquantity("10");
        itItem.setPlatform("1");
        itItem.setKondm("Z1");
        itItems.setItem(Arrays.asList(itItem));

        ZrfcsdpricesimulateContent content = new ZrfcsdpricesimulateContent(new com.crazy.portal.bean.order.wsdl.price.EtItems(),isHeader,itItems);
        ZrfcsdpricesimulateBody body = new ZrfcsdpricesimulateBody(content);
        Zrfcsdpricesimulate zrfcsdpricesimulate = new Zrfcsdpricesimulate(body);
        ZrfcsdpricesimulateResponse response = eccApiService.priceSimulate(zrfcsdpricesimulate);
        log.info(JSON.toJSONString(response));

        Assert.assertNotNull(response);
    }

    @Test
    public void getDeliverylist(){
        ZrfcsddeliverylistContent content = new ZrfcsddeliverylistContent();
        content.setChangedfrom("2019-07-01");
        content.setChangedto("2019-08-02");
        Item item = new Item();
        content.setEtList(Arrays.asList(item));
        ZrfcsddeliverylistBody body = new ZrfcsddeliverylistBody(content);
        Zrfcsddeliverylist zrfcsddeliverylist = new Zrfcsddeliverylist(body);
        ZrfcsddeliverylistResponse response = eccApiService.deliveryList(zrfcsddeliverylist);
        log.info(JSON.toJSONString(response));
        Assert.assertNotNull(response);
    }

    @Test
    public void createDeliveryOrder(){
        ZrfcsdDeliveryCreateContent content = new ZrfcsdDeliveryCreateContent();
        content.setDeliverydate("2019-09-09");
        content.setDeliveryIoc("10");
        content.setPortalDeliveryId("00001");
        content.setSapOrderId("35398");

        TItem tItem = new TItem();
        List<com.crazy.portal.bean.order.wsdl.delivery.create.Item> items = new ArrayList<>();
        com.crazy.portal.bean.order.wsdl.delivery.create.Item item = new com.crazy.portal.bean.order.wsdl.delivery.create.Item();
        item.setDeliveryItemNo("10");
        item.setDeliveryQuantity("10");
        item.setItemNo("1");
        item.setProductId("18000000017");
        items.add(item);

        tItem.setItems(items);
        content.setTItem(tItem);

        ZrfcsdDeliveryCreateBody body = new ZrfcsdDeliveryCreateBody(content);
        ZrfcsdDeliveryCreate create = new ZrfcsdDeliveryCreate(body);
        try{
            ZrfcsddeliverycreateResponse response = eccApiService.deliveryCreate(create);
            System.out.println(response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void changerDeliveryOrder(){
        ZrfcsdDeliveryUpdateContent content = new ZrfcsdDeliveryUpdateContent();
        content.setDeliverydate("2019-09-01");
        content.setIType("D");
        content.setSapDeliveryId("80079594");

        com.crazy.portal.bean.order.wsdl.delivery.update.TItem tItem = new com.crazy.portal.bean.order.wsdl.delivery.update.TItem();
        List<com.crazy.portal.bean.order.wsdl.delivery.update.Item> items = new ArrayList<>();
        com.crazy.portal.bean.order.wsdl.delivery.update.Item item = new com.crazy.portal.bean.order.wsdl.delivery.update.Item();
        item.setDeliveryItemNo("");
        item.setDeliveryQuantity("");
        item.setOperationType("");
        items.add(item);
        tItem.setItems(items);
        content.setTItem(tItem);

        ZrfcsdDeliveryUpdateBody body = new ZrfcsdDeliveryUpdateBody(content);
        ZrfcsdDeliveryUpdate create = new ZrfcsdDeliveryUpdate(body);

    }
}