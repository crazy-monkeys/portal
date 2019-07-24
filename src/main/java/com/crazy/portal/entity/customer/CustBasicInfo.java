package com.crazy.portal.entity.customer;

import lombok.Data;

import java.util.Date;

@Data
public class CustBasicInfo {
    private Long id;

    private String customerName;

    private String chineseName;

    private String englishName;

    private Integer customerType;

    private Integer salesId;

    private String amb;

    private String companyAssets;

    private Integer workersNumber;

    private String fax;

    private String parentCompany;

    private String companySwitchboard;

    private Date registerTime;

    private String registerProvince;

    private String registerCity;

    private String registerDistrict;

    private String registerAddress;

    private String officeProvince;

    private String officeCity;

    private String officeDistrict;

    private String officeAddress;

    private String businessIntroduction;

    private Short status;

    private Long createId;

    private Date crateTime;

    private Long modifyId;

    private Date modifyTime;


}