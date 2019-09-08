package com.crazy.portal.bean.business.rebate;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
public class RebateConfirmBean {

    private List<RebateRecord> rebates;

    @NotNull(message = "请选择执行方")
    private String executor;
    @NotNull(message = "请选择执行方式")
    private String executeStyle;
    /** 备注 **/
    private String remark;

    @Data
    public static class RebateRecord{
        @NotNull(message = "请选择Rebate记录")
        private Integer id;
        @NotNull(message = "请输入释放金额")
        private BigDecimal releaseAmount;
    }
}
