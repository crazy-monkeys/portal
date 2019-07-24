package com.crazy.portal.entity.customer;

import lombok.Data;

import java.util.Date;

@Data
public class CustVisitingRecord {
    private Long id;

    private String customerName;

    private String projectName;

    private String remark;

    private String annex;

    private Long createId;

    private Date createTime;

    private Long modifyId;

    private Date modifyTime;


}