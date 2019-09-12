
package com.crazy.portal.bean.order.wsdl.change;

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
 *         &lt;element name="EsHeader" type="{urn:sap-com:document:sap:soap:functions:mc-style}ZsalesorderchangeOutHeader"/&gt;
 *         &lt;element name="EtItems" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZsalesorderchangeOutItem"/&gt;
 *         &lt;element name="ItItems" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZsalesorderchangeInItem"/&gt;
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
    "esHeader",
    "etItems",
    "itItems"
})
@XmlRootElement(name = "ZrfcsdsalesorderchangeResponse",namespace = "urn:sap-com:document:sap:soap:functions:mc-style")
public class ZrfcsdsalesorderchangeResponse {

    @XmlElement(name = "EsHeader", required = true)
    protected ZsalesorderchangeOutHeader esHeader;
    @XmlElement(name = "EtItems", required = true)
    protected TableOfZsalesorderchangeOutItem etItems;
    @XmlElement(name = "ItItems", required = true)
    protected TableOfZsalesorderchangeInItem itItems;

    /**
     * 获取esHeader属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ZsalesorderchangeOutHeader }
     *     
     */
    public ZsalesorderchangeOutHeader getEsHeader() {
        return esHeader;
    }

    /**
     * 设置esHeader属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ZsalesorderchangeOutHeader }
     *     
     */
    public void setEsHeader(ZsalesorderchangeOutHeader value) {
        this.esHeader = value;
    }

    /**
     * 获取etItems属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TableOfZsalesorderchangeOutItem }
     *     
     */
    public TableOfZsalesorderchangeOutItem getEtItems() {
        return etItems;
    }

    /**
     * 设置etItems属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZsalesorderchangeOutItem }
     *     
     */
    public void setEtItems(TableOfZsalesorderchangeOutItem value) {
        this.etItems = value;
    }

    /**
     * 获取itItems属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TableOfZsalesorderchangeInItem }
     *     
     */
    public TableOfZsalesorderchangeInItem getItItems() {
        return itItems;
    }

    /**
     * 设置itItems属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZsalesorderchangeInItem }
     *     
     */
    public void setItItems(TableOfZsalesorderchangeInItem value) {
        this.itItems = value;
    }

}
