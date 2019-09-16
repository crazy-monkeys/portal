package com.crazy.portal.bean.customer.wsdl.customer.info;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @ClassName: Email
 * @Author: God Man Qiu~
 * @Date: 2019/9/15 15:52
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Email {

    @XmlElement(name = "URI")
    private String uRI;
}
