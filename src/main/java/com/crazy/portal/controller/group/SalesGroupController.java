package com.crazy.portal.controller.group;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.service.group.SalesGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @ClassName: SalesGroupController
 * @Author: God Man Qiu~
 * @Date: 2019/8/25 13:34
 */
@Slf4j
@RestController(value = "/group")
public class SalesGroupController extends BaseController{
    @Resource
    private SalesGroupService salesGroupService;

    @GetMapping(value = "/list")
    public BaseResponse getList(){
        return successResult(salesGroupService.selectSalesGroup());
    }
}
