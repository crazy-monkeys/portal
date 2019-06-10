package com.crazy.portal.entity.archive;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ArchiveUserRelationKey {

    @JsonIgnore
    private Integer archiveId;

    @NotNull(message = "用户ID不能为空")
    private Integer userId;

}