
package com.crazy.portal.bean.customer.wsdl.orgnation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ResponseProcessingConditions complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ResponseProcessingConditions"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ReturnedQueryHitsNumberValue" type="{http://sap.com/xi/AP/Common/GDT}NumberValue"/&gt;
 *         &lt;element name="MoreHitsAvailableIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator"/&gt;
 *         &lt;element name="LastReturnedObjectID" type="{http://sap.com/xi/AP/Common/GDT}ObjectID" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseProcessingConditions", propOrder = {
    "returnedQueryHitsNumberValue",
    "moreHitsAvailableIndicator",
    "lastReturnedObjectID"
})
public class ResponseProcessingConditions {

    @XmlElement(name = "ReturnedQueryHitsNumberValue")
    protected int returnedQueryHitsNumberValue;
    @XmlElement(name = "MoreHitsAvailableIndicator")
    protected boolean moreHitsAvailableIndicator;
    @XmlElement(name = "LastReturnedObjectID")
    protected ObjectID lastReturnedObjectID;

    /**
     * 获取returnedQueryHitsNumberValue属性的值。
     * 
     */
    public int getReturnedQueryHitsNumberValue() {
        return returnedQueryHitsNumberValue;
    }

    /**
     * 设置returnedQueryHitsNumberValue属性的值。
     * 
     */
    public void setReturnedQueryHitsNumberValue(int value) {
        this.returnedQueryHitsNumberValue = value;
    }

    /**
     * 获取moreHitsAvailableIndicator属性的值。
     * 
     */
    public boolean isMoreHitsAvailableIndicator() {
        return moreHitsAvailableIndicator;
    }

    /**
     * 设置moreHitsAvailableIndicator属性的值。
     * 
     */
    public void setMoreHitsAvailableIndicator(boolean value) {
        this.moreHitsAvailableIndicator = value;
    }

    /**
     * 获取lastReturnedObjectID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ObjectID }
     *     
     */
    public ObjectID getLastReturnedObjectID() {
        return lastReturnedObjectID;
    }

    /**
     * 设置lastReturnedObjectID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectID }
     *     
     */
    public void setLastReturnedObjectID(ObjectID value) {
        this.lastReturnedObjectID = value;
    }

}
