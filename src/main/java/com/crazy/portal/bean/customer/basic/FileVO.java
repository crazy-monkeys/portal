package com.crazy.portal.bean.customer.basic;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName: FileVO
 * @Author: God Man Qiu~
 * @Date: 2019/7/24 15:30
 */
@Data
public class FileVO {
    private Integer fileType;
    private String fileName;
    private String filePath;
    private Date createTime;
}
