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

    private static String BI_URL;
    @Value("${api.url.bi-test}")
    public void setBiUrl(String biUrl) {
        BI_URL = biUrl;
    }

    private static String BPM_IDR_APPROVAL_URL;
    @Value("${api.url.bpm.idr.approval}")
    private void setBpmIdrApprovalUrl(String bpmIdrApprovalUrl){ BPM_IDR_APPROVAL_URL = bpmIdrApprovalUrl;}

    private static String REBATE_PRICE_ROLE_URL;
    @Value("${api.url.rebate.price.role}")
    private void setRebatePriceRoleUrl(String rebatePriceRoleUrl){ REBATE_PRICE_ROLE_URL = rebatePriceRoleUrl;}

    private static String REBATE_SALES_DETAILS_URL;
    @Value("${api.url.rebate.sales.details}")
    public void setRebateSalesDetailsUrl(String rebateSalesDetailsUrl){ REBATE_SALES_DETAILS_URL = rebateSalesDetailsUrl;}

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

    /**
     * 提交保差退审批到BPM
     * @param requestBody 请求体
     * @return
     */
    public static String portalSubmitApprovalToBPM(String requestBody) throws IOException{
        return HttpClientUtils.postHeader(BPM_IDR_APPROVAL_URL, requestBody);
    }

    /**
     * 排除JSON字符串中的"和\字符
     * @param message
     * @return
     */
    public static String getJsonMessage(String message){
        if(StringUtil.isBlank(message)){
            return message;
        }
        String firstChar = message.substring(0, 1);
        String lastChar = message.substring(message.length() - 1, message.length());
        if(firstChar.equals("\"") && lastChar.equals("\"")){
            message = message.substring(1, message.length() - 1);
        }
        if(message.indexOf("\\") != -1){
            message = message.replaceAll("\\\\", "");
        }
        return message;
    }

    /**
     * 同步rebate金额信息
     * @param startDate 起始年月
     * @param endDate 结束年月
     * @return
     * @throws IOException
     */
    public static String syncRebatePriceRoleData(String startDate, String endDate) throws IOException{
        String url = REBATE_PRICE_ROLE_URL.concat("?sStartYearMonth=").concat(startDate).concat("&sEndYearMonth=").concat(endDate);
        return getJsonMessage(HttpClientUtils.get(url));
    }

    /**
     * 同步rebate商品明细
     * @param startDate 起始年月
     * @param endDate 结束年月
     * @return
     * @throws IOException
     */
    public static String syncRebatePriceSalesDetails(String startDate, String endDate) throws IOException{
        String url = REBATE_SALES_DETAILS_URL.concat("?sStartYearMonth=").concat(startDate).concat("&sEndYearMonth=").concat(endDate);
        return getJsonMessage(HttpClientUtils.get(url));
    }

    private static String C4C_EMPLOYE;
    @Value("${api.url.c4c-employe}")
    public void setC4cEmploye(String c4cEmploye) {
        C4C_EMPLOYE = c4cEmploye;
    }

    public static EmployeeBasicDataResponseMessageSync querEmployee(){
        try{
            String params = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:glob=\"http://sap.com/xi/SAPGlobal20/Global\">" +
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
            String jsonStr = HttpClientUtils.postHeader(C4C_EMPLOYE, params);
            return JaxbXmlUtil.convertSoapXmlToJavaBean2(jsonStr, EmployeeBasicDataResponseMessageSync.class);
        }catch (Exception e){
            log.info("调用C4C人员同步接口异常！",e);
        }
        return null;
    }

    private static String C4C_ORGNATION;

    @Value("${api.url.c4c-orgnation}")
    public void setC4cOrgnation(String c4cOrgnation) {
        C4C_ORGNATION = c4cOrgnation;
    }

    public static void queryOrganisation(){
        try{
            String params = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:glob=\"http://sap.com/xi/SAPGlobal20/Global\">" +
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
            String jsonStr = HttpClientUtils.postHeader(C4C_ORGNATION, params);
            //OrganisationalUnitByElementsResponseSync response = JaxbXmlUtil.convertSoapXmlToJavaBean2(jsonStr, OrganisationalUnitByElementsResponseSync.class);
            System.out.println("1");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
