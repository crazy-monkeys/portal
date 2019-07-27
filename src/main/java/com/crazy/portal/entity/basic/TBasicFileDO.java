package com.crazy.portal.entity.basic;

import lombok.Data;

import java.util.Date;

/**
 * 客户附件
 */
@Data
public class TBasicFileDO implements BaseEntity{
    private Integer id;
    //客户ID
    private Integer custId;
    //文件类型
    private Integer fileType;
    //文件名
    private String fileName;
    //文件路径
    private String filePath;

    private Integer active;

    private Integer createUser;

    private Date createTime;

    private Integer updateUser;

    private Date updateTime;


}