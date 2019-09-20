
package com.crazy.portal.bean.customer.wsdl.customer.ws.detail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>BO_ExtendCustomerQuery_ExtendCustomerInfoSimpleByRequestMessage_sync complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="BO_ExtendCustomerQuery_ExtendCustomerInfoSimpleByRequestMessage_sync"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="BO_ExtendCustomerSimpleSelectionBy" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerQuery_ExtendCustomerInfoSimpleByRequest"/&gt;
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
@XmlType(name = "BO_ExtendCustomerQuery_ExtendCustomerInfoSimpleByRequestMessage_sync", propOrder = {
    "boExtendCustomerSimpleSelectionBy",
    "processingConditions"
})
public class BOExtendCustomerQueryExtendCustomerInfoSimpleByRequestMessageSync {

    @XmlElement(name = "BO_ExtendCustomerSimpleSelectionBy", required = true)
    protected BOExtendCustomerQueryExtendCustomerInfoSimpleByRequest boExtendCustomerSimpleSelectionBy;
    @XmlElement(name = "ProcessingConditions", required = true)
    protected QueryProcessingConditions processingConditions;

    /**
     * 获取boExtendCustomerSimpleSelectionBy属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BOExtendCustomerQueryExtendCustomerInfoSimpleByRequest }
     *     
     */
    public BOExtendCustomerQueryExtendCustomerInfoSimpleByRequest getBOExtendCustomerSimpleSelectionBy() {
        return boExtendCustomerSimpleSelectionBy;
    }

    /**
     * 设置boExtendCustomerSimpleSelectionBy属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BOExtendCustomerQueryExtendCustomerInfoSimpleByRequest }
     *     
     */
    public void setBOExtendCustomerSimpleSelectionBy(BOExtendCustomerQueryExtendCustomerInfoSimpleByRequest value) {
        this.boExtendCustomerSimpleSelectionBy = value;
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
