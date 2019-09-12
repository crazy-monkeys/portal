
package com.crazy.portal.bean.order.wsdl.create;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;


/**
 * <p>ZsalesordercreateInItem complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ZsalesordercreateInItem"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Sequenceno" type="{urn:sap-com:document:sap:rfc:functions}char6"/&gt;
 *         &lt;element name="Productid" type="{urn:sap-com:document:sap:rfc:functions}char18"/&gt;
 *         &lt;element name="Orderquantity" type="{urn:sap-com:document:sap:rfc:functions}quantum15.3"/&gt;
 *         &lt;element name="Platform" type="{urn:sap-com:document:sap:rfc:functions}char80"/&gt;
 *         &lt;element name="Requestdate" type="{urn:sap-com:document:sap:rfc:functions}char8"/&gt;
 *         &lt;element name="Refsaporderitemno" type="{urn:sap-com:document:sap:rfc:functions}char6"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZsalesordercreateInItem", propOrder = {
    "sequenceno",
    "productid",
    "orderquantity",
    "platform",
    "requestdate",
    "refsaporderitemno"
})
public class ZsalesordercreateInItem {

    @XmlElement(name = "Sequenceno", required = true)
    protected String sequenceno;
    @XmlElement(name = "Productid", required = true)
    protected String productid;
    @XmlElement(name = "Orderquantity", required = true)
    protected BigDecimal orderquantity;
    @XmlElement(name = "Platform", required = true)
    protected String platform;
    @XmlElement(name = "Requestdate", required = true)
    protected String requestdate;
    @XmlElement(name = "Refsaporderitemno", required = true)
    protected String refsaporderitemno;

    /**
     * 获取sequenceno属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSequenceno() {
        return sequenceno;
    }

    /**
     * 设置sequenceno属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSequenceno(String value) {
        this.sequenceno = value;
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
     * 获取orderquantity属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOrderquantity() {
        return orderquantity;
    }

    /**
     * 设置orderquantity属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOrderquantity(BigDecimal value) {
        this.orderquantity = value;
    }

    /**
     * 获取platform属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * 设置platform属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlatform(String value) {
        this.platform = value;
    }

    /**
     * 获取requestdate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestdate() {
        return requestdate;
    }

    /**
     * 设置requestdate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestdate(String value) {
        this.requestdate = value;
    }

    /**
     * 获取refsaporderitemno属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefsaporderitemno() {
        return refsaporderitemno;
    }

    /**
     * 设置refsaporderitemno属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefsaporderitemno(String value) {
        this.refsaporderitemno = value;
    }

}
