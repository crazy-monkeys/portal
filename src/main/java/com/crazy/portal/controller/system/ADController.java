package com.crazy.portal.controller.system;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.service.system.PermissionService;
import com.crazy.portal.util.ErrorCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
@RequestMapping("/ad")
public class ADController extends BaseController {

    @GetMapping("/index")
    public void index() {
        log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    @GetMapping("/forward")
    public BaseResponse authTicket(HttpServletResponse response){
        response.setStatus(401);
        response.setContentType("application/json;charset=utf-8");
        return new BaseResponse(ErrorCodes.SystemManagerEnum.ACCOUNT_ERROR.getCode(),
                ErrorCodes.SystemManagerEnum.ACCOUNT_ERROR.getZhMsg());
    }
}
