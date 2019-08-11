package com.crazy.portal.entity.handover;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.Date;

@Data
public class DeliverDetail extends BaseRowModel {

    private String dealerName;

    private Integer id;
    /** 上传时间 */
    @ExcelProperty(index = 0, value = "上传时间")
    private Date uploadTime;

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
    @ExcelProperty(index = 4, value = "类别一(类型)")
    private String categoryOne;

    /** 类别二(子类) */
    @ExcelProperty(index = 5, value = "类别二(子类)")
    private String categoryTow;

    /** 类别三(平台) */
    @ExcelProperty(index = 6, value = "类别三(平台)")
    private String categoryThree;

    /** 产品型号 */
    @ExcelProperty(index = 7, value = "产品型号")
    private String productModel;

    /** 出货日期 */
    @ExcelProperty(index = 8, value = "出货日期")
    private Date deliveryDate;

    /** 数量 */
    @ExcelProperty(index = 9, value = "数量")
    private Integer deliverNumber;

    /** Sale Price */
    @ExcelProperty(index = 10, value = "Sale Price")
    private Long salePrice;

    /** Po Price */
    @ExcelProperty(index = 11, value = "Po Price")
    private Long poPrice;

    /** Margin */
    @ExcelProperty(index = 12, value = "Margin")
    private String margin;

    /** 币种 */
    @ExcelProperty(index = 13, value = "币种")
    private String currency;

    /** 客户订单号 */
    @ExcelProperty(index = 14, value = "客户订单号")
    private Integer customerOrderNumber;

    /** 出货类型 */
    @ExcelProperty(index = 15, value = "出货类型")
    private Integer deliveryType;

    /** 订单月份 */
    @ExcelProperty(index = 16, value = "订单月份")
    private String orderMonth;

    /** 发货公司 */
    @ExcelProperty(index = 17, value = "发货公司")
    private String deliveryCompany;

    /** 备注 */
    @ExcelProperty(index = 18, value = "备注")
    private String remark;

    private String errorMsg;

    private Integer recordId;

}