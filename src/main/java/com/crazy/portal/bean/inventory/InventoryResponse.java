package com.crazy.portal.bean.inventory;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 00:14 2019-11-15
 * @Modified by:
 */
@Data
public class InventoryResponse {

    /**
     *年月（版本数据Tag）
     */
    private String year_month;
    /**
     *代理简称(内部客户)
     */
    private String agency_short_name;
    /**
     *BU
     */
    private String product_line;
    /**
     *PDT
     */
    private String sub_product_line;
    /**
     *Product Type
     */
    private String class2;
    /**
     *Platform
     */
    private String class3;
    /**
     *产品型号
     */
    private String product;
    /**
     *客户属性
     */
    private String customer_type;
    /**
     *库存类别
     */
    private String inventory_type;
    /**
     *库存单价
     */
    private BigDecimal inventory_price;
    /**
     *发货公司
     */
    private String company;
    /**
     *销售组织
     */
    private String sales_org;


    /************************************************/
    /**
     *期初库存数量合计
     */
    private String monthly_init_total_qty;
    /**
     *期初金额合计
     */
    private BigDecimal monthly_init_total_amount;
    /**
     *期末库存数量合计
     */
    private String monthly_end_total_qty;
    /**
     *期末金额合计
     */
    private BigDecimal monthly_end_total_amount;




    /***********************************************/
    /**
     * 期初库存数量
     */
    private Integer monthly_init_qty;

    /**
     * 代理提货时间
     */
    private String agency_pick_up_date;

    /**
     * 提货数量
     */
    private Integer agency_pick_up_qty;

    /**
     * 出货数量
     */
    private Integer sales_qty;

    /**
     * 期末库存数量
     */
    private Integer monthly_end_qty;

    /**
     * 期末库存周期
     */
    private BigDecimal monthly_end_inventory_period;

    /**
     * 金额（期末金额）
     */
    private BigDecimal inventory_total_amount;

}
