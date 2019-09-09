
package com.crazy.portal.bean.customer.wsdl.orgnation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>OrganisationalUnitRequestedElements complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="OrganisationalUnitRequestedElements"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="OrganisationalUnitRequestedElements" type="{http://sap.com/xi/AP/FO/MOM/Global}OrganisationalUnitQueryRequestedElements" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="OrganisationalUnitTransmissionRequestCode" type="{http://sap.com/xi/AP/Common/GDT}TransmissionRequestCode" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrganisationalUnitRequestedElements", namespace = "http://sap.com/xi/AP/FO/MOM/Global", propOrder = {
    "organisationalUnitRequestedElements"
})
public class OrganisationalUnitRequestedElements {

    @XmlElement(name = "OrganisationalUnitRequestedElements")
    protected OrganisationalUnitQueryRequestedElements organisationalUnitRequestedElements;
    @XmlAttribute(name = "OrganisationalUnitTransmissionRequestCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String organisationalUnitTransmissionRequestCode;

    /**
     * 获取organisationalUnitRequestedElements属性的值。
     * 
     * @return
     *     possible object is
     *     {@link OrganisationalUnitQueryRequestedElements }
     *     
     */
    public OrganisationalUnitQueryRequestedElements getOrganisationalUnitRequestedElements() {
        return organisationalUnitRequestedElements;
    }

    /**
     * 设置organisationalUnitRequestedElements属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link OrganisationalUnitQueryRequestedElements }
     *     
     */
    public void setOrganisationalUnitRequestedElements(OrganisationalUnitQueryRequestedElements value) {
        this.organisationalUnitRequestedElements = value;
    }

    /**
     * 获取organisationalUnitTransmissionRequestCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganisationalUnitTransmissionRequestCode() {
        return organisationalUnitTransmissionRequestCode;
    }

    /**
     * 设置organisationalUnitTransmissionRequestCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganisationalUnitTransmissionRequestCode(String value) {
        this.organisationalUnitTransmissionRequestCode = value;
    }

}
