
package com.crazy.portal.bean.customer.wsdl.employee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>EmployeeDataByOrganisationalIDQueryMessage_sync complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="EmployeeDataByOrganisationalIDQueryMessage_sync"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="EmployeeDataSelectionByOrgCentreID" type="{http://sap.com/xi/A1S/Global}EmployeeDataSelectionByOrganisationalCentreID" minOccurs="0"/&gt;
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
@XmlType(name = "EmployeeDataByOrganisationalIDQueryMessage_sync", propOrder = {
    "employeeDataSelectionByOrgCentreID",
    "processingConditions",
    "requestedElements"
})
public class EmployeeDataByOrganisationalIDQueryMessageSync {

    @XmlElement(name = "EmployeeDataSelectionByOrgCentreID")
    protected EmployeeDataSelectionByOrganisationalCentreID employeeDataSelectionByOrgCentreID;
    @XmlElement(name = "ProcessingConditions")
    protected QueryProcessingConditions processingConditions;
    @XmlElement(name = "RequestedElements")
    protected EmployeeRequestedElements requestedElements;

    /**
     * 获取employeeDataSelectionByOrgCentreID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EmployeeDataSelectionByOrganisationalCentreID }
     *     
     */
    public EmployeeDataSelectionByOrganisationalCentreID getEmployeeDataSelectionByOrgCentreID() {
        return employeeDataSelectionByOrgCentreID;
    }

    /**
     * 设置employeeDataSelectionByOrgCentreID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeDataSelectionByOrganisationalCentreID }
     *     
     */
    public void setEmployeeDataSelectionByOrgCentreID(EmployeeDataSelectionByOrganisationalCentreID value) {
        this.employeeDataSelectionByOrgCentreID = value;
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
