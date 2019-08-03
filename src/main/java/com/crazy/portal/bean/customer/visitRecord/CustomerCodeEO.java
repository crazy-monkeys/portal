package com.crazy.portal.bean.customer.visitRecord;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.ToString;

/**
 * @Description 客户CODE信息
 * @Author Shawn
 * @Date 2019-08-01 22:27
 * @Modify by
 */
@Data
@ToString
public class CustomerCodeEO extends BaseRowModel {

    @ExcelProperty(value = "客户编号", index = 0)
    private String customerCode;
    @ExcelProperty(value = "客户名称", index = 1)
    private String customerName;

}
