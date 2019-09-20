
package com.crazy.portal.bean.customer.wsdl.customer.ws.detail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>BO_ExtendCustomerReadByIDResponseSalesData complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="BO_ExtendCustomerReadByIDResponseSalesData"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SAP_UUID" type="{http://sap.com/xi/Common/DataTypes}UUID" minOccurs="0"/&gt;
 *         &lt;element name="CompanyCode" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BCO_COMPANYCODECode" minOccurs="0"/&gt;
 *         &lt;element name="CompanyName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_LONG_Name" minOccurs="0"/&gt;
 *         &lt;element name="DeliveringPlant" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BCO_DELIVERINGPLANTCode" minOccurs="0"/&gt;
 *         &lt;element name="DeliveringPlantName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_LONG_Name" minOccurs="0"/&gt;
 *         &lt;element name="DistributionChannel" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BCO_DISTRIBUTIONCHANNELCode" minOccurs="0"/&gt;
 *         &lt;element name="DistributionChannelName" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_LONG_Name" minOccurs="0"/&gt;
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
@XmlType(name = "BO_ExtendCustomerReadByIDResponseSalesData", propOrder = {
    "sapuuid",
    "companyCode",
    "companyName",
    "deliveringPlant",
    "deliveringPlantName",
    "distributionChannel",
    "distributionChannelName",
    "sapSystemAdministrativeData"
})
public class BOExtendCustomerReadByIDResponseSalesData {

    @XmlElement(name = "SAP_UUID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String sapuuid;
    @XmlElement(name = "CompanyCode")
    protected BCOCOMPANYCODECode companyCode;
    @XmlElement(name = "CompanyName")
    protected String companyName;
    @XmlElement(name = "DeliveringPlant")
    protected BCODELIVERINGPLANTCode deliveringPlant;
    @XmlElement(name = "DeliveringPlantName")
    protected String deliveringPlantName;
    @XmlElement(name = "DistributionChannel")
    protected BCODISTRIBUTIONCHANNELCode distributionChannel;
    @XmlElement(name = "DistributionChannelName")
    protected String distributionChannelName;
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
     * 获取companyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BCOCOMPANYCODECode }
     *     
     */
    public BCOCOMPANYCODECode getCompanyCode() {
        return companyCode;
    }

    /**
     * 设置companyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BCOCOMPANYCODECode }
     *     
     */
    public void setCompanyCode(BCOCOMPANYCODECode value) {
        this.companyCode = value;
    }

    /**
     * 获取companyName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置companyName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyName(String value) {
        this.companyName = value;
    }

    /**
     * 获取deliveringPlant属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BCODELIVERINGPLANTCode }
     *     
     */
    public BCODELIVERINGPLANTCode getDeliveringPlant() {
        return deliveringPlant;
    }

    /**
     * 设置deliveringPlant属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BCODELIVERINGPLANTCode }
     *     
     */
    public void setDeliveringPlant(BCODELIVERINGPLANTCode value) {
        this.deliveringPlant = value;
    }

    /**
     * 获取deliveringPlantName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveringPlantName() {
        return deliveringPlantName;
    }

    /**
     * 设置deliveringPlantName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveringPlantName(String value) {
        this.deliveringPlantName = value;
    }

    /**
     * 获取distributionChannel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BCODISTRIBUTIONCHANNELCode }
     *     
     */
    public BCODISTRIBUTIONCHANNELCode getDistributionChannel() {
        return distributionChannel;
    }

    /**
     * 设置distributionChannel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BCODISTRIBUTIONCHANNELCode }
     *     
     */
    public void setDistributionChannel(BCODISTRIBUTIONCHANNELCode value) {
        this.distributionChannel = value;
    }

    /**
     * 获取distributionChannelName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistributionChannelName() {
        return distributionChannelName;
    }

    /**
     * 设置distributionChannelName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistributionChannelName(String value) {
        this.distributionChannelName = value;
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
