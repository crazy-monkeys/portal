package com.crazy.portal.entity.price;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 目录价格表
 * @author Bill
 * @date   2019-08-18 15:36::19
 */
@Data
public class CatalogPrice {
    /**
     * 记录序号
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
     * 虚拟料号
     */
    private String sapCode;
    /**
     * 平台
     */
    private String platform;

    /**
     * 产品型号
     */
    private String productModel;

    /**
     * 价格类型
     */
    private String priceType;

    /**
     * 价格类型
     */
    private String productType;

    /**
     * 目录价格
     */
    private BigDecimal catalogPrice;

    /**
     * 内部客户
     */
    private String inCustomer;

    /**
     * 生效时间
     */
    private String effectTime;

    /**
     * 失效时间
     */
    private String deadTime;

    /**
     * BOMS
     * @see CatalogBomsPrice
     */
    private JSONArray boms;
    /**
     * 
     */
    private String remark;

    /**
     *
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    /**
     * 创建时间
     */
    private Date createTime;

}