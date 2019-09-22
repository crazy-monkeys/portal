package com.crazy.portal.bean.order.wsdl.delivery.create;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @ClassName: ZrfcsdDeliveryCreateBody
 * @Author: God Man Qiu~
 * @Date: 2019/9/23 00:24
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "soapenv:Body")
public class ZrfcsdDeliveryCreateBody {

    public ZrfcsdDeliveryCreateBody(){}

    public ZrfcsdDeliveryCreateBody(ZrfcsdDeliveryCreateContent content){
        this.bodyContent = content;
    }

    @XmlElement(name = "urn:Zrfcsddeliverycreate")
    private ZrfcsdDeliveryCreateContent bodyContent;
}
