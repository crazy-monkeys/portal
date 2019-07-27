package com.crazy.portal.bean.customer.basic;

import lombok.Data;

/**
 * @ClassName: InvoiceVO
 * @Author: God Man Qiu~
 * @Date: 2019/7/24 15:30
 */
@Data
public class InvoiceVO {
    private String purchasingUnit;
    private String shippingAddress;
    private String shippingMobile;
    private String taxpayerRegistrationNumber;
    private String currency;
}
