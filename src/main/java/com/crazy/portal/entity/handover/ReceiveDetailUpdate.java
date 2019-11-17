package com.crazy.portal.entity.handover;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author lee
 * @date   2019-11-17 15:27::52
 */

@Data
public class ReceiveDetailUpdate {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String thirdId;

    /**
     * 上传时间
     */
    private Date uploadTime;

    /**
     * 产品型号
     */
    private String productModel;

    /**
     * 库存类别
     */
    private String inventoryCategory;

    /**
     * 库存单价
     */
    private BigDecimal inventoryUnitPrice;

    /**
     * 仓储地
     */
    private String warehouse;

    /**
     * 提货时间
     */
    private String deliveryTime;

    /**
     * 提货发票号
     */
    private String invoiceNumber;

    /**
     * 提货数量
     */
    private Integer deliveryNum;

    /**
     * 发货公司
     */
    private String deliveryCompany;

    /**
     * 采购单号
     */
    private String purchaseNumber;

    /**
     * 备注
     */
    private String remark;

    /**
     * 
     */
    private String errorMsg;

    /**
     * 
     */
    private Integer recordId;

    /**
     * 
     */
    private String stockTransferYearMonth;

    /**
     * 
     */
    private String isTransfer;

    /**
     * 
     */
    private String insideCustomerId;

}