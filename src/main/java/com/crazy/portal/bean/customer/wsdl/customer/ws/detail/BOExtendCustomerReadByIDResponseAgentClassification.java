
package com.crazy.portal.bean.customer.wsdl.customer.ws.detail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>BO_ExtendCustomerReadByIDResponseAgentClassification complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="BO_ExtendCustomerReadByIDResponseAgentClassification"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SAP_UUID" type="{http://sap.com/xi/Common/DataTypes}UUID" minOccurs="0"/&gt;
 *         &lt;element name="BusinessUnit" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BCO_BUCode" minOccurs="0"/&gt;
 *         &lt;element name="BusinessUnitName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_LONG_Name" minOccurs="0"/&gt;
 *         &lt;element name="PDT" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BCO_PDTCode" minOccurs="0"/&gt;
 *         &lt;element name="PDTName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_LONG_Name" minOccurs="0"/&gt;
 *         &lt;element name="Area" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BCO_AREACode" minOccurs="0"/&gt;
 *         &lt;element name="AreaName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_LONG_Name" minOccurs="0"/&gt;
 *         &lt;element name="StartDate" type="{http://sap.com/xi/AP/Common/GDT}Date" minOccurs="0"/&gt;
 *         &lt;element name="EndDate" type="{http://sap.com/xi/AP/Common/GDT}Date" minOccurs="0"/&gt;
 *         &lt;element name="SalesModel" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BCO_SALESMODELCode" minOccurs="0"/&gt;
 *         &lt;element name="SalesModelName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_LONG_Name" minOccurs="0"/&gt;
 *         &lt;element name="Grade" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BCO_GRADECode" minOccurs="0"/&gt;
 *         &lt;element name="GradeName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_LONG_Name" minOccurs="0"/&gt;
 *         &lt;element name="SAP_SystemAdministrativeData" type="{http://sap.com/xi/AP/Common/GDT}SystemAdministrativeData" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BO_ExtendCustomerReadByIDResponseAgentClassification", propOrder = {
    "sapuuid",
    "businessUnit",
    "businessUnitName",
    "pdt",
    "pdtName",
    "area",
    "areaName",
    "startDate",
    "endDate",
    "salesModel",
    "salesModelName",
    "grade",
    "gradeName",
    "sapSystemAdministrativeData"
})
public class BOExtendCustomerReadByIDResponseAgentClassification {

    @XmlElement(name = "SAP_UUID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String sapuuid;
    @XmlElement(name = "BusinessUnit")
    protected BCOBUCode businessUnit;
    @XmlElement(name = "BusinessUnitName")
    protected String businessUnitName;
    @XmlElement(name = "PDT")
    protected BCOPDTCode pdt;
    @XmlElement(name = "PDTName")
    protected String pdtName;
    @XmlElement(name = "Area")
    protected BCOAREACode area;
    @XmlElement(name = "AreaName")
    protected String areaName;
    @XmlElement(name = "StartDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar startDate;
    @XmlElement(name = "EndDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar endDate;
    @XmlElement(name = "SalesModel")
    protected BCOSALESMODELCode salesModel;
    @XmlElement(name = "SalesModelName")
    protected String salesModelName;
    @XmlElement(name = "Grade")
    protected BCOGRADECode grade;
    @XmlElement(name = "GradeName")
    protected String gradeName;
    @XmlElement(name = "SAP_SystemAdministrativeData")
    protected SystemAdministrativeData sapSystemAdministrativeData;

    /**
     * 获取sapuuid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSAPUUID() {
        return sapuuid;
    }

    /**
     * 设置sapuuid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSAPUUID(String value) {
        this.sapuuid = value;
    }

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
     * 获取businessUnitName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessUnitName() {
        return businessUnitName;
    }

    /**
     * 设置businessUnitName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessUnitName(String value) {
        this.businessUnitName = value;
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
     * 获取pdtName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPDTName() {
        return pdtName;
    }

    /**
     * 设置pdtName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPDTName(String value) {
        this.pdtName = value;
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
     * 获取areaName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * 设置areaName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaName(String value) {
        this.areaName = value;
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
     * 获取salesModelName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesModelName() {
        return salesModelName;
    }

    /**
     * 设置salesModelName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesModelName(String value) {
        this.salesModelName = value;
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

    /**
     * 获取gradeName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGradeName() {
        return gradeName;
    }

    /**
     * 设置gradeName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGradeName(String value) {
        this.gradeName = value;
    }

    /**
     * 获取sapSystemAdministrativeData属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SystemAdministrativeData }
     *     
     */
    public SystemAdministrativeData getSAPSystemAdministrativeData() {
        return sapSystemAdministrativeData;
    }

    /**
     * 设置sapSystemAdministrativeData属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SystemAdministrativeData }
     *     
     */
    public void setSAPSystemAdministrativeData(SystemAdministrativeData value) {
        this.sapSystemAdministrativeData = value;
    }

}
