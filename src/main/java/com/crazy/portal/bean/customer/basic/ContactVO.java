package com.crazy.portal.bean.customer.basic;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName: ContactVO
 * @Author: God Man Qiu~
 * @Date: 2019/7/24 15:29
 */
@Data
public class ContactVO {
    private String cName;
    private String cMobile;
    private String cEmail;
    private String cDepartment;
    private String cPosition;
    private Integer isDecisionMaker;
    private BigDecimal equityRatio;
}
