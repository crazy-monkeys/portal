package com.crazy.portal.entity.customer;

import lombok.Data;

import java.util.Date;

/**
 * 附件
 */
@Data
public class CustEnclosure {

    private Long id;
    //附件类型
    private String type;
    //附件
    private String file;
    //上传时间
    private String uploadTime;
    //上传人
    private String uploadPerson;

    private Long createId;

    private Date createTime;

    private Long modifyId;

    private Date modifyTime;
}
