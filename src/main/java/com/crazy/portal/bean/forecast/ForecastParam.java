package com.crazy.portal.bean.forecast;

import com.crazy.portal.entity.forecast.ForecastLine;
import lombok.Data;

import java.util.List;

/**
 * Created by lee on 2019/8/31.
 */

@Data
public class ForecastParam {

    private Integer forecastId;

    private ForecastLine line;

}
