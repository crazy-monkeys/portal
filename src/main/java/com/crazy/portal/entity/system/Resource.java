package com.crazy.portal.entity.system;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Resource {
    private Integer id;

    private Short active;

    private Date createTime;

    private Integer createUserId;

    private String iconClass;

    private Integer parentId;

    private String resourceDesc;

    private String resourceName;

    private Integer resourceOrder;

    private Integer resourceType;

    private String resourceUrl;

    private Date updateTime;

    private Integer updateUserId;

    private List<Resource> children;
}