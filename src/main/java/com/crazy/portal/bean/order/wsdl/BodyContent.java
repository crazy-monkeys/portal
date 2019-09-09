package com.crazy.portal.bean.order.wsdl;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
   "year",
})
@Data
public class BodyContent {

    @XmlElement(required = true,name="eapp:compNo")
    private String year;

}
