
package com.crazy.portal.bean.customer.wsdl.customer.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>CustomerMaintainRequestBundleAddress complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CustomerMaintainRequestBundleAddress"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CorrespondenceLanguageCode" type="{http://sap.com/xi/BASIS/Global}LanguageCode" minOccurs="0"/&gt;
 *         &lt;element name="PreferredCommunicationMediumTypeCode" type="{http://sap.com/xi/AP/Common/GDT}CommunicationMediumTypeCode" minOccurs="0"/&gt;
 *         &lt;element name="GeographicalLocation" type="{http://sap.com/xi/AP/Common/GDT}GeoCoordinates" minOccurs="0"/&gt;
 *         &lt;element name="Email" type="{http://sap.com/xi/A1S/Global}CustomerMaintainRequestBundleEmail" minOccurs="0"/&gt;
 *         &lt;element name="FacsimileFormattedNumberDescription" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_NUMBER_SHORT_Description" minOccurs="0"/&gt;
 *         &lt;element name="PostalAddress" type="{http://sap.com/xi/A1S/Global}CustomerMaintainRequestBundleAddressPostalAddress" minOccurs="0"/&gt;
 *         &lt;element name="DefaultMobilePhone" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_NUMBER_SHORT_Description" minOccurs="0"/&gt;
 *         &lt;element name="DefaultConventionalPhone" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_NUMBER_SHORT_Description" minOccurs="0"/&gt;
 *         &lt;element name="Telephone" type="{http://sap.com/xi/A1S/Global}CustomerMaintainRequestBundleAddressTelephone" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="WebURI" type="{http://sap.com/xi/AP/Common/GDT}WebURI" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="actionCode" type="{http://sap.com/xi/AP/Common/GDT}ActionCode" /&gt;
 *       &lt;attribute name="telephoneListCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerMaintainRequestBundleAddress", namespace = "http://sap.com/xi/A1S/Global", propOrder = {
    "correspondenceLanguageCode",
    "preferredCommunicationMediumTypeCode",
    "geographicalLocation",
    "email",
    "facsimileFormattedNumberDescription",
    "postalAddress",
    "defaultMobilePhone",
    "defaultConventionalPhone",
    "telephone",
    "webURI"
})
public class CustomerMaintainRequestBundleAddress {

    @XmlElement(name = "CorrespondenceLanguageCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "language")
    protected String correspondenceLanguageCode;
    @XmlElement(name = "PreferredCommunicationMediumTypeCode")
    protected CommunicationMediumTypeCode preferredCommunicationMediumTypeCode;
    @XmlElement(name = "GeographicalLocation")
    protected GeoCoordinates geographicalLocation;
    @XmlElement(name = "Email")
    protected CustomerMaintainRequestBundleEmail email;
    @XmlElement(name = "FacsimileFormattedNumberDescription")
    protected String facsimileFormattedNumberDescription;
    @XmlElement(name = "PostalAddress")
    protected CustomerMaintainRequestBundleAddressPostalAddress postalAddress;
    @XmlElement(name = "DefaultMobilePhone")
    protected String defaultMobilePhone;
    @XmlElement(name = "DefaultConventionalPhone")
    protected String defaultConventionalPhone;
    @XmlElement(name = "Telephone")
    protected List<CustomerMaintainRequestBundleAddressTelephone> telephone;
    @XmlElement(name = "WebURI")
    @XmlSchemaType(name = "anyURI")
    protected String webURI;
    @XmlAttribute(name = "actionCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String actionCode;
    @XmlAttribute(name = "telephoneListCompleteTransmissionIndicator")
    protected Boolean telephoneListCompleteTransmissionIndicator;

    /**
     * 获取correspondenceLanguageCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorrespondenceLanguageCode() {
        return correspondenceLanguageCode;
    }

    /**
     * 设置correspondenceLanguageCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorrespondenceLanguageCode(String value) {
        this.correspondenceLanguageCode = value;
    }

    /**
     * 获取preferredCommunicationMediumTypeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CommunicationMediumTypeCode }
     *     
     */
    public CommunicationMediumTypeCode getPreferredCommunicationMediumTypeCode() {
        return preferredCommunicationMediumTypeCode;
    }

    /**
     * 设置preferredCommunicationMediumTypeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CommunicationMediumTypeCode }
     *     
     */
    public void setPreferredCommunicationMediumTypeCode(CommunicationMediumTypeCode value) {
        this.preferredCommunicationMediumTypeCode = value;
    }

    /**
     * 获取geographicalLocation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GeoCoordinates }
     *     
     */
    public GeoCoordinates getGeographicalLocation() {
        return geographicalLocation;
    }

    /**
     * 设置geographicalLocation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GeoCoordinates }
     *     
     */
    public void setGeographicalLocation(GeoCoordinates value) {
        this.geographicalLocation = value;
    }

    /**
     * 获取email属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CustomerMaintainRequestBundleEmail }
     *     
     */
    public CustomerMaintainRequestBundleEmail getEmail() {
        return email;
    }

    /**
     * 设置email属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerMaintainRequestBundleEmail }
     *     
     */
    public void setEmail(CustomerMaintainRequestBundleEmail value) {
        this.email = value;
    }

    /**
     * 获取facsimileFormattedNumberDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFacsimileFormattedNumberDescription() {
        return facsimileFormattedNumberDescription;
    }

    /**
     * 设置facsimileFormattedNumberDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFacsimileFormattedNumberDescription(String value) {
        this.facsimileFormattedNumberDescription = value;
    }

    /**
     * 获取postalAddress属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CustomerMaintainRequestBundleAddressPostalAddress }
     *     
     */
    public CustomerMaintainRequestBundleAddressPostalAddress getPostalAddress() {
        return postalAddress;
    }

    /**
     * 设置postalAddress属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerMaintainRequestBundleAddressPostalAddress }
     *     
     */
    public void setPostalAddress(CustomerMaintainRequestBundleAddressPostalAddress value) {
        this.postalAddress = value;
    }

    /**
     * 获取defaultMobilePhone属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultMobilePhone() {
        return defaultMobilePhone;
    }

    /**
     * 设置defaultMobilePhone属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultMobilePhone(String value) {
        this.defaultMobilePhone = value;
    }

    /**
     * 获取defaultConventionalPhone属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultConventionalPhone() {
        return defaultConventionalPhone;
    }

    /**
     * 设置defaultConventionalPhone属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultConventionalPhone(String value) {
        this.defaultConventionalPhone = value;
    }

    /**
     * Gets the value of the telephone property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the telephone property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTelephone().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustomerMaintainRequestBundleAddressTelephone }
     * 
     * 
     */
    public List<CustomerMaintainRequestBundleAddressTelephone> getTelephone() {
        if (telephone == null) {
            telephone = new ArrayList<CustomerMaintainRequestBundleAddressTelephone>();
        }
        return this.telephone;
    }

    /**
     * 获取webURI属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebURI() {
        return webURI;
    }

    /**
     * 设置webURI属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebURI(String value) {
        this.webURI = value;
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

    /**
     * 获取telephoneListCompleteTransmissionIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTelephoneListCompleteTransmissionIndicator() {
        return telephoneListCompleteTransmissionIndicator;
    }

    /**
     * 设置telephoneListCompleteTransmissionIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTelephoneListCompleteTransmissionIndicator(Boolean value) {
        this.telephoneListCompleteTransmissionIndicator = value;
    }

}
