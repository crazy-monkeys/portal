package com.crazy.portal.entity.order;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author Bill
 * @date   2019-09-22 11:46::52
 */
@Data
public class Order {
    /**
     * 
     */
    private Integer id;

    /**
     * 订单类型
     * A01-客户专货订单；
     * A02-Buffer订单；
     * A03-新产品订单；
     * A04-样品订单；
     * A05-Last Buy订单；
     * A06-分销商专货订单
     */
    @NotEmpty
    private String orderType;

    /**
     * 下单类型
     * ZFD-交货免费-不需要做价格模拟。-审批-纸质订单
     * ZOR-标准订单。-审批-纸质订单
     * ZORT-标准订单(ZRET)-必须录入ZRET订单号。-审批-纸质订单
     * ZRET-退货-需要找到原销售订单
     * KB-客户库存补货
     * KE-客户库存出货
     * ZKE-标准客户库存出货
     * ZKB-标准客户库存补货
     */
    @NotEmpty
    private String underOrderType;

    /**
     * 销售组织
     */
    @NotEmpty
    private String salesOrg;

    /**
     * 分销渠道-默认为10
     */
    private String channel = "10";

    /**
     * 产品组-默认为00
     */
    private String division = "00";

    /**
     * 销售部门-默认为0003
     */
    private String salesOffice = "0003";

    /**
     * 销售组-默认为031
     */
    private String salesGroup = "031";

    /**
     * 售达方(代理商的外部编号)
     */
    @NotEmpty
    private String soldTo;

    /**
     * 送达方(代理商的外部编号)
     */
    @NotEmpty
    private String sendTo;

    /**
     * 采购订单编号
     */
    @NotEmpty
    private String purchaseNo;

    /**
     * 采购订单下达日期
     */
    @NotNull
    private Date purchaseDate;

    /**
     * 国际贸易条款1
     */
    @NotEmpty
    private String incoterms1;

    /**
     * 国际贸易条款2
     */
    @NotEmpty
    private String incoterms2;

    /**
     * 定价日期(需求交货日期)
     */
    @NotNull
    private Date priceDate;

    /**
     * 付款条件
     */
    @NotEmpty
    private String paymentTerms;

    /**
     * 客户属性(B1-Account Market；B2-Mass Market)
     */
    @NotEmpty
    private String customerAttr;


    /**
     * 参考订单号(如果是退货ZRET时需要传入)
     */
    private String refSapOrderId;

    /**
     * SAP订单号
     */
    private String rSapOrderId;

    /**
     * 货币
     */
    private String rSapCurrency;

    /**
     * 含税总金额
     */
    private BigDecimal rGrossValue;

    /**
     * 不含税总金额
     */
    private BigDecimal rNetValue;

    /**
     * 订单原因(10:不合理请求)
     */
    private String orderReason;

    /**
     * 审批状态 0：待审批 1：已通过 2：已驳回
     */
    private Integer approvalStatus;

    /**
     * 是否有效(0 失效 1有效)
     */
    private Integer active;

    /**
     * 创建人
     */
    @JSONField(serialize = false)
    private Integer createId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    @JSONField(serialize = false)
    private Integer updateId;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 订单行集合
     */
    private List<OrderLine> lines;
}