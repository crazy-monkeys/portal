
package com.crazy.portal.bean.customer.wsdl.customer.ws.detail;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>BO_ExtendCustomerReadByIDResponseShareholdingInformation complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="BO_ExtendCustomerReadByIDResponseShareholdingInformation"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SAP_UUID" type="{http://sap.com/xi/Common/DataTypes}UUID" minOccurs="0"/&gt;
 *         &lt;element name="Z_SUPERIOR_SHAREHOLDER" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_SHORT_Description" minOccurs="0"/&gt;
 *         &lt;element name="Z_SHAREHOLDER" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_SHORT_Description" minOccurs="0"/&gt;
 *         &lt;element name="Z_EQUITY_RATIO" type="{http://sap.com/xi/AP/Common/GDT}DecimalValue" minOccurs="0"/&gt;
 *         &lt;element name="Z_NATURE_OF_SHAREHOLDER" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_SHORT_Description" minOccurs="0"/&gt;
 *         &lt;element name="Z_NATURE_OF_COMPANY" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_SHORT_Description" minOccurs="0"/&gt;
 *         &lt;element name="Z_IS_MANAGER" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_SHORT_Description" minOccurs="0"/&gt;
 *         &lt;element name="Z_DEPARTMENT" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_SHORT_Description" minOccurs="0"/&gt;
 *         &lt;element name="Z_TITLE" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_SHORT_Description" minOccurs="0"/&gt;
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
@XmlType(name = "BO_ExtendCustomerReadByIDResponseShareholdingInformation", propOrder = {
    "sapuuid",
    "zsuperiorshareholder",
    "zshareholder",
    "zequityratio",
    "znatureofshareholder",
    "znatureofcompany",
    "zismanager",
    "zdepartment",
    "ztitle",
    "sapSystemAdministrativeData"
})
public class BOExtendCustomerReadByIDResponseShareholdingInformation {

    @XmlElement(name = "SAP_UUID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String sapuuid;
    @XmlElement(name = "Z_SUPERIOR_SHAREHOLDER")
    protected String zsuperiorshareholder;
    @XmlElement(name = "Z_SHAREHOLDER")
    protected String zshareholder;
    @XmlElement(name = "Z_EQUITY_RATIO")
    protected BigDecimal zequityratio;
    @XmlElement(name = "Z_NATURE_OF_SHAREHOLDER")
    protected String znatureofshareholder;
    @XmlElement(name = "Z_NATURE_OF_COMPANY")
    protected String znatureofcompany;
    @XmlElement(name = "Z_IS_MANAGER")
    protected String zismanager;
    @XmlElement(name = "Z_DEPARTMENT")
    protected String zdepartment;
    @XmlElement(name = "Z_TITLE")
    protected String ztitle;
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
     * 获取zsuperiorshareholder属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZSUPERIORSHAREHOLDER() {
        return zsuperiorshareholder;
    }

    /**
     * 设置zsuperiorshareholder属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZSUPERIORSHAREHOLDER(String value) {
        this.zsuperiorshareholder = value;
    }

    /**
     * 获取zshareholder属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZSHAREHOLDER() {
        return zshareholder;
    }

    /**
     * 设置zshareholder属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZSHAREHOLDER(String value) {
        this.zshareholder = value;
    }

    /**
     * 获取zequityratio属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getZEQUITYRATIO() {
        return zequityratio;
    }

    /**
     * 设置zequityratio属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setZEQUITYRATIO(BigDecimal value) {
        this.zequityratio = value;
    }

    /**
     * 获取znatureofshareholder属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZNATUREOFSHAREHOLDER() {
        return znatureofshareholder;
    }

    /**
     * 设置znatureofshareholder属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZNATUREOFSHAREHOLDER(String value) {
        this.znatureofshareholder = value;
    }

    /**
     * 获取znatureofcompany属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZNATUREOFCOMPANY() {
        return znatureofcompany;
    }

    /**
     * 设置znatureofcompany属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZNATUREOFCOMPANY(String value) {
        this.znatureofcompany = value;
    }

    /**
     * 获取zismanager属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZISMANAGER() {
        return zismanager;
    }

    /**
     * 设置zismanager属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZISMANAGER(String value) {
        this.zismanager = value;
    }

    /**
     * 获取zdepartment属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZDEPARTMENT() {
        return zdepartment;
    }

    /**
     * 设置zdepartment属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZDEPARTMENT(String value) {
        this.zdepartment = value;
    }

    /**
     * 获取ztitle属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZTITLE() {
        return ztitle;
    }

    /**
     * 设置ztitle属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZTITLE(String value) {
        this.ztitle = value;
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
