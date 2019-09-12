package com.crazy.portal.bean.order.wsdl.price;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 20:42 2019-09-08
 * @Modified by:
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ZrfcsdpricesimulateBody {

    public ZrfcsdpricesimulateBody() {
    }

    public ZrfcsdpricesimulateBody(ZrfcsdpricesimulateContent bodyContent) {
        this.bodyContent = bodyContent;
    }

    @XmlElement(name = "urn:Zrfcsdpricesimulate")
    private ZrfcsdpricesimulateContent bodyContent;
}
