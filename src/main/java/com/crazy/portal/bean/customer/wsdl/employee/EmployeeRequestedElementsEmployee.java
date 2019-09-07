
package com.crazy.portal.bean.customer.wsdl.employee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>EmployeeRequestedElementsEmployee complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="EmployeeRequestedElementsEmployee"&gt;
 *   &lt;simpleContent&gt;
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
 *       &lt;attribute name="biographicalDataTransmissionRequestCode" type="{http://sap.com/xi/AP/Common/GDT}TransmissionRequestCode" /&gt;
 *       &lt;attribute name="workplaceAddressInformationTransmissionRequestCode" type="{http://sap.com/xi/AP/Common/GDT}TransmissionRequestCode" /&gt;
 *       &lt;attribute name="jobAssignmentTransmissionRequestCode" type="{http://sap.com/xi/AP/Common/GDT}TransmissionRequestCode" /&gt;
 *       &lt;attribute name="employeeOrganisationalAssignmentTransmissionRequestCode" type="{http://sap.com/xi/AP/Common/GDT}TransmissionRequestCode" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/simpleContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmployeeRequestedElementsEmployee", propOrder = {
    "value"
})
public class EmployeeRequestedElementsEmployee {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "biographicalDataTransmissionRequestCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String biographicalDataTransmissionRequestCode;
    @XmlAttribute(name = "workplaceAddressInformationTransmissionRequestCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String workplaceAddressInformationTransmissionRequestCode;
    @XmlAttribute(name = "jobAssignmentTransmissionRequestCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String jobAssignmentTransmissionRequestCode;
    @XmlAttribute(name = "employeeOrganisationalAssignmentTransmissionRequestCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String employeeOrganisationalAssignmentTransmissionRequestCode;

    /**
     * 获取value属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置value属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取biographicalDataTransmissionRequestCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBiographicalDataTransmissionRequestCode() {
        return biographicalDataTransmissionRequestCode;
    }

    /**
     * 设置biographicalDataTransmissionRequestCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBiographicalDataTransmissionRequestCode(String value) {
        this.biographicalDataTransmissionRequestCode = value;
    }

    /**
     * 获取workplaceAddressInformationTransmissionRequestCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkplaceAddressInformationTransmissionRequestCode() {
        return workplaceAddressInformationTransmissionRequestCode;
    }

    /**
     * 设置workplaceAddressInformationTransmissionRequestCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkplaceAddressInformationTransmissionRequestCode(String value) {
        this.workplaceAddressInformationTransmissionRequestCode = value;
    }

    /**
     * 获取jobAssignmentTransmissionRequestCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJobAssignmentTransmissionRequestCode() {
        return jobAssignmentTransmissionRequestCode;
    }

    /**
     * 设置jobAssignmentTransmissionRequestCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJobAssignmentTransmissionRequestCode(String value) {
        this.jobAssignmentTransmissionRequestCode = value;
    }

    /**
     * 获取employeeOrganisationalAssignmentTransmissionRequestCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmployeeOrganisationalAssignmentTransmissionRequestCode() {
        return employeeOrganisationalAssignmentTransmissionRequestCode;
    }

    /**
     * 设置employeeOrganisationalAssignmentTransmissionRequestCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmployeeOrganisationalAssignmentTransmissionRequestCode(String value) {
        this.employeeOrganisationalAssignmentTransmissionRequestCode = value;
    }

}
