
package com.crazy.portal.bean.customer.wsdl.customer.ws.detail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>BO_ExtendCustomerQuery_EXT_Cust_AssetInfoAssetInfoSimpleByRequestSelectionBySeason complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="BO_ExtendCustomerQuery_EXT_Cust_AssetInfoAssetInfoSimpleByRequestSelectionBySeason"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="InclusionExclusionCode" type="{http://sap.com/xi/AP/Common/GDT}InclusionExclusionCode" minOccurs="0"/&gt;
 *         &lt;element name="IntervalBoundaryTypeCode" type="{http://sap.com/xi/AP/Common/GDT}IntervalBoundaryTypeCode"/&gt;
 *         &lt;element name="LowerBoundarySeason" type="{http://sap.com/xi/Common/DataTypes}Quarter" minOccurs="0"/&gt;
 *         &lt;element name="UpperBoundarySeason" type="{http://sap.com/xi/Common/DataTypes}Quarter" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BO_ExtendCustomerQuery_EXT_Cust_AssetInfoAssetInfoSimpleByRequestSelectionBySeason", propOrder = {
    "inclusionExclusionCode",
    "intervalBoundaryTypeCode",
    "lowerBoundarySeason",
    "upperBoundarySeason"
})
public class BOExtendCustomerQueryEXTCustAssetInfoAssetInfoSimpleByRequestSelectionBySeason {

    @XmlElement(name = "InclusionExclusionCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String inclusionExclusionCode;
    @XmlElement(name = "IntervalBoundaryTypeCode", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String intervalBoundaryTypeCode;
    @XmlElement(name = "LowerBoundarySeason")
    @XmlSchemaType(name = "token")
    protected Quarter lowerBoundarySeason;
    @XmlElement(name = "UpperBoundarySeason")
    @XmlSchemaType(name = "token")
    protected Quarter upperBoundarySeason;

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
     * 获取lowerBoundarySeason属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Quarter }
     *     
     */
    public Quarter getLowerBoundarySeason() {
        return lowerBoundarySeason;
    }

    /**
     * 设置lowerBoundarySeason属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Quarter }
     *     
     */
    public void setLowerBoundarySeason(Quarter value) {
        this.lowerBoundarySeason = value;
    }

    /**
     * 获取upperBoundarySeason属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Quarter }
     *     
     */
    public Quarter getUpperBoundarySeason() {
        return upperBoundarySeason;
    }

    /**
     * 设置upperBoundarySeason属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Quarter }
     *     
     */
    public void setUpperBoundarySeason(Quarter value) {
        this.upperBoundarySeason = value;
    }

}
