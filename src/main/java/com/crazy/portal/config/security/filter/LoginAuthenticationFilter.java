package com.crazy.portal.config.security.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.crazy.portal.util.PortalUtil;
import com.crazy.portal.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Optional;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 19:16 2019/4/20
 * @Modified by:
 */
@Slf4j
public class LoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter{

    public LoginAuthenticationFilter() {
        //拦截url为 "/user/login" 的POST请求
        super(new AntPathRequestMatcher("/user/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException {

        String body = StreamUtils.copyToString(request.getInputStream(), Charset.forName("UTF-8"));
        String loginName = "", loginPwd = "";
        if(StringUtils.hasText(body)) {
            JSONObject jsonObj = JSON.parseObject(body);
            String userNameStr = jsonObj.getString("loginName");
            String passStr = jsonObj.getString("loginPwd");
            checkVerifyCode(jsonObj);
            if(userNameStr != null) loginName = userNameStr;
            if(passStr != null) loginPwd = passStr;
        }
        loginName = loginName.trim();
        //封装到token中提交
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                loginName, loginPwd);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private void checkVerifyCode(JSONObject jsonObj) {
        String verifyCode = jsonObj.getString("verifyCode");
        String timestamp = jsonObj.getString("timestamp");
        Object requestVerifyCode = PortalUtil.VERIFY_CODE_MAP.getIfPresent(timestamp);
        boolean isSuccess = StringUtil.isBlank(verifyCode) || !StringUtil.equals(verifyCode, String.valueOf(requestVerifyCode));
        if(!"sit".equals(PortalUtil.ENVIRONMENT) && !"dev".equals(PortalUtil.ENVIRONMENT) && isSuccess){
            throw new BadCredentialsException("Verify Code Inaccurate");
        }
    }

    public static void main(String[] args) {
        PortalUtil.VERIFY_CODE_MAP.put("1", "111");
        System.out.println(PortalUtil.VERIFY_CODE_MAP.getIfPresent("1"));
    }

}
