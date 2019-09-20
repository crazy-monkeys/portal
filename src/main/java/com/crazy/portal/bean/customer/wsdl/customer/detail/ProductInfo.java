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

    @XmlElement(name = "CURRENT_YEAR")
    private String currentYear;

    @XmlElement(name = "CURRENT_MONTH")
    private String currentMonth;

    @XmlElement(name = "EXPECTED_SHIPMENTS1")
    private String expectedShipments1;

    @XmlElement(name = "EXPECTED_SHIPMENTS2")
    private String expectedShipments2;

    @XmlElement(name = "EXPECTED_SHIPMENTS3")
    private String expectedShipments3;

    @XmlElement(name = "EXPECTED_SHIPMENTS4")
    private String expectedShipments4;

    @XmlElement(name = "EXPECTED_SHIPMENTS5")
    private String expectedShipments5;

    @XmlElement(name = "EXPECTED_SHIPMENTS6")
    private String expectedShipments6;
}
