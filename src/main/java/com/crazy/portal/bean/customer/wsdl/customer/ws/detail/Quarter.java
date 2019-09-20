
package com.crazy.portal.bean.customer.wsdl.customer.ws.detail;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Quarter的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="Quarter"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token"&gt;
 *     &lt;enumeration value="--Q1"/&gt;
 *     &lt;enumeration value="--Q2"/&gt;
 *     &lt;enumeration value="--Q3"/&gt;
 *     &lt;enumeration value="--Q4"/&gt;
 *     &lt;maxLength value="4"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "Quarter", namespace = "http://sap.com/xi/Common/DataTypes")
@XmlEnum
public enum Quarter {

    @XmlEnumValue("--Q1")
    Q_1("--Q1"),
    @XmlEnumValue("--Q2")
    Q_2("--Q2"),
    @XmlEnumValue("--Q3")
    Q_3("--Q3"),
    @XmlEnumValue("--Q4")
    Q_4("--Q4");
    private final String value;

    Quarter(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Quarter fromValue(String v) {
        for (Quarter c: Quarter.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
