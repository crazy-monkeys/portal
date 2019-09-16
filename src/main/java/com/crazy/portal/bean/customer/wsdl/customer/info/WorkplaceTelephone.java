package com.crazy.portal.bean.customer.wsdl.customer.info;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @ClassName: WorkplaceTelephone
 * @Author: God Man Qiu~
 * @Date: 2019/9/15 16:01
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class WorkplaceTelephone {
    @XmlElement(name = "ObjectNodeSenderTechnicalID")
    private String objectNodeSenderTechnicalID;

    @XmlElement(name = "FormattedNumberDescription")
    private String formattedNumberDescription;
}
