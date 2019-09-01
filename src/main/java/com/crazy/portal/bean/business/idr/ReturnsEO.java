package com.crazy.portal.bean.business.idr;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.crazy.portal.util.DateUtil;
import com.crazy.portal.util.StringUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
@Slf4j
@Data
public class ReturnsEO extends BaseRowModel {

    @ExcelProperty(value = "类型", index = 0)
    private String type;
    @ExcelProperty(value = "订单号", index = 1)
    private String orderNumber;
    @ExcelProperty(value = "提货日期", index = 2)
    private String takeGoodsDate;
    @ExcelProperty(value = "产品线", index = 3)
    private String productLine;
    @ExcelProperty(value = "BU", index = 4)
    private String bu;
    @ExcelProperty(value = "PDT", index = 5)
    private String pdt;
    @ExcelProperty(value = "平台", index = 6)
    private String platform;
    @ExcelProperty(value = "产品型号", index = 7)
    private String productModel;
    @ExcelProperty(value = "数量", index = 8)
    private Integer num;
    @ExcelProperty(value = "币种", index = 9)
    private String currency;
    @ExcelProperty(value = "价格", index = 10)
    private BigDecimal price;
    @ExcelProperty(value = "金额", index = 11)
    private BigDecimal amount;
    @ExcelProperty(value = "换货日期", index = 12, format = "yyyy-MM-dd")
    private String replacementDate;
    @ExcelProperty(value = "备注", index = 13)
    private String remark;

    @SuppressWarnings("all")
    public void setTakeGoodsDate(String takeGoodsDate) {
        this.takeGoodsDate = DateUtil.getFlexDate(takeGoodsDate);
    }
    @SuppressWarnings("all")
    public void setReplacementDate(String replacementDate) {
        this.replacementDate = DateUtil.getFlexDate(replacementDate);
    }
}