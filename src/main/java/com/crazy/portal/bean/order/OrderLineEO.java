package com.crazy.portal.bean.order;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.crazy.portal.util.DateUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
@Data
public class OrderLineEO extends BaseRowModel {

    @ExcelProperty(value = "物料号", index = 0)
    private String materialNumber;
    @ExcelProperty(value = "单价", index = 1)
    private String unitPrice;
    @ExcelProperty(value = "单位", index = 2)
    private String unit;
    @ExcelProperty(value = "数量", index = 3)
    private String num;
    @ExcelProperty(value = "价格", index = 4)
    private String price;

}