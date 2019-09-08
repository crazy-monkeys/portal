package com.crazy.portal.service.order;

import com.crazy.portal.bean.customer.wsdl.order.ZrfcsdcustomercrrateResponse;
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
public class EccApiService {

    @Value("${api.root}")
    private String ECC_API_URL;

    /**
     * 获取代理费率
     * @param ikunnr
     * @return
     */
    public ZrfcsdcustomercrrateResponse getCustomerRate(String ikunnr){
        String url = String.format("%s/%s",ECC_API_URL,"/cxf/CUSTOMERRATE");

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

}
