package com.crazy.portal.bean.order.wsdl.delivery.create;

import lombok.Data;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @ClassName: Item
 * @Author: God Man Qiu~
 * @Date: 2019/9/23 00:30
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Item {
    @XmlElement(name = "Itemno")
    private String ItemNo;

    @XmlElement(name = "Deliveryitemno")
    private String deliveryItemNo;

    @XmlElement(name = "Productid")
    private String productId;

    @XmlElement(name = "Deliveryquantity")
    private String deliveryQuantity;
}
