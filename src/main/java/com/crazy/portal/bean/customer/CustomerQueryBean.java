package com.crazy.portal.bean.customer;

import com.crazy.portal.bean.common.PageBean;
import lombok.Data;

@Data
public class CustomerQueryBean extends PageBean {
    //客户名称
    private String custName;
    private String custAbbreviation;
    //客户内部编号
    private String customerInCode;
    //客户外部编号
    private String outCode;
    //是否Lincense客户 1-是 0-否
    private Integer isLicense;
    //业务类型  A01 - Account Market（直供）；A02 - Account Market（非直供）；A03 - Mass Market；A04 - 代理商
    private String businessType;
    private String custRole;
    //报备开始时间
    private String reportStartDate;
    //报备结束时间
    private String reportEndDate;
    //创建开始时间
    private String registStartTime;
    //创建结束时间
    private String registEndTime;
    /* 报备状态1-可报备 2-审批中 3-审批通过 4-审批驳回*/
    private Integer customerStatus;
    /*0-潜在客户 1-未报备 2-已报备 4-OPEN 客户*/
    private Integer custType;

    /*1-客户查询 2-审批查询 3-报备查询*/
    private Integer queryType;
    /**代理商报备查询**/
    private Integer reportDealer;
    /**销售报备查询**/
    private String reportSales;
}
