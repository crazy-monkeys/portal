package com.crazy.portal.util;

import com.alibaba.fastjson.JSON;
import com.crazy.portal.bean.customer.dealer.credit.ZrfcsdcustomercreditResponse;
import com.crazy.portal.bean.customer.dealer.credit.Zsdscredit;
import com.crazy.portal.bean.product.BaseProResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.IOException;

/**
 * @ClassName: CallApiUtils
 * @Author: God Man Qiu~
 * @Date: 2019/8/3 13:28
 */
@Slf4j
@Component
public class CallApiUtils {
    private static String MDM_PRODUCT_URL;
    @Value("${api.url.mdm-product}")
    public void setMdmProductUrl(String mdmProductUrl) {
        MDM_PRODUCT_URL = mdmProductUrl;
    }

    private static String ECC_DEALER_CREDIR_URL;
    @Value("${api.url.ecc-credit}")
    public void setEccDealerCredirUrl(String eccDealerCredirUrl) {
        ECC_DEALER_CREDIR_URL = eccDealerCredirUrl;
    }

    private static String BI_TEST_URL;
    @Value("${api.url.bi-test}")
    public void setBiTestUrl(String biTestUrl) {
        BI_TEST_URL = biTestUrl;
    }

    /**
     * 同步产品信息
     * @return
     * @throws IOException
     */
    public static BaseProResponseVO callProductApi() throws IOException{
        String jsonStr = HttpClientUtils.get(MDM_PRODUCT_URL);
        return JSON.parseObject(jsonStr, BaseProResponseVO.class);
    }

    /**
     * 获取代理商授信额度
     * @param dealerCode
     * @return
     */
    public static Zsdscredit callECCCreditApi(String dealerCode){
        Zsdscredit zsdscredit = new Zsdscredit();
        try{
            String params = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:sap-com:document:sap:soap:functions:mc-style\">" +
                    "   <soapenv:Header/>" +
                    "   <soapenv:Body>" +
                    "      <urn:Zrfcsdcustomercredit>" +
                    "         <Ikunnr>"+dealerCode+"</Ikunnr>" +
                    "      </urn:Zrfcsdcustomercredit>" +
                    "   </soapenv:Body>" +
                    "</soapenv:Envelope>";
            String jsonStr = HttpClientUtils.postHeader(ECC_DEALER_CREDIR_URL, params);
            ZrfcsdcustomercreditResponse response = JaxbXmlUtil.convertSoapXmlToJavaBean(jsonStr, ZrfcsdcustomercreditResponse.class);
            if(response.getOreturn().equals("0")){
                zsdscredit = response.getOcredit();
            }
        }catch (Exception e){
            log.error("获取ECC 代理商授信额度异常！ERROR:",e);
        }
        return zsdscredit;
    }

    /**
     * bi 测试接口
     * @param fromUrl portal 上传的文件位置 绝对路径
     * @param toUrl bi 校验返回的文件位置
     * @return
     */
    public static String BITest(String fromUrl, String toUrl)throws IOException{
        String url = BI_TEST_URL+"?sFromUrl"+fromUrl+"&sToUrl"+toUrl;
        String jsonStr = HttpClientUtils.get(url);
        return jsonStr;
    }
}
