
package com.crazy.portal.bean.customer.wsdl.customer.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>MaintenanceAttachmentFolderDocumentProperty complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="MaintenanceAttachmentFolderDocumentProperty"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TechnicalID" type="{http://sap.com/xi/AP/Common/GDT}ObjectNodeTechnicalID" minOccurs="0"/&gt;
 *         &lt;element name="Name" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_Name" minOccurs="0"/&gt;
 *         &lt;element name="DataTypeFormatCode" type="{http://sap.com/xi/AP/Common/GDT}PropertyDataTypeFormatCode" minOccurs="0"/&gt;
 *         &lt;element name="VisibleIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *         &lt;element name="ChangeAllowedIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *         &lt;element name="MultipleValueIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *         &lt;element name="NamespaceURI" type="{http://sap.com/xi/AP/Common/GDT}NamespaceURI" minOccurs="0"/&gt;
 *         &lt;element name="Description" type="{http://sap.com/xi/AP/Common/GDT}Description" minOccurs="0"/&gt;
 *         &lt;element name="PropertyValue" type="{http://sap.com/xi/DocumentServices/Global}MaintenanceAttachmentFolderDocumentPropertyPropertyValue" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="PropertyValueListCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" /&gt;
 *       &lt;attribute name="ActionCode" type="{http://sap.com/xi/AP/Common/GDT}ActionCode" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MaintenanceAttachmentFolderDocumentProperty", namespace = "http://sap.com/xi/DocumentServices/Global", propOrder = {
    "technicalID",
    "name",
    "dataTypeFormatCode",
    "visibleIndicator",
    "changeAllowedIndicator",
    "multipleValueIndicator",
    "namespaceURI",
    "description",
    "propertyValue"
})
public class MaintenanceAttachmentFolderDocumentProperty {

    @XmlElement(name = "TechnicalID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String technicalID;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "DataTypeFormatCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String dataTypeFormatCode;
    @XmlElement(name = "VisibleIndicator")
    protected Boolean visibleIndicator;
    @XmlElement(name = "ChangeAllowedIndicator")
    protected Boolean changeAllowedIndicator;
    @XmlElement(name = "MultipleValueIndicator")
    protected Boolean multipleValueIndicator;
    @XmlElement(name = "NamespaceURI")
    protected NamespaceURI namespaceURI;
    @XmlElement(name = "Description")
    protected Description description;
    @XmlElement(name = "PropertyValue")
    protected List<MaintenanceAttachmentFolderDocumentPropertyPropertyValue> propertyValue;
    @XmlAttribute(name = "PropertyValueListCompleteTransmissionIndicator")
    protected Boolean propertyValueListCompleteTransmissionIndicator;
    @XmlAttribute(name = "ActionCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String actionCode;

    /**
     * 获取technicalID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTechnicalID() {
        return technicalID;
    }

    /**
     * 设置technicalID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTechnicalID(String value) {
        this.technicalID = value;
    }

    /**
     * 获取name属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * 获取dataTypeFormatCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataTypeFormatCode() {
        return dataTypeFormatCode;
    }

    /**
     * 设置dataTypeFormatCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataTypeFormatCode(String value) {
        this.dataTypeFormatCode = value;
    }

    /**
     * 获取visibleIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isVisibleIndicator() {
        return visibleIndicator;
    }

    /**
     * 设置visibleIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setVisibleIndicator(Boolean value) {
        this.visibleIndicator = value;
    }

    /**
     * 获取changeAllowedIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isChangeAllowedIndicator() {
        return changeAllowedIndicator;
    }

    /**
     * 设置changeAllowedIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setChangeAllowedIndicator(Boolean value) {
        this.changeAllowedIndicator = value;
    }

    /**
     * 获取multipleValueIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMultipleValueIndicator() {
        return multipleValueIndicator;
    }

    /**
     * 设置multipleValueIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMultipleValueIndicator(Boolean value) {
        this.multipleValueIndicator = value;
    }

    /**
     * 获取namespaceURI属性的值。
     * 
     * @return
     *     possible object is
     *     {@link NamespaceURI }
     *     
     */
    public NamespaceURI getNamespaceURI() {
        return namespaceURI;
    }

    /**
     * 设置namespaceURI属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link NamespaceURI }
     *     
     */
    public void setNamespaceURI(NamespaceURI value) {
        this.namespaceURI = value;
    }

    /**
     * 获取description属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Description }
     *     
     */
    public Description getDescription() {
        return description;
    }

    /**
     * 设置description属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Description }
     *     
     */
    public void setDescription(Description value) {
        this.description = value;
    }

    /**
     * Gets the value of the propertyValue property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the propertyValue property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPropertyValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MaintenanceAttachmentFolderDocumentPropertyPropertyValue }
     * 
     * 
     */
    public List<MaintenanceAttachmentFolderDocumentPropertyPropertyValue> getPropertyValue() {
        if (propertyValue == null) {
            propertyValue = new ArrayList<MaintenanceAttachmentFolderDocumentPropertyPropertyValue>();
        }
        return this.propertyValue;
    }

    /**
     * 获取propertyValueListCompleteTransmissionIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPropertyValueListCompleteTransmissionIndicator() {
        return propertyValueListCompleteTransmissionIndicator;
    }

    /**
     * 设置propertyValueListCompleteTransmissionIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPropertyValueListCompleteTransmissionIndicator(Boolean value) {
        this.propertyValueListCompleteTransmissionIndicator = value;
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
