
package com.crazy.portal.bean.customer.wsdl.orgnation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>OrganisationalUnitQueryResponseEmployeeAssignment complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="OrganisationalUnitQueryResponseEmployeeAssignment"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ValidityPeriod" type="{http://sap.com/xi/AP/Common/GDT}CLOSED_DatePeriod" minOccurs="0"/&gt;
 *         &lt;element name="RoleCode" type="{http://sap.com/xi/Common/DataTypes}PartyRoleCode" minOccurs="0"/&gt;
 *         &lt;element name="EmployeeUUID" type="{http://sap.com/xi/AP/Common/GDT}UUID" minOccurs="0"/&gt;
 *         &lt;element name="EmployeeInternalID" type="{http://sap.com/xi/AP/Common/GDT}BusinessPartnerInternalID" minOccurs="0"/&gt;
 *         &lt;element name="JobUUID" type="{http://sap.com/xi/AP/Common/GDT}UUID" minOccurs="0"/&gt;
 *         &lt;element name="JobID" type="{http://sap.com/xi/AP/Common/GDT}JobID" minOccurs="0"/&gt;
 *         &lt;element name="ReportingLineIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *         &lt;element name="FunctionalIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *         &lt;element name="SystemAdministrativeData" type="{http://sap.com/xi/Common/DataTypes}SystemAdministrativeData" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrganisationalUnitQueryResponseEmployeeAssignment", namespace = "http://sap.com/xi/AP/FO/MOM/Global", propOrder = {
    "validityPeriod",
    "roleCode",
    "employeeUUID",
    "employeeInternalID",
    "jobUUID",
    "jobID",
    "reportingLineIndicator",
    "functionalIndicator",
    "systemAdministrativeData"
})
public class OrganisationalUnitQueryResponseEmployeeAssignment {

    @XmlElement(name = "ValidityPeriod")
    protected CLOSEDDatePeriod validityPeriod;
    @XmlElement(name = "RoleCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String roleCode;
    @XmlElement(name = "EmployeeUUID")
    protected UUID employeeUUID;
    @XmlElement(name = "EmployeeInternalID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String employeeInternalID;
    @XmlElement(name = "JobUUID")
    protected UUID jobUUID;
    @XmlElement(name = "JobID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String jobID;
    @XmlElement(name = "ReportingLineIndicator")
    protected Boolean reportingLineIndicator;
    @XmlElement(name = "FunctionalIndicator")
    protected Boolean functionalIndicator;
    @XmlElement(name = "SystemAdministrativeData")
    protected SystemAdministrativeData systemAdministrativeData;

    /**
     * 获取validityPeriod属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CLOSEDDatePeriod }
     *     
     */
    public CLOSEDDatePeriod getValidityPeriod() {
        return validityPeriod;
    }

    /**
     * 设置validityPeriod属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CLOSEDDatePeriod }
     *     
     */
    public void setValidityPeriod(CLOSEDDatePeriod value) {
        this.validityPeriod = value;
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
     * 获取employeeUUID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UUID }
     *     
     */
    public UUID getEmployeeUUID() {
        return employeeUUID;
    }

    /**
     * 设置employeeUUID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UUID }
     *     
     */
    public void setEmployeeUUID(UUID value) {
        this.employeeUUID = value;
    }

    /**
     * 获取employeeInternalID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmployeeInternalID() {
        return employeeInternalID;
    }

    /**
     * 设置employeeInternalID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmployeeInternalID(String value) {
        this.employeeInternalID = value;
    }

    /**
     * 获取jobUUID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UUID }
     *     
     */
    public UUID getJobUUID() {
        return jobUUID;
    }

    /**
     * 设置jobUUID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UUID }
     *     
     */
    public void setJobUUID(UUID value) {
        this.jobUUID = value;
    }

    /**
     * 获取jobID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJobID() {
        return jobID;
    }

    /**
     * 设置jobID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJobID(String value) {
        this.jobID = value;
    }

    /**
     * 获取reportingLineIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReportingLineIndicator() {
        return reportingLineIndicator;
    }

    /**
     * 设置reportingLineIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReportingLineIndicator(Boolean value) {
        this.reportingLineIndicator = value;
    }

    /**
     * 获取functionalIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFunctionalIndicator() {
        return functionalIndicator;
    }

    /**
     * 设置functionalIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFunctionalIndicator(Boolean value) {
        this.functionalIndicator = value;
    }

    /**
     * 获取systemAdministrativeData属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SystemAdministrativeData }
     *     
     */
    public SystemAdministrativeData getSystemAdministrativeData() {
        return systemAdministrativeData;
    }

    /**
     * 设置systemAdministrativeData属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SystemAdministrativeData }
     *     
     */
    public void setSystemAdministrativeData(SystemAdministrativeData value) {
        this.systemAdministrativeData = value;
    }

}
