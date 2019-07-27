package com.crazy.portal.controller.dealer;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.entity.system.User;
import com.crazy.portal.service.dealer.DealerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName: DealerController
 * @Author: God Man Qiu~
 * @Date: 2019/7/23 18:14
 */
@Slf4j
@RestController
@RequestMapping("/dealer")
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
        User user = getCurrentUser();
        user.setDealerId(1);
        return super.successResult(dealerService.getDealerInfo(user.getDealerId()));
    }

    /**
     * 修改Dealer信息
     */
    @RequestMapping("/updateDealerInfo")
    public BaseResponse updateDealerInfo(){

        return successResult();
    }
}
