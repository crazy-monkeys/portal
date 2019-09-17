package com.crazy.portal.bean.customer.wsdl.customer.info;

import lombok.Data;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @ClassName: Customer
 * @Author: God Man Qiu~
 * @Date: 2019/9/15 15:35
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Customer {

    @XmlAttribute(name="actionCode")
    private String actionCode = "01";

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
    private String contactPersonListCompleteTransmissionIndicator = "false";

    @XmlAttribute(name="paymentDataListCompleteTransmissionIndicator")
    private String paymentDataListCompleteTransmissionIndicator = "true";

    @XmlAttribute(name="textListCompleteTransmissionIndicator")
    private String textListCompleteTransmissionIndicator = "true";

    @XmlElement(name = "ObjectNodeSenderTechnicalID")
    private String objectNodeSenderTechnicalID = "01";

    @XmlElement(name = "InternalID")
    private String internalID;

    @XmlElement(name = "CategoryCode")
    private String categoryCode;

    @XmlElement(name = "ProspectIndicator")
    private String prospectIndicator;

    @XmlElement(name = "CustomerIndicator")
    private String customerIndicator;

    @XmlElement(name = "LifeCycleStatusCode")
    private String lifeCycleStatusCode;

    @XmlElement(name = "Organisation")
    private Organisation organisation;

    @XmlElement(name = "AddressInformation")
    private AddressInformation addressInformation;

    @XmlElement(name = "ContactPerson")
    private ContactPerson contactPerson;

    @XmlElement(name = "DirectResponsibility")
    private DirectResponsibility directResponsibility;

    @XmlElement(name = "BlockingReasons")
    private BlockingReasons blockingReasons;

    @XmlElement(name = "Relationship")
    private Relationship relationship;

    @XmlElement(name = "y4r:Abbreviation")
    private String abbreviation;

    @XmlElement(name = "y4r:Type")
    private String type;

    @XmlElement(name = "y4r:IsLicenseAccount")
    private String isLicenseAccount;

    @XmlElement(name = "y4r:AccountRole")
    private String accountRole;

    @XmlElement(name = "y4r:RegistrationDate")
    private String registrationDate;

    @XmlElement(name = "y4r:AdvantagesIntroduction")
    private String advantagesIntroduction;

    @XmlElement(name = "y4r:Businessintroduction")
    private String businessintroduction;

    @XmlElement(name = "y4r:StaffNumber")
    private String staffNumber;

    @XmlElement(name = "y4r:DevelopersNumber")
    private String developersNumber;

    @XmlElement(name = "y4r:CorporateAssets currencyCode=\"CNY\"")
    private String corporateAssets;

    @XmlElement(name = "y4r:BankName")
    private String bankName;

    @XmlElement(name = "y4r:BankAddress")
    private String bankAddress;

    @XmlElement(name = "y4r:Account")
    private String account;

    @XmlElement(name = "y4r:BIC")
    private String bIC;

    @XmlElement(name = "y4r:PurchasingUnit")
    private String purchasingUnit;

    @XmlElement(name = "y4r:ShippingAddress")
    private String shippingAddress;

    @XmlElement(name = "y4r:Phone")
    private String phone;

    @XmlElement(name = "y4r:TaxpayerRegistrationNumber")
    private String taxpayerRegistrationNumber;

    @XmlElement(name = "y4r:Currency")
    private String currency;

    @XmlElement(name = "y4r:IsInWhiteList")
    private String isInWhiteList;

    @XmlElement(name = "y4r:Z_PARENT_ACCOUNT")
    private String z_PARENT_ACCOUNT;
}