package com.crazy.portal.entity.business.rebate;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BusinessRebateItem {
    private Integer id;

    private Integer rebateId;

    /**
     * 客户简称
     */
    private String customerShortName;
    /**
     * 销售名称
     */
    private String salesName;
    /**
     * 阿米巴队长
     */
    private String amebaHeader;
    /**
     * 阿米巴部门/代表处
     */
    private String amebaDepartment;
    /**
     * 出货公司
     */
    private String shipmentCompany;

    //释放金额
    private BigDecimal rebateAmount;
    //执行方
    private String executor;
    //执行方式
    private String executeStyle;
    //展锐执行日期
    @JSONField(format="yyyy-MM-dd")
    private Date zrExecuteDate;
    //代理执行日期
    @JSONField(format="yyyy-MM-dd")
    private Date dlExecuteDate;
    //展锐上传文件ID
    private Integer zrFileId;
    //代理上传文件ID
    private Integer dlFileId;
    //通知日期
    @JSONField(format="yyyy-MM-dd")
    private Date noticeDate;
    //备注
    private String remark;
    //状态 1-审核中 2-客户待确认 3-客户已确认 4-执行完成
    private Integer status;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer active;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer createId;
    @JsonIgnore
    @JSONField(serialize = false)
    private Date createTime;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer updateId;
    @JsonIgnore
    @JSONField(serialize = false)
    private Date updateTime;

    /**
     * 剩余释放金额
     */
    private BigDecimal surplusRebateAmount;
    /**
     * rebate金额
     */
    private BigDecimal masterRebateAmount;
    /**
     * 执行方内部客户名（客户简称）
     */
    private String executorInName;
}