package com.crazy.portal.entity.handover;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import java.math.BigDecimal;

/**
 * 
 * @author lee
 * @date   2019-08-24 23:05::42
 */

@Data
public class DeliverDetail extends BaseRowModel {

    private Integer id;

    @ExcelProperty(index = 0, value = "代理全称")
    private String dealerName;

    /** 客户外部号 */
    @ExcelProperty(index = 1, value = "客户外部号")
    private String customerExternalNumber;

    /** 客户全称 */
    @ExcelProperty(index = 2, value = "客户全称")
    private String customerFullName;

    /** 销售 */
    @ExcelProperty(index = 3, value = "销售")
    private String sales;

    /** 类别一(类型) */
//    @ExcelProperty(index = 4, value = "类别一(类型)")
    private String categoryOne;

    /** 类别二(子类) */
    @ExcelProperty(index = 4, value = "类别二(子类型)")
    private String categoryTow;

    /** 类别三(平台) */
    @ExcelProperty(index = 5, value = "类别三(平台)")
    private String categoryThree;

    /** 产品型号 */
    @ExcelProperty(index = 6, value = "产品型号")
    private String productModel;

    /** 出货日期 */
    @ExcelProperty(index = 7, value = "出货日期")
    private String deliveryDate;

    /** 数量 */
    @ExcelProperty(index = 8, value = "数量")
    private String deliverNumber;

    /** Sale Price */
    @ExcelProperty(index = 9, value = "Sales Price")
    private BigDecimal salePrice;

    /** Po Price */
    @ExcelProperty(index = 10, value = "Po Price")
    private BigDecimal poPrice;

    /** Margin */
    @ExcelProperty(index = 11, value = "margin")
    private BigDecimal margin;

    /** 币种 */
//    @ExcelProperty(index = 13, value = "币别")
    private String currency;

    @ExcelProperty(index = 12, value = "销售组织")
    private String salesOrganization;

    /** 客户订单号 */
    @ExcelProperty(index = 13, value = "客户订单号")
    private String customerOrderNumber;

    /** 出货类型 */
    @ExcelProperty(index = 14, value = "出货类型")
    private String deliveryType;

    /** 订单月份 */
    @ExcelProperty(index = 15, value = "订单月份")
    private String orderMonth;

    /** 发货公司 */
    @ExcelProperty(index = 16, value = "发货公司")
    private String deliveryCompany;

    /** 备注 */
    @ExcelProperty(index = 17, value = "备注")
    private String remark;

    @ExcelProperty(index = 18, value = "错误信息")
    private String errorMsg;

    @ExcelProperty(index = 19, value = "ID")
    private String thirdId;

    private Integer recordId;

}