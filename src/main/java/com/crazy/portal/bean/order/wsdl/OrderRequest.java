package com.crazy.portal.bean.order.wsdl;

import com.crazy.portal.bean.BaseWSDLRequest;
import com.crazy.portal.bean.order.OrderVO;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 20:42 2019-09-08
 * @Modified by:
 */
@XmlRootElement(name="soapenv:Envelope")
@Data
public class OrderRequest extends BaseWSDLRequest {


    @XmlElement(name = "urn:OrderRequest")
    private OrderVO obj;
}
