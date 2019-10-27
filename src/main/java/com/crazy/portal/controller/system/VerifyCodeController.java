package com.crazy.portal.controller.system;

import com.crazy.portal.service.system.UserService;
import com.crazy.portal.util.PortalUtil;
import com.crazy.portal.util.VerifyCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Map;

/**
 * 获取验证码
 */
@Slf4j
@RestController
public class VerifyCodeController {

    @Resource
    private UserService userService;

    @GetMapping("/verifyCode")
    public void getCode(HttpServletRequest req, HttpServletResponse resp){
        userService.getVerifyCode(req, resp);
    }
}
