package com.crazy.portal.entity.order;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author Shawn
 * @date 2019-09-14 14:44::48
 */
@Data
public class OrderLine {

    private Integer id;

    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 物料号
     */
    private String materialNumber;

    /**
     * 订单数量
     */
    private Integer num;

    /**
     * 平台
     */
    private String platform;

    /**
     * 期望交货月份
     */
    private Integer expectedDeliveryMonth;

    /**
     * 期望交货日期
     */
    private Date expectedDeliveryDate;

    /**
     * SAP订单行项目号
     */
    private String rSapOrderLineItemNumber;

    /**
     * 含税价格
     */
    private BigDecimal rPriceIncludTax;

    /**
     * 不含税价格
     */
    private BigDecimal rPriceExcludeTax;

    /**
     * 返回-币种
     */
    private String rCurrency;

    /**
     * 返回-物料号
     */
    private String rMaterialNumber;

    /**
     * 返回-类目
     */
    private String rLineItemCategories;

    /**
     * 关联行号
     */
    private String rRelevanceLineNumber;

    /**
     * 关联物料号
     */
    private String rRelevanceMaterialNumber;

    /**
     * 创建人id
     */
    @JSONField(serialize = false)
    private Integer createId;

    /**
     * 创建时间
     */
    @JSONField(serialize = false)
    private Date createTime;

    /**
     * 修改人id
     */
    @JSONField(serialize = false)
    private Integer updateId;

    /**
     * 修改时间
     */
    @JSONField(serialize = false)
    private Date updateTime;

}