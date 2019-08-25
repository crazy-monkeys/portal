package com.crazy.portal.entity.cusotmer;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AssetsInformation {
    private Integer asseteInfoId;

    private Integer custId;

    private String assetsYear;

    private String assetsSeason;

    private BigDecimal assetsTotal;

    private BigDecimal assetsNet;

    private BigDecimal revenue;

    private Integer totalStaff;

    private Date insertTime;

    private Integer active;
}