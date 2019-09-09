
package com.crazy.portal.bean.customer.wsdl.orgnation;

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
 * <p>OrganisationalUnitByElementsResponse_sync complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="OrganisationalUnitByElementsResponse_sync"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ChangeStateID" type="{http://sap.com/xi/AP/Common/GDT}ChangeStateID" minOccurs="0"/&gt;
 *         &lt;element name="ID" type="{http://sap.com/xi/AP/Common/GDT}OrganisationalCentreID" minOccurs="0"/&gt;
 *         &lt;element name="UUID" type="{http://sap.com/xi/AP/Common/GDT}UUID" minOccurs="0"/&gt;
 *         &lt;element name="ValidityPeriod" type="{http://sap.com/xi/AP/Common/GDT}CLOSED_DatePeriod" minOccurs="0"/&gt;
 *         &lt;element name="NameAndAddress" type="{http://sap.com/xi/AP/FO/MOM/Global}OrganisationalUnitQueryRepsonseNameAndAddress" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Functions" type="{http://sap.com/xi/AP/FO/MOM/Global}OrganisationalUnitQueryResponseFunctions" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Identification" type="{http://sap.com/xi/AP/FO/MOM/Global}OrganisationalUnitQueryResponseIdentification" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="EmployeeAssignment" type="{http://sap.com/xi/AP/FO/MOM/Global}OrganisationalUnitQueryResponseEmployeeAssignment" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="ParentOrganisationalUnitAssignment" type="{http://sap.com/xi/AP/FO/MOM/Global}OrganisationalUnitQueryResponseParentOrganisationalUnitAssignment" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="DistributionChannelAndDivision" type="{http://sap.com/xi/AP/FO/MOM/Global}OrganisationalUnitQueryResponseDistributionChannelAndDivision" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Company" type="{http://sap.com/xi/AP/FO/MOM/Global}OrganisationalUnitQueryResponseCompany" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Manager" type="{http://sap.com/xi/AP/FO/MOM/Global}OrganisationalUnitQueryResponseManager" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrganisationalUnitByElementsResponse_sync", namespace = "http://sap.com/xi/AP/FO/MOM/Global", propOrder = {
    "changeStateID",
    "id",
    "uuid",
    "validityPeriod",
    "nameAndAddress",
    "functions",
    "identification",
    "employeeAssignment",
    "parentOrganisationalUnitAssignment",
    "distributionChannelAndDivision",
    "company",
    "manager"
})
public class OrganisationalUnitByElementsResponseSync {

    @XmlElement(name = "ChangeStateID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String changeStateID;
    @XmlElement(name = "ID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String id;
    @XmlElement(name = "UUID")
    protected UUID uuid;
    @XmlElement(name = "ValidityPeriod")
    protected CLOSEDDatePeriod validityPeriod;
    @XmlElement(name = "NameAndAddress")
    protected List<OrganisationalUnitQueryRepsonseNameAndAddress> nameAndAddress;
    @XmlElement(name = "Functions")
    protected List<OrganisationalUnitQueryResponseFunctions> functions;
    @XmlElement(name = "Identification")
    protected List<OrganisationalUnitQueryResponseIdentification> identification;
    @XmlElement(name = "EmployeeAssignment")
    protected List<OrganisationalUnitQueryResponseEmployeeAssignment> employeeAssignment;
    @XmlElement(name = "ParentOrganisationalUnitAssignment")
    protected List<OrganisationalUnitQueryResponseParentOrganisationalUnitAssignment> parentOrganisationalUnitAssignment;
    @XmlElement(name = "DistributionChannelAndDivision")
    protected List<OrganisationalUnitQueryResponseDistributionChannelAndDivision> distributionChannelAndDivision;
    @XmlElement(name = "Company")
    protected List<OrganisationalUnitQueryResponseCompany> company;
    @XmlElement(name = "Manager")
    protected List<OrganisationalUnitQueryResponseManager> manager;

    /**
     * 获取changeStateID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChangeStateID() {
        return changeStateID;
    }

    /**
     * 设置changeStateID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChangeStateID(String value) {
        this.changeStateID = value;
    }

    /**
     * 获取id属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getID() {
        return id;
    }

    /**
     * 设置id属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setID(String value) {
        this.id = value;
    }

    /**
     * 获取uuid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UUID }
     *     
     */
    public UUID getUUID() {
        return uuid;
    }

    /**
     * 设置uuid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UUID }
     *     
     */
    public void setUUID(UUID value) {
        this.uuid = value;
    }

    /**
     * 获取validityPeriod属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CLOSEDDatePeriod }
     *     
     */
    public CLOSEDDatePeriod getValidityPeriod() {
        return validityPeriod;
    }

    /**
     * 设置validityPeriod属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CLOSEDDatePeriod }
     *     
     */
    public void setValidityPeriod(CLOSEDDatePeriod value) {
        this.validityPeriod = value;
    }

    /**
     * Gets the value of the nameAndAddress property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nameAndAddress property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNameAndAddress().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrganisationalUnitQueryRepsonseNameAndAddress }
     * 
     * 
     */
    public List<OrganisationalUnitQueryRepsonseNameAndAddress> getNameAndAddress() {
        if (nameAndAddress == null) {
            nameAndAddress = new ArrayList<OrganisationalUnitQueryRepsonseNameAndAddress>();
        }
        return this.nameAndAddress;
    }

    /**
     * Gets the value of the functions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the functions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFunctions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrganisationalUnitQueryResponseFunctions }
     * 
     * 
     */
    public List<OrganisationalUnitQueryResponseFunctions> getFunctions() {
        if (functions == null) {
            functions = new ArrayList<OrganisationalUnitQueryResponseFunctions>();
        }
        return this.functions;
    }

    /**
     * Gets the value of the identification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the identification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdentification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrganisationalUnitQueryResponseIdentification }
     * 
     * 
     */
    public List<OrganisationalUnitQueryResponseIdentification> getIdentification() {
        if (identification == null) {
            identification = new ArrayList<OrganisationalUnitQueryResponseIdentification>();
        }
        return this.identification;
    }

    /**
     * Gets the value of the employeeAssignment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the employeeAssignment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEmployeeAssignment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrganisationalUnitQueryResponseEmployeeAssignment }
     * 
     * 
     */
    public List<OrganisationalUnitQueryResponseEmployeeAssignment> getEmployeeAssignment() {
        if (employeeAssignment == null) {
            employeeAssignment = new ArrayList<OrganisationalUnitQueryResponseEmployeeAssignment>();
        }
        return this.employeeAssignment;
    }

    /**
     * Gets the value of the parentOrganisationalUnitAssignment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parentOrganisationalUnitAssignment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParentOrganisationalUnitAssignment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrganisationalUnitQueryResponseParentOrganisationalUnitAssignment }
     * 
     * 
     */
    public List<OrganisationalUnitQueryResponseParentOrganisationalUnitAssignment> getParentOrganisationalUnitAssignment() {
        if (parentOrganisationalUnitAssignment == null) {
            parentOrganisationalUnitAssignment = new ArrayList<OrganisationalUnitQueryResponseParentOrganisationalUnitAssignment>();
        }
        return this.parentOrganisationalUnitAssignment;
    }

    /**
     * Gets the value of the distributionChannelAndDivision property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the distributionChannelAndDivision property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDistributionChannelAndDivision().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrganisationalUnitQueryResponseDistributionChannelAndDivision }
     * 
     * 
     */
    public List<OrganisationalUnitQueryResponseDistributionChannelAndDivision> getDistributionChannelAndDivision() {
        if (distributionChannelAndDivision == null) {
            distributionChannelAndDivision = new ArrayList<OrganisationalUnitQueryResponseDistributionChannelAndDivision>();
        }
        return this.distributionChannelAndDivision;
    }

    /**
     * Gets the value of the company property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the company property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCompany().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrganisationalUnitQueryResponseCompany }
     * 
     * 
     */
    public List<OrganisationalUnitQueryResponseCompany> getCompany() {
        if (company == null) {
            company = new ArrayList<OrganisationalUnitQueryResponseCompany>();
        }
        return this.company;
    }

    /**
     * Gets the value of the manager property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the manager property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getManager().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrganisationalUnitQueryResponseManager }
     * 
     * 
     */
    public List<OrganisationalUnitQueryResponseManager> getManager() {
        if (manager == null) {
            manager = new ArrayList<OrganisationalUnitQueryResponseManager>();
        }
        return this.manager;
    }

}
