package com.crazy.portal.entity.rate;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class AgencyRate {
    private Integer id;

    //客户类型
    private String customerType;
    //内部客户
    private String insideCustomer;
    //产品层次一
    private String productLevelOne;
    //产品层次二
    private String productLevelTwo;
    //产品层次三
    private String productLevelThree;
    //产品类型
    private String productType;
    //基准代理费率
    private Float basicAgencyRate;
    //浮动代理费率
    private Float floatAgencyRate;
    //有效开始时间
    private String validStartDate;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer active;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer createUserId;
    @JsonIgnore
    @JSONField(serialize = false)
    private Date createTime;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer updateUserId;
    @JsonIgnore
    @JSONField(serialize = false)
    private Date updateTime;

}