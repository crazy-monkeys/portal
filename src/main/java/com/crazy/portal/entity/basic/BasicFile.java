package com.crazy.portal.entity.basic;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

/**
 * 客户附件
 */
@Data
public class BasicFile implements BaseEntity{
    private Integer id;
    //客户ID
    private Integer custId;
    //文件类型
    private Integer fileType;
    //文件名
    private String fileName;
    //文件路径
    private String filePath;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer active;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer createUser;
    @JsonIgnore
    @JSONField(serialize = false)
    private Date createTime;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer updateUser;
    @JsonIgnore
    @JSONField(serialize = false)
    private Date updateTime;


}