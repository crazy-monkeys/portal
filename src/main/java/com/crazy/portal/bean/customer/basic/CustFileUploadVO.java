package com.crazy.portal.bean.customer.basic;

import com.crazy.portal.entity.cusotmer.CustomerFile;
import lombok.Data;
import java.util.List;

/**
 * @ClassName: CustFileUploadVO
 * @Author: God Man Qiu~
 * @Date: 2019/10/8 13:52
 */
@Data
public class CustFileUploadVO {
    private List<CustomerFile> files;
    private String outCode;
}
