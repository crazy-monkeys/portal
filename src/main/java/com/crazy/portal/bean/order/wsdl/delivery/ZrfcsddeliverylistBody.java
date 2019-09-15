package com.crazy.portal.bean.order.wsdl.delivery;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 20:42 2019-09-08
 * @Modified by:
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "soapenv:Body")
public class ZrfcsddeliverylistBody {

    public ZrfcsddeliverylistBody() {
    }

    public ZrfcsddeliverylistBody(ZrfcsddeliverylistContent bodyContent) {
        this.bodyContent = bodyContent;
    }

    @XmlElement(name = "urn:Zrfcsddeliverylist")
    private ZrfcsddeliverylistContent bodyContent;
}
