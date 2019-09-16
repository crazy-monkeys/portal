package com.crazy.portal.bean.customer.wsdl.visits;

import lombok.extern.slf4j.Slf4j;
import javax.xml.bind.annotation.*;

/**
 * @ClassName: VisitCreate
 * @Author: God Man Qiu~
 * @Date: 2019/9/11 17:50
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "soap:Envelope")
@Slf4j
public class VisitCreate {
    public VisitCreate() {
    }

    public VisitCreate(VisitCreateBody body) {
        this.body = body;
    }

    @XmlAttribute(name="xmlns:soap")
    private String soap = "http://www.w3.org/2003/05/soap-envelope";

    @XmlAttribute(name="xmlns:glob")
    private String glob = "http://sap.com/xi/SAPGlobal20/Global";

    @XmlAttribute(name="xmlns:y4r")
    private String y4r = "http://0003111061-one-off.sap.com/Y4R4GVT9Y_";

    @XmlElement(name="soap:Body")
    private VisitCreateBody body;
}
