
package npcxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;


/**
 * <p>ZpricessimulateHeaderOut complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ZpricessimulateHeaderOut"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Grossvalue" type="{urn:sap-com:document:sap:rfc:functions}decimal23.4"/&gt;
 *         &lt;element name="Netvalue" type="{urn:sap-com:document:sap:rfc:functions}decimal23.4"/&gt;
 *         &lt;element name="Resulttype" type="{urn:sap-com:document:sap:rfc:functions}char1"/&gt;
 *         &lt;element name="Resultmessage" type="{urn:sap-com:document:sap:rfc:functions}char255"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZpricessimulateHeaderOut", propOrder = {
    "grossvalue",
    "netvalue",
    "resulttype",
    "resultmessage"
})
public class ZpricessimulateHeaderOut {

    @XmlElement(name = "Grossvalue", required = true)
    protected BigDecimal grossvalue;
    @XmlElement(name = "Netvalue", required = true)
    protected BigDecimal netvalue;
    @XmlElement(name = "Resulttype", required = true)
    protected String resulttype;
    @XmlElement(name = "Resultmessage", required = true)
    protected String resultmessage;

    /**
     * 获取grossvalue属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getGrossvalue() {
        return grossvalue;
    }

    /**
     * 设置grossvalue属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setGrossvalue(BigDecimal value) {
        this.grossvalue = value;
    }

    /**
     * 获取netvalue属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNetvalue() {
        return netvalue;
    }

    /**
     * 设置netvalue属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNetvalue(BigDecimal value) {
        this.netvalue = value;
    }

    /**
     * 获取resulttype属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResulttype() {
        return resulttype;
    }

    /**
     * 设置resulttype属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResulttype(String value) {
        this.resulttype = value;
    }

    /**
     * 获取resultmessage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultmessage() {
        return resultmessage;
    }

    /**
     * 设置resultmessage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultmessage(String value) {
        this.resultmessage = value;
    }

}
