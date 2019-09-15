package com.crazy.portal.controller.system;

import com.crazy.portal.annotation.OperationLog;
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
@RequestMapping("/param")
public class SysParamController extends BaseController {
    @Resource
    private SysParamService sysParamService;


    @GetMapping("/selectAll")
    public BaseResponse selectAll(@RequestParam(required = false) Integer pModel,
                                  @RequestParam(required = false) Integer pFunction,
                                  @RequestParam(required = false) Integer active,
                                  @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                  @RequestParam(required = false,defaultValue = "10") Integer pageSize){
        return super.successResult(sysParamService.selectAll(pModel, pFunction, active, pageNum, pageSize));
    }

    @OperationLog
    @PostMapping("/saveOrUpdate")
    public BaseResponse saveOrUpdate(@RequestBody SysParameter sysParameter){
        sysParamService.saveOrUpdate(sysParameter, super.getCurrentUser().getId());
        return super.successResult();
    }
}
