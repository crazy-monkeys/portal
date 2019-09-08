
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
 *         &lt;element name="EsHeader" type="{urn:sap-com:document:sap:soap:functions:mc-style}ZpricessimulateHeaderOut"/&gt;
 *         &lt;element name="EtItems" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZpricessimulateItemOut"/&gt;
 *         &lt;element name="ItItems" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZpricessimulateItemIn"/&gt;
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
    "esHeader",
    "etItems",
    "itItems"
})
@XmlRootElement(name = "ZrfcsdpricesimulateResponse")
public class ZrfcsdpricesimulateResponse {

    @XmlElement(name = "EsHeader", required = true)
    protected ZpricessimulateHeaderOut esHeader;
    @XmlElement(name = "EtItems", required = true)
    protected TableOfZpricessimulateItemOut etItems;
    @XmlElement(name = "ItItems", required = true)
    protected TableOfZpricessimulateItemIn itItems;

    /**
     * 获取esHeader属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ZpricessimulateHeaderOut }
     *     
     */
    public ZpricessimulateHeaderOut getEsHeader() {
        return esHeader;
    }

    /**
     * 设置esHeader属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ZpricessimulateHeaderOut }
     *     
     */
    public void setEsHeader(ZpricessimulateHeaderOut value) {
        this.esHeader = value;
    }

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
