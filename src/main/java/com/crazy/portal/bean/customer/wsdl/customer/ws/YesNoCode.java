
package com.crazy.portal.bean.customer.wsdl.customer.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>YesNoCode的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="YesNoCode"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token"&gt;
 *     &lt;enumeration value="Y"/&gt;
 *     &lt;enumeration value="N"/&gt;
 *     &lt;maxLength value="10"/&gt;
 *     &lt;minLength value="1"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "YesNoCode", namespace = "http://0003111061-one-off.sap.com/Y4R4GVT9Y_")
@XmlEnum
public enum YesNoCode {

    Y,
    N;

    public String value() {
        return name();
    }

    public static YesNoCode fromValue(String v) {
        return valueOf(v);
    }

}
