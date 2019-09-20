
package com.crazy.portal.bean.customer.wsdl.customer.ws.detail;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>BO_ExtendCustomerQuery_ExtendCustomerInfoSimpleByRequest complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="BO_ExtendCustomerQuery_ExtendCustomerInfoSimpleByRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SelectionByCustomerID" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerQuery_ExtendCustomerInfoSimpleByRequestSelectionByCustomerID" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SelectionBySAP_ToCustomer" type="{http://0003111061-one-off.sap.com/Y4R4GVT9Y_}BO_ExtendCustomerQuery_ExtendCustomerInfoSimpleByRequestSelectionBySAP_ToCustomer" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BO_ExtendCustomerQuery_ExtendCustomerInfoSimpleByRequest", propOrder = {
    "selectionByCustomerID",
    "selectionBySAPToCustomer"
})
public class BOExtendCustomerQueryExtendCustomerInfoSimpleByRequest {

    @XmlElement(name = "SelectionByCustomerID")
    protected List<BOExtendCustomerQueryExtendCustomerInfoSimpleByRequestSelectionByCustomerID> selectionByCustomerID;
    @XmlElement(name = "SelectionBySAP_ToCustomer")
    protected List<BOExtendCustomerQueryExtendCustomerInfoSimpleByRequestSelectionBySAPToCustomer> selectionBySAPToCustomer;

    /**
     * Gets the value of the selectionByCustomerID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionByCustomerID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionByCustomerID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BOExtendCustomerQueryExtendCustomerInfoSimpleByRequestSelectionByCustomerID }
     * 
     * 
     */
    public List<BOExtendCustomerQueryExtendCustomerInfoSimpleByRequestSelectionByCustomerID> getSelectionByCustomerID() {
        if (selectionByCustomerID == null) {
            selectionByCustomerID = new ArrayList<BOExtendCustomerQueryExtendCustomerInfoSimpleByRequestSelectionByCustomerID>();
        }
        return this.selectionByCustomerID;
    }

    /**
     * Gets the value of the selectionBySAPToCustomer property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectionBySAPToCustomer property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectionBySAPToCustomer().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BOExtendCustomerQueryExtendCustomerInfoSimpleByRequestSelectionBySAPToCustomer }
     * 
     * 
     */
    public List<BOExtendCustomerQueryExtendCustomerInfoSimpleByRequestSelectionBySAPToCustomer> getSelectionBySAPToCustomer() {
        if (selectionBySAPToCustomer == null) {
            selectionBySAPToCustomer = new ArrayList<BOExtendCustomerQueryExtendCustomerInfoSimpleByRequestSelectionBySAPToCustomer>();
        }
        return this.selectionBySAPToCustomer;
    }

}
