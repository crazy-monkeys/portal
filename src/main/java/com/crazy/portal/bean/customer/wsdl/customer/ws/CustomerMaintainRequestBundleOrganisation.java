
package com.crazy.portal.bean.customer.wsdl.customer.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>CustomerMaintainRequestBundleOrganisation complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CustomerMaintainRequestBundleOrganisation"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CompanyLegalFormCode" type="{http://sap.com/xi/AP/Common/GDT}CompanyLegalFormCode" minOccurs="0"/&gt;
 *         &lt;element name="FirstLineName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_MEDIUM_Name" minOccurs="0"/&gt;
 *         &lt;element name="SecondLineName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_MEDIUM_Name" minOccurs="0"/&gt;
 *         &lt;element name="ThirdLineName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_MEDIUM_Name" minOccurs="0"/&gt;
 *         &lt;element name="FourthLineName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_MEDIUM_Name" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerMaintainRequestBundleOrganisation", namespace = "http://sap.com/xi/A1S/Global", propOrder = {
    "companyLegalFormCode",
    "firstLineName",
    "secondLineName",
    "thirdLineName",
    "fourthLineName"
})
public class CustomerMaintainRequestBundleOrganisation {

    @XmlElement(name = "CompanyLegalFormCode")
    protected CompanyLegalFormCode companyLegalFormCode;
    @XmlElement(name = "FirstLineName")
    protected String firstLineName;
    @XmlElement(name = "SecondLineName")
    protected String secondLineName;
    @XmlElement(name = "ThirdLineName")
    protected String thirdLineName;
    @XmlElement(name = "FourthLineName")
    protected String fourthLineName;

    /**
     * 获取companyLegalFormCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CompanyLegalFormCode }
     *     
     */
    public CompanyLegalFormCode getCompanyLegalFormCode() {
        return companyLegalFormCode;
    }

    /**
     * 设置companyLegalFormCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CompanyLegalFormCode }
     *     
     */
    public void setCompanyLegalFormCode(CompanyLegalFormCode value) {
        this.companyLegalFormCode = value;
    }

    /**
     * 获取firstLineName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstLineName() {
        return firstLineName;
    }

    /**
     * 设置firstLineName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstLineName(String value) {
        this.firstLineName = value;
    }

    /**
     * 获取secondLineName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecondLineName() {
        return secondLineName;
    }

    /**
     * 设置secondLineName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecondLineName(String value) {
        this.secondLineName = value;
    }

    /**
     * 获取thirdLineName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getThirdLineName() {
        return thirdLineName;
    }

    /**
     * 设置thirdLineName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setThirdLineName(String value) {
        this.thirdLineName = value;
    }

    /**
     * 获取fourthLineName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFourthLineName() {
        return fourthLineName;
    }

    /**
     * 设置fourthLineName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFourthLineName(String value) {
        this.fourthLineName = value;
    }

}
