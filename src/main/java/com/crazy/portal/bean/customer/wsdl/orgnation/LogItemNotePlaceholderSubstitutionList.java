
package com.crazy.portal.bean.customer.wsdl.orgnation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>LogItemNotePlaceholderSubstitutionList complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="LogItemNotePlaceholderSubstitutionList"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="FirstPlaceholderID" type="{http://sap.com/xi/AP/Common/GDT}LogItemTemplatePlaceholderID"/&gt;
 *         &lt;element name="SecondPlaceholderID" type="{http://sap.com/xi/AP/Common/GDT}LogItemTemplatePlaceholderID" minOccurs="0"/&gt;
 *         &lt;element name="ThirdPlaceholderID" type="{http://sap.com/xi/AP/Common/GDT}LogItemTemplatePlaceholderID" minOccurs="0"/&gt;
 *         &lt;element name="FourthPlaceholderID" type="{http://sap.com/xi/AP/Common/GDT}LogItemTemplatePlaceholderID" minOccurs="0"/&gt;
 *         &lt;element name="FirstPlaceholderSubstitutionText" type="{http://sap.com/xi/AP/Common/GDT}LogItemPlaceholderSubstitutionText"/&gt;
 *         &lt;element name="SecondPlaceholderSubstitutionText" type="{http://sap.com/xi/AP/Common/GDT}LogItemPlaceholderSubstitutionText" minOccurs="0"/&gt;
 *         &lt;element name="ThirdPlaceholderSubstitutionText" type="{http://sap.com/xi/AP/Common/GDT}LogItemPlaceholderSubstitutionText" minOccurs="0"/&gt;
 *         &lt;element name="FourthPlaceholderSubstitutionText" type="{http://sap.com/xi/AP/Common/GDT}LogItemPlaceholderSubstitutionText" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LogItemNotePlaceholderSubstitutionList", propOrder = {
    "firstPlaceholderID",
    "secondPlaceholderID",
    "thirdPlaceholderID",
    "fourthPlaceholderID",
    "firstPlaceholderSubstitutionText",
    "secondPlaceholderSubstitutionText",
    "thirdPlaceholderSubstitutionText",
    "fourthPlaceholderSubstitutionText"
})
public class LogItemNotePlaceholderSubstitutionList {

    @XmlElement(name = "FirstPlaceholderID", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String firstPlaceholderID;
    @XmlElement(name = "SecondPlaceholderID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String secondPlaceholderID;
    @XmlElement(name = "ThirdPlaceholderID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String thirdPlaceholderID;
    @XmlElement(name = "FourthPlaceholderID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String fourthPlaceholderID;
    @XmlElement(name = "FirstPlaceholderSubstitutionText", required = true)
    protected String firstPlaceholderSubstitutionText;
    @XmlElement(name = "SecondPlaceholderSubstitutionText")
    protected String secondPlaceholderSubstitutionText;
    @XmlElement(name = "ThirdPlaceholderSubstitutionText")
    protected String thirdPlaceholderSubstitutionText;
    @XmlElement(name = "FourthPlaceholderSubstitutionText")
    protected String fourthPlaceholderSubstitutionText;

    /**
     * 获取firstPlaceholderID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstPlaceholderID() {
        return firstPlaceholderID;
    }

    /**
     * 设置firstPlaceholderID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstPlaceholderID(String value) {
        this.firstPlaceholderID = value;
    }

    /**
     * 获取secondPlaceholderID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecondPlaceholderID() {
        return secondPlaceholderID;
    }

    /**
     * 设置secondPlaceholderID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecondPlaceholderID(String value) {
        this.secondPlaceholderID = value;
    }

    /**
     * 获取thirdPlaceholderID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getThirdPlaceholderID() {
        return thirdPlaceholderID;
    }

    /**
     * 设置thirdPlaceholderID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setThirdPlaceholderID(String value) {
        this.thirdPlaceholderID = value;
    }

    /**
     * 获取fourthPlaceholderID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFourthPlaceholderID() {
        return fourthPlaceholderID;
    }

    /**
     * 设置fourthPlaceholderID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFourthPlaceholderID(String value) {
        this.fourthPlaceholderID = value;
    }

    /**
     * 获取firstPlaceholderSubstitutionText属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstPlaceholderSubstitutionText() {
        return firstPlaceholderSubstitutionText;
    }

    /**
     * 设置firstPlaceholderSubstitutionText属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstPlaceholderSubstitutionText(String value) {
        this.firstPlaceholderSubstitutionText = value;
    }

    /**
     * 获取secondPlaceholderSubstitutionText属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecondPlaceholderSubstitutionText() {
        return secondPlaceholderSubstitutionText;
    }

    /**
     * 设置secondPlaceholderSubstitutionText属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecondPlaceholderSubstitutionText(String value) {
        this.secondPlaceholderSubstitutionText = value;
    }

    /**
     * 获取thirdPlaceholderSubstitutionText属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getThirdPlaceholderSubstitutionText() {
        return thirdPlaceholderSubstitutionText;
    }

    /**
     * 设置thirdPlaceholderSubstitutionText属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setThirdPlaceholderSubstitutionText(String value) {
        this.thirdPlaceholderSubstitutionText = value;
    }

    /**
     * 获取fourthPlaceholderSubstitutionText属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFourthPlaceholderSubstitutionText() {
        return fourthPlaceholderSubstitutionText;
    }

    /**
     * 设置fourthPlaceholderSubstitutionText属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFourthPlaceholderSubstitutionText(String value) {
        this.fourthPlaceholderSubstitutionText = value;
    }

}
