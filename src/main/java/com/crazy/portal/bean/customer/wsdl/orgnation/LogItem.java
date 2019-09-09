
package com.crazy.portal.bean.customer.wsdl.orgnation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>LogItem complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="LogItem"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TypeID" type="{http://sap.com/xi/AP/Common/GDT}LogItemTypeID" minOccurs="0"/&gt;
 *         &lt;element name="CategoryCode" type="{http://sap.com/xi/AP/Common/GDT}LogItemCategoryCode" minOccurs="0"/&gt;
 *         &lt;element name="SeverityCode" type="{http://sap.com/xi/AP/Common/GDT}LogItemSeverityCode" minOccurs="0"/&gt;
 *         &lt;element name="ReferenceObjectNodeSenderTechnicalID" type="{http://sap.com/xi/AP/Common/GDT}ObjectNodePartyTechnicalID" minOccurs="0"/&gt;
 *         &lt;element name="ReferenceMessageElementName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_Name" minOccurs="0"/&gt;
 *         &lt;element name="Note" type="{http://sap.com/xi/AP/Common/GDT}LogItemNote"/&gt;
 *         &lt;element name="NoteTemplateText" type="{http://sap.com/xi/AP/Common/GDT}LogItemNoteTemplateText" minOccurs="0"/&gt;
 *         &lt;element name="LogItemNotePlaceholderSubstitutionList" type="{http://sap.com/xi/AP/Common/GDT}LogItemNotePlaceholderSubstitutionList" minOccurs="0"/&gt;
 *         &lt;element name="WebURI" type="{http://sap.com/xi/AP/Common/GDT}WebURI" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LogItem", propOrder = {
    "typeID",
    "categoryCode",
    "severityCode",
    "referenceObjectNodeSenderTechnicalID",
    "referenceMessageElementName",
    "note",
    "noteTemplateText",
    "logItemNotePlaceholderSubstitutionList",
    "webURI"
})
public class LogItem {

    @XmlElement(name = "TypeID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String typeID;
    @XmlElement(name = "CategoryCode")
    protected LogItemCategoryCode categoryCode;
    @XmlElement(name = "SeverityCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String severityCode;
    @XmlElement(name = "ReferenceObjectNodeSenderTechnicalID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String referenceObjectNodeSenderTechnicalID;
    @XmlElement(name = "ReferenceMessageElementName")
    protected String referenceMessageElementName;
    @XmlElement(name = "Note", required = true)
    protected String note;
    @XmlElement(name = "NoteTemplateText")
    protected String noteTemplateText;
    @XmlElement(name = "LogItemNotePlaceholderSubstitutionList")
    protected LogItemNotePlaceholderSubstitutionList logItemNotePlaceholderSubstitutionList;
    @XmlElement(name = "WebURI")
    @XmlSchemaType(name = "anyURI")
    protected String webURI;

    /**
     * 获取typeID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeID() {
        return typeID;
    }

    /**
     * 设置typeID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeID(String value) {
        this.typeID = value;
    }

    /**
     * 获取categoryCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link LogItemCategoryCode }
     *     
     */
    public LogItemCategoryCode getCategoryCode() {
        return categoryCode;
    }

    /**
     * 设置categoryCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link LogItemCategoryCode }
     *     
     */
    public void setCategoryCode(LogItemCategoryCode value) {
        this.categoryCode = value;
    }

    /**
     * 获取severityCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeverityCode() {
        return severityCode;
    }

    /**
     * 设置severityCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeverityCode(String value) {
        this.severityCode = value;
    }

    /**
     * 获取referenceObjectNodeSenderTechnicalID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceObjectNodeSenderTechnicalID() {
        return referenceObjectNodeSenderTechnicalID;
    }

    /**
     * 设置referenceObjectNodeSenderTechnicalID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceObjectNodeSenderTechnicalID(String value) {
        this.referenceObjectNodeSenderTechnicalID = value;
    }

    /**
     * 获取referenceMessageElementName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceMessageElementName() {
        return referenceMessageElementName;
    }

    /**
     * 设置referenceMessageElementName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceMessageElementName(String value) {
        this.referenceMessageElementName = value;
    }

    /**
     * 获取note属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNote() {
        return note;
    }

    /**
     * 设置note属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNote(String value) {
        this.note = value;
    }

    /**
     * 获取noteTemplateText属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoteTemplateText() {
        return noteTemplateText;
    }

    /**
     * 设置noteTemplateText属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoteTemplateText(String value) {
        this.noteTemplateText = value;
    }

    /**
     * 获取logItemNotePlaceholderSubstitutionList属性的值。
     * 
     * @return
     *     possible object is
     *     {@link LogItemNotePlaceholderSubstitutionList }
     *     
     */
    public LogItemNotePlaceholderSubstitutionList getLogItemNotePlaceholderSubstitutionList() {
        return logItemNotePlaceholderSubstitutionList;
    }

    /**
     * 设置logItemNotePlaceholderSubstitutionList属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link LogItemNotePlaceholderSubstitutionList }
     *     
     */
    public void setLogItemNotePlaceholderSubstitutionList(LogItemNotePlaceholderSubstitutionList value) {
        this.logItemNotePlaceholderSubstitutionList = value;
    }

    /**
     * 获取webURI属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebURI() {
        return webURI;
    }

    /**
     * 设置webURI属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebURI(String value) {
        this.webURI = value;
    }

}
