
package com.crazy.portal.bean.customer.wsdl.visits;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>AppointmentActivityMaintainRequestBundleMessage_sync_V1 complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="AppointmentActivityMaintainRequestBundleMessage_sync_V1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="BasicMessageHeader" type="{http://sap.com/xi/AP/Common/GDT}BusinessDocumentBasicMessageHeader"/&gt;
 *         &lt;element name="AppointmentActivity" type="{http://sap.com/xi/A1S/Global}AppointmentActivityMaintainRequestBundle_V1" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AppointmentActivityMaintainRequestBundleMessage_sync_V1", namespace = "http://sap.com/xi/A1S/Global", propOrder = {
    "basicMessageHeader",
    "appointmentActivity"
})
public class AppointmentActivityMaintainRequestBundleMessageSyncV1 {

    @XmlElement(name = "BasicMessageHeader", required = true)
    protected BusinessDocumentBasicMessageHeader basicMessageHeader;
    @XmlElement(name = "AppointmentActivity", required = true)
    protected List<AppointmentActivityMaintainRequestBundleV1> appointmentActivity;

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
     * Gets the value of the appointmentActivity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the appointmentActivity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAppointmentActivity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AppointmentActivityMaintainRequestBundleV1 }
     * 
     * 
     */
    public List<AppointmentActivityMaintainRequestBundleV1> getAppointmentActivity() {
        if (appointmentActivity == null) {
            appointmentActivity = new ArrayList<AppointmentActivityMaintainRequestBundleV1>();
        }
        return this.appointmentActivity;
    }

}
