package com.crazy.portal.controller.system;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.service.system.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Slf4j
@RequestMapping("/ad")
public class ADController extends BaseController {


    @Resource
    private PermissionService permissionService;

    @GetMapping("/index")
    public void authTicket(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        response.addHeader("WWW-Authenticate", "Negotiate");
        response.setStatus(401);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ad/authTicket");
        log.info("AD user single sign-on.....");
        dispatcher.forward(request, response);
    }

    @GetMapping("/authTicket")
    public BaseResponse getADPermissions(){
        return super.successResult();
    }
}
