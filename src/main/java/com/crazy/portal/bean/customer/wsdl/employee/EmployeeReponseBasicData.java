
package com.crazy.portal.bean.customer.wsdl.employee;

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
 * <p>EmployeeReponseBasicData complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="EmployeeReponseBasicData"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ChangeStateID" type="{http://sap.com/xi/AP/Common/GDT}ChangeStateID" minOccurs="0"/&gt;
 *         &lt;element name="UUID" type="{http://sap.com/xi/AP/Common/GDT}UUID" minOccurs="0"/&gt;
 *         &lt;element name="EmployeeID" type="{http://sap.com/xi/AP/Common/GDT}EmployeeID" minOccurs="0"/&gt;
 *         &lt;element name="EmployeeType" type="{http://sap.com/xi/A1S/Global}EmployeeDataResponseEmployeeType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="EmployeeOrganisationalAssignment" type="{http://sap.com/xi/A1S/Global}EmployeeOrganisationalAssignment" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SystemAdministrativeData" type="{http://sap.com/xi/AP/Common/GDT}SystemAdministrativeData" minOccurs="0"/&gt;
 *         &lt;element name="BiographicalData" type="{http://sap.com/xi/A1S/Global}EmployeeDataReponseBiograhicalData" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="WorkplaceAddressInformation" type="{http://sap.com/xi/A1S/Global}EmployeeReponseEmployeeWorkplaceAddressInformation" minOccurs="0"/&gt;
 *         &lt;element name="JobAssignment" type="{http://sap.com/xi/A1S/Global}EmployeeDataResponseJobAssignment" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmployeeReponseBasicData", propOrder = {
    "changeStateID",
    "uuid",
    "employeeID",
    "employeeType",
    "employeeOrganisationalAssignment",
    "systemAdministrativeData",
    "biographicalData",
    "workplaceAddressInformation",
    "jobAssignment"
})
public class EmployeeReponseBasicData {

    @XmlElement(name = "ChangeStateID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String changeStateID;
    @XmlElement(name = "UUID")
    protected UUID uuid;
    @XmlElement(name = "EmployeeID")
    protected EmployeeID employeeID;
    @XmlElement(name = "EmployeeType")
    protected List<EmployeeDataResponseEmployeeType> employeeType;
    @XmlElement(name = "EmployeeOrganisationalAssignment")
    protected List<EmployeeOrganisationalAssignment> employeeOrganisationalAssignment;
    @XmlElement(name = "SystemAdministrativeData")
    protected SystemAdministrativeData systemAdministrativeData;
    @XmlElement(name = "BiographicalData")
    protected List<EmployeeDataReponseBiograhicalData> biographicalData;
    @XmlElement(name = "WorkplaceAddressInformation")
    protected EmployeeReponseEmployeeWorkplaceAddressInformation workplaceAddressInformation;
    @XmlElement(name = "JobAssignment")
    protected List<EmployeeDataResponseJobAssignment> jobAssignment;

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
     * 获取employeeID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EmployeeID }
     *     
     */
    public EmployeeID getEmployeeID() {
        return employeeID;
    }

    /**
     * 设置employeeID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeID }
     *     
     */
    public void setEmployeeID(EmployeeID value) {
        this.employeeID = value;
    }

    /**
     * Gets the value of the employeeType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the employeeType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEmployeeType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EmployeeDataResponseEmployeeType }
     * 
     * 
     */
    public List<EmployeeDataResponseEmployeeType> getEmployeeType() {
        if (employeeType == null) {
            employeeType = new ArrayList<EmployeeDataResponseEmployeeType>();
        }
        return this.employeeType;
    }

    /**
     * Gets the value of the employeeOrganisationalAssignment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the employeeOrganisationalAssignment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEmployeeOrganisationalAssignment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EmployeeOrganisationalAssignment }
     * 
     * 
     */
    public List<EmployeeOrganisationalAssignment> getEmployeeOrganisationalAssignment() {
        if (employeeOrganisationalAssignment == null) {
            employeeOrganisationalAssignment = new ArrayList<EmployeeOrganisationalAssignment>();
        }
        return this.employeeOrganisationalAssignment;
    }

    /**
     * 获取systemAdministrativeData属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SystemAdministrativeData }
     *     
     */
    public SystemAdministrativeData getSystemAdministrativeData() {
        return systemAdministrativeData;
    }

    /**
     * 设置systemAdministrativeData属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SystemAdministrativeData }
     *     
     */
    public void setSystemAdministrativeData(SystemAdministrativeData value) {
        this.systemAdministrativeData = value;
    }

    /**
     * Gets the value of the biographicalData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the biographicalData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBiographicalData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EmployeeDataReponseBiograhicalData }
     * 
     * 
     */
    public List<EmployeeDataReponseBiograhicalData> getBiographicalData() {
        if (biographicalData == null) {
            biographicalData = new ArrayList<EmployeeDataReponseBiograhicalData>();
        }
        return this.biographicalData;
    }

    /**
     * 获取workplaceAddressInformation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link EmployeeReponseEmployeeWorkplaceAddressInformation }
     *     
     */
    public EmployeeReponseEmployeeWorkplaceAddressInformation getWorkplaceAddressInformation() {
        return workplaceAddressInformation;
    }

    /**
     * 设置workplaceAddressInformation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeReponseEmployeeWorkplaceAddressInformation }
     *     
     */
    public void setWorkplaceAddressInformation(EmployeeReponseEmployeeWorkplaceAddressInformation value) {
        this.workplaceAddressInformation = value;
    }

    /**
     * Gets the value of the jobAssignment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the jobAssignment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getJobAssignment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EmployeeDataResponseJobAssignment }
     * 
     * 
     */
    public List<EmployeeDataResponseJobAssignment> getJobAssignment() {
        if (jobAssignment == null) {
            jobAssignment = new ArrayList<EmployeeDataResponseJobAssignment>();
        }
        return this.jobAssignment;
    }

}
