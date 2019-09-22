package com.crazy.portal.bean.customer.basic;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName: DealerCreditVO
 * @Author: God Man Qiu~
 * @Date: 2019/9/22 16:09
 */
@Data
public class DealerCreditVO {
    //授信额度初始值
    private BigDecimal credit;
    //授信额度占用值
    private BigDecimal creditUSE;
    //授信额度剩余值
    private BigDecimal creditUnUSE;
}
