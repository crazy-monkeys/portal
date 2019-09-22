package com.crazy.portal.bean.order.wsdl.delivery.update;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @ClassName: ZrfcsdDeliveryUpdateBody
 * @Author: God Man Qiu~
 * @Date: 2019/9/23 01:03
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "soapenv:Body")
public class ZrfcsdDeliveryUpdateBody {
    public ZrfcsdDeliveryUpdateBody(){}

    public ZrfcsdDeliveryUpdateBody(ZrfcsdDeliveryUpdateContent content){
        this.content = content;
    }

    @XmlElement(name = "urn:Zrfcsddeliverychange")
    private ZrfcsdDeliveryUpdateContent content;
}
