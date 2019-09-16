package com.crazy.portal.bean.customer.wsdl.customer.info;

import lombok.Data;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @ClassName: Organisation
 * @Author: God Man Qiu~
 * @Date: 2019/9/15 15:42
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Organisation {

    @XmlElement(name = "firstLineName")
    private String FirstLineName;
}
