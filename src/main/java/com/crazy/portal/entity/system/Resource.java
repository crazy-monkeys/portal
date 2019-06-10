package com.crazy.portal.entity.system;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Resource {
    private Integer id;

    private Short active;

    private String iconClass;

    private Integer parentId;

    private String resourceDesc;

    private String resourceName;

    private Integer resourceOrder;

    private Integer resourceType;

    private String resourceUrl;

    @JsonIgnore
    @JSONField(serialize=false)
    private Date createTime;
    @JsonIgnore
    @JSONField(serialize=false)
    private Integer createUserId;
    @JsonIgnore
    @JSONField(serialize=false)
    private Date updateTime;
    @JsonIgnore
    @JSONField(serialize=false)
    private Integer updateUserId;

    private List<Resource> children = new ArrayList<>();
}