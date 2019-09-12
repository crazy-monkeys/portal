package com.crazy.portal.bean.order.wsdl.change;

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
public class ZrfcsdsalesorderchangeBody {

    public ZrfcsdsalesorderchangeBody() {
    }

    public ZrfcsdsalesorderchangeBody(ZrfcsdsalesorderchangeContent bodyContent) {
        this.bodyContent = bodyContent;
    }

    @XmlElement(name = "urn:Zrfcsdsalesorderchange")
    private ZrfcsdsalesorderchangeContent bodyContent;
}
