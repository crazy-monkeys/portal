
package com.crazy.portal.bean.order.wsdl.delivery.update;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Zsdptdnitemchange complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Zsdptdnitemchange"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Operationtype" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="Deliveryitemno" type="{urn:sap-com:document:sap:rfc:functions}numeric6"/&gt;
 *         &lt;element name="Deliveryquantity" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Zsdptdnitemchange", propOrder = {
    "operationtype",
    "deliveryitemno",
    "deliveryquantity"
})
public class Zsdptdnitemchange {

    @XmlElement(name = "Operationtype", required = true)
    protected String operationtype;
    @XmlElement(name = "Deliveryitemno", required = true)
    protected String deliveryitemno;
    @XmlElement(name = "Deliveryquantity", required = true)
    protected BigDecimal deliveryquantity;

    /**
     * 获取operationtype属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperationtype() {
        return operationtype;
    }

    /**
     * 设置operationtype属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperationtype(String value) {
        this.operationtype = value;
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

}
