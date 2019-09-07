
package com.crazy.portal.bean.customer.wsdl.employee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>EncryptedFormattedPostalAddress complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="EncryptedFormattedPostalAddress"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="FirstLineDescription" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_MEDIUM_Description"/&gt;
 *         &lt;element name="SecondLineDescription" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_MEDIUM_Description" minOccurs="0"/&gt;
 *         &lt;element name="ThirdLineDescription" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_MEDIUM_Description" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EncryptedFormattedPostalAddress", namespace = "http://sap.com/xi/AP/Common/GDT", propOrder = {
    "firstLineDescription",
    "secondLineDescription",
    "thirdLineDescription"
})
public class EncryptedFormattedPostalAddress {

    @XmlElement(name = "FirstLineDescription", required = true)
    protected String firstLineDescription;
    @XmlElement(name = "SecondLineDescription")
    protected String secondLineDescription;
    @XmlElement(name = "ThirdLineDescription")
    protected String thirdLineDescription;

    /**
     * 获取firstLineDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstLineDescription() {
        return firstLineDescription;
    }

    /**
     * 设置firstLineDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstLineDescription(String value) {
        this.firstLineDescription = value;
    }

    /**
     * 获取secondLineDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecondLineDescription() {
        return secondLineDescription;
    }

    /**
     * 设置secondLineDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecondLineDescription(String value) {
        this.secondLineDescription = value;
    }

    /**
     * 获取thirdLineDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getThirdLineDescription() {
        return thirdLineDescription;
    }

    /**
     * 设置thirdLineDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setThirdLineDescription(String value) {
        this.thirdLineDescription = value;
    }

}
