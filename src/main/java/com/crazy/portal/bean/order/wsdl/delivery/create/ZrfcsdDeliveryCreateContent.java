package com.crazy.portal.bean.order.wsdl.delivery.create;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @ClassName: ZrfcsdDeliveryCreateContent
 * @Author: God Man Qiu~
 * @Date: 2019/9/23 00:25
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class ZrfcsdDeliveryCreateContent {
    @XmlElement(name = "Deliverydate")
    private String deliverydate;

    @XmlElement(name = "DeliveryIoc")
    private String deliveryIoc;

    @XmlElement(name = "Portaldeliveryid")
    private String portalDeliveryId;

    @XmlElement(name = "Saporderid")
    private String sapOrderId;

    @XmlElement(name = "Titem")
    private TItem tItem;
}
