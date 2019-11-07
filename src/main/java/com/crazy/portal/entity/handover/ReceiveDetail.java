package com.crazy.portal.entity.handover;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReceiveDetail {
    @ExcelIgnore
    private Integer id;
    @ExcelIgnore
    private Integer deliveryOrderId;

    @ExcelProperty(index = 0, value = "代理全称")
    private String dealerName;

    @ExcelProperty(index = 1, value = "客户分类")
    private String customerType;

    @ExcelProperty(index = 2, value = "平台")
    private String platform;

    /** 产品型号 */
    @ExcelProperty(index = 3, value = "产品型号")
    private String productModel;

    /** 库存类别 */
    @ExcelProperty(index = 4, value = "库存类别")
    private String inventoryCategory;

    /** 库存单价 */
    @ExcelProperty(index = 5, value = "库存单价")
    private BigDecimal inventoryUnitPrice;

    @ExcelProperty(index = 6, value = "销售组织")
    private String salesOrganization;

    /** 仓储地 */
//    @ExcelProperty(index = 4, value = "仓储地")
    @ExcelIgnore
    private String warehouse;

    /** 提货时间  == 代理商点击收货时间 */
    @ExcelProperty(index = 7, value = "代理提货时间")
    private String deliveryTime;

    /** 提货发票号 */
//    @ExcelProperty(index = 6, value = "提货发票号")
    @ExcelIgnore
    private String invoiceNumber;

    /** 提货数量 */
    @ExcelProperty(index = 8, value = "代理提货数量")
    private String deliveryNum;

    /** 发货公司 */
    @ExcelProperty(index = 9, value = "发货公司")
    private String deliveryCompany;

    /** 备注 */
    @ExcelProperty(index = 10, value = "备注")
    private String remark;

    /** 采购单号 */
    @ExcelProperty(index = 11, value = "采购单号")
    private String purchaseNumber;

    @ExcelProperty(index = 12, value = "错误信息")
    private String errorMsg;

    @ExcelProperty(index = 13, value = "ID")
    private Integer thirdId;

    @ExcelIgnore
    private Integer recordId;

}