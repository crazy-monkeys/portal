package com.crazy.portal.bean.order.wsdl.createOrder.request;

import javax.xml.bind.annotation.*;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 20:42 2019-09-08
 * @Modified by:
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "soapenv:Body")
public class ZrfcsdsalesordercreateBody {

    public ZrfcsdsalesordercreateBody() {
    }

    public ZrfcsdsalesordercreateBody(ZrfcsdsalesordercreateContent bodyContent) {
        this.bodyContent = bodyContent;
    }

    @XmlElement(name = "urn:Zrfcsdsalesordercreate")
    private ZrfcsdsalesordercreateContent bodyContent;
}
