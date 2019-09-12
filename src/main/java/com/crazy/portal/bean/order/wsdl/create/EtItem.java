package com.crazy.portal.bean.order.wsdl.create;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 23:16 2019-09-10
 * @Modified by:
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class EtItem {

    @XmlElement(name = "Saporderid")
    private String saporderid;
    @XmlElement(name = "Sequenceno")
    private String sequenceno;
    @XmlElement(name = "Itemno")
    private String itemno;
    @XmlElement(name = "Productid")
    private String productid;
    @XmlElement(name = "Sapquantity")
    private String sapquantity;
    @XmlElement(name = "Price")
    private String price;
    @XmlElement(name = "Netprice")
    private String netprice;
    @XmlElement(name = "CondUnit")
    private String condUnit;
    @XmlElement(name = "Currency")
    private String currency;
    @XmlElement(name = "Refitemno")
    private String refitemno;
    @XmlElement(name = "Refitemproductid")
    private String refitemproductid;
}
