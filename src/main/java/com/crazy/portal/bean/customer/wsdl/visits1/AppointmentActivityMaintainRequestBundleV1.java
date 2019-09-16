
package com.crazy.portal.bean.customer.wsdl.visits1;

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
 * <p>AppointmentActivityMaintainRequestBundle_V1 complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="AppointmentActivityMaintainRequestBundle_V1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ObjectNodeSenderTechnicalID" type="{http://sap.com/xi/AP/Common/GDT}ObjectNodePartyTechnicalID" minOccurs="0"/&gt;
 *         &lt;element name="ChangeStateID" type="{http://sap.com/xi/AP/Common/GDT}ChangeStateID" minOccurs="0"/&gt;
 *         &lt;element name="ID" type="{http://sap.com/xi/AP/Common/GDT}BusinessTransactionDocumentID" minOccurs="0"/&gt;
 *         &lt;element name="UUID" type="{http://sap.com/xi/AP/Common/GDT}UUID" minOccurs="0"/&gt;
 *         &lt;element name="DocumentTypeCode" type="{http://sap.com/xi/AP/Common/GDT}BusinessTransactionDocumentProcessingTypeCode" minOccurs="0"/&gt;
 *         &lt;element name="MigratedDataAdaptationTypeCode" type="{http://sap.com/xi/AP/Common/GDT}MigratedDataAdaptationTypeCode" minOccurs="0"/&gt;
 *         &lt;element name="Name" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_EXTENDED_Name" minOccurs="0"/&gt;
 *         &lt;element name="PriorityCode" type="{http://sap.com/xi/AP/Common/GDT}PriorityCode" minOccurs="0"/&gt;
 *         &lt;element name="InitiatorCode" type="{http://sap.com/xi/AP/Common/GDT}ActivityInitiatorCode" minOccurs="0"/&gt;
 *         &lt;element name="InformationSensitivityCode" type="{http://sap.com/xi/AP/Common/GDT}InformationSensitivityCode" minOccurs="0"/&gt;
 *         &lt;element name="GroupCode" type="{http://sap.com/xi/AP/Common/GDT}ActivityGroupCode" minOccurs="0"/&gt;
 *         &lt;element name="DataOriginTypeCode" type="{http://sap.com/xi/AP/Common/GDT}ActivityDataOriginTypeCode" minOccurs="0"/&gt;
 *         &lt;element name="LifeCycleStatusCode" type="{http://sap.com/xi/AP/Common/GDT}ActivityLifeCycleStatusCode" minOccurs="0"/&gt;
 *         &lt;element name="StartDateTime" type="{http://sap.com/xi/BASIS/Global}LOCALNORMALISED_DateTime" minOccurs="0"/&gt;
 *         &lt;element name="EndDateTime" type="{http://sap.com/xi/BASIS/Global}LOCALNORMALISED_DateTime" minOccurs="0"/&gt;
 *         &lt;element name="FullDayIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *         &lt;element name="CompletionDateTime" type="{http://sap.com/xi/BASIS/Global}GLOBAL_DateTime" minOccurs="0"/&gt;
 *         &lt;element name="ActualStartDateTime" type="{http://sap.com/xi/BASIS/Global}GLOBAL_DateTime" minOccurs="0"/&gt;
 *         &lt;element name="ActualEndDateTime" type="{http://sap.com/xi/BASIS/Global}GLOBAL_DateTime" minOccurs="0"/&gt;
 *         &lt;element name="ActivityFollowUpServiceRequestBlockingReasonCode" type="{http://sap.com/xi/Common/DataTypes}ActivityFollowUpServiceRequestBlockingReasonCode" minOccurs="0"/&gt;
 *         &lt;element name="GroupwareItemID" type="{http://sap.com/xi/AP/Common/GDT}GroupwareItemID" minOccurs="0"/&gt;
 *         &lt;element name="StartGeoCoordinates" type="{http://sap.com/xi/AP/Common/GDT}GeoCoordinates" minOccurs="0"/&gt;
 *         &lt;element name="EndGeoCoordinates" type="{http://sap.com/xi/AP/Common/GDT}GeoCoordinates" minOccurs="0"/&gt;
 *         &lt;element name="SalesOrganisationID" type="{http://sap.com/xi/AP/Common/GDT}OrganisationalCentreID" minOccurs="0"/&gt;
 *         &lt;element name="DistributionChannelCode" type="{http://sap.com/xi/AP/Common/GDT}DistributionChannelCode" minOccurs="0"/&gt;
 *         &lt;element name="DivisionCode" type="{http://sap.com/xi/AP/Common/GDT}DivisionCode" minOccurs="0"/&gt;
 *         &lt;element name="SalesTerritoryID" type="{http://sap.com/xi/AP/Common/GDT}SalesTerritoryID" minOccurs="0"/&gt;
 *         &lt;element name="ActivityListUUID" type="{http://sap.com/xi/Common/DataTypes}UUID" minOccurs="0"/&gt;
 *         &lt;element name="ActivityListID" type="{http://sap.com/xi/Common/DataTypes}BusinessTransactionDocumentID" minOccurs="0"/&gt;
 *         &lt;element name="LocationName" type="{http://sap.com/xi/A1S/Global}AppointmentActivityRequestBundleLocationName" minOccurs="0"/&gt;
 *         &lt;element name="AdditionalLocationName" type="{http://sap.com/xi/A1S/Global}AppointmentActivityRequestBundleAdditionalLocationName" minOccurs="0"/&gt;
 *         &lt;element name="OrganizerParty" type="{http://sap.com/xi/A1S/Global}AppointmentActivityMaintainRequestBundleOrganizerParty"/&gt;
 *         &lt;element name="AttendeeParty" type="{http://sap.com/xi/A1S/Global}AppointmentActivityMaintainRequestBundleAttendeeParty" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="EmployeeResponsibleParty" type="{http://sap.com/xi/A1S/Global}AppointmentActivityMaintainRequestBundleEmployeeResponsibleParty" minOccurs="0"/&gt;
 *         &lt;element name="MainActivityParty" type="{http://sap.com/xi/A1S/Global}AppointmentActivityMaintainRequestBundleMainActivityParty" minOccurs="0"/&gt;
 *         &lt;element name="ReferenceParty" type="{http://sap.com/xi/A1S/Global}AppointmentActivityMaintainRequestBundleReferenceParty" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="ActivityUnitParty" type="{http://sap.com/xi/A1S/Global}AppointmentActivityMaintainRequestBundleActivityUnitParty" minOccurs="0"/&gt;
 *         &lt;element name="OtherParty" type="{http://sap.com/xi/A1S/Global}AppointmentActivityMaintainRequestBundleOtherParty" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="BusinessTransactionDocumentReference" type="{http://sap.com/xi/A1S/Global}AppointmentActivityBusinessTransactionDocumentReference" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Text" type="{http://sap.com/xi/A1S/Global}ActivityMaintainRequestBundleText" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="AttachmentFolder" type="{http://sap.com/xi/DocumentServices/Global}MaintenanceAttachmentFolder" minOccurs="0"/&gt;
 *         &lt;element name="WorklistItem" type="{http://sap.com/xi/A1S/Global}AppointmentActivityMaintainRequestBundleWorklistItem" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="ContactParty" type="{http://sap.com/xi/A1S/Global}AppointmentActivityMaintainRequestBundleContactParty" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="PerfectStoreIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *         &lt;element name="AvailabilityCode" type="{http://sap.com/xi/Common/DataTypes}ActivityShowAsAvailabilityCode" minOccurs="0"/&gt;
 *         &lt;element name="VisitTypeCode" type="{http://sap.com/xi/Common/DataTypes}VisitTypeCode" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="actionCode" type="{http://sap.com/xi/AP/Common/GDT}ActionCode" /&gt;
 *       &lt;attribute name="AttendeePartyListCompleteTransimissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" /&gt;
 *       &lt;attribute name="referencePartyListCompleteTransimissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" /&gt;
 *       &lt;attribute name="businessTransactionDocumentReferenceListCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" /&gt;
 *       &lt;attribute name="textListCompleteTransimissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" /&gt;
 *       &lt;attribute name="ContactPartyListCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AppointmentActivityMaintainRequestBundle_V1", namespace = "http://sap.com/xi/A1S/Global", propOrder = {
    "objectNodeSenderTechnicalID",
    "changeStateID",
    "id",
    "uuid",
    "documentTypeCode",
    "migratedDataAdaptationTypeCode",
    "name",
    "priorityCode",
    "initiatorCode",
    "informationSensitivityCode",
    "groupCode",
    "dataOriginTypeCode",
    "lifeCycleStatusCode",
    "startDateTime",
    "endDateTime",
    "fullDayIndicator",
    "completionDateTime",
    "actualStartDateTime",
    "actualEndDateTime",
    "activityFollowUpServiceRequestBlockingReasonCode",
    "groupwareItemID",
    "startGeoCoordinates",
    "endGeoCoordinates",
    "salesOrganisationID",
    "distributionChannelCode",
    "divisionCode",
    "salesTerritoryID",
    "activityListUUID",
    "activityListID",
    "locationName",
    "additionalLocationName",
    "organizerParty",
    "attendeeParty",
    "employeeResponsibleParty",
    "mainActivityParty",
    "referenceParty",
    "activityUnitParty",
    "otherParty",
    "businessTransactionDocumentReference",
    "text",
    "attachmentFolder",
    "worklistItem",
    "contactParty",
    "perfectStoreIndicator",
    "availabilityCode",
    "visitTypeCode"
})
public class AppointmentActivityMaintainRequestBundleV1 {

    @XmlElement(name = "ObjectNodeSenderTechnicalID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String objectNodeSenderTechnicalID;
    @XmlElement(name = "ChangeStateID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String changeStateID;
    @XmlElement(name = "ID")
    protected BusinessTransactionDocumentID id;
    @XmlElement(name = "UUID")
    protected UUID uuid;
    @XmlElement(name = "DocumentTypeCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String documentTypeCode;
    @XmlElement(name = "MigratedDataAdaptationTypeCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String migratedDataAdaptationTypeCode;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "PriorityCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String priorityCode;
    @XmlElement(name = "InitiatorCode")
    protected ActivityInitiatorCode initiatorCode;
    @XmlElement(name = "InformationSensitivityCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String informationSensitivityCode;
    @XmlElement(name = "GroupCode")
    protected ActivityGroupCode groupCode;
    @XmlElement(name = "DataOriginTypeCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String dataOriginTypeCode;
    @XmlElement(name = "LifeCycleStatusCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String lifeCycleStatusCode;
    @XmlElement(name = "StartDateTime")
    protected LOCALNORMALISEDDateTime startDateTime;
    @XmlElement(name = "EndDateTime")
    protected LOCALNORMALISEDDateTime endDateTime;
    @XmlElement(name = "FullDayIndicator")
    protected Boolean fullDayIndicator;
    @XmlElement(name = "CompletionDateTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar completionDateTime;
    @XmlElement(name = "ActualStartDateTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar actualStartDateTime;
    @XmlElement(name = "ActualEndDateTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar actualEndDateTime;
    @XmlElement(name = "ActivityFollowUpServiceRequestBlockingReasonCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String activityFollowUpServiceRequestBlockingReasonCode;
    @XmlElement(name = "GroupwareItemID")
    protected GroupwareItemID groupwareItemID;
    @XmlElement(name = "StartGeoCoordinates")
    protected GeoCoordinates startGeoCoordinates;
    @XmlElement(name = "EndGeoCoordinates")
    protected GeoCoordinates endGeoCoordinates;
    @XmlElement(name = "SalesOrganisationID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String salesOrganisationID;
    @XmlElement(name = "DistributionChannelCode")
    protected DistributionChannelCode distributionChannelCode;
    @XmlElement(name = "DivisionCode")
    protected DivisionCode divisionCode;
    @XmlElement(name = "SalesTerritoryID")
    protected SalesTerritoryID salesTerritoryID;
    @XmlElement(name = "ActivityListUUID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String activityListUUID;
    @XmlElement(name = "ActivityListID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String activityListID;
    @XmlElement(name = "LocationName")
    protected AppointmentActivityRequestBundleLocationName locationName;
    @XmlElement(name = "AdditionalLocationName")
    protected AppointmentActivityRequestBundleAdditionalLocationName additionalLocationName;
    @XmlElement(name = "OrganizerParty", required = true)
    protected AppointmentActivityMaintainRequestBundleOrganizerParty organizerParty;
    @XmlElement(name = "AttendeeParty")
    protected List<AppointmentActivityMaintainRequestBundleAttendeeParty> attendeeParty;
    @XmlElement(name = "EmployeeResponsibleParty")
    protected AppointmentActivityMaintainRequestBundleEmployeeResponsibleParty employeeResponsibleParty;
    @XmlElement(name = "MainActivityParty")
    protected AppointmentActivityMaintainRequestBundleMainActivityParty mainActivityParty;
    @XmlElement(name = "ReferenceParty")
    protected List<AppointmentActivityMaintainRequestBundleReferenceParty> referenceParty;
    @XmlElement(name = "ActivityUnitParty")
    protected AppointmentActivityMaintainRequestBundleActivityUnitParty activityUnitParty;
    @XmlElement(name = "OtherParty")
    protected List<AppointmentActivityMaintainRequestBundleOtherParty> otherParty;
    @XmlElement(name = "BusinessTransactionDocumentReference")
    protected List<AppointmentActivityBusinessTransactionDocumentReference> businessTransactionDocumentReference;
    @XmlElement(name = "Text")
    protected List<ActivityMaintainRequestBundleText> text;
    @XmlElement(name = "AttachmentFolder")
    protected MaintenanceAttachmentFolder attachmentFolder;
    @XmlElement(name = "WorklistItem")
    protected List<AppointmentActivityMaintainRequestBundleWorklistItem> worklistItem;
    @XmlElement(name = "ContactParty")
    protected List<AppointmentActivityMaintainRequestBundleContactParty> contactParty;
    @XmlElement(name = "PerfectStoreIndicator")
    protected Boolean perfectStoreIndicator;
    @XmlElement(name = "AvailabilityCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String availabilityCode;
    @XmlElement(name = "VisitTypeCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String visitTypeCode;
    @XmlAttribute(name = "actionCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String actionCode;
    @XmlAttribute(name = "AttendeePartyListCompleteTransimissionIndicator")
    protected Boolean attendeePartyListCompleteTransimissionIndicator;
    @XmlAttribute(name = "referencePartyListCompleteTransimissionIndicator")
    protected Boolean referencePartyListCompleteTransimissionIndicator;
    @XmlAttribute(name = "businessTransactionDocumentReferenceListCompleteTransmissionIndicator")
    protected Boolean businessTransactionDocumentReferenceListCompleteTransmissionIndicator;
    @XmlAttribute(name = "textListCompleteTransimissionIndicator")
    protected Boolean textListCompleteTransimissionIndicator;
    @XmlAttribute(name = "ContactPartyListCompleteTransmissionIndicator")
    protected Boolean contactPartyListCompleteTransmissionIndicator;

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
     * 获取id属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BusinessTransactionDocumentID }
     *     
     */
    public BusinessTransactionDocumentID getID() {
        return id;
    }

    /**
     * 设置id属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessTransactionDocumentID }
     *     
     */
    public void setID(BusinessTransactionDocumentID value) {
        this.id = value;
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
     * 获取documentTypeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentTypeCode() {
        return documentTypeCode;
    }

    /**
     * 设置documentTypeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentTypeCode(String value) {
        this.documentTypeCode = value;
    }

    /**
     * 获取migratedDataAdaptationTypeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMigratedDataAdaptationTypeCode() {
        return migratedDataAdaptationTypeCode;
    }

    /**
     * 设置migratedDataAdaptationTypeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMigratedDataAdaptationTypeCode(String value) {
        this.migratedDataAdaptationTypeCode = value;
    }

    /**
     * 获取name属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * 获取priorityCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPriorityCode() {
        return priorityCode;
    }

    /**
     * 设置priorityCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPriorityCode(String value) {
        this.priorityCode = value;
    }

    /**
     * 获取initiatorCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ActivityInitiatorCode }
     *     
     */
    public ActivityInitiatorCode getInitiatorCode() {
        return initiatorCode;
    }

    /**
     * 设置initiatorCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ActivityInitiatorCode }
     *     
     */
    public void setInitiatorCode(ActivityInitiatorCode value) {
        this.initiatorCode = value;
    }

    /**
     * 获取informationSensitivityCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInformationSensitivityCode() {
        return informationSensitivityCode;
    }

    /**
     * 设置informationSensitivityCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInformationSensitivityCode(String value) {
        this.informationSensitivityCode = value;
    }

    /**
     * 获取groupCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ActivityGroupCode }
     *     
     */
    public ActivityGroupCode getGroupCode() {
        return groupCode;
    }

    /**
     * 设置groupCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ActivityGroupCode }
     *     
     */
    public void setGroupCode(ActivityGroupCode value) {
        this.groupCode = value;
    }

    /**
     * 获取dataOriginTypeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataOriginTypeCode() {
        return dataOriginTypeCode;
    }

    /**
     * 设置dataOriginTypeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataOriginTypeCode(String value) {
        this.dataOriginTypeCode = value;
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
     * 获取startDateTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link LOCALNORMALISEDDateTime }
     *     
     */
    public LOCALNORMALISEDDateTime getStartDateTime() {
        return startDateTime;
    }

    /**
     * 设置startDateTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link LOCALNORMALISEDDateTime }
     *     
     */
    public void setStartDateTime(LOCALNORMALISEDDateTime value) {
        this.startDateTime = value;
    }

    /**
     * 获取endDateTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link LOCALNORMALISEDDateTime }
     *     
     */
    public LOCALNORMALISEDDateTime getEndDateTime() {
        return endDateTime;
    }

    /**
     * 设置endDateTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link LOCALNORMALISEDDateTime }
     *     
     */
    public void setEndDateTime(LOCALNORMALISEDDateTime value) {
        this.endDateTime = value;
    }

    /**
     * 获取fullDayIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFullDayIndicator() {
        return fullDayIndicator;
    }

    /**
     * 设置fullDayIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFullDayIndicator(Boolean value) {
        this.fullDayIndicator = value;
    }

    /**
     * 获取completionDateTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCompletionDateTime() {
        return completionDateTime;
    }

    /**
     * 设置completionDateTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCompletionDateTime(XMLGregorianCalendar value) {
        this.completionDateTime = value;
    }

    /**
     * 获取actualStartDateTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getActualStartDateTime() {
        return actualStartDateTime;
    }

    /**
     * 设置actualStartDateTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setActualStartDateTime(XMLGregorianCalendar value) {
        this.actualStartDateTime = value;
    }

    /**
     * 获取actualEndDateTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getActualEndDateTime() {
        return actualEndDateTime;
    }

    /**
     * 设置actualEndDateTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setActualEndDateTime(XMLGregorianCalendar value) {
        this.actualEndDateTime = value;
    }

    /**
     * 获取activityFollowUpServiceRequestBlockingReasonCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActivityFollowUpServiceRequestBlockingReasonCode() {
        return activityFollowUpServiceRequestBlockingReasonCode;
    }

    /**
     * 设置activityFollowUpServiceRequestBlockingReasonCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActivityFollowUpServiceRequestBlockingReasonCode(String value) {
        this.activityFollowUpServiceRequestBlockingReasonCode = value;
    }

    /**
     * 获取groupwareItemID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GroupwareItemID }
     *     
     */
    public GroupwareItemID getGroupwareItemID() {
        return groupwareItemID;
    }

    /**
     * 设置groupwareItemID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GroupwareItemID }
     *     
     */
    public void setGroupwareItemID(GroupwareItemID value) {
        this.groupwareItemID = value;
    }

    /**
     * 获取startGeoCoordinates属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GeoCoordinates }
     *     
     */
    public GeoCoordinates getStartGeoCoordinates() {
        return startGeoCoordinates;
    }

    /**
     * 设置startGeoCoordinates属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GeoCoordinates }
     *     
     */
    public void setStartGeoCoordinates(GeoCoordinates value) {
        this.startGeoCoordinates = value;
    }

    /**
     * 获取endGeoCoordinates属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GeoCoordinates }
     *     
     */
    public GeoCoordinates getEndGeoCoordinates() {
        return endGeoCoordinates;
    }

    /**
     * 设置endGeoCoordinates属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GeoCoordinates }
     *     
     */
    public void setEndGeoCoordinates(GeoCoordinates value) {
        this.endGeoCoordinates = value;
    }

    /**
     * 获取salesOrganisationID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesOrganisationID() {
        return salesOrganisationID;
    }

    /**
     * 设置salesOrganisationID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesOrganisationID(String value) {
        this.salesOrganisationID = value;
    }

    /**
     * 获取distributionChannelCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DistributionChannelCode }
     *     
     */
    public DistributionChannelCode getDistributionChannelCode() {
        return distributionChannelCode;
    }

    /**
     * 设置distributionChannelCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DistributionChannelCode }
     *     
     */
    public void setDistributionChannelCode(DistributionChannelCode value) {
        this.distributionChannelCode = value;
    }

    /**
     * 获取divisionCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DivisionCode }
     *     
     */
    public DivisionCode getDivisionCode() {
        return divisionCode;
    }

    /**
     * 设置divisionCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DivisionCode }
     *     
     */
    public void setDivisionCode(DivisionCode value) {
        this.divisionCode = value;
    }

    /**
     * 获取salesTerritoryID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SalesTerritoryID }
     *     
     */
    public SalesTerritoryID getSalesTerritoryID() {
        return salesTerritoryID;
    }

    /**
     * 设置salesTerritoryID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SalesTerritoryID }
     *     
     */
    public void setSalesTerritoryID(SalesTerritoryID value) {
        this.salesTerritoryID = value;
    }

    /**
     * 获取activityListUUID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActivityListUUID() {
        return activityListUUID;
    }

    /**
     * 设置activityListUUID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActivityListUUID(String value) {
        this.activityListUUID = value;
    }

    /**
     * 获取activityListID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActivityListID() {
        return activityListID;
    }

    /**
     * 设置activityListID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActivityListID(String value) {
        this.activityListID = value;
    }

    /**
     * 获取locationName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AppointmentActivityRequestBundleLocationName }
     *     
     */
    public AppointmentActivityRequestBundleLocationName getLocationName() {
        return locationName;
    }

    /**
     * 设置locationName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AppointmentActivityRequestBundleLocationName }
     *     
     */
    public void setLocationName(AppointmentActivityRequestBundleLocationName value) {
        this.locationName = value;
    }

    /**
     * 获取additionalLocationName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AppointmentActivityRequestBundleAdditionalLocationName }
     *     
     */
    public AppointmentActivityRequestBundleAdditionalLocationName getAdditionalLocationName() {
        return additionalLocationName;
    }

    /**
     * 设置additionalLocationName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AppointmentActivityRequestBundleAdditionalLocationName }
     *     
     */
    public void setAdditionalLocationName(AppointmentActivityRequestBundleAdditionalLocationName value) {
        this.additionalLocationName = value;
    }

    /**
     * 获取organizerParty属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AppointmentActivityMaintainRequestBundleOrganizerParty }
     *     
     */
    public AppointmentActivityMaintainRequestBundleOrganizerParty getOrganizerParty() {
        return organizerParty;
    }

    /**
     * 设置organizerParty属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AppointmentActivityMaintainRequestBundleOrganizerParty }
     *     
     */
    public void setOrganizerParty(AppointmentActivityMaintainRequestBundleOrganizerParty value) {
        this.organizerParty = value;
    }

    /**
     * Gets the value of the attendeeParty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attendeeParty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttendeeParty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AppointmentActivityMaintainRequestBundleAttendeeParty }
     * 
     * 
     */
    public List<AppointmentActivityMaintainRequestBundleAttendeeParty> getAttendeeParty() {
        if (attendeeParty == null) {
            attendeeParty = new ArrayList<AppointmentActivityMaintainRequestBundleAttendeeParty>();
        }
        return this.attendeeParty;
    }

    /**
     * 获取employeeResponsibleParty属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AppointmentActivityMaintainRequestBundleEmployeeResponsibleParty }
     *     
     */
    public AppointmentActivityMaintainRequestBundleEmployeeResponsibleParty getEmployeeResponsibleParty() {
        return employeeResponsibleParty;
    }

    /**
     * 设置employeeResponsibleParty属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AppointmentActivityMaintainRequestBundleEmployeeResponsibleParty }
     *     
     */
    public void setEmployeeResponsibleParty(AppointmentActivityMaintainRequestBundleEmployeeResponsibleParty value) {
        this.employeeResponsibleParty = value;
    }

    /**
     * 获取mainActivityParty属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AppointmentActivityMaintainRequestBundleMainActivityParty }
     *     
     */
    public AppointmentActivityMaintainRequestBundleMainActivityParty getMainActivityParty() {
        return mainActivityParty;
    }

    /**
     * 设置mainActivityParty属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AppointmentActivityMaintainRequestBundleMainActivityParty }
     *     
     */
    public void setMainActivityParty(AppointmentActivityMaintainRequestBundleMainActivityParty value) {
        this.mainActivityParty = value;
    }

    /**
     * Gets the value of the referenceParty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the referenceParty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReferenceParty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AppointmentActivityMaintainRequestBundleReferenceParty }
     * 
     * 
     */
    public List<AppointmentActivityMaintainRequestBundleReferenceParty> getReferenceParty() {
        if (referenceParty == null) {
            referenceParty = new ArrayList<AppointmentActivityMaintainRequestBundleReferenceParty>();
        }
        return this.referenceParty;
    }

    /**
     * 获取activityUnitParty属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AppointmentActivityMaintainRequestBundleActivityUnitParty }
     *     
     */
    public AppointmentActivityMaintainRequestBundleActivityUnitParty getActivityUnitParty() {
        return activityUnitParty;
    }

    /**
     * 设置activityUnitParty属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AppointmentActivityMaintainRequestBundleActivityUnitParty }
     *     
     */
    public void setActivityUnitParty(AppointmentActivityMaintainRequestBundleActivityUnitParty value) {
        this.activityUnitParty = value;
    }

    /**
     * Gets the value of the otherParty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the otherParty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOtherParty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AppointmentActivityMaintainRequestBundleOtherParty }
     * 
     * 
     */
    public List<AppointmentActivityMaintainRequestBundleOtherParty> getOtherParty() {
        if (otherParty == null) {
            otherParty = new ArrayList<AppointmentActivityMaintainRequestBundleOtherParty>();
        }
        return this.otherParty;
    }

    /**
     * Gets the value of the businessTransactionDocumentReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the businessTransactionDocumentReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBusinessTransactionDocumentReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AppointmentActivityBusinessTransactionDocumentReference }
     * 
     * 
     */
    public List<AppointmentActivityBusinessTransactionDocumentReference> getBusinessTransactionDocumentReference() {
        if (businessTransactionDocumentReference == null) {
            businessTransactionDocumentReference = new ArrayList<AppointmentActivityBusinessTransactionDocumentReference>();
        }
        return this.businessTransactionDocumentReference;
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
     * {@link ActivityMaintainRequestBundleText }
     * 
     * 
     */
    public List<ActivityMaintainRequestBundleText> getText() {
        if (text == null) {
            text = new ArrayList<ActivityMaintainRequestBundleText>();
        }
        return this.text;
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
     * Gets the value of the worklistItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the worklistItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWorklistItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AppointmentActivityMaintainRequestBundleWorklistItem }
     * 
     * 
     */
    public List<AppointmentActivityMaintainRequestBundleWorklistItem> getWorklistItem() {
        if (worklistItem == null) {
            worklistItem = new ArrayList<AppointmentActivityMaintainRequestBundleWorklistItem>();
        }
        return this.worklistItem;
    }

    /**
     * Gets the value of the contactParty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contactParty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContactParty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AppointmentActivityMaintainRequestBundleContactParty }
     * 
     * 
     */
    public List<AppointmentActivityMaintainRequestBundleContactParty> getContactParty() {
        if (contactParty == null) {
            contactParty = new ArrayList<AppointmentActivityMaintainRequestBundleContactParty>();
        }
        return this.contactParty;
    }

    /**
     * 获取perfectStoreIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPerfectStoreIndicator() {
        return perfectStoreIndicator;
    }

    /**
     * 设置perfectStoreIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPerfectStoreIndicator(Boolean value) {
        this.perfectStoreIndicator = value;
    }

    /**
     * 获取availabilityCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAvailabilityCode() {
        return availabilityCode;
    }

    /**
     * 设置availabilityCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAvailabilityCode(String value) {
        this.availabilityCode = value;
    }

    /**
     * 获取visitTypeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVisitTypeCode() {
        return visitTypeCode;
    }

    /**
     * 设置visitTypeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVisitTypeCode(String value) {
        this.visitTypeCode = value;
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
     * 获取attendeePartyListCompleteTransimissionIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAttendeePartyListCompleteTransimissionIndicator() {
        return attendeePartyListCompleteTransimissionIndicator;
    }

    /**
     * 设置attendeePartyListCompleteTransimissionIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAttendeePartyListCompleteTransimissionIndicator(Boolean value) {
        this.attendeePartyListCompleteTransimissionIndicator = value;
    }

    /**
     * 获取referencePartyListCompleteTransimissionIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReferencePartyListCompleteTransimissionIndicator() {
        return referencePartyListCompleteTransimissionIndicator;
    }

    /**
     * 设置referencePartyListCompleteTransimissionIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReferencePartyListCompleteTransimissionIndicator(Boolean value) {
        this.referencePartyListCompleteTransimissionIndicator = value;
    }

    /**
     * 获取businessTransactionDocumentReferenceListCompleteTransmissionIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBusinessTransactionDocumentReferenceListCompleteTransmissionIndicator() {
        return businessTransactionDocumentReferenceListCompleteTransmissionIndicator;
    }

    /**
     * 设置businessTransactionDocumentReferenceListCompleteTransmissionIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBusinessTransactionDocumentReferenceListCompleteTransmissionIndicator(Boolean value) {
        this.businessTransactionDocumentReferenceListCompleteTransmissionIndicator = value;
    }

    /**
     * 获取textListCompleteTransimissionIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTextListCompleteTransimissionIndicator() {
        return textListCompleteTransimissionIndicator;
    }

    /**
     * 设置textListCompleteTransimissionIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTextListCompleteTransimissionIndicator(Boolean value) {
        this.textListCompleteTransimissionIndicator = value;
    }

    /**
     * 获取contactPartyListCompleteTransmissionIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isContactPartyListCompleteTransmissionIndicator() {
        return contactPartyListCompleteTransmissionIndicator;
    }

    /**
     * 设置contactPartyListCompleteTransmissionIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setContactPartyListCompleteTransmissionIndicator(Boolean value) {
        this.contactPartyListCompleteTransmissionIndicator = value;
    }

}
