package com.crazy.portal.controller.system;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.entity.system.SysParameter;
import com.crazy.portal.service.system.SysParamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by weiying on 2019/7/28.
 */
@Slf4j
@RestController
@RequestMapping("/sys/param")
public class SysParamController extends BaseController {
    @Resource
    private SysParamService sysParamService;

    @GetMapping("/selectAll")
    public BaseResponse selectAll(){
        return super.successResult(sysParamService.selectAll());
    }

    @GetMapping("/selectAllModel")
    public BaseResponse selectAllModel(){
        return super.successResult(sysParamService.selectAllModel());
    }

    @GetMapping("/selectAllFunction/{model}")
    public BaseResponse selectAllFunction(@PathVariable String model){
        return super.successResult(sysParamService.selectAllFunction(model));
    }

    @GetMapping("/selectByMAndF/{model}/{function}")
    public BaseResponse selectByMAndF(@PathVariable String model, @PathVariable String function){
        return super.successResult(sysParamService.selectParam(model, function));
    }

    @PostMapping("/saveOrUpdate")
    public BaseResponse saveOrUpdate(@RequestParam SysParameter sysParameter){
        sysParamService.saveOrUpdate(sysParameter);
        return super.successResult();
    }

}
