package com.crazy.portal.bean.price;

import com.crazy.portal.bean.common.PageBean;
import lombok.Data;

import java.util.Date;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 16:23 2019-08-18
 * @Modified by:
 */
@Data
public class CatalogPriceVO extends PageBean {

    private String bu;

    private String pdt;

    private String platform;

    private String productModel;

    private String productType;

    private String status;

    private String inCustomer;

    private Date effectBeginTime;

    private Date effectEndTime;

}
