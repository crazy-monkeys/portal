package com.crazy.portal.bean.rate;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

@Data
public class AgencyRateQueryBean extends BaseRowModel {

    @ExcelProperty(value = "客户类型", index = 0)
    private String customerType;
    @ExcelProperty(value = "内部客户", index = 1)
    private String insideCustomer;
    @ExcelProperty(value = "产品层次一", index = 2)
    private String productLevelOne;
    @ExcelProperty(value = "产品层次二", index = 3)
    private String productLevelTwo;
    @ExcelProperty(value = "产品层次三", index = 4)
    private String productLevelThree;
    @ExcelProperty(value = "产品类型", index = 5)
    private String productType;
    @ExcelProperty(value = "基准代理费率", index = 6)
    private String basicAgencyRate;
    @ExcelProperty(value = "浮动代理费率", index = 7)
    private String floatAgencyRate;
    @ExcelProperty(value = "有效开始时间", index = 8)
    private String validStartDate;

    private Integer pageIndex;

    private Integer pageSize;

    private String startTime;

    private String endTime;
}
