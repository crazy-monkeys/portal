package com.crazy.portal.entity.customer;

import lombok.Data;

import java.util.Date;

@Data
public class TCustomerRegion {
    private Integer id;

    private Integer customerId;

    private Integer userId;

    private String region;

    private Date createTime;

    private Integer createUserId;

    private Date updateTime;

    private Integer updateUserId;

    private Short active;

    private Integer registStatus;

}