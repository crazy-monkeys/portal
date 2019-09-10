package com.crazy.portal.entity.product;

import lombok.Data;

import java.util.Date;

@Data
public class ProductSubDO {
    private Integer subId;

    private Integer productId;

    private String subMid;

    private String subNumber;

    private Integer active;

    private Date createTime;

    private Date updateTime;
}