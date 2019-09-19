package com.crazy.portal.bean.customer.wsdl.customer.detail;

import lombok.extern.slf4j.Slf4j;
import javax.xml.bind.annotation.*;

/**
 * @ClassName: CustomerDetailCreate
 * @Author: God Man Qiu~
 * @Date: 2019/9/17 10:08
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "soap:Envelope")
@Slf4j
public class CustomerDetailCreate {
    public CustomerDetailCreate(){}

    public CustomerDetailCreate(CustomerDetailBody body){
        this.body = body;
    }

    @XmlAttribute(name="xmlns:soap")
    private String soap = "http://www.w3.org/2003/05/soap-envelope";

    @XmlAttribute(name="xmlns:glob")
    private String glob = "http://sap.com/xi/SAPGlobal20/Global";

    @XmlElement(name="soap:Body")
    private CustomerDetailBody body;

}
