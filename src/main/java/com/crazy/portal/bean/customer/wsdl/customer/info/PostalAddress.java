package com.crazy.portal.bean.customer.wsdl.customer.info;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @ClassName: PostalAddress
 * @Author: God Man Qiu~
 * @Date: 2019/9/15 15:48
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class PostalAddress {
    @XmlElement(name="CountryCode")
    private String countryCode;

    @XmlElement(name="CityName")
    private String cityName;

    @XmlElement(name="StreetName")
    private String streetName;
}
