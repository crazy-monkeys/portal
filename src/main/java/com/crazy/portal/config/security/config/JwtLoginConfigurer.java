package com.crazy.portal.config.security.config;

import com.crazy.portal.config.security.filter.JwtAuthenticationFilter;
import com.crazy.portal.config.security.handler.AuthenticationFailHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 20:11 2019/4/20
 * @Modified by:
 */
public class JwtLoginConfigurer <T extends JwtLoginConfigurer<T, B>,
        B extends HttpSecurityBuilder<B>> extends AbstractHttpConfigurer<T, B> {

    private JwtAuthenticationFilter authFilter;

    public JwtLoginConfigurer() {
        this.authFilter = new JwtAuthenticationFilter();
    }

    @Override
    public void configure(B http){
        authFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        authFilter.setAuthenticationFailureHandler(new AuthenticationFailHandler());
        //将filter放到logoutFilter之前
        JwtAuthenticationFilter filter = postProcess(authFilter);
        http.addFilterBefore(filter, LogoutFilter.class);
    }

    //设置匿名用户可访问url
    public JwtLoginConfigurer<T, B> permissiveRequestUrls(String ... urls){
        authFilter.setPermissiveUrl(urls);
        return this;
    }

    public JwtLoginConfigurer<T, B> tokenValidSuccessHandler(AuthenticationSuccessHandler successHandler){
        authFilter.setAuthenticationSuccessHandler(successHandler);
        return this;
    }
}
