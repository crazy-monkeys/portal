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
import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

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
    public ZrfcsdcustomercrrateResponse getCustomerRate(String ikunnr) throws Exception{
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

        String response = HttpClientUtils.post(url,requestXML);
        log.info("CUSTOMERRATE Interface return {}",response);

        ZrfcsdcustomercrrateResponse zrfcsdcustomercrrateResponse = JaxbXmlUtil.convertSoapXmlToJavaBean(response, ZrfcsdcustomercrrateResponse.class);

        this.checkResultMessage(zrfcsdcustomercrrateResponse);

        return zrfcsdcustomercrrateResponse;
    }

    /**
     * 创建销售单
     * @param order
     * @return
     */
    public ZrfcsdsalesordercreateResponse createSalesOrder(Zrfcsdsalesordercreate order) throws Exception{
        String url = String.format("%s%s",ECC_API_URL,"/cxf/PORTAL/ECC/CREATESALESORDER");
        String requestXml = JaxbXmlUtil.convertToXml(order);
        log.info("request - >" + requestXml);
        String response = HttpClientUtils.post(url,requestXml);
        log.info("response - >" + response);

        ZrfcsdsalesordercreateResponse zrfcsdsalesordercreateResponse =
                JaxbXmlUtil.convertSoapXmlToJavaBean(response, ZrfcsdsalesordercreateResponse.class);

        this.checkResultMessage(zrfcsdsalesordercreateResponse);

        return zrfcsdsalesordercreateResponse;
    }

    /**
     * 调用接口之后验证是否正确获取值
     * @param obj
     * @throws Exception
     */
    private void checkResultMessage(Object obj) throws Exception{

        BusinessUtil.notNull(obj,ErrorCodes.CommonEnum.SYSTEM_EXCEPTION);
        Method getEsHeaderMethod = obj.getClass().getMethod("getEsHeader");
        Object esHeader = getEsHeaderMethod.invoke(obj, null);

        BusinessUtil.notNull(esHeader,ErrorCodes.CommonEnum.SYSTEM_EXCEPTION);

        Method resulttypeMethod = esHeader.getClass().getMethod("getResulttype");
        String resultType = (String)resulttypeMethod.invoke(esHeader, null);

        BusinessUtil.assertTrue(resultType.equals("1"),ErrorCodes.CommonEnum.SYSTEM_EXCEPTION);

        Method resultmessage = esHeader.getClass().getMethod("getResultmessage");
        String resultMessage = String.valueOf(resultmessage.invoke(esHeader,null));

        if(StringUtil.isEmpty(resultMessage) || "null".equals(resultMessage)){
            return;
        }
        throw new BusinessException(ErrorCodes.CommonEnum.SYSTEM_EXCEPTION.getCode(), resultMessage);
    }


    /**
     * 修改销售单
     * @param order
     * @return
     */
    public ZrfcsdsalesorderchangeResponse changeSalesOrder(Zrfcsdsalesorderchange order) throws Exception{
        String url = String.format("%s%s",ECC_API_URL,"/cxf/PORTAL/ECC/CHANGE_SALES_ORDER");

        String requestXml = JaxbXmlUtil.convertToXml(order);
        log.info("request - >" + requestXml);
        String response = HttpClientUtils.post(url,requestXml);
        log.info("response - >" + response);

        ZrfcsdsalesorderchangeResponse zrfcsdsalesorderchangeResponse
                = JaxbXmlUtil.convertSoapXmlToJavaBean(response, ZrfcsdsalesorderchangeResponse.class);

        this.checkResultMessage(zrfcsdsalesorderchangeResponse);

        return zrfcsdsalesorderchangeResponse;

    }

    /**
     * 调价试算
     * @param priceSimulate
     * @return
     */
    public ZrfcsdpricesimulateResponse priceSimulate(Zrfcsdpricesimulate priceSimulate) throws Exception{
        String url = String.format("%s%s",ECC_API_URL,"/cxf/ECC/PORTAL/GETPRICESIMULATION");
        String requestXml = JaxbXmlUtil.convertToXml(priceSimulate);
        log.info("request - >" + requestXml);
        String response = HttpClientUtils.post(url,requestXml);
        log.info("response - >" + response);
        ZrfcsdpricesimulateResponse zrfcsdpricesimulateResponse =
                JaxbXmlUtil.convertSoapXmlToJavaBean(response, ZrfcsdpricesimulateResponse.class);

        this.checkResultMessage(zrfcsdpricesimulateResponse);

        return zrfcsdpricesimulateResponse;
    }

    /**
     * 交货单创建
     * @param create
     * @return
     */
    public ZrfcsddeliverycreateResponse deliveryCreate(ZrfcsdDeliveryCreate create) throws Exception{
        String url = String.format("%s%s",ECC_API_URL,"/cxf/PORTAL/ECC/CREATEDELIVERY");
        String requestXml = JaxbXmlUtil.convertToXml(create);
        log.info("request - >" + requestXml);
        String response = HttpClientUtils.post(url,requestXml);
        log.info("response - >" + response);
        ZrfcsddeliverycreateResponse zrfcsddeliverycreateResponse = JaxbXmlUtil.convertSoapXmlToJavaBean(response, ZrfcsddeliverycreateResponse.class);

        this.checkResultMessage(zrfcsddeliverycreateResponse);

        return zrfcsddeliverycreateResponse;

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
