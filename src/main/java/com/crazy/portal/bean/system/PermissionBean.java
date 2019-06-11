package com.crazy.portal.bean.system;

import lombok.Data;

import java.util.List;

@Data
public class PermissionBean {
    private Integer roleId;
    private List<Integer> addPermissionIds;
    private List<Integer> rmPermissionIds;
}
