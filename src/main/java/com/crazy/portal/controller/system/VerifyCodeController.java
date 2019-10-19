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
        // 调用工具类生成的验证码和验证码图片
        Map<String, Object> codeMap = VerifyCodeUtil.generateCodeAndPic();
        // 将四位数字的验证码保存到Session中。
        PortalUtil.VERIFY_CODE_MAP.put(req.getParameter("timestamp"), codeMap.get("code").toString());
        // 禁止图像缓存。
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", -1);
        resp.setContentType("image/jpeg");
        ServletOutputStream sos;
        try {
            sos = resp.getOutputStream();
            ImageIO.write((RenderedImage) codeMap.get("codePic"), "jpeg", sos);
            sos.close();
        } catch (IOException e) {
            log.error("获取验证码异常", e);
        }
    }
}
