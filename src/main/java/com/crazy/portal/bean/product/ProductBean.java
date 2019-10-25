package com.crazy.portal.bean.product;

import lombok.Data;

/**
 * Created by weiying on 2019/7/29.
 */
@Data
public class ProductBean {
    private String BU;
    private String BUHead;
    private String PDT;
    private String Platform;
    private String Product;
    private String SAPMID;
    private String SubCategory;
    private String LifeCycle;
    private String Category;
    private Integer SN;
    private String Manager;
    //1-有  0-没有
    private String PriceFlag;
    private String[] BOMCode;
}
