package com.crazy.portal.bean.business.idr;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.crazy.portal.util.DateUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * 差价模板
 */
@Slf4j
@Data
public class DiffPriceEO extends BaseRowModel {

    @ExcelProperty(value = "发货公司", index = 0)
    private String shipmentCompany;
    @ExcelProperty(value = "客户", index = 1)
    private String customerName;
    @ExcelProperty(value = "客户属性", index = 2)
    private String customerAttribute;
    @ExcelProperty(value = "BU", index = 3)
    private String bu;
    @ExcelProperty(value = "PDT", index = 4)
    private String pdt;
    @ExcelProperty(value = "Product Type", index = 5)
    private String productType;
    @ExcelProperty(value = "Platfom", index = 6)
    private String platfom;
    @ExcelProperty(value = "产品型号", index = 7)
    private String productModel;
    @ExcelProperty(value = "出货时间", index = 8)
    private String shipmentDate;
    @ExcelProperty(value = "数量", index = 9)
    private Integer num;
    @ExcelProperty(value = "客户提货单价", index = 10)
    private BigDecimal customerPrice;
    @ExcelProperty(value = "代理提货单价", index = 11)
    private BigDecimal agentPrice;
    @ExcelProperty(value = "代理费率", index = 12)
    private String agencyRate;
    @ExcelProperty(value = "差价金额", index = 13)
    private BigDecimal differenceAmount;
    @ExcelProperty(value = "备注", index = 14)
    private String remark;

    @SuppressWarnings("all")
    public void setShipmentDate(String shipmentDate) {
        this.shipmentDate = DateUtil.getFlexDate(shipmentDate);
    }

}