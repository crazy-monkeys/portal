package com.crazy.portal.bean.order.wsdl.change;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 23:56 2019-09-11
 * @Modified by:
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class ItItem {

    @XmlElement(name = "Operationtype")
    private String operationtype = "";
    @XmlElement(name = "Sequenceno")
    private String sequenceno = "";
    @XmlElement(name = "Itemno")
    private String itemno = "";
    @XmlElement(name = "Productid")
    private String productid = "";
    @XmlElement(name = "Orderquantity")
    private String orderquantity = "";
    @XmlElement(name = "Platform")
    private String platform = "";
    @XmlElement(name = "Requestdate")
    private String requestdate = "";
    @XmlElement(name = "Rejectreason")
    private String rejectreason = "";
    @XmlElement(name = "Customercode")
    private String customercode = "";
}
