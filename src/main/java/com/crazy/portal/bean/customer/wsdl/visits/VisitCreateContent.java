package com.crazy.portal.bean.customer.wsdl.visits;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @ClassName: VisitCreateContent
 * @Author: God Man Qiu~
 * @Date: 2019/9/11 17:52
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class VisitCreateContent {

    public VisitCreateContent() {
    }

    public VisitCreateContent(VisitCreateBean visitBean,VisitCreateHeader header) {
        this.visitBean = visitBean;
        this.header = header;
    }

    @XmlElement(name = "BasicMessageHeader")
    private VisitCreateHeader header;

    @XmlElement(name = "AppointmentActivity")
    private VisitCreateBean visitBean;
}
