package com.crazy.portal.entity.system;

import lombok.Data;

import java.util.Date;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 23:32 2019-09-15
 * @Modified by:
 */
@Data
public class OperationLogDO {

    public OperationLogDO(Date createTime) {
        this.createTime = createTime;
    }

    private String operator;
    private String url;
    private String invoke;
    private String businessKey;
    private String errorMsg;
    private Date createTime;
}
