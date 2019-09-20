
package com.crazy.portal.bean.customer.wsdl.customer.ws.detail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>BO_ExtendCustomerquerySalesDataSalesDataSimpleByRequestMessage_sync complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="BO_ExtendCustomerquerySalesDataSalesDataSimpleByRequestMessage_sync"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="BO_ExtendCustomerSalesDataSimpleSelectionBy" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerquerySalesDataSalesDataSimpleByRequest"/&gt;
 *         &lt;element name="ProcessingConditions" type="{http://sap.com/xi/AP/Common/GDT}QueryProcessingConditions"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BO_ExtendCustomerquerySalesDataSalesDataSimpleByRequestMessage_sync", propOrder = {
    "boExtendCustomerSalesDataSimpleSelectionBy",
    "processingConditions"
})
public class BOExtendCustomerquerySalesDataSalesDataSimpleByRequestMessageSync {

    @XmlElement(name = "BO_ExtendCustomerSalesDataSimpleSelectionBy", required = true)
    protected BOExtendCustomerquerySalesDataSalesDataSimpleByRequest boExtendCustomerSalesDataSimpleSelectionBy;
    @XmlElement(name = "ProcessingConditions", required = true)
    protected QueryProcessingConditions processingConditions;

    /**
     * 获取boExtendCustomerSalesDataSimpleSelectionBy属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BOExtendCustomerquerySalesDataSalesDataSimpleByRequest }
     *     
     */
    public BOExtendCustomerquerySalesDataSalesDataSimpleByRequest getBOExtendCustomerSalesDataSimpleSelectionBy() {
        return boExtendCustomerSalesDataSimpleSelectionBy;
    }

    /**
     * 设置boExtendCustomerSalesDataSimpleSelectionBy属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BOExtendCustomerquerySalesDataSalesDataSimpleByRequest }
     *     
     */
    public void setBOExtendCustomerSalesDataSimpleSelectionBy(BOExtendCustomerquerySalesDataSalesDataSimpleByRequest value) {
        this.boExtendCustomerSalesDataSimpleSelectionBy = value;
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
