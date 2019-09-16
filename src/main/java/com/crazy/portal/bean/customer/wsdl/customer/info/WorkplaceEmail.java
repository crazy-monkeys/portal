package com.crazy.portal.bean.customer.wsdl.customer.info;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @ClassName: WorkplaceEmail
 * @Author: God Man Qiu~
 * @Date: 2019/9/15 16:00
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class WorkplaceEmail {

    @XmlElement(name = "URI")
    private String uRI;
}
