
package com.crazy.portal.bean.customer.wsdl.customer.ws.detail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>BO_ExtendCustomerQuery_ExtendCustomerInfoSimpleByRequestSelectionBySAP_ToCustomer complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="BO_ExtendCustomerQuery_ExtendCustomerInfoSimpleByRequestSelectionBySAP_ToCustomer"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="InclusionExclusionCode" type="{http://sap.com/xi/AP/Common/GDT}InclusionExclusionCode" minOccurs="0"/&gt;
 *         &lt;element name="IntervalBoundaryTypeCode" type="{http://sap.com/xi/AP/Common/GDT}IntervalBoundaryTypeCode"/&gt;
 *         &lt;element name="LowerBoundarySAP_ToCustomer" type="{http://sap.com/xi/AP/Common/GDT}UUID" minOccurs="0"/&gt;
 *         &lt;element name="UpperBoundarySAP_ToCustomer" type="{http://sap.com/xi/AP/Common/GDT}UUID" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BO_ExtendCustomerQuery_ExtendCustomerInfoSimpleByRequestSelectionBySAP_ToCustomer", propOrder = {
    "inclusionExclusionCode",
    "intervalBoundaryTypeCode",
    "lowerBoundarySAPToCustomer",
    "upperBoundarySAPToCustomer"
})
public class BOExtendCustomerQueryExtendCustomerInfoSimpleByRequestSelectionBySAPToCustomer {

    @XmlElement(name = "InclusionExclusionCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String inclusionExclusionCode;
    @XmlElement(name = "IntervalBoundaryTypeCode", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String intervalBoundaryTypeCode;
    @XmlElement(name = "LowerBoundarySAP_ToCustomer")
    protected UUID lowerBoundarySAPToCustomer;
    @XmlElement(name = "UpperBoundarySAP_ToCustomer")
    protected UUID upperBoundarySAPToCustomer;

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
     * 获取lowerBoundarySAPToCustomer属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UUID }
     *     
     */
    public UUID getLowerBoundarySAPToCustomer() {
        return lowerBoundarySAPToCustomer;
    }

    /**
     * 设置lowerBoundarySAPToCustomer属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UUID }
     *     
     */
    public void setLowerBoundarySAPToCustomer(UUID value) {
        this.lowerBoundarySAPToCustomer = value;
    }

    /**
     * 获取upperBoundarySAPToCustomer属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UUID }
     *     
     */
    public UUID getUpperBoundarySAPToCustomer() {
        return upperBoundarySAPToCustomer;
    }

    /**
     * 设置upperBoundarySAPToCustomer属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UUID }
     *     
     */
    public void setUpperBoundarySAPToCustomer(UUID value) {
        this.upperBoundarySAPToCustomer = value;
    }

}