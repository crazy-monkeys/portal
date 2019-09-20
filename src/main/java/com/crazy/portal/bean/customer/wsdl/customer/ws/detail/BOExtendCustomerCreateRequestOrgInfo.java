
package com.crazy.portal.bean.customer.wsdl.customer.ws.detail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>BO_ExtendCustomerCreateRequestOrgInfo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="BO_ExtendCustomerCreateRequestOrgInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Z_DEPT_GRADE" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_SHORT_Description" minOccurs="0"/&gt;
 *         &lt;element name="Z_SUPERIOR_DEP" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_SHORT_Description" minOccurs="0"/&gt;
 *         &lt;element name="Z_DEPT_NAME" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_SHORT_Description" minOccurs="0"/&gt;
 *         &lt;element name="Z_DEPT_MANAGER" type="{http://sap.com/xi/AP/Common/GDT}LANGUAGEINDEPENDENT_SHORT_Description" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BO_ExtendCustomerCreateRequestOrgInfo", propOrder = {
    "zdeptgrade",
    "zsuperiordep",
    "zdeptname",
    "zdeptmanager"
})
public class BOExtendCustomerCreateRequestOrgInfo {

    @XmlElement(name = "Z_DEPT_GRADE")
    protected String zdeptgrade;
    @XmlElement(name = "Z_SUPERIOR_DEP")
    protected String zsuperiordep;
    @XmlElement(name = "Z_DEPT_NAME")
    protected String zdeptname;
    @XmlElement(name = "Z_DEPT_MANAGER")
    protected String zdeptmanager;

    /**
     * 获取zdeptgrade属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZDEPTGRADE() {
        return zdeptgrade;
    }

    /**
     * 设置zdeptgrade属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZDEPTGRADE(String value) {
        this.zdeptgrade = value;
    }

    /**
     * 获取zsuperiordep属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZSUPERIORDEP() {
        return zsuperiordep;
    }

    /**
     * 设置zsuperiordep属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZSUPERIORDEP(String value) {
        this.zsuperiordep = value;
    }

    /**
     * 获取zdeptname属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZDEPTNAME() {
        return zdeptname;
    }

    /**
     * 设置zdeptname属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZDEPTNAME(String value) {
        this.zdeptname = value;
    }

    /**
     * 获取zdeptmanager属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZDEPTMANAGER() {
        return zdeptmanager;
    }

    /**
     * 设置zdeptmanager属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZDEPTMANAGER(String value) {
        this.zdeptmanager = value;
    }

}
