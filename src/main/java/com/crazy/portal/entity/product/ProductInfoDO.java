package com.crazy.portal.entity.product;

import lombok.Data;

import java.util.Date;

@Data
public class ProductInfoDO {
    private Integer id;

    private Integer sn;

    private String product;

    private String bu;

    private String pdt;

    private String category;

    private String subCategory;

    private String platform;

    private String buHeader;

    private String manager;

    private String sapMid;

    private String type;

    private Integer proStatus;

    private Integer active;

    private Date insertTime;

    private Date updateTime;
}