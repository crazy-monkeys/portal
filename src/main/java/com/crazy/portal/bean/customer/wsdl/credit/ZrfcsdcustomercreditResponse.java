
package com.crazy.portal.bean.customer.wsdl.credit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="Ocredit" type="{urn:sap-com:document:sap:soap:functions:mc-style}Zsdscredit"/&gt;
 *         &lt;element name="Omessag" type="{urn:sap-com:document:sap:rfc:functions}char100"/&gt;
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
    "ocredit",
    "omessag",
    "oreturn"
})
@XmlRootElement(name = "ZrfcsdcustomercreditResponse")
public class ZrfcsdcustomercreditResponse {

    @XmlElement(name = "Ocredit", required = true)
    protected Zsdscredit ocredit;
    @XmlElement(name = "Omessag", required = true)
    protected String omessag;
    @XmlElement(name = "Oreturn", required = true)
    protected String oreturn;

    /**
     * 获取ocredit属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Zsdscredit }
     *     
     */
    public Zsdscredit getOcredit() {
        return ocredit;
    }

    /**
     * 设置ocredit属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Zsdscredit }
     *     
     */
    public void setOcredit(Zsdscredit value) {
        this.ocredit = value;
    }

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
