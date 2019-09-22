package com.crazy.portal.bean.order.wsdl.delivery.update;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @ClassName: Item
 * @Author: God Man Qiu~
 * @Date: 2019/9/23 01:07
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Item {
    @XmlElement(name = "Operationtype")
    private String operationType;

    @XmlElement(name = "Deliveryitemno")
    private String deliveryItemNo;

    @XmlElement(name = "Deliveryquantity")
    private String deliveryQuantity;
}
