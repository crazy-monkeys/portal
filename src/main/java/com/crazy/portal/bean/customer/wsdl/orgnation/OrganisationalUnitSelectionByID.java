
package com.crazy.portal.bean.customer.wsdl.orgnation;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>OrganisationalUnitSelectionByID complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="OrganisationalUnitSelectionByID"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Identifier" type="{http://sap.com/xi/AP/Common/Global}SelectionByIdentifier" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="ValidityPeriodStartDate" type="{http://sap.com/xi/AP/Common/Global}SelectionByDate" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="ValidityPeriodEndDate" type="{http://sap.com/xi/AP/Common/Global}SelectionByDate" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrganisationalUnitSelectionByID", namespace = "http://sap.com/xi/A1S/Global", propOrder = {
    "identifier",
    "validityPeriodStartDate",
    "validityPeriodEndDate"
})
public class OrganisationalUnitSelectionByID {

    @XmlElement(name = "Identifier")
    protected List<SelectionByIdentifier> identifier;
    @XmlElement(name = "ValidityPeriodStartDate")
    protected List<SelectionByDate> validityPeriodStartDate;
    @XmlElement(name = "ValidityPeriodEndDate")
    protected List<SelectionByDate> validityPeriodEndDate;

    /**
     * Gets the value of the identifier property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the identifier property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdentifier().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByIdentifier }
     * 
     * 
     */
    public List<SelectionByIdentifier> getIdentifier() {
        if (identifier == null) {
            identifier = new ArrayList<SelectionByIdentifier>();
        }
        return this.identifier;
    }

    /**
     * Gets the value of the validityPeriodStartDate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the validityPeriodStartDate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValidityPeriodStartDate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByDate }
     * 
     * 
     */
    public List<SelectionByDate> getValidityPeriodStartDate() {
        if (validityPeriodStartDate == null) {
            validityPeriodStartDate = new ArrayList<SelectionByDate>();
        }
        return this.validityPeriodStartDate;
    }

    /**
     * Gets the value of the validityPeriodEndDate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the validityPeriodEndDate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValidityPeriodEndDate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectionByDate }
     * 
     * 
     */
    public List<SelectionByDate> getValidityPeriodEndDate() {
        if (validityPeriodEndDate == null) {
            validityPeriodEndDate = new ArrayList<SelectionByDate>();
        }
        return this.validityPeriodEndDate;
    }

}
