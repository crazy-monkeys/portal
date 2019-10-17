package com.crazy.portal.entity.cusotmer;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 
 * @author weiying
 * @date   2019-08-29 16:10::00
 */
@Data
public class CustCorporateRelationship {
    /**
     * 
     */
    private Integer shipId;

    /**
     * 
     */
    private Integer custId;

    /**
     * 
     */
    private String corporateId;

    /**
     * 
     */
    private String corporateName;

    /**
     * Rebate 确认人 -  C2  出货确认人 -  C1
     */
    private String corporateType;

    /**
     * 
     */
    private Integer createUser;

    /**
     * 
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 
     */
    private Integer updateUser;

    /**
     * 
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 
     */
    private Integer active;
}