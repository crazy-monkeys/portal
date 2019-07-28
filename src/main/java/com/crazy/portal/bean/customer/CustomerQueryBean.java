package com.crazy.portal.bean.customer;

import com.crazy.portal.bean.common.PageBean;
import lombok.Data;

@Data
public class CustomerQueryBean extends PageBean {
    //客户名称
    private String customerName;
    //客户内部编号
    private String customerInCode;
    //客户外部编号
    private String customerOutCode;
    //是否Lincense客户 1-是 0-否
    private Short isLicense;
    //业务类型 1-massMarket 2-accountMarket
    private Short businessType;
    //报备开始时间
    private String reportStartDate;
    //报备结束时间
    private String reportEndDate;
    //创建开始时间
    private String createStartDate;
    //创建结束时间
    private String createEndDate;
    //客户状态 1-待提交 2-待审批 3-正常
    private Integer customerStatus;
    //用户ID
    private Integer userId;

}