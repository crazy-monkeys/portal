
package com.crazy.portal.bean.customer.wsdl.customer.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>CustomerMaintainRequestBundlePerson complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CustomerMaintainRequestBundlePerson"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="FormOfAddressCode" type="{http://sap.com/xi/AP/Common/GDT}FormOfAddressCode" minOccurs="0"/&gt;
 *         &lt;element name="AcademicTitleCode" type="{http://sap.com/xi/AP/Common/GDT}AcademicTitleCode" minOccurs="0"/&gt;
 *         &lt;element name="GivenName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_MEDIUM_Name" minOccurs="0"/&gt;
 *         &lt;element name="MiddleName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_MEDIUM_Name" minOccurs="0"/&gt;
 *         &lt;element name="FamilyName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_MEDIUM_Name" minOccurs="0"/&gt;
 *         &lt;element name="BirthName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_MEDIUM_Name" minOccurs="0"/&gt;
 *         &lt;element name="NickName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_MEDIUM_Name" minOccurs="0"/&gt;
 *         &lt;element name="InitialsName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_SHORT_Name" minOccurs="0"/&gt;
 *         &lt;element name="NamePrefixCode" type="{http://sap.com/xi/AP/Common/GDT}FamilyNamePrefixCode" minOccurs="0"/&gt;
 *         &lt;element name="NameFormatCountryCode" type="{http://sap.com/xi/AP/Common/GDT}CountryCode" minOccurs="0"/&gt;
 *         &lt;element name="GenderCode" type="{http://sap.com/xi/AP/Common/GDT}GenderCode" minOccurs="0"/&gt;
 *         &lt;element name="BirthDate" type="{http://sap.com/xi/AP/Common/GDT}Date" minOccurs="0"/&gt;
 *         &lt;element name="MaritalStatusCode" type="{http://sap.com/xi/AP/Common/GDT}MaritalStatusCode" minOccurs="0"/&gt;
 *         &lt;element name="NonVerbalCommunicationLanguageCode" type="{http://sap.com/xi/BASIS/Global}LanguageCode" minOccurs="0"/&gt;
 *         &lt;element name="NationalityCountryCode" type="{http://sap.com/xi/AP/Common/GDT}CountryCode" minOccurs="0"/&gt;
 *         &lt;element name="OccupationCode" type="{http://sap.com/xi/AP/Common/GDT}OccupationCode" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerMaintainRequestBundlePerson", namespace = "http://sap.com/xi/A1S/Global", propOrder = {
    "formOfAddressCode",
    "academicTitleCode",
    "givenName",
    "middleName",
    "familyName",
    "birthName",
    "nickName",
    "initialsName",
    "namePrefixCode",
    "nameFormatCountryCode",
    "genderCode",
    "birthDate",
    "maritalStatusCode",
    "nonVerbalCommunicationLanguageCode",
    "nationalityCountryCode",
    "occupationCode"
})
public class CustomerMaintainRequestBundlePerson {

    @XmlElement(name = "FormOfAddressCode")
    protected FormOfAddressCode formOfAddressCode;
    @XmlElement(name = "AcademicTitleCode")
    protected AcademicTitleCode academicTitleCode;
    @XmlElement(name = "GivenName")
    protected String givenName;
    @XmlElement(name = "MiddleName")
    protected String middleName;
    @XmlElement(name = "FamilyName")
    protected String familyName;
    @XmlElement(name = "BirthName")
    protected String birthName;
    @XmlElement(name = "NickName")
    protected String nickName;
    @XmlElement(name = "InitialsName")
    protected String initialsName;
    @XmlElement(name = "NamePrefixCode")
    protected FamilyNamePrefixCode namePrefixCode;
    @XmlElement(name = "NameFormatCountryCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String nameFormatCountryCode;
    @XmlElement(name = "GenderCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String genderCode;
    @XmlElement(name = "BirthDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar birthDate;
    @XmlElement(name = "MaritalStatusCode")
    protected MaritalStatusCode maritalStatusCode;
    @XmlElement(name = "NonVerbalCommunicationLanguageCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "language")
    protected String nonVerbalCommunicationLanguageCode;
    @XmlElement(name = "NationalityCountryCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String nationalityCountryCode;
    @XmlElement(name = "OccupationCode")
    protected OccupationCode occupationCode;

    /**
     * 获取formOfAddressCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link FormOfAddressCode }
     *     
     */
    public FormOfAddressCode getFormOfAddressCode() {
        return formOfAddressCode;
    }

    /**
     * 设置formOfAddressCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link FormOfAddressCode }
     *     
     */
    public void setFormOfAddressCode(FormOfAddressCode value) {
        this.formOfAddressCode = value;
    }

    /**
     * 获取academicTitleCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AcademicTitleCode }
     *     
     */
    public AcademicTitleCode getAcademicTitleCode() {
        return academicTitleCode;
    }

    /**
     * 设置academicTitleCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AcademicTitleCode }
     *     
     */
    public void setAcademicTitleCode(AcademicTitleCode value) {
        this.academicTitleCode = value;
    }

    /**
     * 获取givenName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGivenName() {
        return givenName;
    }

    /**
     * 设置givenName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGivenName(String value) {
        this.givenName = value;
    }

    /**
     * 获取middleName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * 设置middleName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiddleName(String value) {
        this.middleName = value;
    }

    /**
     * 获取familyName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * 设置familyName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFamilyName(String value) {
        this.familyName = value;
    }

    /**
     * 获取birthName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBirthName() {
        return birthName;
    }

    /**
     * 设置birthName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBirthName(String value) {
        this.birthName = value;
    }

    /**
     * 获取nickName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置nickName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNickName(String value) {
        this.nickName = value;
    }

    /**
     * 获取initialsName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInitialsName() {
        return initialsName;
    }

    /**
     * 设置initialsName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInitialsName(String value) {
        this.initialsName = value;
    }

    /**
     * 获取namePrefixCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link FamilyNamePrefixCode }
     *     
     */
    public FamilyNamePrefixCode getNamePrefixCode() {
        return namePrefixCode;
    }

    /**
     * 设置namePrefixCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link FamilyNamePrefixCode }
     *     
     */
    public void setNamePrefixCode(FamilyNamePrefixCode value) {
        this.namePrefixCode = value;
    }

    /**
     * 获取nameFormatCountryCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameFormatCountryCode() {
        return nameFormatCountryCode;
    }

    /**
     * 设置nameFormatCountryCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameFormatCountryCode(String value) {
        this.nameFormatCountryCode = value;
    }

    /**
     * 获取genderCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGenderCode() {
        return genderCode;
    }

    /**
     * 设置genderCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGenderCode(String value) {
        this.genderCode = value;
    }

    /**
     * 获取birthDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBirthDate() {
        return birthDate;
    }

    /**
     * 设置birthDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBirthDate(XMLGregorianCalendar value) {
        this.birthDate = value;
    }

    /**
     * 获取maritalStatusCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link MaritalStatusCode }
     *     
     */
    public MaritalStatusCode getMaritalStatusCode() {
        return maritalStatusCode;
    }

    /**
     * 设置maritalStatusCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link MaritalStatusCode }
     *     
     */
    public void setMaritalStatusCode(MaritalStatusCode value) {
        this.maritalStatusCode = value;
    }

    /**
     * 获取nonVerbalCommunicationLanguageCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNonVerbalCommunicationLanguageCode() {
        return nonVerbalCommunicationLanguageCode;
    }

    /**
     * 设置nonVerbalCommunicationLanguageCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNonVerbalCommunicationLanguageCode(String value) {
        this.nonVerbalCommunicationLanguageCode = value;
    }

    /**
     * 获取nationalityCountryCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNationalityCountryCode() {
        return nationalityCountryCode;
    }

    /**
     * 设置nationalityCountryCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNationalityCountryCode(String value) {
        this.nationalityCountryCode = value;
    }

    /**
     * 获取occupationCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link OccupationCode }
     *     
     */
    public OccupationCode getOccupationCode() {
        return occupationCode;
    }

    /**
     * 设置occupationCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link OccupationCode }
     *     
     */
    public void setOccupationCode(OccupationCode value) {
        this.occupationCode = value;
    }

}