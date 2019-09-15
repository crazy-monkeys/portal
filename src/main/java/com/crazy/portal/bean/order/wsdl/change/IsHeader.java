package com.crazy.portal.bean.order.wsdl.change;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 23:54 2019-09-11
 * @Modified by:
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class IsHeader {

    @XmlElement(name = "Saporderid")
    private String saporderid = "";
    @XmlElement(name = "Salesoffice")
    private String salesoffice = "";
    @XmlElement(name = "Salesgroup")
    private String salesgroup = "";
    @XmlElement(name = "Sendto")
    private String sendto = "";
    @XmlElement(name = "Purchaseno")
    private String purchaseno = "";
    @XmlElement(name = "Purchasedate")
    private String purchasedate = "";
    @XmlElement(name = "Orderreason")
    private String orderreason = "";
    @XmlElement(name = "Paymentterms")
    private String paymentterms = "";
    @XmlElement(name = "Incoterms1")
    private String incoterms1 = "";
    @XmlElement(name = "Incoterms2")
    private String incoterms2 = "";
    @XmlElement(name = "Customergroup1")
    private String customergroup1 = "";
    @XmlElement(name = "Customergroup2")
    private String customergroup2 = "";
    @XmlElement(name = "Pricedate")
    private String pricedate = "";
}
