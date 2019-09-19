
package com.crazy.portal.bean.customer.wsdl.customer.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>WeekdaySelection complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="WeekdaySelection"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="MondayIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *         &lt;element name="TuesdayIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *         &lt;element name="WednesdayIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *         &lt;element name="ThursdayIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *         &lt;element name="FridayIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *         &lt;element name="SaturdayIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *         &lt;element name="SundayIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WeekdaySelection", propOrder = {
    "mondayIndicator",
    "tuesdayIndicator",
    "wednesdayIndicator",
    "thursdayIndicator",
    "fridayIndicator",
    "saturdayIndicator",
    "sundayIndicator"
})
public class WeekdaySelection {

    @XmlElement(name = "MondayIndicator")
    protected Boolean mondayIndicator;
    @XmlElement(name = "TuesdayIndicator")
    protected Boolean tuesdayIndicator;
    @XmlElement(name = "WednesdayIndicator")
    protected Boolean wednesdayIndicator;
    @XmlElement(name = "ThursdayIndicator")
    protected Boolean thursdayIndicator;
    @XmlElement(name = "FridayIndicator")
    protected Boolean fridayIndicator;
    @XmlElement(name = "SaturdayIndicator")
    protected Boolean saturdayIndicator;
    @XmlElement(name = "SundayIndicator")
    protected Boolean sundayIndicator;

    /**
     * 获取mondayIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMondayIndicator() {
        return mondayIndicator;
    }

    /**
     * 设置mondayIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMondayIndicator(Boolean value) {
        this.mondayIndicator = value;
    }

    /**
     * 获取tuesdayIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTuesdayIndicator() {
        return tuesdayIndicator;
    }

    /**
     * 设置tuesdayIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTuesdayIndicator(Boolean value) {
        this.tuesdayIndicator = value;
    }

    /**
     * 获取wednesdayIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isWednesdayIndicator() {
        return wednesdayIndicator;
    }

    /**
     * 设置wednesdayIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setWednesdayIndicator(Boolean value) {
        this.wednesdayIndicator = value;
    }

    /**
     * 获取thursdayIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isThursdayIndicator() {
        return thursdayIndicator;
    }

    /**
     * 设置thursdayIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setThursdayIndicator(Boolean value) {
        this.thursdayIndicator = value;
    }

    /**
     * 获取fridayIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFridayIndicator() {
        return fridayIndicator;
    }

    /**
     * 设置fridayIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFridayIndicator(Boolean value) {
        this.fridayIndicator = value;
    }

    /**
     * 获取saturdayIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSaturdayIndicator() {
        return saturdayIndicator;
    }

    /**
     * 设置saturdayIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSaturdayIndicator(Boolean value) {
        this.saturdayIndicator = value;
    }

    /**
     * 获取sundayIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSundayIndicator() {
        return sundayIndicator;
    }

    /**
     * 设置sundayIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSundayIndicator(Boolean value) {
        this.sundayIndicator = value;
    }

}
