package com.crazy.portal.util;

import com.alibaba.fastjson.JSON;
import com.crazy.portal.bean.customer.wsdl.credit.ZrfcsdcustomercreditResponse;
import com.crazy.portal.bean.customer.wsdl.credit.Zsdscredit;
import com.crazy.portal.bean.customer.wsdl.customer.detail.CustomerDetail;
import com.crazy.portal.bean.customer.wsdl.customer.detail.CustomerDetailCreate;
import com.crazy.portal.bean.customer.wsdl.customer.info.CustomerInfoCreate;
import com.crazy.portal.bean.customer.wsdl.customer.ws.CustomerBundleMaintainConfirmationMessageSyncV1;
import com.crazy.portal.bean.customer.wsdl.customer.ws.detail.BOExtendCustomerCreateConfirmationMessageSync;
import com.crazy.portal.bean.customer.wsdl.customer.ws.detail.BOExtendCustomerUpdateConfirmationMessageSync;
import com.crazy.portal.bean.customer.wsdl.employee.EmployeeBasicDataResponseMessageSync;
import com.crazy.portal.bean.customer.wsdl.orgnation.OrganisationalUnitByIDResponseMessageSync;
import com.crazy.portal.bean.customer.wsdl.visits1.AppointmentActivityMaintainConfirmationBundleMessageSyncV1;
import com.crazy.portal.bean.customer.wsdl.visits.VisitCreate;
import com.crazy.portal.bean.product.BaseProResponseVO;
import com.crazy.portal.config.exception.BusinessException;
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
    private static final String BI_URL = "/http/";

    public static String ECC_API_URL;

    @Value("${ecc.api.url}")
    public void setEccApiUrl(String eccApiUrl) {
        ECC_API_URL = eccApiUrl;
    }

    /**
     * 同步产品信息
     *
     * @return
     * @throws IOException
     */
    public static BaseProResponseVO callProductApi() throws IOException {
        String url = String.format("%s%s", ECC_API_URL, "/http/MDMProductMaster");
        String jsonStr = HttpClientUtils.get(url);
        return JSON.parseObject(jsonStr, BaseProResponseVO.class);
    }

    /**
     * 获取代理商授信额度
     *
     * @param dealerCode
     * @return
     */
    public static Zsdscredit callECCCreditApi(String dealerCode) {
        String url = String.format("%s%s", ECC_API_URL, "/cxf/CUSTOMERCREDIT");
        Zsdscredit zsdscredit = new Zsdscredit();
        try {
            String params = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:sap-com:document:sap:soap:functions:mc-style\">" +
                    "   <soapenv:Header/>" +
                    "   <soapenv:Body>" +
                    "      <urn:Zrfcsdcustomercredit>" +
                    "         <Ikunnr>" + dealerCode + "</Ikunnr>" +
                    "      </urn:Zrfcsdcustomercredit>" +
                    "   </soapenv:Body>" +
                    "</soapenv:Envelope>";
            String jsonStr = HttpClientUtils.post(url, params);
            ZrfcsdcustomercreditResponse response = JaxbXmlUtil.convertSoapXmlToJavaBean(jsonStr, ZrfcsdcustomercreditResponse.class);
            if (response.getOreturn().equals("0")) {
                zsdscredit = response.getOcredit();
            }
        } catch (Exception e) {
            log.error("获取ECC 代理商授信额度异常！ERROR:", e);
        }
        return zsdscredit;
    }

    /**
     * bi 接口
     *
     * @param fromUrl portal 上传的文件位置 绝对路径 带文件名
     * @param toUrl   bi 校验返回的文件位置
     * @return
     */
    public static String callBiApi(Enums.BI_FUNCTION_CODE function_code, String fromUrl, String toUrl) throws IOException {
        return callBiApi(function_code, null, fromUrl, toUrl);
    }

    public static String callBiApi(Enums.BI_FUNCTION_CODE function_code, String baseMapping, String fromUrl, String toUrl) throws IOException {
        String url;
        if (StringUtils.isNotEmpty(baseMapping)) {
            url = ECC_API_URL + BI_URL + baseMapping + function_code + "?sFromUrl=" + fromUrl + "&sToUrl=" + toUrl;
        } else {
            url = ECC_API_URL + BI_URL + function_code + "?sFromUrl=" + fromUrl + "&sToUrl=" + toUrl;
        }
        log.info("call bi url:=======================" + url);
        String jsonStr = HttpClientUtils.get(url);
        log.info("bi response:======================" + jsonStr);
        return jsonStr;
    }

    public static String callBiGetApi(Enums.BI_FUNCTION_CODE function_code, String baseMapping, String param) throws IOException {
        if (StringUtils.isEmpty(param)) {
            return HttpClientUtils.get(getBiUrl(function_code, baseMapping));
        }
        log.info("call bi url:=======================" + String.format("%s?%s", getBiUrl(function_code, baseMapping), param));
        String response = HttpClientUtils.get(String.format("%s?%s", getBiUrl(function_code, baseMapping), param));
        log.info("bi response:======================" + response);
        return response;
    }

    public static String callBiPostApi(Enums.BI_FUNCTION_CODE function_code, String baseMapping, String body) throws IOException {
        String response = HttpClientUtils.post(getBiUrl(function_code, baseMapping), body);
        return response;
    }

    private static String getBiUrl(Enums.BI_FUNCTION_CODE function_code, String baseMapping) {
        String url;
        if (StringUtils.isNotEmpty(baseMapping)) {
            url = ECC_API_URL + BI_URL + baseMapping + function_code;
        } else {
            url = ECC_API_URL + BI_URL + function_code;
        }
        return url;
    }

    private static String C4C_EMPLOYE = "/cxf/C4C/PORTAL/GETEMPLOYEEINFO";

    public static EmployeeBasicDataResponseMessageSync querEmployee() {
        try {
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
            String jsonStr = HttpClientUtils.post(ECC_API_URL.concat(C4C_EMPLOYE), body);
            return JaxbXmlUtil.convertSoapXmlToJavaBean2(jsonStr, EmployeeBasicDataResponseMessageSync.class);
        } catch (Exception e) {
            log.info("调用C4C人员同步接口异常！", e);
        }
        return null;
    }

    private static String C4C_ORGNATION = "/cxf/C4C/PORTAL/GETORGANISATIONALUNIT";

    public static OrganisationalUnitByIDResponseMessageSync queryOrganisation() {
        try {
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
            String jsonStr = HttpClientUtils.post(ECC_API_URL.concat(C4C_ORGNATION), body);
            return JaxbXmlUtil.convertSoapXmlToJavaBean2(jsonStr, OrganisationalUnitByIDResponseMessageSync.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String C4C_Visits = "/cxf/PORTAL/C4C/VISIT";

    public static AppointmentActivityMaintainConfirmationBundleMessageSyncV1 callC4cVisits(VisitCreate create) {
        String url = String.format("%s%s", ECC_API_URL, C4C_Visits);
        try {
            String requestXml = JaxbXmlUtil.convertToXml(create).replace("</StartDateTime timeZoneCode=\"CET\">", "</StartDateTime>").replace("</EndDateTime timeZoneCode=\"CET\">", "</EndDateTime>");
            String response = HttpClientUtils.post(url, requestXml);
            return JaxbXmlUtil.convertSoapXmlToJavaBean2(response, AppointmentActivityMaintainConfirmationBundleMessageSyncV1.class);
        } catch (Exception e) {
            log.error("", e);
            throw new BusinessException("拜访记录上传失败");
        }
    }

    private static String C4C_CUSTOMER_INFO = "/cxf/PORTAL/C4C/CUSTOMERMASTER";

    public static String callC4cCustomerInfo(CustomerInfoCreate create) {
        String url = String.format("%s%s", ECC_API_URL, C4C_CUSTOMER_INFO);
        try {
            String requestXml = JaxbXmlUtil.convertToXml(create).replace("</y4r:CorporateAssets currencyCode=\"CNY\">", "</y4r:CorporateAssets>");
            log.info(requestXml);
            String response = HttpClientUtils.post(url, requestXml);
            log.info(response);
            CustomerBundleMaintainConfirmationMessageSyncV1 v1 = JaxbXmlUtil.convertSoapXmlToJavaBean2(response, CustomerBundleMaintainConfirmationMessageSyncV1.class);
            if (!v1.getCustomer().isEmpty() && null != v1.getCustomer().get(0)) {
                if (v1.getCustomer().get(0).getReferenceObjectNodeSenderTechnicalID().equals("03")) {
                    log.error("customer sync c4c log :" + JSON.toJSONString(v1));
                    throw new BusinessException("客户信息同步C4C失败");
                }
            }else{
                log.error("customer sync c4c log :" + JSON.toJSONString(v1));
                 throw new BusinessException("客户信息同步C4C异常");
            }
            return v1.getCustomer().get(0).getInternalID();
        } catch (Exception e) {
            log.error("", e);
            throw new BusinessException("客户信息同步C4C异常");
        }
    }

    private static String C4C_CUSTOMER_DETAIL = "/cxf/PORTAL/C4C/CREATEEXTENDCUSTOMERMASTER";

    public static void callC4cCustomerDetail(CustomerDetailCreate create) {
        String url = String.format("%s%s", ECC_API_URL, C4C_CUSTOMER_DETAIL);
        try {
            String requestXml = JaxbXmlUtil.convertToXml(create).replace("</Revenue currencyCode=\"?\">", "</Revenue>").replace("</NetAssets currencyCode=\"?\">","</NetAssets>").replace("</TotalAssets currencyCode=\"?\">","</TotalAssets>");
            log.info(requestXml);
            String response = HttpClientUtils.post(url, requestXml);
            log.info(response);
            BOExtendCustomerUpdateConfirmationMessageSync v1 = JaxbXmlUtil.convertSoapXmlToJavaBean2(response, BOExtendCustomerUpdateConfirmationMessageSync.class);
            if(null == v1.getLog() || v1.getLog().getMaximumLogItemSeverityCode().equals("3")){
                throw new BusinessException("C4C扩展信息修改失败");
            }
        } catch (Exception e) {
            log.error("", e);
            throw new BusinessException("C4C扩展信息修改异常");
        }
    }
}
