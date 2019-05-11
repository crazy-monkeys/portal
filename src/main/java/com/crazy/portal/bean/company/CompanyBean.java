package com.crazy.portal.bean.company;

import lombok.Data;

/**
 * @ClassName: CompanyBean
 * @Author: God Man Qiu~
 * @Date: 2019/4/19 14:13
 */
@Data
public class CompanyBean {
    private Integer companyId;

    private String companyName;

    private String parentCompanyName;

    private String region;

    private String city;

    private String province;

    private String district;

    private String businessLicenseAddress;

    private String businessEmailAddress;

    private String countryCode;

    private String stateNumber;

    private String phoneNumber;

    private String extension;

    private String corporateLegalPerson;

    private String postalCode;

    private String companyEstablishedTime;

    private String businessTerm;

    private Long registeredFunds;

    private String currency;

    private String companyWebsite;

    private String businessRegistrationNumber;

    private String taxCode;

    private String companyIntroduction;
}
