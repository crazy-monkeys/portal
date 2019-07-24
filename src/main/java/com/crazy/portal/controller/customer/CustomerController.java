package com.crazy.portal.controller.customer;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.customer.CustomerQueryBean;
import com.crazy.portal.bean.customer.CustomerVO;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.entity.customer.CustBasicInfo;
import com.crazy.portal.service.customer.CustomersService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description 客户管理
 * @Author Shawn
 * @Date 2019-07-11 22:00
 * @Modify by
 */
@Slf4j
@RestController
@RequestMapping("/cust")
public class CustomerController extends BaseController{

    @Resource
    private CustomersService customersService;

    @GetMapping("/customers")
    public BaseResponse customers(@RequestBody CustomerQueryBean bean){
        try {
            PageInfo page = customersService.queryCustByPage(bean);
            return successResult(page);
        } catch (Exception e) {
            log.error("查询客户列表异常", e);
            return errorResult();
        }

    }

    @GetMapping("/customer/{id}")
    public BaseResponse customer(@PathVariable Long id){
        try {
            CustomerVO vo = customersService.queryCustDetail(id);
            return successResult(vo);
        } catch (Exception e) {
            log.error("查询客户详情异常", e);
            return errorResult();
        }
    }

    @PostMapping("/customer")
    public BaseResponse customer(CustomerVO vo){
        try {
            customersService.update(vo);
            return successResult();
        } catch (Exception e) {
            log.error("客户报备异常", e);
            return errorResult();
        }
    }
}
