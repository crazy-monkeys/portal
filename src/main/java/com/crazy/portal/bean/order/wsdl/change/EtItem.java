package com.crazy.portal.bean.order.wsdl.change;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 23:49 2019-09-11
 * @Modified by:
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class EtItem {

    @XmlElement(name = "Itemno")
    private String itemno = "";
    @XmlElement(name = "Productid")
    private String productid = "";
    @XmlElement(name = "Price")
    private String price = "";
    @XmlElement(name = "Netprice")
    private String netprice = "";
    @XmlElement(name = "Sapquantity")
    private String sapquantity = "";
    @XmlElement(name = "CondUnit")
    private String condUnit = "";
    @XmlElement(name = "Currency")
    private String currency = "";
    @XmlElement(name = "Itemcategory")
    private String itemcategory = "";
    @XmlElement(name = "Refitemno")
    private String refitemno = "";
    @XmlElement(name = "Refitemproductid")
    private String refitemproductid = "";
    @XmlElement(name = "Platform")
    private String platform = "";
    @XmlElement(name = "Abgru")
    private String abgru = "";
}
