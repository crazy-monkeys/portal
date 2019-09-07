
package com.crazy.portal.bean.customer.wsdl.employee;

import javax.xml.bind.annotation.*;


/**
 * <p>EmployeeBasicDataByIdentificationQueryMessage_sync complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="EmployeeBasicDataByIdentificationQueryMessage_sync"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="EmployeeBasicDataSelectionByIdentification" type="{http://sap.com/xi/A1S/Global}EmployeeBasicDataSelectionByIdentification" minOccurs="0"/&gt;
 *         &lt;element name="ProcessingConditions" type="{http://sap.com/xi/AP/Common/GDT}QueryProcessingConditions" minOccurs="0"/&gt;
 *         &lt;element name="RequestedElements" type="{http://sap.com/xi/A1S/Global}EmployeeRequestedElements" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "employeeBasicDataSelectionByIdentification",
    "processingConditions",
    "requestedElements"
})
@XmlRootElement(name = "EmployeeBasicDataByIdentificationQueryMessage_sync", namespace = "http://sap.com/xi/SAPGlobal20/Global")
public class EmployeeBasicDataByIdentificationQueryMessageSync {

    @XmlElement(name = "EmployeeBasicDataSelectionByIdentification")
    protected EmployeeBasicDataSelectionByIdentification employeeBasicDataSelectionByIdentification;
    @XmlElement(name = "ProcessingConditions")
    protected QueryProcessingConditions processingConditions;
    @XmlElement(name = "RequestedElements")
    protected EmployeeRequestedElements requestedElements;

    /**
     * 获取employeeBasicDataSelectionByIdentification属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EmployeeBasicDataSelectionByIdentification }
     *     
     */
    public EmployeeBasicDataSelectionByIdentification getEmployeeBasicDataSelectionByIdentification() {
        return employeeBasicDataSelectionByIdentification;
    }

    /**
     * 设置employeeBasicDataSelectionByIdentification属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeBasicDataSelectionByIdentification }
     *     
     */
    public void setEmployeeBasicDataSelectionByIdentification(EmployeeBasicDataSelectionByIdentification value) {
        this.employeeBasicDataSelectionByIdentification = value;
    }

    /**
     * 获取processingConditions属性的值。
     * 
     * @return
     *     possible object is
     *     {@link QueryProcessingConditions }
     *     
     */
    public QueryProcessingConditions getProcessingConditions() {
        return processingConditions;
    }

    /**
     * 设置processingConditions属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link QueryProcessingConditions }
     *     
     */
    public void setProcessingConditions(QueryProcessingConditions value) {
        this.processingConditions = value;
    }

    /**
     * 获取requestedElements属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EmployeeRequestedElements }
     *     
     */
    public EmployeeRequestedElements getRequestedElements() {
        return requestedElements;
    }

    /**
     * 设置requestedElements属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeRequestedElements }
     *     
     */
    public void setRequestedElements(EmployeeRequestedElements value) {
        this.requestedElements = value;
    }

}
