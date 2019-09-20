
package com.crazy.portal.bean.customer.wsdl.customer.ws.detail;

import javax.xml.bind.annotation.*;


/**
 * <p>BO_ExtendCustomerCreateConfirmationMessage_sync complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="BO_ExtendCustomerCreateConfirmationMessage_sync"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="BO_ExtendCustomer" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerCreateConfirmation" minOccurs="0"/&gt;
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
@XmlType(name = "BO_ExtendCustomerCreateConfirmationMessage_sync", propOrder = {
    "boExtendCustomer",
    "log"
})
@XmlRootElement(name = "BO_ExtendCustomerCreateConfirmationMessage_sync", namespace = "http://sap.com/xi/SAPGlobal20/Global")
public class BOExtendCustomerCreateConfirmationMessageSync {

    @XmlElement(name = "BO_ExtendCustomer")
    protected BOExtendCustomerCreateConfirmation boExtendCustomer;
    @XmlElement(name = "Log", required = true)
    protected Log log;

    /**
     * 获取boExtendCustomer属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BOExtendCustomerCreateConfirmation }
     *     
     */
    public BOExtendCustomerCreateConfirmation getBOExtendCustomer() {
        return boExtendCustomer;
    }

    /**
     * 设置boExtendCustomer属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BOExtendCustomerCreateConfirmation }
     *     
     */
    public void setBOExtendCustomer(BOExtendCustomerCreateConfirmation value) {
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
