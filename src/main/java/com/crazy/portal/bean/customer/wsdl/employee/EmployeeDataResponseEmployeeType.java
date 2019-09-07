
package com.crazy.portal.bean.customer.wsdl.employee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>EmployeeDataResponseEmployeeType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="EmployeeDataResponseEmployeeType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="InternalEmployeeIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *         &lt;element name="ValidityPeriod" type="{http://sap.com/xi/AP/Common/GDT}CLOSED_DatePeriod" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmployeeDataResponseEmployeeType", propOrder = {
    "internalEmployeeIndicator",
    "validityPeriod"
})
public class EmployeeDataResponseEmployeeType {

    @XmlElement(name = "InternalEmployeeIndicator")
    protected Boolean internalEmployeeIndicator;
    @XmlElement(name = "ValidityPeriod")
    protected CLOSEDDatePeriod validityPeriod;

    /**
     * 获取internalEmployeeIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isInternalEmployeeIndicator() {
        return internalEmployeeIndicator;
    }

    /**
     * 设置internalEmployeeIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setInternalEmployeeIndicator(Boolean value) {
        this.internalEmployeeIndicator = value;
    }

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

}
