package com.crazy.portal.entity.inventory;

import com.alibaba.fastjson.annotation.JSONField;
import com.crazy.portal.bean.common.PageBean;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 库存转移表
 * @author Bill
 * @date   2019-11-16 17:00::23
 */
@Data
public class InventoryTransferDO extends PageBean {
    /**
     * 
     */
    private Integer id;

    /**
     * 年月（版本数据Tag）
     */
    private String yearMonth;

    /**
     * 代理简称(内部客户)
     */
    private String agencyShortName;

    /**
     * BU
     */
    private String productLine;

    /**
     * PDT
     */
    private String subProductLine;

    /**
     * Product Type
     */
    private String class2;

    /**
     * Platform
     */
    private String class3;

    /**
     * 产品型号
     */
    private String product;

    /**
     * 客户属性
     */
    private String customerType;

    /**
     * 库存类别
     */
    private String inventoryType;

    /**
     * 库存单价
     */
    private BigDecimal inventoryPrice;

    /**
     * 发货公司
     */
    private String company;

    /**
     * 销售组织
     */
    private String salesOrg;

    /**
     * 期初库存数量
     */
    private Integer monthlyInitQty;

    /**
     * 代理提货时间
     */
    private String agencyPickUpDate;

    /**
     * 提货数量
     */
    private Integer agencyPickUpQty;

    /**
     * 出货数量
     */
    private Integer salesQty;

    /**
     * 期末库存数量
     */
    private Integer monthlyEndQty;

    /**
     * 期末库存周期
     */
    private String monthlyEndInventoryPeriod;

    /**
     * 金额（期末金额）
     */
    private BigDecimal inventoryTotalAmount;

    /**
     * 转移-转出客户
     */
    private String transferOutCustomer;

    /**
     * 转移-转入客户
     */
    private String transferIntoCustomer;

    /**
     * 转移-年月
     */
    private String transferYearMonth;

    private Integer approvalStatus;
    /**
     * 
     */
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;

    /**
     * 
     */
    private Integer createId;

    private String createUserName;

    /**
     * 
     */
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date updateTime;

    /**
     * 
     */
    private Integer updateId;


    /** ext **/
    private String applyType;

    private List<Integer> userIds;
}