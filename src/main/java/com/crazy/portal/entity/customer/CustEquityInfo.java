package com.crazy.portal.entity.customer;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CustEquityInfo {
    private Long id;

    private String shareholder;

    private BigDecimal proportion;

    private Long createId;

    private Date createTime;

    private Long modifyId;

    private Date modifyTime;


}