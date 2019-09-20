package com.crazy.portal.bean.customer.wsdl.customer.detail;

import lombok.Data;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @ClassName: BusinessIntroduction
 * @Author: God Man Qiu~
 * @Date: 2019/9/17 10:37
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class BusinessIntroduction {
    @XmlAttribute(name="ActionCode")
    private String actionCode = "01";

    @XmlElement(name = "Year")
    private String year;

    @XmlElement(name = "ProductLine1")
    private String productLine1;

    @XmlElement(name = "RevenuePL1")
    private String revenuePL1;

    @XmlElement(name = "RevenuePL2")
    private String revenuePL2;

    @XmlElement(name = "RevenuePL3")
    private String revenuePL3;
}
