package com.crazy.portal.bean.customer.wsdl.customer.detail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @ClassName: CustomerDetailBody
 * @Author: God Man Qiu~
 * @Date: 2019/9/17 10:07
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "soap:Body")
public class CustomerDetailBody {
    public CustomerDetailBody(){}

    public CustomerDetailBody(CustomerDetailContent content){
        this.content = content;
    }

    @XmlElement(name = "glob:BO_ExtendCustomerUpdateRequest_sync")
    private CustomerDetailContent content;
}
