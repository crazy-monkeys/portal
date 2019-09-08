
package com.crazy.portal.bean.customer.wsdl.order;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;


/**
 * <p>Zsdptdninfo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Zsdptdninfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Sapdeliveryid" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
 *         &lt;element name="Soldto" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
 *         &lt;element name="Sendto" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
 *         &lt;element name="Deliverydate" type="{urn:sap-com:document:sap:rfc:functions}date"/&gt;
 *         &lt;element name="Actualdeliverydate" type="{urn:sap-com:document:sap:rfc:functions}date"/&gt;
 *         &lt;element name="Deliveryitemno" type="{urn:sap-com:document:sap:rfc:functions}numeric6"/&gt;
 *         &lt;element name="Reforderid" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
 *         &lt;element name="Reforderitemno" type="{urn:sap-com:document:sap:rfc:functions}numeric6"/&gt;
 *         &lt;element name="Productid" type="{urn:sap-com:document:sap:rfc:functions}char18"/&gt;
 *         &lt;element name="Deliveryquantity" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/&gt;
 *         &lt;element name="Changedon" type="{urn:sap-com:document:sap:rfc:functions}date"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Zsdptdninfo", propOrder = {
    "sapdeliveryid",
    "soldto",
    "sendto",
    "deliverydate",
    "actualdeliverydate",
    "deliveryitemno",
    "reforderid",
    "reforderitemno",
    "productid",
    "deliveryquantity",
    "changedon"
})
public class Zsdptdninfo {

    @XmlElement(name = "Sapdeliveryid", required = true)
    protected String sapdeliveryid;
    @XmlElement(name = "Soldto", required = true)
    protected String soldto;
    @XmlElement(name = "Sendto", required = true)
    protected String sendto;
    @XmlElement(name = "Deliverydate", required = true)
    protected String deliverydate;
    @XmlElement(name = "Actualdeliverydate", required = true)
    protected String actualdeliverydate;
    @XmlElement(name = "Deliveryitemno", required = true)
    protected String deliveryitemno;
    @XmlElement(name = "Reforderid", required = true)
    protected String reforderid;
    @XmlElement(name = "Reforderitemno", required = true)
    protected String reforderitemno;
    @XmlElement(name = "Productid", required = true)
    protected String productid;
    @XmlElement(name = "Deliveryquantity", required = true)
    protected BigDecimal deliveryquantity;
    @XmlElement(name = "Changedon", required = true)
    protected String changedon;

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
     * 获取soldto属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSoldto() {
        return soldto;
    }

    /**
     * 设置soldto属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSoldto(String value) {
        this.soldto = value;
    }

    /**
     * 获取sendto属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSendto() {
        return sendto;
    }

    /**
     * 设置sendto属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSendto(String value) {
        this.sendto = value;
    }

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
     * 获取actualdeliverydate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActualdeliverydate() {
        return actualdeliverydate;
    }

    /**
     * 设置actualdeliverydate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActualdeliverydate(String value) {
        this.actualdeliverydate = value;
    }

    /**
     * 获取deliveryitemno属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryitemno() {
        return deliveryitemno;
    }

    /**
     * 设置deliveryitemno属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryitemno(String value) {
        this.deliveryitemno = value;
    }

    /**
     * 获取reforderid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReforderid() {
        return reforderid;
    }

    /**
     * 设置reforderid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReforderid(String value) {
        this.reforderid = value;
    }

    /**
     * 获取reforderitemno属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReforderitemno() {
        return reforderitemno;
    }

    /**
     * 设置reforderitemno属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReforderitemno(String value) {
        this.reforderitemno = value;
    }

    /**
     * 获取productid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductid() {
        return productid;
    }

    /**
     * 设置productid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductid(String value) {
        this.productid = value;
    }

    /**
     * 获取deliveryquantity属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDeliveryquantity() {
        return deliveryquantity;
    }

    /**
     * 设置deliveryquantity属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDeliveryquantity(BigDecimal value) {
        this.deliveryquantity = value;
    }

    /**
     * 获取changedon属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChangedon() {
        return changedon;
    }

    /**
     * 设置changedon属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChangedon(String value) {
        this.changedon = value;
    }

}
