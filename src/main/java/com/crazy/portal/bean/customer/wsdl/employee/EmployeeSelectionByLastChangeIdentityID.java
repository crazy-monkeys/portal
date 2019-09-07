
package com.crazy.portal.bean.customer.wsdl.employee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>EmployeeSelectionByLastChangeIdentityID complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="EmployeeSelectionByLastChangeIdentityID"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="InclusionExclusionCode" type="{http://sap.com/xi/AP/Common/GDT}InclusionExclusionCode" minOccurs="0"/&gt;
 *         &lt;element name="IntervalBoundaryTypeCode" type="{http://sap.com/xi/AP/Common/GDT}IntervalBoundaryTypeCode" minOccurs="0"/&gt;
 *         &lt;element name="LowerBoundaryLastChangeIdentityID" type="{http://sap.com/xi/AP/Common/GDT}IdentityID" minOccurs="0"/&gt;
 *         &lt;element name="UpperBoundaryLastChangeIdentityID" type="{http://sap.com/xi/AP/Common/GDT}IdentityID" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmployeeSelectionByLastChangeIdentityID", propOrder = {
    "inclusionExclusionCode",
    "intervalBoundaryTypeCode",
    "lowerBoundaryLastChangeIdentityID",
    "upperBoundaryLastChangeIdentityID"
})
public class EmployeeSelectionByLastChangeIdentityID {

    @XmlElement(name = "InclusionExclusionCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String inclusionExclusionCode;
    @XmlElement(name = "IntervalBoundaryTypeCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String intervalBoundaryTypeCode;
    @XmlElement(name = "LowerBoundaryLastChangeIdentityID")
    protected IdentityID lowerBoundaryLastChangeIdentityID;
    @XmlElement(name = "UpperBoundaryLastChangeIdentityID")
    protected IdentityID upperBoundaryLastChangeIdentityID;

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
     * 获取lowerBoundaryLastChangeIdentityID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link IdentityID }
     *     
     */
    public IdentityID getLowerBoundaryLastChangeIdentityID() {
        return lowerBoundaryLastChangeIdentityID;
    }

    /**
     * 设置lowerBoundaryLastChangeIdentityID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link IdentityID }
     *     
     */
    public void setLowerBoundaryLastChangeIdentityID(IdentityID value) {
        this.lowerBoundaryLastChangeIdentityID = value;
    }

    /**
     * 获取upperBoundaryLastChangeIdentityID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link IdentityID }
     *     
     */
    public IdentityID getUpperBoundaryLastChangeIdentityID() {
        return upperBoundaryLastChangeIdentityID;
    }

    /**
     * 设置upperBoundaryLastChangeIdentityID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link IdentityID }
     *     
     */
    public void setUpperBoundaryLastChangeIdentityID(IdentityID value) {
        this.upperBoundaryLastChangeIdentityID = value;
    }

}
