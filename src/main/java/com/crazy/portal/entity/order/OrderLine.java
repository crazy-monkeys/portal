package com.crazy.portal.entity.order;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author Bill
 * @date   2019-09-22 11:57::14
 */
@Data
public class OrderLine {
    /**
     * 
     */
    private Integer id;

    /**
     * 订单主键ID
     */
    private Integer orderId;

    /**
     * 虚拟物料号
     */
    @NotEmpty(message = "虚拟物料号不能为空")
    private String productId;

    /**
     * 单位
     */
    private String unit;

    /**
     * 数量
     */
    @NotNull(message = "数量不能为空")
    private Integer num;

    /**
     * 剩余数量
     */
    private Integer remainingNum;

    /**
     * 平台
     */
    @NotEmpty(message = "平台不能为空")
    private String platform;

    /**
     * 期望交货月份
     */
    private String expectedDeliveryMonth;

    /**
     * 期望交货日期
     */
    private String expectedDeliveryDate;

    /**
     * sap订单号
     */
    private String rSapOrderId;

    /**
     * 序号
     */
    private String rSequenceNo;

    /**
     * sap订单行号
     */
    private String rItemNo;

    /**
     * 物料号
     */
    private String rProductId;

    /**
     * 含税价格
     */
    private BigDecimal rPrice;

    /**
     * 不含税价格
     */
    private BigDecimal rNetPrice;

    /**
     * 数量
     */
    private Integer rSapQty;

    /**
     * 单位
     */
    private String rCondUnit;

    /**
     * 币种
     */
    private String rCurrency;
    /**
     * 行项目类别(TAP:虚拟物料 ZTAN: 实体物料)
     */
    private String rItemCategory;

    /**
     * 关联行号
     */
    private String rRefItemNo;

    /**
     * 关联物料号
     */
    private String rRefItemProductId;

    /**
     * 返回平台
     */
    private String rPlatform;

    /**
     * 是否有效(0:失效  1 有效)
     */
    private Integer actice;

    /**
     * 创建人
     */
    @JSONField(serialize = false)
    private Integer createId;

    /**
     * 创建时间
     */
    @JSONField(serialize = false)
    private Date createTime;

    /**
     * 修改人
     */
    @JSONField(serialize = false)
    private Integer updateId;

    /**
     * 更新时间
     */
    @JSONField(serialize = false)
    private Date updateTime;


    /**
     * 客户简称
     */
    private String custAbbreviation;


    /**
     * 扩展字段 供显示用
     */
    private String product;
    private String bu;
    private String pdt;

    public String getProductIDAndPlatform(){
        return this.productId + this.platform;
    }
}