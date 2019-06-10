package com.crazy.portal.entity.archive;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class Archive {

    @NotNull(message = "文档ID不能为空")
    private Integer id;

    private String thirdFileName;

    private String version;

    private Integer status;

    private String fileStoragePath;
    @JsonIgnore
    private Integer active;

    private String fileSource;

    @JsonIgnore
    private Date createTime;
    @JsonIgnore
    private Date updateTime;
    @JsonIgnore
    private Integer releaseUserId;

    private String releaseFileName;
    @NotNull(message = "发布简介[releaseIntroduction]不能为空")
    private String releaseIntroduction;

    @NotNull(message = "操作方式[operationalMode]不能为空")
    @Range(max = 1, min = 0, message = "请输入有效的参数值operationalMode:[0,1]")
    private Integer operationalMode;
    @JsonIgnore
    private Date releaseTime;

    private String createTimeStr;
    private String releaseTimeStr;

    @Valid
    private List<ArchiveUserRelation> relations;

}