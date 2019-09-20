
package com.crazy.portal.bean.customer.wsdl.customer.ws.detail;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>BO_ExtendCustomerquerySalesDataSalesDataSimpleByConfirmationMessage_sync complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="BO_ExtendCustomerquerySalesDataSalesDataSimpleByConfirmationMessage_sync"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="BO_ExtendCustomerSalesData" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerquerySalesDataSalesDataSimpleByConfirmation" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="ProcessingConditions" type="{http://sap.com/xi/AP/Common/GDT}ResponseProcessingConditions"/&gt;
 *         &lt;element name="Log" type="{http://sap.com/xi/AP/Common/GDT}Log"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BO_ExtendCustomerquerySalesDataSalesDataSimpleByConfirmationMessage_sync", propOrder = {
    "boExtendCustomerSalesData",
    "processingConditions",
    "log"
})
public class BOExtendCustomerquerySalesDataSalesDataSimpleByConfirmationMessageSync {

    @XmlElement(name = "BO_ExtendCustomerSalesData")
    protected List<BOExtendCustomerquerySalesDataSalesDataSimpleByConfirmation> boExtendCustomerSalesData;
    @XmlElement(name = "ProcessingConditions", required = true)
    protected ResponseProcessingConditions processingConditions;
    @XmlElement(name = "Log", required = true)
    protected Log log;

    /**
     * Gets the value of the boExtendCustomerSalesData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the boExtendCustomerSalesData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBOExtendCustomerSalesData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BOExtendCustomerquerySalesDataSalesDataSimpleByConfirmation }
     * 
     * 
     */
    public List<BOExtendCustomerquerySalesDataSalesDataSimpleByConfirmation> getBOExtendCustomerSalesData() {
        if (boExtendCustomerSalesData == null) {
            boExtendCustomerSalesData = new ArrayList<BOExtendCustomerquerySalesDataSalesDataSimpleByConfirmation>();
        }
        return this.boExtendCustomerSalesData;
    }

    /**
     * 获取processingConditions属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ResponseProcessingConditions }
     *     
     */
    public ResponseProcessingConditions getProcessingConditions() {
        return processingConditions;
    }

    /**
     * 设置processingConditions属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseProcessingConditions }
     *     
     */
    public void setProcessingConditions(ResponseProcessingConditions value) {
        this.processingConditions = value;
    }

    /**
     * 获取log属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Log }
     *     
     */
    public Log getLog() {
        return log;
    }

    /**
     * 设置log属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Log }
     *     
     */
    public void setLog(Log value) {
        this.log = value;
    }

}
