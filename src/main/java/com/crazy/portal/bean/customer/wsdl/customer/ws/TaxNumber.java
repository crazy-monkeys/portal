
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
 * <p>TaxNumber complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="TaxNumber"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ObjectNodeSenderTechnicalID" type="{http://sap.com/xi/AP/Common/GDT}ObjectNodePartyTechnicalID" minOccurs="0"/&gt;
 *         &lt;element name="UUID" type="{http://sap.com/xi/AP/Common/GDT}UUID" minOccurs="0"/&gt;
 *         &lt;element name="CountryCode" type="{http://sap.com/xi/AP/Common/GDT}CountryCode" minOccurs="0"/&gt;
 *         &lt;element name="TaxIdentificationNumber" type="{http://sap.com/xi/AP/Common/GDT}TaxIdentificationNumberTypeCode" minOccurs="0"/&gt;
 *         &lt;element name="PartyTaxID" type="{http://sap.com/xi/AP/Common/GDT}PartyTaxID" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="TaxUsageListTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" /&gt;
 *       &lt;attribute name="ActionCode" type="{http://sap.com/xi/AP/Common/GDT}ActionCode" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TaxNumber", namespace = "http://sap.com/xi/A1S/Global", propOrder = {
    "objectNodeSenderTechnicalID",
    "uuid",
    "countryCode",
    "taxIdentificationNumber",
    "partyTaxID"
})
public class TaxNumber {

    @XmlElement(name = "ObjectNodeSenderTechnicalID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String objectNodeSenderTechnicalID;
    @XmlElement(name = "UUID")
    protected UUID uuid;
    @XmlElement(name = "CountryCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String countryCode;
    @XmlElement(name = "TaxIdentificationNumber")
    protected TaxIdentificationNumberTypeCode taxIdentificationNumber;
    @XmlElement(name = "PartyTaxID")
    protected PartyTaxID partyTaxID;
    @XmlAttribute(name = "TaxUsageListTransmissionIndicator")
    protected Boolean taxUsageListTransmissionIndicator;
    @XmlAttribute(name = "ActionCode")
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
     * 获取countryCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * 设置countryCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryCode(String value) {
        this.countryCode = value;
    }

    /**
     * 获取taxIdentificationNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TaxIdentificationNumberTypeCode }
     *     
     */
    public TaxIdentificationNumberTypeCode getTaxIdentificationNumber() {
        return taxIdentificationNumber;
    }

    /**
     * 设置taxIdentificationNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TaxIdentificationNumberTypeCode }
     *     
     */
    public void setTaxIdentificationNumber(TaxIdentificationNumberTypeCode value) {
        this.taxIdentificationNumber = value;
    }

    /**
     * 获取partyTaxID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PartyTaxID }
     *     
     */
    public PartyTaxID getPartyTaxID() {
        return partyTaxID;
    }

    /**
     * 设置partyTaxID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PartyTaxID }
     *     
     */
    public void setPartyTaxID(PartyTaxID value) {
        this.partyTaxID = value;
    }

    /**
     * 获取taxUsageListTransmissionIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTaxUsageListTransmissionIndicator() {
        return taxUsageListTransmissionIndicator;
    }

    /**
     * 设置taxUsageListTransmissionIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTaxUsageListTransmissionIndicator(Boolean value) {
        this.taxUsageListTransmissionIndicator = value;
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
