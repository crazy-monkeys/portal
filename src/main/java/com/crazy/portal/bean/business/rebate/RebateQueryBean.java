package com.crazy.portal.bean.business.rebate;

import com.crazy.portal.bean.common.PageBean;
import lombok.Data;

/**
 * 列表查询筛选
 */
@Data
public class RebateQueryBean extends PageBean {

    //代理名称
    private String dealerName;
    //客户名称
    private String customerName;
    //状态
    private Integer status;

    //执行方-item
    private String executor;
    //执行方式-item
    private String executeStyle;
    //通知开始日期-item
    private String noticeBeginDate;
    //通知结束日期-item
    private String noticeEndDate;
    //代理商ID
    private Integer dealerId;
}
