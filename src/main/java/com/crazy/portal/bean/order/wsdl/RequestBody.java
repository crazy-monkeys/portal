package com.crazy.portal.bean.order.wsdl;

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
public class RequestBody {

    public RequestBody() {
    }

    public RequestBody(BodyContent bodyContent) {
        this.bodyContent = bodyContent;
    }

    @XmlElement(name = "urn:OrderRequest")
    private BodyContent bodyContent;
}
