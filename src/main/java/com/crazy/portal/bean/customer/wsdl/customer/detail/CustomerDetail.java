package com.crazy.portal.bean.customer.wsdl.customer.detail;

import lombok.Data;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

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
    private List<ProductInfo> productInfo;

    @XmlElement(name = "AssetInfo")
    private List<AssetInfo> assetInfo;

    @XmlElement(name = "BusinessIntroduction")
    private List<BusinessIntroduction> businessIntroductions;

    @XmlElement(name = "ShareholdingInformation")
    private List<ShareholdingInformation> shareholdingInformation;
}
