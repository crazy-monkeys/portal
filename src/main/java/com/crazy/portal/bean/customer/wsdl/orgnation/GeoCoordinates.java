
package com.crazy.portal.bean.customer.wsdl.orgnation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>GeoCoordinates complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="GeoCoordinates"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="LatitudeMeasure" type="{http://sap.com/xi/AP/Common/GDT}Measure"/&gt;
 *         &lt;element name="LongitudeMeasure" type="{http://sap.com/xi/AP/Common/GDT}Measure"/&gt;
 *         &lt;element name="AltitudeMeasure" type="{http://sap.com/xi/AP/Common/GDT}Measure" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GeoCoordinates", propOrder = {
    "latitudeMeasure",
    "longitudeMeasure",
    "altitudeMeasure"
})
public class GeoCoordinates {

    @XmlElement(name = "LatitudeMeasure", required = true)
    protected Measure latitudeMeasure;
    @XmlElement(name = "LongitudeMeasure", required = true)
    protected Measure longitudeMeasure;
    @XmlElement(name = "AltitudeMeasure")
    protected Measure altitudeMeasure;

    /**
     * 获取latitudeMeasure属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Measure }
     *     
     */
    public Measure getLatitudeMeasure() {
        return latitudeMeasure;
    }

    /**
     * 设置latitudeMeasure属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Measure }
     *     
     */
    public void setLatitudeMeasure(Measure value) {
        this.latitudeMeasure = value;
    }

    /**
     * 获取longitudeMeasure属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Measure }
     *     
     */
    public Measure getLongitudeMeasure() {
        return longitudeMeasure;
    }

    /**
     * 设置longitudeMeasure属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Measure }
     *     
     */
    public void setLongitudeMeasure(Measure value) {
        this.longitudeMeasure = value;
    }

    /**
     * 获取altitudeMeasure属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Measure }
     *     
     */
    public Measure getAltitudeMeasure() {
        return altitudeMeasure;
    }

    /**
     * 设置altitudeMeasure属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Measure }
     *     
     */
    public void setAltitudeMeasure(Measure value) {
        this.altitudeMeasure = value;
    }

}
