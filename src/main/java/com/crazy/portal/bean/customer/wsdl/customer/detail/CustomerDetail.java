package com.crazy.portal.bean.customer.wsdl.customer.detail;

import lombok.Data;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @ClassName: CustomerDetail
 * @Author: God Man Qiu~
 * @Date: 2019/9/17 10:01
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class CustomerDetail {
    @XmlAttribute(name="ProductInfoListCompleteTransmissionIndicator")
    private String productInfoListCompleteTransmissionIndicator = "true";

    @XmlElement(name = "CustomerID")
    private String customerID;

    @XmlElement(name = "ProductInfo")
    private ProductInfo productInfo;

    @XmlElement(name = "AssetInfo")
    private AssetInfo assetInfo;
}
