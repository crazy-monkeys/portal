package com.crazy.portal.entity.cust;

import lombok.Data;

@Data
public class TCustomerInfoDOWithBLOBs extends TCustomerInfoDO {
    /**
     *优势价值
     */
    private String advantageValue;

    /**
     * 优势介绍
     */
    private String advantageIntroduction;

    /**
     * 业务介绍
     */
    private String businessIntroduction;
}