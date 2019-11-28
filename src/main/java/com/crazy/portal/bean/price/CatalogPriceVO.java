package com.crazy.portal.bean.price;

import com.crazy.portal.bean.common.PageBean;
import com.crazy.portal.entity.cusotmer.CustomerInfo;
import lombok.Data;

import java.util.List;

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

    private String priceType;

    private String status;

    private String inCustomer;

    private String effectBeginTime;

    private String effectEndTime;

    /**
     * 当前登录人
     */
    private Integer createId;
    private String userType;
    private String proposer;
    private Integer dealerId;
    /**
     * 销售负责的客户
     */
    private List<CustomerInfo> customerInfos;

}
