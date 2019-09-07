
package com.crazy.portal.bean.customer.wsdl.employee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>EmployeeBiographicalDataByIdentificationQueryMessage_sync complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="EmployeeBiographicalDataByIdentificationQueryMessage_sync"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="EmployeeBiographicalDataSelectionByIdentification" type="{http://sap.com/xi/A1S/Global}EmployeeBiographicalDataSelectionByIdentification" minOccurs="0"/&gt;
 *         &lt;element name="ProcessingConditions" type="{http://sap.com/xi/AP/Common/GDT}QueryProcessingConditions" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmployeeBiographicalDataByIdentificationQueryMessage_sync", propOrder = {
    "employeeBiographicalDataSelectionByIdentification",
    "processingConditions"
})
public class EmployeeBiographicalDataByIdentificationQueryMessageSync {

    @XmlElement(name = "EmployeeBiographicalDataSelectionByIdentification")
    protected EmployeeBiographicalDataSelectionByIdentification employeeBiographicalDataSelectionByIdentification;
    @XmlElement(name = "ProcessingConditions")
    protected QueryProcessingConditions processingConditions;

    /**
     * 获取employeeBiographicalDataSelectionByIdentification属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EmployeeBiographicalDataSelectionByIdentification }
     *     
     */
    public EmployeeBiographicalDataSelectionByIdentification getEmployeeBiographicalDataSelectionByIdentification() {
        return employeeBiographicalDataSelectionByIdentification;
    }

    /**
     * 设置employeeBiographicalDataSelectionByIdentification属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeBiographicalDataSelectionByIdentification }
     *     
     */
    public void setEmployeeBiographicalDataSelectionByIdentification(EmployeeBiographicalDataSelectionByIdentification value) {
        this.employeeBiographicalDataSelectionByIdentification = value;
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

}
