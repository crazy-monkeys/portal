
package com.crazy.portal.bean.customer.wsdl.employee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>EmployeeReponseEmployeeWorkplaceAddress complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="EmployeeReponseEmployeeWorkplaceAddress"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="OrganisationalPostalAddress" type="{http://sap.com/xi/A1S/Global}EmployeeReponseEmployeeOrganisationalAddressPostalAddress" minOccurs="0"/&gt;
 *         &lt;element name="Workplace" type="{http://sap.com/xi/A1S/Global}EmployeeReponseEmployeeWorkplaceAddressWorkplace" minOccurs="0"/&gt;
 *         &lt;element name="EmailURI" type="{http://sap.com/xi/AP/Common/GDT}ENCRYPTED_MEDIUM_EmailURI" minOccurs="0"/&gt;
 *         &lt;element name="Fax" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_NUMBER_SHORT_Description" minOccurs="0"/&gt;
 *         &lt;element name="Phone" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_NUMBER_SHORT_Description" minOccurs="0"/&gt;
 *         &lt;element name="MobilePhone" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_NUMBER_SHORT_Description" minOccurs="0"/&gt;
 *         &lt;element name="FormattedAddress" type="{http://sap.com/xi/A1S/Global}EmployeeReponseEmployeeFormattedAddress" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmployeeReponseEmployeeWorkplaceAddress", propOrder = {
    "organisationalPostalAddress",
    "workplace",
    "emailURI",
    "fax",
    "phone",
    "mobilePhone",
    "formattedAddress"
})
public class EmployeeReponseEmployeeWorkplaceAddress {

    @XmlElement(name = "OrganisationalPostalAddress")
    protected EmployeeReponseEmployeeOrganisationalAddressPostalAddress organisationalPostalAddress;
    @XmlElement(name = "Workplace")
    protected EmployeeReponseEmployeeWorkplaceAddressWorkplace workplace;
    @XmlElement(name = "EmailURI")
    protected ENCRYPTEDMEDIUMEmailURI emailURI;
    @XmlElement(name = "Fax")
    protected String fax;
    @XmlElement(name = "Phone")
    protected String phone;
    @XmlElement(name = "MobilePhone")
    protected String mobilePhone;
    @XmlElement(name = "FormattedAddress")
    protected EmployeeReponseEmployeeFormattedAddress formattedAddress;

    /**
     * 获取organisationalPostalAddress属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EmployeeReponseEmployeeOrganisationalAddressPostalAddress }
     *     
     */
    public EmployeeReponseEmployeeOrganisationalAddressPostalAddress getOrganisationalPostalAddress() {
        return organisationalPostalAddress;
    }

    /**
     * 设置organisationalPostalAddress属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeReponseEmployeeOrganisationalAddressPostalAddress }
     *     
     */
    public void setOrganisationalPostalAddress(EmployeeReponseEmployeeOrganisationalAddressPostalAddress value) {
        this.organisationalPostalAddress = value;
    }

    /**
     * 获取workplace属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EmployeeReponseEmployeeWorkplaceAddressWorkplace }
     *     
     */
    public EmployeeReponseEmployeeWorkplaceAddressWorkplace getWorkplace() {
        return workplace;
    }

    /**
     * 设置workplace属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeReponseEmployeeWorkplaceAddressWorkplace }
     *     
     */
    public void setWorkplace(EmployeeReponseEmployeeWorkplaceAddressWorkplace value) {
        this.workplace = value;
    }

    /**
     * 获取emailURI属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ENCRYPTEDMEDIUMEmailURI }
     *     
     */
    public ENCRYPTEDMEDIUMEmailURI getEmailURI() {
        return emailURI;
    }

    /**
     * 设置emailURI属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ENCRYPTEDMEDIUMEmailURI }
     *     
     */
    public void setEmailURI(ENCRYPTEDMEDIUMEmailURI value) {
        this.emailURI = value;
    }

    /**
     * 获取fax属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFax() {
        return fax;
    }

    /**
     * 设置fax属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFax(String value) {
        this.fax = value;
    }

    /**
     * 获取phone属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置phone属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhone(String value) {
        this.phone = value;
    }

    /**
     * 获取mobilePhone属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * 设置mobilePhone属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobilePhone(String value) {
        this.mobilePhone = value;
    }

    /**
     * 获取formattedAddress属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EmployeeReponseEmployeeFormattedAddress }
     *     
     */
    public EmployeeReponseEmployeeFormattedAddress getFormattedAddress() {
        return formattedAddress;
    }

    /**
     * 设置formattedAddress属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeReponseEmployeeFormattedAddress }
     *     
     */
    public void setFormattedAddress(EmployeeReponseEmployeeFormattedAddress value) {
        this.formattedAddress = value;
    }

}
