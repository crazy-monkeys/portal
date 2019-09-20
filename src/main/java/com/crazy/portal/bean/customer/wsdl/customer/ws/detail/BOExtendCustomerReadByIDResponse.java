
package com.crazy.portal.bean.customer.wsdl.customer.ws.detail;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>BO_ExtendCustomerReadByIDResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="BO_ExtendCustomerReadByIDResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SAP_UUID" type="{http://sap.com/xi/Common/DataTypes}UUID" minOccurs="0"/&gt;
 *         &lt;element name="CustomerID" type="{http://sap.com/xi/AP/Common/GDT}BusinessPartnerInternalID" minOccurs="0"/&gt;
 *         &lt;element name="SAP_ToCustomer" type="{http://sap.com/xi/AP/Common/GDT}UUID" minOccurs="0"/&gt;
 *         &lt;element name="SalesData" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerReadByIDResponseSalesData" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="AgentClassification" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerReadByIDResponseAgentClassification" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="BusinessIntroduction" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerReadByIDResponseBusinessIntroduction" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="AssetInfo" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerReadByIDResponseAssetInfo" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="ContractInfo" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerReadByIDResponseContractInfo" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="ToCustomer" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerReadByIDResponseToCustomer" minOccurs="0"/&gt;
 *         &lt;element name="ProductInfo" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerReadByIDResponseProductInfo" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="OrgInfo" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerReadByIDResponseOrgInfo" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="ShareholdingInformation" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerReadByIDResponseShareholdingInformation" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BO_ExtendCustomerReadByIDResponse", propOrder = {
    "sapuuid",
    "customerID",
    "sapToCustomer",
    "salesData",
    "agentClassification",
    "businessIntroduction",
    "assetInfo",
    "contractInfo",
    "toCustomer",
    "productInfo",
    "orgInfo",
    "shareholdingInformation"
})
public class BOExtendCustomerReadByIDResponse {

    @XmlElement(name = "SAP_UUID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String sapuuid;
    @XmlElement(name = "CustomerID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String customerID;
    @XmlElement(name = "SAP_ToCustomer")
    protected UUID sapToCustomer;
    @XmlElement(name = "SalesData")
    protected List<BOExtendCustomerReadByIDResponseSalesData> salesData;
    @XmlElement(name = "AgentClassification")
    protected List<BOExtendCustomerReadByIDResponseAgentClassification> agentClassification;
    @XmlElement(name = "BusinessIntroduction")
    protected List<BOExtendCustomerReadByIDResponseBusinessIntroduction> businessIntroduction;
    @XmlElement(name = "AssetInfo")
    protected List<BOExtendCustomerReadByIDResponseAssetInfo> assetInfo;
    @XmlElement(name = "ContractInfo")
    protected List<BOExtendCustomerReadByIDResponseContractInfo> contractInfo;
    @XmlElement(name = "ToCustomer")
    protected BOExtendCustomerReadByIDResponseToCustomer toCustomer;
    @XmlElement(name = "ProductInfo")
    protected List<BOExtendCustomerReadByIDResponseProductInfo> productInfo;
    @XmlElement(name = "OrgInfo")
    protected List<BOExtendCustomerReadByIDResponseOrgInfo> orgInfo;
    @XmlElement(name = "ShareholdingInformation")
    protected List<BOExtendCustomerReadByIDResponseShareholdingInformation> shareholdingInformation;

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
     * 获取sapToCustomer属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UUID }
     *     
     */
    public UUID getSAPToCustomer() {
        return sapToCustomer;
    }

    /**
     * 设置sapToCustomer属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UUID }
     *     
     */
    public void setSAPToCustomer(UUID value) {
        this.sapToCustomer = value;
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
     * {@link BOExtendCustomerReadByIDResponseSalesData }
     * 
     * 
     */
    public List<BOExtendCustomerReadByIDResponseSalesData> getSalesData() {
        if (salesData == null) {
            salesData = new ArrayList<BOExtendCustomerReadByIDResponseSalesData>();
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
     * {@link BOExtendCustomerReadByIDResponseAgentClassification }
     * 
     * 
     */
    public List<BOExtendCustomerReadByIDResponseAgentClassification> getAgentClassification() {
        if (agentClassification == null) {
            agentClassification = new ArrayList<BOExtendCustomerReadByIDResponseAgentClassification>();
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
     * {@link BOExtendCustomerReadByIDResponseBusinessIntroduction }
     * 
     * 
     */
    public List<BOExtendCustomerReadByIDResponseBusinessIntroduction> getBusinessIntroduction() {
        if (businessIntroduction == null) {
            businessIntroduction = new ArrayList<BOExtendCustomerReadByIDResponseBusinessIntroduction>();
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
     * {@link BOExtendCustomerReadByIDResponseAssetInfo }
     * 
     * 
     */
    public List<BOExtendCustomerReadByIDResponseAssetInfo> getAssetInfo() {
        if (assetInfo == null) {
            assetInfo = new ArrayList<BOExtendCustomerReadByIDResponseAssetInfo>();
        }
        return this.assetInfo;
    }

    /**
     * Gets the value of the contractInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contractInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContractInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BOExtendCustomerReadByIDResponseContractInfo }
     * 
     * 
     */
    public List<BOExtendCustomerReadByIDResponseContractInfo> getContractInfo() {
        if (contractInfo == null) {
            contractInfo = new ArrayList<BOExtendCustomerReadByIDResponseContractInfo>();
        }
        return this.contractInfo;
    }

    /**
     * 获取toCustomer属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BOExtendCustomerReadByIDResponseToCustomer }
     *     
     */
    public BOExtendCustomerReadByIDResponseToCustomer getToCustomer() {
        return toCustomer;
    }

    /**
     * 设置toCustomer属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BOExtendCustomerReadByIDResponseToCustomer }
     *     
     */
    public void setToCustomer(BOExtendCustomerReadByIDResponseToCustomer value) {
        this.toCustomer = value;
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
     * {@link BOExtendCustomerReadByIDResponseProductInfo }
     * 
     * 
     */
    public List<BOExtendCustomerReadByIDResponseProductInfo> getProductInfo() {
        if (productInfo == null) {
            productInfo = new ArrayList<BOExtendCustomerReadByIDResponseProductInfo>();
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
     * {@link BOExtendCustomerReadByIDResponseOrgInfo }
     * 
     * 
     */
    public List<BOExtendCustomerReadByIDResponseOrgInfo> getOrgInfo() {
        if (orgInfo == null) {
            orgInfo = new ArrayList<BOExtendCustomerReadByIDResponseOrgInfo>();
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
     * {@link BOExtendCustomerReadByIDResponseShareholdingInformation }
     * 
     * 
     */
    public List<BOExtendCustomerReadByIDResponseShareholdingInformation> getShareholdingInformation() {
        if (shareholdingInformation == null) {
            shareholdingInformation = new ArrayList<BOExtendCustomerReadByIDResponseShareholdingInformation>();
        }
        return this.shareholdingInformation;
    }

}
