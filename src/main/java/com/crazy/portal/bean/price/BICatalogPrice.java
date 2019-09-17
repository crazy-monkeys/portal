package com.crazy.portal.bean.price;

import lombok.Data;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 23:00 2019-09-16
 * @Modified by:
 */
@Data
public class BICatalogPrice {

    private String bU;
    private String pDT;
    private String class3;
    private String sap_code;
    private String product;
    private String effective_date;
    private String comments;
    private String active;
    private String price_type;

    //boms
    private String bom_id;
    private String bom_name;
    private String class2;
    private String price;
    private String qty;
    private String customer_incode;



}
