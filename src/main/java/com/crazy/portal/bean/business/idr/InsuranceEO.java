package com.crazy.portal.bean.business.idr;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.crazy.portal.util.DateUtil;
import com.crazy.portal.util.StringUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
@Data
public class InsuranceEO extends BaseRowModel {

    @ExcelProperty(value = "客户", index = 0)
    private String customerName;
    @ExcelProperty(value = "调价时间", index = 1, format = "yyyy-MM-dd")
    private String adjustDate;
    @ExcelProperty(value = "收货时间", index = 2)
    private String receiveGoodsDate;
    @ExcelProperty(value = "BU", index = 3)
    private String bu;
    @ExcelProperty(value = "PDT", index = 4)
    private String pdt;
    @ExcelProperty(value = "产品类型", index = 5)
    private String productType;
    @ExcelProperty(value = "平台", index = 6)
    private String platform;
    @ExcelProperty(value = "产品型号", index = 7)
    private String productModel;
    @ExcelProperty(value = "库存数量", index = 8)
    private String num;
    @ExcelProperty(value = "库存价格", index = 9)
    private String price;
    @ExcelProperty(value = "币种", index = 10)
    private String currency;
    @ExcelProperty(value = "新价格", index = 11)
    private String newPrice;
    @ExcelProperty(value = "保价金额", index = 12)
    private String insuranceAmount;
    @ExcelProperty(value = "调整时间", index = 13)
    private String modifyDate;
    @ExcelProperty(value = "备注", index = 14)
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