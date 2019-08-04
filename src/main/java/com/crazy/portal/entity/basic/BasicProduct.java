package com.crazy.portal.entity.basic;

import lombok.Data;

import java.util.Date;

/**
 * 客户产品信息
 */
@Data
public class BasicProduct {
    private Integer id;

    private Integer custId;

    private String productId;

    private String pdt;

    private String futureSixMonthShipment;

    private Integer active;

    private Integer createUserId;

    private Date createTime;

    private Integer updateUserId;

    private Date updateTime;

}