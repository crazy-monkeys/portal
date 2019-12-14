package com.crazy.portal.entity.order;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 18:59 2019/12/14
 * @Modified by:
 */
@Getter
@Setter
public class PoAdditionalOrderReq {

    @JSONField(name = "portal_id")
    private String portalId;

    @JSONField(name = "year_month")
    private String yearMonth;

    private String company;

    @JSONField(name = "agency_incode")
    private String agencyIncode;

    @JSONField(name = "customer_incode")
    private String customerIncode;

    @JSONField(name = "sap_code")
    private String sapCode;

    private String class3;

    @JSONField(name = "po_price")
    private String poPrice;

    private String qty;
}
