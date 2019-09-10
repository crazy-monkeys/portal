package com.crazy.portal.service.order;

import com.crazy.portal.bean.order.wsdl.createOrder.request.Zrfcsdsalesordercreate;
import com.crazy.portal.bean.order.wsdl.createOrder.responce.ZrfcsdsalesordercreateResponse;
import com.crazy.portal.bean.order.wsdl.customerRate.responce.ZrfcsdcustomercrrateResponse;
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
public class OrderService {

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
            String response = HttpClientUtils.post(url,requestXml);
            return JaxbXmlUtil.convertSoapXmlToJavaBean(response, ZrfcsdsalesordercreateResponse.class);
        } catch (Exception e) {
            log.error("",e);
        }
        return null;
    }


}
