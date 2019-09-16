package com.crazy.portal.bean.customer.wsdl.customer.info;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @ClassName: DirectResponsibility
 * @Author: God Man Qiu~
 * @Date: 2019/9/15 16:03
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class DirectResponsibility {
    @XmlElement(name = "ObjectNodeSenderTechnicalID")
    private String objectNodeSenderTechnicalID;

    @XmlElement(name = "PartyRoleCode")
    private String partyRoleCode;

    @XmlElement(name = "EmployeeID")
    private String employeeID;
}
