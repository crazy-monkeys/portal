
package com.crazy.portal.bean.customer.wsdl.orgnation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>OrganisationalUnitMaintainRequestAddress complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="OrganisationalUnitMaintainRequestAddress"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CompanyName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_MEDIUM_Name" minOccurs="0"/&gt;
 *         &lt;element name="DepartmentName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_MEDIUM_Name" minOccurs="0"/&gt;
 *         &lt;element name="CountryCode" type="{http://sap.com/xi/AP/Common/GDT}CountryCode" minOccurs="0"/&gt;
 *         &lt;element name="CareOfName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_MEDIUM_Name" minOccurs="0"/&gt;
 *         &lt;element name="AddressLine1" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_MEDIUM_Name" minOccurs="0"/&gt;
 *         &lt;element name="AddressLine2" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_MEDIUM_Name" minOccurs="0"/&gt;
 *         &lt;element name="HouseID" type="{http://sap.com/xi/AP/Common/GDT}EncryptedHouseID" minOccurs="0"/&gt;
 *         &lt;element name="StreetName" type="{http://sap.com/xi/AP/Common/GDT}EncryptedStreetName" minOccurs="0"/&gt;
 *         &lt;element name="AddressLine4" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_MEDIUM_Name" minOccurs="0"/&gt;
 *         &lt;element name="AddressLine5" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_MEDIUM_Name" minOccurs="0"/&gt;
 *         &lt;element name="CityName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_MEDIUM_Name" minOccurs="0"/&gt;
 *         &lt;element name="RegionCode" type="{http://sap.com/xi/AP/Common/GDT}RegionCode" minOccurs="0"/&gt;
 *         &lt;element name="StreetPostalCode" type="{http://sap.com/xi/AP/Common/GDT}EncryptedPostalCode" minOccurs="0"/&gt;
 *         &lt;element name="DistrictName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_MEDIUM_Name" minOccurs="0"/&gt;
 *         &lt;element name="CountyName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_MEDIUM_Name" minOccurs="0"/&gt;
 *         &lt;element name="POBoxIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *         &lt;element name="POBoxID" type="{http://sap.com/xi/AP/Common/GDT}EncryptedPOBoxID" minOccurs="0"/&gt;
 *         &lt;element name="POBoxPostalCode" type="{http://sap.com/xi/AP/Common/GDT}EncryptedPostalCode" minOccurs="0"/&gt;
 *         &lt;element name="POBoxDeviatingCityName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_MEDIUM_Name" minOccurs="0"/&gt;
 *         &lt;element name="POBoxDeviatingCountryCode" type="{http://sap.com/xi/AP/Common/GDT}CountryCode" minOccurs="0"/&gt;
 *         &lt;element name="POBoxDeviatingRegionCode" type="{http://sap.com/xi/AP/Common/GDT}RegionCode" minOccurs="0"/&gt;
 *         &lt;element name="GeoCoordinates" type="{http://sap.com/xi/AP/Common/GDT}GeoCoordinates" minOccurs="0"/&gt;
 *         &lt;element name="TimeZoneCode" type="{http://sap.com/xi/AP/Common/GDT}TimeZoneCode" minOccurs="0"/&gt;
 *         &lt;element name="ConventionalPhoneFormattedNumberDescription" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_NUMBER_SHORT_Description" minOccurs="0"/&gt;
 *         &lt;element name="FacsimileFormattedNumberDescription" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_NUMBER_SHORT_Description" minOccurs="0"/&gt;
 *         &lt;element name="MobileFormattedNumberDescription" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_NUMBER_SHORT_Description" minOccurs="0"/&gt;
 *         &lt;element name="EMailURI" type="{http://sap.com/xi/AP/Common/GDT}ENCRYPTED_MEDIUM_EmailURI" minOccurs="0"/&gt;
 *         &lt;element name="WebURI" type="{http://sap.com/xi/AP/Common/GDT}EXTENDED_WebURI" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrganisationalUnitMaintainRequestAddress", namespace = "http://sap.com/xi/A1S/Global", propOrder = {
    "companyName",
    "departmentName",
    "countryCode",
    "careOfName",
    "addressLine1",
    "addressLine2",
    "houseID",
    "streetName",
    "addressLine4",
    "addressLine5",
    "cityName",
    "regionCode",
    "streetPostalCode",
    "districtName",
    "countyName",
    "poBoxIndicator",
    "poBoxID",
    "poBoxPostalCode",
    "poBoxDeviatingCityName",
    "poBoxDeviatingCountryCode",
    "poBoxDeviatingRegionCode",
    "geoCoordinates",
    "timeZoneCode",
    "conventionalPhoneFormattedNumberDescription",
    "facsimileFormattedNumberDescription",
    "mobileFormattedNumberDescription",
    "eMailURI",
    "webURI"
})
public class OrganisationalUnitMaintainRequestAddress {

    @XmlElement(name = "CompanyName")
    protected String companyName;
    @XmlElement(name = "DepartmentName")
    protected String departmentName;
    @XmlElement(name = "CountryCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String countryCode;
    @XmlElement(name = "CareOfName")
    protected String careOfName;
    @XmlElement(name = "AddressLine1")
    protected String addressLine1;
    @XmlElement(name = "AddressLine2")
    protected String addressLine2;
    @XmlElement(name = "HouseID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String houseID;
    @XmlElement(name = "StreetName")
    protected String streetName;
    @XmlElement(name = "AddressLine4")
    protected String addressLine4;
    @XmlElement(name = "AddressLine5")
    protected String addressLine5;
    @XmlElement(name = "CityName")
    protected String cityName;
    @XmlElement(name = "RegionCode")
    protected RegionCode regionCode;
    @XmlElement(name = "StreetPostalCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String streetPostalCode;
    @XmlElement(name = "DistrictName")
    protected String districtName;
    @XmlElement(name = "CountyName")
    protected String countyName;
    @XmlElement(name = "POBoxIndicator")
    protected Boolean poBoxIndicator;
    @XmlElement(name = "POBoxID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String poBoxID;
    @XmlElement(name = "POBoxPostalCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String poBoxPostalCode;
    @XmlElement(name = "POBoxDeviatingCityName")
    protected String poBoxDeviatingCityName;
    @XmlElement(name = "POBoxDeviatingCountryCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String poBoxDeviatingCountryCode;
    @XmlElement(name = "POBoxDeviatingRegionCode")
    protected RegionCode poBoxDeviatingRegionCode;
    @XmlElement(name = "GeoCoordinates")
    protected GeoCoordinates geoCoordinates;
    @XmlElement(name = "TimeZoneCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String timeZoneCode;
    @XmlElement(name = "ConventionalPhoneFormattedNumberDescription")
    protected String conventionalPhoneFormattedNumberDescription;
    @XmlElement(name = "FacsimileFormattedNumberDescription")
    protected String facsimileFormattedNumberDescription;
    @XmlElement(name = "MobileFormattedNumberDescription")
    protected String mobileFormattedNumberDescription;
    @XmlElement(name = "EMailURI")
    protected ENCRYPTEDMEDIUMEmailURI eMailURI;
    @XmlElement(name = "WebURI")
    @XmlSchemaType(name = "anyURI")
    protected String webURI;

    /**
     * 获取companyName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置companyName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyName(String value) {
        this.companyName = value;
    }

    /**
     * 获取departmentName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * 设置departmentName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartmentName(String value) {
        this.departmentName = value;
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
     * 获取careOfName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCareOfName() {
        return careOfName;
    }

    /**
     * 设置careOfName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCareOfName(String value) {
        this.careOfName = value;
    }

    /**
     * 获取addressLine1属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * 设置addressLine1属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressLine1(String value) {
        this.addressLine1 = value;
    }

    /**
     * 获取addressLine2属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * 设置addressLine2属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressLine2(String value) {
        this.addressLine2 = value;
    }

    /**
     * 获取houseID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseID() {
        return houseID;
    }

    /**
     * 设置houseID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseID(String value) {
        this.houseID = value;
    }

    /**
     * 获取streetName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * 设置streetName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreetName(String value) {
        this.streetName = value;
    }

    /**
     * 获取addressLine4属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressLine4() {
        return addressLine4;
    }

    /**
     * 设置addressLine4属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressLine4(String value) {
        this.addressLine4 = value;
    }

    /**
     * 获取addressLine5属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressLine5() {
        return addressLine5;
    }

    /**
     * 设置addressLine5属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressLine5(String value) {
        this.addressLine5 = value;
    }

    /**
     * 获取cityName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * 设置cityName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityName(String value) {
        this.cityName = value;
    }

    /**
     * 获取regionCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RegionCode }
     *     
     */
    public RegionCode getRegionCode() {
        return regionCode;
    }

    /**
     * 设置regionCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RegionCode }
     *     
     */
    public void setRegionCode(RegionCode value) {
        this.regionCode = value;
    }

    /**
     * 获取streetPostalCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreetPostalCode() {
        return streetPostalCode;
    }

    /**
     * 设置streetPostalCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreetPostalCode(String value) {
        this.streetPostalCode = value;
    }

    /**
     * 获取districtName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistrictName() {
        return districtName;
    }

    /**
     * 设置districtName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistrictName(String value) {
        this.districtName = value;
    }

    /**
     * 获取countyName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountyName() {
        return countyName;
    }

    /**
     * 设置countyName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountyName(String value) {
        this.countyName = value;
    }

    /**
     * 获取poBoxIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPOBoxIndicator() {
        return poBoxIndicator;
    }

    /**
     * 设置poBoxIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPOBoxIndicator(Boolean value) {
        this.poBoxIndicator = value;
    }

    /**
     * 获取poBoxID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPOBoxID() {
        return poBoxID;
    }

    /**
     * 设置poBoxID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPOBoxID(String value) {
        this.poBoxID = value;
    }

    /**
     * 获取poBoxPostalCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPOBoxPostalCode() {
        return poBoxPostalCode;
    }

    /**
     * 设置poBoxPostalCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPOBoxPostalCode(String value) {
        this.poBoxPostalCode = value;
    }

    /**
     * 获取poBoxDeviatingCityName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPOBoxDeviatingCityName() {
        return poBoxDeviatingCityName;
    }

    /**
     * 设置poBoxDeviatingCityName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPOBoxDeviatingCityName(String value) {
        this.poBoxDeviatingCityName = value;
    }

    /**
     * 获取poBoxDeviatingCountryCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPOBoxDeviatingCountryCode() {
        return poBoxDeviatingCountryCode;
    }

    /**
     * 设置poBoxDeviatingCountryCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPOBoxDeviatingCountryCode(String value) {
        this.poBoxDeviatingCountryCode = value;
    }

    /**
     * 获取poBoxDeviatingRegionCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RegionCode }
     *     
     */
    public RegionCode getPOBoxDeviatingRegionCode() {
        return poBoxDeviatingRegionCode;
    }

    /**
     * 设置poBoxDeviatingRegionCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RegionCode }
     *     
     */
    public void setPOBoxDeviatingRegionCode(RegionCode value) {
        this.poBoxDeviatingRegionCode = value;
    }

    /**
     * 获取geoCoordinates属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GeoCoordinates }
     *     
     */
    public GeoCoordinates getGeoCoordinates() {
        return geoCoordinates;
    }

    /**
     * 设置geoCoordinates属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GeoCoordinates }
     *     
     */
    public void setGeoCoordinates(GeoCoordinates value) {
        this.geoCoordinates = value;
    }

    /**
     * 获取timeZoneCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeZoneCode() {
        return timeZoneCode;
    }

    /**
     * 设置timeZoneCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeZoneCode(String value) {
        this.timeZoneCode = value;
    }

    /**
     * 获取conventionalPhoneFormattedNumberDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConventionalPhoneFormattedNumberDescription() {
        return conventionalPhoneFormattedNumberDescription;
    }

    /**
     * 设置conventionalPhoneFormattedNumberDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConventionalPhoneFormattedNumberDescription(String value) {
        this.conventionalPhoneFormattedNumberDescription = value;
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
     * 获取mobileFormattedNumberDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobileFormattedNumberDescription() {
        return mobileFormattedNumberDescription;
    }

    /**
     * 设置mobileFormattedNumberDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobileFormattedNumberDescription(String value) {
        this.mobileFormattedNumberDescription = value;
    }

    /**
     * 获取eMailURI属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ENCRYPTEDMEDIUMEmailURI }
     *     
     */
    public ENCRYPTEDMEDIUMEmailURI getEMailURI() {
        return eMailURI;
    }

    /**
     * 设置eMailURI属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ENCRYPTEDMEDIUMEmailURI }
     *     
     */
    public void setEMailURI(ENCRYPTEDMEDIUMEmailURI value) {
        this.eMailURI = value;
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

}
