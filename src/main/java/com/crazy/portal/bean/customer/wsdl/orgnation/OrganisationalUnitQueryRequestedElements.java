
package com.crazy.portal.bean.customer.wsdl.orgnation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>OrganisationalUnitQueryRequestedElements complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="OrganisationalUnitQueryRequestedElements"&gt;
 *   &lt;simpleContent&gt;
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
 *       &lt;attribute name="nameAndAddressTransmissionRequestCode" type="{http://sap.com/xi/AP/Common/GDT}TransmissionRequestCode" /&gt;
 *       &lt;attribute name="functionsTransmissionRequestCode" type="{http://sap.com/xi/AP/Common/GDT}TransmissionRequestCode" /&gt;
 *       &lt;attribute name="identificationTransmissionRequestCode" type="{http://sap.com/xi/AP/Common/GDT}TransmissionRequestCode" /&gt;
 *       &lt;attribute name="employeeAssignmentTransmissionRequestCode" type="{http://sap.com/xi/AP/Common/GDT}TransmissionRequestCode" /&gt;
 *       &lt;attribute name="parentOrganisationalUnitAssignmentTransmissionRequestCode" type="{http://sap.com/xi/AP/Common/GDT}TransmissionRequestCode" /&gt;
 *       &lt;attribute name="distributionChannelAndDivisionTransmissionRequestCode" type="{http://sap.com/xi/AP/Common/GDT}TransmissionRequestCode" /&gt;
 *       &lt;attribute name="companyTransmissionRequestCode" type="{http://sap.com/xi/AP/Common/GDT}TransmissionRequestCode" /&gt;
 *       &lt;attribute name="managerTransmissionRequestCode" type="{http://sap.com/xi/AP/Common/GDT}TransmissionRequestCode" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/simpleContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrganisationalUnitQueryRequestedElements", namespace = "http://sap.com/xi/AP/FO/MOM/Global", propOrder = {
    "value"
})
public class OrganisationalUnitQueryRequestedElements {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "nameAndAddressTransmissionRequestCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String nameAndAddressTransmissionRequestCode;
    @XmlAttribute(name = "functionsTransmissionRequestCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String functionsTransmissionRequestCode;
    @XmlAttribute(name = "identificationTransmissionRequestCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String identificationTransmissionRequestCode;
    @XmlAttribute(name = "employeeAssignmentTransmissionRequestCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String employeeAssignmentTransmissionRequestCode;
    @XmlAttribute(name = "parentOrganisationalUnitAssignmentTransmissionRequestCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String parentOrganisationalUnitAssignmentTransmissionRequestCode;
    @XmlAttribute(name = "distributionChannelAndDivisionTransmissionRequestCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String distributionChannelAndDivisionTransmissionRequestCode;
    @XmlAttribute(name = "companyTransmissionRequestCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String companyTransmissionRequestCode;
    @XmlAttribute(name = "managerTransmissionRequestCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String managerTransmissionRequestCode;

    /**
     * 获取value属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置value属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取nameAndAddressTransmissionRequestCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameAndAddressTransmissionRequestCode() {
        return nameAndAddressTransmissionRequestCode;
    }

    /**
     * 设置nameAndAddressTransmissionRequestCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameAndAddressTransmissionRequestCode(String value) {
        this.nameAndAddressTransmissionRequestCode = value;
    }

    /**
     * 获取functionsTransmissionRequestCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFunctionsTransmissionRequestCode() {
        return functionsTransmissionRequestCode;
    }

    /**
     * 设置functionsTransmissionRequestCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFunctionsTransmissionRequestCode(String value) {
        this.functionsTransmissionRequestCode = value;
    }

    /**
     * 获取identificationTransmissionRequestCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificationTransmissionRequestCode() {
        return identificationTransmissionRequestCode;
    }

    /**
     * 设置identificationTransmissionRequestCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificationTransmissionRequestCode(String value) {
        this.identificationTransmissionRequestCode = value;
    }

    /**
     * 获取employeeAssignmentTransmissionRequestCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmployeeAssignmentTransmissionRequestCode() {
        return employeeAssignmentTransmissionRequestCode;
    }

    /**
     * 设置employeeAssignmentTransmissionRequestCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmployeeAssignmentTransmissionRequestCode(String value) {
        this.employeeAssignmentTransmissionRequestCode = value;
    }

    /**
     * 获取parentOrganisationalUnitAssignmentTransmissionRequestCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentOrganisationalUnitAssignmentTransmissionRequestCode() {
        return parentOrganisationalUnitAssignmentTransmissionRequestCode;
    }

    /**
     * 设置parentOrganisationalUnitAssignmentTransmissionRequestCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentOrganisationalUnitAssignmentTransmissionRequestCode(String value) {
        this.parentOrganisationalUnitAssignmentTransmissionRequestCode = value;
    }

    /**
     * 获取distributionChannelAndDivisionTransmissionRequestCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistributionChannelAndDivisionTransmissionRequestCode() {
        return distributionChannelAndDivisionTransmissionRequestCode;
    }

    /**
     * 设置distributionChannelAndDivisionTransmissionRequestCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistributionChannelAndDivisionTransmissionRequestCode(String value) {
        this.distributionChannelAndDivisionTransmissionRequestCode = value;
    }

    /**
     * 获取companyTransmissionRequestCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyTransmissionRequestCode() {
        return companyTransmissionRequestCode;
    }

    /**
     * 设置companyTransmissionRequestCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyTransmissionRequestCode(String value) {
        this.companyTransmissionRequestCode = value;
    }

    /**
     * 获取managerTransmissionRequestCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManagerTransmissionRequestCode() {
        return managerTransmissionRequestCode;
    }

    /**
     * 设置managerTransmissionRequestCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManagerTransmissionRequestCode(String value) {
        this.managerTransmissionRequestCode = value;
    }

}
