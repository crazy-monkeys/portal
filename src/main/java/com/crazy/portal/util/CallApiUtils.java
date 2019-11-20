package com.crazy.portal.util;

import com.alibaba.fastjson.JSON;
import com.crazy.portal.bean.customer.wsdl.credit.ZrfcsdcustomercreditResponse;
import com.crazy.portal.bean.customer.wsdl.credit.Zsdscredit;
import com.crazy.portal.bean.customer.wsdl.customer.detail.CustomerDetailCreate;
import com.crazy.portal.bean.customer.wsdl.customer.info.CustomerInfoCreate;
import com.crazy.portal.bean.customer.wsdl.customer.ws.detail.BOExtendCustomerUpdateConfirmationMessageSync;
import com.crazy.portal.bean.customer.wsdl.employee.EmployeeBasicDataResponseMessageSync;
import com.crazy.portal.bean.customer.wsdl.orgnation.OrganisationalUnitByIDResponseMessageSync;
import com.crazy.portal.bean.customer.wsdl.visits.VisitCreate;
import com.crazy.portal.bean.customer.wsdl.visits1.AppointmentActivityMaintainConfirmationBundleMessageSyncV1;
import com.crazy.portal.bean.product.BaseProResponseVO;
import com.crazy.portal.config.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.crazy.portal.util.Enums.BI_FUNCTION_CODE.DELETE_INVENTORY_CASE;

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
        } else if (function_code.toString().equals(Enums.BI_FUNCTION_CODE.UPDATE_SALES_IMPORT_FILE.toString())){
            url = ECC_API_URL + BI_URL+"PORTAL/BI/"+ function_code + "?sFromUrl=" + fromUrl + "&sToUrl=" + toUrl;
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
        log.info("call bi url:=======================" + String.format("%s?%s", getBiUrl(function_code, baseMapping), body));
        String response = HttpClientUtils.post(getBiUrl(function_code, baseMapping), body);
        log.info("bi response:======================" + response);
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
            log.info("========"+JSON.toJSONString(create));
            String requestXml = JaxbXmlUtil.convertToXml(create).replace("</StartDateTime timeZoneCode=\"CET\">", "</StartDateTime>").replace("</EndDateTime timeZoneCode=\"CET\">", "</EndDateTime>");
            String response = HttpClientUtils.post(url, requestXml);
            return JaxbXmlUtil.convertSoapXmlToJavaBean2(response, AppointmentActivityMaintainConfirmationBundleMessageSyncV1.class);
        } catch (Exception e) {
            log.error("", e);
            throw new BusinessException("拜访记录上传失败");
        }
    }

    private static String C4C_CUSTOMER_INFO = "/cxf/PORTAL/C4C/CREATECUSTOMERMASTER";

    public static Map<String,String> callC4cCustomerInfo(CustomerInfoCreate create) {
        String url = String.format("%s%s", ECC_API_URL, C4C_CUSTOMER_INFO);
        try {
            String requestXml = JaxbXmlUtil.convertToXml(create).replace("</y4r:CorporateAssets currencyCode=\"CNY\">", "</y4r:CorporateAssets>");
            log.info(requestXml);
            String response = HttpClientUtils.post(url, requestXml);
            log.info(response);
            Map<String,String> responseMap = new HashMap<>();
            if(response.indexOf("<InternalID>")>0 && response.indexOf("<ExternalID>")>0){
                responseMap.put("inCode",response.substring(response.indexOf("<InternalID>")+12,response.indexOf("</InternalID>")));
                responseMap.put("outCode",response.substring(response.indexOf("<ExternalID>")+12,response.indexOf("</ExternalID>")));
            }else{
                throw new BusinessException(ErrorCodes.BusinessEnum.CUSTOMER_IS_SYNC_ERROR);
            }
            return responseMap;
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

    public static void main(String[] args) {
        String str = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                "    <soap:Header/>\n" +
                "    <soap:Body>\n" +
                "        <ns1:CustomerByElementsResponse_sync xmlns:ns1=\"http://sap.com/xi/SAPGlobal20/Global\">\n" +
                "            <Customer>\n" +
                "                <ChangeStateID>                 20191111094554.5544210</ChangeStateID>\n" +
                "                <UUID>00163e73-29d9-1eea-818d-01b63ea4f2a1</UUID>\n" +
                "                <SystemAdministrativeData>\n" +
                "                    <CreationDateTime>2019-11-11T09:45:54.554421Z</CreationDateTime>\n" +
                "                    <CreationIdentityUUID>00163e73-2961-1ee9-abbb-75417d4c7631</CreationIdentityUUID>\n" +
                "                    <LastChangeDateTime>2019-11-11T09:45:54.554421Z</LastChangeDateTime>\n" +
                "                    <LastChangeIdentityUUID>00163e73-2961-1ee9-abbb-75417d4c7631</LastChangeIdentityUUID>\n" +
                "                </SystemAdministrativeData>\n" +
                "                <CategoryCode>2</CategoryCode>\n" +
                "                <CustomerIndicator>true</CustomerIndicator>\n" +
                "                <LifeCycleStatusCode>2</LifeCycleStatusCode>\n" +
                "                <Organisation>\n" +
                "                    <FirstLineName>test201911110002</FirstLineName>\n" +
                "                </Organisation>\n" +
                "                <LegalCompetenceIndicator>true</LegalCompetenceIndicator>\n" +
                "                <AddressInformation>\n" +
                "                    <UUID>00163e73-29d9-1eea-818d-01b63ea5f2a1</UUID>\n" +
                "                    <CurrentAddressSnapshotUUID>00163e73-29d9-1eea-818d-01b63eb892a1</CurrentAddressSnapshotUUID>\n" +
                "                    <AddressUsage>\n" +
                "                        <AddressUsageCode>XXDEFAULT</AddressUsageCode>\n" +
                "                    </AddressUsage>\n" +
                "                    <Address>\n" +
                "                        <PostalAddress>\n" +
                "                            <CountryCode>CN</CountryCode>\n" +
                "                            <CityName>10</CityName>\n" +
                "                            <StreetName>1</StreetName>\n" +
                "                            <TimeZoneCode>UTC+8</TimeZoneCode>\n" +
                "                        </PostalAddress>\n" +
                "                        <Telephone>\n" +
                "                            <FormattedNumberDescription>+86 15200000000</FormattedNumberDescription>\n" +
                "                        </Telephone>\n" +
                "                        <FormattedAddress>\n" +
                "                            <FormattedAddressDescription>test201911110002 / 1 / 10 / CN</FormattedAddressDescription>\n" +
                "                            <FormattedPostalAddressDescription>1 / 10 / CN</FormattedPostalAddressDescription>\n" +
                "                            <FormattedAddress>\n" +
                "                                <FirstLineDescription>test201911110002</FirstLineDescription>\n" +
                "                                <SecondLineDescription>1</SecondLineDescription>\n" +
                "                                <ThirdLineDescription>10</ThirdLineDescription>\n" +
                "                                <FourthLineDescription>China</FourthLineDescription>\n" +
                "                            </FormattedAddress>\n" +
                "                            <FormattedPostalAddress>\n" +
                "                                <FirstLineDescription>1</FirstLineDescription>\n" +
                "                                <SecondLineDescription>10</SecondLineDescription>\n" +
                "                                <ThirdLineDescription>China</ThirdLineDescription>\n" +
                "                            </FormattedPostalAddress>\n" +
                "                        </FormattedAddress>\n" +
                "                    </Address>\n" +
                "                </AddressInformation>\n" +
                "                <Relationship>\n" +
                "                    <RelationshipBusinessPartnerUUID>00163e73-29a9-1ed9-bb99-0ddddb9a7690</RelationshipBusinessPartnerUUID>\n" +
                "                    <RelationshipBusinessPartnerInternalID>300137</RelationshipBusinessPartnerInternalID>\n" +
                "                    <RoleCode>Z004-1</RoleCode>\n" +
                "                    <DefaultIndicator>true</DefaultIndicator>\n" +
                "                    <BusinessPartnerRelationshipRootUUID>00163e73-29d9-1eea-818d-01b63ea6f2a1</BusinessPartnerRelationshipRootUUID>\n" +
                "                </Relationship>\n" +
                "                <Role>\n" +
                "                    <RoleCode>CRM000</RoleCode>\n" +
                "                </Role>\n" +
                "                <RoleDescription>Customer</RoleDescription>\n" +
                "                <DirectResponsibility>\n" +
                "                    <PartyRoleCode>142</PartyRoleCode>\n" +
                "                    <EmployeeID>20742</EmployeeID>\n" +
                "                    <EmployeeUUID>00163e73-29d9-1ed9-bd90-ccb56fe6b024</EmployeeUUID>\n" +
                "                    <ValidityPeriod>\n" +
                "                        <StartDate>0001-01-01</StartDate>\n" +
                "                        <EndDate>9999-12-31</EndDate>\n" +
                "                    </ValidityPeriod>\n" +
                "                    <DefaultIndicator>true</DefaultIndicator>\n" +
                "                </DirectResponsibility>\n" +
                "                <ns2:Z_CITYNAME xmlns:ns2=\"http://0003111061-one-off.sap.com/Y4R4GVT9Y_\">10</ns2:Z_CITYNAME>\n" +
                "                <ns2:Z_STREETNAME xmlns:ns2=\"http://0003111061-one-off.sap.com/Y4R4GVT9Y_\">1</ns2:Z_STREETNAME>\n" +
                "                <ns2:Businessintroduction xmlns:ns2=\"http://0003111061-one-off.sap.com/Y4R4GVT9Y_\">11</ns2:Businessintroduction>\n" +
                "                <ns2:StaffNumber xmlns:ns2=\"http://0003111061-one-off.sap.com/Y4R4GVT9Y_\">1</ns2:StaffNumber>\n" +
                "                <ns2:DevelopersNumber xmlns:ns2=\"http://0003111061-one-off.sap.com/Y4R4GVT9Y_\">1</ns2:DevelopersNumber>\n" +
                "                <ns2:CorporateAssets xmlns:ns2=\"http://0003111061-one-off.sap.com/Y4R4GVT9Y_\" currencyCode=\"CNY\">1.0</ns2:CorporateAssets>\n" +
                "                <ns2:RegistrationDate xmlns:ns2=\"http://0003111061-one-off.sap.com/Y4R4GVT9Y_\">2019-11-05</ns2:RegistrationDate>\n" +
                "                <ns2:AccountRole xmlns:ns2=\"http://0003111061-one-off.sap.com/Y4R4GVT9Y_\">Z002</ns2:AccountRole>\n" +
                "                <ns2:Type xmlns:ns2=\"http://0003111061-one-off.sap.com/Y4R4GVT9Y_\">A03</ns2:Type>\n" +
                "                <ns2:IsLicenseAccount xmlns:ns2=\"http://0003111061-one-off.sap.com/Y4R4GVT9Y_\">Y</ns2:IsLicenseAccount>\n" +
                "            </Customer>\n" +
                "            <ProcessingConditions>\n" +
                "                <ReturnedQueryHitsNumberValue>1</ReturnedQueryHitsNumberValue>\n" +
                "                <MoreHitsAvailableIndicator>true</MoreHitsAvailableIndicator>\n" +
                "                <LastReturnedObjectID>00163E7329D91EEA818D01B63EA4F2A1</LastReturnedObjectID>\n" +
                "            </ProcessingConditions>\n" +
                "        </ns1:CustomerByElementsResponse_sync>\n" +
                "    </soap:Body>\n" +
                "</soap:Envelope>";

        System.out.println(str.indexOf("<InternalID>"));
        System.out.println(str.substring(str.indexOf("<InternalID>")+12,str.indexOf("</InternalID>")));
        System.out.println(str.substring(str.indexOf("<ExternalID>")+12,str.indexOf("</ExternalID>")));
    }

}
