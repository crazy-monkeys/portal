package com.crazy.portal.bean.handover;

import lombok.Data;

/**
 * Created by lee on 2019/8/6.
 */

@Data
public class DeliverUploadVO {

    /** 是否存在错误信息 1:存在 0:不存在 */
    private Integer isError = 1;

    /** 包含错误信息的文件名 */
    private String errorFileName;

    /** 全部校验通过 则将数据返回给前端 */
    private Object details;

    /** 校验成功后的记录ID */
    private Integer recordId;

    /** 当前操作结果描述信息 */
    private String msg;

}
