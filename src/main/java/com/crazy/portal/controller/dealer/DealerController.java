package com.crazy.portal.controller.dealer;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.entity.customer.CustomerInfo;
import com.crazy.portal.service.dealer.DealerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * @ClassName: DealerController
 * @Author: God Man Qiu~
 * @Date: 2019/7/23 18:14
 */
@Slf4j
@RestController
@RequestMapping("/user/dealer")
public class DealerController extends BaseController{

    @Resource
    private DealerService dealerService;

    /**
     * 获取Dealer的个人信息
     * 取当前用户
     * @return
     */
    @GetMapping("/getDealerInfo")
    public BaseResponse getDealerInfo(){
        return super.successResult(dealerService.getDealerInfo(this.getCurrentUser().getDealerId()));
    }

    /**
     * 修改Dealer信息
     */
    @PostMapping("/updateDealerInfo")
    public BaseResponse updateDealerInfo(@RequestBody CustomerInfo vo){
        dealerService.updateDealerInfo(vo, this.getCurrentUser().getDealerId());
        return successResult();
    }
}
