package com.crazy.portal.entity.price;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 00:41 2019-09-17
 * @Modified by:
 */
@Data
public class CatalogBomsPrice {

    //实体料id
    private String bomId;
    //实体料名称
    private String bomName;
    //内部客户
    private String inCustomer;
    //价格
    private BigDecimal price;
    //数量
    private Integer qty;
    //货币
    private String currency;
}
