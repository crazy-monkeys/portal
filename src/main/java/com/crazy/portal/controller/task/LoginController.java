package com.crazy.portal.controller.task;

import com.crazy.portal.service.system.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 登录
 * @author Shawn
 * @date 2017-10-11
 */
@Slf4j
@RestController
public class LoginController {
	
	@Value("${login.username}")
	private String userName;
	@Value("${login.password}")
	private String password;
	@Resource
	private UserService userService;

	/**
	 * 登录验证
	 * @param userName
	 * @param password
	 * @return
	 */
	@RequestMapping("/loginCheck")
	public String loginCheck(String userName, String password, String verifyCode, HttpServletRequest request){
		if(!userService.checkVerifyCode(verifyCode,request)){
			return "verify_code_error";
		}
		if(userName.equals(this.userName) && password.equals(this.password)){
			request.getSession().setAttribute("isLogin", true);
			return "succeed";
		}else{
			return "fail";
		}
	}
}
