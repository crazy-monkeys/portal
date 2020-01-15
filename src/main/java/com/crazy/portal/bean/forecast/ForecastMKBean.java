package com.crazy.portal.bean.forecast;

import com.crazy.portal.bean.common.PageBean;
import lombok.Data;

/**
 * @ClassName: ForecastMKBean
 * @Author: God Man Qiu~
 * @Date: 2020/1/15 18:45
 */
@Data
public class ForecastMKBean extends PageBean {
    private Integer dealerId;
    private String dealerName;
    private Integer insertS;
    private Integer updateS;
}
