
package npcxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ZpricessimulateHeaderIn complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ZpricessimulateHeaderIn"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Portalorderid" type="{urn:sap-com:document:sap:rfc:functions}char40"/&gt;
 *         &lt;element name="Ordertype" type="{urn:sap-com:document:sap:rfc:functions}char4"/&gt;
 *         &lt;element name="Salesorg" type="{urn:sap-com:document:sap:rfc:functions}char4"/&gt;
 *         &lt;element name="Channel" type="{urn:sap-com:document:sap:rfc:functions}char2"/&gt;
 *         &lt;element name="Division" type="{urn:sap-com:document:sap:rfc:functions}char2"/&gt;
 *         &lt;element name="Salesoffice" type="{urn:sap-com:document:sap:rfc:functions}char4"/&gt;
 *         &lt;element name="Salesgroup" type="{urn:sap-com:document:sap:rfc:functions}char3"/&gt;
 *         &lt;element name="Soldto" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
 *         &lt;element name="Sendto" type="{urn:sap-com:document:sap:rfc:functions}char10"/&gt;
 *         &lt;element name="Pricedate" type="{urn:sap-com:document:sap:rfc:functions}char8"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZpricessimulateHeaderIn", propOrder = {
    "portalorderid",
    "ordertype",
    "salesorg",
    "channel",
    "division",
    "salesoffice",
    "salesgroup",
    "soldto",
    "sendto",
    "pricedate"
})
public class ZpricessimulateHeaderIn {

    @XmlElement(name = "Portalorderid", required = true)
    protected String portalorderid;
    @XmlElement(name = "Ordertype", required = true)
    protected String ordertype;
    @XmlElement(name = "Salesorg", required = true)
    protected String salesorg;
    @XmlElement(name = "Channel", required = true)
    protected String channel;
    @XmlElement(name = "Division", required = true)
    protected String division;
    @XmlElement(name = "Salesoffice", required = true)
    protected String salesoffice;
    @XmlElement(name = "Salesgroup", required = true)
    protected String salesgroup;
    @XmlElement(name = "Soldto", required = true)
    protected String soldto;
    @XmlElement(name = "Sendto", required = true)
    protected String sendto;
    @XmlElement(name = "Pricedate", required = true)
    protected String pricedate;

    /**
     * 获取portalorderid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortalorderid() {
        return portalorderid;
    }

    /**
     * 设置portalorderid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortalorderid(String value) {
        this.portalorderid = value;
    }

    /**
     * 获取ordertype属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrdertype() {
        return ordertype;
    }

    /**
     * 设置ordertype属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrdertype(String value) {
        this.ordertype = value;
    }

    /**
     * 获取salesorg属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesorg() {
        return salesorg;
    }

    /**
     * 设置salesorg属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesorg(String value) {
        this.salesorg = value;
    }

    /**
     * 获取channel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannel() {
        return channel;
    }

    /**
     * 设置channel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannel(String value) {
        this.channel = value;
    }

    /**
     * 获取division属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDivision() {
        return division;
    }

    /**
     * 设置division属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDivision(String value) {
        this.division = value;
    }

    /**
     * 获取salesoffice属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesoffice() {
        return salesoffice;
    }

    /**
     * 设置salesoffice属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesoffice(String value) {
        this.salesoffice = value;
    }

    /**
     * 获取salesgroup属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesgroup() {
        return salesgroup;
    }

    /**
     * 设置salesgroup属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesgroup(String value) {
        this.salesgroup = value;
    }

    /**
     * 获取soldto属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSoldto() {
        return soldto;
    }

    /**
     * 设置soldto属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSoldto(String value) {
        this.soldto = value;
    }

    /**
     * 获取sendto属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSendto() {
        return sendto;
    }

    /**
     * 设置sendto属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSendto(String value) {
        this.sendto = value;
    }

    /**
     * 获取pricedate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPricedate() {
        return pricedate;
    }

    /**
     * 设置pricedate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPricedate(String value) {
        this.pricedate = value;
    }

}
