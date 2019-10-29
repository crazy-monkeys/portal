
package com.crazy.portal.bean.order.wsdl.delivery.update;

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
 *         &lt;element name="Deliverydate" type="{urn:sap-com:document:sap:rfc:functions}date"/&gt;
 *         &lt;element name="Itype" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="Sapdeliveryid" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
 *         &lt;element name="Titem" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZsdptdnitemchange"/&gt;
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
    "deliverydate",
    "itype",
    "sapdeliveryid",
    "titem"
})
@XmlRootElement(name = "Zrfcsddeliverychange")
public class Zrfcsddeliverychange {

    @XmlElement(name = "Deliverydate", required = true)
    protected String deliverydate;
    @XmlElement(name = "Itype", required = true)
    protected String itype;
    @XmlElement(name = "Sapdeliveryid", required = true)
    protected String sapdeliveryid;
    @XmlElement(name = "Titem", required = true)
    protected TableOfZsdptdnitemchange titem;

    /**
     * 获取deliverydate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliverydate() {
        return deliverydate;
    }

    /**
     * 设置deliverydate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliverydate(String value) {
        this.deliverydate = value;
    }

    /**
     * 获取itype属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItype() {
        return itype;
    }

    /**
     * 设置itype属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItype(String value) {
        this.itype = value;
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
     *     {@link TableOfZsdptdnitemchange }
     *     
     */
    public TableOfZsdptdnitemchange getTitem() {
        return titem;
    }

    /**
     * 设置titem属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZsdptdnitemchange }
     *     
     */
    public void setTitem(TableOfZsdptdnitemchange value) {
        this.titem = value;
    }

}
