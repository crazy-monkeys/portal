package com.crazy.portal.bean.customer.wsdl.customer.detail;

import lombok.Data;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @ClassName: SalesData
 * @Author: God Man Qiu~
 * @Date: 2019/9/17 10:39
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class SalesData {
    @XmlAttribute(name="ActionCode")
    private String actionCode = "01";

    @XmlElement(name = "CompanyCode")
    private String companyCode;

    @XmlElement(name = "DeliveringPlant")
    private String deliveringPlant;

    @XmlElement(name = "DistributionChannel")
    private String distributionChannel;
}
