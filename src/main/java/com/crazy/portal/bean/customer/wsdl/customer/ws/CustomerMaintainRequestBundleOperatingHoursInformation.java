
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


/**
 * <p>CustomerMaintainRequestBundleOperatingHoursInformation complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CustomerMaintainRequestBundleOperatingHoursInformation"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ObjectNodeSenderTechnicalID" type="{http://sap.com/xi/AP/Common/GDT}ObjectNodePartyTechnicalID" minOccurs="0"/&gt;
 *         &lt;element name="RoleCode" type="{http://sap.com/xi/AP/Common/GDT}BUSINESSPARTNER_OperatingHoursRoleCode"/&gt;
 *         &lt;element name="WorkingDayCalendarCode" type="{http://sap.com/xi/AP/Common/GDT}WorkingDayCalendarCode" minOccurs="0"/&gt;
 *         &lt;element name="TimeZoneCode" type="{http://sap.com/xi/BASIS/Global}TimeZoneCode" minOccurs="0"/&gt;
 *         &lt;element name="RecurringDayProgramme" type="{http://sap.com/xi/A1S/Global}CustomerMaintainRequestBundleOperatingHoursInformationRecurringDayProgramme" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="actionCode" type="{http://sap.com/xi/AP/Common/GDT}ActionCode" /&gt;
 *       &lt;attribute name="recurringDayProgrammeListCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerMaintainRequestBundleOperatingHoursInformation", namespace = "http://sap.com/xi/A1S/Global", propOrder = {
    "objectNodeSenderTechnicalID",
    "roleCode",
    "workingDayCalendarCode",
    "timeZoneCode",
    "recurringDayProgramme"
})
public class CustomerMaintainRequestBundleOperatingHoursInformation {

    @XmlElement(name = "ObjectNodeSenderTechnicalID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String objectNodeSenderTechnicalID;
    @XmlElement(name = "RoleCode", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String roleCode;
    @XmlElement(name = "WorkingDayCalendarCode")
    protected WorkingDayCalendarCode workingDayCalendarCode;
    @XmlElement(name = "TimeZoneCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String timeZoneCode;
    @XmlElement(name = "RecurringDayProgramme")
    protected List<CustomerMaintainRequestBundleOperatingHoursInformationRecurringDayProgramme> recurringDayProgramme;
    @XmlAttribute(name = "actionCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String actionCode;
    @XmlAttribute(name = "recurringDayProgrammeListCompleteTransmissionIndicator")
    protected Boolean recurringDayProgrammeListCompleteTransmissionIndicator;

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
     * 获取roleCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * 设置roleCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoleCode(String value) {
        this.roleCode = value;
    }

    /**
     * 获取workingDayCalendarCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link WorkingDayCalendarCode }
     *     
     */
    public WorkingDayCalendarCode getWorkingDayCalendarCode() {
        return workingDayCalendarCode;
    }

    /**
     * 设置workingDayCalendarCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link WorkingDayCalendarCode }
     *     
     */
    public void setWorkingDayCalendarCode(WorkingDayCalendarCode value) {
        this.workingDayCalendarCode = value;
    }

    /**
     * 获取timeZoneCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeZoneCode() {
        return timeZoneCode;
    }

    /**
     * 设置timeZoneCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeZoneCode(String value) {
        this.timeZoneCode = value;
    }

    /**
     * Gets the value of the recurringDayProgramme property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the recurringDayProgramme property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRecurringDayProgramme().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustomerMaintainRequestBundleOperatingHoursInformationRecurringDayProgramme }
     * 
     * 
     */
    public List<CustomerMaintainRequestBundleOperatingHoursInformationRecurringDayProgramme> getRecurringDayProgramme() {
        if (recurringDayProgramme == null) {
            recurringDayProgramme = new ArrayList<CustomerMaintainRequestBundleOperatingHoursInformationRecurringDayProgramme>();
        }
        return this.recurringDayProgramme;
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
     * 获取recurringDayProgrammeListCompleteTransmissionIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRecurringDayProgrammeListCompleteTransmissionIndicator() {
        return recurringDayProgrammeListCompleteTransmissionIndicator;
    }

    /**
     * 设置recurringDayProgrammeListCompleteTransmissionIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRecurringDayProgrammeListCompleteTransmissionIndicator(Boolean value) {
        this.recurringDayProgrammeListCompleteTransmissionIndicator = value;
    }

}
