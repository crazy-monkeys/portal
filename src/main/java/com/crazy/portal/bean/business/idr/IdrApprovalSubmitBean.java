package com.crazy.portal.bean.business.idr;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class IdrApprovalSubmitBean {

    //单据类型,KP(报价),CP（差价）,TH（退货）,HH（换货）
    private String type;
    //发货方编码
    private String shipperCode;
    //公司
    private String company;
    //币种
    private String currency;
    //内部客户名称
    private String internalCustomer;
    //外部客户名称
    private String externalCustomer;
    //原因
    private String reason;
    //备注
    private String sumRemark;

    List<InsuredPrice> InsuredPriceItem;

    List<RefundPrice> RefundPriceItem;

    List<ExchangeGood> ExchangeGoods;

    List<ReturnGood> ReturnGoods;

    @Data
    public static class InsuredPrice{
        @JSONField(format="yyyy-MM-dd hh:mm:ss")
        private Date deliveryTime;
        @JSONField(format="yyyy-MM-dd hh:mm:ss")
        private Date adjustPriceTime;
        private String BU;
        private String PDT;
        private String productType;
        private String platform;
        private String productModel;
        private Integer inventoryQuantity;
        private String currency;
        private Float inventoryPrice;
        private Float newPrice;
        private Float insured;
        @JSONField(format="yyyy-MM-dd hh:mm:ss")
        private Date adjustTime;
        private String insuredRemark;

    }

    @Data
    public static class RefundPrice{

        private String customer;
        private String BU;
        private String PDT;
        private String productType;
        private String platform;
        private String productModel;
        @JSONField(format="yyyy-MM-dd hh:mm:ss")
        private Date shipmentTime;
        private Integer quantity;
        private Float cusPickPrice;
        private Float agentPickPrice;
        private Float agenceRate;
        private Float differencePrice;
        private String refundRemark;

    }

    @Data
    public static class ReturnGood{

        private String returnBU;
        private String returnPDT;
        private String returnPlatform;
        private String returnProModel;
        private Integer returnQuantity;
        private Float  returnPrice;
        private Float  agenceRate;
        private Float  returnAmount;
        private String returnRemark;

    }

    @Data
    public static class ExchangeGood{

        private String exchangeBU;
        private String exchangePDT;
        private String exchangePlatform;
        private String exchangeProModel;
        private Integer exchangeQuantity;
        private Float  exchangePrice;
        private Float  agenceRate;
        private Float  exchangeAmount;
        private String exchangeRemark;

    }


}
