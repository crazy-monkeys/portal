package com.crazy.portal.entity.price;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author Bill
 * @date   2019-08-19 23:10::14
 */
@Data
public class PriceStrategy {
    /**
     * 序号
     */
    private Integer id;

    /**
     * 状态
     */
    private String status;

    /**
     * 
     */
    private String bu;

    /**
     * 
     */
    private String pdt;

    /**
     * 产品类型
     */
    private String productType;

    /**
     * 平台
     */
    private String platform;

    /**
     * 
     */
    private String productModel;

    /**
     * 数量上限
     */
    private Integer upperNumber;

    /**
     * 数量下限
     */
    private Integer lowerNumber;

    /**
     * 目录价格
     */
    private BigDecimal catalogPrice;

    /**
     * 权限价格
     */
    private BigDecimal permissionPrice;

    /**
     * 底线价格
     */
    private BigDecimal bottomLinePrice;

    /**
     * 标准成本
     */
    private BigDecimal standardCost;

    /**
     * 减值成本
     */
    private Long depreciationCost;

    /**
     * 内部客户
     */
    private String inCustomer;

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
     * 修改时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    /**
     * 产品状态
     */
    private String productStatus;

    /**
     * 产品归属
     */
    private String productBelongTo;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;
}