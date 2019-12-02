package com.crazy.portal.bean.business.idr;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

/**
 * @Description: 上传
 * @Author Shawn
 * @Date 2019-09-22 17:49
 * @Modify by
 */
@Getter
@Setter
public class IdrUploadBean {

    @JSONField(serialize = false)
    private MultipartFile file;
    /** 保差退ID **/
    private Integer id;
    /** 保差退类型 1.保价 2.差价补偿 3.退换货 **/
    private Integer idrType;
    /** 文件类型   1：普通附件 2：保差退附件 3：财务完结附件 **/
    private Integer fileType;
    /** CR金额 **/
    private BigDecimal crAmount;
}
