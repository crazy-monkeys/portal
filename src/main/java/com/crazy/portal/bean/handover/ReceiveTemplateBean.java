package com.crazy.portal.bean.handover;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by lee on 2019/8/3.
 */

@Data
public class ReceiveTemplateBean extends BaseRowModel {

    private String dealerName;

    /** 产品型号 */
    @ExcelProperty(index = 0, value = "产品型号")
    private String productModel;

    /** 库存类别 */
    @ExcelProperty(index = 1, value = "库存类别")
    private String inventoryCategory;

    /** 库存单价 */
    @ExcelProperty(index = 2, value = "库存单价")
    private BigDecimal inventoryUnitPrice;

    /** 仓储地 */
    @ExcelProperty(index = 3, value = "仓储地")
    private String warehouse;

    /** 提货时间 */
    @ExcelProperty(index = 4, value = "提货时间")
    private String deliveryTime;

    /** 提货发票号 */
    @ExcelProperty(index = 5, value = "提货发票号")
    private String invoiceNumber;

    /** 提货数量 */
    @ExcelProperty(index = 6, value = "提货数量")
    private String deliveryNum;

    /** 发货公司 */
    @ExcelProperty(index = 7, value = "发货公司")
    private String deliveryCompany;

    /** 采购单号 */
    @ExcelProperty(index = 8, value = "采购单号")
    private String purchaseNumber;

    /** 备注 */
    @ExcelProperty(index = 9, value = "备注")
    private String remark;

    /** 第三方错误信息错误信息 */
    @ExcelProperty(index = 10, value = "错误信息")
    private String thirdErrorMsg;

    /** 错误信息在portal中的数据ID */
    @ExcelProperty(index = 11, value = "错误ID")
    private String errorId;

}
