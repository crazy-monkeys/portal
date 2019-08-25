package com.crazy.portal.entity.business.idr;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BusinessReturnsInfo implements IdrBaseEntity{
    private Integer id;

    private Integer idrInfoId;
    //换货日期
    private String replacementDate;
    //金额
    private BigDecimal amount;
    //价格
    private BigDecimal price;
    //币种
    private String currency;
    //数量
    private Integer num;
    //产品型号
    private String productModel;
    //平台
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