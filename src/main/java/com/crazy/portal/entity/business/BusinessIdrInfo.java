package com.crazy.portal.entity.business;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class BusinessIdrInfo {
    private Integer id;
    //发货方编号
    @NotNull(message = "请选择发货方编号")
    private String shipperCode;
    //申请时间
    @NotNull(message = "请选择申请时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date applyTime;
    //币种
    @NotNull(message = "请选择币种")
    private String currency;
    //CR金额
    private BigDecimal crAmount;
    //公司
    private String company;
    //内部客户名
    private String inCustomerName;
    //外部客户ID
    private Integer dealerId;
    //原因
    private String reson;
    //备注
    private String remark;
    //类型 1.保价 2.差价补偿 3.退换货
    @NotNull(message = "请选择保差退类型")
    private Integer type;
    //类型名称
    private String typeName;
    //状态 1：已提交  2：已完结
    private Integer status;


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


    private List<BusinessInsuranceInfo> iList;

    private List<BusinessDiffPriceInfo> dList;

    private List<BusinessReturnsInfo> rList;

    private List<BusinessFile> files;
}