package com.crazy.portal.bean.business.rebate;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Description 上传文件
 * @Author Shawn
 * @Date 2019-09-25 23:01
 * @Modify by
 */
@Data
public class RebateUploadBean {

    /** 客户简称 **/
    @NotBlank(message = "客户简称不能为空")
    private String customerShortName;
    /** 销售名称 **/
    private String salesName;
    /** 阿米巴队长 **/
    private String amebaHeader;
    /** 阿米巴部门/代表处 **/
    private String amebaDepartment;
    /** 出货公司 **/
    private String shipmentCompany;
    /** 执行方 **/
    @NotBlank(message = "执行方不能为空")
    private String executor;
    /** 通知日期 **/
    @NotNull(message = "通知日期不能为空")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date noticeDate;

    /** 展锐执行日期 **/
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date zrExecuteDate;

    /** 文件 **/
    private MultipartFile file;
}
