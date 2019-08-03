package com.crazy.portal.bean.handover;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * Created by lee on 2019/8/3.
 */

@Data
public class DeliverTemplateBean extends BaseRowModel {

    @ExcelProperty(index = 0)
    private String customerName;

    @ExcelProperty(index = 1)
    private String projectType;

    @ExcelProperty(index = 2)
    private String remark;

}
