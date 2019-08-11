package com.crazy.portal.bean.business;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class DiffPriceEO extends BaseRowModel {

    @ExcelProperty(value = "备注", index = 0)
    private String remark;
    @ExcelProperty(value = "差价金额", index = 1)
    private BigDecimal differenceAmount;
    @ExcelProperty(value = "代理提货单价", index = 2)
    private BigDecimal agentPrice;
    @ExcelProperty(value = "客户提货单价", index = 3)
    private BigDecimal customerPrice;
    @ExcelProperty(value = "数量", index = 4)
    private Integer num;
    @ExcelProperty(value = "出货时间", index = 5)
    private String shipmentDate;
    @ExcelProperty(value = "产品型号", index = 6)
    private String productModel;
    @ExcelProperty(value = "平台", index = 7)
    private String platfom;
    @ExcelProperty(value = "产品类型", index = 8)
    private String productType;
    @ExcelProperty(value = "PDT", index = 9)
    private String pdt;
    @ExcelProperty(value = "BU", index = 10)
    private String bu;
    @ExcelProperty(value = "客户", index = 11)
    private String customerName;



}