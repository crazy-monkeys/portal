package com.crazy.portal.entity.handover;

import lombok.Data;
import java.util.Date;

@Data
public class DeliverRecord {
    private Integer id;

    private Integer dealerId;

    private String dealerName;

    private Date deliveryDate;

    private Date uploadTime;

    private Integer status;

    private Date createTime;

    private Integer approvalUserId;

    private Date approvalTime;

}