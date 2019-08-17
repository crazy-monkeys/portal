package com.crazy.portal.entity.business.idr;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BusinessDiffPriceInfo implements IdrBaseEntity{
    private Integer id;

    private Integer idrInfoId;

    private BigDecimal differenceAmount;

    private BigDecimal agentPrice;

    private BigDecimal customerPrice;

    private Integer num;

    private String shipmentDate;

    private String productModel;

    private String platfom;

    private String productType;

    private String pdt;

    private String bu;

    private String customerName;

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