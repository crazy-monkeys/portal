package com.crazy.portal.bean.business.idr;

import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.List;

@Data
public class BusinessFileUploadBean {

    private String fileName;

    private String filePath;

    private List<BaseRowModel> idrList;
}
