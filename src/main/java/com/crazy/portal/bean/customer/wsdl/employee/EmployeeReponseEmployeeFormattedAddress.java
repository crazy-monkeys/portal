
package com.crazy.portal.bean.customer.wsdl.employee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>EmployeeReponseEmployeeFormattedAddress complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="EmployeeReponseEmployeeFormattedAddress"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="FormattedAddressDescription" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_MEDIUM_Description" minOccurs="0"/&gt;
 *         &lt;element name="FormattedPostalAddressDescription" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_MEDIUM_Description" minOccurs="0"/&gt;
 *         &lt;element name="FormattedAddress" type="{http://sap.com/xi/AP/Common/GDT}EncryptedFormattedAddress" minOccurs="0"/&gt;
 *         &lt;element name="FormattedPostalAddress" type="{http://sap.com/xi/AP/Common/GDT}EncryptedFormattedPostalAddress" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmployeeReponseEmployeeFormattedAddress", propOrder = {
    "formattedAddressDescription",
    "formattedPostalAddressDescription",
    "formattedAddress",
    "formattedPostalAddress"
})
public class EmployeeReponseEmployeeFormattedAddress {

    @XmlElement(name = "FormattedAddressDescription")
    protected String formattedAddressDescription;
    @XmlElement(name = "FormattedPostalAddressDescription")
    protected String formattedPostalAddressDescription;
    @XmlElement(name = "FormattedAddress")
    protected EncryptedFormattedAddress formattedAddress;
    @XmlElement(name = "FormattedPostalAddress")
    protected EncryptedFormattedPostalAddress formattedPostalAddress;

    /**
     * 获取formattedAddressDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormattedAddressDescription() {
        return formattedAddressDescription;
    }

    /**
     * 设置formattedAddressDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormattedAddressDescription(String value) {
        this.formattedAddressDescription = value;
    }

    /**
     * 获取formattedPostalAddressDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormattedPostalAddressDescription() {
        return formattedPostalAddressDescription;
    }

    /**
     * 设置formattedPostalAddressDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormattedPostalAddressDescription(String value) {
        this.formattedPostalAddressDescription = value;
    }

    /**
     * 获取formattedAddress属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EncryptedFormattedAddress }
     *     
     */
    public EncryptedFormattedAddress getFormattedAddress() {
        return formattedAddress;
    }

    /**
     * 设置formattedAddress属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EncryptedFormattedAddress }
     *     
     */
    public void setFormattedAddress(EncryptedFormattedAddress value) {
        this.formattedAddress = value;
    }

    /**
     * 获取formattedPostalAddress属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EncryptedFormattedPostalAddress }
     *     
     */
    public EncryptedFormattedPostalAddress getFormattedPostalAddress() {
        return formattedPostalAddress;
    }

    /**
     * 设置formattedPostalAddress属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EncryptedFormattedPostalAddress }
     *     
     */
    public void setFormattedPostalAddress(EncryptedFormattedPostalAddress value) {
        this.formattedPostalAddress = value;
    }

}
