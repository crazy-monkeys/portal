package com.crazy.portal.bean.customer.wsdl.customer.info;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @ClassName: Telephone
 * @Author: God Man Qiu~
 * @Date: 2019/9/15 15:50
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Telephone {
    @XmlElement(name = "ObjectNodeSenderTechnicalID")
    private String objectNodeSenderTechnicalID;

    /*手机号码*/
    @XmlElement(name = "FormattedNumberDescription")
    private String formattedNumberDescription;
}
