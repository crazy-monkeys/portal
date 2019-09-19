
package com.crazy.portal.bean.customer.wsdl.customer.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;


/**
 * <p>CustomerBundleMaintainConfirmationMessage_sync_V1 complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CustomerBundleMaintainConfirmationMessage_sync_V1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Customer" type="{http://sap.com/xi/A1S/Global}CustomerMaintainConfirmationBundleCustomer_V1" maxOccurs="unbounded" minOccurs="0"/&gt;
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
@XmlType(name = "", propOrder = {
    "customer",
    "log"
})

@XmlRootElement(name = "CustomerBundleMaintainConfirmation_sync_V1", namespace = "http://sap.com/xi/SAPGlobal20/Global")
public class CustomerBundleMaintainConfirmationMessageSyncV1 {

    @XmlElement(name = "Customer")
    protected List<CustomerMaintainConfirmationBundleCustomerV1> customer;
    @XmlElement(name = "Log", required = true)
    protected Log log;

    /**
     * Gets the value of the customer property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the customer property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCustomer().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustomerMaintainConfirmationBundleCustomerV1 }
     * 
     * 
     */
    public List<CustomerMaintainConfirmationBundleCustomerV1> getCustomer() {
        if (customer == null) {
            customer = new ArrayList<CustomerMaintainConfirmationBundleCustomerV1>();
        }
        return this.customer;
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
