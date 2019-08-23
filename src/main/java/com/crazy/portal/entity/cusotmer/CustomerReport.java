package com.crazy.portal.entity.cusotmer;

import lombok.Data;
import java.util.Date;

@Data
public class CustomerReport {
    private Integer repId;

    private Integer reportDealer;

    private Integer reportSales;

    private Integer customerId;

   /* 报备状态 0-初始化 1-已报备 2-可报备 3-报备中*/
    private Integer reportStatus;

    private Date reportTime;

    private Integer approveUser;

    private String approveRemark;

    private Date approveTime;

    private Date validTime;

    private Date invalidTime;

    private Integer active;

}