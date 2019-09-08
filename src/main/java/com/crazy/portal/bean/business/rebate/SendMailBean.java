package com.crazy.portal.bean.business.rebate;

import lombok.*;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class SendMailBean {

    /** 邮箱 **/
    private String email;
    /** 模板名 **/
    private String templateName;
    /** 标题 **/
    private String title;
    /** 数据 **/
    private Map<String, Object> data;
}
