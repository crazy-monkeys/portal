package com.crazy.portal.service.order;

import com.crazy.portal.bean.customer.wsdl.order.ZrfcsdcustomercrrateResponse;
import com.crazy.portal.bean.customer.wsdl.order.ZrfcsddeliverylistResponse;
import com.crazy.portal.bean.customer.wsdl.order.ZrfcsdpricesimulateResponse;
import com.crazy.portal.util.HttpClientUtils;
import com.crazy.portal.util.JaxbXmlUtil;
import lombok.extern.slf4j.Slf4j;
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

//    @Value("${ecc.api.url}")
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

    /**
     * 获取交货单
     * @return
     */
    public ZrfcsddeliverylistResponse getDeliveryList(){
        return null;
    }

    /**
     * 调价试算
     * @return
     */
    public ZrfcsdpricesimulateResponse calculatePrice(){
        String requestXml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:urn=\"urn:sap-com:document:sap:soap:functions:mc-style\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <urn:Zrfcsdpricesimulate>\n" +
                "         <!--Optional:-->\n" +
                "         <EtItems>\n" +
                "            <!--Zero or more repetitions:-->\n" +
                "            <item>\n" +
                "               <Sequenceno></Sequenceno>\n" +
                "               <Itemno></Itemno>\n" +
                "               <Productid></Productid>\n" +
                "               <Price></Price>\n" +
                "               <Netprice></Netprice>\n" +
                "               <Currency></Currency>\n" +
                "               <Itemcategory></Itemcategory>\n" +
                "               <Refitemno></Refitemno>\n" +
                "               <Refitemproductid></Refitemproductid>\n" +
                "               <CondUnit></CondUnit>\n" +
                "            </item>\n" +
                "         </EtItems>\n" +
                "         <!--Optional:-->\n" +
                "         <IsHeader>\n" +
                "            <Portalorderid>1</Portalorderid>\n" +
                "            <Ordertype>ZOR</Ordertype>\n" +
                "            <Salesorg>7100</Salesorg>\n" +
                "            <Channel>10</Channel>\n" +
                "            <Division>00</Division>\n" +
                "            <Salesoffice>0003</Salesoffice>\n" +
                "            <Salesgroup>031</Salesgroup>\n" +
                "            <Soldto>300184</Soldto>\n" +
                "            <Sendto>300184</Sendto>\n" +
                "            <Pricedate>20190822</Pricedate>\n" +
                "         </IsHeader>\n" +
                "         <!--Optional:-->\n" +
                "         <ItItems>\n" +
                "            <!--Zero or more repetitions:-->\n" +
                "            <item>\n" +
                "               <Sequenceno>1</Sequenceno>\n" +
                "               <Productid>18000000017</Productid>\n" +
                "               <Orderquantity>10</Orderquantity>\n" +
                "               <Platform>1</Platform>\n" +
                "            </item>\n" +
                "         </ItItems>\n" +
                "      </urn:Zrfcsdpricesimulate>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>\n";

        return null;
    }

}
