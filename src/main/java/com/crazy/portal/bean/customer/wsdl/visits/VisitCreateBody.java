package com.crazy.portal.bean.customer.wsdl.visits;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @ClassName: VisitCreateBody
 * @Author: God Man Qiu~
 * @Date: 2019/9/11 17:52
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "soap:Body")
public class VisitCreateBody {

    public VisitCreateBody() {
    }

    public VisitCreateBody(VisitCreateContent content) {
        this.content = content;
    }

    @XmlElement(name = "glob:AppointmentActivityBundleMaintainRequest_sync_V1")
    private VisitCreateContent content;
}
