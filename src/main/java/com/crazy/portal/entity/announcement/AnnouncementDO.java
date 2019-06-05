package com.crazy.portal.entity.announcement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class AnnouncementDO {

    private Integer id;

    private String title;

    private Integer topmost;

    private Integer type;

    private Integer status;
    @JsonIgnore
    private Integer createUserId;
    @JsonIgnore
    private Date createTime;
    @JsonIgnore
    private Date releaseTime;
    @JsonIgnore
    private Date updateTime;

    private String content;

    //创建人
    private Integer createUserName;
    //创建时间
    private Date createTimeStr;
    //发布时间
    private Date releaseTimeStr;
    //修改时间
    private Date updateTimeStr;

}