package com.crazy.portal.entity.announcement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class Announcement {

    private Integer id;

    @NotNull(message = "title不能为空")
    private String title;

//    @NotNull(message = "请选择是否置顶此公告topmost:[0,1]")
//    @Range(max = 1, min = 0, message = "请输入有效的参数值topmost:[0,1]")
    @JsonIgnore
    private Integer topmost;
    @NotNull(message = "请选择公告类型")
    private Integer typeId;

    private Integer status;
    @JsonIgnore
    private Integer createUserId;
    @JsonIgnore
    private Date createTime;
    @JsonIgnore
    private Date releaseTime;
    @JsonIgnore
    private Date updateTime;
//    @NotNull(message = "content不能为空")
    @JsonIgnore
    private String content;
    //创建人
    private String createUserName;
    //创建时间
    private String createTimeStr;
    //发布时间
    private String releaseTimeStr;

    private String zhName;

    private List<AnnouncementFile> fileList;

    private List<Integer> roleList;

}