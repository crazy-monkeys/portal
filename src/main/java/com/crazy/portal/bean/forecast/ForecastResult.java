package com.crazy.portal.bean.forecast;

import lombok.Data;

/**
 * Created by lee on 2019/8/31.
 */

@Data
public class ForecastResult {

    //是否存在错误
    private boolean isSuccess = false;

//    private String fileName;

    private Object data;

    private String batchNo;

}
