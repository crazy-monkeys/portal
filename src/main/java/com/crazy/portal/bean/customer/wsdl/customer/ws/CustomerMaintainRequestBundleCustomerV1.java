
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
import javax.xml.datatype.Duration;


/**
 * <p>CustomerMaintainRequestBundleCustomer_V1 complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CustomerMaintainRequestBundleCustomer_V1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ObjectNodeSenderTechnicalID" type="{http://sap.com/xi/AP/Common/GDT}ObjectNodePartyTechnicalID" minOccurs="0"/&gt;
 *         &lt;element name="ChangeStateID" type="{http://sap.com/xi/AP/Common/GDT}ChangeStateID" minOccurs="0"/&gt;
 *         &lt;element name="UUID" type="{http://sap.com/xi/AP/Common/GDT}UUID" minOccurs="0"/&gt;
 *         &lt;element name="InternalID" type="{http://sap.com/xi/AP/Common/GDT}BusinessPartnerInternalID" minOccurs="0"/&gt;
 *         &lt;element name="CategoryCode" type="{http://sap.com/xi/AP/Common/GDT}BusinessPartnerCategoryCode" minOccurs="0"/&gt;
 *         &lt;element name="ProspectIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *         &lt;element name="CustomerIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *         &lt;element name="LifeCycleStatusCode" type="{http://sap.com/xi/AP/Common/GDT}PartyLifeCycleStatusCode" minOccurs="0"/&gt;
 *         &lt;element name="Person" type="{http://sap.com/xi/A1S/Global}CustomerMaintainRequestBundlePerson" minOccurs="0"/&gt;
 *         &lt;element name="Organisation" type="{http://sap.com/xi/A1S/Global}CustomerMaintainRequestBundleOrganisation" minOccurs="0"/&gt;
 *         &lt;element name="VerbalCommunicationLanguageCode" type="{http://sap.com/xi/BASIS/Global}LanguageCode" minOccurs="0"/&gt;
 *         &lt;element name="ContactAllowedCode" type="{http://sap.com/xi/AP/Common/GDT}ContactAllowedCode" minOccurs="0"/&gt;
 *         &lt;element name="LegalCompetenceIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *         &lt;element name="DunAndBradstreetNumberID" type="{http://sap.com/xi/AP/Common/GDT}BusinessPartnerID" minOccurs="0"/&gt;
 *         &lt;element name="GlobalLocationNumberID" type="{http://sap.com/xi/AP/Common/GDT}BusinessPartnerID" minOccurs="0"/&gt;
 *         &lt;element name="NationalProviderID" type="{http://sap.com/xi/AP/Common/GDT}BusinessPartnerID" minOccurs="0"/&gt;
 *         &lt;element name="ABCClassificationCode" type="{http://sap.com/xi/AP/Common/GDT}CustomerABCClassificationCode" minOccurs="0"/&gt;
 *         &lt;element name="NielsenRegionCode" type="{http://sap.com/xi/AP/Common/GDT}NielsenRegionCode" minOccurs="0"/&gt;
 *         &lt;element name="RecommendedVisitingFrequencyDuration" type="{http://sap.com/xi/Common/DataTypes}Duration" minOccurs="0"/&gt;
 *         &lt;element name="IndustrialSectorCode" type="{http://sap.com/xi/AP/Common/GDT}IndustrialSectorCode" minOccurs="0"/&gt;
 *         &lt;element name="AddressInformation" type="{http://sap.com/xi/A1S/Global}CustomerMaintainRequestBundleAddressInformation" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Relationship" type="{http://sap.com/xi/A1S/Global}CustomerMaintainRequestBundleRelationship" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Role" type="{http://sap.com/xi/A1S/Global}CustomerMaintainRequestBundleBusinessRole" minOccurs="0"/&gt;
 *         &lt;element name="ContactPerson" type="{http://sap.com/xi/A1S/Global}CustomerMaintainRequestBundleContactPerson" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="CommunicationArrangement" type="{http://sap.com/xi/A1S/Global}CommunicationArrangement" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="DirectResponsibility" type="{http://sap.com/xi/A1S/Global}CustomerMaintainRequestBundleDirectResponsibility" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SalesArrangement" type="{http://sap.com/xi/A1S/Global}CustomerMaintainRequestBundleSalesArrangement" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="PaymentData" type="{http://sap.com/xi/A1S/Global}CustomerMaintainRequestBundlePaymentData" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="OperatingHoursInformation" type="{http://sap.com/xi/A1S/Global}CustomerMaintainRequestBundleOperatingHoursInformation" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="TaxNumber" type="{http://sap.com/xi/A1S/Global}TaxNumber" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="OperatingHoursTransformedInformation" type="{http://sap.com/xi/A1S/Global}CustomerMaintainRequestBundleTransformedOperatingHoursInformation" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Text" type="{http://sap.com/xi/A1S/Global}CustomerMaintainRequestBundleText" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="BlockingReasons" type="{http://sap.com/xi/A1S/Global}CustomerMaintainRequestBundleBlockingReasons" minOccurs="0"/&gt;
 *         &lt;element name="AttachmentFolder" type="{http://sap.com/xi/DocumentServices/Global}MaintenanceAttachmentFolder" minOccurs="0"/&gt;
 *         &lt;element name="DuplicateCheckApplyIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *         &lt;element name="EnterpriseIdentificationNumber" type="{http://sap.com/xi/AP/Common/GDT}BusinessPartnerID" minOccurs="0"/&gt;
 *         &lt;group ref="{http://sap.com/xi/AP/Globalization}CustomerCommonExtensionElements"/&gt;
 *         &lt;group ref="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}Ext00163E732DE21EE9AE9DAE9F28EE1766"/&gt;
 *         &lt;group ref="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}Ext00163E732DE21EE9AE9E0835D25AD987"/&gt;
 *         &lt;group ref="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}Ext00163E732DE21EE9AE9E1E73267239F5"/&gt;
 *         &lt;group ref="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}Ext00163E732DE21EE9AE9E215E29DEFA42"/&gt;
 *         &lt;group ref="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}Ext00163E732DE21EE9AE9E24200510BA44"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="actionCode" use="required" type="{http://sap.com/xi/AP/Common/GDT}ActionCode" /&gt;
 *       &lt;attribute name="addressInformationListCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" /&gt;
 *       &lt;attribute name="communicationArrangementListCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" /&gt;
 *       &lt;attribute name="directResponsibilityListCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" /&gt;
 *       &lt;attribute name="relationshipListCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" /&gt;
 *       &lt;attribute name="TaxNumberListCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" /&gt;
 *       &lt;attribute name="salesArrangementListCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" /&gt;
 *       &lt;attribute name="contactPersonListCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" /&gt;
 *       &lt;attribute name="paymentDataListCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" /&gt;
 *       &lt;attribute name="textListCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" /&gt;
 *       &lt;attribute name="BlockingReasonListCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" /&gt;
 *       &lt;attribute name="operatingHoursInformationListCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerMaintainRequestBundleCustomer_V1", namespace = "http://sap.com/xi/A1S/Global", propOrder = {
    "objectNodeSenderTechnicalID",
    "changeStateID",
    "uuid",
    "internalID",
    "categoryCode",
    "prospectIndicator",
    "customerIndicator",
    "lifeCycleStatusCode",
    "person",
    "organisation",
    "verbalCommunicationLanguageCode",
    "contactAllowedCode",
    "legalCompetenceIndicator",
    "dunAndBradstreetNumberID",
    "globalLocationNumberID",
    "nationalProviderID",
    "abcClassificationCode",
    "nielsenRegionCode",
    "recommendedVisitingFrequencyDuration",
    "industrialSectorCode",
    "addressInformation",
    "relationship",
    "role",
    "contactPerson",
    "communicationArrangement",
    "directResponsibility",
    "salesArrangement",
    "paymentData",
    "operatingHoursInformation",
    "taxNumber",
    "operatingHoursTransformedInformation",
    "text",
    "blockingReasons",
    "attachmentFolder",
    "duplicateCheckApplyIndicator",
    "enterpriseIdentificationNumber",
    "taxPayerCategoryCode",
    "enterpriseIdentificationSupplementCode",
    "accountRole",
    "abbreviation",
    "type",
    "isInWhiteList",
    "isLicenseAccount"
})
public class CustomerMaintainRequestBundleCustomerV1 {

    @XmlElement(name = "ObjectNodeSenderTechnicalID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String objectNodeSenderTechnicalID;
    @XmlElement(name = "ChangeStateID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String changeStateID;
    @XmlElement(name = "UUID")
    protected UUID uuid;
    @XmlElement(name = "InternalID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String internalID;
    @XmlElement(name = "CategoryCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String categoryCode;
    @XmlElement(name = "ProspectIndicator")
    protected Boolean prospectIndicator;
    @XmlElement(name = "CustomerIndicator")
    protected Boolean customerIndicator;
    @XmlElement(name = "LifeCycleStatusCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String lifeCycleStatusCode;
    @XmlElement(name = "Person")
    protected CustomerMaintainRequestBundlePerson person;
    @XmlElement(name = "Organisation")
    protected CustomerMaintainRequestBundleOrganisation organisation;
    @XmlElement(name = "VerbalCommunicationLanguageCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "language")
    protected String verbalCommunicationLanguageCode;
    @XmlElement(name = "ContactAllowedCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String contactAllowedCode;
    @XmlElement(name = "LegalCompetenceIndicator")
    protected Boolean legalCompetenceIndicator;
    @XmlElement(name = "DunAndBradstreetNumberID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String dunAndBradstreetNumberID;
    @XmlElement(name = "GlobalLocationNumberID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String globalLocationNumberID;
    @XmlElement(name = "NationalProviderID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String nationalProviderID;
    @XmlElement(name = "ABCClassificationCode")
    protected CustomerABCClassificationCode abcClassificationCode;
    @XmlElement(name = "NielsenRegionCode")
    protected NielsenRegionCode nielsenRegionCode;
    @XmlElement(name = "RecommendedVisitingFrequencyDuration")
    protected Duration recommendedVisitingFrequencyDuration;
    @XmlElement(name = "IndustrialSectorCode")
    protected IndustrialSectorCode industrialSectorCode;
    @XmlElement(name = "AddressInformation")
    protected List<CustomerMaintainRequestBundleAddressInformation> addressInformation;
    @XmlElement(name = "Relationship")
    protected List<CustomerMaintainRequestBundleRelationship> relationship;
    @XmlElement(name = "Role")
    protected CustomerMaintainRequestBundleBusinessRole role;
    @XmlElement(name = "ContactPerson")
    protected List<CustomerMaintainRequestBundleContactPerson> contactPerson;
    @XmlElement(name = "CommunicationArrangement")
    protected List<CommunicationArrangement> communicationArrangement;
    @XmlElement(name = "DirectResponsibility")
    protected List<CustomerMaintainRequestBundleDirectResponsibility> directResponsibility;
    @XmlElement(name = "SalesArrangement")
    protected List<CustomerMaintainRequestBundleSalesArrangement> salesArrangement;
    @XmlElement(name = "PaymentData")
    protected List<CustomerMaintainRequestBundlePaymentData> paymentData;
    @XmlElement(name = "OperatingHoursInformation")
    protected List<CustomerMaintainRequestBundleOperatingHoursInformation> operatingHoursInformation;
    @XmlElement(name = "TaxNumber")
    protected List<TaxNumber> taxNumber;
    @XmlElement(name = "OperatingHoursTransformedInformation")
    protected List<CustomerMaintainRequestBundleTransformedOperatingHoursInformation> operatingHoursTransformedInformation;
    @XmlElement(name = "Text")
    protected List<CustomerMaintainRequestBundleText> text;
    @XmlElement(name = "BlockingReasons")
    protected CustomerMaintainRequestBundleBlockingReasons blockingReasons;
    @XmlElement(name = "AttachmentFolder")
    protected MaintenanceAttachmentFolder attachmentFolder;
    @XmlElement(name = "DuplicateCheckApplyIndicator")
    protected Boolean duplicateCheckApplyIndicator;
    @XmlElement(name = "EnterpriseIdentificationNumber")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String enterpriseIdentificationNumber;
    @XmlElement(name = "TaxPayerCategoryCode", namespace = "http://sap.com/xi/AP/Globalization")
    protected TaxPayerCategoryCode taxPayerCategoryCode;
    @XmlElement(name = "EnterpriseIdentificationSupplementCode", namespace = "http://sap.com/xi/AP/Globalization")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String enterpriseIdentificationSupplementCode;
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
    @XmlAttribute(name = "actionCode", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String actionCode;
    @XmlAttribute(name = "addressInformationListCompleteTransmissionIndicator")
    protected Boolean addressInformationListCompleteTransmissionIndicator;
    @XmlAttribute(name = "communicationArrangementListCompleteTransmissionIndicator")
    protected Boolean communicationArrangementListCompleteTransmissionIndicator;
    @XmlAttribute(name = "directResponsibilityListCompleteTransmissionIndicator")
    protected Boolean directResponsibilityListCompleteTransmissionIndicator;
    @XmlAttribute(name = "relationshipListCompleteTransmissionIndicator")
    protected Boolean relationshipListCompleteTransmissionIndicator;
    @XmlAttribute(name = "TaxNumberListCompleteTransmissionIndicator")
    protected Boolean taxNumberListCompleteTransmissionIndicator;
    @XmlAttribute(name = "salesArrangementListCompleteTransmissionIndicator")
    protected Boolean salesArrangementListCompleteTransmissionIndicator;
    @XmlAttribute(name = "contactPersonListCompleteTransmissionIndicator")
    protected Boolean contactPersonListCompleteTransmissionIndicator;
    @XmlAttribute(name = "paymentDataListCompleteTransmissionIndicator")
    protected Boolean paymentDataListCompleteTransmissionIndicator;
    @XmlAttribute(name = "textListCompleteTransmissionIndicator")
    protected Boolean textListCompleteTransmissionIndicator;
    @XmlAttribute(name = "BlockingReasonListCompleteTransmissionIndicator")
    protected Boolean blockingReasonListCompleteTransmissionIndicator;
    @XmlAttribute(name = "operatingHoursInformationListCompleteTransmissionIndicator")
    protected Boolean operatingHoursInformationListCompleteTransmissionIndicator;

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
     * 获取changeStateID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChangeStateID() {
        return changeStateID;
    }

    /**
     * 设置changeStateID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChangeStateID(String value) {
        this.changeStateID = value;
    }

    /**
     * 获取uuid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UUID }
     *     
     */
    public UUID getUUID() {
        return uuid;
    }

    /**
     * 设置uuid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UUID }
     *     
     */
    public void setUUID(UUID value) {
        this.uuid = value;
    }

    /**
     * 获取internalID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInternalID() {
        return internalID;
    }

    /**
     * 设置internalID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInternalID(String value) {
        this.internalID = value;
    }

    /**
     * 获取categoryCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryCode() {
        return categoryCode;
    }

    /**
     * 设置categoryCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryCode(String value) {
        this.categoryCode = value;
    }

    /**
     * 获取prospectIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isProspectIndicator() {
        return prospectIndicator;
    }

    /**
     * 设置prospectIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setProspectIndicator(Boolean value) {
        this.prospectIndicator = value;
    }

    /**
     * 获取customerIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCustomerIndicator() {
        return customerIndicator;
    }

    /**
     * 设置customerIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCustomerIndicator(Boolean value) {
        this.customerIndicator = value;
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
     * 获取person属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CustomerMaintainRequestBundlePerson }
     *     
     */
    public CustomerMaintainRequestBundlePerson getPerson() {
        return person;
    }

    /**
     * 设置person属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerMaintainRequestBundlePerson }
     *     
     */
    public void setPerson(CustomerMaintainRequestBundlePerson value) {
        this.person = value;
    }

    /**
     * 获取organisation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CustomerMaintainRequestBundleOrganisation }
     *     
     */
    public CustomerMaintainRequestBundleOrganisation getOrganisation() {
        return organisation;
    }

    /**
     * 设置organisation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerMaintainRequestBundleOrganisation }
     *     
     */
    public void setOrganisation(CustomerMaintainRequestBundleOrganisation value) {
        this.organisation = value;
    }

    /**
     * 获取verbalCommunicationLanguageCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVerbalCommunicationLanguageCode() {
        return verbalCommunicationLanguageCode;
    }

    /**
     * 设置verbalCommunicationLanguageCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVerbalCommunicationLanguageCode(String value) {
        this.verbalCommunicationLanguageCode = value;
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
     * 获取legalCompetenceIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLegalCompetenceIndicator() {
        return legalCompetenceIndicator;
    }

    /**
     * 设置legalCompetenceIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLegalCompetenceIndicator(Boolean value) {
        this.legalCompetenceIndicator = value;
    }

    /**
     * 获取dunAndBradstreetNumberID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDunAndBradstreetNumberID() {
        return dunAndBradstreetNumberID;
    }

    /**
     * 设置dunAndBradstreetNumberID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDunAndBradstreetNumberID(String value) {
        this.dunAndBradstreetNumberID = value;
    }

    /**
     * 获取globalLocationNumberID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGlobalLocationNumberID() {
        return globalLocationNumberID;
    }

    /**
     * 设置globalLocationNumberID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGlobalLocationNumberID(String value) {
        this.globalLocationNumberID = value;
    }

    /**
     * 获取nationalProviderID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNationalProviderID() {
        return nationalProviderID;
    }

    /**
     * 设置nationalProviderID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNationalProviderID(String value) {
        this.nationalProviderID = value;
    }

    /**
     * 获取abcClassificationCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CustomerABCClassificationCode }
     *     
     */
    public CustomerABCClassificationCode getABCClassificationCode() {
        return abcClassificationCode;
    }

    /**
     * 设置abcClassificationCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerABCClassificationCode }
     *     
     */
    public void setABCClassificationCode(CustomerABCClassificationCode value) {
        this.abcClassificationCode = value;
    }

    /**
     * 获取nielsenRegionCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link NielsenRegionCode }
     *     
     */
    public NielsenRegionCode getNielsenRegionCode() {
        return nielsenRegionCode;
    }

    /**
     * 设置nielsenRegionCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link NielsenRegionCode }
     *     
     */
    public void setNielsenRegionCode(NielsenRegionCode value) {
        this.nielsenRegionCode = value;
    }

    /**
     * 获取recommendedVisitingFrequencyDuration属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getRecommendedVisitingFrequencyDuration() {
        return recommendedVisitingFrequencyDuration;
    }

    /**
     * 设置recommendedVisitingFrequencyDuration属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setRecommendedVisitingFrequencyDuration(Duration value) {
        this.recommendedVisitingFrequencyDuration = value;
    }

    /**
     * 获取industrialSectorCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link IndustrialSectorCode }
     *     
     */
    public IndustrialSectorCode getIndustrialSectorCode() {
        return industrialSectorCode;
    }

    /**
     * 设置industrialSectorCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link IndustrialSectorCode }
     *     
     */
    public void setIndustrialSectorCode(IndustrialSectorCode value) {
        this.industrialSectorCode = value;
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
     * {@link CustomerMaintainRequestBundleAddressInformation }
     * 
     * 
     */
    public List<CustomerMaintainRequestBundleAddressInformation> getAddressInformation() {
        if (addressInformation == null) {
            addressInformation = new ArrayList<CustomerMaintainRequestBundleAddressInformation>();
        }
        return this.addressInformation;
    }

    /**
     * Gets the value of the relationship property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the relationship property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRelationship().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustomerMaintainRequestBundleRelationship }
     * 
     * 
     */
    public List<CustomerMaintainRequestBundleRelationship> getRelationship() {
        if (relationship == null) {
            relationship = new ArrayList<CustomerMaintainRequestBundleRelationship>();
        }
        return this.relationship;
    }

    /**
     * 获取role属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CustomerMaintainRequestBundleBusinessRole }
     *     
     */
    public CustomerMaintainRequestBundleBusinessRole getRole() {
        return role;
    }

    /**
     * 设置role属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerMaintainRequestBundleBusinessRole }
     *     
     */
    public void setRole(CustomerMaintainRequestBundleBusinessRole value) {
        this.role = value;
    }

    /**
     * Gets the value of the contactPerson property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contactPerson property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContactPerson().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustomerMaintainRequestBundleContactPerson }
     * 
     * 
     */
    public List<CustomerMaintainRequestBundleContactPerson> getContactPerson() {
        if (contactPerson == null) {
            contactPerson = new ArrayList<CustomerMaintainRequestBundleContactPerson>();
        }
        return this.contactPerson;
    }

    /**
     * Gets the value of the communicationArrangement property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the communicationArrangement property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCommunicationArrangement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CommunicationArrangement }
     * 
     * 
     */
    public List<CommunicationArrangement> getCommunicationArrangement() {
        if (communicationArrangement == null) {
            communicationArrangement = new ArrayList<CommunicationArrangement>();
        }
        return this.communicationArrangement;
    }

    /**
     * Gets the value of the directResponsibility property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the directResponsibility property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDirectResponsibility().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustomerMaintainRequestBundleDirectResponsibility }
     * 
     * 
     */
    public List<CustomerMaintainRequestBundleDirectResponsibility> getDirectResponsibility() {
        if (directResponsibility == null) {
            directResponsibility = new ArrayList<CustomerMaintainRequestBundleDirectResponsibility>();
        }
        return this.directResponsibility;
    }

    /**
     * Gets the value of the salesArrangement property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the salesArrangement property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSalesArrangement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustomerMaintainRequestBundleSalesArrangement }
     * 
     * 
     */
    public List<CustomerMaintainRequestBundleSalesArrangement> getSalesArrangement() {
        if (salesArrangement == null) {
            salesArrangement = new ArrayList<CustomerMaintainRequestBundleSalesArrangement>();
        }
        return this.salesArrangement;
    }

    /**
     * Gets the value of the paymentData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the paymentData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPaymentData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustomerMaintainRequestBundlePaymentData }
     * 
     * 
     */
    public List<CustomerMaintainRequestBundlePaymentData> getPaymentData() {
        if (paymentData == null) {
            paymentData = new ArrayList<CustomerMaintainRequestBundlePaymentData>();
        }
        return this.paymentData;
    }

    /**
     * Gets the value of the operatingHoursInformation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the operatingHoursInformation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOperatingHoursInformation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustomerMaintainRequestBundleOperatingHoursInformation }
     * 
     * 
     */
    public List<CustomerMaintainRequestBundleOperatingHoursInformation> getOperatingHoursInformation() {
        if (operatingHoursInformation == null) {
            operatingHoursInformation = new ArrayList<CustomerMaintainRequestBundleOperatingHoursInformation>();
        }
        return this.operatingHoursInformation;
    }

    /**
     * Gets the value of the taxNumber property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the taxNumber property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTaxNumber().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TaxNumber }
     * 
     * 
     */
    public List<TaxNumber> getTaxNumber() {
        if (taxNumber == null) {
            taxNumber = new ArrayList<TaxNumber>();
        }
        return this.taxNumber;
    }

    /**
     * Gets the value of the operatingHoursTransformedInformation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the operatingHoursTransformedInformation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOperatingHoursTransformedInformation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustomerMaintainRequestBundleTransformedOperatingHoursInformation }
     * 
     * 
     */
    public List<CustomerMaintainRequestBundleTransformedOperatingHoursInformation> getOperatingHoursTransformedInformation() {
        if (operatingHoursTransformedInformation == null) {
            operatingHoursTransformedInformation = new ArrayList<CustomerMaintainRequestBundleTransformedOperatingHoursInformation>();
        }
        return this.operatingHoursTransformedInformation;
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
     * 获取blockingReasons属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CustomerMaintainRequestBundleBlockingReasons }
     *     
     */
    public CustomerMaintainRequestBundleBlockingReasons getBlockingReasons() {
        return blockingReasons;
    }

    /**
     * 设置blockingReasons属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerMaintainRequestBundleBlockingReasons }
     *     
     */
    public void setBlockingReasons(CustomerMaintainRequestBundleBlockingReasons value) {
        this.blockingReasons = value;
    }

    /**
     * 获取attachmentFolder属性的值。
     * 
     * @return
     *     possible object is
     *     {@link MaintenanceAttachmentFolder }
     *     
     */
    public MaintenanceAttachmentFolder getAttachmentFolder() {
        return attachmentFolder;
    }

    /**
     * 设置attachmentFolder属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link MaintenanceAttachmentFolder }
     *     
     */
    public void setAttachmentFolder(MaintenanceAttachmentFolder value) {
        this.attachmentFolder = value;
    }

    /**
     * 获取duplicateCheckApplyIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDuplicateCheckApplyIndicator() {
        return duplicateCheckApplyIndicator;
    }

    /**
     * 设置duplicateCheckApplyIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDuplicateCheckApplyIndicator(Boolean value) {
        this.duplicateCheckApplyIndicator = value;
    }

    /**
     * 获取enterpriseIdentificationNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnterpriseIdentificationNumber() {
        return enterpriseIdentificationNumber;
    }

    /**
     * 设置enterpriseIdentificationNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnterpriseIdentificationNumber(String value) {
        this.enterpriseIdentificationNumber = value;
    }

    /**
     * 获取taxPayerCategoryCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TaxPayerCategoryCode }
     *     
     */
    public TaxPayerCategoryCode getTaxPayerCategoryCode() {
        return taxPayerCategoryCode;
    }

    /**
     * 设置taxPayerCategoryCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TaxPayerCategoryCode }
     *     
     */
    public void setTaxPayerCategoryCode(TaxPayerCategoryCode value) {
        this.taxPayerCategoryCode = value;
    }

    /**
     * 获取enterpriseIdentificationSupplementCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnterpriseIdentificationSupplementCode() {
        return enterpriseIdentificationSupplementCode;
    }

    /**
     * 设置enterpriseIdentificationSupplementCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnterpriseIdentificationSupplementCode(String value) {
        this.enterpriseIdentificationSupplementCode = value;
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
     * 获取communicationArrangementListCompleteTransmissionIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCommunicationArrangementListCompleteTransmissionIndicator() {
        return communicationArrangementListCompleteTransmissionIndicator;
    }

    /**
     * 设置communicationArrangementListCompleteTransmissionIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCommunicationArrangementListCompleteTransmissionIndicator(Boolean value) {
        this.communicationArrangementListCompleteTransmissionIndicator = value;
    }

    /**
     * 获取directResponsibilityListCompleteTransmissionIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDirectResponsibilityListCompleteTransmissionIndicator() {
        return directResponsibilityListCompleteTransmissionIndicator;
    }

    /**
     * 设置directResponsibilityListCompleteTransmissionIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDirectResponsibilityListCompleteTransmissionIndicator(Boolean value) {
        this.directResponsibilityListCompleteTransmissionIndicator = value;
    }

    /**
     * 获取relationshipListCompleteTransmissionIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRelationshipListCompleteTransmissionIndicator() {
        return relationshipListCompleteTransmissionIndicator;
    }

    /**
     * 设置relationshipListCompleteTransmissionIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRelationshipListCompleteTransmissionIndicator(Boolean value) {
        this.relationshipListCompleteTransmissionIndicator = value;
    }

    /**
     * 获取taxNumberListCompleteTransmissionIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTaxNumberListCompleteTransmissionIndicator() {
        return taxNumberListCompleteTransmissionIndicator;
    }

    /**
     * 设置taxNumberListCompleteTransmissionIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTaxNumberListCompleteTransmissionIndicator(Boolean value) {
        this.taxNumberListCompleteTransmissionIndicator = value;
    }

    /**
     * 获取salesArrangementListCompleteTransmissionIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSalesArrangementListCompleteTransmissionIndicator() {
        return salesArrangementListCompleteTransmissionIndicator;
    }

    /**
     * 设置salesArrangementListCompleteTransmissionIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSalesArrangementListCompleteTransmissionIndicator(Boolean value) {
        this.salesArrangementListCompleteTransmissionIndicator = value;
    }

    /**
     * 获取contactPersonListCompleteTransmissionIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isContactPersonListCompleteTransmissionIndicator() {
        return contactPersonListCompleteTransmissionIndicator;
    }

    /**
     * 设置contactPersonListCompleteTransmissionIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setContactPersonListCompleteTransmissionIndicator(Boolean value) {
        this.contactPersonListCompleteTransmissionIndicator = value;
    }

    /**
     * 获取paymentDataListCompleteTransmissionIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPaymentDataListCompleteTransmissionIndicator() {
        return paymentDataListCompleteTransmissionIndicator;
    }

    /**
     * 设置paymentDataListCompleteTransmissionIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPaymentDataListCompleteTransmissionIndicator(Boolean value) {
        this.paymentDataListCompleteTransmissionIndicator = value;
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

    /**
     * 获取blockingReasonListCompleteTransmissionIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBlockingReasonListCompleteTransmissionIndicator() {
        return blockingReasonListCompleteTransmissionIndicator;
    }

    /**
     * 设置blockingReasonListCompleteTransmissionIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBlockingReasonListCompleteTransmissionIndicator(Boolean value) {
        this.blockingReasonListCompleteTransmissionIndicator = value;
    }

    /**
     * 获取operatingHoursInformationListCompleteTransmissionIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOperatingHoursInformationListCompleteTransmissionIndicator() {
        return operatingHoursInformationListCompleteTransmissionIndicator;
    }

    /**
     * 设置operatingHoursInformationListCompleteTransmissionIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOperatingHoursInformationListCompleteTransmissionIndicator(Boolean value) {
        this.operatingHoursInformationListCompleteTransmissionIndicator = value;
    }

}
