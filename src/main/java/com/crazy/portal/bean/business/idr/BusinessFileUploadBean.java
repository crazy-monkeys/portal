package com.crazy.portal.bean.business.idr;

import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.List;

/**
 * 文件上传
 */
@Data
public class BusinessFileUploadBean {
    /** 文件名 **/
    private String fileName;
    /** 文件地址 **/
    private String filePath;
    /** 文件类型 **/
    private Integer fileType;

    private List<BaseRowModel> idrList;
}
