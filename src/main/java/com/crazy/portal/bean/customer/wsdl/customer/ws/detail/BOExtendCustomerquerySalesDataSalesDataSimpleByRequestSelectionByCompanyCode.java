
package com.crazy.portal.bean.customer.wsdl.customer.ws.detail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>BO_ExtendCustomerquerySalesDataSalesDataSimpleByRequestSelectionByCompanyCode complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="BO_ExtendCustomerquerySalesDataSalesDataSimpleByRequestSelectionByCompanyCode"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="InclusionExclusionCode" type="{http://sap.com/xi/AP/Common/GDT}InclusionExclusionCode" minOccurs="0"/&gt;
 *         &lt;element name="IntervalBoundaryTypeCode" type="{http://sap.com/xi/AP/Common/GDT}IntervalBoundaryTypeCode"/&gt;
 *         &lt;element name="LowerBoundaryCompanyCode" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BCO_COMPANYCODECode" minOccurs="0"/&gt;
 *         &lt;element name="UpperBoundaryCompanyCode" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BCO_COMPANYCODECode" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BO_ExtendCustomerquerySalesDataSalesDataSimpleByRequestSelectionByCompanyCode", propOrder = {
    "inclusionExclusionCode",
    "intervalBoundaryTypeCode",
    "lowerBoundaryCompanyCode",
    "upperBoundaryCompanyCode"
})
public class BOExtendCustomerquerySalesDataSalesDataSimpleByRequestSelectionByCompanyCode {

    @XmlElement(name = "InclusionExclusionCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String inclusionExclusionCode;
    @XmlElement(name = "IntervalBoundaryTypeCode", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String intervalBoundaryTypeCode;
    @XmlElement(name = "LowerBoundaryCompanyCode")
    protected BCOCOMPANYCODECode lowerBoundaryCompanyCode;
    @XmlElement(name = "UpperBoundaryCompanyCode")
    protected BCOCOMPANYCODECode upperBoundaryCompanyCode;

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
     * 获取lowerBoundaryCompanyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BCOCOMPANYCODECode }
     *     
     */
    public BCOCOMPANYCODECode getLowerBoundaryCompanyCode() {
        return lowerBoundaryCompanyCode;
    }

    /**
     * 设置lowerBoundaryCompanyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BCOCOMPANYCODECode }
     *     
     */
    public void setLowerBoundaryCompanyCode(BCOCOMPANYCODECode value) {
        this.lowerBoundaryCompanyCode = value;
    }

    /**
     * 获取upperBoundaryCompanyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BCOCOMPANYCODECode }
     *     
     */
    public BCOCOMPANYCODECode getUpperBoundaryCompanyCode() {
        return upperBoundaryCompanyCode;
    }

    /**
     * 设置upperBoundaryCompanyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BCOCOMPANYCODECode }
     *     
     */
    public void setUpperBoundaryCompanyCode(BCOCOMPANYCODECode value) {
        this.upperBoundaryCompanyCode = value;
    }

}
