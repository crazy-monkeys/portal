package com.crazy.portal.bean.business.idr;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.crazy.portal.util.DateUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 保价模板
 */
@Slf4j
@Data
public class InsuranceEO extends BaseRowModel {

    @ExcelProperty(value = "发货公司", index = 0)
    private String shipmentCompany;
    @ExcelProperty(value = "客户", index = 1)
    private String customerName;
    @ExcelProperty(value = "收货时间", index = 2)
    private String receiveGoodsDate;
    @ExcelProperty(value = "调价时间", index = 3, format = "yyyy-MM-dd")
    private String adjustDate;
    @ExcelProperty(value = "BU", index = 4)
    private String bu;
    @ExcelProperty(value = "PDT", index = 5)
    private String pdt;
    @ExcelProperty(value = "Product Type", index = 6)
    private String productType;
    @ExcelProperty(value = "Platform", index = 7)
    private String platform;
    @ExcelProperty(value = "产品型号", index = 8)
    private String productModel;
    @ExcelProperty(value = "库存数量", index = 9)
    private String num;
    @ExcelProperty(value = "币种", index = 10)
    private String currency;
    @ExcelProperty(value = "客户属性", index = 11)
    private String customerAttribute;
    @ExcelProperty(value = "代理费率", index = 12)
    private String agencyRate;
    @ExcelProperty(value = "库存价格", index = 13)
    private String price;
    @ExcelProperty(value = "新价格", index = 14)
    private String newPrice;
    @ExcelProperty(value = "保价金额", index = 15)
    private String insuranceAmount;
    @ExcelProperty(value = "调整时间", index = 16)
    private String modifyDate;
    @ExcelProperty(value = "备注", index = 17)
    private String remark;


    @SuppressWarnings("all")
    public void setAdjustDate(String adjustDate) {
        this.adjustDate = DateUtil.getFlexDate(adjustDate);
    }
    @SuppressWarnings("all")
    public void setModifyDate(String modifyDate) {
        this.modifyDate = DateUtil.getFlexDate(modifyDate);
    }

    public void setReceiveGoodsDate(String receiveGoodsDate) {
        this.receiveGoodsDate = DateUtil.getFlexDate(receiveGoodsDate);
    }
}