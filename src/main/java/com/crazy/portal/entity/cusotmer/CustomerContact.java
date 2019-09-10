package com.crazy.portal.entity.cusotmer;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 联系人
 * @author weiying
 * @date   2019-08-29 16:10::00
 */
@Data
public class CustomerContact {
    /**
     * 
     */
    private Integer contactId;

    /**
     * 
     */
    private Integer custId;

    /**
     * 
     */
    private String c4cId;

    /**
     * （customer）C01 - 出货数据确认人(Shipment Data Contact); C02 - Rebate确认人(Rebate Contact);
     */
    private String type;

    /**
     * 姓名
     */
    private String contactName;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 部门
     */
    private String department;

    /**
     * 二级部门
     */
    private String subDepartment;

    /**
     * 职位
     */
    private String position;

    /**
     * 股权占比
     */
    private BigDecimal equityRatio;

    /**
     * 
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date insertTime;

    /**
     * 
     */
    private Integer insertUser;

    /**
     * 
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 
     */
    private Integer updateUser;

    /**
     * 
     */
    private Integer active;
}