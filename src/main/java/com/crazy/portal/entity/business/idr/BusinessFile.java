package com.crazy.portal.entity.business.idr;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class BusinessFile implements IdrBaseEntity{
    private Integer id;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer idrInfoId;

    private String fileName;

    private String filePath;

    private Integer fileType;

    @JsonIgnore
    @JSONField(serialize = false)
    private Integer createId;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonIgnore
    @JSONField(serialize = false)
    private Integer updateId;
    @JsonIgnore
    @JSONField(serialize = false)
    private Date updateTime;

}