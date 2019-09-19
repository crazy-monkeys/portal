
package com.crazy.portal.bean.customer.wsdl.customer.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>CustomerMaintainRequestBundleMarketingPermissionChannelPermission complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CustomerMaintainRequestBundleMarketingPermissionChannelPermission"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CommunicationMediumTypeCode" type="{http://sap.com/xi/Common/DataTypes}MarketingPermissionChannelTypeCode" minOccurs="0"/&gt;
 *         &lt;element name="MarketingPermissionCode" type="{http://sap.com/xi/Common/DataTypes}MarketingPermissionCode" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="actionCode" type="{http://sap.com/xi/AP/Common/GDT}ActionCode" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerMaintainRequestBundleMarketingPermissionChannelPermission", namespace = "http://sap.com/xi/A1S/Global", propOrder = {
    "communicationMediumTypeCode",
    "marketingPermissionCode"
})
public class CustomerMaintainRequestBundleMarketingPermissionChannelPermission {

    @XmlElement(name = "CommunicationMediumTypeCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String communicationMediumTypeCode;
    @XmlElement(name = "MarketingPermissionCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String marketingPermissionCode;
    @XmlAttribute(name = "actionCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String actionCode;

    /**
     * 获取communicationMediumTypeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommunicationMediumTypeCode() {
        return communicationMediumTypeCode;
    }

    /**
     * 设置communicationMediumTypeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommunicationMediumTypeCode(String value) {
        this.communicationMediumTypeCode = value;
    }

    /**
     * 获取marketingPermissionCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarketingPermissionCode() {
        return marketingPermissionCode;
    }

    /**
     * 设置marketingPermissionCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarketingPermissionCode(String value) {
        this.marketingPermissionCode = value;
    }

    /**
     * 获取actionCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionCode() {
        return actionCode;
    }

    /**
     * 设置actionCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionCode(String value) {
        this.actionCode = value;
    }

}
