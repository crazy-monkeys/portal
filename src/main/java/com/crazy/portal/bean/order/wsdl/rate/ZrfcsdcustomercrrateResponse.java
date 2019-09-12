
package com.crazy.portal.bean.order.wsdl.rate;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Omessag" type="{urn:sap-com:document:sap:rfc:functions}char100"/&gt;
 *         &lt;element name="Orate" type="{urn:sap-com:document:sap:rfc:functions}decimal13.8"/&gt;
 *         &lt;element name="Oreturn" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "omessag",
    "orate",
    "oreturn"
})
@XmlRootElement(name = "ZrfcsdcustomercrrateResponse",namespace = "urn:sap-com:document:sap:soap:functions:mc-style")
public class ZrfcsdcustomercrrateResponse {

    @XmlElement(name = "Omessag", required = true)
    protected String omessag;
    @XmlElement(name = "Orate", required = true)
    protected BigDecimal orate;
    @XmlElement(name = "Oreturn", required = true)
    protected String oreturn;

    /**
     * 获取omessag属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOmessag() {
        return omessag;
    }

    /**
     * 设置omessag属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOmessag(String value) {
        this.omessag = value;
    }

    /**
     * 获取orate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOrate() {
        return orate;
    }

    /**
     * 设置orate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOrate(BigDecimal value) {
        this.orate = value;
    }

    /**
     * 获取oreturn属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOreturn() {
        return oreturn;
    }

    /**
     * 设置oreturn属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOreturn(String value) {
        this.oreturn = value;
    }

}
