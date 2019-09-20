
package com.crazy.portal.bean.customer.wsdl.customer.ws.detail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>BO_ExtendCustomerUpdateRequestMessage_sync complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="BO_ExtendCustomerUpdateRequestMessage_sync"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="BasicMessageHeader" type="{http://sap.com/xi/AP/Common/GDT}BusinessDocumentBasicMessageHeader"/&gt;
 *         &lt;element name="BO_ExtendCustomer" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerUpdateRequest"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BO_ExtendCustomerUpdateRequestMessage_sync", propOrder = {
    "basicMessageHeader",
    "boExtendCustomer"
})
public class BOExtendCustomerUpdateRequestMessageSync {

    @XmlElement(name = "BasicMessageHeader", required = true)
    protected BusinessDocumentBasicMessageHeader basicMessageHeader;
    @XmlElement(name = "BO_ExtendCustomer", required = true)
    protected BOExtendCustomerUpdateRequest boExtendCustomer;

    /**
     * 获取basicMessageHeader属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BusinessDocumentBasicMessageHeader }
     *     
     */
    public BusinessDocumentBasicMessageHeader getBasicMessageHeader() {
        return basicMessageHeader;
    }

    /**
     * 设置basicMessageHeader属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessDocumentBasicMessageHeader }
     *     
     */
    public void setBasicMessageHeader(BusinessDocumentBasicMessageHeader value) {
        this.basicMessageHeader = value;
    }

    /**
     * 获取boExtendCustomer属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BOExtendCustomerUpdateRequest }
     *     
     */
    public BOExtendCustomerUpdateRequest getBOExtendCustomer() {
        return boExtendCustomer;
    }

    /**
     * 设置boExtendCustomer属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BOExtendCustomerUpdateRequest }
     *     
     */
    public void setBOExtendCustomer(BOExtendCustomerUpdateRequest value) {
        this.boExtendCustomer = value;
    }

}
