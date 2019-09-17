package com.crazy.portal.bean.customer.wsdl.visits;

import lombok.Data;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @ClassName: VisitCreateBean
 * @Author: God Man Qiu~
 * @Date: 2019/9/11 18:11
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class VisitCreateBean {

    @XmlAttribute(name="actionCode")
    private String actionCode = "01";

    //c4c id
    @XmlElement(name = "ObjectNodeSenderTechnicalID")
    private String objectNodeSenderTechnicalID="001";

    //主题
    @XmlElement(name = "Name")
    private String name;

    //类型 Z01 -Z05
    @XmlElement(name = "LifeCycleStatusCode")
    private String lifeCycleStatusCode;

    //状态1- 2- 3-
    @XmlElement(name = "VisitTypeCode")
    private String visitTypeCode;

    //0027
    @XmlElement(name = "GroupCode")
    private String groupCode = "0027";

    //12(
    @XmlElement(name = "TypeCode")
    private String typeCode ="12";

    //开始日期
    @XmlElement(name = "StartDateTime timeZoneCode=\"CET\"")
    private String startDateTime;

    //结束日期
    @XmlElement(name = "EndDateTime timeZoneCode=\"CET\"")
    private String endDateTime;

    //会谈内容简介
    @XmlElement(name = "y4r:Z_MEETING_CONTENT")
    private String zMeetingContent;

    //后续行动计划
    @XmlElement(name = "y4r:Z_FOLLOW_UP_PLANS")
    private String zFollowUpPlans;

    //诉求描述
    @XmlElement(name = "y4r:Z_REQUIREMENT_DESCRIPTION")
    private String zRequirementDescription;

    @XmlElement(name = "y4r:Z_ATTENDEES")
    private String attendees;

    //地址
    @XmlElement(name = "y4r:Z_ADDRESS")
    private String address;

    @XmlElement(name = "OrganizerParty")
    private OrganizerPartyBean organizerPartyBean;

    @XmlElement(name = "MainActivityParty")
    private MainActivityPartyBean mainActivityPartyBean;

    @XmlElement(name = "OtherParty")
    private OtherPartyBean otherPartyBean;
}