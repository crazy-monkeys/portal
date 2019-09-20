package com.crazy.portal.bean.customer.wsdl.customer.detail;

import lombok.Data;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @ClassName: ShareholdingInformation
 * @Author: God Man Qiu~
 * @Date: 2019/9/20 11:20
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class ShareholdingInformation {
    @XmlAttribute(name="ActionCode")
    private String actionCode = "01";

    @XmlElement(name = "Z_SUPERIOR_SHAREHOLDER")
    private String zSuperiorShareholder;

    @XmlElement(name = "Z_SHAREHOLDER")
    private String zShareholder;

    @XmlElement(name = "Z_EQUITY_RATIO")
    private String zEquityRatio;

    @XmlElement(name = "Z_NATURE_OF_SHAREHOLDER")
    private String zNatureOfShareholder;

    @XmlElement(name = "Z_NATURE_OF_COMPANY")
    private String zNatureOfCompany;

    @XmlElement(name = "Z_IS_MANAGER")
    private String zIsManager;

    @XmlElement(name = "Z_DEPARTMENT")
    private String zDepartment;

    @XmlElement(name = "Z_TITLE")
    private String zTitle;
}
