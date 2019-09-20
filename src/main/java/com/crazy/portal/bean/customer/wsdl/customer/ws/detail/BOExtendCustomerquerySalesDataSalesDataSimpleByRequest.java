
package com.crazy.portal.bean.customer.wsdl.customer.ws.detail;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>BO_ExtendCustomerquerySalesDataSalesDataSimpleByRequest complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="BO_ExtendCustomerquerySalesDataSalesDataSimpleByRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SelectionByCompanyCode" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerquerySalesDataSalesDataSimpleByRequestSelectionByCompanyCode" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SelectionByDeliveringPlant" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerquerySalesDataSalesDataSimpleByRequestSelectionByDeliveringPlant" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SelectionByDistributionChannel" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerquerySalesDataSalesDataSimpleByRequestSelectionByDistributionChannel" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SelectionBySAP_SystemAdministrativeDataCreationDateTime" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerquerySalesDataSalesDataSimpleByRequestSelectionBySAP_SystemAdministrativeDataCreationDateTime" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SelectionBySAP_SystemAdministrativeDataCreationIdentityUUID" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerquerySalesDataSalesDataSimpleByRequestSelectionBySAP_SystemAdministrativeDataCreationIdentityUUID" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SelectionBySAP_SystemAdministrativeDataLastChangeDateTime" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerquerySalesDataSalesDataSimpleByRequestSelectionBySAP_SystemAdministrativeDataLastChangeDateTime" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SelectionBySAP_SystemAdministrativeDataLastChangeIdentityUUID" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerquerySalesDataSalesDataSimpleByRequestSelectionBySAP_SystemAdministrativeDataLastChangeIdentityUUID" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BO_ExtendCustomerquerySalesDataSalesDataSimpleByRequest", propOrder = {
    "selectionByCompanyCode",
    "selectionByDeliveringPlant",
    "selectionByDistributionChannel",
    "selectionBySAPSystemAdministrativeDataCreationDateTime",
    "selectionBySAPSystemAdministrativeDataCreationIdentityUUID",
    "selectionBySAPSystemAdministrativeDataLastChangeDateTime",
    "selectionBySAPSystemAdministrativeDataLastChangeIdentityUUID"
})
public class BOExtendCustomerquerySalesDataSalesDataSimpleByRequest {

    @XmlElement(name = "SelectionByCompanyCode")
    protected List<BOExtendCustomerquerySalesDataSalesDataSimpleByRequestSelectionByCompanyCode> selectionByCompanyCode;
    @XmlElement(name = "SelectionByDeliveringPlant")
    protected List<BOExtendCustomerquerySalesDataSalesDataSimpleByRequestSelectionByDeliveringPlant> selectionByDeliveringPlant;
    @XmlElement(name = "SelectionByDistributionChannel")
    protected List<BOExtendCustomerquerySalesDataSalesDataSimpleByRequestSelectionByDistributionChannel> selectionByDistributionChannel;
    @XmlElement(name = "SelectionBySAP_SystemAdministrativeDataCreationDateTime")
    protected List<BOExtendCustomerquerySalesDataSalesDataSimpleByRequestSelectionBySAPSystemAdministrativeDataCreationDateTime> selectionBySAPSystemAdministrativeDataCreationDateTime;
    @XmlElement(name = "SelectionBySAP_SystemAdministrativeDataCreationIdentityUUID")
    protected List<BOExtendCustomerquerySalesDataSalesDataSimpleByRequestSelectionBySAPSystemAdministrativeDataCreationIdentityUUID> selectionBySAPSystemAdministrativeDataCreationIdentityUUID;
    @XmlElement(name = "SelectionBySAP_SystemAdministrativeDataLastChangeDateTime")
    protected List<BOExtendCustomerquerySalesDataSalesDataSimpleByRequestSelectionBySAPSystemAdministrativeDataLastChangeDateTime> selectionBySAPSystemAdministrativeDataLastChangeDateTime;
    @XmlElement(name = "SelectionBySAP_SystemAdministrativeDataLastChangeIdentityUUID")
    protected List<BOExtendCustomerquerySalesDataSalesDataSimpleByRequestSelectionBySAPSystemAdministrativeDataLastChangeIdentityUUID> selectionBySAPSystemAdministrativeDataLastChangeIdentityUUID;

    /**
     * Gets the value of the selectionByCompanyCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByCompanyCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByCompanyCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BOExtendCustomerquerySalesDataSalesDataSimpleByRequestSelectionByCompanyCode }
     * 
     * 
     */
    public List<BOExtendCustomerquerySalesDataSalesDataSimpleByRequestSelectionByCompanyCode> getSelectionByCompanyCode() {
        if (selectionByCompanyCode == null) {
            selectionByCompanyCode = new ArrayList<BOExtendCustomerquerySalesDataSalesDataSimpleByRequestSelectionByCompanyCode>();
        }
        return this.selectionByCompanyCode;
    }

    /**
     * Gets the value of the selectionByDeliveringPlant property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByDeliveringPlant property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByDeliveringPlant().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BOExtendCustomerquerySalesDataSalesDataSimpleByRequestSelectionByDeliveringPlant }
     * 
     * 
     */
    public List<BOExtendCustomerquerySalesDataSalesDataSimpleByRequestSelectionByDeliveringPlant> getSelectionByDeliveringPlant() {
        if (selectionByDeliveringPlant == null) {
            selectionByDeliveringPlant = new ArrayList<BOExtendCustomerquerySalesDataSalesDataSimpleByRequestSelectionByDeliveringPlant>();
        }
        return this.selectionByDeliveringPlant;
    }

    /**
     * Gets the value of the selectionByDistributionChannel property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByDistributionChannel property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByDistributionChannel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BOExtendCustomerquerySalesDataSalesDataSimpleByRequestSelectionByDistributionChannel }
     * 
     * 
     */
    public List<BOExtendCustomerquerySalesDataSalesDataSimpleByRequestSelectionByDistributionChannel> getSelectionByDistributionChannel() {
        if (selectionByDistributionChannel == null) {
            selectionByDistributionChannel = new ArrayList<BOExtendCustomerquerySalesDataSalesDataSimpleByRequestSelectionByDistributionChannel>();
        }
        return this.selectionByDistributionChannel;
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
     * {@link BOExtendCustomerquerySalesDataSalesDataSimpleByRequestSelectionBySAPSystemAdministrativeDataCreationDateTime }
     * 
     * 
     */
    public List<BOExtendCustomerquerySalesDataSalesDataSimpleByRequestSelectionBySAPSystemAdministrativeDataCreationDateTime> getSelectionBySAPSystemAdministrativeDataCreationDateTime() {
        if (selectionBySAPSystemAdministrativeDataCreationDateTime == null) {
            selectionBySAPSystemAdministrativeDataCreationDateTime = new ArrayList<BOExtendCustomerquerySalesDataSalesDataSimpleByRequestSelectionBySAPSystemAdministrativeDataCreationDateTime>();
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
     * {@link BOExtendCustomerquerySalesDataSalesDataSimpleByRequestSelectionBySAPSystemAdministrativeDataCreationIdentityUUID }
     * 
     * 
     */
    public List<BOExtendCustomerquerySalesDataSalesDataSimpleByRequestSelectionBySAPSystemAdministrativeDataCreationIdentityUUID> getSelectionBySAPSystemAdministrativeDataCreationIdentityUUID() {
        if (selectionBySAPSystemAdministrativeDataCreationIdentityUUID == null) {
            selectionBySAPSystemAdministrativeDataCreationIdentityUUID = new ArrayList<BOExtendCustomerquerySalesDataSalesDataSimpleByRequestSelectionBySAPSystemAdministrativeDataCreationIdentityUUID>();
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
     * {@link BOExtendCustomerquerySalesDataSalesDataSimpleByRequestSelectionBySAPSystemAdministrativeDataLastChangeDateTime }
     * 
     * 
     */
    public List<BOExtendCustomerquerySalesDataSalesDataSimpleByRequestSelectionBySAPSystemAdministrativeDataLastChangeDateTime> getSelectionBySAPSystemAdministrativeDataLastChangeDateTime() {
        if (selectionBySAPSystemAdministrativeDataLastChangeDateTime == null) {
            selectionBySAPSystemAdministrativeDataLastChangeDateTime = new ArrayList<BOExtendCustomerquerySalesDataSalesDataSimpleByRequestSelectionBySAPSystemAdministrativeDataLastChangeDateTime>();
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
     * {@link BOExtendCustomerquerySalesDataSalesDataSimpleByRequestSelectionBySAPSystemAdministrativeDataLastChangeIdentityUUID }
     * 
     * 
     */
    public List<BOExtendCustomerquerySalesDataSalesDataSimpleByRequestSelectionBySAPSystemAdministrativeDataLastChangeIdentityUUID> getSelectionBySAPSystemAdministrativeDataLastChangeIdentityUUID() {
        if (selectionBySAPSystemAdministrativeDataLastChangeIdentityUUID == null) {
            selectionBySAPSystemAdministrativeDataLastChangeIdentityUUID = new ArrayList<BOExtendCustomerquerySalesDataSalesDataSimpleByRequestSelectionBySAPSystemAdministrativeDataLastChangeIdentityUUID>();
        }
        return this.selectionBySAPSystemAdministrativeDataLastChangeIdentityUUID;
    }

}
