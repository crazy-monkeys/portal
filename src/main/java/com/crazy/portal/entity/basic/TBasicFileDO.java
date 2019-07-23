package com.crazy.portal.entity.basic;

import lombok.Data;

import java.util.Date;

/**
 * 客户附件
 */
@Data
public class TBasicFileDO {
    private Integer id;

    private Integer custId;

    private Integer fileType;

    private String fileName;

    private String filePath;

    private Integer createUser;

    private Date createTime;

    private Integer updateUser;

    private Date updateTime;

    private Integer active;
}