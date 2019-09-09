
package com.crazy.portal.bean.customer.wsdl.orgnation;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ExchangeFaultData complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ExchangeFaultData"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="faultText" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="faultUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="faultDetail" type="{http://sap.com/xi/AP/Common/Global}ExchangeLogData" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExchangeFaultData", namespace = "http://sap.com/xi/AP/Common/Global", propOrder = {
    "faultText",
    "faultUrl",
    "faultDetail"
})
public class ExchangeFaultData {

    @XmlElement(required = true)
    protected String faultText;
    protected String faultUrl;
    protected List<ExchangeLogData> faultDetail;

    /**
     * 获取faultText属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFaultText() {
        return faultText;
    }

    /**
     * 设置faultText属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFaultText(String value) {
        this.faultText = value;
    }

    /**
     * 获取faultUrl属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFaultUrl() {
        return faultUrl;
    }

    /**
     * 设置faultUrl属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFaultUrl(String value) {
        this.faultUrl = value;
    }

    /**
     * Gets the value of the faultDetail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the faultDetail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFaultDetail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExchangeLogData }
     * 
     * 
     */
    public List<ExchangeLogData> getFaultDetail() {
        if (faultDetail == null) {
            faultDetail = new ArrayList<ExchangeLogData>();
        }
        return this.faultDetail;
    }

}
