package com.crazy.portal.bean.customer;

import com.crazy.portal.bean.common.PageBean;
import lombok.Data;

@Data
public class CustomerQueryBean extends PageBean {
    //客户名称
    private String customerName;
    //客户编号
    private String customerCode;
    //客户简称
    private String customerSimpleName;
    //是否Lincense客户
    private Short isLicencse;
    //业务类型
    private String businessType;
    //报备开始时间
    private String reportStartDate;
    //报备结束时间
    private String reportEndDate;
    //创建开始时间
    private String createStartDate;
    //创建结束时间
    private String createEndDate;

}
