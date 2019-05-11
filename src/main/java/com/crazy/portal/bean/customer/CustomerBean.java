package com.crazy.portal.bean.customer;

import lombok.Data;

/**
 * @ClassName: CustomerBean
 * @Author: God Man Qiu~
 * @Date: 2019/4/20 01:49
 */
@Data
public class CustomerBean {
    private Integer customerId;

    private String customerCode;

    private String customerName;

    private String productLine;

    private String region;

    private String taxCode;

    private Integer parentCustomer;
}
