package com.crazy.portal.controller.forecast;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.forecast.ForecastMKBean;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.entity.forecast.ForecastMk;
import com.crazy.portal.service.forecast.ForecastMKService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName: ForecastMkController
 * @Author: God Man Qiu~
 * @Date: 2020/1/15 18:53
 */
@RestController
@RequestMapping("/forecast")
public class ForecastMkController extends BaseController{
    @Resource
    private ForecastMKService forecastMKService;

    @GetMapping("/list")
    public BaseResponse list(ForecastMKBean bean){
        return successResult(forecastMKService.list(bean));
    }

    //新增
    @PostMapping("/save")
    public BaseResponse save(@RequestBody ForecastMKBean bean){
        forecastMKService.save(bean.getDealerId(), bean.getDealerName());
        return successResult();
    }

    @PostMapping("/update")
    public BaseResponse update(@RequestBody ForecastMk mk){
        forecastMKService.update(mk);
        return successResult();
    }
}
