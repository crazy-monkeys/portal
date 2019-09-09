
package com.crazy.portal.bean.customer.wsdl.orgnation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>OrganisationalUnitQueryResponseManager complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="OrganisationalUnitQueryResponseManager"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ValidityPeriod" type="{http://sap.com/xi/AP/Common/GDT}CLOSED_DatePeriod" minOccurs="0"/&gt;
 *         &lt;element name="ReportingLineManagerIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *         &lt;element name="FunctionalManagerIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *         &lt;element name="BusinessPartnerInternalID" type="{http://sap.com/xi/AP/Common/GDT}BusinessPartnerInternalID" minOccurs="0"/&gt;
 *         &lt;element name="EmployeeID" type="{http://sap.com/xi/AP/Common/GDT}EmployeeID" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrganisationalUnitQueryResponseManager", namespace = "http://sap.com/xi/AP/FO/MOM/Global", propOrder = {
    "validityPeriod",
    "reportingLineManagerIndicator",
    "functionalManagerIndicator",
    "businessPartnerInternalID",
    "employeeID"
})
public class OrganisationalUnitQueryResponseManager {

    @XmlElement(name = "ValidityPeriod")
    protected CLOSEDDatePeriod validityPeriod;
    @XmlElement(name = "ReportingLineManagerIndicator")
    protected Boolean reportingLineManagerIndicator;
    @XmlElement(name = "FunctionalManagerIndicator")
    protected Boolean functionalManagerIndicator;
    @XmlElement(name = "BusinessPartnerInternalID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String businessPartnerInternalID;
    @XmlElement(name = "EmployeeID")
    protected EmployeeID employeeID;

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
     * 获取reportingLineManagerIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReportingLineManagerIndicator() {
        return reportingLineManagerIndicator;
    }

    /**
     * 设置reportingLineManagerIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReportingLineManagerIndicator(Boolean value) {
        this.reportingLineManagerIndicator = value;
    }

    /**
     * 获取functionalManagerIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFunctionalManagerIndicator() {
        return functionalManagerIndicator;
    }

    /**
     * 设置functionalManagerIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFunctionalManagerIndicator(Boolean value) {
        this.functionalManagerIndicator = value;
    }

    /**
     * 获取businessPartnerInternalID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessPartnerInternalID() {
        return businessPartnerInternalID;
    }

    /**
     * 设置businessPartnerInternalID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessPartnerInternalID(String value) {
        this.businessPartnerInternalID = value;
    }

    /**
     * 获取employeeID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EmployeeID }
     *     
     */
    public EmployeeID getEmployeeID() {
        return employeeID;
    }

    /**
     * 设置employeeID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeID }
     *     
     */
    public void setEmployeeID(EmployeeID value) {
        this.employeeID = value;
    }

}
