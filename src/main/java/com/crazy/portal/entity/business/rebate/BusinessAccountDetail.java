package com.crazy.portal.entity.business.rebate;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BusinessAccountDetail {
    private Integer id;

    private Integer rebateId;

    private String dealerAbbr;

    private String dealerFullName;

    private String customerAbbr;

    private String customerOutCode;

    private String customerType;

    private String ambCaptain;

    private String ambDepartment;

    private String bu;

    private String productModel;

    private String shipmentType;

    private Integer number;

    private BigDecimal salesPrice;

    private BigDecimal poPrice;

    private BigDecimal actualPrice;

    private BigDecimal rebateAmount;

    private String accountYears;

    private String shipmentYears;

    private String shipmentDate;

    private String orderYears;

    private String orderSerialNumber;

    private String priceStrategyNumber;

    private String shipmentCompany;

    private String rebateAccountType;
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