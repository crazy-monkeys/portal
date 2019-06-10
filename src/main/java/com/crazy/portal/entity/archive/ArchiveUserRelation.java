package com.crazy.portal.entity.archive;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ArchiveUserRelation extends ArchiveUserRelationKey {

    @NotNull(message = "角色ID不能为空")
    private Integer roleId;

    private String userName;

    private String roleName;

}