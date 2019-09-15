package com.crazy.portal.bean.order.wsdl.delivery;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class ZrfcsddeliverylistContent {

    @XmlElement(name = "Changedfrom")
    private String changedfrom = "";

    @XmlElement(name = "Changedto")
    private String changedto = "";

    @XmlElement(name = "Deliverydatefrom")
    private String deliverydatefrom = "";

    @XmlElement(name = "Deliverydateto")
    private String deliverydateto = "";

    @XmlElement(name = "EtList")
    private List<Item> etList;

    @XmlElement(name = "Productid")
    private String productid = "";

    @XmlElement(name = "Sapdeliveryid")
    private String sapdeliveryid = "";

    @XmlElement(name = "Saporderid")
    private String saporderid = "";

    @XmlElement(name = "Sendto")
    private String sendto = "";

    @XmlElement(name = "Soldto")
    private String soldto = "";
}
