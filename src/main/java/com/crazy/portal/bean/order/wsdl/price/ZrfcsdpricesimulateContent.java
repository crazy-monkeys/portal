package com.crazy.portal.bean.order.wsdl.price;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ZrfcsdpricesimulateContent {

    public ZrfcsdpricesimulateContent() {
    }


    public ZrfcsdpricesimulateContent(EtItems etItems, IsHeader isHeader, ItItems itItems) {
        this.etItems = etItems;
        this.isHeader = isHeader;
        this.itItems = itItems;
    }

    @XmlElement(name = "EtItems")
    private EtItems etItems;

    @XmlElement(name = "IsHeader")
    private IsHeader isHeader;

    @XmlElement(name = "ItItems")
    private ItItems itItems;


}
