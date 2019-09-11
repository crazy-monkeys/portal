
package com.crazy.portal.bean.order.wsdl.create;

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
 *         &lt;element name="EsHeader" type="{urn:sap-com:document:sap:soap:functions:mc-style}ZsalesordercreateOutHeader"/&gt;
 *         &lt;element name="EtItems" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZsalesordercreateOutItem"/&gt;
 *         &lt;element name="ItItems" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZsalesordercreateInItem"/&gt;
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
@XmlRootElement(name = "ZrfcsdsalesordercreateResponse",namespace = "urn:sap-com:document:sap:soap:functions:mc-style")
public class ZrfcsdsalesordercreateResponse {

    @XmlElement(name = "EsHeader", required = true)
    protected ZsalesordercreateOutHeader esHeader;
    @XmlElement(name = "EtItems", required = true)
    protected TableOfZsalesordercreateOutItem etItems;
    @XmlElement(name = "ItItems", required = true)
    protected TableOfZsalesordercreateInItem itItems;

    /**
     * 获取esHeader属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ZsalesordercreateOutHeader }
     *     
     */
    public ZsalesordercreateOutHeader getEsHeader() {
        return esHeader;
    }

    /**
     * 设置esHeader属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ZsalesordercreateOutHeader }
     *     
     */
    public void setEsHeader(ZsalesordercreateOutHeader value) {
        this.esHeader = value;
    }

    /**
     * 获取etItems属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TableOfZsalesordercreateOutItem }
     *     
     */
    public TableOfZsalesordercreateOutItem getEtItems() {
        return etItems;
    }

    /**
     * 设置etItems属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZsalesordercreateOutItem }
     *     
     */
    public void setEtItems(TableOfZsalesordercreateOutItem value) {
        this.etItems = value;
    }

    /**
     * 获取itItems属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TableOfZsalesordercreateInItem }
     *     
     */
    public TableOfZsalesordercreateInItem getItItems() {
        return itItems;
    }

    /**
     * 设置itItems属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZsalesordercreateInItem }
     *     
     */
    public void setItItems(TableOfZsalesordercreateInItem value) {
        this.itItems = value;
    }

}
