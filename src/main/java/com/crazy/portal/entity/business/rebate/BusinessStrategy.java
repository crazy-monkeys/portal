package com.crazy.portal.entity.business.rebate;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BusinessStrategy {
    private Integer id;

    private Integer rebateId;

    private String customerOutCode;

    private String customerFullName;

    private String customerInCode;

    private String customerAbbr;

    private String productMode;

    private Date validStartDate;

    private Date validEndDate;

    private BigDecimal actualPrice;

    private BigDecimal salesLowerLimit;

    private BigDecimal salesUpperLimit;

    private String accountType;

    private String rebateExecuteStyle;

    private String priceApplicant;

    private String shipmentType;

    private String companyExternalCode;

    private String companyFullName;

    private String product;

    private String priceStrategyNumber;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer active;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer createId;
    @JsonIgnore
    @JSONField(serialize = false)
    private Date createTime;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer updateId;
    @JsonIgnore
    @JSONField(serialize = false)
    private Date updateTime;

}