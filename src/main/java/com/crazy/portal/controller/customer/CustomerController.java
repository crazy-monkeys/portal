package com.crazy.portal.controller.customer;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/customers")
    public BaseResponse customers(){
        try {
            //TODO
        } catch (Exception e) {
            log.error("查询客户列表异常", e);
        }
        return successResult();
    }

    @GetMapping("/customer/{id}")
    public BaseResponse customer(@PathVariable Long id){
        try {
            //TODO
        } catch (Exception e) {
            log.error("查询客户详情异常", e);
        }
        return successResult();
    }

    @PostMapping("/customer")
    public BaseResponse customer(){
        try {
            //TODO
        } catch (Exception e) {
            log.error("客户报备异常", e);
        }
        return successResult();
    }
}
