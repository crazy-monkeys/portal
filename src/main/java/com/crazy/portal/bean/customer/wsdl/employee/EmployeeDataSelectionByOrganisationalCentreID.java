
package com.crazy.portal.bean.customer.wsdl.employee;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>EmployeeDataSelectionByOrganisationalCentreID complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="EmployeeDataSelectionByOrganisationalCentreID"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SelectionByOrgCentreID" type="{http://sap.com/xi/A1S/Global}EmployeeSelectionByOrganisationalCentreID" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmployeeDataSelectionByOrganisationalCentreID", propOrder = {
    "selectionByOrgCentreID"
})
public class EmployeeDataSelectionByOrganisationalCentreID {

    @XmlElement(name = "SelectionByOrgCentreID")
    protected List<EmployeeSelectionByOrganisationalCentreID> selectionByOrgCentreID;

    /**
     * Gets the value of the selectionByOrgCentreID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByOrgCentreID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByOrgCentreID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EmployeeSelectionByOrganisationalCentreID }
     * 
     * 
     */
    public List<EmployeeSelectionByOrganisationalCentreID> getSelectionByOrgCentreID() {
        if (selectionByOrgCentreID == null) {
            selectionByOrgCentreID = new ArrayList<EmployeeSelectionByOrganisationalCentreID>();
        }
        return this.selectionByOrgCentreID;
    }

}
