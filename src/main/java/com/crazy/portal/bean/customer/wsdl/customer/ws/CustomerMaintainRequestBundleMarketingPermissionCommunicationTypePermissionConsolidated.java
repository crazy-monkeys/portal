
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
 * <p>CustomerMaintainRequestBundleMarketingPermissionCommunicationTypePermissionConsolidated complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CustomerMaintainRequestBundleMarketingPermissionCommunicationTypePermissionConsolidated"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CommunicationTypeCode" type="{http://sap.com/xi/Common/DataTypes}MarketingCommunicationTypeCode" minOccurs="0"/&gt;
 *         &lt;element name="MarketingCommunicationTypeSubscriptionIndicator" type="{http://sap.com/xi/BASIS/Global}Indicator" minOccurs="0"/&gt;
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
@XmlType(name = "CustomerMaintainRequestBundleMarketingPermissionCommunicationTypePermissionConsolidated", namespace = "http://sap.com/xi/A1S/Global", propOrder = {
    "communicationTypeCode",
    "marketingCommunicationTypeSubscriptionIndicator"
})
public class CustomerMaintainRequestBundleMarketingPermissionCommunicationTypePermissionConsolidated {

    @XmlElement(name = "CommunicationTypeCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String communicationTypeCode;
    @XmlElement(name = "MarketingCommunicationTypeSubscriptionIndicator")
    protected Boolean marketingCommunicationTypeSubscriptionIndicator;
    @XmlAttribute(name = "actionCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String actionCode;

    /**
     * 获取communicationTypeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommunicationTypeCode() {
        return communicationTypeCode;
    }

    /**
     * 设置communicationTypeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommunicationTypeCode(String value) {
        this.communicationTypeCode = value;
    }

    /**
     * 获取marketingCommunicationTypeSubscriptionIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMarketingCommunicationTypeSubscriptionIndicator() {
        return marketingCommunicationTypeSubscriptionIndicator;
    }

    /**
     * 设置marketingCommunicationTypeSubscriptionIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMarketingCommunicationTypeSubscriptionIndicator(Boolean value) {
        this.marketingCommunicationTypeSubscriptionIndicator = value;
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
