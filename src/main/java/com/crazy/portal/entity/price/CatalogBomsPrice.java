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
    private String bomId;
    private String bomName;
    private String class2;
    private String inCustomer;
    private BigDecimal price;
    private Integer qty;
}
