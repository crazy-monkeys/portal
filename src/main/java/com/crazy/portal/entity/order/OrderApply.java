package com.crazy.portal.entity.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author Bill
 * @date   2019-09-25 01:20::22
 */
@Data
public class OrderApply {

    @JSONField(serialize = false)
    private MultipartFile lineFile;

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
    @NotEmpty(message = "订单类型不能为空")
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
    @NotEmpty(message = "下单类型不能为空")
    private String underOrderType;

    /**
     * 销售组织
     */
    @NotEmpty(message = "销售组织不能为空")
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
    @NotEmpty(message = "售达方不能为空")
    private String soldTo;

    /**
     * 送达方(代理商的外部编号)
     */
    @NotEmpty(message = "送达方不能为空")
    private String sendTo;

    /**
     * 采购订单编号
     */
    @NotEmpty(message = "采购订单号不能为空")
    private String purchaseNo;

    /**
     * 采购订单下达日期
     */
    @NotEmpty(message = "采购订单下达日期不能为空")
    private String purchaseDate;

    /**
     * 国际贸易条款1
     */
    @NotEmpty(message = "国际贸易条款1不能为空")
    private String incoterms1;

    /**
     * 国际贸易条款2
     */
    @NotEmpty(message = "国际贸易条款2不能为空")
    private String incoterms2;

    /**
     * 定价日期(需求交货日期)
     */
    private String priceDate;

    /**
     * 付款条件
     */
    private String paymentTerms="";

    /**
     * 客户属性(B1-Account Market；B2-Mass Market)
     */
    @NotEmpty(message = "客户属性不能为空")
    private String customerAttr;

    /**
     * 发票类型（1.出口发票 2.增值税普通发票 3.增值税专业发票）
     */
    private Integer invoiceType;

    /**
     * 发票专递类型 1.随货 2.指定(甲方办公地或者详细地址invoice_delivery_address)
     */
    private Integer invoiceDeliveryType;

    /**
     * 指定发票配送地址
     */
    private String invoiceDeliveryAddress;

    /**
     * 是否同意条款
     */
    private Integer isAgreed;

    /**
     * 参考订单号(如果是退货ZRET时需要传入)
     */
    private String refSapOrderId;

    /**
     * 订单原因(10:不合理请求)
     */
    private String orderReason;

    /**
     * 申请代理商ID
     */
    private Integer dealerId;

    /**
     * 申请类型
     * 1.创单申请 2.订单修改 3.订单取消 4.变更交期
     */
    private Integer appalyType;

    /**
     * SAP订单号
     */
    private String rSapOrderId;

    /**
     * 订单行信息
     */
    private JSONArray jsonLines;

    public List<OrderLine> lineJsonToObj(JSONArray lines) {
        return JSONObject.parseArray(lines.toJSONString(), OrderLine.class);
    }
    public JSONArray objToLineJson(List<OrderLine> lines) {
        return JSONArray.parseArray(JSON.toJSONString(lines));
    }

    /**
     * 审批人
     */
    private String approver;

    /**
     * 审批意见
     */
    private String approvalOpinions;

    /**
     * 审批时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date approvalTime;
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
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


    /** ext **/

    /**
     * 申请代理商名称
     */
    private String proposer;

    /**
     * 订单行信息
     */
    private List<OrderLine> orderLines;

}