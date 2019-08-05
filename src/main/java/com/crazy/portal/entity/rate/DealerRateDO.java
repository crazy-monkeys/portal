package com.crazy.portal.entity.rate;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class DealerRateDO {
    private Integer id;

    private Integer batchId;

    private String custType;

    private String custInCode;

    private String productTypeOne;

    private String productTypeTwo;

    private String productTypeThree;

    private String productType;

    private BigDecimal basicRate;

    private BigDecimal floatRate;

    private String validStart;

    private String validEnd;

    private Integer acitve;

    private Date createTime;

    private Integer createUser;

    private Date updateTime;

    private Integer updateUser;

    //vo
    private Integer batchStatus;
    private String startTime;
    private String endTime;
    private Integer pageIndex;
    private Integer pageSize;
}