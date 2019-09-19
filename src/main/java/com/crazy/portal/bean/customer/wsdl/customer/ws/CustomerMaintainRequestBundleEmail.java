
package com.crazy.portal.bean.customer.wsdl.customer.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>CustomerMaintainRequestBundleEmail complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CustomerMaintainRequestBundleEmail"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="URI" type="{http://sap.com/xi/AP/Common/GDT}ENCRYPTED_MEDIUM_EmailURI" minOccurs="0"/&gt;
 *         &lt;element name="UsageDeniedIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerMaintainRequestBundleEmail", namespace = "http://sap.com/xi/A1S/Global", propOrder = {
    "uri",
    "usageDeniedIndicator"
})
public class CustomerMaintainRequestBundleEmail {

    @XmlElement(name = "URI")
    protected ENCRYPTEDMEDIUMEmailURI uri;
    @XmlElement(name = "UsageDeniedIndicator")
    protected Boolean usageDeniedIndicator;

    /**
     * 获取uri属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ENCRYPTEDMEDIUMEmailURI }
     *     
     */
    public ENCRYPTEDMEDIUMEmailURI getURI() {
        return uri;
    }

    /**
     * 设置uri属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ENCRYPTEDMEDIUMEmailURI }
     *     
     */
    public void setURI(ENCRYPTEDMEDIUMEmailURI value) {
        this.uri = value;
    }

    /**
     * 获取usageDeniedIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUsageDeniedIndicator() {
        return usageDeniedIndicator;
    }

    /**
     * 设置usageDeniedIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUsageDeniedIndicator(Boolean value) {
        this.usageDeniedIndicator = value;
    }

}
