
package com.crazy.portal.bean.customer.dealer.credit;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Zsdscredit complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Zsdscredit"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Kunnr" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
 *         &lt;element name="Dmbtr" type="{urn:sap-com:document:sap:rfc:functions}curr13.2"/&gt;
 *         &lt;element name="Zoccupy" type="{urn:sap-com:document:sap:rfc:functions}curr13.2"/&gt;
 *         &lt;element name="Zremain" type="{urn:sap-com:document:sap:rfc:functions}curr13.2"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Zsdscredit", propOrder = {
    "kunnr",
    "dmbtr",
    "zoccupy",
    "zremain"
})
public class Zsdscredit {

    @XmlElement(name = "Kunnr", required = true)
    protected String kunnr;
    @XmlElement(name = "Dmbtr", required = true)
    protected BigDecimal dmbtr;
    @XmlElement(name = "Zoccupy", required = true)
    protected BigDecimal zoccupy;
    @XmlElement(name = "Zremain", required = true)
    protected BigDecimal zremain;

    /**
     * 获取kunnr属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKunnr() {
        return kunnr;
    }

    /**
     * 设置kunnr属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKunnr(String value) {
        this.kunnr = value;
    }

    /**
     * 获取dmbtr属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDmbtr() {
        return dmbtr;
    }

    /**
     * 设置dmbtr属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDmbtr(BigDecimal value) {
        this.dmbtr = value;
    }

    /**
     * 获取zoccupy属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getZoccupy() {
        return zoccupy;
    }

    /**
     * 设置zoccupy属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setZoccupy(BigDecimal value) {
        this.zoccupy = value;
    }

    /**
     * 获取zremain属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getZremain() {
        return zremain;
    }

    /**
     * 设置zremain属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setZremain(BigDecimal value) {
        this.zremain = value;
    }

}
