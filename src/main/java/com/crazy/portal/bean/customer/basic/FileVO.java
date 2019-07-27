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

    //文件名称（a.pdf）
    private String fileName;

    //文件夹路径（/a/b/c/dir/）
    private String filePath;

    //文件地址全路径（/a/b/c/dir/a.pdf）
    private String fullPath;

    //创建时间
    private Date createTime;

}
