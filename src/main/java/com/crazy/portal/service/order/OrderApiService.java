package com.crazy.portal.service.order;

import com.crazy.portal.bean.order.wsdl.change.Zrfcsdsalesorderchange;
import com.crazy.portal.bean.order.wsdl.change.ZrfcsdsalesorderchangeResponse;
import com.crazy.portal.bean.order.wsdl.create.Zrfcsdsalesordercreate;
import com.crazy.portal.bean.order.wsdl.create.ZrfcsdsalesordercreateResponse;
import com.crazy.portal.bean.order.wsdl.delivery.Zrfcsddeliverylist;
import com.crazy.portal.bean.order.wsdl.delivery.ZrfcsddeliverylistResponse;
import com.crazy.portal.bean.order.wsdl.delivery.create.ZrfcsdDeliveryCreate;
import com.crazy.portal.bean.order.wsdl.delivery.create.ZrfcsddeliverycreateResponse;
import com.crazy.portal.bean.order.wsdl.delivery.update.ZrfcsdDeliveryUpdate;
import com.crazy.portal.bean.order.wsdl.delivery.update.ZrfcsddeliverychangeResponse;
import com.crazy.portal.bean.order.wsdl.price.Zrfcsdpricesimulate;
import com.crazy.portal.bean.order.wsdl.price.ZrfcsdpricesimulateResponse;
import com.crazy.portal.bean.order.wsdl.rate.ZrfcsdcustomercrrateResponse;
import com.crazy.portal.util.HttpClientUtils;
import com.crazy.portal.util.JaxbXmlUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 10:53 2019-09-08
 * @Modified by:
 */
@Service
@Slf4j
public class OrderApiService {

    @Value("${ecc.api.url}")
    private String ECC_API_URL;

    /**
     * 获取代理费率
     * @param ikunnr
     * @return
     */
    public ZrfcsdcustomercrrateResponse getCustomerRate(String ikunnr){
        String url = String.format("%s%s",ECC_API_URL,"/cxf/CUSTOMERRATE");

        String requestXML = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:urn=\"urn:sap-com:document:sap:soap:functions:mc-style\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <urn:Zrfcsdcustomercrrate>\n" +
                "         <Ikunnr>"+ikunnr+"</Ikunnr>\n" +
                "      </urn:Zrfcsdcustomercrrate>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        try {
            String response = HttpClientUtils.post(url,requestXML);
            log.info("CUSTOMERRATE Interface return {}",response);
            return JaxbXmlUtil.convertSoapXmlToJavaBean(response, ZrfcsdcustomercrrateResponse.class);
        } catch (Exception e) {
            log.error("",e);
        }
        return null;
    }

    /**
     * 创建销售单
     * @param order
     * @return
     */
    public ZrfcsdsalesordercreateResponse createSalesOrder(Zrfcsdsalesordercreate order){
        String url = String.format("%s%s",ECC_API_URL,"/cxf/PORTAL/ECC/CREATESALESORDER");
        try {
            String requestXml = JaxbXmlUtil.convertToXml(order);
            log.info("request - >" + requestXml);
            String response = HttpClientUtils.post(url,requestXml);
            log.info("response - >" + response);
            return JaxbXmlUtil.convertSoapXmlToJavaBean(response, ZrfcsdsalesordercreateResponse.class);
        } catch (Exception e) {
            log.error("",e);
        }
        return null;
    }

    /**
     * 修改销售单
     * @param order
     * @return
     */
    public ZrfcsdsalesorderchangeResponse changeSalesOrder(Zrfcsdsalesorderchange order){
        String url = String.format("%s%s",ECC_API_URL,"/cxf/PORTAL/ECC/CHANGE_SALES_ORDER");

        try {
            String requestXml = JaxbXmlUtil.convertToXml(order);
            log.info("request - >" + requestXml);
            String response = HttpClientUtils.post(url,requestXml);
            log.info("response - >" + response);
            return JaxbXmlUtil.convertSoapXmlToJavaBean(response, ZrfcsdsalesorderchangeResponse.class);
        } catch (Exception e) {
            log.error("",e);
        }
        return null;
    }

    /**
     * 调价试算
     * @param priceSimulate
     * @return
     */
    public ZrfcsdpricesimulateResponse priceSimulate(Zrfcsdpricesimulate priceSimulate){
        String url = String.format("%s%s",ECC_API_URL,"/cxf/ECC/PORTAL/GETPRICESIMULATION");

        try {
            String requestXml = JaxbXmlUtil.convertToXml(priceSimulate);
            log.info("request - >" + requestXml);
            String response = HttpClientUtils.post(url,requestXml);
            log.info("response - >" + response);
            return JaxbXmlUtil.convertSoapXmlToJavaBean(response, ZrfcsdpricesimulateResponse.class);
        } catch (Exception e) {
            log.error("",e);
        }
        return null;
    }

    public ZrfcsddeliverycreateResponse deliveryCreate(ZrfcsdDeliveryCreate create){
        String url = String.format("%s%s",ECC_API_URL,"/cxf/PORTAL/ECC/CREATEDELIVERY");
        try {
            String requestXml = JaxbXmlUtil.convertToXml(create);
            log.info("request - >" + requestXml);
            String response = HttpClientUtils.post(url,requestXml);
            log.info("response - >" + response);
            return JaxbXmlUtil.convertSoapXmlToJavaBean(response, ZrfcsddeliverycreateResponse.class);
        } catch (Exception e) {
            log.error("",e);
        }
        return null;
    }

    public ZrfcsddeliverychangeResponse deliveryUpdate(ZrfcsdDeliveryUpdate update){
        String url = String.format("%s%s",ECC_API_URL,"/cxf/PORTAL/ECC/CHANGEDELIVERY");
        try {
            String requestXml = JaxbXmlUtil.convertToXml(update);
            log.info("request - >" + requestXml);
            String response = HttpClientUtils.post(url,requestXml);
            log.info("response - >" + response);
            return JaxbXmlUtil.convertSoapXmlToJavaBean(response, ZrfcsddeliverychangeResponse.class);
        } catch (Exception e) {
            log.error("",e);
        }
        return null;
    }

    /**
     * 交货单查询
     * @return
     */
    public ZrfcsddeliverylistResponse deliveryList(Zrfcsddeliverylist zrfcsddeliverylist){
        String url = String.format("%s%s",ECC_API_URL,"/cxf/ECC/PORTAL/GET_DELIVERY_LIST");

        try {
            String requestXml = JaxbXmlUtil.convertToXml(zrfcsddeliverylist);
            log.info("request - >" + requestXml);
            String response = HttpClientUtils.post(url,requestXml);
            log.info("response - >" + response);
            return JaxbXmlUtil.convertSoapXmlToJavaBean(response, ZrfcsddeliverylistResponse.class);
        } catch (Exception e) {
            log.error("",e);
        }
        return null;
    }



}
