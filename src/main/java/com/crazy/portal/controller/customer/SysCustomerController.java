package com.crazy.portal.controller.customer;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.service.customer.CustomerInfoService;
import com.crazy.portal.service.group.SalesGroupService;
import com.crazy.portal.service.system.SysParamService;
import com.crazy.portal.service.system.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName: SysCustomerController
 * @Author: God Man Qiu~
 * @Date: 2019/9/9 15:05
 */
@Slf4j
@RestController
@RequestMapping("/sys")
public class SysCustomerController extends BaseController {
    @Resource
    private CustomerInfoService customerInfoService;
    @Resource
    private SysParamService sysParamService;
    @Resource
    private SalesGroupService salesGroupService;
    @Resource
    private UserService userService;

    @GetMapping("/cusotmer/all")
    public BaseResponse getAllCustomer(){
        return successResult(customerInfoService.selecAllCustomer());
    }

    /**
     * 获取代理商的内外部客户
     * @return
     */
    @GetMapping(value = "/ships")
    public BaseResponse selectShips(){
        return successResult(userService.getUserShips(getCurrentUser().getDealerId()));
    }

    @GetMapping("/selectByMAndF/{model}/{function}")
    public BaseResponse selectByMAndF(@PathVariable("model") String model, @PathVariable("function") String function){
        return super.successResult(sysParamService.selectParam(model, function));
    }

    /**
     * 获取销售组织
     * @return
     */
    @GetMapping(value = "/list")
    public BaseResponse getList(){
        return successResult(salesGroupService.selectSalesGroup());
    }
}
