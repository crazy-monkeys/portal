package com.crazy.portal.bean.order.wsdl.createOrder.request.visits;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @ClassName: OtherPartyBean
 * @Author: God Man Qiu~
 * @Date: 2019/9/11 18:31
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class OtherPartyBean {
    @XmlElement(name = "BusinessPartnerInternalID")
    private String businessPartnerInternalID;
}
