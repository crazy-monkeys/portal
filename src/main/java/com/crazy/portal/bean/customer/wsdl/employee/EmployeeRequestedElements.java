
package com.crazy.portal.bean.customer.wsdl.employee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>EmployeeRequestedElements complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="EmployeeRequestedElements"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Employee" type="{http://sap.com/xi/A1S/Global}EmployeeRequestedElementsEmployee" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="employeeTransmissionRequestCode" type="{http://sap.com/xi/AP/Common/GDT}TransmissionRequestCode" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmployeeRequestedElements", propOrder = {
    "employee"
})
public class EmployeeRequestedElements {

    @XmlElement(name = "Employee")
    protected EmployeeRequestedElementsEmployee employee;
    @XmlAttribute(name = "employeeTransmissionRequestCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String employeeTransmissionRequestCode;

    /**
     * 获取employee属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EmployeeRequestedElementsEmployee }
     *     
     */
    public EmployeeRequestedElementsEmployee getEmployee() {
        return employee;
    }

    /**
     * 设置employee属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeRequestedElementsEmployee }
     *     
     */
    public void setEmployee(EmployeeRequestedElementsEmployee value) {
        this.employee = value;
    }

    /**
     * 获取employeeTransmissionRequestCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmployeeTransmissionRequestCode() {
        return employeeTransmissionRequestCode;
    }

    /**
     * 设置employeeTransmissionRequestCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmployeeTransmissionRequestCode(String value) {
        this.employeeTransmissionRequestCode = value;
    }

}
