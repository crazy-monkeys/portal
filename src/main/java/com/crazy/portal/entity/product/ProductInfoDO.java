package com.crazy.portal.entity.product;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class ProductInfoDO {
    private Integer id;

    private Integer batchId;

    private Integer sn;

    private String product;

    private String bu;

    private String pdt;

    private String category;

    private String lifeCycle;

    private String subCategory;

    private String platform;

    private String buHeader;

    private String manager;

    private String sapMid;

    private String type;

    private String mpq;

    private Integer proStatus;

    private Integer active;

    private Date insertTime;

    private Date updateTime;

    private List<ProductSubDO> subProducts;
    private String subMid;
}