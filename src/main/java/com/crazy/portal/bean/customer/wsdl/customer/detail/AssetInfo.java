package com.crazy.portal.bean.customer.wsdl.customer.detail;

import lombok.Data;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @ClassName: AssetInfo
 * @Author: God Man Qiu~
 * @Date: 2019/9/17 10:34
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class AssetInfo {
    @XmlAttribute(name="ActionCode")
    private String actionCode = "01";

    @XmlElement(name = "Year")
    private String year;

    @XmlElement(name = "Season")
    private String season;

    @XmlElement(name = "TotalAssets currencyCode=\"USD\"")
    private String totalAssets;

    @XmlElement(name = "NetAssets currencyCode=\"USD\"")
    private String netAssets;

    @XmlElement(name = "Revenue currencyCode=\"USD\"")
    private String revenue;

    @XmlElement(name = "TotalStaff")
    private String totalStaff;
}
