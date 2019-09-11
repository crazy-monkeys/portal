package com.crazy.portal.bean.order.wsdl.price;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 23:21 2019-09-11
 * @Modified by:
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class ItItems {

    private List<ItItem> item;
}
