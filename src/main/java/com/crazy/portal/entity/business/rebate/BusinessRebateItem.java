package com.crazy.portal.entity.business.rebate;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BusinessRebateItem {
    private Integer id;

    private Integer rebateId;
    /**
     * 代理商全称
     */
    private String agencyName;

    /**
     * 客户简称
     */
    private String customerShortName;

    /**
     * 客户属性/类别
     */
    private String customerType;

    /**
     * 销售名称
     */
    private String salesName;

    /**
     * 阿米巴队长
     */
    private String amebaHeader;

    /**
     * 阿米巴部门/代表处
     */
    private String amebaDepartment;

    /**
     * 出货公司
     */
    private String shipmentCompany;

    /**
     * 核算年月
     */
    private String accountYearMonth;

    /**
     * 订单年月
     */
    private String orderMonth;

    /**
     * 出货年月
     */
    private String shipmentYearMonth;

    /**
     * BU
     */
    private String bu;

    /**
     * PDT
     */
    private String pdt;

    /**
     * 产品型号
     */
    private String product;

    /**
     * 数量
     */
    private Integer qty;

    /**
     * 销售价格
     */
    private BigDecimal salesPrice;

    /**
     * OLD PRICE
     */
    private BigDecimal poPrice;

    /**
     * 真实价格
     */
    private BigDecimal actualPrice;

    /**
     * 平台
     */
    private String platform;
    //释放金额
    private BigDecimal rebateAmount;
    //执行方
    private String executor;
    //执行方式
    private String executeStyle;
    //通知日期
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date noticeDate;
    //备注
    private String remark;
    //状态 1-审核中 2-客户待确认 3-客户已确认 4-执行完成
    private Integer status;
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