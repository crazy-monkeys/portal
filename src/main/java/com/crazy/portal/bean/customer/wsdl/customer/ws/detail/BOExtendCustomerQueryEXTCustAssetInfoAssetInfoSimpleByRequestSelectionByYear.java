
package com.crazy.portal.bean.customer.wsdl.customer.ws.detail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>BO_ExtendCustomerQuery_EXT_Cust_AssetInfoAssetInfoSimpleByRequestSelectionByYear complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="BO_ExtendCustomerQuery_EXT_Cust_AssetInfoAssetInfoSimpleByRequestSelectionByYear"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="InclusionExclusionCode" type="{http://sap.com/xi/AP/Common/GDT}InclusionExclusionCode" minOccurs="0"/&gt;
 *         &lt;element name="IntervalBoundaryTypeCode" type="{http://sap.com/xi/AP/Common/GDT}IntervalBoundaryTypeCode"/&gt;
 *         &lt;element name="LowerBoundaryYear" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BCO_YEARCode" minOccurs="0"/&gt;
 *         &lt;element name="UpperBoundaryYear" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BCO_YEARCode" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BO_ExtendCustomerQuery_EXT_Cust_AssetInfoAssetInfoSimpleByRequestSelectionByYear", propOrder = {
    "inclusionExclusionCode",
    "intervalBoundaryTypeCode",
    "lowerBoundaryYear",
    "upperBoundaryYear"
})
public class BOExtendCustomerQueryEXTCustAssetInfoAssetInfoSimpleByRequestSelectionByYear {

    @XmlElement(name = "InclusionExclusionCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String inclusionExclusionCode;
    @XmlElement(name = "IntervalBoundaryTypeCode", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String intervalBoundaryTypeCode;
    @XmlElement(name = "LowerBoundaryYear")
    protected BCOYEARCode lowerBoundaryYear;
    @XmlElement(name = "UpperBoundaryYear")
    protected BCOYEARCode upperBoundaryYear;

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
     * 获取lowerBoundaryYear属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BCOYEARCode }
     *     
     */
    public BCOYEARCode getLowerBoundaryYear() {
        return lowerBoundaryYear;
    }

    /**
     * 设置lowerBoundaryYear属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BCOYEARCode }
     *     
     */
    public void setLowerBoundaryYear(BCOYEARCode value) {
        this.lowerBoundaryYear = value;
    }

    /**
     * 获取upperBoundaryYear属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BCOYEARCode }
     *     
     */
    public BCOYEARCode getUpperBoundaryYear() {
        return upperBoundaryYear;
    }

    /**
     * 设置upperBoundaryYear属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BCOYEARCode }
     *     
     */
    public void setUpperBoundaryYear(BCOYEARCode value) {
        this.upperBoundaryYear = value;
    }

}
