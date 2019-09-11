
package com.crazy.portal.bean.order.wsdl.change;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;


/**
 * <p>ZsalesorderchangeOutItem complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ZsalesorderchangeOutItem"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Itemno" type="{urn:sap-com:document:sap:rfc:functions}char6"/&gt;
 *         &lt;element name="Productid" type="{urn:sap-com:document:sap:rfc:functions}char18"/&gt;
 *         &lt;element name="Price" type="{urn:sap-com:document:sap:rfc:functions}curr15.2"/&gt;
 *         &lt;element name="Netprice" type="{urn:sap-com:document:sap:rfc:functions}curr15.2"/&gt;
 *         &lt;element name="Sapquantity" type="{urn:sap-com:document:sap:rfc:functions}quantum15.3"/&gt;
 *         &lt;element name="CondUnit" type="{urn:sap-com:document:sap:rfc:functions}char3"/&gt;
 *         &lt;element name="Currency" type="{urn:sap-com:document:sap:rfc:functions}char5"/&gt;
 *         &lt;element name="Itemcategory" type="{urn:sap-com:document:sap:rfc:functions}char4"/&gt;
 *         &lt;element name="Refitemno" type="{urn:sap-com:document:sap:rfc:functions}char6"/&gt;
 *         &lt;element name="Refitemproductid" type="{urn:sap-com:document:sap:rfc:functions}char18"/&gt;
 *         &lt;element name="Platform" type="{urn:sap-com:document:sap:rfc:functions}char80"/&gt;
 *         &lt;element name="Abgru" type="{urn:sap-com:document:sap:rfc:functions}char2"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZsalesorderchangeOutItem", propOrder = {
    "itemno",
    "productid",
    "price",
    "netprice",
    "sapquantity",
    "condUnit",
    "currency",
    "itemcategory",
    "refitemno",
    "refitemproductid",
    "platform",
    "abgru"
})
public class ZsalesorderchangeOutItem {

    @XmlElement(name = "Itemno", required = true)
    protected String itemno;
    @XmlElement(name = "Productid", required = true)
    protected String productid;
    @XmlElement(name = "Price", required = true)
    protected BigDecimal price;
    @XmlElement(name = "Netprice", required = true)
    protected BigDecimal netprice;
    @XmlElement(name = "Sapquantity", required = true)
    protected BigDecimal sapquantity;
    @XmlElement(name = "CondUnit", required = true)
    protected String condUnit;
    @XmlElement(name = "Currency", required = true)
    protected String currency;
    @XmlElement(name = "Itemcategory", required = true)
    protected String itemcategory;
    @XmlElement(name = "Refitemno", required = true)
    protected String refitemno;
    @XmlElement(name = "Refitemproductid", required = true)
    protected String refitemproductid;
    @XmlElement(name = "Platform", required = true)
    protected String platform;
    @XmlElement(name = "Abgru", required = true)
    protected String abgru;

    /**
     * 获取itemno属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemno() {
        return itemno;
    }

    /**
     * 设置itemno属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemno(String value) {
        this.itemno = value;
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
     * 获取price属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置price属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPrice(BigDecimal value) {
        this.price = value;
    }

    /**
     * 获取netprice属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNetprice() {
        return netprice;
    }

    /**
     * 设置netprice属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNetprice(BigDecimal value) {
        this.netprice = value;
    }

    /**
     * 获取sapquantity属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSapquantity() {
        return sapquantity;
    }

    /**
     * 设置sapquantity属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSapquantity(BigDecimal value) {
        this.sapquantity = value;
    }

    /**
     * 获取condUnit属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCondUnit() {
        return condUnit;
    }

    /**
     * 设置condUnit属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCondUnit(String value) {
        this.condUnit = value;
    }

    /**
     * 获取currency属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * 设置currency属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

    /**
     * 获取itemcategory属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemcategory() {
        return itemcategory;
    }

    /**
     * 设置itemcategory属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemcategory(String value) {
        this.itemcategory = value;
    }

    /**
     * 获取refitemno属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefitemno() {
        return refitemno;
    }

    /**
     * 设置refitemno属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefitemno(String value) {
        this.refitemno = value;
    }

    /**
     * 获取refitemproductid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefitemproductid() {
        return refitemproductid;
    }

    /**
     * 设置refitemproductid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefitemproductid(String value) {
        this.refitemproductid = value;
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
     * 获取abgru属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbgru() {
        return abgru;
    }

    /**
     * 设置abgru属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbgru(String value) {
        this.abgru = value;
    }

}
