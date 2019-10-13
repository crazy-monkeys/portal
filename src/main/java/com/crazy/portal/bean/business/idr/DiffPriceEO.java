package com.crazy.portal.bean.business.idr;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.crazy.portal.util.DateUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
@Data
public class DiffPriceEO extends BaseRowModel {

    @ExcelProperty(value = "备注", index = 0)
    private String remark;
    @ExcelProperty(value = "差价金额", index = 1)
    private BigDecimal differenceAmount;
    @ExcelProperty(value = "代理费率", index = 2)
    private String agencyRate;
    @ExcelProperty(value = "代理提货单价", index = 3)
    private BigDecimal agentPrice;
    @ExcelProperty(value = "客户提货单价", index = 4)
    private BigDecimal customerPrice;
    @ExcelProperty(value = "数量", index = 5)
    private Integer num;
    @ExcelProperty(value = "出货时间", index = 6)
    private String shipmentDate;
    @ExcelProperty(value = "产品型号", index = 7)
    private String productModel;
    @ExcelProperty(value = "平台", index = 8)
    private String platfom;
    @ExcelProperty(value = "产品类型", index = 9)
    private String productType;
    @ExcelProperty(value = "PDT", index = 10)
    private String pdt;
    @ExcelProperty(value = "BU", index = 11)
    private String bu;
    @ExcelProperty(value = "客户属性", index = 12)
    private String customerAttribute;
    @ExcelProperty(value = "客户", index = 13)
    private String customerName;
    @ExcelProperty(value = "发货公司", index = 14)
    private String shipmentCompany;

    @SuppressWarnings("all")
    public void setShipmentDate(String shipmentDate) {
        this.shipmentDate = DateUtil.getFlexDate(shipmentDate);
    }

}