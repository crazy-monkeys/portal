package com.crazy.portal.entity.customer;

import lombok.Data;

import java.util.Date;


/**
 * 客户关系
 */
@Data
public class CustRelation {
    private Long id;

    private String name;

    private String relationType;

    private Long createId;

    private Date createTime;

    private Long modifyId;

    private Date modifyTime;


}