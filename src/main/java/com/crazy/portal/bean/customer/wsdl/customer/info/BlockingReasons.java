package com.crazy.portal.bean.customer.wsdl.customer.info;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @ClassName: BlockingReasons
 * @Author: God Man Qiu~
 * @Date: 2019/9/15 16:04
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class BlockingReasons {
    /*下单冻结*/
    @XmlElement(name = "OrderBlockingReasonCode")
    private String orderBlockingReasonCode;

    /*出货冻结*/
    @XmlElement(name = "DeliveryBlockingReasonCode")
    private String deliveryBlockingReasonCode;

    /*发货冻结*/
    @XmlElement(name = "BillingBlockingReasonCode")
    private String billingBlockingReasonCode;

    /*销售支持冻结*/
    @XmlElement(name = "SalesSupportBlockingIndicator")
    private String salesSupportBlockingIndicator;
}
