package com.crazy.portal.entity.company;

import lombok.Data;

import java.util.Date;

@Data
public class TCompanyFile {
    private Integer fileId;

    private Integer companyId;

    private String fileName;

    private String fileType;

    private String subType;

    private String ossName;

    private String ossPath;

    private Date uploadTime;

    private Integer createUserId;

    private Short active;
}