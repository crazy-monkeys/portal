
package com.crazy.portal.bean.customer.wsdl.visits1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>ActivityMaintainRequestBundleText complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ActivityMaintainRequestBundleText"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ObjectNodeSenderTechnicalID" type="{http://sap.com/xi/AP/Common/GDT}ObjectNodePartyTechnicalID" minOccurs="0"/&gt;
 *         &lt;element name="TextID" type="{http://sap.com/xi/AP/Common/GDT}TextCollectionTextID" minOccurs="0"/&gt;
 *         &lt;element name="TextTypeCode" type="{http://sap.com/xi/AP/Common/GDT}TextCollectionTextTypeCode" minOccurs="0"/&gt;
 *         &lt;element name="ContentText" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_Text" minOccurs="0"/&gt;
 *         &lt;element name="CreationDateTime" type="{http://sap.com/xi/BASIS/Global}GLOBAL_DateTime" minOccurs="0"/&gt;
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
@XmlType(name = "ActivityMaintainRequestBundleText", namespace = "http://sap.com/xi/A1S/Global", propOrder = {
    "objectNodeSenderTechnicalID",
    "textID",
    "textTypeCode",
    "contentText",
    "creationDateTime"
})
public class ActivityMaintainRequestBundleText {

    @XmlElement(name = "ObjectNodeSenderTechnicalID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String objectNodeSenderTechnicalID;
    @XmlElement(name = "TextID")
    protected TextCollectionTextID textID;
    @XmlElement(name = "TextTypeCode")
    protected TextCollectionTextTypeCode textTypeCode;
    @XmlElement(name = "ContentText")
    protected String contentText;
    @XmlElement(name = "CreationDateTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar creationDateTime;
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
     * 获取textID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TextCollectionTextID }
     *     
     */
    public TextCollectionTextID getTextID() {
        return textID;
    }

    /**
     * 设置textID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TextCollectionTextID }
     *     
     */
    public void setTextID(TextCollectionTextID value) {
        this.textID = value;
    }

    /**
     * 获取textTypeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TextCollectionTextTypeCode }
     *     
     */
    public TextCollectionTextTypeCode getTextTypeCode() {
        return textTypeCode;
    }

    /**
     * 设置textTypeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TextCollectionTextTypeCode }
     *     
     */
    public void setTextTypeCode(TextCollectionTextTypeCode value) {
        this.textTypeCode = value;
    }

    /**
     * 获取contentText属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentText() {
        return contentText;
    }

    /**
     * 设置contentText属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentText(String value) {
        this.contentText = value;
    }

    /**
     * 获取creationDateTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreationDateTime() {
        return creationDateTime;
    }

    /**
     * 设置creationDateTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreationDateTime(XMLGregorianCalendar value) {
        this.creationDateTime = value;
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
