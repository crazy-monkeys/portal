package com.crazy.portal.controller.customer;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.service.customer.CustomerInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName: SysCustomerController
 * @Author: God Man Qiu~
 * @Date: 2019/9/1 18:18
 */
@RestController(value = "/sys")
public class SysCustomerController extends BaseController{
    @Resource
    private CustomerInfoService customerInfoService;

    @GetMapping(value = "/cusotmer/all")
    public BaseResponse getAllCustomer(){
        return successResult(customerInfoService.selecAllCustomer());
    }
}
