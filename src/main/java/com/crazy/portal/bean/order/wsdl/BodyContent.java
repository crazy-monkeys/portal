package com.crazy.portal.bean.order.wsdl;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
   "year",
})
public class BodyContent {

    @XmlElement(required = true,name="eapp:compNo")
    private String year;
}
