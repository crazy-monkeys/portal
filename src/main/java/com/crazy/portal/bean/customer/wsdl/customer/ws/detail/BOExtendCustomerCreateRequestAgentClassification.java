
package com.crazy.portal.bean.customer.wsdl.customer.ws.detail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>BO_ExtendCustomerCreateRequestAgentClassification complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="BO_ExtendCustomerCreateRequestAgentClassification"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="BusinessUnit" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BCO_BUCode" minOccurs="0"/&gt;
 *         &lt;element name="PDT" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BCO_PDTCode" minOccurs="0"/&gt;
 *         &lt;element name="Area" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BCO_AREACode" minOccurs="0"/&gt;
 *         &lt;element name="StartDate" type="{http://sap.com/xi/AP/Common/GDT}Date" minOccurs="0"/&gt;
 *         &lt;element name="EndDate" type="{http://sap.com/xi/AP/Common/GDT}Date" minOccurs="0"/&gt;
 *         &lt;element name="SalesModel" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BCO_SALESMODELCode" minOccurs="0"/&gt;
 *         &lt;element name="Grade" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BCO_GRADECode" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BO_ExtendCustomerCreateRequestAgentClassification", propOrder = {
    "businessUnit",
    "pdt",
    "area",
    "startDate",
    "endDate",
    "salesModel",
    "grade"
})
public class BOExtendCustomerCreateRequestAgentClassification {

    @XmlElement(name = "BusinessUnit")
    protected BCOBUCode businessUnit;
    @XmlElement(name = "PDT")
    protected BCOPDTCode pdt;
    @XmlElement(name = "Area")
    protected BCOAREACode area;
    @XmlElement(name = "StartDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar startDate;
    @XmlElement(name = "EndDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar endDate;
    @XmlElement(name = "SalesModel")
    protected BCOSALESMODELCode salesModel;
    @XmlElement(name = "Grade")
    protected BCOGRADECode grade;

    /**
     * 获取businessUnit属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BCOBUCode }
     *     
     */
    public BCOBUCode getBusinessUnit() {
        return businessUnit;
    }

    /**
     * 设置businessUnit属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BCOBUCode }
     *     
     */
    public void setBusinessUnit(BCOBUCode value) {
        this.businessUnit = value;
    }

    /**
     * 获取pdt属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BCOPDTCode }
     *     
     */
    public BCOPDTCode getPDT() {
        return pdt;
    }

    /**
     * 设置pdt属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BCOPDTCode }
     *     
     */
    public void setPDT(BCOPDTCode value) {
        this.pdt = value;
    }

    /**
     * 获取area属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BCOAREACode }
     *     
     */
    public BCOAREACode getArea() {
        return area;
    }

    /**
     * 设置area属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BCOAREACode }
     *     
     */
    public void setArea(BCOAREACode value) {
        this.area = value;
    }

    /**
     * 获取startDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * 设置startDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
    }

    /**
     * 获取endDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndDate() {
        return endDate;
    }

    /**
     * 设置endDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDate(XMLGregorianCalendar value) {
        this.endDate = value;
    }

    /**
     * 获取salesModel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BCOSALESMODELCode }
     *     
     */
    public BCOSALESMODELCode getSalesModel() {
        return salesModel;
    }

    /**
     * 设置salesModel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BCOSALESMODELCode }
     *     
     */
    public void setSalesModel(BCOSALESMODELCode value) {
        this.salesModel = value;
    }

    /**
     * 获取grade属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BCOGRADECode }
     *     
     */
    public BCOGRADECode getGrade() {
        return grade;
    }

    /**
     * 设置grade属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BCOGRADECode }
     *     
     */
    public void setGrade(BCOGRADECode value) {
        this.grade = value;
    }

}
