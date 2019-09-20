
package com.crazy.portal.bean.customer.wsdl.customer.ws.detail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>BO_ExtendCustomerReadByIDQueryMessage_sync complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="BO_ExtendCustomerReadByIDQueryMessage_sync"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="BO_ExtendCustomer" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerReadByIDQuery"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BO_ExtendCustomerReadByIDQueryMessage_sync", propOrder = {
    "boExtendCustomer"
})
public class BOExtendCustomerReadByIDQueryMessageSync {

    @XmlElement(name = "BO_ExtendCustomer", required = true)
    protected BOExtendCustomerReadByIDQuery boExtendCustomer;

    /**
     * 获取boExtendCustomer属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BOExtendCustomerReadByIDQuery }
     *     
     */
    public BOExtendCustomerReadByIDQuery getBOExtendCustomer() {
        return boExtendCustomer;
    }

    /**
     * 设置boExtendCustomer属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BOExtendCustomerReadByIDQuery }
     *     
     */
    public void setBOExtendCustomer(BOExtendCustomerReadByIDQuery value) {
        this.boExtendCustomer = value;
    }

}
