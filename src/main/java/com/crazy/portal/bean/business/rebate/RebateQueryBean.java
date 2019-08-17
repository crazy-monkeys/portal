package com.crazy.portal.bean.business.rebate;

import com.crazy.portal.bean.common.PageBean;
import lombok.Data;

@Data
public class RebateQueryBean extends PageBean {

    //代理名称
    private String dealerName;
    //客户名称
    private String customerName;
    //状态
    private Integer status;
}
