package com.crazy.portal.entity.cusotmer;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * 客户附件
 * @author weiying
 * @date   2019-08-29 16:10::00
 */
@Data
public class CustomerFile {
    /**
     * 
     */
    private Integer fileId;

    /**
     * 
     */
    private Integer custId;

    /**
     * 
     */
    private String custCode;

    /**
     * A001 - 营业执照/商业登记证，A002 - 开票信息，A003 - 关联公司证明，A004 - 法人身份证，A005 - 收货委托证明，A006 - 其他；
客户(Account): C001 - 营业执照/商业登记证，C002 - 银行开户证明，C003 - 组织架构图，C004 - 母公司营业执照/商业登记证，C005 - 其他
     */
    private String type;

    /**
     * 
     */
    private String title;

    /**
     * 
     */
    private Date uploadTime;

    /**
     * 
     */
    private Integer active;

    /**
     * 
     */
    private String fileName;

    /**
     * 
     */
    private String filePath;


    private String fileType;

    private MultipartFile file;

    private Integer index;
}