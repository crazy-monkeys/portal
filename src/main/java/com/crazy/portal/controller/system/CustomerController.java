package com.crazy.portal.controller.system;

import com.crazy.portal.bean.customer.SaveCustomerVO;
import com.crazy.portal.entity.customer.TCustomerAddress;
import com.crazy.portal.entity.customer.TCustomerContacts;
import com.crazy.portal.entity.customer.TCustomerInfo;
import com.crazy.portal.entity.customer.TCustomerProject;
import com.crazy.portal.service.customer.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: CustomerController
 * @Author: God Man Qiu~
 * @Date: 2019/4/20 01:47
 */
@Slf4j
@RestController
@RequestMapping("/customer")
@Deprecated
public class CustomerController {
    @Resource
    private CustomerService customerService;

    @RequestMapping("/list")
    public List<TCustomerInfo> list(){
        return customerService.selectAllCustomer();
    }

    @RequestMapping("/approvalList")
    public List<TCustomerInfo> approvalList(){
        return customerService.approvalList();
    }

    @RequestMapping("/create")
    public Boolean create(@RequestBody SaveCustomerVO info){
        customerService.saveCustomer(info);
        return true;
    }

    @RequestMapping("/CustomerInfo")
    public TCustomerInfo selectCustomerInfo(String registId){
        return customerService.selectByRegist(Integer.valueOf(registId));
    }

    @RequestMapping("/approval")
    public Boolean approval(Integer registId){
        try{
            customerService.approval(registId);
            return true;
        }catch (Exception e){
            log.error("",e);
            return false;
        }
    }
}
