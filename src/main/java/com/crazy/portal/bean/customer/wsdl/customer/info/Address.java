package com.crazy.portal.bean.customer.wsdl.customer.info;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @ClassName: Address
 * @Author: God Man Qiu~
 * @Date: 2019/9/15 15:47
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Address {
    @XmlAttribute(name="actionCode")
    private String actionCode = "01";

    @XmlAttribute(name="telephoneListCompleteTransmissionIndicator")
    private String telephoneListCompleteTransmissionIndicator = "true";

    @XmlElement(name = "PostalAddress")
    private PostalAddress postalAddress;

    @XmlElement(name = "Telephone")
    private Telephone telephone;

    @XmlElement(name = "Email")
    private Email email;

    @XmlElement(name = "WebURI")
    private String webURI;




}
