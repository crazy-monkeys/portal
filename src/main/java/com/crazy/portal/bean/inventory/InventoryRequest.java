package com.crazy.portal.bean.inventory;

import com.crazy.portal.util.StringUtil;
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
        StringBuilder sb = new StringBuilder("?1");
        if(StringUtil.isNotEmpty(sYearMonth)){
            sb.append("?sYearMonth=").append(sYearMonth);
        }
        if(StringUtil.isNotEmpty(sAgencyShortName)){
            sb.append("&sAgencyShortName=").append(sAgencyShortName);
        }
        if(StringUtil.isNotEmpty(sBU)){
            sb.append("&sBU=").append(sBU);
        }
        if(StringUtil.isNotEmpty(sPDT)){
            sb.append("&sPDT=").append(sPDT);
        }
        if(StringUtil.isNotEmpty(sProductType)){
            sb.append("&sProductType=").append(sProductType);
        }
        if(StringUtil.isNotEmpty(sPlatform)){
            sb.append("&sPlatform=").append(sPlatform);
        }
        if(StringUtil.isNotEmpty(sProduct)){
            sb.append("&sProduct=").append(sProduct);
        }
        if(StringUtil.isNotEmpty(sCustomerType)){
            sb.append("&sCustomerType=").append(sCustomerType);
        }
        if(StringUtil.isNotEmpty(sInventoryType)){
            sb.append("&sInventoryType=").append(sInventoryType);
        }
        if(StringUtil.isNotEmpty(sCompany)){
            sb.append("&sCompany=").append(sCompany);
        }
        if(StringUtil.isNotEmpty(sMonthEndInventoryPeriod)){
            sb.append("&sMonthEndInventoryPeriod=").append(sMonthEndInventoryPeriod);
        }
        return sb.toString();
    }
}
