package com.crazy.portal.bean;

import com.crazy.portal.bean.order.wsdl.RequestBody;
import lombok.Data;

import javax.xml.bind.annotation.*;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 20:40 2019-09-08
 * @Modified by:
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "body"
})
@Data
@XmlRootElement(name = "soapenv:Envelope")
public class BaseWSDLRequest {

    public BaseWSDLRequest() {}

    public BaseWSDLRequest(RequestBody body) {
        this.body = body;
    }

    @XmlAttribute(name="xmlns:soapenv")
    private String soapenv = "http://schemas.xmlsoap.org/soap/envelope/";

    @XmlAttribute(name="xmlns:urn")
    private String urn = "urn:sap-com:document:sap:soap:functions:mc-style";

    @XmlElement(name="soap:Body")
    private RequestBody body;
}
