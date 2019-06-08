package com.crazy.portal.config.security;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;

/**
 * @Desc:鉴权
 * @Author: Bill
 * @Date: created in 10:54 2019/6/7
 * @Modified by:
 */
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource{

    private JwtUserService jwtUserService;

    private AntPathMatcher antPathMatcher;

    public UrlFilterInvocationSecurityMetadataSource(JwtUserService jwtUserService){
        this.jwtUserService = jwtUserService;
        antPathMatcher = new AntPathMatcher();
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        if ("/login".equals(requestUrl)) {
            return null;
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
