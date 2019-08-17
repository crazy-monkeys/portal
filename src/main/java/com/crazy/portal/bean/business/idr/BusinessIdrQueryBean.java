package com.crazy.portal.bean.business.idr;

import com.crazy.portal.bean.common.PageBean;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BusinessIdrQueryBean extends PageBean {

    //类型 1.保价 2.差价补偿 3.退换货
    private Integer type;
    //发货方编号
    private String shipperCode;
    //公司
    private String company;
    //CR金额
    private BigDecimal crAmount;
    //申请开始时间
    private String applyStartTime;
    //申请结束时间
    private String applyEndTime;
    //币种
    private String currency;
    //内部客户名
    private String inCustomerName;
    //外部客户ID
    private Integer outCustomerName;

}
