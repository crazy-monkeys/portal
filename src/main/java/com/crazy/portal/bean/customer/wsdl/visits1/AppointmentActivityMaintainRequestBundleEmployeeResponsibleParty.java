
package com.crazy.portal.bean.customer.wsdl.visits1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>AppointmentActivityMaintainRequestBundleEmployeeResponsibleParty complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="AppointmentActivityMaintainRequestBundleEmployeeResponsibleParty"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="BusinessPartnerInternalID" type="{http://sap.com/xi/AP/Common/GDT}BusinessPartnerInternalID" minOccurs="0"/&gt;
 *         &lt;element name="EmployeeID" type="{http://sap.com/xi/AP/Common/GDT}BusinessPartnerID" minOccurs="0"/&gt;
 *         &lt;element name="EmailURI" type="{http://sap.com/xi/AP/Common/GDT}EmailURI" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AppointmentActivityMaintainRequestBundleEmployeeResponsibleParty", namespace = "http://sap.com/xi/A1S/Global", propOrder = {
    "businessPartnerInternalID",
    "employeeID",
    "emailURI"
})
public class AppointmentActivityMaintainRequestBundleEmployeeResponsibleParty {

    @XmlElement(name = "BusinessPartnerInternalID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String businessPartnerInternalID;
    @XmlElement(name = "EmployeeID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String employeeID;
    @XmlElement(name = "EmailURI")
    protected EmailURI emailURI;

    /**
     * 获取businessPartnerInternalID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessPartnerInternalID() {
        return businessPartnerInternalID;
    }

    /**
     * 设置businessPartnerInternalID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessPartnerInternalID(String value) {
        this.businessPartnerInternalID = value;
    }

    /**
     * 获取employeeID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmployeeID() {
        return employeeID;
    }

    /**
     * 设置employeeID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmployeeID(String value) {
        this.employeeID = value;
    }

    /**
     * 获取emailURI属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EmailURI }
     *     
     */
    public EmailURI getEmailURI() {
        return emailURI;
    }

    /**
     * 设置emailURI属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EmailURI }
     *     
     */
    public void setEmailURI(EmailURI value) {
        this.emailURI = value;
    }

}
