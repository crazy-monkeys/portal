package com.crazy.portal.bean.customer.wsdl.customer.info;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @ClassName: CustomerInfoBody
 * @Author: God Man Qiu~
 * @Date: 2019/9/15 15:31
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "soap:Body")
public class CustomerInfoBody {

    public CustomerInfoBody(){}

    public CustomerInfoBody(CustomerInfoContent content){
        this.content = content;
    }

    @XmlElement(name = "glob:CustomerBundleMaintainRequest_sync_V1")
    private CustomerInfoContent content;
}
