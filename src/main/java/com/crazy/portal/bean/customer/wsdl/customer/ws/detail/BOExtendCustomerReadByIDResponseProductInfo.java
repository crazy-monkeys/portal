
package com.crazy.portal.bean.customer.wsdl.customer.ws.detail;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>BO_ExtendCustomerReadByIDResponseProductInfo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="BO_ExtendCustomerReadByIDResponseProductInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SAP_UUID" type="{http://sap.com/xi/Common/DataTypes}UUID" minOccurs="0"/&gt;
 *         &lt;element name="ProductCode" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_SHORT_Description" minOccurs="0"/&gt;
 *         &lt;element name="BusinessUnit" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BCO_BUCode" minOccurs="0"/&gt;
 *         &lt;element name="BusinessUnitName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_LONG_Name" minOccurs="0"/&gt;
 *         &lt;element name="CURRENT_YEAR" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_SHORT_Description" minOccurs="0"/&gt;
 *         &lt;element name="CURRENT_MONTH" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_SHORT_Description" minOccurs="0"/&gt;
 *         &lt;element name="EXPECTED_SHIPMENTS1" type="{http://sap.com/xi/AP/Common/GDT}DecimalValue" minOccurs="0"/&gt;
 *         &lt;element name="EXPECTED_SHIPMENTS2" type="{http://sap.com/xi/AP/Common/GDT}DecimalValue" minOccurs="0"/&gt;
 *         &lt;element name="EXPECTED_SHIPMENTS3" type="{http://sap.com/xi/AP/Common/GDT}DecimalValue" minOccurs="0"/&gt;
 *         &lt;element name="EXPECTED_SHIPMENTS4" type="{http://sap.com/xi/AP/Common/GDT}DecimalValue" minOccurs="0"/&gt;
 *         &lt;element name="EXPECTED_SHIPMENTS5" type="{http://sap.com/xi/AP/Common/GDT}DecimalValue" minOccurs="0"/&gt;
 *         &lt;element name="EXPECTED_SHIPMENTS6" type="{http://sap.com/xi/AP/Common/GDT}DecimalValue" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BO_ExtendCustomerReadByIDResponseProductInfo", propOrder = {
    "sapuuid",
    "productCode",
    "businessUnit",
    "businessUnitName",
    "currentyear",
    "currentmonth",
    "expectedshipments1",
    "expectedshipments2",
    "expectedshipments3",
    "expectedshipments4",
    "expectedshipments5",
    "expectedshipments6"
})
public class BOExtendCustomerReadByIDResponseProductInfo {

    @XmlElement(name = "SAP_UUID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String sapuuid;
    @XmlElement(name = "ProductCode")
    protected String productCode;
    @XmlElement(name = "BusinessUnit")
    protected BCOBUCode businessUnit;
    @XmlElement(name = "BusinessUnitName")
    protected String businessUnitName;
    @XmlElement(name = "CURRENT_YEAR")
    protected String currentyear;
    @XmlElement(name = "CURRENT_MONTH")
    protected String currentmonth;
    @XmlElement(name = "EXPECTED_SHIPMENTS1")
    protected BigDecimal expectedshipments1;
    @XmlElement(name = "EXPECTED_SHIPMENTS2")
    protected BigDecimal expectedshipments2;
    @XmlElement(name = "EXPECTED_SHIPMENTS3")
    protected BigDecimal expectedshipments3;
    @XmlElement(name = "EXPECTED_SHIPMENTS4")
    protected BigDecimal expectedshipments4;
    @XmlElement(name = "EXPECTED_SHIPMENTS5")
    protected BigDecimal expectedshipments5;
    @XmlElement(name = "EXPECTED_SHIPMENTS6")
    protected BigDecimal expectedshipments6;

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
     * 获取productCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * 设置productCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductCode(String value) {
        this.productCode = value;
    }

    /**
     * 获取businessUnit属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BCOBUCode }
     *     
     */
    public BCOBUCode getBusinessUnit() {
        return businessUnit;
    }

    /**
     * 设置businessUnit属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BCOBUCode }
     *     
     */
    public void setBusinessUnit(BCOBUCode value) {
        this.businessUnit = value;
    }

    /**
     * 获取businessUnitName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessUnitName() {
        return businessUnitName;
    }

    /**
     * 设置businessUnitName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessUnitName(String value) {
        this.businessUnitName = value;
    }

    /**
     * 获取currentyear属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCURRENTYEAR() {
        return currentyear;
    }

    /**
     * 设置currentyear属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCURRENTYEAR(String value) {
        this.currentyear = value;
    }

    /**
     * 获取currentmonth属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCURRENTMONTH() {
        return currentmonth;
    }

    /**
     * 设置currentmonth属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCURRENTMONTH(String value) {
        this.currentmonth = value;
    }

    /**
     * 获取expectedshipments1属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getEXPECTEDSHIPMENTS1() {
        return expectedshipments1;
    }

    /**
     * 设置expectedshipments1属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setEXPECTEDSHIPMENTS1(BigDecimal value) {
        this.expectedshipments1 = value;
    }

    /**
     * 获取expectedshipments2属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getEXPECTEDSHIPMENTS2() {
        return expectedshipments2;
    }

    /**
     * 设置expectedshipments2属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setEXPECTEDSHIPMENTS2(BigDecimal value) {
        this.expectedshipments2 = value;
    }

    /**
     * 获取expectedshipments3属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getEXPECTEDSHIPMENTS3() {
        return expectedshipments3;
    }

    /**
     * 设置expectedshipments3属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setEXPECTEDSHIPMENTS3(BigDecimal value) {
        this.expectedshipments3 = value;
    }

    /**
     * 获取expectedshipments4属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getEXPECTEDSHIPMENTS4() {
        return expectedshipments4;
    }

    /**
     * 设置expectedshipments4属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setEXPECTEDSHIPMENTS4(BigDecimal value) {
        this.expectedshipments4 = value;
    }

    /**
     * 获取expectedshipments5属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getEXPECTEDSHIPMENTS5() {
        return expectedshipments5;
    }

    /**
     * 设置expectedshipments5属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setEXPECTEDSHIPMENTS5(BigDecimal value) {
        this.expectedshipments5 = value;
    }

    /**
     * 获取expectedshipments6属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getEXPECTEDSHIPMENTS6() {
        return expectedshipments6;
    }

    /**
     * 设置expectedshipments6属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setEXPECTEDSHIPMENTS6(BigDecimal value) {
        this.expectedshipments6 = value;
    }

}
