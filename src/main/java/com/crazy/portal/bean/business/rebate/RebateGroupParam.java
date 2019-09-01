package com.crazy.portal.bean.business.rebate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RebateGroupParam {

    /** 代理商简称 **/
    private String agencyShortName;
    /** 客户简称 **/
    private String customerShortName;
    /** 产品型号 **/
    private String product;
    /** 核算年月 **/
    private String accountYearMonth;
    /** 出货年月 **/
    private String shipmentYearMonth;
    /** 平台 **/
    private String platform;

}
