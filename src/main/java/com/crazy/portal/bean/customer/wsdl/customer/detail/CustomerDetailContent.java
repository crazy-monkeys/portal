package com.crazy.portal.bean.customer.wsdl.customer.detail;

import com.crazy.portal.bean.customer.wsdl.visits.VisitCreateHeader;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @ClassName: CustomerDetailContent
 * @Author: God Man Qiu~
 * @Date: 2019/9/17 10:05
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerDetailContent {
    public CustomerDetailContent(){

    }

    public CustomerDetailContent(VisitCreateHeader header, CustomerDetail customerDetail){
        this.header = header;
        this.customerDetail = customerDetail;
    }

    @XmlElement(name = "BasicMessageHeader")
    private VisitCreateHeader header;

    @XmlElement(name = "BO_ExtendCustomer")
    private CustomerDetail customerDetail;
}
