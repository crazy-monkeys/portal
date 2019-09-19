
package com.crazy.portal.bean.customer.wsdl.customer.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>CustomerMaintainRequestBundleContactPerson complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CustomerMaintainRequestBundleContactPerson"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ObjectNodeSenderTechnicalID" type="{http://sap.com/xi/AP/Common/GDT}ObjectNodePartyTechnicalID" minOccurs="0"/&gt;
 *         &lt;element name="BusinessPartnerContactUUID" type="{http://sap.com/xi/AP/Common/GDT}UUID" minOccurs="0"/&gt;
 *         &lt;element name="BusinessPartnerContactInternalID" type="{http://sap.com/xi/AP/Common/GDT}BusinessPartnerInternalID" minOccurs="0"/&gt;
 *         &lt;element name="DefaultContactPersonIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *         &lt;element name="FormOfAddressCode" type="{http://sap.com/xi/AP/Common/GDT}FormOfAddressCode" minOccurs="0"/&gt;
 *         &lt;element name="AcademicTitleCode" type="{http://sap.com/xi/AP/Common/GDT}AcademicTitleCode" minOccurs="0"/&gt;
 *         &lt;element name="GivenName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_MEDIUM_Name" minOccurs="0"/&gt;
 *         &lt;element name="MiddleName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_MEDIUM_Name" minOccurs="0"/&gt;
 *         &lt;element name="FamilyName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_MEDIUM_Name" minOccurs="0"/&gt;
 *         &lt;element name="BirthName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_MEDIUM_Name" minOccurs="0"/&gt;
 *         &lt;element name="NickName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_MEDIUM_Name" minOccurs="0"/&gt;
 *         &lt;element name="InitialsName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_SHORT_Name" minOccurs="0"/&gt;
 *         &lt;element name="NamePrefixCode" type="{http://sap.com/xi/AP/Common/GDT}FamilyNamePrefixCode" minOccurs="0"/&gt;
 *         &lt;element name="AdditionalAcademicTitleCode" type="{http://sap.com/xi/AP/Common/GDT}AcademicTitleCode" minOccurs="0"/&gt;
 *         &lt;element name="GenderCode" type="{http://sap.com/xi/AP/Common/GDT}GenderCode" minOccurs="0"/&gt;
 *         &lt;element name="BirthDate" type="{http://sap.com/xi/AP/Common/GDT}Date" minOccurs="0"/&gt;
 *         &lt;element name="NonVerbalCommunicationLanguageCode" type="{http://sap.com/xi/BASIS/Global}LanguageCode" minOccurs="0"/&gt;
 *         &lt;element name="OccupationCode" type="{http://sap.com/xi/AP/Common/GDT}OccupationCode" minOccurs="0"/&gt;
 *         &lt;element name="ContactAllowedCode" type="{http://sap.com/xi/AP/Common/GDT}ContactAllowedCode" minOccurs="0"/&gt;
 *         &lt;element name="BusinessPartnerFunctionTypeCode" type="{http://sap.com/xi/AP/Common/GDT}BusinessPartnerFunctionTypeCode" minOccurs="0"/&gt;
 *         &lt;element name="BusinessPartnerFunctionalAreaCode" type="{http://sap.com/xi/AP/Common/GDT}BusinessPartnerFunctionalAreaCode" minOccurs="0"/&gt;
 *         &lt;element name="EngagementScoreNumberValue" type="{http://sap.com/xi/AP/Common/GDT}EngagementScoreNumberValue" minOccurs="0"/&gt;
 *         &lt;element name="VIPReasonCode" type="{http://sap.com/xi/AP/Common/GDT}VIPReasonCode" minOccurs="0"/&gt;
 *         &lt;element name="WorkplaceBusinessAddressUUID" type="{http://sap.com/xi/AP/Common/GDT}UUID" minOccurs="0"/&gt;
 *         &lt;element name="WorkplacePreferredCommunicationMediumTypeCode" type="{http://sap.com/xi/AP/Common/GDT}CommunicationMediumTypeCode" minOccurs="0"/&gt;
 *         &lt;element name="WorkplaceEmail" type="{http://sap.com/xi/A1S/Global}CustomerMaintainRequestBundleEmail" minOccurs="0"/&gt;
 *         &lt;element name="WorkplaceFacsimileFormattedNumberDescription" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_NUMBER_SHORT_Description" minOccurs="0"/&gt;
 *         &lt;element name="WorkplaceTelephone" type="{http://sap.com/xi/A1S/Global}CustomerMaintainRequestBundleWorkplaceTelephone" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="WorkplaceWebURI" type="{http://sap.com/xi/AP/Common/GDT}WebURI" minOccurs="0"/&gt;
 *         &lt;element name="WorkplaceFunctionalTitleName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_MEDIUM_Name" minOccurs="0"/&gt;
 *         &lt;element name="WorkplaceDepartmentName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_MEDIUM_Name" minOccurs="0"/&gt;
 *         &lt;element name="WorkplaceBuildingID" type="{http://sap.com/xi/AP/Common/GDT}BuildingID" minOccurs="0"/&gt;
 *         &lt;element name="WorkplaceFloorID" type="{http://sap.com/xi/AP/Common/GDT}FloorID" minOccurs="0"/&gt;
 *         &lt;element name="WorkplaceRoomID" type="{http://sap.com/xi/AP/Common/GDT}RoomID" minOccurs="0"/&gt;
 *         &lt;element name="AddressInformation" type="{http://sap.com/xi/A1S/Global}CustomerMaintainRequestBundleContactPersonAddressInformation" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="LifeCycleStatusCode" type="{http://sap.com/xi/AP/Common/GDT}PartyLifeCycleStatusCode" minOccurs="0"/&gt;
 *         &lt;element name="MarketingPermission" type="{http://sap.com/xi/A1S/Global}CustomerMaintainRequestBundleMarketingPermission" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="MaritalStatusCode" type="{http://sap.com/xi/AP/Common/GDT}MaritalStatusCode" minOccurs="0"/&gt;
 *         &lt;element name="Text" type="{http://sap.com/xi/A1S/Global}CustomerMaintainRequestBundleText" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;group ref="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}Ext00163E732DE21EE9AE9DAE9F28EDD766"/&gt;
 *         &lt;group ref="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}Ext00163E732DE21EE9AE9E0835D25A9987"/&gt;
 *         &lt;group ref="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}Ext00163E732DE21EE9AE9E1E732671F9F5"/&gt;
 *         &lt;group ref="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}Ext00163E732DE21EE9AE9E215E29DEBA42"/&gt;
 *         &lt;group ref="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}Ext00163E732DE21EE9AE9E242005107A44"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="actionCode" type="{http://sap.com/xi/AP/Common/GDT}ActionCode" /&gt;
 *       &lt;attribute name="workplaceTelephoneListCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" /&gt;
 *       &lt;attribute name="addressInformationListCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" /&gt;
 *       &lt;attribute name="MarketingPermissionCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" /&gt;
 *       &lt;attribute name="textListCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerMaintainRequestBundleContactPerson", namespace = "http://sap.com/xi/A1S/Global", propOrder = {
    "objectNodeSenderTechnicalID",
    "businessPartnerContactUUID",
    "businessPartnerContactInternalID",
    "defaultContactPersonIndicator",
    "formOfAddressCode",
    "academicTitleCode",
    "givenName",
    "middleName",
    "familyName",
    "birthName",
    "nickName",
    "initialsName",
    "namePrefixCode",
    "additionalAcademicTitleCode",
    "genderCode",
    "birthDate",
    "nonVerbalCommunicationLanguageCode",
    "occupationCode",
    "contactAllowedCode",
    "businessPartnerFunctionTypeCode",
    "businessPartnerFunctionalAreaCode",
    "engagementScoreNumberValue",
    "vipReasonCode",
    "workplaceBusinessAddressUUID",
    "workplacePreferredCommunicationMediumTypeCode",
    "workplaceEmail",
    "workplaceFacsimileFormattedNumberDescription",
    "workplaceTelephone",
    "workplaceWebURI",
    "workplaceFunctionalTitleName",
    "workplaceDepartmentName",
    "workplaceBuildingID",
    "workplaceFloorID",
    "workplaceRoomID",
    "addressInformation",
    "lifeCycleStatusCode",
    "marketingPermission",
    "maritalStatusCode",
    "text",
    "accountRole",
    "abbreviation",
    "type",
    "isInWhiteList",
    "isLicenseAccount"
})
public class CustomerMaintainRequestBundleContactPerson {

    @XmlElement(name = "ObjectNodeSenderTechnicalID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String objectNodeSenderTechnicalID;
    @XmlElement(name = "BusinessPartnerContactUUID")
    protected UUID businessPartnerContactUUID;
    @XmlElement(name = "BusinessPartnerContactInternalID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String businessPartnerContactInternalID;
    @XmlElement(name = "DefaultContactPersonIndicator")
    protected Boolean defaultContactPersonIndicator;
    @XmlElement(name = "FormOfAddressCode")
    protected FormOfAddressCode formOfAddressCode;
    @XmlElement(name = "AcademicTitleCode")
    protected AcademicTitleCode academicTitleCode;
    @XmlElement(name = "GivenName")
    protected String givenName;
    @XmlElement(name = "MiddleName")
    protected String middleName;
    @XmlElement(name = "FamilyName")
    protected String familyName;
    @XmlElement(name = "BirthName")
    protected String birthName;
    @XmlElement(name = "NickName")
    protected String nickName;
    @XmlElement(name = "InitialsName")
    protected String initialsName;
    @XmlElement(name = "NamePrefixCode")
    protected FamilyNamePrefixCode namePrefixCode;
    @XmlElement(name = "AdditionalAcademicTitleCode")
    protected AcademicTitleCode additionalAcademicTitleCode;
    @XmlElement(name = "GenderCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String genderCode;
    @XmlElement(name = "BirthDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar birthDate;
    @XmlElement(name = "NonVerbalCommunicationLanguageCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "language")
    protected String nonVerbalCommunicationLanguageCode;
    @XmlElement(name = "OccupationCode")
    protected OccupationCode occupationCode;
    @XmlElement(name = "ContactAllowedCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String contactAllowedCode;
    @XmlElement(name = "BusinessPartnerFunctionTypeCode")
    protected BusinessPartnerFunctionTypeCode businessPartnerFunctionTypeCode;
    @XmlElement(name = "BusinessPartnerFunctionalAreaCode")
    protected BusinessPartnerFunctionalAreaCode businessPartnerFunctionalAreaCode;
    @XmlElement(name = "EngagementScoreNumberValue")
    protected Integer engagementScoreNumberValue;
    @XmlElement(name = "VIPReasonCode")
    protected VIPReasonCode vipReasonCode;
    @XmlElement(name = "WorkplaceBusinessAddressUUID")
    protected UUID workplaceBusinessAddressUUID;
    @XmlElement(name = "WorkplacePreferredCommunicationMediumTypeCode")
    protected CommunicationMediumTypeCode workplacePreferredCommunicationMediumTypeCode;
    @XmlElement(name = "WorkplaceEmail")
    protected CustomerMaintainRequestBundleEmail workplaceEmail;
    @XmlElement(name = "WorkplaceFacsimileFormattedNumberDescription")
    protected String workplaceFacsimileFormattedNumberDescription;
    @XmlElement(name = "WorkplaceTelephone")
    protected List<CustomerMaintainRequestBundleWorkplaceTelephone> workplaceTelephone;
    @XmlElement(name = "WorkplaceWebURI")
    @XmlSchemaType(name = "anyURI")
    protected String workplaceWebURI;
    @XmlElement(name = "WorkplaceFunctionalTitleName")
    protected String workplaceFunctionalTitleName;
    @XmlElement(name = "WorkplaceDepartmentName")
    protected String workplaceDepartmentName;
    @XmlElement(name = "WorkplaceBuildingID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String workplaceBuildingID;
    @XmlElement(name = "WorkplaceFloorID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String workplaceFloorID;
    @XmlElement(name = "WorkplaceRoomID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String workplaceRoomID;
    @XmlElement(name = "AddressInformation")
    protected List<CustomerMaintainRequestBundleContactPersonAddressInformation> addressInformation;
    @XmlElement(name = "LifeCycleStatusCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String lifeCycleStatusCode;
    @XmlElement(name = "MarketingPermission")
    protected List<CustomerMaintainRequestBundleMarketingPermission> marketingPermission;
    @XmlElement(name = "MaritalStatusCode")
    protected MaritalStatusCode maritalStatusCode;
    @XmlElement(name = "Text")
    protected List<CustomerMaintainRequestBundleText> text;
    @XmlElement(name = "AccountRole", namespace = "http://0003111061-one-off.sap.com/Y4R4GVT9Y_")
    protected BCOACCOUNTROLECode accountRole;
    @XmlElement(name = "Abbreviation", namespace = "http://0003111061-one-off.sap.com/Y4R4GVT9Y_")
    protected String abbreviation;
    @XmlElement(name = "Type", namespace = "http://0003111061-one-off.sap.com/Y4R4GVT9Y_")
    protected BCOACCOUNTTYPECode type;
    @XmlElement(name = "IsInWhiteList", namespace = "http://0003111061-one-off.sap.com/Y4R4GVT9Y_")
    @XmlSchemaType(name = "token")
    protected YesNoCode isInWhiteList;
    @XmlElement(name = "IsLicenseAccount", namespace = "http://0003111061-one-off.sap.com/Y4R4GVT9Y_")
    @XmlSchemaType(name = "token")
    protected YesNoCode isLicenseAccount;
    @XmlAttribute(name = "actionCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String actionCode;
    @XmlAttribute(name = "workplaceTelephoneListCompleteTransmissionIndicator")
    protected Boolean workplaceTelephoneListCompleteTransmissionIndicator;
    @XmlAttribute(name = "addressInformationListCompleteTransmissionIndicator")
    protected Boolean addressInformationListCompleteTransmissionIndicator;
    @XmlAttribute(name = "MarketingPermissionCompleteTransmissionIndicator")
    protected Boolean marketingPermissionCompleteTransmissionIndicator;
    @XmlAttribute(name = "textListCompleteTransmissionIndicator")
    protected Boolean textListCompleteTransmissionIndicator;

    /**
     * 获取objectNodeSenderTechnicalID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectNodeSenderTechnicalID() {
        return objectNodeSenderTechnicalID;
    }

    /**
     * 设置objectNodeSenderTechnicalID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectNodeSenderTechnicalID(String value) {
        this.objectNodeSenderTechnicalID = value;
    }

    /**
     * 获取businessPartnerContactUUID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UUID }
     *     
     */
    public UUID getBusinessPartnerContactUUID() {
        return businessPartnerContactUUID;
    }

    /**
     * 设置businessPartnerContactUUID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UUID }
     *     
     */
    public void setBusinessPartnerContactUUID(UUID value) {
        this.businessPartnerContactUUID = value;
    }

    /**
     * 获取businessPartnerContactInternalID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessPartnerContactInternalID() {
        return businessPartnerContactInternalID;
    }

    /**
     * 设置businessPartnerContactInternalID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessPartnerContactInternalID(String value) {
        this.businessPartnerContactInternalID = value;
    }

    /**
     * 获取defaultContactPersonIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDefaultContactPersonIndicator() {
        return defaultContactPersonIndicator;
    }

    /**
     * 设置defaultContactPersonIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDefaultContactPersonIndicator(Boolean value) {
        this.defaultContactPersonIndicator = value;
    }

    /**
     * 获取formOfAddressCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link FormOfAddressCode }
     *     
     */
    public FormOfAddressCode getFormOfAddressCode() {
        return formOfAddressCode;
    }

    /**
     * 设置formOfAddressCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link FormOfAddressCode }
     *     
     */
    public void setFormOfAddressCode(FormOfAddressCode value) {
        this.formOfAddressCode = value;
    }

    /**
     * 获取academicTitleCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AcademicTitleCode }
     *     
     */
    public AcademicTitleCode getAcademicTitleCode() {
        return academicTitleCode;
    }

    /**
     * 设置academicTitleCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AcademicTitleCode }
     *     
     */
    public void setAcademicTitleCode(AcademicTitleCode value) {
        this.academicTitleCode = value;
    }

    /**
     * 获取givenName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGivenName() {
        return givenName;
    }

    /**
     * 设置givenName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGivenName(String value) {
        this.givenName = value;
    }

    /**
     * 获取middleName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * 设置middleName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiddleName(String value) {
        this.middleName = value;
    }

    /**
     * 获取familyName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * 设置familyName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFamilyName(String value) {
        this.familyName = value;
    }

    /**
     * 获取birthName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBirthName() {
        return birthName;
    }

    /**
     * 设置birthName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBirthName(String value) {
        this.birthName = value;
    }

    /**
     * 获取nickName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置nickName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNickName(String value) {
        this.nickName = value;
    }

    /**
     * 获取initialsName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInitialsName() {
        return initialsName;
    }

    /**
     * 设置initialsName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInitialsName(String value) {
        this.initialsName = value;
    }

    /**
     * 获取namePrefixCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link FamilyNamePrefixCode }
     *     
     */
    public FamilyNamePrefixCode getNamePrefixCode() {
        return namePrefixCode;
    }

    /**
     * 设置namePrefixCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link FamilyNamePrefixCode }
     *     
     */
    public void setNamePrefixCode(FamilyNamePrefixCode value) {
        this.namePrefixCode = value;
    }

    /**
     * 获取additionalAcademicTitleCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AcademicTitleCode }
     *     
     */
    public AcademicTitleCode getAdditionalAcademicTitleCode() {
        return additionalAcademicTitleCode;
    }

    /**
     * 设置additionalAcademicTitleCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AcademicTitleCode }
     *     
     */
    public void setAdditionalAcademicTitleCode(AcademicTitleCode value) {
        this.additionalAcademicTitleCode = value;
    }

    /**
     * 获取genderCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGenderCode() {
        return genderCode;
    }

    /**
     * 设置genderCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGenderCode(String value) {
        this.genderCode = value;
    }

    /**
     * 获取birthDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBirthDate() {
        return birthDate;
    }

    /**
     * 设置birthDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBirthDate(XMLGregorianCalendar value) {
        this.birthDate = value;
    }

    /**
     * 获取nonVerbalCommunicationLanguageCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNonVerbalCommunicationLanguageCode() {
        return nonVerbalCommunicationLanguageCode;
    }

    /**
     * 设置nonVerbalCommunicationLanguageCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNonVerbalCommunicationLanguageCode(String value) {
        this.nonVerbalCommunicationLanguageCode = value;
    }

    /**
     * 获取occupationCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link OccupationCode }
     *     
     */
    public OccupationCode getOccupationCode() {
        return occupationCode;
    }

    /**
     * 设置occupationCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link OccupationCode }
     *     
     */
    public void setOccupationCode(OccupationCode value) {
        this.occupationCode = value;
    }

    /**
     * 获取contactAllowedCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactAllowedCode() {
        return contactAllowedCode;
    }

    /**
     * 设置contactAllowedCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactAllowedCode(String value) {
        this.contactAllowedCode = value;
    }

    /**
     * 获取businessPartnerFunctionTypeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BusinessPartnerFunctionTypeCode }
     *     
     */
    public BusinessPartnerFunctionTypeCode getBusinessPartnerFunctionTypeCode() {
        return businessPartnerFunctionTypeCode;
    }

    /**
     * 设置businessPartnerFunctionTypeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessPartnerFunctionTypeCode }
     *     
     */
    public void setBusinessPartnerFunctionTypeCode(BusinessPartnerFunctionTypeCode value) {
        this.businessPartnerFunctionTypeCode = value;
    }

    /**
     * 获取businessPartnerFunctionalAreaCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BusinessPartnerFunctionalAreaCode }
     *     
     */
    public BusinessPartnerFunctionalAreaCode getBusinessPartnerFunctionalAreaCode() {
        return businessPartnerFunctionalAreaCode;
    }

    /**
     * 设置businessPartnerFunctionalAreaCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessPartnerFunctionalAreaCode }
     *     
     */
    public void setBusinessPartnerFunctionalAreaCode(BusinessPartnerFunctionalAreaCode value) {
        this.businessPartnerFunctionalAreaCode = value;
    }

    /**
     * 获取engagementScoreNumberValue属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEngagementScoreNumberValue() {
        return engagementScoreNumberValue;
    }

    /**
     * 设置engagementScoreNumberValue属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEngagementScoreNumberValue(Integer value) {
        this.engagementScoreNumberValue = value;
    }

    /**
     * 获取vipReasonCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link VIPReasonCode }
     *     
     */
    public VIPReasonCode getVIPReasonCode() {
        return vipReasonCode;
    }

    /**
     * 设置vipReasonCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link VIPReasonCode }
     *     
     */
    public void setVIPReasonCode(VIPReasonCode value) {
        this.vipReasonCode = value;
    }

    /**
     * 获取workplaceBusinessAddressUUID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UUID }
     *     
     */
    public UUID getWorkplaceBusinessAddressUUID() {
        return workplaceBusinessAddressUUID;
    }

    /**
     * 设置workplaceBusinessAddressUUID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UUID }
     *     
     */
    public void setWorkplaceBusinessAddressUUID(UUID value) {
        this.workplaceBusinessAddressUUID = value;
    }

    /**
     * 获取workplacePreferredCommunicationMediumTypeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CommunicationMediumTypeCode }
     *     
     */
    public CommunicationMediumTypeCode getWorkplacePreferredCommunicationMediumTypeCode() {
        return workplacePreferredCommunicationMediumTypeCode;
    }

    /**
     * 设置workplacePreferredCommunicationMediumTypeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CommunicationMediumTypeCode }
     *     
     */
    public void setWorkplacePreferredCommunicationMediumTypeCode(CommunicationMediumTypeCode value) {
        this.workplacePreferredCommunicationMediumTypeCode = value;
    }

    /**
     * 获取workplaceEmail属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CustomerMaintainRequestBundleEmail }
     *     
     */
    public CustomerMaintainRequestBundleEmail getWorkplaceEmail() {
        return workplaceEmail;
    }

    /**
     * 设置workplaceEmail属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerMaintainRequestBundleEmail }
     *     
     */
    public void setWorkplaceEmail(CustomerMaintainRequestBundleEmail value) {
        this.workplaceEmail = value;
    }

    /**
     * 获取workplaceFacsimileFormattedNumberDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkplaceFacsimileFormattedNumberDescription() {
        return workplaceFacsimileFormattedNumberDescription;
    }

    /**
     * 设置workplaceFacsimileFormattedNumberDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkplaceFacsimileFormattedNumberDescription(String value) {
        this.workplaceFacsimileFormattedNumberDescription = value;
    }

    /**
     * Gets the value of the workplaceTelephone property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the workplaceTelephone property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWorkplaceTelephone().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustomerMaintainRequestBundleWorkplaceTelephone }
     * 
     * 
     */
    public List<CustomerMaintainRequestBundleWorkplaceTelephone> getWorkplaceTelephone() {
        if (workplaceTelephone == null) {
            workplaceTelephone = new ArrayList<CustomerMaintainRequestBundleWorkplaceTelephone>();
        }
        return this.workplaceTelephone;
    }

    /**
     * 获取workplaceWebURI属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkplaceWebURI() {
        return workplaceWebURI;
    }

    /**
     * 设置workplaceWebURI属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkplaceWebURI(String value) {
        this.workplaceWebURI = value;
    }

    /**
     * 获取workplaceFunctionalTitleName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkplaceFunctionalTitleName() {
        return workplaceFunctionalTitleName;
    }

    /**
     * 设置workplaceFunctionalTitleName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkplaceFunctionalTitleName(String value) {
        this.workplaceFunctionalTitleName = value;
    }

    /**
     * 获取workplaceDepartmentName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkplaceDepartmentName() {
        return workplaceDepartmentName;
    }

    /**
     * 设置workplaceDepartmentName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkplaceDepartmentName(String value) {
        this.workplaceDepartmentName = value;
    }

    /**
     * 获取workplaceBuildingID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkplaceBuildingID() {
        return workplaceBuildingID;
    }

    /**
     * 设置workplaceBuildingID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkplaceBuildingID(String value) {
        this.workplaceBuildingID = value;
    }

    /**
     * 获取workplaceFloorID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkplaceFloorID() {
        return workplaceFloorID;
    }

    /**
     * 设置workplaceFloorID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkplaceFloorID(String value) {
        this.workplaceFloorID = value;
    }

    /**
     * 获取workplaceRoomID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkplaceRoomID() {
        return workplaceRoomID;
    }

    /**
     * 设置workplaceRoomID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkplaceRoomID(String value) {
        this.workplaceRoomID = value;
    }

    /**
     * Gets the value of the addressInformation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the addressInformation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddressInformation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustomerMaintainRequestBundleContactPersonAddressInformation }
     * 
     * 
     */
    public List<CustomerMaintainRequestBundleContactPersonAddressInformation> getAddressInformation() {
        if (addressInformation == null) {
            addressInformation = new ArrayList<CustomerMaintainRequestBundleContactPersonAddressInformation>();
        }
        return this.addressInformation;
    }

    /**
     * 获取lifeCycleStatusCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLifeCycleStatusCode() {
        return lifeCycleStatusCode;
    }

    /**
     * 设置lifeCycleStatusCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLifeCycleStatusCode(String value) {
        this.lifeCycleStatusCode = value;
    }

    /**
     * Gets the value of the marketingPermission property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the marketingPermission property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMarketingPermission().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustomerMaintainRequestBundleMarketingPermission }
     * 
     * 
     */
    public List<CustomerMaintainRequestBundleMarketingPermission> getMarketingPermission() {
        if (marketingPermission == null) {
            marketingPermission = new ArrayList<CustomerMaintainRequestBundleMarketingPermission>();
        }
        return this.marketingPermission;
    }

    /**
     * 获取maritalStatusCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link MaritalStatusCode }
     *     
     */
    public MaritalStatusCode getMaritalStatusCode() {
        return maritalStatusCode;
    }

    /**
     * 设置maritalStatusCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link MaritalStatusCode }
     *     
     */
    public void setMaritalStatusCode(MaritalStatusCode value) {
        this.maritalStatusCode = value;
    }

    /**
     * Gets the value of the text property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the text property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getText().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustomerMaintainRequestBundleText }
     * 
     * 
     */
    public List<CustomerMaintainRequestBundleText> getText() {
        if (text == null) {
            text = new ArrayList<CustomerMaintainRequestBundleText>();
        }
        return this.text;
    }

    /**
     * 获取accountRole属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BCOACCOUNTROLECode }
     *     
     */
    public BCOACCOUNTROLECode getAccountRole() {
        return accountRole;
    }

    /**
     * 设置accountRole属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BCOACCOUNTROLECode }
     *     
     */
    public void setAccountRole(BCOACCOUNTROLECode value) {
        this.accountRole = value;
    }

    /**
     * 获取abbreviation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * 设置abbreviation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbbreviation(String value) {
        this.abbreviation = value;
    }

    /**
     * 获取type属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BCOACCOUNTTYPECode }
     *     
     */
    public BCOACCOUNTTYPECode getType() {
        return type;
    }

    /**
     * 设置type属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BCOACCOUNTTYPECode }
     *     
     */
    public void setType(BCOACCOUNTTYPECode value) {
        this.type = value;
    }

    /**
     * 获取isInWhiteList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link YesNoCode }
     *     
     */
    public YesNoCode getIsInWhiteList() {
        return isInWhiteList;
    }

    /**
     * 设置isInWhiteList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link YesNoCode }
     *     
     */
    public void setIsInWhiteList(YesNoCode value) {
        this.isInWhiteList = value;
    }

    /**
     * 获取isLicenseAccount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link YesNoCode }
     *     
     */
    public YesNoCode getIsLicenseAccount() {
        return isLicenseAccount;
    }

    /**
     * 设置isLicenseAccount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link YesNoCode }
     *     
     */
    public void setIsLicenseAccount(YesNoCode value) {
        this.isLicenseAccount = value;
    }

    /**
     * 获取actionCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionCode() {
        return actionCode;
    }

    /**
     * 设置actionCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionCode(String value) {
        this.actionCode = value;
    }

    /**
     * 获取workplaceTelephoneListCompleteTransmissionIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isWorkplaceTelephoneListCompleteTransmissionIndicator() {
        return workplaceTelephoneListCompleteTransmissionIndicator;
    }

    /**
     * 设置workplaceTelephoneListCompleteTransmissionIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setWorkplaceTelephoneListCompleteTransmissionIndicator(Boolean value) {
        this.workplaceTelephoneListCompleteTransmissionIndicator = value;
    }

    /**
     * 获取addressInformationListCompleteTransmissionIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAddressInformationListCompleteTransmissionIndicator() {
        return addressInformationListCompleteTransmissionIndicator;
    }

    /**
     * 设置addressInformationListCompleteTransmissionIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAddressInformationListCompleteTransmissionIndicator(Boolean value) {
        this.addressInformationListCompleteTransmissionIndicator = value;
    }

    /**
     * 获取marketingPermissionCompleteTransmissionIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMarketingPermissionCompleteTransmissionIndicator() {
        return marketingPermissionCompleteTransmissionIndicator;
    }

    /**
     * 设置marketingPermissionCompleteTransmissionIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMarketingPermissionCompleteTransmissionIndicator(Boolean value) {
        this.marketingPermissionCompleteTransmissionIndicator = value;
    }

    /**
     * 获取textListCompleteTransmissionIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTextListCompleteTransmissionIndicator() {
        return textListCompleteTransmissionIndicator;
    }

    /**
     * 设置textListCompleteTransmissionIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTextListCompleteTransmissionIndicator(Boolean value) {
        this.textListCompleteTransmissionIndicator = value;
    }

}
