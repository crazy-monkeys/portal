package com.crazy.portal.bean.customer.wsdl.customer.info;

import lombok.Data;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @ClassName: Relationship
 * @Author: God Man Qiu~
 * @Date: 2019/9/15 16:07
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Relationship {
    @XmlElement(name = "ObjectNodeSenderTechnicalID")
    private String objectNodeSenderTechnicalID;

    @XmlElement(name = "RelationshipBusinessPartnerInternalID")
    private String relationshipBusinessPartnerInternalID;

    @XmlElement(name = "RoleCode")
    private String roleCode;
}
