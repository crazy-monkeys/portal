
package com.crazy.portal.bean.customer.wsdl.orgnation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>SystemAdministrativeData complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="SystemAdministrativeData"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CreationDateTime" type="{http://sap.com/xi/Common/DataTypes}GLOBAL_DateTime"/&gt;
 *         &lt;element name="CreationIdentityUUID" type="{http://sap.com/xi/Common/DataTypes}UUID"/&gt;
 *         &lt;element name="LastChangeDateTime" type="{http://sap.com/xi/Common/DataTypes}GLOBAL_DateTime"/&gt;
 *         &lt;element name="LastChangeIdentityUUID" type="{http://sap.com/xi/Common/DataTypes}UUID"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SystemAdministrativeData", namespace = "http://sap.com/xi/Common/DataTypes", propOrder = {
    "creationDateTime",
    "creationIdentityUUID",
    "lastChangeDateTime",
    "lastChangeIdentityUUID"
})
public class SystemAdministrativeData {

    @XmlElement(name = "CreationDateTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar creationDateTime;
    @XmlElement(name = "CreationIdentityUUID", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String creationIdentityUUID;
    @XmlElement(name = "LastChangeDateTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastChangeDateTime;
    @XmlElement(name = "LastChangeIdentityUUID", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String lastChangeIdentityUUID;

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
     * 获取creationIdentityUUID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreationIdentityUUID() {
        return creationIdentityUUID;
    }

    /**
     * 设置creationIdentityUUID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreationIdentityUUID(String value) {
        this.creationIdentityUUID = value;
    }

    /**
     * 获取lastChangeDateTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastChangeDateTime() {
        return lastChangeDateTime;
    }

    /**
     * 设置lastChangeDateTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastChangeDateTime(XMLGregorianCalendar value) {
        this.lastChangeDateTime = value;
    }

    /**
     * 获取lastChangeIdentityUUID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastChangeIdentityUUID() {
        return lastChangeIdentityUUID;
    }

    /**
     * 设置lastChangeIdentityUUID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastChangeIdentityUUID(String value) {
        this.lastChangeIdentityUUID = value;
    }

}