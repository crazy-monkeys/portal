
package com.crazy.portal.bean.customer.wsdl.customer.ws.detail;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>BO_ExtendCustomerUpdateRequest complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="BO_ExtendCustomerUpdateRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SAP_UUID" type="{http://sap.com/xi/Common/DataTypes}UUID" minOccurs="0"/&gt;
 *         &lt;element name="CustomerID" type="{http://sap.com/xi/AP/Common/GDT}BusinessPartnerInternalID" minOccurs="0"/&gt;
 *         &lt;element name="SalesData" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerUpdateRequestSalesData" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="AgentClassification" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerUpdateRequestAgentClassification" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="BusinessIntroduction" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerUpdateRequestBusinessIntroduction" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="AssetInfo" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerUpdateRequestAssetInfo" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="ProductInfo" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerUpdateRequestProductInfo" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="OrgInfo" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerUpdateRequestOrgInfo" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="ShareholdingInformation" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerUpdateRequestShareholdingInformation" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="SalesDataListCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" /&gt;
 *       &lt;attribute name="AgentClassificationListCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" /&gt;
 *       &lt;attribute name="BusinessIntroductionListCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" /&gt;
 *       &lt;attribute name="AssetInfoListCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" /&gt;
 *       &lt;attribute name="ProductInfoListCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" /&gt;
 *       &lt;attribute name="OrgInfoListCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" /&gt;
 *       &lt;attribute name="ShareholdingInformationListCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BO_ExtendCustomerUpdateRequest", propOrder = {
    "sapuuid",
    "customerID",
    "salesData",
    "agentClassification",
    "businessIntroduction",
    "assetInfo",
    "productInfo",
    "orgInfo",
    "shareholdingInformation"
})
public class BOExtendCustomerUpdateRequest {

    @XmlElement(name = "SAP_UUID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String sapuuid;
    @XmlElement(name = "CustomerID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String customerID;
    @XmlElement(name = "SalesData")
    protected List<BOExtendCustomerUpdateRequestSalesData> salesData;
    @XmlElement(name = "AgentClassification")
    protected List<BOExtendCustomerUpdateRequestAgentClassification> agentClassification;
    @XmlElement(name = "BusinessIntroduction")
    protected List<BOExtendCustomerUpdateRequestBusinessIntroduction> businessIntroduction;
    @XmlElement(name = "AssetInfo")
    protected List<BOExtendCustomerUpdateRequestAssetInfo> assetInfo;
    @XmlElement(name = "ProductInfo")
    protected List<BOExtendCustomerUpdateRequestProductInfo> productInfo;
    @XmlElement(name = "OrgInfo")
    protected List<BOExtendCustomerUpdateRequestOrgInfo> orgInfo;
    @XmlElement(name = "ShareholdingInformation")
    protected List<BOExtendCustomerUpdateRequestShareholdingInformation> shareholdingInformation;
    @XmlAttribute(name = "SalesDataListCompleteTransmissionIndicator")
    protected Boolean salesDataListCompleteTransmissionIndicator;
    @XmlAttribute(name = "AgentClassificationListCompleteTransmissionIndicator")
    protected Boolean agentClassificationListCompleteTransmissionIndicator;
    @XmlAttribute(name = "BusinessIntroductionListCompleteTransmissionIndicator")
    protected Boolean businessIntroductionListCompleteTransmissionIndicator;
    @XmlAttribute(name = "AssetInfoListCompleteTransmissionIndicator")
    protected Boolean assetInfoListCompleteTransmissionIndicator;
    @XmlAttribute(name = "ProductInfoListCompleteTransmissionIndicator")
    protected Boolean productInfoListCompleteTransmissionIndicator;
    @XmlAttribute(name = "OrgInfoListCompleteTransmissionIndicator")
    protected Boolean orgInfoListCompleteTransmissionIndicator;
    @XmlAttribute(name = "ShareholdingInformationListCompleteTransmissionIndicator")
    protected Boolean shareholdingInformationListCompleteTransmissionIndicator;

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
     * 获取customerID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerID() {
        return customerID;
    }

    /**
     * 设置customerID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerID(String value) {
        this.customerID = value;
    }

    /**
     * Gets the value of the salesData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the salesData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSalesData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BOExtendCustomerUpdateRequestSalesData }
     * 
     * 
     */
    public List<BOExtendCustomerUpdateRequestSalesData> getSalesData() {
        if (salesData == null) {
            salesData = new ArrayList<BOExtendCustomerUpdateRequestSalesData>();
        }
        return this.salesData;
    }

    /**
     * Gets the value of the agentClassification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the agentClassification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAgentClassification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BOExtendCustomerUpdateRequestAgentClassification }
     * 
     * 
     */
    public List<BOExtendCustomerUpdateRequestAgentClassification> getAgentClassification() {
        if (agentClassification == null) {
            agentClassification = new ArrayList<BOExtendCustomerUpdateRequestAgentClassification>();
        }
        return this.agentClassification;
    }

    /**
     * Gets the value of the businessIntroduction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the businessIntroduction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBusinessIntroduction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BOExtendCustomerUpdateRequestBusinessIntroduction }
     * 
     * 
     */
    public List<BOExtendCustomerUpdateRequestBusinessIntroduction> getBusinessIntroduction() {
        if (businessIntroduction == null) {
            businessIntroduction = new ArrayList<BOExtendCustomerUpdateRequestBusinessIntroduction>();
        }
        return this.businessIntroduction;
    }

    /**
     * Gets the value of the assetInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the assetInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssetInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BOExtendCustomerUpdateRequestAssetInfo }
     * 
     * 
     */
    public List<BOExtendCustomerUpdateRequestAssetInfo> getAssetInfo() {
        if (assetInfo == null) {
            assetInfo = new ArrayList<BOExtendCustomerUpdateRequestAssetInfo>();
        }
        return this.assetInfo;
    }

    /**
     * Gets the value of the productInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BOExtendCustomerUpdateRequestProductInfo }
     * 
     * 
     */
    public List<BOExtendCustomerUpdateRequestProductInfo> getProductInfo() {
        if (productInfo == null) {
            productInfo = new ArrayList<BOExtendCustomerUpdateRequestProductInfo>();
        }
        return this.productInfo;
    }

    /**
     * Gets the value of the orgInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the orgInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrgInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BOExtendCustomerUpdateRequestOrgInfo }
     * 
     * 
     */
    public List<BOExtendCustomerUpdateRequestOrgInfo> getOrgInfo() {
        if (orgInfo == null) {
            orgInfo = new ArrayList<BOExtendCustomerUpdateRequestOrgInfo>();
        }
        return this.orgInfo;
    }

    /**
     * Gets the value of the shareholdingInformation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the shareholdingInformation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getShareholdingInformation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BOExtendCustomerUpdateRequestShareholdingInformation }
     * 
     * 
     */
    public List<BOExtendCustomerUpdateRequestShareholdingInformation> getShareholdingInformation() {
        if (shareholdingInformation == null) {
            shareholdingInformation = new ArrayList<BOExtendCustomerUpdateRequestShareholdingInformation>();
        }
        return this.shareholdingInformation;
    }

    /**
     * 获取salesDataListCompleteTransmissionIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSalesDataListCompleteTransmissionIndicator() {
        return salesDataListCompleteTransmissionIndicator;
    }

    /**
     * 设置salesDataListCompleteTransmissionIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSalesDataListCompleteTransmissionIndicator(Boolean value) {
        this.salesDataListCompleteTransmissionIndicator = value;
    }

    /**
     * 获取agentClassificationListCompleteTransmissionIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAgentClassificationListCompleteTransmissionIndicator() {
        return agentClassificationListCompleteTransmissionIndicator;
    }

    /**
     * 设置agentClassificationListCompleteTransmissionIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAgentClassificationListCompleteTransmissionIndicator(Boolean value) {
        this.agentClassificationListCompleteTransmissionIndicator = value;
    }

    /**
     * 获取businessIntroductionListCompleteTransmissionIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBusinessIntroductionListCompleteTransmissionIndicator() {
        return businessIntroductionListCompleteTransmissionIndicator;
    }

    /**
     * 设置businessIntroductionListCompleteTransmissionIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBusinessIntroductionListCompleteTransmissionIndicator(Boolean value) {
        this.businessIntroductionListCompleteTransmissionIndicator = value;
    }

    /**
     * 获取assetInfoListCompleteTransmissionIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAssetInfoListCompleteTransmissionIndicator() {
        return assetInfoListCompleteTransmissionIndicator;
    }

    /**
     * 设置assetInfoListCompleteTransmissionIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAssetInfoListCompleteTransmissionIndicator(Boolean value) {
        this.assetInfoListCompleteTransmissionIndicator = value;
    }

    /**
     * 获取productInfoListCompleteTransmissionIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isProductInfoListCompleteTransmissionIndicator() {
        return productInfoListCompleteTransmissionIndicator;
    }

    /**
     * 设置productInfoListCompleteTransmissionIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setProductInfoListCompleteTransmissionIndicator(Boolean value) {
        this.productInfoListCompleteTransmissionIndicator = value;
    }

    /**
     * 获取orgInfoListCompleteTransmissionIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOrgInfoListCompleteTransmissionIndicator() {
        return orgInfoListCompleteTransmissionIndicator;
    }

    /**
     * 设置orgInfoListCompleteTransmissionIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOrgInfoListCompleteTransmissionIndicator(Boolean value) {
        this.orgInfoListCompleteTransmissionIndicator = value;
    }

    /**
     * 获取shareholdingInformationListCompleteTransmissionIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isShareholdingInformationListCompleteTransmissionIndicator() {
        return shareholdingInformationListCompleteTransmissionIndicator;
    }

    /**
     * 设置shareholdingInformationListCompleteTransmissionIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setShareholdingInformationListCompleteTransmissionIndicator(Boolean value) {
        this.shareholdingInformationListCompleteTransmissionIndicator = value;
    }

}
