
package com.crazy.portal.bean.customer.wsdl.customer.ws.detail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>BO_ExtendCustomerReadByIDResponseToCustomer complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="BO_ExtendCustomerReadByIDResponseToCustomer"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="UUID" type="{http://sap.com/xi/AP/Common/GDT}UUID" minOccurs="0"/&gt;
 *         &lt;element name="InternalID" type="{http://sap.com/xi/AP/Common/GDT}BusinessPartnerInternalID" minOccurs="0"/&gt;
 *         &lt;element name="InternalIDNoAlphaConversion" type="{http://sap.com/xi/AP/FO/BusinessPartner/Global}BusinessPartnerInternalIDNoAlphaConversion" minOccurs="0"/&gt;
 *         &lt;element name="CategoryCode" type="{http://sap.com/xi/AP/Common/GDT}BusinessPartnerCategoryCode" minOccurs="0"/&gt;
 *         &lt;element name="CategoryName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_LONG_Name" minOccurs="0"/&gt;
 *         &lt;element name="ActsAsOrganisationalCentreIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *         &lt;element name="MarketingLeadIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *         &lt;element name="PartialSystemAdministrativeData" type="{http://sap.com/xi/AP/Common/GDT}SystemAdministrativeData" minOccurs="0"/&gt;
 *         &lt;element name="Status" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerReadByIDResponseToCustomerStatus" minOccurs="0"/&gt;
 *         &lt;element name="BusinessPurposeCompletedIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BO_ExtendCustomerReadByIDResponseToCustomer", propOrder = {
    "uuid",
    "internalID",
    "internalIDNoAlphaConversion",
    "categoryCode",
    "categoryName",
    "actsAsOrganisationalCentreIndicator",
    "marketingLeadIndicator",
    "partialSystemAdministrativeData",
    "status",
    "businessPurposeCompletedIndicator"
})
public class BOExtendCustomerReadByIDResponseToCustomer {

    @XmlElement(name = "UUID")
    protected UUID uuid;
    @XmlElement(name = "InternalID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String internalID;
    @XmlElement(name = "InternalIDNoAlphaConversion")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String internalIDNoAlphaConversion;
    @XmlElement(name = "CategoryCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String categoryCode;
    @XmlElement(name = "CategoryName")
    protected String categoryName;
    @XmlElement(name = "ActsAsOrganisationalCentreIndicator")
    protected Boolean actsAsOrganisationalCentreIndicator;
    @XmlElement(name = "MarketingLeadIndicator")
    protected Boolean marketingLeadIndicator;
    @XmlElement(name = "PartialSystemAdministrativeData")
    protected SystemAdministrativeData partialSystemAdministrativeData;
    @XmlElement(name = "Status")
    protected BOExtendCustomerReadByIDResponseToCustomerStatus status;
    @XmlElement(name = "BusinessPurposeCompletedIndicator")
    protected Boolean businessPurposeCompletedIndicator;

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
     * 获取internalIDNoAlphaConversion属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInternalIDNoAlphaConversion() {
        return internalIDNoAlphaConversion;
    }

    /**
     * 设置internalIDNoAlphaConversion属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInternalIDNoAlphaConversion(String value) {
        this.internalIDNoAlphaConversion = value;
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
     * 获取categoryName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * 设置categoryName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryName(String value) {
        this.categoryName = value;
    }

    /**
     * 获取actsAsOrganisationalCentreIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isActsAsOrganisationalCentreIndicator() {
        return actsAsOrganisationalCentreIndicator;
    }

    /**
     * 设置actsAsOrganisationalCentreIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setActsAsOrganisationalCentreIndicator(Boolean value) {
        this.actsAsOrganisationalCentreIndicator = value;
    }

    /**
     * 获取marketingLeadIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMarketingLeadIndicator() {
        return marketingLeadIndicator;
    }

    /**
     * 设置marketingLeadIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMarketingLeadIndicator(Boolean value) {
        this.marketingLeadIndicator = value;
    }

    /**
     * 获取partialSystemAdministrativeData属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SystemAdministrativeData }
     *     
     */
    public SystemAdministrativeData getPartialSystemAdministrativeData() {
        return partialSystemAdministrativeData;
    }

    /**
     * 设置partialSystemAdministrativeData属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SystemAdministrativeData }
     *     
     */
    public void setPartialSystemAdministrativeData(SystemAdministrativeData value) {
        this.partialSystemAdministrativeData = value;
    }

    /**
     * 获取status属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BOExtendCustomerReadByIDResponseToCustomerStatus }
     *     
     */
    public BOExtendCustomerReadByIDResponseToCustomerStatus getStatus() {
        return status;
    }

    /**
     * 设置status属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BOExtendCustomerReadByIDResponseToCustomerStatus }
     *     
     */
    public void setStatus(BOExtendCustomerReadByIDResponseToCustomerStatus value) {
        this.status = value;
    }

    /**
     * 获取businessPurposeCompletedIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBusinessPurposeCompletedIndicator() {
        return businessPurposeCompletedIndicator;
    }

    /**
     * 设置businessPurposeCompletedIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBusinessPurposeCompletedIndicator(Boolean value) {
        this.businessPurposeCompletedIndicator = value;
    }

}
