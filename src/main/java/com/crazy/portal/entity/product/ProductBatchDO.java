package com.crazy.portal.entity.product;

import lombok.Data;

import java.util.Date;

@Data
public class ProductBatchDO {
    private Integer id;

    private String batchSeq;

    private Integer batchStatus;

    private Date insertTime;
}