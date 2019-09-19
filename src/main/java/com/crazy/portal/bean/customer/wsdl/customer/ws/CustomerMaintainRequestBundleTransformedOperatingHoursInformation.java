
package com.crazy.portal.bean.customer.wsdl.customer.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>CustomerMaintainRequestBundleTransformedOperatingHoursInformation complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CustomerMaintainRequestBundleTransformedOperatingHoursInformation"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ObjectNodeSenderTechnicalID" type="{http://sap.com/xi/AP/Common/GDT}ObjectNodePartyTechnicalID" minOccurs="0"/&gt;
 *         &lt;element name="RoleCode" type="{http://sap.com/xi/AP/Common/GDT}BUSINESSPARTNER_OperatingHoursRoleCode"/&gt;
 *         &lt;element name="WorkingDayCalendarCode" type="{http://sap.com/xi/AP/Common/GDT}WorkingDayCalendarCode" minOccurs="0"/&gt;
 *         &lt;element name="TimeZoneCode" type="{http://sap.com/xi/BASIS/Global}TimeZoneCode" minOccurs="0"/&gt;
 *         &lt;element name="WeekdaySelection" type="{http://sap.com/xi/AP/Common/GDT}WeekdaySelection" minOccurs="0"/&gt;
 *         &lt;element name="TimePeriod" type="{http://sap.com/xi/AP/Common/GDT}UPPEROPEN_TimePeriod" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerMaintainRequestBundleTransformedOperatingHoursInformation", namespace = "http://sap.com/xi/A1S/Global", propOrder = {
    "objectNodeSenderTechnicalID",
    "roleCode",
    "workingDayCalendarCode",
    "timeZoneCode",
    "weekdaySelection",
    "timePeriod"
})
public class CustomerMaintainRequestBundleTransformedOperatingHoursInformation {

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
    @XmlElement(name = "WeekdaySelection")
    protected WeekdaySelection weekdaySelection;
    @XmlElement(name = "TimePeriod")
    protected UPPEROPENTimePeriod timePeriod;

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
     * 获取weekdaySelection属性的值。
     * 
     * @return
     *     possible object is
     *     {@link WeekdaySelection }
     *     
     */
    public WeekdaySelection getWeekdaySelection() {
        return weekdaySelection;
    }

    /**
     * 设置weekdaySelection属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link WeekdaySelection }
     *     
     */
    public void setWeekdaySelection(WeekdaySelection value) {
        this.weekdaySelection = value;
    }

    /**
     * 获取timePeriod属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UPPEROPENTimePeriod }
     *     
     */
    public UPPEROPENTimePeriod getTimePeriod() {
        return timePeriod;
    }

    /**
     * 设置timePeriod属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UPPEROPENTimePeriod }
     *     
     */
    public void setTimePeriod(UPPEROPENTimePeriod value) {
        this.timePeriod = value;
    }

}
