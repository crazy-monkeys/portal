package com.crazy.portal.bean.customer.wsdl.customer.info;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @ClassName: AddressUsage
 * @Author: God Man Qiu~
 * @Date: 2019/9/15 15:45
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class AddressUsage {
    @XmlAttribute(name="actionCode")
    private String actionCode = "01";

    @XmlElement(name="ObjectNodeSenderTechnicalID")
    private String objectNodeSenderTechnicalID;

    @XmlElement(name="AddressUsageCode")
    private String addressUsageCode;
}
