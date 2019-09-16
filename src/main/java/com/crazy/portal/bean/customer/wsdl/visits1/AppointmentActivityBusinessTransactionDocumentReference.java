
package com.crazy.portal.bean.customer.wsdl.visits1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>AppointmentActivityBusinessTransactionDocumentReference complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="AppointmentActivityBusinessTransactionDocumentReference"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ObjectNodeSenderTechnicalID" type="{http://sap.com/xi/AP/Common/GDT}ObjectNodePartyTechnicalID" minOccurs="0"/&gt;
 *         &lt;element name="ID" type="{http://sap.com/xi/AP/Common/GDT}BusinessTransactionDocumentID" minOccurs="0"/&gt;
 *         &lt;element name="UUID" type="{http://sap.com/xi/AP/Common/GDT}UUID" minOccurs="0"/&gt;
 *         &lt;element name="TypeCode" type="{http://sap.com/xi/AP/Common/GDT}BusinessTransactionDocumentTypeCode" minOccurs="0"/&gt;
 *         &lt;element name="ItemID" type="{http://sap.com/xi/AP/Common/GDT}BusinessTransactionDocumentItemID" minOccurs="0"/&gt;
 *         &lt;element name="ItemUUID" type="{http://sap.com/xi/AP/Common/GDT}UUID" minOccurs="0"/&gt;
 *         &lt;element name="ItemTypeCode" type="{http://sap.com/xi/AP/Common/GDT}BusinessTransactionDocumentItemTypeCode" minOccurs="0"/&gt;
 *         &lt;element name="RoleCode" type="{http://sap.com/xi/AP/Common/GDT}BusinessTransactionDocumentRelationshipRoleCode" minOccurs="0"/&gt;
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
@XmlType(name = "AppointmentActivityBusinessTransactionDocumentReference", namespace = "http://sap.com/xi/A1S/Global", propOrder = {
    "objectNodeSenderTechnicalID",
    "id",
    "uuid",
    "typeCode",
    "itemID",
    "itemUUID",
    "itemTypeCode",
    "roleCode"
})
public class AppointmentActivityBusinessTransactionDocumentReference {

    @XmlElement(name = "ObjectNodeSenderTechnicalID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String objectNodeSenderTechnicalID;
    @XmlElement(name = "ID")
    protected BusinessTransactionDocumentID id;
    @XmlElement(name = "UUID")
    protected UUID uuid;
    @XmlElement(name = "TypeCode")
    protected BusinessTransactionDocumentTypeCode typeCode;
    @XmlElement(name = "ItemID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String itemID;
    @XmlElement(name = "ItemUUID")
    protected UUID itemUUID;
    @XmlElement(name = "ItemTypeCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String itemTypeCode;
    @XmlElement(name = "RoleCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String roleCode;
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
     * 获取id属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BusinessTransactionDocumentID }
     *     
     */
    public BusinessTransactionDocumentID getID() {
        return id;
    }

    /**
     * 设置id属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessTransactionDocumentID }
     *     
     */
    public void setID(BusinessTransactionDocumentID value) {
        this.id = value;
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
     * 获取typeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BusinessTransactionDocumentTypeCode }
     *     
     */
    public BusinessTransactionDocumentTypeCode getTypeCode() {
        return typeCode;
    }

    /**
     * 设置typeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessTransactionDocumentTypeCode }
     *     
     */
    public void setTypeCode(BusinessTransactionDocumentTypeCode value) {
        this.typeCode = value;
    }

    /**
     * 获取itemID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemID() {
        return itemID;
    }

    /**
     * 设置itemID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemID(String value) {
        this.itemID = value;
    }

    /**
     * 获取itemUUID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UUID }
     *     
     */
    public UUID getItemUUID() {
        return itemUUID;
    }

    /**
     * 设置itemUUID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UUID }
     *     
     */
    public void setItemUUID(UUID value) {
        this.itemUUID = value;
    }

    /**
     * 获取itemTypeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemTypeCode() {
        return itemTypeCode;
    }

    /**
     * 设置itemTypeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemTypeCode(String value) {
        this.itemTypeCode = value;
    }

    /**
     * 获取roleCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * 设置roleCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoleCode(String value) {
        this.roleCode = value;
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
