
package com.crazy.portal.bean.customer.wsdl.employee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>EmployeeSelectionByOrganisationalCentreID complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="EmployeeSelectionByOrganisationalCentreID"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="InclusionExclusionCode" type="{http://sap.com/xi/AP/Common/GDT}InclusionExclusionCode" minOccurs="0"/&gt;
 *         &lt;element name="IntervalBoundaryTypeCode" type="{http://sap.com/xi/AP/Common/GDT}IntervalBoundaryTypeCode"/&gt;
 *         &lt;element name="LowerBoundaryOrganisationalCentreID" type="{http://sap.com/xi/AP/Common/GDT}OrganisationalCentreID" minOccurs="0"/&gt;
 *         &lt;element name="UpperBoundaryOrganisationalCentreID" type="{http://sap.com/xi/AP/Common/GDT}OrganisationalCentreID" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmployeeSelectionByOrganisationalCentreID", propOrder = {
    "inclusionExclusionCode",
    "intervalBoundaryTypeCode",
    "lowerBoundaryOrganisationalCentreID",
    "upperBoundaryOrganisationalCentreID"
})
public class EmployeeSelectionByOrganisationalCentreID {

    @XmlElement(name = "InclusionExclusionCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String inclusionExclusionCode;
    @XmlElement(name = "IntervalBoundaryTypeCode", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String intervalBoundaryTypeCode;
    @XmlElement(name = "LowerBoundaryOrganisationalCentreID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String lowerBoundaryOrganisationalCentreID;
    @XmlElement(name = "UpperBoundaryOrganisationalCentreID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String upperBoundaryOrganisationalCentreID;

    /**
     * 获取inclusionExclusionCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInclusionExclusionCode() {
        return inclusionExclusionCode;
    }

    /**
     * 设置inclusionExclusionCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInclusionExclusionCode(String value) {
        this.inclusionExclusionCode = value;
    }

    /**
     * 获取intervalBoundaryTypeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntervalBoundaryTypeCode() {
        return intervalBoundaryTypeCode;
    }

    /**
     * 设置intervalBoundaryTypeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntervalBoundaryTypeCode(String value) {
        this.intervalBoundaryTypeCode = value;
    }

    /**
     * 获取lowerBoundaryOrganisationalCentreID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLowerBoundaryOrganisationalCentreID() {
        return lowerBoundaryOrganisationalCentreID;
    }

    /**
     * 设置lowerBoundaryOrganisationalCentreID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLowerBoundaryOrganisationalCentreID(String value) {
        this.lowerBoundaryOrganisationalCentreID = value;
    }

    /**
     * 获取upperBoundaryOrganisationalCentreID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpperBoundaryOrganisationalCentreID() {
        return upperBoundaryOrganisationalCentreID;
    }

    /**
     * 设置upperBoundaryOrganisationalCentreID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpperBoundaryOrganisationalCentreID(String value) {
        this.upperBoundaryOrganisationalCentreID = value;
    }

}
