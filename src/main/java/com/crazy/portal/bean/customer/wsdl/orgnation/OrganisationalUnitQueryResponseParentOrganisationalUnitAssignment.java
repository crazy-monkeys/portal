
package com.crazy.portal.bean.customer.wsdl.orgnation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>OrganisationalUnitQueryResponseParentOrganisationalUnitAssignment complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="OrganisationalUnitQueryResponseParentOrganisationalUnitAssignment"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ValidityPeriod" type="{http://sap.com/xi/AP/Common/GDT}CLOSED_DatePeriod" minOccurs="0"/&gt;
 *         &lt;element name="ParentOrganisationalUnitUUID" type="{http://sap.com/xi/AP/Common/GDT}UUID" minOccurs="0"/&gt;
 *         &lt;element name="ParentOrganisationalUnitID" type="{http://sap.com/xi/AP/Common/GDT}OrganisationalCentreID" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrganisationalUnitQueryResponseParentOrganisationalUnitAssignment", namespace = "http://sap.com/xi/AP/FO/MOM/Global", propOrder = {
    "validityPeriod",
    "parentOrganisationalUnitUUID",
    "parentOrganisationalUnitID"
})
public class OrganisationalUnitQueryResponseParentOrganisationalUnitAssignment {

    @XmlElement(name = "ValidityPeriod")
    protected CLOSEDDatePeriod validityPeriod;
    @XmlElement(name = "ParentOrganisationalUnitUUID")
    protected UUID parentOrganisationalUnitUUID;
    @XmlElement(name = "ParentOrganisationalUnitID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String parentOrganisationalUnitID;

    /**
     * 获取validityPeriod属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CLOSEDDatePeriod }
     *     
     */
    public CLOSEDDatePeriod getValidityPeriod() {
        return validityPeriod;
    }

    /**
     * 设置validityPeriod属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CLOSEDDatePeriod }
     *     
     */
    public void setValidityPeriod(CLOSEDDatePeriod value) {
        this.validityPeriod = value;
    }

    /**
     * 获取parentOrganisationalUnitUUID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UUID }
     *     
     */
    public UUID getParentOrganisationalUnitUUID() {
        return parentOrganisationalUnitUUID;
    }

    /**
     * 设置parentOrganisationalUnitUUID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UUID }
     *     
     */
    public void setParentOrganisationalUnitUUID(UUID value) {
        this.parentOrganisationalUnitUUID = value;
    }

    /**
     * 获取parentOrganisationalUnitID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentOrganisationalUnitID() {
        return parentOrganisationalUnitID;
    }

    /**
     * 设置parentOrganisationalUnitID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentOrganisationalUnitID(String value) {
        this.parentOrganisationalUnitID = value;
    }

}
