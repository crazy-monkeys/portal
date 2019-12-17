package com.crazy.portal.service.order;

import com.alibaba.fastjson.JSON;
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
import com.crazy.portal.entity.order.PoAdditionalOrderReq;
import com.crazy.portal.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.crazy.portal.util.Enums.BI_FUNCTION_CODE.DELETE_INVENTORY_CASE;

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

    @Value("${ecc.api.price-simulate-url}")
    private String PRICE_SIMULATE_URL;

    @Value("${ecc.api.order-change-url}")
    private String ORDER_CHANGE_URL;

    @Value("${ecc.api.order-create-url}")
    private String ORDER_CREATE_URL;

    @Value("${ecc.api.save-additional-order}")
    private String SAVE_ADDITIONAL_ORDER_URL;

    @Value("${ecc.api.delete-additional-order}")
    private String DELETE_ADDITIONAL_ORDER_URL;

    /**
     * 获取代理费率
     * @param ikunnr
     * @return
     */
    public BigDecimal getCustomerRate(String ikunnr) throws Exception{
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

         /*ZrfcsdsalesordercreateResponse zrfcsdsalesordercreateResponse =
                JaxbXmlUtil.convertSoapXmlToJavaBean(response, ZrfcsdsalesordercreateResponse.class);

        this.checkResultMessage(zrfcsdsalesordercreateResponse);*/
        String orate = response.substring(response.indexOf("<Orate>")+7,response.indexOf("</InternalID>"));
        if(StringUtil.isNotEmpty(orate)){
            return new BigDecimal(orate);
        }else{
            return BigDecimal.ZERO;
        }
    }

    /**
     * 创建销售单
     * @param order
     * @return
     */
    public ZrfcsdsalesordercreateResponse createSalesOrder(Zrfcsdsalesordercreate order) throws Exception{
        String url = String.format("%s%s",ECC_API_URL,ORDER_CREATE_URL);
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

        String resultMessage = null;
        //如果对方返回失败
        if(resultType.equals("1")){
            Method resultMethod = esHeader.getClass().getMethod("getResultmessage");
            resultMessage = String.valueOf(resultMethod.invoke(esHeader,null));
        }

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
        String url = String.format("%s%s",ECC_API_URL,ORDER_CHANGE_URL);
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
        String url = String.format("%s%s",ECC_API_URL,PRICE_SIMULATE_URL);
        String requestXml = JaxbXmlUtil.convertToXml(priceSimulate);
        log.info("url->"+url+"request - >" + requestXml);
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
        return zrfcsddeliverycreateResponse;

    }

    public ZrfcsddeliverychangeResponse deliveryUpdate(ZrfcsdDeliveryUpdate update){
        String url = String.format("%s%s",ECC_API_URL,"/cxf/PORTAL/ECC/CHANGEDELIVERY");
        try {
            String requestXml = JaxbXmlUtil.convertToXml(update);
            log.info("request URL- >" + url);
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

    /**
     *
     * @param reqs
     * @return
     */
    public String savePOAdditionalOrderFromCRM(List<PoAdditionalOrderReq> reqs)throws Exception{
        String url = String.format("%s%s",ECC_API_URL,SAVE_ADDITIONAL_ORDER_URL);
        Map<String,String> map = new HashMap<>();
        map.put("sJson",JSON.toJSONString(reqs));
        String body = JSON.toJSONString(map);
        log.info("url -> {}, request -> {}",url,body);
        String response = HttpClientUtils.post(url, body);
        log.info("response - >" + response);
        return response;

    }

    /**
     * 删除
     * @param ids
     * @return
     */
    public String deletePOAdditionalOrder(String ids)throws Exception{
        String url = String.format("%s%s",ECC_API_URL,DELETE_ADDITIONAL_ORDER_URL);
        url = url + "?sIDList=" + ids;
        log.info("url -> {}",url);
        String response = HttpClientUtils.get(url);
        log.info("response - >" + response);
        return response;
    }



}
