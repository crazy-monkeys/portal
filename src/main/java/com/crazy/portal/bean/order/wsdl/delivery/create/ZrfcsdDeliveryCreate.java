package com.crazy.portal.bean.order.wsdl.delivery.create;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.annotation.*;

/**
 * @ClassName: ZrfcsdDeliveryCreate
 * @Author: God Man Qiu~
 * @Date: 2019/9/23 00:37
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "soapenv:Envelope")
@Slf4j
@Data
public class ZrfcsdDeliveryCreate {

    public ZrfcsdDeliveryCreate(){}

    public ZrfcsdDeliveryCreate(ZrfcsdDeliveryCreateBody body){
        this.body = body;
    }

    @XmlAttribute(name="xmlns:soapenv")
    private String soapenv = "http://schemas.xmlsoap.org/soap/envelope/";

    @XmlAttribute(name="xmlns:urn")
    private String urn = "urn:sap-com:document:sap:soap:functions:mc-style";

    @XmlElement(name="soapenv:Body")
    private ZrfcsdDeliveryCreateBody body;

}
