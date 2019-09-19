
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
 * <p>CommunicationArrangement complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CommunicationArrangement"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ObjectNodeSenderTechnicalID" type="{http://sap.com/xi/AP/Common/GDT}ObjectNodePartyTechnicalID" minOccurs="0"/&gt;
 *         &lt;element name="UUID" type="{http://sap.com/xi/AP/Common/GDT}UUID" minOccurs="0"/&gt;
 *         &lt;element name="CompoundServiceInterfaceCode" type="{http://sap.com/xi/AP/Common/GDT}CompoundServiceInterfaceCode" minOccurs="0"/&gt;
 *         &lt;element name="EnabledIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *         &lt;element name="CommunicationMediumTypeCode" type="{http://sap.com/xi/AP/Common/GDT}CommunicationMediumTypeCode" minOccurs="0"/&gt;
 *         &lt;element name="OutputRequestFormTemplateCode" type="{http://sap.com/xi/AP/Common/GDT}OutputRequestFormTemplateCode" minOccurs="0"/&gt;
 *         &lt;element name="EMailURI" type="{http://sap.com/xi/AP/Common/GDT}ENCRYPTED_MEDIUM_EmailURI" minOccurs="0"/&gt;
 *         &lt;element name="FacsimileFormattedNumberDescription" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_NUMBER_SHORT_Description" minOccurs="0"/&gt;
 *         &lt;element name="OutputCopyNumberValue" type="{http://sap.com/xi/AP/Common/GDT}NumberValue" minOccurs="0"/&gt;
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
@XmlType(name = "CommunicationArrangement", namespace = "http://sap.com/xi/A1S/Global", propOrder = {
    "objectNodeSenderTechnicalID",
    "uuid",
    "compoundServiceInterfaceCode",
    "enabledIndicator",
    "communicationMediumTypeCode",
    "outputRequestFormTemplateCode",
    "eMailURI",
    "facsimileFormattedNumberDescription",
    "outputCopyNumberValue"
})
public class CommunicationArrangement {

    @XmlElement(name = "ObjectNodeSenderTechnicalID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String objectNodeSenderTechnicalID;
    @XmlElement(name = "UUID")
    protected UUID uuid;
    @XmlElement(name = "CompoundServiceInterfaceCode")
    protected CompoundServiceInterfaceCode compoundServiceInterfaceCode;
    @XmlElement(name = "EnabledIndicator")
    protected Boolean enabledIndicator;
    @XmlElement(name = "CommunicationMediumTypeCode")
    protected CommunicationMediumTypeCode communicationMediumTypeCode;
    @XmlElement(name = "OutputRequestFormTemplateCode")
    protected OutputRequestFormTemplateCode outputRequestFormTemplateCode;
    @XmlElement(name = "EMailURI")
    protected ENCRYPTEDMEDIUMEmailURI eMailURI;
    @XmlElement(name = "FacsimileFormattedNumberDescription")
    protected String facsimileFormattedNumberDescription;
    @XmlElement(name = "OutputCopyNumberValue")
    protected Integer outputCopyNumberValue;
    @XmlAttribute(name = "actionCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String actionCode;

    /**
     * 获取objectNodeSenderTechnicalID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectNodeSenderTechnicalID() {
        return objectNodeSenderTechnicalID;
    }

    /**
     * 设置objectNodeSenderTechnicalID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectNodeSenderTechnicalID(String value) {
        this.objectNodeSenderTechnicalID = value;
    }

    /**
     * 获取uuid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UUID }
     *     
     */
    public UUID getUUID() {
        return uuid;
    }

    /**
     * 设置uuid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UUID }
     *     
     */
    public void setUUID(UUID value) {
        this.uuid = value;
    }

    /**
     * 获取compoundServiceInterfaceCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CompoundServiceInterfaceCode }
     *     
     */
    public CompoundServiceInterfaceCode getCompoundServiceInterfaceCode() {
        return compoundServiceInterfaceCode;
    }

    /**
     * 设置compoundServiceInterfaceCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CompoundServiceInterfaceCode }
     *     
     */
    public void setCompoundServiceInterfaceCode(CompoundServiceInterfaceCode value) {
        this.compoundServiceInterfaceCode = value;
    }

    /**
     * 获取enabledIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEnabledIndicator() {
        return enabledIndicator;
    }

    /**
     * 设置enabledIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEnabledIndicator(Boolean value) {
        this.enabledIndicator = value;
    }

    /**
     * 获取communicationMediumTypeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CommunicationMediumTypeCode }
     *     
     */
    public CommunicationMediumTypeCode getCommunicationMediumTypeCode() {
        return communicationMediumTypeCode;
    }

    /**
     * 设置communicationMediumTypeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CommunicationMediumTypeCode }
     *     
     */
    public void setCommunicationMediumTypeCode(CommunicationMediumTypeCode value) {
        this.communicationMediumTypeCode = value;
    }

    /**
     * 获取outputRequestFormTemplateCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link OutputRequestFormTemplateCode }
     *     
     */
    public OutputRequestFormTemplateCode getOutputRequestFormTemplateCode() {
        return outputRequestFormTemplateCode;
    }

    /**
     * 设置outputRequestFormTemplateCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link OutputRequestFormTemplateCode }
     *     
     */
    public void setOutputRequestFormTemplateCode(OutputRequestFormTemplateCode value) {
        this.outputRequestFormTemplateCode = value;
    }

    /**
     * 获取eMailURI属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ENCRYPTEDMEDIUMEmailURI }
     *     
     */
    public ENCRYPTEDMEDIUMEmailURI getEMailURI() {
        return eMailURI;
    }

    /**
     * 设置eMailURI属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ENCRYPTEDMEDIUMEmailURI }
     *     
     */
    public void setEMailURI(ENCRYPTEDMEDIUMEmailURI value) {
        this.eMailURI = value;
    }

    /**
     * 获取facsimileFormattedNumberDescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFacsimileFormattedNumberDescription() {
        return facsimileFormattedNumberDescription;
    }

    /**
     * 设置facsimileFormattedNumberDescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFacsimileFormattedNumberDescription(String value) {
        this.facsimileFormattedNumberDescription = value;
    }

    /**
     * 获取outputCopyNumberValue属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOutputCopyNumberValue() {
        return outputCopyNumberValue;
    }

    /**
     * 设置outputCopyNumberValue属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOutputCopyNumberValue(Integer value) {
        this.outputCopyNumberValue = value;
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
