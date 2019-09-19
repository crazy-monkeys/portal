
package com.crazy.portal.bean.customer.wsdl.customer.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>CustomerMaintainRequestBundleMarketingPermission complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CustomerMaintainRequestBundleMarketingPermission"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="BusinessPartnerUUID" type="{http://sap.com/xi/AP/Common/GDT}UUID" minOccurs="0"/&gt;
 *         &lt;element name="ChannelPermission" type="{http://sap.com/xi/A1S/Global}CustomerMaintainRequestBundleMarketingPermissionChannelPermission" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="CommunicationTypePermission" type="{http://sap.com/xi/A1S/Global}CustomerMaintainRequestBundleMarketingPermissionCommunicationTypePermissionConsolidated" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="ChannelPermissionCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" /&gt;
 *       &lt;attribute name="CommunicationTypePermissionTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" /&gt;
 *       &lt;attribute name="actionCode" type="{http://sap.com/xi/AP/Common/GDT}ActionCode" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerMaintainRequestBundleMarketingPermission", namespace = "http://sap.com/xi/A1S/Global", propOrder = {
    "businessPartnerUUID",
    "channelPermission",
    "communicationTypePermission"
})
public class CustomerMaintainRequestBundleMarketingPermission {

    @XmlElement(name = "BusinessPartnerUUID")
    protected UUID businessPartnerUUID;
    @XmlElement(name = "ChannelPermission")
    protected List<CustomerMaintainRequestBundleMarketingPermissionChannelPermission> channelPermission;
    @XmlElement(name = "CommunicationTypePermission")
    protected List<CustomerMaintainRequestBundleMarketingPermissionCommunicationTypePermissionConsolidated> communicationTypePermission;
    @XmlAttribute(name = "ChannelPermissionCompleteTransmissionIndicator")
    protected Boolean channelPermissionCompleteTransmissionIndicator;
    @XmlAttribute(name = "CommunicationTypePermissionTransmissionIndicator")
    protected Boolean communicationTypePermissionTransmissionIndicator;
    @XmlAttribute(name = "actionCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String actionCode;

    /**
     * 获取businessPartnerUUID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UUID }
     *     
     */
    public UUID getBusinessPartnerUUID() {
        return businessPartnerUUID;
    }

    /**
     * 设置businessPartnerUUID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UUID }
     *     
     */
    public void setBusinessPartnerUUID(UUID value) {
        this.businessPartnerUUID = value;
    }

    /**
     * Gets the value of the channelPermission property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the channelPermission property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChannelPermission().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustomerMaintainRequestBundleMarketingPermissionChannelPermission }
     * 
     * 
     */
    public List<CustomerMaintainRequestBundleMarketingPermissionChannelPermission> getChannelPermission() {
        if (channelPermission == null) {
            channelPermission = new ArrayList<CustomerMaintainRequestBundleMarketingPermissionChannelPermission>();
        }
        return this.channelPermission;
    }

    /**
     * Gets the value of the communicationTypePermission property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the communicationTypePermission property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCommunicationTypePermission().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustomerMaintainRequestBundleMarketingPermissionCommunicationTypePermissionConsolidated }
     * 
     * 
     */
    public List<CustomerMaintainRequestBundleMarketingPermissionCommunicationTypePermissionConsolidated> getCommunicationTypePermission() {
        if (communicationTypePermission == null) {
            communicationTypePermission = new ArrayList<CustomerMaintainRequestBundleMarketingPermissionCommunicationTypePermissionConsolidated>();
        }
        return this.communicationTypePermission;
    }

    /**
     * 获取channelPermissionCompleteTransmissionIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isChannelPermissionCompleteTransmissionIndicator() {
        return channelPermissionCompleteTransmissionIndicator;
    }

    /**
     * 设置channelPermissionCompleteTransmissionIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setChannelPermissionCompleteTransmissionIndicator(Boolean value) {
        this.channelPermissionCompleteTransmissionIndicator = value;
    }

    /**
     * 获取communicationTypePermissionTransmissionIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCommunicationTypePermissionTransmissionIndicator() {
        return communicationTypePermissionTransmissionIndicator;
    }

    /**
     * 设置communicationTypePermissionTransmissionIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCommunicationTypePermissionTransmissionIndicator(Boolean value) {
        this.communicationTypePermissionTransmissionIndicator = value;
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
