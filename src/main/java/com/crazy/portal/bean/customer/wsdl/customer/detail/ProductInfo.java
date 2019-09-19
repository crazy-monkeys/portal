package com.crazy.portal.bean.customer.wsdl.customer.detail;

import lombok.Data;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @ClassName: ProductInfo
 * @Author: God Man Qiu~
 * @Date: 2019/9/17 10:03
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class ProductInfo {
    @XmlAttribute(name="ActionCode")
    private String actionCode = "01";

    @XmlElement(name = "ProductCode")
    private String productCode;

    @XmlElement(name = "BusinessUnit")
    private String businessUnit;

    @XmlElement(name = "ExpectedShipments")
    private String expectedShipments;
}
