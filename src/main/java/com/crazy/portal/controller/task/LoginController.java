package com.crazy.portal.controller.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	/**
	 * 登录验证
	 * @param userName
	 * @param password
	 * @return
	 */
	@RequestMapping("/loginCheck")
	public String loginCheck(String userName, String password, String verifyCode, HttpServletRequest request){
		if(!verifyCode.equals(request.getSession().getAttribute("verifyCode"))){
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
