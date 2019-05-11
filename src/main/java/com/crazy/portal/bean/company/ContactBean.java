package com.crazy.portal.bean.company;

import lombok.Data;

/**
 * @ClassName: ContactBean
 * @Author: God Man Qiu~
 * @Date: 2019/4/19 16:57
 */
@Data
public class ContactBean {
    private String contactCode;

    private Short isDefault;

    private String lastName;

    private String firstName;

    private String jobTitle;

    private String postalCode;

    private String countryCode;

    private String stateNumber;

    private String phoneNumber;

    private String extension;

    private String workEmail;
}
