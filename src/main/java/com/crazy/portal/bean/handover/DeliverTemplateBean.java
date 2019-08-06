package com.crazy.portal.bean.handover;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * Created by lee on 2019/8/3.
 */

@Data
public class DeliverTemplateBean extends BaseRowModel {

    private String dealerName;

    /** 上传时间 */
    @ExcelProperty(index = 0)
    private String uploadTime;

    /** 客户外部号 */
    @ExcelProperty(index = 1)
    private String customerExternalNumber;

    /** 客户全称 */
    @ExcelProperty(index = 2)
    private String customerFullName;

    /** 销售 */
    @ExcelProperty(index = 3)
    private String sales;

    /** 类别一(类型) */
    @ExcelProperty(index = 4)
    private String categoryOne;

    /** 类别二(子类) */
    @ExcelProperty(index = 5)
    private String categoryTow;

    /** 类别三(平台) */
    @ExcelProperty(index = 6)
    private String categoryThree;

    /** 产品型号 */
    @ExcelProperty(index = 7)
    private String productModel;

    /** 出货日期 */
    @ExcelProperty(index = 8)
    private String deliveryDate;

    /** 数量 */
    @ExcelProperty(index = 9)
    private String deliverNumber;

    /** Sale Price */
    @ExcelProperty(index = 10)
    private String salePrice;

    /** Po Price */
    @ExcelProperty(index = 11)
    private String poPrice;

    /** Margin */
    @ExcelProperty(index = 12)
    private String margin;

    /** 币种 */
    @ExcelProperty(index = 13)
    private String currency;

    /** 客户订单号 */
    @ExcelProperty(index = 14)
    private String customerOrderNumber;

    /** 出货类型 */
    @ExcelProperty(index = 15)
    private String deliveryType;

    /** 订单月份 */
    @ExcelProperty(index = 16)
    private String orderMonth;

    /** 发货公司 */
    @ExcelProperty(index = 17)
    private String deliveryCompany;

    /** 备注 */
    @ExcelProperty(index = 18)
    private String remark;

}
