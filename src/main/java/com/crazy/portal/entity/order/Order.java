package com.crazy.portal.entity.order;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author Shawn
 * @date 2019-09-14 14:43::30
 */
@Data
public class Order {

    private Integer id;

    /**
     * 下单类型
     */
    @NotBlank(message = "请选择下单类型")
    private String underOrderType;

    /**
     * 销售组织主键
     */
    @NotNull(message = "请选择销售组织")
    private Integer salesOrgId;

    /**
     * 售达方(代理商的外部编号)
     */
    private String soldParty;

    /**
     * 送达方(代理商的外部编号)
     */
    private String shipParty;

    /**
     * 采购订单编号
     */
    @NotBlank(message = "请输入订单编号")
    private String purchaseOrderNo;

    /**
     * 采购订单下达日期
     */
    @NotNull(message = "请选择订单日期")
    @JSONField(format="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date purchaseOrderDate;

    /**
     * 客户属性(B1-Account Market；B2-Mass Market)
     */
    @NotBlank(message = "请选择客户属性")
    private String customerAttribute;

    /**
     * 订单类型
     */
    @NotBlank(message = "请选择订单类型")
    private String orderType;

    /**
     * 交货日期
     */
    @NotNull(message = "请选择交货日期")
    @JSONField(format="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date deliveryDate;

    /**
     * 定价日期
     */
    @JSONField(format="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date pricingDate;

    /**
     * 付款条件
     */
    private String payCondition;

    /**
     * 国际贸易条款1
     */
    private String inco1;

    /**
     * 国际贸易条款2
     */
    private String inco2;

    /**
     * 订单状态
     */
    private String orderStatus;

    /**
     * 返回-含税总金额
     */
    private BigDecimal rTotalAmountIncludTax;

    /**
     * 返回-不含税总金额
     */
    private BigDecimal rTotalAmountExcludeTax;

    /**
     * 返回-成功标识
     */
    private String rSuccessFlag;

    /**
     * 返回-报错消息
     */
    private String rErrorMsg;

    /**
     * 审批状态  0：未审批 1：已通过 2：已驳回
     */
    private Integer approvalStatus;
    /**
     * 驳回意见
     */
    private String rejectReason;

    /**
     * 创建人
     */
    @JSONField(serialize = false)
    private Integer createId;

    /**
     * 创建时间
     */
    @JSONField(serialize = false)
    private Date createTime;

    /**
     * 更新人
     */
    @JSONField(serialize = false)
    private Integer updateId;
    /**
     * 更新时间
     */
    @JSONField(serialize = false)
    private Date updateTime;
    /**
     * 订单行集合
     */
    private List<OrderLine> lines;

}