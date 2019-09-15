package com.crazy.portal.bean.order.wsdl.delivery;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 15:06 2019-09-15
 * @Modified by:
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Item {

    @XmlElement(name = "Sapdeliveryid")
    private String sapdeliveryid = "";
    @XmlElement(name = "Soldto")
    private String soldto = "";
    @XmlElement(name = "Sendto")
    private String sendto = "";
    @XmlElement(name = "Deliverydate")
    private String deliverydate = "";
    @XmlElement(name = "Actualdeliverydate")
    private String actualdeliverydate = "";
    @XmlElement(name = "Deliveryitemno")
    private String deliveryitemno = "";
    @XmlElement(name = "Reforderid")
    private String reforderid = "";
    @XmlElement(name = "Reforderitemno")
    private String reforderitemno = "";
    @XmlElement(name = "Productid")
    private String productid = "";
    @XmlElement(name = "Deliveryquantity")
    private String deliveryquantity = "";
    @XmlElement(name = "Changedon")
    private String hangedon = "";
}
