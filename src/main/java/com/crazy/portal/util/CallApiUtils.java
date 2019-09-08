package com.crazy.portal.util;

import com.alibaba.fastjson.JSON;
import com.crazy.portal.bean.customer.wsdl.credit.ZrfcsdcustomercreditResponse;
import com.crazy.portal.bean.customer.wsdl.credit.Zsdscredit;
import com.crazy.portal.bean.customer.wsdl.employee.EmployeeBasicDataResponseMessageSync;
import com.crazy.portal.bean.product.BaseProResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
    private static final String MDM_PRODUCT_URL = "/http/MDMProductMaster";

    private static final String ECC_DEALER_CREDIR_URL = "/cxf/CUSTOMERCREDIT";

    private static final String BI_URL = "/http/";


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
            String jsonStr = HttpClientUtils.post(ECC_DEALER_CREDIR_URL, params);
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
     * bi 接口
     * @param fromUrl portal 上传的文件位置 绝对路径 带文件名
     * @param toUrl bi 校验返回的文件位置
     * @return
     */
    public static String callBiApi(Enums.BI_FUNCTION_CODE function_code,String fromUrl, String toUrl) throws IOException{
        return callBiApi(function_code, null, fromUrl, toUrl);
    }

    public static String callBiApi(Enums.BI_FUNCTION_CODE function_code, String baseMapping, String fromUrl, String toUrl) throws IOException {
        String url;
        if(StringUtils.isNotEmpty(baseMapping)){
            url = BI_URL + baseMapping + function_code + "?sFromUrl=" + fromUrl + "&sToUrl=" + toUrl;
        }else{
            url = BI_URL + function_code + "?sFromUrl=" + fromUrl + "&sToUrl=" + toUrl;
        }
        log.info("call bi url:======================="+url);
        String jsonStr = HttpClientUtils.get(url);
        log.info("bi response:======================"+jsonStr);
        return jsonStr;
    }

    public static String callBiGetApi(Enums.BI_FUNCTION_CODE function_code, String baseMapping ,String param) throws IOException {
        if(StringUtils.isEmpty(param)){
            return HttpClientUtils.get(getBiUrl(function_code, baseMapping));
        }
        return HttpClientUtils.get(String.format("%s?%s", getBiUrl(function_code, baseMapping), param));
    }

    public static String callBiPostApi(Enums.BI_FUNCTION_CODE function_code, String baseMapping, String body) throws IOException{
        String response = HttpClientUtils.post(getBiUrl(function_code, baseMapping), body);
        return response;
    }

    private static String getBiUrl(Enums.BI_FUNCTION_CODE function_code, String baseMapping) {
        String url;
        if(StringUtils.isNotEmpty(baseMapping)){
            url = BI_URL + baseMapping + function_code;
        }else{
            url = BI_URL + function_code;
        }
        return url;
    }



    private static String C4C_EMPLOYE = "/cxf/C4C/PORTAL/GETEMPLOYEEINFO";

    public static EmployeeBasicDataResponseMessageSync querEmployee(){
        try{
            String body = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:glob=\"http://sap.com/xi/SAPGlobal20/Global\">" +
                    "   <soap:Header/>" +
                    "   <soap:Body>" +
                    "      <glob:EmployeeBasicDataByIdentificationQuery_sync>" +
                    "         <!--Optional:-->" +
                    "         <EmployeeBasicDataSelectionByIdentification>" +
                    "            <!--Zero or more repetitions:-->" +
                    "            <SelectionByEmployeeID>" +
                    "               <!--Optional:-->" +
                    "               <InclusionExclusionCode>I</InclusionExclusionCode>" +
                    "               <!--Optional:-->" +
                    "               <IntervalBoundaryTypeCode>9</IntervalBoundaryTypeCode>" +
                    "               <!--Optional:-->" +
                    "               <LowerBoundaryEmployeeID>1</LowerBoundaryEmployeeID>" +
                    "               </SelectionByEmployeeID>" +
                    "         </EmployeeBasicDataSelectionByIdentification>" +
                    "      </glob:EmployeeBasicDataByIdentificationQuery_sync>" +
                    "   </soap:Body>" +
                    "</soap:Envelope>";
            String jsonStr = HttpClientUtils.post(C4C_EMPLOYE, body);
            return JaxbXmlUtil.convertSoapXmlToJavaBean2(jsonStr, EmployeeBasicDataResponseMessageSync.class);
        }catch (Exception e){
            log.info("调用C4C人员同步接口异常！",e);
        }
        return null;
    }

    private static String C4C_ORGNATION = "/cxf/C4C/PORTAL/GETORGANISATIONALUNIT";

    public static void queryOrganisation(){
        try{
            String body = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:glob=\"http://sap.com/xi/SAPGlobal20/Global\">" +
                    "   <soap:Header/>" +
                    "    <soap:Body>" +
                    "      <glob:OrganisationalUnitByIDQuery_Sync>" +
                    "         <OrganisationalUnitSelectionByID>" +
                    "            <Identifier>" +
                    "               <InclusionExclusionCode>I</InclusionExclusionCode>" +
                    "               <IntervalBoundaryTypeCode>1</IntervalBoundaryTypeCode>" +
                    "               <LowerBoundaryIdentifier>*</LowerBoundaryIdentifier>" +
                    "            </Identifier>" +
                    "         </OrganisationalUnitSelectionByID>" +
                    "         <ProcessingConditions>" +
                    "            <QueryHitsUnlimitedIndicator>true</QueryHitsUnlimitedIndicator>" +
                    "         </ProcessingConditions>" +
                    "      </glob:OrganisationalUnitByIDQuery_Sync>" +
                    "   </soap:Body>" +
                    "</soap:Envelope>";
            String jsonStr = HttpClientUtils.post(C4C_ORGNATION, body);
            //OrganisationalUnitByElementsResponseSync response = JaxbXmlUtil.convertSoapXmlToJavaBean2(jsonStr, OrganisationalUnitByElementsResponseSync.class);
            System.out.println("1");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
