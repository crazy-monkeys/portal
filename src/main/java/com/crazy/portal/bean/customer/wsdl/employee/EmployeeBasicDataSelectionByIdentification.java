
package com.crazy.portal.bean.customer.wsdl.employee;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>EmployeeBasicDataSelectionByIdentification complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="EmployeeBasicDataSelectionByIdentification"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SelectionByEmployeeID" type="{http://sap.com/xi/A1S/Global}EmployeeSelectionByEmployeeID" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SelectionByEmployeeFamilyName" type="{http://sap.com/xi/A1S/Global}EmployeeSelectionByEmployeeFamilyName" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SelectionByEmployeeGivenName" type="{http://sap.com/xi/A1S/Global}EmployeeSelectionByEmployeeGivenName" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SelectionByCreatedSinceDate" type="{http://sap.com/xi/A1S/Global}EmployeeSelectionByCreatedSinceDate" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SelectionByCreationIdentityID" type="{http://sap.com/xi/A1S/Global}EmployeeSelectionByCreationIdentityID" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SelectionByChangedSinceDate" type="{http://sap.com/xi/A1S/Global}EmployeeSelectionByChangedSinceDate" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SelectionByLastChangeIdentityID" type="{http://sap.com/xi/A1S/Global}EmployeeSelectionByLastChangeIdentityID" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SelectionBySearchText" type="{http://sap.com/xi/AP/Common/GDT}SearchText" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmployeeBasicDataSelectionByIdentification", propOrder = {
    "selectionByEmployeeID",
    "selectionByEmployeeFamilyName",
    "selectionByEmployeeGivenName",
    "selectionByCreatedSinceDate",
    "selectionByCreationIdentityID",
    "selectionByChangedSinceDate",
    "selectionByLastChangeIdentityID",
    "selectionBySearchText"
})


public class EmployeeBasicDataSelectionByIdentification {

    @XmlElement(name = "SelectionByEmployeeID")
    protected List<EmployeeSelectionByEmployeeID> selectionByEmployeeID;
    @XmlElement(name = "SelectionByEmployeeFamilyName")
    protected List<EmployeeSelectionByEmployeeFamilyName> selectionByEmployeeFamilyName;
    @XmlElement(name = "SelectionByEmployeeGivenName")
    protected List<EmployeeSelectionByEmployeeGivenName> selectionByEmployeeGivenName;
    @XmlElement(name = "SelectionByCreatedSinceDate")
    protected List<EmployeeSelectionByCreatedSinceDate> selectionByCreatedSinceDate;
    @XmlElement(name = "SelectionByCreationIdentityID")
    protected List<EmployeeSelectionByCreationIdentityID> selectionByCreationIdentityID;
    @XmlElement(name = "SelectionByChangedSinceDate")
    protected List<EmployeeSelectionByChangedSinceDate> selectionByChangedSinceDate;
    @XmlElement(name = "SelectionByLastChangeIdentityID")
    protected List<EmployeeSelectionByLastChangeIdentityID> selectionByLastChangeIdentityID;
    @XmlElement(name = "SelectionBySearchText")
    protected String selectionBySearchText;

    /**
     * Gets the value of the selectionByEmployeeID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByEmployeeID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByEmployeeID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EmployeeSelectionByEmployeeID }
     * 
     * 
     */
    public List<EmployeeSelectionByEmployeeID> getSelectionByEmployeeID() {
        if (selectionByEmployeeID == null) {
            selectionByEmployeeID = new ArrayList<EmployeeSelectionByEmployeeID>();
        }
        return this.selectionByEmployeeID;
    }

    /**
     * Gets the value of the selectionByEmployeeFamilyName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByEmployeeFamilyName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByEmployeeFamilyName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EmployeeSelectionByEmployeeFamilyName }
     * 
     * 
     */
    public List<EmployeeSelectionByEmployeeFamilyName> getSelectionByEmployeeFamilyName() {
        if (selectionByEmployeeFamilyName == null) {
            selectionByEmployeeFamilyName = new ArrayList<EmployeeSelectionByEmployeeFamilyName>();
        }
        return this.selectionByEmployeeFamilyName;
    }

    /**
     * Gets the value of the selectionByEmployeeGivenName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByEmployeeGivenName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByEmployeeGivenName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EmployeeSelectionByEmployeeGivenName }
     * 
     * 
     */
    public List<EmployeeSelectionByEmployeeGivenName> getSelectionByEmployeeGivenName() {
        if (selectionByEmployeeGivenName == null) {
            selectionByEmployeeGivenName = new ArrayList<EmployeeSelectionByEmployeeGivenName>();
        }
        return this.selectionByEmployeeGivenName;
    }

    /**
     * Gets the value of the selectionByCreatedSinceDate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByCreatedSinceDate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByCreatedSinceDate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EmployeeSelectionByCreatedSinceDate }
     * 
     * 
     */
    public List<EmployeeSelectionByCreatedSinceDate> getSelectionByCreatedSinceDate() {
        if (selectionByCreatedSinceDate == null) {
            selectionByCreatedSinceDate = new ArrayList<EmployeeSelectionByCreatedSinceDate>();
        }
        return this.selectionByCreatedSinceDate;
    }

    /**
     * Gets the value of the selectionByCreationIdentityID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByCreationIdentityID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByCreationIdentityID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EmployeeSelectionByCreationIdentityID }
     * 
     * 
     */
    public List<EmployeeSelectionByCreationIdentityID> getSelectionByCreationIdentityID() {
        if (selectionByCreationIdentityID == null) {
            selectionByCreationIdentityID = new ArrayList<EmployeeSelectionByCreationIdentityID>();
        }
        return this.selectionByCreationIdentityID;
    }

    /**
     * Gets the value of the selectionByChangedSinceDate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByChangedSinceDate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByChangedSinceDate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EmployeeSelectionByChangedSinceDate }
     * 
     * 
     */
    public List<EmployeeSelectionByChangedSinceDate> getSelectionByChangedSinceDate() {
        if (selectionByChangedSinceDate == null) {
            selectionByChangedSinceDate = new ArrayList<EmployeeSelectionByChangedSinceDate>();
        }
        return this.selectionByChangedSinceDate;
    }

    /**
     * Gets the value of the selectionByLastChangeIdentityID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByLastChangeIdentityID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByLastChangeIdentityID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EmployeeSelectionByLastChangeIdentityID }
     * 
     * 
     */
    public List<EmployeeSelectionByLastChangeIdentityID> getSelectionByLastChangeIdentityID() {
        if (selectionByLastChangeIdentityID == null) {
            selectionByLastChangeIdentityID = new ArrayList<EmployeeSelectionByLastChangeIdentityID>();
        }
        return this.selectionByLastChangeIdentityID;
    }

    /**
     * 获取selectionBySearchText属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelectionBySearchText() {
        return selectionBySearchText;
    }

    /**
     * 设置selectionBySearchText属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelectionBySearchText(String value) {
        this.selectionBySearchText = value;
    }

    public void setSelectionByEmployeeID(List<EmployeeSelectionByEmployeeID> selectionByEmployeeID) {
        this.selectionByEmployeeID = selectionByEmployeeID;
    }
}
