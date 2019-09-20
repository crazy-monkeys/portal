
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
 * <p>BO_ExtendCustomerReadByIDResponseContractInfo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="BO_ExtendCustomerReadByIDResponseContractInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SAP_UUID" type="{http://sap.com/xi/Common/DataTypes}UUID" minOccurs="0"/&gt;
 *         &lt;element name="ContractType" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BCO_CONTRACTTYPECode" minOccurs="0"/&gt;
 *         &lt;element name="ContractTypeName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_LONG_Name" minOccurs="0"/&gt;
 *         &lt;element name="ContractID" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_SHORT_Description" minOccurs="0"/&gt;
 *         &lt;element name="CustomerSignatory" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_LONG_Description" minOccurs="0"/&gt;
 *         &lt;element name="UnisocSignatory" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_LONG_Description" minOccurs="0"/&gt;
 *         &lt;element name="ContractStartDate" type="{http://sap.com/xi/AP/Common/GDT}Date" minOccurs="0"/&gt;
 *         &lt;element name="ContractEndDate" type="{http://sap.com/xi/AP/Common/GDT}Date" minOccurs="0"/&gt;
 *         &lt;element name="NRE" type="{http://sap.com/xi/AP/Common/GDT}Amount" minOccurs="0"/&gt;
 *         &lt;element name="HasCashDeposit" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}YesNoCode" minOccurs="0"/&gt;
 *         &lt;element name="HasCashDepositName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_LONG_Name" minOccurs="0"/&gt;
 *         &lt;element name="CashDeposit" type="{http://sap.com/xi/AP/Common/GDT}Amount" minOccurs="0"/&gt;
 *         &lt;element name="ConditionDepositReturn" type="{http://sap.com/xi/AP/PDI/bo}String" minOccurs="0"/&gt;
 *         &lt;element name="MainContent" type="{http://sap.com/xi/AP/PDI/bo}String" minOccurs="0"/&gt;
 *         &lt;element name="LicenseAuthorization" type="{http://sap.com/xi/AP/PDI/bo}String" minOccurs="0"/&gt;
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
@XmlType(name = "BO_ExtendCustomerReadByIDResponseContractInfo", propOrder = {
    "sapuuid",
    "contractType",
    "contractTypeName",
    "contractID",
    "customerSignatory",
    "unisocSignatory",
    "contractStartDate",
    "contractEndDate",
    "nre",
    "hasCashDeposit",
    "hasCashDepositName",
    "cashDeposit",
    "conditionDepositReturn",
    "mainContent",
    "licenseAuthorization",
    "sapSystemAdministrativeData"
})
public class BOExtendCustomerReadByIDResponseContractInfo {

    @XmlElement(name = "SAP_UUID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String sapuuid;
    @XmlElement(name = "ContractType")
    protected BCOCONTRACTTYPECode contractType;
    @XmlElement(name = "ContractTypeName")
    protected String contractTypeName;
    @XmlElement(name = "ContractID")
    protected String contractID;
    @XmlElement(name = "CustomerSignatory")
    protected String customerSignatory;
    @XmlElement(name = "UnisocSignatory")
    protected String unisocSignatory;
    @XmlElement(name = "ContractStartDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar contractStartDate;
    @XmlElement(name = "ContractEndDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar contractEndDate;
    @XmlElement(name = "NRE")
    protected Amount nre;
    @XmlElement(name = "HasCashDeposit")
    @XmlSchemaType(name = "token")
    protected YesNoCode hasCashDeposit;
    @XmlElement(name = "HasCashDepositName")
    protected String hasCashDepositName;
    @XmlElement(name = "CashDeposit")
    protected Amount cashDeposit;
    @XmlElement(name = "ConditionDepositReturn")
    protected String conditionDepositReturn;
    @XmlElement(name = "MainContent")
    protected String mainContent;
    @XmlElement(name = "LicenseAuthorization")
    protected String licenseAuthorization;
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
     * 获取contractType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BCOCONTRACTTYPECode }
     *     
     */
    public BCOCONTRACTTYPECode getContractType() {
        return contractType;
    }

    /**
     * 设置contractType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BCOCONTRACTTYPECode }
     *     
     */
    public void setContractType(BCOCONTRACTTYPECode value) {
        this.contractType = value;
    }

    /**
     * 获取contractTypeName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractTypeName() {
        return contractTypeName;
    }

    /**
     * 设置contractTypeName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractTypeName(String value) {
        this.contractTypeName = value;
    }

    /**
     * 获取contractID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractID() {
        return contractID;
    }

    /**
     * 设置contractID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractID(String value) {
        this.contractID = value;
    }

    /**
     * 获取customerSignatory属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerSignatory() {
        return customerSignatory;
    }

    /**
     * 设置customerSignatory属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerSignatory(String value) {
        this.customerSignatory = value;
    }

    /**
     * 获取unisocSignatory属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnisocSignatory() {
        return unisocSignatory;
    }

    /**
     * 设置unisocSignatory属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnisocSignatory(String value) {
        this.unisocSignatory = value;
    }

    /**
     * 获取contractStartDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getContractStartDate() {
        return contractStartDate;
    }

    /**
     * 设置contractStartDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setContractStartDate(XMLGregorianCalendar value) {
        this.contractStartDate = value;
    }

    /**
     * 获取contractEndDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getContractEndDate() {
        return contractEndDate;
    }

    /**
     * 设置contractEndDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setContractEndDate(XMLGregorianCalendar value) {
        this.contractEndDate = value;
    }

    /**
     * 获取nre属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getNRE() {
        return nre;
    }

    /**
     * 设置nre属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setNRE(Amount value) {
        this.nre = value;
    }

    /**
     * 获取hasCashDeposit属性的值。
     * 
     * @return
     *     possible object is
     *     {@link YesNoCode }
     *     
     */
    public YesNoCode getHasCashDeposit() {
        return hasCashDeposit;
    }

    /**
     * 设置hasCashDeposit属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link YesNoCode }
     *     
     */
    public void setHasCashDeposit(YesNoCode value) {
        this.hasCashDeposit = value;
    }

    /**
     * 获取hasCashDepositName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHasCashDepositName() {
        return hasCashDepositName;
    }

    /**
     * 设置hasCashDepositName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHasCashDepositName(String value) {
        this.hasCashDepositName = value;
    }

    /**
     * 获取cashDeposit属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getCashDeposit() {
        return cashDeposit;
    }

    /**
     * 设置cashDeposit属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setCashDeposit(Amount value) {
        this.cashDeposit = value;
    }

    /**
     * 获取conditionDepositReturn属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConditionDepositReturn() {
        return conditionDepositReturn;
    }

    /**
     * 设置conditionDepositReturn属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConditionDepositReturn(String value) {
        this.conditionDepositReturn = value;
    }

    /**
     * 获取mainContent属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMainContent() {
        return mainContent;
    }

    /**
     * 设置mainContent属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMainContent(String value) {
        this.mainContent = value;
    }

    /**
     * 获取licenseAuthorization属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLicenseAuthorization() {
        return licenseAuthorization;
    }

    /**
     * 设置licenseAuthorization属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLicenseAuthorization(String value) {
        this.licenseAuthorization = value;
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
