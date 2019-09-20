
package com.crazy.portal.bean.customer.wsdl.customer.ws.detail;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>BO_ExtendCustomerQuery_EXT_Cust_AssetInfoAssetInfoSimpleByRequest complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="BO_ExtendCustomerQuery_EXT_Cust_AssetInfoAssetInfoSimpleByRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SelectionByYear" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerQuery_EXT_Cust_AssetInfoAssetInfoSimpleByRequestSelectionByYear" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SelectionBySeason" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerQuery_EXT_Cust_AssetInfoAssetInfoSimpleByRequestSelectionBySeason" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SelectionByTotalAssets" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerQuery_EXT_Cust_AssetInfoAssetInfoSimpleByRequestSelectionByTotalAssets" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SelectionByNetAssets" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerQuery_EXT_Cust_AssetInfoAssetInfoSimpleByRequestSelectionByNetAssets" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SelectionByRevenue" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerQuery_EXT_Cust_AssetInfoAssetInfoSimpleByRequestSelectionByRevenue" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SelectionByTotalStaff" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerQuery_EXT_Cust_AssetInfoAssetInfoSimpleByRequestSelectionByTotalStaff" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SelectionBySAP_SystemAdministrativeDataCreationDateTime" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerQuery_EXT_Cust_AssetInfoAssetInfoSimpleByRequestSelectionBySAP_SystemAdministrativeDataCreationDateTime" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SelectionBySAP_SystemAdministrativeDataCreationIdentityUUID" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BOExtCustQueryEXTCustAssetInfoAssetInfoSimpleByRequestSelectionBySAPSystemAdminDataCreationIdentity" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SelectionBySAP_SystemAdministrativeDataLastChangeDateTime" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BOExtCustQueryAssetInfoByRequestSelectionByAdminDataLastChangeDateTi" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SelectionBySAP_SystemAdministrativeDataLastChangeIdentityUUID" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BOExtCustQueryAssetInfoByRequestSelectionByAdminDataLastChangeIdenti" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BO_ExtendCustomerQuery_EXT_Cust_AssetInfoAssetInfoSimpleByRequest", propOrder = {
    "selectionByYear",
    "selectionBySeason",
    "selectionByTotalAssets",
    "selectionByNetAssets",
    "selectionByRevenue",
    "selectionByTotalStaff",
    "selectionBySAPSystemAdministrativeDataCreationDateTime",
    "selectionBySAPSystemAdministrativeDataCreationIdentityUUID",
    "selectionBySAPSystemAdministrativeDataLastChangeDateTime",
    "selectionBySAPSystemAdministrativeDataLastChangeIdentityUUID"
})
public class BOExtendCustomerQueryEXTCustAssetInfoAssetInfoSimpleByRequest {

    @XmlElement(name = "SelectionByYear")
    protected List<BOExtendCustomerQueryEXTCustAssetInfoAssetInfoSimpleByRequestSelectionByYear> selectionByYear;
    @XmlElement(name = "SelectionBySeason")
    protected List<BOExtendCustomerQueryEXTCustAssetInfoAssetInfoSimpleByRequestSelectionBySeason> selectionBySeason;
    @XmlElement(name = "SelectionByTotalAssets")
    protected List<BOExtendCustomerQueryEXTCustAssetInfoAssetInfoSimpleByRequestSelectionByTotalAssets> selectionByTotalAssets;
    @XmlElement(name = "SelectionByNetAssets")
    protected List<BOExtendCustomerQueryEXTCustAssetInfoAssetInfoSimpleByRequestSelectionByNetAssets> selectionByNetAssets;
    @XmlElement(name = "SelectionByRevenue")
    protected List<BOExtendCustomerQueryEXTCustAssetInfoAssetInfoSimpleByRequestSelectionByRevenue> selectionByRevenue;
    @XmlElement(name = "SelectionByTotalStaff")
    protected List<BOExtendCustomerQueryEXTCustAssetInfoAssetInfoSimpleByRequestSelectionByTotalStaff> selectionByTotalStaff;
    @XmlElement(name = "SelectionBySAP_SystemAdministrativeDataCreationDateTime")
    protected List<BOExtendCustomerQueryEXTCustAssetInfoAssetInfoSimpleByRequestSelectionBySAPSystemAdministrativeDataCreationDateTime> selectionBySAPSystemAdministrativeDataCreationDateTime;
    @XmlElement(name = "SelectionBySAP_SystemAdministrativeDataCreationIdentityUUID")
    protected List<BOExtCustQueryEXTCustAssetInfoAssetInfoSimpleByRequestSelectionBySAPSystemAdminDataCreationIdentity> selectionBySAPSystemAdministrativeDataCreationIdentityUUID;
    @XmlElement(name = "SelectionBySAP_SystemAdministrativeDataLastChangeDateTime")
    protected List<BOExtCustQueryAssetInfoByRequestSelectionByAdminDataLastChangeDateTi> selectionBySAPSystemAdministrativeDataLastChangeDateTime;
    @XmlElement(name = "SelectionBySAP_SystemAdministrativeDataLastChangeIdentityUUID")
    protected List<BOExtCustQueryAssetInfoByRequestSelectionByAdminDataLastChangeIdenti> selectionBySAPSystemAdministrativeDataLastChangeIdentityUUID;

    /**
     * Gets the value of the selectionByYear property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByYear property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByYear().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BOExtendCustomerQueryEXTCustAssetInfoAssetInfoSimpleByRequestSelectionByYear }
     * 
     * 
     */
    public List<BOExtendCustomerQueryEXTCustAssetInfoAssetInfoSimpleByRequestSelectionByYear> getSelectionByYear() {
        if (selectionByYear == null) {
            selectionByYear = new ArrayList<BOExtendCustomerQueryEXTCustAssetInfoAssetInfoSimpleByRequestSelectionByYear>();
        }
        return this.selectionByYear;
    }

    /**
     * Gets the value of the selectionBySeason property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionBySeason property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionBySeason().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BOExtendCustomerQueryEXTCustAssetInfoAssetInfoSimpleByRequestSelectionBySeason }
     * 
     * 
     */
    public List<BOExtendCustomerQueryEXTCustAssetInfoAssetInfoSimpleByRequestSelectionBySeason> getSelectionBySeason() {
        if (selectionBySeason == null) {
            selectionBySeason = new ArrayList<BOExtendCustomerQueryEXTCustAssetInfoAssetInfoSimpleByRequestSelectionBySeason>();
        }
        return this.selectionBySeason;
    }

    /**
     * Gets the value of the selectionByTotalAssets property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByTotalAssets property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByTotalAssets().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BOExtendCustomerQueryEXTCustAssetInfoAssetInfoSimpleByRequestSelectionByTotalAssets }
     * 
     * 
     */
    public List<BOExtendCustomerQueryEXTCustAssetInfoAssetInfoSimpleByRequestSelectionByTotalAssets> getSelectionByTotalAssets() {
        if (selectionByTotalAssets == null) {
            selectionByTotalAssets = new ArrayList<BOExtendCustomerQueryEXTCustAssetInfoAssetInfoSimpleByRequestSelectionByTotalAssets>();
        }
        return this.selectionByTotalAssets;
    }

    /**
     * Gets the value of the selectionByNetAssets property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByNetAssets property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByNetAssets().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BOExtendCustomerQueryEXTCustAssetInfoAssetInfoSimpleByRequestSelectionByNetAssets }
     * 
     * 
     */
    public List<BOExtendCustomerQueryEXTCustAssetInfoAssetInfoSimpleByRequestSelectionByNetAssets> getSelectionByNetAssets() {
        if (selectionByNetAssets == null) {
            selectionByNetAssets = new ArrayList<BOExtendCustomerQueryEXTCustAssetInfoAssetInfoSimpleByRequestSelectionByNetAssets>();
        }
        return this.selectionByNetAssets;
    }

    /**
     * Gets the value of the selectionByRevenue property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByRevenue property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByRevenue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BOExtendCustomerQueryEXTCustAssetInfoAssetInfoSimpleByRequestSelectionByRevenue }
     * 
     * 
     */
    public List<BOExtendCustomerQueryEXTCustAssetInfoAssetInfoSimpleByRequestSelectionByRevenue> getSelectionByRevenue() {
        if (selectionByRevenue == null) {
            selectionByRevenue = new ArrayList<BOExtendCustomerQueryEXTCustAssetInfoAssetInfoSimpleByRequestSelectionByRevenue>();
        }
        return this.selectionByRevenue;
    }

    /**
     * Gets the value of the selectionByTotalStaff property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByTotalStaff property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByTotalStaff().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BOExtendCustomerQueryEXTCustAssetInfoAssetInfoSimpleByRequestSelectionByTotalStaff }
     * 
     * 
     */
    public List<BOExtendCustomerQueryEXTCustAssetInfoAssetInfoSimpleByRequestSelectionByTotalStaff> getSelectionByTotalStaff() {
        if (selectionByTotalStaff == null) {
            selectionByTotalStaff = new ArrayList<BOExtendCustomerQueryEXTCustAssetInfoAssetInfoSimpleByRequestSelectionByTotalStaff>();
        }
        return this.selectionByTotalStaff;
    }

    /**
     * Gets the value of the selectionBySAPSystemAdministrativeDataCreationDateTime property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionBySAPSystemAdministrativeDataCreationDateTime property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionBySAPSystemAdministrativeDataCreationDateTime().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BOExtendCustomerQueryEXTCustAssetInfoAssetInfoSimpleByRequestSelectionBySAPSystemAdministrativeDataCreationDateTime }
     * 
     * 
     */
    public List<BOExtendCustomerQueryEXTCustAssetInfoAssetInfoSimpleByRequestSelectionBySAPSystemAdministrativeDataCreationDateTime> getSelectionBySAPSystemAdministrativeDataCreationDateTime() {
        if (selectionBySAPSystemAdministrativeDataCreationDateTime == null) {
            selectionBySAPSystemAdministrativeDataCreationDateTime = new ArrayList<BOExtendCustomerQueryEXTCustAssetInfoAssetInfoSimpleByRequestSelectionBySAPSystemAdministrativeDataCreationDateTime>();
        }
        return this.selectionBySAPSystemAdministrativeDataCreationDateTime;
    }

    /**
     * Gets the value of the selectionBySAPSystemAdministrativeDataCreationIdentityUUID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionBySAPSystemAdministrativeDataCreationIdentityUUID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionBySAPSystemAdministrativeDataCreationIdentityUUID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BOExtCustQueryEXTCustAssetInfoAssetInfoSimpleByRequestSelectionBySAPSystemAdminDataCreationIdentity }
     * 
     * 
     */
    public List<BOExtCustQueryEXTCustAssetInfoAssetInfoSimpleByRequestSelectionBySAPSystemAdminDataCreationIdentity> getSelectionBySAPSystemAdministrativeDataCreationIdentityUUID() {
        if (selectionBySAPSystemAdministrativeDataCreationIdentityUUID == null) {
            selectionBySAPSystemAdministrativeDataCreationIdentityUUID = new ArrayList<BOExtCustQueryEXTCustAssetInfoAssetInfoSimpleByRequestSelectionBySAPSystemAdminDataCreationIdentity>();
        }
        return this.selectionBySAPSystemAdministrativeDataCreationIdentityUUID;
    }

    /**
     * Gets the value of the selectionBySAPSystemAdministrativeDataLastChangeDateTime property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionBySAPSystemAdministrativeDataLastChangeDateTime property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionBySAPSystemAdministrativeDataLastChangeDateTime().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BOExtCustQueryAssetInfoByRequestSelectionByAdminDataLastChangeDateTi }
     * 
     * 
     */
    public List<BOExtCustQueryAssetInfoByRequestSelectionByAdminDataLastChangeDateTi> getSelectionBySAPSystemAdministrativeDataLastChangeDateTime() {
        if (selectionBySAPSystemAdministrativeDataLastChangeDateTime == null) {
            selectionBySAPSystemAdministrativeDataLastChangeDateTime = new ArrayList<BOExtCustQueryAssetInfoByRequestSelectionByAdminDataLastChangeDateTi>();
        }
        return this.selectionBySAPSystemAdministrativeDataLastChangeDateTime;
    }

    /**
     * Gets the value of the selectionBySAPSystemAdministrativeDataLastChangeIdentityUUID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionBySAPSystemAdministrativeDataLastChangeIdentityUUID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionBySAPSystemAdministrativeDataLastChangeIdentityUUID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BOExtCustQueryAssetInfoByRequestSelectionByAdminDataLastChangeIdenti }
     * 
     * 
     */
    public List<BOExtCustQueryAssetInfoByRequestSelectionByAdminDataLastChangeIdenti> getSelectionBySAPSystemAdministrativeDataLastChangeIdentityUUID() {
        if (selectionBySAPSystemAdministrativeDataLastChangeIdentityUUID == null) {
            selectionBySAPSystemAdministrativeDataLastChangeIdentityUUID = new ArrayList<BOExtCustQueryAssetInfoByRequestSelectionByAdminDataLastChangeIdenti>();
        }
        return this.selectionBySAPSystemAdministrativeDataLastChangeIdentityUUID;
    }

}
