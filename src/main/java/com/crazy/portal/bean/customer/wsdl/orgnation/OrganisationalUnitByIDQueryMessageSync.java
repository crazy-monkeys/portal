
package com.crazy.portal.bean.customer.wsdl.orgnation;

import javax.xml.bind.annotation.*;


/**
 * <p>OrganisationalUnitByIDQueryMessage_sync complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="OrganisationalUnitByIDQueryMessage_sync"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="OrganisationalUnitSelectionByID" type="{http://sap.com/xi/A1S/Global}OrganisationalUnitSelectionByID" minOccurs="0"/&gt;
 *         &lt;element name="ProcessingConditions" type="{http://sap.com/xi/AP/Common/GDT}QueryProcessingConditions" minOccurs="0"/&gt;
 *         &lt;element name="RequestedElements" type="{http://sap.com/xi/AP/FO/MOM/Global}OrganisationalUnitRequestedElements" minOccurs="0"/&gt;
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
    "organisationalUnitSelectionByID",
    "processingConditions",
    "requestedElements"
})
@XmlRootElement(name = "OrganisationalUnitByIDQueryMessage_sync", namespace = "http://sap.com/xi/AP/FO/MOM/Global")
public class OrganisationalUnitByIDQueryMessageSync {

    @XmlElement(name = "OrganisationalUnitSelectionByID")
    protected OrganisationalUnitSelectionByID organisationalUnitSelectionByID;
    @XmlElement(name = "ProcessingConditions")
    protected QueryProcessingConditions processingConditions;
    @XmlElement(name = "RequestedElements")
    protected OrganisationalUnitRequestedElements requestedElements;

    /**
     * 获取organisationalUnitSelectionByID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link OrganisationalUnitSelectionByID }
     *     
     */
    public OrganisationalUnitSelectionByID getOrganisationalUnitSelectionByID() {
        return organisationalUnitSelectionByID;
    }

    /**
     * 设置organisationalUnitSelectionByID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link OrganisationalUnitSelectionByID }
     *     
     */
    public void setOrganisationalUnitSelectionByID(OrganisationalUnitSelectionByID value) {
        this.organisationalUnitSelectionByID = value;
    }

    /**
     * 获取processingConditions属性的值。
     * 
     * @return
     *     possible object is
     *     {@link QueryProcessingConditions }
     *     
     */
    public QueryProcessingConditions getProcessingConditions() {
        return processingConditions;
    }

    /**
     * 设置processingConditions属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link QueryProcessingConditions }
     *     
     */
    public void setProcessingConditions(QueryProcessingConditions value) {
        this.processingConditions = value;
    }

    /**
     * 获取requestedElements属性的值。
     * 
     * @return
     *     possible object is
     *     {@link OrganisationalUnitRequestedElements }
     *     
     */
    public OrganisationalUnitRequestedElements getRequestedElements() {
        return requestedElements;
    }

    /**
     * 设置requestedElements属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link OrganisationalUnitRequestedElements }
     *     
     */
    public void setRequestedElements(OrganisationalUnitRequestedElements value) {
        this.requestedElements = value;
    }

}
