
package com.crazy.portal.bean.customer.wsdl.employee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>EmployeeSelectionByCreationIdentityID complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="EmployeeSelectionByCreationIdentityID"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="InclusionExclusionCode" type="{http://sap.com/xi/AP/Common/GDT}InclusionExclusionCode" minOccurs="0"/&gt;
 *         &lt;element name="IntervalBoundaryTypeCode" type="{http://sap.com/xi/AP/Common/GDT}IntervalBoundaryTypeCode" minOccurs="0"/&gt;
 *         &lt;element name="LowerBoundaryEmployeeCreationIdentityID" type="{http://sap.com/xi/AP/Common/GDT}IdentityID" minOccurs="0"/&gt;
 *         &lt;element name="UpperBoundaryEmployeeCreationIdentityID" type="{http://sap.com/xi/AP/Common/GDT}IdentityID" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmployeeSelectionByCreationIdentityID", propOrder = {
    "inclusionExclusionCode",
    "intervalBoundaryTypeCode",
    "lowerBoundaryEmployeeCreationIdentityID",
    "upperBoundaryEmployeeCreationIdentityID"
})
public class EmployeeSelectionByCreationIdentityID {

    @XmlElement(name = "InclusionExclusionCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String inclusionExclusionCode;
    @XmlElement(name = "IntervalBoundaryTypeCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String intervalBoundaryTypeCode;
    @XmlElement(name = "LowerBoundaryEmployeeCreationIdentityID")
    protected IdentityID lowerBoundaryEmployeeCreationIdentityID;
    @XmlElement(name = "UpperBoundaryEmployeeCreationIdentityID")
    protected IdentityID upperBoundaryEmployeeCreationIdentityID;

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
     * 获取lowerBoundaryEmployeeCreationIdentityID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link IdentityID }
     *     
     */
    public IdentityID getLowerBoundaryEmployeeCreationIdentityID() {
        return lowerBoundaryEmployeeCreationIdentityID;
    }

    /**
     * 设置lowerBoundaryEmployeeCreationIdentityID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link IdentityID }
     *     
     */
    public void setLowerBoundaryEmployeeCreationIdentityID(IdentityID value) {
        this.lowerBoundaryEmployeeCreationIdentityID = value;
    }

    /**
     * 获取upperBoundaryEmployeeCreationIdentityID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link IdentityID }
     *     
     */
    public IdentityID getUpperBoundaryEmployeeCreationIdentityID() {
        return upperBoundaryEmployeeCreationIdentityID;
    }

    /**
     * 设置upperBoundaryEmployeeCreationIdentityID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link IdentityID }
     *     
     */
    public void setUpperBoundaryEmployeeCreationIdentityID(IdentityID value) {
        this.upperBoundaryEmployeeCreationIdentityID = value;
    }

}
