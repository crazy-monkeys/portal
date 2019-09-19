
package com.crazy.portal.bean.customer.wsdl.customer.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>CustomerMaintainRequestBundleBlockingReasons complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CustomerMaintainRequestBundleBlockingReasons"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="OrderBlockingReasonCode" type="{http://sap.com/xi/AP/Common/GDT}CustomerBlockingReasonCode" minOccurs="0"/&gt;
 *         &lt;element name="DeliveryBlockingReasonCode" type="{http://sap.com/xi/AP/Common/GDT}CustomerTransactionDocumentFulfilmentBlockingReasonCode" minOccurs="0"/&gt;
 *         &lt;element name="BillingBlockingReasonCode" type="{http://sap.com/xi/AP/Common/GDT}InvoicingBlockingReasonCode" minOccurs="0"/&gt;
 *         &lt;element name="SalesSupportBlockingIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerMaintainRequestBundleBlockingReasons", namespace = "http://sap.com/xi/A1S/Global", propOrder = {
    "orderBlockingReasonCode",
    "deliveryBlockingReasonCode",
    "billingBlockingReasonCode",
    "salesSupportBlockingIndicator"
})
public class CustomerMaintainRequestBundleBlockingReasons {

    @XmlElement(name = "OrderBlockingReasonCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String orderBlockingReasonCode;
    @XmlElement(name = "DeliveryBlockingReasonCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String deliveryBlockingReasonCode;
    @XmlElement(name = "BillingBlockingReasonCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String billingBlockingReasonCode;
    @XmlElement(name = "SalesSupportBlockingIndicator")
    protected Boolean salesSupportBlockingIndicator;

    /**
     * 获取orderBlockingReasonCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderBlockingReasonCode() {
        return orderBlockingReasonCode;
    }

    /**
     * 设置orderBlockingReasonCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderBlockingReasonCode(String value) {
        this.orderBlockingReasonCode = value;
    }

    /**
     * 获取deliveryBlockingReasonCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryBlockingReasonCode() {
        return deliveryBlockingReasonCode;
    }

    /**
     * 设置deliveryBlockingReasonCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryBlockingReasonCode(String value) {
        this.deliveryBlockingReasonCode = value;
    }

    /**
     * 获取billingBlockingReasonCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingBlockingReasonCode() {
        return billingBlockingReasonCode;
    }

    /**
     * 设置billingBlockingReasonCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingBlockingReasonCode(String value) {
        this.billingBlockingReasonCode = value;
    }

    /**
     * 获取salesSupportBlockingIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSalesSupportBlockingIndicator() {
        return salesSupportBlockingIndicator;
    }

    /**
     * 设置salesSupportBlockingIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSalesSupportBlockingIndicator(Boolean value) {
        this.salesSupportBlockingIndicator = value;
    }

}
