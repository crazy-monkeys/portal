package com.crazy.portal.bean.business.idr.call;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description TODO
 * @Author Shawn
 * @Date 2019-08-23 23:12
 * @Modify by
 */
@Getter
@Setter
public class ApprovalResult {

    private String result;

    private String message;

    public void success(){
        this.result = "1";
        this.message = "导入成功";
    }
    public void fail(String message){
        this.result = "0";
        this.message = message;
    }
}
