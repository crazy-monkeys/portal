package com.crazy.portal.bean.customer.wsdl.customer.info;

import lombok.Data;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * @ClassName: Customer
 * @Author: God Man Qiu~
 * @Date: 2019/9/15 15:35
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Customer {

    /*01-新增 02-修改*/
    @XmlAttribute(name="actionCode")
    private String actionCode;

    @XmlAttribute(name="addressInformationListCompleteTransmissionIndicator")
    private String addressInformationListCompleteTransmissionIndicator = "true";

    @XmlAttribute(name="bankDetailsListCompleteTransmissionIndicator")
    private String bankDetailsListCompleteTransmissionIndicator = "true";

    @XmlAttribute(name="communicationArrangementListCompleteTransmissionIndicator")
    private String communicationArrangementListCompleteTransmissionIndicator = "true";

    @XmlAttribute(name="directResponsibilityListCompleteTransmissionIndicator")
    private String directResponsibilityListCompleteTransmissionIndicator = "true";

    @XmlAttribute(name="operatingHoursInformationListCompleteTransmissionIndicator")
    private String operatingHoursInformationListCompleteTransmissionIndicator = "true";

    @XmlAttribute(name="paymentCardDetailsListCompleteTransmissionIndicator")
    private String paymentCardDetailsListCompleteTransmissionIndicator = "true";

    @XmlAttribute(name="relationshipListCompleteTransmissionIndicator")
    private String relationshipListCompleteTransmissionIndicator = "true";

    @XmlAttribute(name="salesArrangementListCompleteTransmissionIndicator")
    private String salesArrangementListCompleteTransmissionIndicator = "true";

    @XmlAttribute(name="taxNumberListCompleteTransmissionIndicator")
    private String taxNumberListCompleteTransmissionIndicator = "true";

    @XmlAttribute(name="generalProductTaxExemptionListCompleteTransmissionIndicator")
    private String generalProductTaxExemptionListCompleteTransmissionIndicator = "true";

    @XmlAttribute(name="contactPersonListCompleteTransmissionIndicator")
    private String contactPersonListCompleteTransmissionIndicator = "true";

    @XmlAttribute(name="paymentDataListCompleteTransmissionIndicator")
    private String paymentDataListCompleteTransmissionIndicator = "true";

    @XmlAttribute(name="textListCompleteTransmissionIndicator")
    private String textListCompleteTransmissionIndicator = "true";

    @XmlElement(name = "ObjectNodeSenderTechnicalID")
    private String objectNodeSenderTechnicalID = "01";

    @XmlElement(name = "InternalID")
    private String internalID;

    @XmlElement(name = "CategoryCode")
    private String categoryCode="2";

    @XmlElement(name = "ProspectIndicator")
    private String prospectIndicator = "false";

    @XmlElement(name = "CustomerIndicator")
    private String customerIndicator = "true";

    /*状态 1- 2- */
    @XmlElement(name = "LifeCycleStatusCode")
    private String lifeCycleStatusCode="2";

    @XmlElement(name = "Organisation")
    private Organisation organisation;

    @XmlElement(name = "AddressInformation")
    private List<AddressInformation> addressInformation;

    @XmlElement(name = "ContactPerson")
    private List<ContactPerson> contactPerson;

    @XmlElement(name = "DirectResponsibility")
    private List<DirectResponsibility> directResponsibility;

    @XmlElement(name = "BlockingReasons")
    private BlockingReasons blockingReasons;

    @XmlElement(name = "Relationship")
    private List<Relationship> relationship;

    /*客户简称*/
    @XmlElement(name = "yor:Abbreviation")
    private String abbreviation;

    /*客户类型 A01 - Account Market（直供）；A02 - Account Market（非直供）；A03 - Mass Market；A04 - 代理商*/
    @XmlElement(name = "yor:Type")
    private String type;

    /*是否license Y/N*/
    @XmlElement(name = "yor:IsLicenseAccount")
    private String isLicenseAccount;

    /*用户角色 Z001 - 中国客户(Chinese Account); Z002 - 亚太客户(Asia-Pacific Account); Z003 - 北美客户(North American Account); Z010 - 内部客户(Customer);*/
    @XmlElement(name = "yor:AccountRole")
    private String accountRole;

    @XmlElement(name = "yor:RegistrationDate")
    private String registrationDate;

    @XmlElement(name = "yor:AdvantagesIntroduction")
    private String advantagesIntroduction;

    @XmlElement(name = "yor:Businessintroduction")
    private String businessintroduction;

    @XmlElement(name = "yor:StaffNumber")
    private String staffNumber;

    @XmlElement(name = "yor:DevelopersNumber")
    private String developersNumber;

    @XmlElement(name = "yor:CorporateAssets currencyCode=\"CNY\"")
    private String corporateAssets;

    @XmlElement(name = "yor:BankName")
    private String bankName;

    @XmlElement(name = "yor:BankAddress")
    private String bankAddress;

    @XmlElement(name = "yor:Account")
    private String account;

    @XmlElement(name = "yor:BIC")
    private String bIC;

    @XmlElement(name = "yor:PurchasingUnit")
    private String purchasingUnit;

    @XmlElement(name = "yor:ShippingAddress")
    private String shippingAddress;

    @XmlElement(name = "yor:Phone")
    private String phone;

    @XmlElement(name = "yor:TaxpayerRegistrationNumber")
    private String taxpayerRegistrationNumber;

    @XmlElement(name = "yor:Currency")
    private String currency;

    /*白名单*/
    @XmlElement(name = "yor:IsInWhiteList")
    private String isInWhiteList;

    @XmlElement(name = "yor:Z_PARENT_ACCOUNT")
    private String z_PARENT_ACCOUNT;

    @XmlElement(name = "a3p:FromPortal")
    private String fromportal="";

    @XmlElement(name = "a3p:IntegrationCheck")
    private String integrationcheck="";
}
