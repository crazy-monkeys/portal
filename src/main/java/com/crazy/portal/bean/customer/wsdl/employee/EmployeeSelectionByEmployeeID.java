
package com.crazy.portal.bean.customer.wsdl.employee;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>EmployeeSelectionByEmployeeID complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="EmployeeSelectionByEmployeeID"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="InclusionExclusionCode" type="{http://sap.com/xi/AP/Common/GDT}InclusionExclusionCode" minOccurs="0"/&gt;
 *         &lt;element name="IntervalBoundaryTypeCode" type="{http://sap.com/xi/AP/Common/GDT}IntervalBoundaryTypeCode" minOccurs="0"/&gt;
 *         &lt;element name="LowerBoundaryEmployeeID" type="{http://sap.com/xi/AP/Common/GDT}EmployeeID" minOccurs="0"/&gt;
 *         &lt;element name="UpperBoundaryEmployeeID" type="{http://sap.com/xi/AP/Common/GDT}EmployeeID" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmployeeSelectionByEmployeeID", propOrder = {
    "inclusionExclusionCode",
    "intervalBoundaryTypeCode",
    "lowerBoundaryEmployeeID",
    "upperBoundaryEmployeeID"
})
public class EmployeeSelectionByEmployeeID {

    @XmlElement(name = "InclusionExclusionCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String inclusionExclusionCode;
    @XmlElement(name = "IntervalBoundaryTypeCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String intervalBoundaryTypeCode;
    @XmlElement(name = "LowerBoundaryEmployeeID")
    protected EmployeeID lowerBoundaryEmployeeID;
    @XmlElement(name = "UpperBoundaryEmployeeID")
    protected EmployeeID upperBoundaryEmployeeID;

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
     * 获取lowerBoundaryEmployeeID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EmployeeID }
     *     
     */
    public EmployeeID getLowerBoundaryEmployeeID() {
        return lowerBoundaryEmployeeID;
    }

    /**
     * 设置lowerBoundaryEmployeeID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeID }
     *     
     */
    public void setLowerBoundaryEmployeeID(EmployeeID value) {
        this.lowerBoundaryEmployeeID = value;
    }

    /**
     * 获取upperBoundaryEmployeeID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EmployeeID }
     *     
     */
    public EmployeeID getUpperBoundaryEmployeeID() {
        return upperBoundaryEmployeeID;
    }

    /**
     * 设置upperBoundaryEmployeeID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeID }
     *     
     */
    public void setUpperBoundaryEmployeeID(EmployeeID value) {
        this.upperBoundaryEmployeeID = value;
    }

}
