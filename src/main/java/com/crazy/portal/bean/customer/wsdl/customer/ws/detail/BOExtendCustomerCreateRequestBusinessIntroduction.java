
package com.crazy.portal.bean.customer.wsdl.customer.ws.detail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>BO_ExtendCustomerCreateRequestBusinessIntroduction complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="BO_ExtendCustomerCreateRequestBusinessIntroduction"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Year" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BCO_YEARCode" minOccurs="0"/&gt;
 *         &lt;element name="ProductLine1" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_LONG_Description" minOccurs="0"/&gt;
 *         &lt;element name="RevenuePL1" type="{http://sap.com/xi/AP/Common/GDT}Amount" minOccurs="0"/&gt;
 *         &lt;element name="RevenuePL2" type="{http://sap.com/xi/AP/Common/GDT}Amount" minOccurs="0"/&gt;
 *         &lt;element name="RevenuePL3" type="{http://sap.com/xi/AP/Common/GDT}Amount" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BO_ExtendCustomerCreateRequestBusinessIntroduction", propOrder = {
    "year",
    "productLine1",
    "revenuePL1",
    "revenuePL2",
    "revenuePL3"
})
public class BOExtendCustomerCreateRequestBusinessIntroduction {

    @XmlElement(name = "Year")
    protected BCOYEARCode year;
    @XmlElement(name = "ProductLine1")
    protected String productLine1;
    @XmlElement(name = "RevenuePL1")
    protected Amount revenuePL1;
    @XmlElement(name = "RevenuePL2")
    protected Amount revenuePL2;
    @XmlElement(name = "RevenuePL3")
    protected Amount revenuePL3;

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
     * 获取productLine1属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductLine1() {
        return productLine1;
    }

    /**
     * 设置productLine1属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductLine1(String value) {
        this.productLine1 = value;
    }

    /**
     * 获取revenuePL1属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getRevenuePL1() {
        return revenuePL1;
    }

    /**
     * 设置revenuePL1属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setRevenuePL1(Amount value) {
        this.revenuePL1 = value;
    }

    /**
     * 获取revenuePL2属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getRevenuePL2() {
        return revenuePL2;
    }

    /**
     * 设置revenuePL2属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setRevenuePL2(Amount value) {
        this.revenuePL2 = value;
    }

    /**
     * 获取revenuePL3属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getRevenuePL3() {
        return revenuePL3;
    }

    /**
     * 设置revenuePL3属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setRevenuePL3(Amount value) {
        this.revenuePL3 = value;
    }

}
