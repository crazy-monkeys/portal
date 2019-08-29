package com.crazy.portal.bean.business.rebate;

import com.alibaba.fastjson.annotation.JSONField;
import com.crazy.portal.bean.common.PageBean;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class RebateQueryBean extends PageBean {

    //代理名称
    private String dealerName;
    //客户名称
    private String customerName;
    //状态
    private Integer status;
    //执行方
    private String executor;
    //执行方式
    private String executeStyle;
    //通知开始日期
    private String noticeBeginDate;
    //通知结束日期
    private String noticeEndDate;

}
