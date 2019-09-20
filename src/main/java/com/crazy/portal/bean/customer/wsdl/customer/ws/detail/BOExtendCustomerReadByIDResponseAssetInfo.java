
package com.crazy.portal.bean.customer.wsdl.customer.ws.detail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>BO_ExtendCustomerReadByIDResponseAssetInfo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="BO_ExtendCustomerReadByIDResponseAssetInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SAP_UUID" type="{http://sap.com/xi/Common/DataTypes}UUID" minOccurs="0"/&gt;
 *         &lt;element name="Year" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BCO_YEARCode" minOccurs="0"/&gt;
 *         &lt;element name="YearName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_LONG_Name" minOccurs="0"/&gt;
 *         &lt;element name="Season" type="{http://sap.com/xi/Common/DataTypes}Quarter" minOccurs="0"/&gt;
 *         &lt;element name="SeasonName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_LONG_Name" minOccurs="0"/&gt;
 *         &lt;element name="TotalAssets" type="{http://sap.com/xi/AP/Common/GDT}Amount" minOccurs="0"/&gt;
 *         &lt;element name="NetAssets" type="{http://sap.com/xi/AP/Common/GDT}Amount" minOccurs="0"/&gt;
 *         &lt;element name="Revenue" type="{http://sap.com/xi/AP/Common/GDT}Amount" minOccurs="0"/&gt;
 *         &lt;element name="TotalStaff" type="{http://sap.com/xi/AP/Common/GDT}NumberValue" minOccurs="0"/&gt;
 *         &lt;element name="SAP_SystemAdministrativeData" type="{http://sap.com/xi/AP/Common/GDT}SystemAdministrativeData" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BO_ExtendCustomerReadByIDResponseAssetInfo", propOrder = {
    "sapuuid",
    "year",
    "yearName",
    "season",
    "seasonName",
    "totalAssets",
    "netAssets",
    "revenue",
    "totalStaff",
    "sapSystemAdministrativeData"
})
public class BOExtendCustomerReadByIDResponseAssetInfo {

    @XmlElement(name = "SAP_UUID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String sapuuid;
    @XmlElement(name = "Year")
    protected BCOYEARCode year;
    @XmlElement(name = "YearName")
    protected String yearName;
    @XmlElement(name = "Season")
    @XmlSchemaType(name = "token")
    protected Quarter season;
    @XmlElement(name = "SeasonName")
    protected String seasonName;
    @XmlElement(name = "TotalAssets")
    protected Amount totalAssets;
    @XmlElement(name = "NetAssets")
    protected Amount netAssets;
    @XmlElement(name = "Revenue")
    protected Amount revenue;
    @XmlElement(name = "TotalStaff")
    protected Integer totalStaff;
    @XmlElement(name = "SAP_SystemAdministrativeData")
    protected SystemAdministrativeData sapSystemAdministrativeData;

    /**
     * 获取sapuuid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSAPUUID() {
        return sapuuid;
    }

    /**
     * 设置sapuuid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSAPUUID(String value) {
        this.sapuuid = value;
    }

    /**
     * 获取year属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BCOYEARCode }
     *     
     */
    public BCOYEARCode getYear() {
        return year;
    }

    /**
     * 设置year属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BCOYEARCode }
     *     
     */
    public void setYear(BCOYEARCode value) {
        this.year = value;
    }

    /**
     * 获取yearName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYearName() {
        return yearName;
    }

    /**
     * 设置yearName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYearName(String value) {
        this.yearName = value;
    }

    /**
     * 获取season属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Quarter }
     *     
     */
    public Quarter getSeason() {
        return season;
    }

    /**
     * 设置season属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Quarter }
     *     
     */
    public void setSeason(Quarter value) {
        this.season = value;
    }

    /**
     * 获取seasonName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeasonName() {
        return seasonName;
    }

    /**
     * 设置seasonName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeasonName(String value) {
        this.seasonName = value;
    }

    /**
     * 获取totalAssets属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getTotalAssets() {
        return totalAssets;
    }

    /**
     * 设置totalAssets属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setTotalAssets(Amount value) {
        this.totalAssets = value;
    }

    /**
     * 获取netAssets属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getNetAssets() {
        return netAssets;
    }

    /**
     * 设置netAssets属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setNetAssets(Amount value) {
        this.netAssets = value;
    }

    /**
     * 获取revenue属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getRevenue() {
        return revenue;
    }

    /**
     * 设置revenue属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setRevenue(Amount value) {
        this.revenue = value;
    }

    /**
     * 获取totalStaff属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTotalStaff() {
        return totalStaff;
    }

    /**
     * 设置totalStaff属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTotalStaff(Integer value) {
        this.totalStaff = value;
    }

    /**
     * 获取sapSystemAdministrativeData属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SystemAdministrativeData }
     *     
     */
    public SystemAdministrativeData getSAPSystemAdministrativeData() {
        return sapSystemAdministrativeData;
    }

    /**
     * 设置sapSystemAdministrativeData属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SystemAdministrativeData }
     *     
     */
    public void setSAPSystemAdministrativeData(SystemAdministrativeData value) {
        this.sapSystemAdministrativeData = value;
    }

}
