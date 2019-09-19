
package com.crazy.portal.bean.customer.wsdl.customer.ws;

import java.math.BigDecimal;
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
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;ccts:RepresentationTerm xmlns:ccts="urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:3.0" xmlns="http://sap.com/xi/AP/Common/GDT" xmlns:http="http://schemas.xmlsoap.org/wsdl/http/" xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/" xmlns:n1="http://sap.com/xi/AP/Globalization" xmlns:n10="http://sap.com/xi/AP/PDI/ABSL" xmlns:n2="http://0003111061-one-off.sap.com/Y4R4GVT9Y_" xmlns:n3="http://sap.com/xi/SAPGlobal20/Global" xmlns:n4="http://sap.com/xi/AP/Common/Global" xmlns:n5="http://sap.com/xi/AP/Common/GDT" xmlns:n6="http://sap.com/xi/AP/FinancialAccounting/Global" xmlns:n7="http://sap.com/xi/BASIS/Global" xmlns:n8="http://sap.com/xi/DocumentServices/Global" xmlns:n9="http://sap.com/xi/Common/DataTypes" xmlns:rfc="urn:sap-com:sap:rfc:functions" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tns="http://sap.com/xi/A1S/Global" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:wsoap12="http://schemas.xmlsoap.org/wsdl/soap12/" xmlns:wsp="http://schemas.xmlsoap.org/ws/2004/09/policy" xmlns:wsu="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd" xmlns:xi0="http://sap.com/xi/AP/Globalization" xmlns:xi39="http://0003111061-one-off.sap.com/Y4R4GVT9Y_" xmlns:xi40="http://sap.com/xi/SAPGlobal20/Global" xmlns:xi41="http://sap.com/xi/AP/Common/Global" xmlns:xi42="http://sap.com/xi/A1S/Global" xmlns:xi43="http://sap.com/xi/AP/Common/GDT" xmlns:xi45="http://sap.com/xi/AP/FinancialAccounting/Global" xmlns:xi46="http://sap.com/xi/BASIS/Global" xmlns:xi47="http://sap.com/xi/DocumentServices/Global" xmlns:xi49="http://sap.com/xi/Common/DataTypes" xmlns:xi51="http://sap.com/xi/AP/PDI/ABSL" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;Measure&lt;/ccts:RepresentationTerm&gt;
 * </pre>
 * 
 * 
 * <p>Measure complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Measure"&gt;
 *   &lt;simpleContent&gt;
 *     &lt;extension base="&lt;http://sap.com/xi/AP/Common/GDT&gt;Measure.Content"&gt;
 *       &lt;attribute name="unitCode" use="required" type="{http://sap.com/xi/AP/Common/GDT}MeasureUnitCode" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/simpleContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Measure", propOrder = {
    "value"
})
public class Measure {

    @XmlValue
    protected BigDecimal value;
    @XmlAttribute(name = "unitCode", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String unitCode;

    /**
     * 获取value属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * 设置value属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    /**
     * 获取unitCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitCode() {
        return unitCode;
    }

    /**
     * 设置unitCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitCode(String value) {
        this.unitCode = value;
    }

}
