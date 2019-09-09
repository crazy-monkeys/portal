
package com.crazy.portal.bean.customer.wsdl.orgnation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>OrganisationalUnitQueryResponseFunctions complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="OrganisationalUnitQueryResponseFunctions"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ValidityPeriod" type="{http://sap.com/xi/AP/Common/GDT}CLOSED_DatePeriod" minOccurs="0"/&gt;
 *         &lt;element name="CompanyIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *         &lt;element name="ReportingLineIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *         &lt;element name="SalesIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *         &lt;element name="ServiceIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *         &lt;element name="MarketingIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *         &lt;element name="SalesOrganisationIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *         &lt;element name="ServiceOrganisationIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *         &lt;element name="SalesOfficeIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *         &lt;element name="SalesGroupIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *         &lt;element name="CurrencyCode" type="{http://sap.com/xi/AP/Common/GDT}CurrencyCode" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrganisationalUnitQueryResponseFunctions", namespace = "http://sap.com/xi/AP/FO/MOM/Global", propOrder = {
    "validityPeriod",
    "companyIndicator",
    "reportingLineIndicator",
    "salesIndicator",
    "serviceIndicator",
    "marketingIndicator",
    "salesOrganisationIndicator",
    "serviceOrganisationIndicator",
    "salesOfficeIndicator",
    "salesGroupIndicator",
    "currencyCode"
})
public class OrganisationalUnitQueryResponseFunctions {

    @XmlElement(name = "ValidityPeriod")
    protected CLOSEDDatePeriod validityPeriod;
    @XmlElement(name = "CompanyIndicator")
    protected Boolean companyIndicator;
    @XmlElement(name = "ReportingLineIndicator")
    protected Boolean reportingLineIndicator;
    @XmlElement(name = "SalesIndicator")
    protected Boolean salesIndicator;
    @XmlElement(name = "ServiceIndicator")
    protected Boolean serviceIndicator;
    @XmlElement(name = "MarketingIndicator")
    protected Boolean marketingIndicator;
    @XmlElement(name = "SalesOrganisationIndicator")
    protected Boolean salesOrganisationIndicator;
    @XmlElement(name = "ServiceOrganisationIndicator")
    protected Boolean serviceOrganisationIndicator;
    @XmlElement(name = "SalesOfficeIndicator")
    protected Boolean salesOfficeIndicator;
    @XmlElement(name = "SalesGroupIndicator")
    protected Boolean salesGroupIndicator;
    @XmlElement(name = "CurrencyCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String currencyCode;

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
     * 获取companyIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCompanyIndicator() {
        return companyIndicator;
    }

    /**
     * 设置companyIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCompanyIndicator(Boolean value) {
        this.companyIndicator = value;
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
     * 获取salesIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSalesIndicator() {
        return salesIndicator;
    }

    /**
     * 设置salesIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSalesIndicator(Boolean value) {
        this.salesIndicator = value;
    }

    /**
     * 获取serviceIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isServiceIndicator() {
        return serviceIndicator;
    }

    /**
     * 设置serviceIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setServiceIndicator(Boolean value) {
        this.serviceIndicator = value;
    }

    /**
     * 获取marketingIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMarketingIndicator() {
        return marketingIndicator;
    }

    /**
     * 设置marketingIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMarketingIndicator(Boolean value) {
        this.marketingIndicator = value;
    }

    /**
     * 获取salesOrganisationIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSalesOrganisationIndicator() {
        return salesOrganisationIndicator;
    }

    /**
     * 设置salesOrganisationIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSalesOrganisationIndicator(Boolean value) {
        this.salesOrganisationIndicator = value;
    }

    /**
     * 获取serviceOrganisationIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isServiceOrganisationIndicator() {
        return serviceOrganisationIndicator;
    }

    /**
     * 设置serviceOrganisationIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setServiceOrganisationIndicator(Boolean value) {
        this.serviceOrganisationIndicator = value;
    }

    /**
     * 获取salesOfficeIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSalesOfficeIndicator() {
        return salesOfficeIndicator;
    }

    /**
     * 设置salesOfficeIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSalesOfficeIndicator(Boolean value) {
        this.salesOfficeIndicator = value;
    }

    /**
     * 获取salesGroupIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSalesGroupIndicator() {
        return salesGroupIndicator;
    }

    /**
     * 设置salesGroupIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSalesGroupIndicator(Boolean value) {
        this.salesGroupIndicator = value;
    }

    /**
     * 获取currencyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * 设置currencyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyCode(String value) {
        this.currencyCode = value;
    }

}
