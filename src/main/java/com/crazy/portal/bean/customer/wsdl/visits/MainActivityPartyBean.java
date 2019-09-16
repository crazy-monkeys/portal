package com.crazy.portal.bean.customer.wsdl.visits;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @ClassName: MainActivityPartyBean
 * @Author: God Man Qiu~
 * @Date: 2019/9/11 18:31
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class MainActivityPartyBean {
    @XmlElement(name = "BusinessPartnerInternalID")
    private String businessPartnerInternalID;
}
