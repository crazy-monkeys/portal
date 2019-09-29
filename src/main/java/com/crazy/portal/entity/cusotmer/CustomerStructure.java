package com.crazy.portal.entity.cusotmer;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author weiying
 * @date   2019-09-02 23:56::47
 */
@Data
public class CustomerStructure {
    /**
     * 
     */
    private Integer structureId;

    /**
     * 
     */
    private Integer custId;

    /**
     * 
     */
    private String strName;

    /**
     * 
     */
    private BigDecimal strValue;

    /**
     * 上级公司
     */
    private String strOne;

    /**
     * 股东性质
     */
    private String strTwo;

    /**
     * 公司性质
     */
    private String strThree;

    /**
     * 是否公司管理层
     */
    private String strFour;

    /**
     * 职务
     */
    private String strFive;

    /**
     * 部门
     */
    private String strSix;

    /**
     * 
     */
    private Integer active;

    /**
     * 
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 
     */
    private Integer createUserId;

    /**
     * 
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 
     */
    private Integer updateUserId;
}