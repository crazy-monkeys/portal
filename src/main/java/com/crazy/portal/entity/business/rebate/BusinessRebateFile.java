package com.crazy.portal.entity.business.rebate;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class BusinessRebateFile {
    private Integer id;

    private Integer rebateItemId;

    private String fileName;

    private String filePath;
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

}