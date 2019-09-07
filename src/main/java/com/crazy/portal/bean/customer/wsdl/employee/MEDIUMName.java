
package com.crazy.portal.bean.customer.wsdl.employee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:RepresentationTerm xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:3.0" xmlns="http://sap.com/xi/AP/Common/GDT" xmlns:http="http://schemas.xmlsoap.org/wsdl/http/" xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/" xmlns:n1="http://sap.com/xi/SAPGlobal20/Global" xmlns:n2="http://sap.com/xi/AP/Common/Global" xmlns:n3="http://sap.com/xi/AP/Common/GDT" xmlns:n4="http://sap.com/xi/BASIS/Global" xmlns:n5="http://sap.com/xi/AP/PDI/ABSL" xmlns:n6="http://sap.com/xi/Common/DataTypes" xmlns:rfc="urn:sap-com:sap:rfc:functions" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://sap.com/xi/A1S/Global" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:wsoap12="http://schemas.xmlsoap.org/wsdl/soap12/" xmlns:wsp="http://schemas.xmlsoap.org/ws/2004/09/policy" xmlns:wsu="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd" xmlns:xi10="http://sap.com/xi/BASIS/Global" xmlns:xi13="http://sap.com/xi/AP/PDI/ABSL" xmlns:xi14="http://sap.com/xi/Common/DataTypes" xmlns:xi5="http://sap.com/xi/SAPGlobal20/Global" xmlns:xi6="http://sap.com/xi/AP/Common/Global" xmlns:xi7="http://sap.com/xi/A1S/Global" xmlns:xi8="http://sap.com/xi/AP/Common/GDT" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Name&lt;/ccts:RepresentationTerm&gt;
 * </pre>
 * 
 * 
 * <p>MEDIUM_Name complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="MEDIUM_Name"&gt;
 *   &lt;simpleContent&gt;
 *     &lt;extension base="&lt;http://sap.com/xi/AP/Common/GDT&gt;MEDIUM_Name.Content"&gt;
 *       &lt;attribute name="languageCode" type="{http://sap.com/xi/BASIS/Global}LanguageCode" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/simpleContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MEDIUM_Name", namespace = "http://sap.com/xi/AP/Common/GDT", propOrder = {
    "value"
})
public class MEDIUMName {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "languageCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String languageCode;

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
     * 获取languageCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguageCode() {
        return languageCode;
    }

    /**
     * 设置languageCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguageCode(String value) {
        this.languageCode = value;
    }

}
