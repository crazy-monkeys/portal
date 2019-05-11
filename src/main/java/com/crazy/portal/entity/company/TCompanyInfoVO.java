package com.crazy.portal.entity.company;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TCompanyInfoVO {
    private Integer companyId;

    private Integer userId;

    private String companyName;

    private String parentCompanyName;

    private String region;

    private String city;

    private String province;

    private String district;

    private String businessLicenseAddress;

    private String businessEmailAddress;

    private String fixedLineTelephone;

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

    private Date createTime;

    private Integer createUserId;

    private Date updateTime;

    private Integer updateUserId;

    private Short active;

    private List<TContactInformationVO> information;

    private List<TSolutionInfoVO> solution;
}