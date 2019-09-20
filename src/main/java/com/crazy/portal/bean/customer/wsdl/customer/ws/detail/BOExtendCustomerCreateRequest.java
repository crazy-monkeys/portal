
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
 * <p>BO_ExtendCustomerCreateRequest complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="BO_ExtendCustomerCreateRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CustomerID" type="{http://sap.com/xi/AP/Common/GDT}BusinessPartnerInternalID" minOccurs="0"/&gt;
 *         &lt;element name="SalesData" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerCreateRequestSalesData" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="AgentClassification" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerCreateRequestAgentClassification" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="BusinessIntroduction" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerCreateRequestBusinessIntroduction" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="AssetInfo" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerCreateRequestAssetInfo" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="ProductInfo" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerCreateRequestProductInfo" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="OrgInfo" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerCreateRequestOrgInfo" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="ShareholdingInformation" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerCreateRequestShareholdingInformation" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BO_ExtendCustomerCreateRequest", propOrder = {
    "customerID",
    "salesData",
    "agentClassification",
    "businessIntroduction",
    "assetInfo",
    "productInfo",
    "orgInfo",
    "shareholdingInformation"
})
public class BOExtendCustomerCreateRequest {

    @XmlElement(name = "CustomerID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String customerID;
    @XmlElement(name = "SalesData")
    protected List<BOExtendCustomerCreateRequestSalesData> salesData;
    @XmlElement(name = "AgentClassification")
    protected List<BOExtendCustomerCreateRequestAgentClassification> agentClassification;
    @XmlElement(name = "BusinessIntroduction")
    protected List<BOExtendCustomerCreateRequestBusinessIntroduction> businessIntroduction;
    @XmlElement(name = "AssetInfo")
    protected List<BOExtendCustomerCreateRequestAssetInfo> assetInfo;
    @XmlElement(name = "ProductInfo")
    protected List<BOExtendCustomerCreateRequestProductInfo> productInfo;
    @XmlElement(name = "OrgInfo")
    protected List<BOExtendCustomerCreateRequestOrgInfo> orgInfo;
    @XmlElement(name = "ShareholdingInformation")
    protected List<BOExtendCustomerCreateRequestShareholdingInformation> shareholdingInformation;

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
     * {@link BOExtendCustomerCreateRequestSalesData }
     * 
     * 
     */
    public List<BOExtendCustomerCreateRequestSalesData> getSalesData() {
        if (salesData == null) {
            salesData = new ArrayList<BOExtendCustomerCreateRequestSalesData>();
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
     * {@link BOExtendCustomerCreateRequestAgentClassification }
     * 
     * 
     */
    public List<BOExtendCustomerCreateRequestAgentClassification> getAgentClassification() {
        if (agentClassification == null) {
            agentClassification = new ArrayList<BOExtendCustomerCreateRequestAgentClassification>();
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
     * {@link BOExtendCustomerCreateRequestBusinessIntroduction }
     * 
     * 
     */
    public List<BOExtendCustomerCreateRequestBusinessIntroduction> getBusinessIntroduction() {
        if (businessIntroduction == null) {
            businessIntroduction = new ArrayList<BOExtendCustomerCreateRequestBusinessIntroduction>();
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
     * {@link BOExtendCustomerCreateRequestAssetInfo }
     * 
     * 
     */
    public List<BOExtendCustomerCreateRequestAssetInfo> getAssetInfo() {
        if (assetInfo == null) {
            assetInfo = new ArrayList<BOExtendCustomerCreateRequestAssetInfo>();
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
     * {@link BOExtendCustomerCreateRequestProductInfo }
     * 
     * 
     */
    public List<BOExtendCustomerCreateRequestProductInfo> getProductInfo() {
        if (productInfo == null) {
            productInfo = new ArrayList<BOExtendCustomerCreateRequestProductInfo>();
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
     * {@link BOExtendCustomerCreateRequestOrgInfo }
     * 
     * 
     */
    public List<BOExtendCustomerCreateRequestOrgInfo> getOrgInfo() {
        if (orgInfo == null) {
            orgInfo = new ArrayList<BOExtendCustomerCreateRequestOrgInfo>();
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
     * {@link BOExtendCustomerCreateRequestShareholdingInformation }
     * 
     * 
     */
    public List<BOExtendCustomerCreateRequestShareholdingInformation> getShareholdingInformation() {
        if (shareholdingInformation == null) {
            shareholdingInformation = new ArrayList<BOExtendCustomerCreateRequestShareholdingInformation>();
        }
        return this.shareholdingInformation;
    }

}
