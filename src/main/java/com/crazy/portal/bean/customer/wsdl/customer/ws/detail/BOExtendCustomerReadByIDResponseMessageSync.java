
package com.crazy.portal.bean.customer.wsdl.customer.ws.detail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>BO_ExtendCustomerReadByIDResponseMessage_sync complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="BO_ExtendCustomerReadByIDResponseMessage_sync"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="BO_ExtendCustomer" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerReadByIDResponse" minOccurs="0"/&gt;
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
@XmlType(name = "BO_ExtendCustomerReadByIDResponseMessage_sync", propOrder = {
    "boExtendCustomer",
    "log"
})
public class BOExtendCustomerReadByIDResponseMessageSync {

    @XmlElement(name = "BO_ExtendCustomer")
    protected BOExtendCustomerReadByIDResponse boExtendCustomer;
    @XmlElement(name = "Log", required = true)
    protected Log log;

    /**
     * 获取boExtendCustomer属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BOExtendCustomerReadByIDResponse }
     *     
     */
    public BOExtendCustomerReadByIDResponse getBOExtendCustomer() {
        return boExtendCustomer;
    }

    /**
     * 设置boExtendCustomer属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BOExtendCustomerReadByIDResponse }
     *     
     */
    public void setBOExtendCustomer(BOExtendCustomerReadByIDResponse value) {
        this.boExtendCustomer = value;
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
