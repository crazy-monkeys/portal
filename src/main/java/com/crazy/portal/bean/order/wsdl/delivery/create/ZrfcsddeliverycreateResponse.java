
package com.crazy.portal.bean.order.wsdl.delivery.create;

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
 *         &lt;element name="Resultmessage" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Resulttype" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="Sapdeliveryid" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
 *         &lt;element name="Titem" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZsdptdnitem"/&gt;
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
    "resultmessage",
    "resulttype",
    "sapdeliveryid",
    "titem"
})
@XmlRootElement(name = "ZrfcsddeliverycreateResponse",namespace = "urn:sap-com:document:sap:soap:functions:mc-style")
public class ZrfcsddeliverycreateResponse {

    @XmlElement(name = "Resultmessage", required = true)
    protected String resultmessage;
    @XmlElement(name = "Resulttype", required = true)
    protected String resulttype;
    @XmlElement(name = "Sapdeliveryid", required = true)
    protected String sapdeliveryid;
    @XmlElement(name = "Titem", required = true)
    protected TableOfZsdptdnitem titem;

    /**
     * 获取resultmessage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultmessage() {
        return resultmessage;
    }

    /**
     * 设置resultmessage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultmessage(String value) {
        this.resultmessage = value;
    }

    /**
     * 获取resulttype属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResulttype() {
        return resulttype;
    }

    /**
     * 设置resulttype属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResulttype(String value) {
        this.resulttype = value;
    }

    /**
     * 获取sapdeliveryid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSapdeliveryid() {
        return sapdeliveryid;
    }

    /**
     * 设置sapdeliveryid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSapdeliveryid(String value) {
        this.sapdeliveryid = value;
    }

    /**
     * 获取titem属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TableOfZsdptdnitem }
     *     
     */
    public TableOfZsdptdnitem getTitem() {
        return titem;
    }

    /**
     * 设置titem属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZsdptdnitem }
     *     
     */
    public void setTitem(TableOfZsdptdnitem value) {
        this.titem = value;
    }

}
