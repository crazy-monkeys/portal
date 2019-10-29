package com.crazy.portal.bean.rate;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.crazy.portal.util.DateUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class AgencyRateQueryBean {

    @ExcelProperty(value = "客户类型", index = 0)
    private String customerType;
    @ExcelProperty(value = "内部客户", index = 1)
    private String insideCustomer;
    @ExcelProperty(value = "BU", index = 2)
    private String productLevelOne;
    @ExcelProperty(value = "PDT", index = 3)
    private String productLevelTwo;
    @ExcelProperty(value = "Product Type", index = 4)
    private String productLevelThree;
    @ExcelProperty(value = "Platform", index = 5)
    private String productType;
    @ExcelProperty(value = "基准代理费率", index = 6)
    private Float basicAgencyRate;
    @ExcelProperty(value = "浮动代理费率", index = 7)
    private Float floatAgencyRate;
    @ExcelProperty(value = "有效开始时间", index = 8)
    private String validStartDate;
    @ExcelIgnore
    private Integer pageIndex;
    @ExcelIgnore
    private Integer pageSize;
    @ExcelIgnore
    private String startTime;
    @ExcelIgnore
    private String endTime;


    public void setValidStartDate(String validStartDate) {
        this.validStartDate = DateUtil.getFlexDate(validStartDate);
    }
}
