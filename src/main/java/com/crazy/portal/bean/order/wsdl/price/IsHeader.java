package com.crazy.portal.bean.order.wsdl.price;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 23:17 2019-09-11
 * @Modified by:
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class IsHeader {

    /**
     * portal订单主键ID
     */
    @XmlElement(name = "Portalorderid")
    private String portalorderid = "";
    /**
     * 订单类型
     */
    @XmlElement(name = "Ordertype")
    private String ordertype = "";
    /**
     * 销售组织 --代理商选择销售组织
     */
    @XmlElement(name = "Salesorg")
    private String salesorg = "";
    /**
     * 分销渠道 默认为10
     */
    @XmlElement(name = "Channel")
    private String channel = "10";
    /**
     * 产品组 默认为00
     */
    @XmlElement(name = "Division")
    private String division = "00";
    /**
     * 销售部门 默认为0003
     */
    @XmlElement(name = "Salesoffice")
    private String salesoffice = "0003";
    /**
     * 销售组 默认为031
     */
    @XmlElement(name = "Salesgroup")
    private String salesgroup = "031";
    /**
     * 售达方 --代理商外部编号
     */
    @XmlElement(name = "Soldto")
    private String soldto = "";
    /**
     * 送达方 --代理商外部编号
     */
    @XmlElement(name = "Sendto")
    private String sendto = "";
    /**
     * 定价日期 格式为yyyy-mm
     */
    @XmlElement(name = "Pricedate")
    private String pricedate = "";
}
