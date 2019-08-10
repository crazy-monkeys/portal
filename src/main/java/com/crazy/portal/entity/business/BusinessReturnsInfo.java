package com.crazy.portal.entity.business;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BusinessReturnsInfo {
    private Integer id;

    private Integer idrInfoId;

    private String replacementDate;

    private BigDecimal amount;

    private BigDecimal price;

    private String currency;

    private Integer num;

    private String productModel;

    private String platform;

    private String pdt;

    private String bu;

    private String productLine;

    private String takeGoodsDate;

    private String orderNumber;

    private String type;

    private String remark;
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