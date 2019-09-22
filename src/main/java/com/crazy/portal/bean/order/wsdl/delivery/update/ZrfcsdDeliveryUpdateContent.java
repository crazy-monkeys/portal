package com.crazy.portal.bean.order.wsdl.delivery.update;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @ClassName: ZrfcsdDeliveryUpdateContent
 * @Author: God Man Qiu~
 * @Date: 2019/9/23 01:04
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class ZrfcsdDeliveryUpdateContent {
    @XmlElement(name = "Deliverydate")
    private String deliverydate;

    @XmlElement(name = "Itype")
    private String iType;

    @XmlElement(name = "Sapdeliveryid")
    private String sapDeliveryId;

    @XmlElement(name = "Titem")
    private TItem tItem;
}
