
package com.crazy.portal.bean.customer.wsdl.employee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="standard" type="{http://sap.com/xi/AP/Common/Global}ExchangeFaultData"/&gt;
 *         &lt;element name="addition" type="{http://sap.com/xi/AP/Common/Global}StandardFaultMessageExtension"/&gt;
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
    "standard",
    "addition"
})
@XmlRootElement(name = "StandardFaultMessage", namespace = "http://sap.com/xi/AP/Common/Global")
public class StandardFaultMessage {

    @XmlElement(required = true)
    protected ExchangeFaultData standard;
    @XmlElement(required = true)
    protected StandardFaultMessageExtension addition;

    /**
     * 获取standard属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ExchangeFaultData }
     *     
     */
    public ExchangeFaultData getStandard() {
        return standard;
    }

    /**
     * 设置standard属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ExchangeFaultData }
     *     
     */
    public void setStandard(ExchangeFaultData value) {
        this.standard = value;
    }

    /**
     * 获取addition属性的值。
     * 
     * @return
     *     possible object is
     *     {@link StandardFaultMessageExtension }
     *     
     */
    public StandardFaultMessageExtension getAddition() {
        return addition;
    }

    /**
     * 设置addition属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link StandardFaultMessageExtension }
     *     
     */
    public void setAddition(StandardFaultMessageExtension value) {
        this.addition = value;
    }

}
