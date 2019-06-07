package com.crazy.portal.entity.announcement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class AnnouncementFile {

    private Integer id;
    @JsonIgnore
    private Integer announcementId;

    private String fileName;

    private String fileStoragePath;
    @JsonIgnore
    private String active;

}