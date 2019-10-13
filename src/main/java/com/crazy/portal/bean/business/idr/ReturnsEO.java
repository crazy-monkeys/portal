package com.crazy.portal.bean.business.idr;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.crazy.portal.util.DateUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
@Slf4j
@Data
public class ReturnsEO extends BaseRowModel {

    @ExcelProperty(value = "发货公司", index = 0)
    private String shipmentCompany;
    @ExcelProperty(value = "代理商", index = 1)
    private String agencyName;
    @ExcelProperty(value = "提货日期", index = 2)
    private String takeGoodsDate;

    @ExcelProperty(value = "退货产品线", index = 3)
    private String returnProductLine;
    @ExcelProperty(value = "退货BU", index = 4)
    private String returnBu;
    @ExcelProperty(value = "退货PDT", index = 5)
    private String returnPdt;
    @ExcelProperty(value = "退货平台", index = 6)
    private String returnPlatform;
    @ExcelProperty(value = "退货产品型号", index = 7)
    private String returnProductModel;
    @ExcelProperty(value = "退货数量", index = 8)
    private Integer returnNum;
    @ExcelProperty(value = "退货币种", index = 9)
    private String returnCurrency;
    @ExcelProperty(value = "退货价格", index = 10)
    private BigDecimal returnPrice;
    @ExcelProperty(value = "客户属性", index = 11)
    private String returnCustomerAttribute;
    @ExcelProperty(value = "代理费率", index = 12)
    private String returnAgencyRate;
    @ExcelProperty(value = "退货金额", index = 13)
    private BigDecimal returnAmount;
    @ExcelProperty(value = "退货代理费金额", index = 14)
    private BigDecimal returnRateAmount;


    @ExcelProperty(value = "换货产品线", index = 15)
    private String exchangeProductLine;
    @ExcelProperty(value = "换货BU", index = 16)
    private String exchangeBu;
    @ExcelProperty(value = "换货PDT", index = 17)
    private String exchangePdt;
    @ExcelProperty(value = "换货平台", index = 18)
    private String exchangePlatform;
    @ExcelProperty(value = "换货产品型号", index = 19)
    private String exchangeProductModel;
    @ExcelProperty(value = "换货数量", index = 20)
    private Integer exchangeNum;
    @ExcelProperty(value = "换货币种", index = 21)
    private String exchangeCurrency;
    @ExcelProperty(value = "换货价格", index = 22)
    private BigDecimal exchangePrice;
    @ExcelProperty(value = "换货金额", index = 23)
    private BigDecimal exchangeAmount;
    @ExcelProperty(value = "换货日期", index = 24, format = "yyyy-MM-dd")
    private String exchangeDate;

    @ExcelProperty(value = "备注", index = 25)
    private String remark;

    @SuppressWarnings("all")
    public void setTakeGoodsDate(String takeGoodsDate) {
        this.takeGoodsDate = DateUtil.getFlexDate(takeGoodsDate);
    }
    @SuppressWarnings("all")
    public void setExchangeDate(String exchangeDate) {
        this.exchangeDate = DateUtil.getFlexDate(exchangeDate);
    }
}