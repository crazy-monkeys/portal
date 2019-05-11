package com.crazy.portal.entity.company;

import lombok.Data;

import java.util.Date;

@Data
public class TContactInformationVO {
    private Integer id;

    private Integer companyId;

    private String contactCode;

    private Short isDefault;

    private String lastName;

    private String firstName;

    private String jobTitle;

    private String officePhone;

    private String postalCode;

    private String countryCode;

    private String stateNumber;

    private String phoneNumber;

    private String extension;

    private String workEmail;

    private Date createTime;

    private Integer createUserId;

    private Date updateTime;

    private Integer updateUserId;

    private Short active;
}