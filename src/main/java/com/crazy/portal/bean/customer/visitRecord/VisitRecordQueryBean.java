package com.crazy.portal.bean.customer.visitRecord;

import com.crazy.portal.bean.common.PageBean;
import lombok.Data;

/**
 * @Description 拜访记录列表查询
 * @Author Shawn
 * @Date 2019-08-01 23:11
 * @Modify by
 */
@Data
public class VisitRecordQueryBean extends PageBean{

    //客户名
    private String customerName;
    //项目名
    private String projectName;
    //拜访目的
    private String visitPurpose;
    //拜访开始时间
    private String visitStartDate;
    //拜访结束时间
    private String visitEndDate;

    private Integer userId;
}
