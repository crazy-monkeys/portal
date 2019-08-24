package com.crazy.portal.entity.price;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author Bill
 * @date   2019-08-19 22:14::33
 */
@Data
public class ActualPrice {
    /**
     * 序号
     */
    private Integer id;

    /**
     * 状态
     */
    private String status;

    /**
     * 内部客户编码
     */
    private String inCustomerCode;

    /**
     * 内部客户
     */
    private String inCustomer;

    /**
     * 外部客户编码
     */
    private String outCustomerCode;

    /**
     * 外部客户
     */
    private String outCustomer;

    /**
     * 
     */
    private String bu;

    /**
     * 
     */
    private String pdt;

    /**
     * 产品型号
     */
    private String productModel;

    /**
     * 实际价格
     */
    private BigDecimal actualPrice;

    /**
     * 数量上限
     */
    private Integer upperNumber;

    /**
     * 数量下限
     */
    private Integer lowerNumber;

    /**
     * 结算类型
     */
    private String settlementType;

    /**
     * 生效时间
     */
    @JSONField(format="yyyy-MM-dd")
    private Date effectTime;

    /**
     * 失效时间
     */
    @JSONField(format="yyyy-MM-dd")
    private Date deadTime;

    /**
     * 关联外部客户号
     */
    private String relationOutCustomerCode;

    /**
     * 关联外部客户
     */
    private String relationOutCustomer;

    /**
     * 关联产品型号
     */
    private String relationProductModel;

    /**
     * 执行方式
     */
    private String implementWay;

    /**
     * 价格申请人
     */
    private String priceProposer;

    /**
     * 出货类型
     */
    private String shipmentType;

    /**
     * 创建时间
     */
    private Date createTime;
}