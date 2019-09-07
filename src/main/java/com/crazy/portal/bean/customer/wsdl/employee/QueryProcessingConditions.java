
package com.crazy.portal.bean.customer.wsdl.employee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>QueryProcessingConditions complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="QueryProcessingConditions"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="QueryHitsMaximumNumberValue" type="{http://sap.com/xi/AP/Common/GDT}NumberValue" minOccurs="0"/&gt;
 *         &lt;element name="QueryHitsUnlimitedIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator"/&gt;
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
@XmlType(name = "QueryProcessingConditions", namespace = "http://sap.com/xi/AP/Common/GDT", propOrder = {
    "queryHitsMaximumNumberValue",
    "queryHitsUnlimitedIndicator",
    "lastReturnedObjectID"
})
public class QueryProcessingConditions {

    @XmlElement(name = "QueryHitsMaximumNumberValue")
    protected Integer queryHitsMaximumNumberValue;
    @XmlElement(name = "QueryHitsUnlimitedIndicator")
    protected boolean queryHitsUnlimitedIndicator;
    @XmlElement(name = "LastReturnedObjectID")
    protected ObjectID lastReturnedObjectID;

    /**
     * 获取queryHitsMaximumNumberValue属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getQueryHitsMaximumNumberValue() {
        return queryHitsMaximumNumberValue;
    }

    /**
     * 设置queryHitsMaximumNumberValue属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setQueryHitsMaximumNumberValue(Integer value) {
        this.queryHitsMaximumNumberValue = value;
    }

    /**
     * 获取queryHitsUnlimitedIndicator属性的值。
     * 
     */
    public boolean isQueryHitsUnlimitedIndicator() {
        return queryHitsUnlimitedIndicator;
    }

    /**
     * 设置queryHitsUnlimitedIndicator属性的值。
     * 
     */
    public void setQueryHitsUnlimitedIndicator(boolean value) {
        this.queryHitsUnlimitedIndicator = value;
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
