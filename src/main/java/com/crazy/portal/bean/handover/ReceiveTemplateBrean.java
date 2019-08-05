package com.crazy.portal.bean.handover;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * Created by lee on 2019/8/3.
 */

@Data
public class ReceiveTemplateBrean extends BaseRowModel {

    /** 上传时间 */
    @ExcelProperty(index = 0)
    private String uploadTime;

    /** 产品型号 */
    @ExcelProperty(index = 1)
    private String productModel;

    /** 库存类别 */
    @ExcelProperty(index = 2)
    private String inventoryCategory;

    /** 库存单价 */
    @ExcelProperty(index = 3)
    private String inventoryUnitPrice;

    /** 仓储地 */
    @ExcelProperty(index = 4)
    private String warehouse;

    /** 提货时间 */
    @ExcelProperty(index = 5)
    private String deliveryTime;

    /** 提货发票号 */
    @ExcelProperty(index = 6)
    private String invoiceNumber;

    /** 提货数量 */
    @ExcelProperty(index = 7)
    private String deliveryNum;

    /** 发货公司 */
    @ExcelProperty(index = 8)
    private String deliveryCompany;

    /** 采购单号 */
    @ExcelProperty(index = 9)
    private String purchaseNumber;

    /** 备注 */
    @ExcelProperty(index = 10)
    private String remark;

}
