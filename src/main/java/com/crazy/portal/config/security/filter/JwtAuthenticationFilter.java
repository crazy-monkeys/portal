package com.crazy.portal.config.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.crazy.portal.config.security.JwtAuthenticationToken;
import com.crazy.portal.service.system.PermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.www.NonceExpiredException;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 资源访问过滤器
 * 1.token认证
 * 2.url鉴权
 * @Author: Bill
 * @Date: created in 20:03 2019/4/20
 * @Modified by:
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private List<RequestMatcher> permissiveRequestMatchers;
    private AuthenticationManager authenticationManager;
    private AuthenticationSuccessHandler successHandler;
    private AuthenticationFailureHandler failureHandler;
    private AntPathMatcher antPathMatcher;

    public JwtAuthenticationFilter() {
        this.successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        this.failureHandler = new SimpleUrlAuthenticationFailureHandler();
        this.antPathMatcher = new AntPathMatcher();
    }

    @Resource
    PermissionService permissionService;

    /**
     * 资源访问过滤器
     * 1.token认证
     * 2.url鉴权
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String url = request.getRequestURI();
        if (url.contains("/login") || url.equals("/") || url.contains(".html")) {
            //放行
            filterChain.doFilter(request, response);
            return;
        }
        Throwable throwable;
        try {
            String token = getJwtToken(request);
            if(StringUtils.isEmpty(token)){
                this.authenticationFailure(request, response,new InsufficientAuthenticationException("JWT is Empty"));
                return;
            }
            else {
                JwtAuthenticationToken authToken = new JwtAuthenticationToken(JWT.decode(token));
                Authentication authResult = this.getAuthenticationManager().authenticate(authToken);
                //token认证不成功，或者不具有访问url的权限
                if(authResult == null || !authRequest(request,authResult)){
                    this.authenticationFailure(request, response,
                            new InsufficientAuthenticationException("Insufficient permissions"));
                    return;
                }
                //放行
                this.successfulAuthentication(request, response, filterChain, authResult);
                filterChain.doFilter(request, response);
                return;
            }
        } catch(JWTDecodeException e) {
            logger.error("JWT format error!", e);
            throwable = e;
        } catch (NonceExpiredException e){
            logger.error("token failure!", e);
            throwable = e;
        } catch (BadCredentialsException e){
            logger.error("bad credentials!", e);
            throwable = e;
        } catch (Exception e){
            logger.error("runtime exception!", e);
            this.authenticationFailure(request, response,
                    new DisabledException("system disabled！", e));
            return;
        }
        this.authenticationFailure(request, response,
                new InsufficientAuthenticationException("authentication failure！", throwable));
        return;
    }

    /**
     * 检查请求的url 是否具备访问权限
     * @param request
     * @return
     */
    protected boolean authRequest(HttpServletRequest request,Authentication authResult) {
        if(authResult == null){
            return false;
        }
        //可以忽略权限的url
        if(permissiveRequestMatchers != null && !permissiveRequestMatchers.isEmpty()){
            for(RequestMatcher permissiveMatcher : permissiveRequestMatchers) {
                if(permissiveMatcher.matches(request)){
                    return true;
                }
            }
        }
        try {
            //用户在系统的权限
            UserDetails userDetails = (UserDetails)authResult.getPrincipal();
            List<com.crazy.portal.entity.system.Resource> resources = permissionService.findAllPerMissionByUserId(userDetails.getUsername());
            String requestUrl = request.getRequestURI();
            for(com.crazy.portal.entity.system.Resource resource : resources){
                if(antPathMatcher.match(resource.getPermissionPrefixUrl(),requestUrl)){
                    return true;
                }
            }
            logger.warn("权限不足!");
            return false;
        } catch (Exception e) {
            throw new RuntimeException("查询用户权限发生异常",e);
        }
    }

    protected String getJwtToken(HttpServletRequest request) {
        String authInfo = request.getHeader("Authorization");
        return StringUtils.removeStart(authInfo, "Bearer ");
    }

    protected void authenticationFailure(HttpServletRequest request,
                                         HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {

        SecurityContextHolder.clearContext();
        failureHandler.onAuthenticationFailure(request, response, failed);
    }

    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException{
        SecurityContextHolder.getContext().setAuthentication(authResult);
        successHandler.onAuthenticationSuccess(request, response, authResult);
    }

    protected AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public void setPermissiveUrl(String... urls) {
        if(permissiveRequestMatchers == null)
            permissiveRequestMatchers = new ArrayList<>();
        for(String url : urls)
            permissiveRequestMatchers .add(new AntPathRequestMatcher(url));
    }

    public void setAuthenticationSuccessHandler(
            AuthenticationSuccessHandler successHandler) {
        Assert.notNull(successHandler, "successHandler cannot be null");
        this.successHandler = successHandler;
    }

    public void setAuthenticationFailureHandler(
            AuthenticationFailureHandler failureHandler) {
        Assert.notNull(failureHandler, "failureHandler cannot be null");
        this.failureHandler = failureHandler;
    }

    @Override
    public void afterPropertiesSet() {
        Assert.notNull(authenticationManager, "authenticationManager must be specified");
        Assert.notNull(successHandler, "AuthenticationSuccessHandler must be specified");
        Assert.notNull(failureHandler, "AuthenticationFailureHandler must be specified");
    }
}
