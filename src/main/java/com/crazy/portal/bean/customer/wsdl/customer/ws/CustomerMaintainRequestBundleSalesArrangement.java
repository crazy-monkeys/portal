
package com.crazy.portal.bean.customer.wsdl.customer.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>CustomerMaintainRequestBundleSalesArrangement complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CustomerMaintainRequestBundleSalesArrangement"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ObjectNodeSenderTechnicalID" type="{http://sap.com/xi/AP/Common/GDT}ObjectNodePartyTechnicalID" minOccurs="0"/&gt;
 *         &lt;element name="SalesOrganisationID" type="{http://sap.com/xi/AP/Common/GDT}OrganisationalCentreID" minOccurs="0"/&gt;
 *         &lt;element name="SalesOfficeID" type="{http://sap.com/xi/AP/Common/GDT}OrganisationalCentreID" minOccurs="0"/&gt;
 *         &lt;element name="SalesGroupID" type="{http://sap.com/xi/AP/Common/GDT}OrganisationalCentreID" minOccurs="0"/&gt;
 *         &lt;element name="DistributionChannelCode" type="{http://sap.com/xi/AP/Common/GDT}DistributionChannelCode" minOccurs="0"/&gt;
 *         &lt;element name="DivisionCode" type="{http://sap.com/xi/AP/Common/GDT}DivisionCode" minOccurs="0"/&gt;
 *         &lt;element name="Incoterms" type="{http://sap.com/xi/AP/Common/GDT}Incoterms" minOccurs="0"/&gt;
 *         &lt;element name="DeliveryPriorityCode" type="{http://sap.com/xi/Common/DataTypes}DeliveryPriorityCode" minOccurs="0"/&gt;
 *         &lt;element name="CompleteDeliveryRequestedIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *         &lt;element name="CurrencyCode" type="{http://sap.com/xi/AP/Common/GDT}CurrencyCode" minOccurs="0"/&gt;
 *         &lt;element name="CustomerGroupCode" type="{http://sap.com/xi/AP/Common/GDT}CustomerGroupCode" minOccurs="0"/&gt;
 *         &lt;element name="CashDiscountTermsCode" type="{http://sap.com/xi/AP/Common/GDT}CashDiscountTermsCode" minOccurs="0"/&gt;
 *         &lt;element name="SalesArrangementUUID" type="{http://sap.com/xi/AP/Common/GDT}UUID" minOccurs="0"/&gt;
 *         &lt;element name="CustomerMaintainRequestBundleBlockingReasons" type="{http://sap.com/xi/A1S/Global}CustomerMaintainRequestBundleBlockingReasons" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="actionCode" type="{http://sap.com/xi/AP/Common/GDT}ActionCode" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerMaintainRequestBundleSalesArrangement", namespace = "http://sap.com/xi/A1S/Global", propOrder = {
    "objectNodeSenderTechnicalID",
    "salesOrganisationID",
    "salesOfficeID",
    "salesGroupID",
    "distributionChannelCode",
    "divisionCode",
    "incoterms",
    "deliveryPriorityCode",
    "completeDeliveryRequestedIndicator",
    "currencyCode",
    "customerGroupCode",
    "cashDiscountTermsCode",
    "salesArrangementUUID",
    "customerMaintainRequestBundleBlockingReasons"
})
public class CustomerMaintainRequestBundleSalesArrangement {

    @XmlElement(name = "ObjectNodeSenderTechnicalID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String objectNodeSenderTechnicalID;
    @XmlElement(name = "SalesOrganisationID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String salesOrganisationID;
    @XmlElement(name = "SalesOfficeID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String salesOfficeID;
    @XmlElement(name = "SalesGroupID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String salesGroupID;
    @XmlElement(name = "DistributionChannelCode")
    protected DistributionChannelCode distributionChannelCode;
    @XmlElement(name = "DivisionCode")
    protected DivisionCode divisionCode;
    @XmlElement(name = "Incoterms")
    protected Incoterms incoterms;
    @XmlElement(name = "DeliveryPriorityCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String deliveryPriorityCode;
    @XmlElement(name = "CompleteDeliveryRequestedIndicator")
    protected Boolean completeDeliveryRequestedIndicator;
    @XmlElement(name = "CurrencyCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String currencyCode;
    @XmlElement(name = "CustomerGroupCode")
    protected CustomerGroupCode customerGroupCode;
    @XmlElement(name = "CashDiscountTermsCode")
    protected CashDiscountTermsCode cashDiscountTermsCode;
    @XmlElement(name = "SalesArrangementUUID")
    protected UUID salesArrangementUUID;
    @XmlElement(name = "CustomerMaintainRequestBundleBlockingReasons")
    protected CustomerMaintainRequestBundleBlockingReasons customerMaintainRequestBundleBlockingReasons;
    @XmlAttribute(name = "actionCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String actionCode;

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
     * 获取salesOfficeID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesOfficeID() {
        return salesOfficeID;
    }

    /**
     * 设置salesOfficeID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesOfficeID(String value) {
        this.salesOfficeID = value;
    }

    /**
     * 获取salesGroupID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesGroupID() {
        return salesGroupID;
    }

    /**
     * 设置salesGroupID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesGroupID(String value) {
        this.salesGroupID = value;
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
     * 获取incoterms属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Incoterms }
     *     
     */
    public Incoterms getIncoterms() {
        return incoterms;
    }

    /**
     * 设置incoterms属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Incoterms }
     *     
     */
    public void setIncoterms(Incoterms value) {
        this.incoterms = value;
    }

    /**
     * 获取deliveryPriorityCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryPriorityCode() {
        return deliveryPriorityCode;
    }

    /**
     * 设置deliveryPriorityCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryPriorityCode(String value) {
        this.deliveryPriorityCode = value;
    }

    /**
     * 获取completeDeliveryRequestedIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCompleteDeliveryRequestedIndicator() {
        return completeDeliveryRequestedIndicator;
    }

    /**
     * 设置completeDeliveryRequestedIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCompleteDeliveryRequestedIndicator(Boolean value) {
        this.completeDeliveryRequestedIndicator = value;
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

    /**
     * 获取customerGroupCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CustomerGroupCode }
     *     
     */
    public CustomerGroupCode getCustomerGroupCode() {
        return customerGroupCode;
    }

    /**
     * 设置customerGroupCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerGroupCode }
     *     
     */
    public void setCustomerGroupCode(CustomerGroupCode value) {
        this.customerGroupCode = value;
    }

    /**
     * 获取cashDiscountTermsCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CashDiscountTermsCode }
     *     
     */
    public CashDiscountTermsCode getCashDiscountTermsCode() {
        return cashDiscountTermsCode;
    }

    /**
     * 设置cashDiscountTermsCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CashDiscountTermsCode }
     *     
     */
    public void setCashDiscountTermsCode(CashDiscountTermsCode value) {
        this.cashDiscountTermsCode = value;
    }

    /**
     * 获取salesArrangementUUID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UUID }
     *     
     */
    public UUID getSalesArrangementUUID() {
        return salesArrangementUUID;
    }

    /**
     * 设置salesArrangementUUID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UUID }
     *     
     */
    public void setSalesArrangementUUID(UUID value) {
        this.salesArrangementUUID = value;
    }

    /**
     * 获取customerMaintainRequestBundleBlockingReasons属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CustomerMaintainRequestBundleBlockingReasons }
     *     
     */
    public CustomerMaintainRequestBundleBlockingReasons getCustomerMaintainRequestBundleBlockingReasons() {
        return customerMaintainRequestBundleBlockingReasons;
    }

    /**
     * 设置customerMaintainRequestBundleBlockingReasons属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerMaintainRequestBundleBlockingReasons }
     *     
     */
    public void setCustomerMaintainRequestBundleBlockingReasons(CustomerMaintainRequestBundleBlockingReasons value) {
        this.customerMaintainRequestBundleBlockingReasons = value;
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

}
