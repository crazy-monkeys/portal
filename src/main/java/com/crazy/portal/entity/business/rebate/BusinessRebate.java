package com.crazy.portal.entity.business.rebate;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class BusinessRebate {
    private Integer id;
    //代理商名称
    private String dealerName;
    //客户名称
    private String customerName;
    //核算年月
    private String accountYearMonth;
    //出货年月
    private String shipmentYearMonth;
    //产品型号
    private String product;
    //平台
    private String platform;
    //rebate金额
    private BigDecimal rebateAmount;
    //释放金额
    private BigDecimal releaseAmount;
    //剩余释放金额
    private BigDecimal surplusRebateAmount;
    //状态 1-已提交 2-已结束
    private Integer status;

    private String dealerCode;

    private String customerCode;
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


    List<BusinessSalesDetail> salesDetails;

    List<BusinessPriceRole> priceRoles;

    List<BusinessRebateItem> itemList;
}