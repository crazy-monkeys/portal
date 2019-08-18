package com.crazy.portal.bean.business.rebate;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class RebateConfirmBean {

    private Integer id;
    //执行方
    @NotNull(message = "请选择执行方")
    private String executor;
    //释放金额
    @NotNull(message = "请输入释放金额")
    private BigDecimal surplusRebateAmount;
    //执行方式
    @NotNull(message = "请选择执行方式")
    private String executeStyle;
    //备注
    private String remark;
}
