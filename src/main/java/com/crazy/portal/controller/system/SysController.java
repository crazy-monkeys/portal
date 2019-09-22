package com.crazy.portal.controller.system;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.dao.system.InternalUserMapper;
import com.crazy.portal.service.customer.CustomerInfoService;
import com.crazy.portal.service.group.SalesGroupService;
import com.crazy.portal.service.system.SysParamService;
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
public class SysController extends BaseController {
    @Resource
    private CustomerInfoService customerInfoService;
    @Resource
    private SysParamService sysParamService;
    @Resource
    private SalesGroupService salesGroupService;
    @Resource
    private InternalUserMapper internalUserMapper;

    @GetMapping("/customer/all")
    public BaseResponse getAllCustomer(){
        return successResult(customerInfoService.selecAllCustomer());
    }

    @GetMapping("/sales/list")
    public BaseResponse getAllSales(){
        return successResult(internalUserMapper.selectSales());
    }

    @GetMapping("/dealer/list")
    public BaseResponse getDealerList(){
        return successResult(customerInfoService.getDealerList());
    }

    @GetMapping("/dealer/getCredit/{dealerId}")
    public BaseResponse getDealerCredit(@PathVariable("dealerId") Integer dealerId){
        return successResult(customerInfoService.getDealerCredit(dealerId));
    }

    @GetMapping("/selectCustomerShip/{custId}")
    public BaseResponse selectCustomerShip(@PathVariable("custId") Integer custId){
        return super.successResult(customerInfoService.selectCustShip(custId));
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
