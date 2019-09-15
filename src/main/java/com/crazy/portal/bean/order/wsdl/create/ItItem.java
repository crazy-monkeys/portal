package com.crazy.portal.bean.order.wsdl.create;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 00:03 2019-09-11
 * @Modified by:
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class ItItem {

    @XmlElement(name = "Sequenceno")
    private String sequenceno = "";
    @XmlElement(name = "Productid")
    private String productid = "";
    @XmlElement(name = "Orderquantity")
    private String orderquantity = "";
    @XmlElement(name = "Platform")
    private String platform = "";
    @XmlElement(name = "Requestdate")
    private String requestdate = "";
    @XmlElement(name = "Refsaporderitemno")
    private String refsaporderitemno = "";
}
