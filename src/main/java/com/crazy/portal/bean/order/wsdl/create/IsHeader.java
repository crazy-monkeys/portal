package com.crazy.portal.bean.order.wsdl.create;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 23:56 2019-09-10
 * @Modified by:
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class IsHeader {
    @XmlElement(name = "Portalorderid")
    private String portalorderid = "";
    @XmlElement(name = "Ordertype")
    private String ordertype = "";
    @XmlElement(name = "Salesorg")
    private String salesorg = "";
    @XmlElement(name = "Channel")
    private String channel = "";
    @XmlElement(name = "Division")
    private String division = "";
    @XmlElement(name = "Salesoffice")
    private String salesoffice = "";
    @XmlElement(name = "Salesgroup")
    private String salesgroup = "";
    @XmlElement(name = "Soldto")
    private String soldto = "";
    @XmlElement(name = "Sendto")
    private String sendto = "";
    @XmlElement(name = "Purchaseno")
    private String purchaseno = "";
    @XmlElement(name = "Purchasedate")
    private String purchasedate = "";
    @XmlElement(name = "Paymentterms")
    private String paymentterms = "";
    @XmlElement(name = "Customergroup1")
    private String customergroup1 = "";
    @XmlElement(name = "Customergroup2")
    private String customergroup2 = "";
    @XmlElement(name = "Pricedate")
    private String pricedate = "";
    @XmlElement(name = "Refsaporderid")
    private String refsaporderid = "";
    @XmlElement(name = "Inco1")
    private String inco1 = "";
    @XmlElement(name = "Inco2")
    private String inco2 = "";
    @XmlElement(name = "Augru")
    private String augru = "";

}
