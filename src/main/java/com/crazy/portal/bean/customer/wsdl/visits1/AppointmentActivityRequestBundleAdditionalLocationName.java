
package com.crazy.portal.bean.customer.wsdl.visits1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>AppointmentActivityRequestBundleAdditionalLocationName complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="AppointmentActivityRequestBundleAdditionalLocationName"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AdditionalLocationName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_ENCRYPTED_EXTENDED_Name" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AppointmentActivityRequestBundleAdditionalLocationName", namespace = "http://sap.com/xi/A1S/Global", propOrder = {
    "additionalLocationName"
})
public class AppointmentActivityRequestBundleAdditionalLocationName {

    @XmlElement(name = "AdditionalLocationName")
    protected String additionalLocationName;

    /**
     * 获取additionalLocationName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdditionalLocationName() {
        return additionalLocationName;
    }

    /**
     * 设置additionalLocationName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdditionalLocationName(String value) {
        this.additionalLocationName = value;
    }

}
