
package npcxf;

/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="EtItems" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZpricessimulateItemOut" minOccurs="0"/&gt;
 *         &lt;element name="IsHeader" type="{urn:sap-com:document:sap:soap:functions:mc-style}ZpricessimulateHeaderIn" minOccurs="0"/&gt;
 *         &lt;element name="ItItems" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZpricessimulateItemIn" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "etItems",
    "isHeader",
    "itItems"
})
@XmlRootElement(name = "Zrfcsdpricesimulate")
public class Zrfcsdpricesimulate_Type {

    @XmlElement(name = "EtItems")
    protected TableOfZpricessimulateItemOut etItems;
    @XmlElement(name = "IsHeader")
    protected ZpricessimulateHeaderIn isHeader;
    @XmlElement(name = "ItItems")
    protected TableOfZpricessimulateItemIn itItems;

    /**
     * 获取etItems属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TableOfZpricessimulateItemOut }
     *     
     */
    public TableOfZpricessimulateItemOut getEtItems() {
        return etItems;
    }

    /**
     * 设置etItems属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZpricessimulateItemOut }
     *     
     */
    public void setEtItems(TableOfZpricessimulateItemOut value) {
        this.etItems = value;
    }

    /**
     * 获取isHeader属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ZpricessimulateHeaderIn }
     *     
     */
    public ZpricessimulateHeaderIn getIsHeader() {
        return isHeader;
    }

    /**
     * 设置isHeader属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ZpricessimulateHeaderIn }
     *     
     */
    public void setIsHeader(ZpricessimulateHeaderIn value) {
        this.isHeader = value;
    }

    /**
     * 获取itItems属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TableOfZpricessimulateItemIn }
     *     
     */
    public TableOfZpricessimulateItemIn getItItems() {
        return itItems;
    }

    /**
     * 设置itItems属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZpricessimulateItemIn }
     *     
     */
    public void setItItems(TableOfZpricessimulateItemIn value) {
        this.itItems = value;
    }

}
