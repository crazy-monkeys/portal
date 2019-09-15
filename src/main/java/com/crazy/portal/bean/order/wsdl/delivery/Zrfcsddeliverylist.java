package com.crazy.portal.bean.order.wsdl.delivery;

import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.annotation.*;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 20:40 2019-09-08
 * @Modified by:
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "soapenv:Envelope")
@Slf4j
public class Zrfcsddeliverylist {

    public Zrfcsddeliverylist() {
    }

    public Zrfcsddeliverylist(ZrfcsddeliverylistBody body) {
        this.body = body;
    }

    @XmlAttribute(name="xmlns:soapenv")
    private String soapenv = "http://schemas.xmlsoap.org/soap/envelope/";

    @XmlAttribute(name="xmlns:urn")
    private String urn = "urn:sap-com:document:sap:soap:functions:mc-style";

    @XmlElement(name="soapenv:Body")
    private ZrfcsddeliverylistBody body;
}