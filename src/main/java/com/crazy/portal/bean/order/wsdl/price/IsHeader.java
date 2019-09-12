package com.crazy.portal.bean.order.wsdl.price;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 23:17 2019-09-11
 * @Modified by:
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class IsHeader {

    @XmlElement(name = "Portalorderid")
    private String portalorderid;
    @XmlElement(name = "Ordertype")
    private String ordertype;
    @XmlElement(name = "Salesorg")
    private String salesorg;
    @XmlElement(name = "Channel")
    private String channel;
    @XmlElement(name = "Division")
    private String division;
    @XmlElement(name = "Salesoffice")
    private String salesoffice;
    @XmlElement(name = "Salesgroup")
    private String salesgroup;
    @XmlElement(name = "Soldto")
    private String soldto;
    @XmlElement(name = "Sendto")
    private String sendto;
    @XmlElement(name = "Pricedate")
    private String pricedate;
}
