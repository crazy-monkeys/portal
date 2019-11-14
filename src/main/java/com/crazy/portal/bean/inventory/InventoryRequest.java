package com.crazy.portal.bean.inventory;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 00:11 2019-11-15
 * @Modified by:
 */
@Getter
@Setter
public class InventoryRequest {

    /**
     * 年月（版本数据Tag）
     */
    private String sYearMonth;
    /**
     * 代理简称(内部客户)
     */
    private String sAgencyShortName;
    /**
     * BU
     */
    private String sBU;
    /**
     * PDT
     */
    private String sPDT;
    /**
     * Product Type
     */
    private String sProductType;
    /**
     * Platform
     */
    private String sPlatform;
    /**
     * 产品型号
     */
    private String sProduct;
    /**
     * 客户属性
     */
    private String sCustomerType;
    /**
     * 库存类别
     */
    private String sInventoryType;
    /**
     * 发货公司
     */
    private String sCompany;
    /**
     * 期末库存周期
     */
    private String sMonthEndInventoryPeriod;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("?sYearMonth='").append(sYearMonth);
        sb.append("&sAgencyShortName='").append(sAgencyShortName);
        sb.append("&sBU='").append(sBU);
        sb.append("&sPDT='").append(sPDT);
        sb.append("&sProductType='").append(sProductType);
        sb.append("&sPlatform='").append(sPlatform);
        sb.append("&sProduct='").append(sProduct);
        sb.append("&sCustomerType='").append(sCustomerType);
        sb.append("&sInventoryType='").append(sInventoryType);
        sb.append("&sCompany='").append(sCompany);
        sb.append("&sMonthEndInventoryPeriod='").append(sMonthEndInventoryPeriod);
        return sb.toString();
    }
}
