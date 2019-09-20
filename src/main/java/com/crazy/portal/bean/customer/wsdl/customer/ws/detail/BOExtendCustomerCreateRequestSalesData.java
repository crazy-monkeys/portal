
package com.crazy.portal.bean.customer.wsdl.customer.ws.detail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>BO_ExtendCustomerCreateRequestSalesData complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="BO_ExtendCustomerCreateRequestSalesData"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CompanyCode" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BCO_COMPANYCODECode" minOccurs="0"/&gt;
 *         &lt;element name="DeliveringPlant" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BCO_DELIVERINGPLANTCode" minOccurs="0"/&gt;
 *         &lt;element name="DistributionChannel" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BCO_DISTRIBUTIONCHANNELCode" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BO_ExtendCustomerCreateRequestSalesData", propOrder = {
    "companyCode",
    "deliveringPlant",
    "distributionChannel"
})
public class BOExtendCustomerCreateRequestSalesData {

    @XmlElement(name = "CompanyCode")
    protected BCOCOMPANYCODECode companyCode;
    @XmlElement(name = "DeliveringPlant")
    protected BCODELIVERINGPLANTCode deliveringPlant;
    @XmlElement(name = "DistributionChannel")
    protected BCODISTRIBUTIONCHANNELCode distributionChannel;

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

}
