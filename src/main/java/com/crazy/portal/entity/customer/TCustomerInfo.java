package com.crazy.portal.entity.customer;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TCustomerInfo {
    private Integer customerId;

    private String customerCode;

    private String customerName;

    private String productLine;

    private String region;

    private String taxCode;

    private Integer parentCustomer;

    private Date createTime;

    private Integer createUserId;

    private Date updateTime;

    private Integer updateUserId;

    private Short active;

    private List<TCustomerAddress> address;

    private List<TCustomerProject> project;

    private List<TCustomerContacts> contacts;

    private Integer registStatus;

    private Integer registId;
}