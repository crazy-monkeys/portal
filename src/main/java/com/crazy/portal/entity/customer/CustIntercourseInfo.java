package com.crazy.portal.entity.customer;

import lombok.Data;

import java.util.Date;

@Data
public class CustIntercourseInfo {
    private Long id;

    private String majorSupplier;

    private String majorClient;

    private Long createId;

    private Date createTime;

    private Long modifyId;

    private Date modifyTime;


}