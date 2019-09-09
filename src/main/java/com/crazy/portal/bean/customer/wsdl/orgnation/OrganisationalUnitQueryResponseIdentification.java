
package com.crazy.portal.bean.customer.wsdl.orgnation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>OrganisationalUnitQueryResponseIdentification complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="OrganisationalUnitQueryResponseIdentification"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ValidityPeriod" type="{http://sap.com/xi/AP/Common/GDT}CLOSED_DatePeriod" minOccurs="0"/&gt;
 *         &lt;element name="IDTypeCode" type="{http://sap.com/xi/AP/Common/GDT}PartyIdentifierTypeCode" minOccurs="0"/&gt;
 *         &lt;element name="ID" type="{http://sap.com/xi/AP/Common/GDT}NOALPHANUMERICCONVERSION_PartyID" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrganisationalUnitQueryResponseIdentification", namespace = "http://sap.com/xi/AP/FO/MOM/Global", propOrder = {
    "validityPeriod",
    "idTypeCode",
    "id"
})
public class OrganisationalUnitQueryResponseIdentification {

    @XmlElement(name = "ValidityPeriod")
    protected CLOSEDDatePeriod validityPeriod;
    @XmlElement(name = "IDTypeCode")
    protected PartyIdentifierTypeCode idTypeCode;
    @XmlElement(name = "ID")
    protected NOALPHANUMERICCONVERSIONPartyID id;

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
     * 获取idTypeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PartyIdentifierTypeCode }
     *     
     */
    public PartyIdentifierTypeCode getIDTypeCode() {
        return idTypeCode;
    }

    /**
     * 设置idTypeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PartyIdentifierTypeCode }
     *     
     */
    public void setIDTypeCode(PartyIdentifierTypeCode value) {
        this.idTypeCode = value;
    }

    /**
     * 获取id属性的值。
     * 
     * @return
     *     possible object is
     *     {@link NOALPHANUMERICCONVERSIONPartyID }
     *     
     */
    public NOALPHANUMERICCONVERSIONPartyID getID() {
        return id;
    }

    /**
     * 设置id属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link NOALPHANUMERICCONVERSIONPartyID }
     *     
     */
    public void setID(NOALPHANUMERICCONVERSIONPartyID value) {
        this.id = value;
    }

}
