package com.crazy.portal.bean.customer.wsdl.customer.info;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @ClassName: ContactPerson
 * @Author: God Man Qiu~
 * @Date: 2019/9/15 15:57
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class ContactPerson {
    @XmlAttribute(name="actionCode")
    private String actionCode = "01";

    @XmlAttribute(name="workplaceTelephoneListCompleteTransmissionIndicator")
    private String workplaceTelephoneListCompleteTransmissionIndicator = "true";

    @XmlAttribute(name="addressInformationListCompleteTransmissionIndicator")
    private String addressInformationListCompleteTransmissionIndicator = "false";

    @XmlElement(name = "ObjectNodeSenderTechnicalID")
    private String objectNodeSenderTechnicalID;

    @XmlElement(name = "FamilyName")
    private String familyName;

/*    @XmlElement(name = "BusinessPartnerContactInternalID")
    private String businessPartnerContactInternalID ="1";*/

    @XmlElement(name = "BusinessPartnerFunctionTypeCode")
    private String businessPartnerFunctionTypeCode;

    @XmlElement(name = "WorkplacePreferredCommunicationMediumTypeCode")
    private String workplacePreferredCommunicationMediumTypeCode;

    @XmlElement(name = "WorkplaceEmailURI")
    private String workplaceEmailURI;

    @XmlElement(name = "WorkplaceEmail")
    private WorkplaceEmail workplaceEmail;

    @XmlElement(name = "WorkplaceTelephone")
    private WorkplaceTelephone workplaceTelephone;

    @XmlElement(name = "WorkplaceDepartmentName")
    private String workplaceDepartmentName;

    @XmlElement(name = "LifeCycleStatusCode")
    private String lifeCycleStatusCode;

    @XmlElement(name = "y4r:SecondaryDept")
    private String secondaryDept;
}
