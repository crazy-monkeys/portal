package com.crazy.portal.bean.customer.wsdl.customer.info;

import com.crazy.portal.bean.customer.wsdl.visits.VisitCreateHeader;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @ClassName: CustomerInfoContent
 * @Author: God Man Qiu~
 * @Date: 2019/9/15 15:33
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerInfoContent {

    public CustomerInfoContent(){}

    public CustomerInfoContent(VisitCreateHeader header, Customer customer){
        this.header = header;
        this.customer = customer;
    }

    @XmlElement(name = "BasicMessageHeader")
    private VisitCreateHeader header;

    @XmlElement(name = "Customer")
    private Customer customer;
}
