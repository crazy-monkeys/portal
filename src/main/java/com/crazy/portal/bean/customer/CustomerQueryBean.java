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
    //业务类型  A01 - Account Market（直供）；A02 - Account Market（非直供）；A03 - Mass Market；A04 - 代理商
    private Short businessType;
    //报备开始时间
    private String reportStartDate;
    //报备结束时间
    private String reportEndDate;
    //创建开始时间
    private String createStartDate;
    //创建结束时间
    private String createEndDate;
    /* 报备状态 0-初始化 1-已报备 2-可报备 3-报备中*/
    private Integer customerStatus;
}
