package com.crazy.portal.bean.customer.wsdl.customer.info;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @ClassName: AddressInformation
 * @Author: God Man Qiu~
 * @Date: 2019/9/15 15:43
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class AddressInformation {

    @XmlAttribute(name="actionCode")
    private String actionCode = "01";

    @XmlAttribute(name="addressUsageListCompleteTransmissionIndicator")
    private String addressUsageListCompleteTransmissionIndicator = "true";

    @XmlElement(name = "ObjectNodeSenderTechnicalID")
    private String objectNodeSenderTechnicalID;

    @XmlElement(name = "AddressUsage")
    private AddressUsage addressUsage;

    @XmlElement(name = "Address")
    private Address address;

    @XmlElement(name = "y4r:AddressType")
    private String addressType;

    @XmlElement(name = "y4r:AddressContact")
    private String addressContact;
}
