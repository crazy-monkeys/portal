package com.crazy.portal.bean.customer.basic;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: UploadFileVO
 * @Author: God Man Qiu~
 * @Date: 2019/9/1 21:23
 */
@Data
public class UploadFileVO {
    private String type;
    private MultipartFile file;
}
