package com.crazy.portal.entity.business;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class BusinessFile {
    private Integer id;

    private Integer idrInfoId;

    private String fileName;

    private String filePath;

    private Integer fileType;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer active;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer createUserId;
    @JsonIgnore
    @JSONField(serialize = false)
    private Date createTime;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer updateUserId;
    @JsonIgnore
    @JSONField(serialize = false)
    private Date updateTime;

}