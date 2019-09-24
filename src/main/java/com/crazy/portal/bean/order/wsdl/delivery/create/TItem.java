package com.crazy.portal.bean.order.wsdl.delivery.create;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * @ClassName: TItem
 * @Author: God Man Qiu~
 * @Date: 2019/9/23 00:29
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class TItem {
    @XmlElement(name = "item")
    private List<Item> items;
}
